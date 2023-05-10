package com.pushpa.creditcardvalidation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    @NotEmpty
    @Size(min = 13, max = 16)
    private String creditCardNumber;
    @Min(3)
    private int cvv;
    @NotEmpty
    private String expiryDate;
    private String cardType;

    public CreditCard(String creditCardNumber, int cvv, String expiryDate, String cardType) {
    }

}
