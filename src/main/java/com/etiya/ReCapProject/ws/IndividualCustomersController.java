package com.etiya.ReCapProject.ws;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.etiya.ReCapProject.business.abstracts.IndividualCustomerService;
import com.etiya.ReCapProject.business.dtos.IndividualCustomerSearchListDto;
import com.etiya.ReCapProject.business.requests.individualRequests.CreateIndividualCustomerRequest;
import com.etiya.ReCapProject.business.requests.individualRequests.DeleteIndividualCustomerRequest;
import com.etiya.ReCapProject.business.requests.individualRequests.UpdateIndividualCustomerRequest;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;

@RestController
@RequestMapping("/api/individualCustomers")
public class IndividualCustomersController {

	private IndividualCustomerService individualCustomerService;

	@Autowired
	public IndividualCustomersController(IndividualCustomerService individualCustomerService) {
		super();
		this.individualCustomerService = individualCustomerService;
	}

	@GetMapping("/getAll")
	public DataResult<List<IndividualCustomerSearchListDto>> getAll() {
		return this.individualCustomerService.getAll();
	}

	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateIndividualCustomerRequest createIndividualCustomerRequest) {
		return this.individualCustomerService.add(createIndividualCustomerRequest);
	}

	@DeleteMapping("/delete")
	public Result delete(@RequestBody @Valid DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) {
		return this.individualCustomerService.delete(deleteIndividualCustomerRequest);
	}

	@PutMapping("/update")
	public Result update(@RequestBody @Valid  UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
		return this.individualCustomerService.update(updateIndividualCustomerRequest);
	}

	@GetMapping("/getById")
	public DataResult<IndividualCustomerSearchListDto> getByIndividualCustomerById(@RequestParam("individualCustomerId") int individualCustomerId) {
		return this.individualCustomerService.getByIndividualCustomerId(individualCustomerId);
	}

}
