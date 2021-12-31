package com.etiya.ReCapProject.business.abstracts;

import java.time.LocalDate;
import java.util.List;

import com.etiya.ReCapProject.business.dtos.InvoiceSearchListDto;
import com.etiya.ReCapProject.business.requests.invoiceRequests.CreateInvoiceRequest;
import com.etiya.ReCapProject.business.requests.invoiceRequests.DeleteInvoiceRequest;
import com.etiya.ReCapProject.business.requests.invoiceRequests.UpdateInvoiceRequest;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;

public interface InvoiceService {
	DataResult<List<InvoiceSearchListDto>> getAll();
	Result add(CreateInvoiceRequest createInvoiceRequest);
	Result delete(DeleteInvoiceRequest deleteInvoiceRequest);
	Result update(UpdateInvoiceRequest updateInvoiceRequest);
	DataResult<List<InvoiceSearchListDto>> getByCustomerId(int customerId);
	DataResult<List<InvoiceSearchListDto>> getBySelectedInterval(LocalDate beginDate, LocalDate endDate);
}
