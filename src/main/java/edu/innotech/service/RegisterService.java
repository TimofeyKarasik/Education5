package edu.innotech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import edu.innotech.dto.*;
import edu.innotech.mapper.CSAccountMapper;
import edu.innotech.mapper.ProductRegisterMapper;
import edu.innotech.model.*;
import edu.innotech.exceptions.BadFieldValueException;
import edu.innotech.exceptions.NoDataFoundException;
import edu.innotech.repo.TppProductRegisterRepo;
import edu.innotech.repo.TppRefProductRegisterTypeRepo;

import java.util.List;
import java.util.Optional;

@Service
public class RegisterService {

    private final TppRefProductRegisterTypeRepo registerTypeRepo;
    private final TppProductRegisterRepo registerRepo;
    private final AccountPoolService accountPoolService;
    private final ProductRegisterMapper registerMapper;
    private final CSAccountMapper csaAccountMapper;

    public RegisterService( @Autowired TppRefProductRegisterTypeRepo registerTypeRepo
            ,@Autowired TppProductRegisterRepo registerRepo
            ,@Autowired AccountPoolService accountPoolService
            ,@Autowired ProductRegisterMapper registerMapper
            ,@Autowired CSAccountMapper csaAccountMapper) {
        this.registerTypeRepo = registerTypeRepo;
        this.registerRepo = registerRepo;
        this.accountPoolService = accountPoolService;
        this.registerMapper = registerMapper;
        this.csaAccountMapper = csaAccountMapper;
    }

    public List<Long> getProductRegisterIds(CorporateSettlementInstance csi){
        return registerRepo.getRegisterIdsByProduct(csi.getInstanceId());
    }

    public List<TppRefProductRegisterType> getRegisterTypeListOrThrow(String registryTypeCode){
        List<TppRefProductRegisterType> list = registerTypeRepo.findByAccountType(registryTypeCode);
        if (list.size() > 0 ) return list;
        throw new NoDataFoundException("КодПродукта '"+registryTypeCode+"' не найдено в Каталоге продуктов tpp_ref_product_register_type");

    }

    public List<TppRefProductRegisterType> getRegisterTypeInstanceListOrThrow(String productCode){
        String accountType = "Клиентский";
        List<TppRefProductRegisterType> list = registerTypeRepo.findByProductCode(productCode, accountType);
        if (list.size() > 0 ) return list;
        throw new NoDataFoundException("КодПродукта '"+productCode+"' не найдено в Каталоге продуктов tpp_ref_product_class");

    }




    public TppProductRegister create(ProductRegisterDTO registerDTO){
        Account account = accountPoolService.getNextAccount(registerDTO.getBranchCode(), registerDTO.getCurrencyCode(), registerDTO.getMdmCode(), registerDTO.getPriorityCode(), registerDTO.getType());
        TppProductRegister registerEntity = registerMapper.toEntity(registerDTO);
        registerEntity.setAccount(account.getId());
        registerEntity.setAccountNumber(account.getAccountNumber());
        registerRepo.save(registerEntity);

        return registerEntity;
    }

    private void registerNotExistsOrThrown(CorporateSettlementAccount csa){
        Optional<TppProductRegister> register = registerRepo.findFirstByProductIdAndType(csa.getInstanceId(), csa.getAccountType());
        if (register.isEmpty()) return;
        throw new BadFieldValueException("Параметр registryTypeCode тип регистра '"
                + csa.getAccountType()
                + "' уже существует для ЭП с ИД  '"
                + register.get().getId() + "'.");
    }

    @Transactional(rollbackFor = {Exception.class})
    public RegisterResponse processRequest(CorporateSettlementAccount csa){
        System.out.println(csa);
        registerNotExistsOrThrown(csa);
        List<TppRefProductRegisterType> registerTypeList = getRegisterTypeListOrThrow(csa.getRegistryTypeCode());
        ProductRegisterDTO registerDTO = csaAccountMapper.toRegisterDTO(csa);
        TppProductRegister registerEntity = create(registerDTO);



        RegisterResponse registerResponse= new RegisterResponse(registerEntity.getAccount().toString());
        return registerResponse;
    }

}