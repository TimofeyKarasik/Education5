package edu.innotech.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import edu.innotech.dto.CorporateSettlementInstance;
import edu.innotech.dto.ProductResponse;
import edu.innotech.service.ProductService;

@RestController()
@Validated
public class CorporateSettlementInstanceCreate {
    @Autowired
    ProductService productService;
    @PostMapping("/corporate-settlement-instance/create")
    ProductResponse handle(@Valid @RequestBody CorporateSettlementInstance csi){
        ProductResponse productResponse = productService.processCreateRequest(csi);

        return productResponse;
    }

}