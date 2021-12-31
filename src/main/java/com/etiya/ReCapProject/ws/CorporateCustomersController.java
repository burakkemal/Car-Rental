package com.etiya.ReCapProject.ws;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.ReCapProject.business.abstracts.CorporateCustomerService;
import com.etiya.ReCapProject.business.dtos.CorporateCustomerSearchListDto;
import com.etiya.ReCapProject.business.requests.corporateCustomerRequests.CreateCorporateCustomerRequest;
import com.etiya.ReCapProject.business.requests.corporateCustomerRequests.DeleteCorporateCustomerRequest;
import com.etiya.ReCapProject.business.requests.corporateCustomerRequests.UpdateCorporateCustomerRequest;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;

@RestController
@RequestMapping("/api/corporateCustomers/")
public class CorporateCustomersController {
	
	private CorporateCustomerService corporateCustomerService;
	
	@Autowired
	public CorporateCustomersController(CorporateCustomerService corporateCustomerService){
		this.corporateCustomerService = corporateCustomerService;
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateCorporateCustomerRequest createCorporateCustomerRequest){
		return this.corporateCustomerService.add(createCorporateCustomerRequest);
	}
	@PutMapping("/update")
	public Result update(@RequestBody @Valid UpdateCorporateCustomerRequest updateCorporateCustomerRequest){
		return this.corporateCustomerService.update(updateCorporateCustomerRequest);
	}
	@DeleteMapping("/delete")
	public Result delete(@RequestBody @Valid DeleteCorporateCustomerRequest deleteCorporateCustomerRequest){
		return this.corporateCustomerService.delete(deleteCorporateCustomerRequest);
	}
	
	@GetMapping("/getAll")
	public DataResult<List<CorporateCustomerSearchListDto>> getAll(){
		return this.corporateCustomerService.getAll();
	}
	
	@GetMapping("/getById")
	public DataResult<CorporateCustomerSearchListDto> getById(@RequestParam("corporateCustomerId") int corporateCustomerId){
		return this.corporateCustomerService.getById(corporateCustomerId);
	}
	
	
	

}
