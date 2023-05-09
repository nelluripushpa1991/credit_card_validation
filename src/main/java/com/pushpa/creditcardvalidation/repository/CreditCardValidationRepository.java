package com.pushpa.creditcardvalidation.repository;

import com.pushpa.creditcardvalidation.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditCardValidationRepository extends JpaRepository<CreditCard, Integer> {
    public List<CreditCard> findByCardType(String cardType);
}
