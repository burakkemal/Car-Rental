package com.etiya.ReCapProject.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.ReCapProject.entities.concretes.Payment;

public interface PaymentDao extends JpaRepository<Payment, Integer>{

}
