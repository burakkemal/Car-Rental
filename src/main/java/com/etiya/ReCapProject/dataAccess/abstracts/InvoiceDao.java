package com.etiya.ReCapProject.dataAccess.abstracts;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.ReCapProject.business.dtos.InvoiceSearchListDto;
import com.etiya.ReCapProject.entities.concretes.Invoice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InvoiceDao extends JpaRepository<Invoice, Integer>{
	
	List<Invoice> getByCustomer_Id(int id);

	List<Invoice> getByCreateDateBetween(LocalDate beginDate, LocalDate endDate);


	//int SumTotalAdditionalService();
	
	boolean existsByCustomerId(int customerId);
	boolean existsByInvoiceNumber(String invoiceNumber);

}
