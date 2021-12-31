package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.business.dtos.IndividualCustomerSearchListDto;
import com.etiya.ReCapProject.business.requests.individualRequests.CreateIndividualCustomerRequest;
import com.etiya.ReCapProject.business.requests.individualRequests.DeleteIndividualCustomerRequest;
import com.etiya.ReCapProject.business.requests.individualRequests.UpdateIndividualCustomerRequest;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;

public interface IndividualCustomerService {
	DataResult<List<IndividualCustomerSearchListDto>> getAll();

	Result add(CreateIndividualCustomerRequest createIndividualRequest);

	Result delete(DeleteIndividualCustomerRequest deleteIndividualRequest);

	Result update(UpdateIndividualCustomerRequest updateIndividualRequest);

	DataResult<IndividualCustomerSearchListDto> getByIndividualCustomerId(int individualCustomerId);

}
