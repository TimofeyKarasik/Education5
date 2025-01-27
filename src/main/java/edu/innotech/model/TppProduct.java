package edu.innotech.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Builder
@Entity
@Table(name="tpp_product")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TppProduct {
    @Id
    @SequenceGenerator(name="tpp_product_id_seq",
            sequenceName="tpp_product_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="tpp_product_id_seq")
    Long id;

    @Column(name="product_code_id")
    private Long productCodeId;

    @Column(name="client_id")
    private Long clientId;

    @Column(name="type")
    private String type;

    @Column(name="number")
    private String number;

    @Column(name="priority")
    private String priority;

    @Column(name="date_of_conclusion")
    private Date dateOfConclusion;

    @Column(name="start_date_time")
    private Date startDateTime;

    @Column(name="end_date_time")
    private Date endDateTime;

    @Column(name="days")
    private Long days;

    @Column(name="penalty_rate")
    private BigDecimal penaltyRate;

    @Column(name="nso")
    private BigDecimal nso;

    @Column(name="threshold_amount")
    private BigDecimal thresholdAmount;

    @Column(name="requisite_type")
    private String requisiteType;

    @Column(name="interest_rate_type")
    private String interestRateType;

    @Column(name="tax_rate")
    private BigDecimal taxRate;

    @Column(name="reasone_close")
    private String reasonClose;

    @Column(name="state")
    private String state;

}