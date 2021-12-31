package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.business.dtos.CorporateCustomerSearchListDto;
import com.etiya.ReCapProject.business.requests.corporateCustomerRequests.CreateCorporateCustomerRequest;
import com.etiya.ReCapProject.business.requests.corporateCustomerRequests.DeleteCorporateCustomerRequest;
import com.etiya.ReCapProject.business.requests.corporateCustomerRequests.UpdateCorporateCustomerRequest;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;

public interface CorporateCustomerService {
	
	DataResult<List<CorporateCustomerSearchListDto>> getAll();
	Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest);
	Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest);
	Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest);
	DataResult<CorporateCustomerSearchListDto> getById(int corporateCustomerId);
	
}
