package com.etiya.ReCapProject.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.ReCapProject.entities.concretes.CreditCard;

public interface CreditCardDao extends JpaRepository<CreditCard, Integer>{
    Boolean existsByCardNumber(String cardNumber);
}
