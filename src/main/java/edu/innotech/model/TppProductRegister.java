package edu.innotech.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Entity
@Table(name="tpp_product_register")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TppProductRegister {
    @Id
    @SequenceGenerator(name="tpp_product_register_id_seq",
            sequenceName="tpp_product_register_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="tpp_product_register_id_seq")
    private Long id;

    @Column(name="product_id")
    private Long productId;

    @Column(name="type", nullable = false)
    @Size(max=100)
    private String type;

    @Column(name="account")
    private Long account;

    @Size(max=30)
    @Column(name="currency_code")
    private String currencyCode;

    @Size(max=50)
    @Column(name="state")
    private String state;

    @Size(max=25)
    @Column(name="account_number")
    private String accountNumber;
}