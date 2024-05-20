package edu.innotech.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductResponse {
    private String instanceId;
    private List<Long> registerId = new ArrayList<>();
    private List<Long> supplementaryAgreementId = new ArrayList<>();
}