package edu.innotech.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import edu.innotech.dto.ProductRegisterDTO;
import edu.innotech.mapper.InstanceMapper;
import edu.innotech.model.*;
import edu.innotech.exceptions.BadFieldValueException;
import edu.innotech.exceptions.NoDataFoundException;
import edu.innotech.dto.CorporateSettlementInstance;
import edu.innotech.dto.ProductResponse;
import edu.innotech.repo.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Getter
@Setter
public class ProductService {
    private final TppProductRepo productRepo;
    private final TppRefProductClassRepo productClassRepo;
    private final AgreementService agreementService;
    private final RegisterService registerService;
    private final InstanceMapper instanceMapper;

    public ProductService(@Autowired TppProductRepo productRepo
            ,@Autowired TppRefProductClassRepo productClassRepo
            ,@Autowired AgreementService agreementService
            ,@Autowired RegisterService registerService
            ,@Autowired InstanceMapper instanceMapper) {
        this.productRepo = productRepo;
        this.productClassRepo = productClassRepo;
        this.agreementService = agreementService;
        this.registerService = registerService;
        this.instanceMapper = instanceMapper;
    }

    private void checkProductNotExistsOrThrow(String contructNumber){
        List<TppProduct> productList = productRepo.findByNumber(contructNumber);
        if (productList.size() > 0){
            throw new BadFieldValueException("Параметр ContractNumber № договора \""
                    + contructNumber
                    + "' уже существует для ЭП с ИД '"
                    + productList.get(0).getId()+"'"
            );
        }
    }

    private TppProduct getProductEntity(CorporateSettlementInstance csi){
        Optional<TppRefProductClass> productClass = productClassRepo.findByValue(csi.getProductCode());

        if (productClass.isEmpty()) {
            throw new NoDataFoundException("Не найдена запись [tpp_ref_product_class_repo] с полем value = "+csi.getProductCode());
        }

        TppProduct productEntity = instanceMapper.map(csi);
        productEntity.setProductCodeId(productClass.get().getInternalId());

        productRepo.save(productEntity);

        return productEntity;

    }
    private void create(CorporateSettlementInstance csi){
        checkProductNotExistsOrThrow(csi.getContractNumber());
        csi.getInstanceArrangement().forEach(agreement -> agreementService.checkAgreementNotExistsOrThrow(agreement.getNumber()));
        List<TppRefProductRegisterType> registerTypeList = registerService.getRegisterTypeInstanceListOrThrow(csi.getProductCode());
        csi.setInstanceId(getProductEntity(csi).getId());

        registerTypeList.forEach(registerType ->
        { ProductRegisterDTO registerDTO = instanceMapper.registerMap(csi);
            registerDTO.setType(registerType.getValue());
            registerService.create(registerDTO);
        });
        csi.getInstanceArrangement().forEach(agreement ->
        { agreement.setProductId(csi.getInstanceId());
            agreementService.create(agreement);
        });
    }

    private void checkProductExistOrThrow(Long id){

        try {
            TppProduct product = productRepo.findById(id).get();
        }catch(NoSuchElementException e){
            throw new NoDataFoundException("Экземпляр продукта с параметром instanceId '"+id+"' не найден.");
        }

    }

    private void update(CorporateSettlementInstance csi){
        checkProductExistOrThrow(csi.getInstanceId());
        csi.getInstanceArrangement().forEach(agreement->agreementService.checkAgreementNotExistsOrThrow(agreement.getNumber()));
        csi.getInstanceArrangement().forEach(agreement ->
        { agreement.setProductId(csi.getInstanceId());
            agreementService.create(agreement);
        });

    }

    @Transactional(rollbackFor = {Exception.class, NoDataFoundException.class})
    public ProductResponse processCreateRequest(CorporateSettlementInstance csi){
        System.out.println(csi);
        if (csi.getInstanceId() == null){
            create(csi);
        } else{
            update(csi);
        }
        ProductResponse response = new ProductResponse();
        response.setInstanceId(csi.getInstanceId().toString());
        response.setRegisterId( registerService.getProductRegisterIds(csi) );
        response.setSupplementaryAgreementId(agreementService.getProductAgreementIds(csi));
        return response;
    }
}