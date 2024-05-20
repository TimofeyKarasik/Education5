package edu.innotech.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class CorporateSettlementAccount {
    @Range(min=1,  message= "Имя обязательного параметра instanceId не заполнено.")
    private long instanceId;
    private String registryTypeCode;
    private String accountType;
    private String currencyCode;
    private String branchCode;
    private String priorityCode;
    private String mdmCode;
    private String clientCode;
    private String trainRegion;
    private String counter;
    private String salesCode;
}