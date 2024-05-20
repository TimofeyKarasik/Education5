package edu.innotech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.innotech.mapper.AgreementMapper;
import edu.innotech.model.Agreement;
import edu.innotech.exceptions.BadFieldValueException;
import edu.innotech.dto.AgreementDTO;
import edu.innotech.dto.CorporateSettlementInstance;
import edu.innotech.repo.AgreementRepo;

import java.util.List;

@Service
public class AgreementService {

    private final AgreementRepo agreementRepo;
    private final AgreementMapper agreementMapper;

    public AgreementService(@Autowired AgreementRepo agreementRepo, @Autowired AgreementMapper agreementMapper) {
        this.agreementRepo = agreementRepo;
        this.agreementMapper = agreementMapper;
    }

    public void checkAgreementNotExistsOrThrow(String number){
        List<Agreement> egreementList = agreementRepo.findByNumber(number);
        if (egreementList.size() > 0){
            throw new BadFieldValueException("Параметр № Дополнительного соглашения (сделки) Number '"
                    + number
                    + "' уже существует для ЭП с ИД '"
                    + egreementList.get(0).getId()+"'"
            );
        }
    }

    public List<Long> getProductAgreementIds(CorporateSettlementInstance csi){
        return agreementRepo.getAgreementIdsByProduct(csi.getInstanceId());
    }

    public void create(AgreementDTO arrangement){
        Agreement agreement = agreementMapper.toEntity(arrangement);
        agreementRepo.save(agreement);
    }

}