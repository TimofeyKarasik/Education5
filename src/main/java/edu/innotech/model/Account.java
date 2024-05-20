package edu.innotech.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Entity
@Table(name="account")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Account {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="account_pool_id")
    private Long accountPoolId;

    @Size(max=25)
    @Column(name="account_number")
    private String accountNumber;

    @Column(name="bussy")
    private Boolean busy;
}