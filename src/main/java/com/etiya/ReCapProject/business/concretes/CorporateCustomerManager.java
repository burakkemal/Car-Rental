package com.etiya.ReCapProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import com.etiya.ReCapProject.business.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.CorporateCustomerService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.business.dtos.CorporateCustomerSearchListDto;
import com.etiya.ReCapProject.business.requests.corporateCustomerRequests.CreateCorporateCustomerRequest;
import com.etiya.ReCapProject.business.requests.corporateCustomerRequests.DeleteCorporateCustomerRequest;
import com.etiya.ReCapProject.business.requests.corporateCustomerRequests.UpdateCorporateCustomerRequest;
import com.etiya.ReCapProject.core.utilities.business.BusinessRules;
import com.etiya.ReCapProject.core.utilities.mapping.ModelMapperService;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.ErrorDataResult;
import com.etiya.ReCapProject.core.utilities.results.ErrorResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.results.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.CorporateCustomerDao;
import com.etiya.ReCapProject.entities.concretes.CorporateCustomer;

@Service
public class CorporateCustomerManager implements CorporateCustomerService {

	private CorporateCustomerDao corporateCustomerDao;
	private ModelMapperService modelMapperService;
	private UserService userService;



	@Autowired
	public CorporateCustomerManager(CorporateCustomerDao corporateCustomerDao, ModelMapperService modelMapperService, @Lazy UserService userService) {
		this.corporateCustomerDao = corporateCustomerDao;
		this.modelMapperService = modelMapperService;
		this.userService = userService;

	}

	@Override
	public DataResult<List<CorporateCustomerSearchListDto>> getAll() {
		List<CorporateCustomer> result = this.corporateCustomerDao.findAll();
		List<CorporateCustomerSearchListDto> response = result.stream().map(corporateCustomer -> modelMapperService
				.forDto().map(corporateCustomer, CorporateCustomerSearchListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CorporateCustomerSearchListDto>>(response, Messages.CUSTOMERLIST);

	}

	@Override
	public Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest) {
		var result = BusinessRules.run(checkIsCorporateCustomerEmailExists(createCorporateCustomerRequest.getEmail()),checkCorporateTaxNumberExists(createCorporateCustomerRequest.getTaxNumber()));
		if (result!= null) {
			return result;
		}
		CorporateCustomer corporateCustomer = modelMapperService.forRequest().map(createCorporateCustomerRequest,
				CorporateCustomer.class);
		this.corporateCustomerDao.save(corporateCustomer);
		return new SuccessResult(Messages.CUSTOMERADD);
	}

	@Override
	public Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
		Result result = BusinessRules.run(checkCorporateCustomerExistsBuId(updateCorporateCustomerRequest.getId()),checkCorporateTaxNumberExists(updateCorporateCustomerRequest.getTaxNumber()),
				checkIsCorporateCustomerEmailExists(updateCorporateCustomerRequest.getEmail()));
		if (result != null) {
			return result;
		}
		CorporateCustomer corporateCustomer = modelMapperService.forRequest().map(updateCorporateCustomerRequest,
				CorporateCustomer.class);
		this.corporateCustomerDao.save(corporateCustomer);
		return new SuccessResult(Messages.CUSTOMERUPDATE);
	}

	@Override
	public Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) {
		Result result = BusinessRules.run(checkCorporateCustomerExistsBuId(deleteCorporateCustomerRequest.getCustomerId()));
		if (result != null) {
			return result;
		}
		CorporateCustomer corporateCustomer = modelMapperService.forRequest().map(deleteCorporateCustomerRequest,
				CorporateCustomer.class);
		this.corporateCustomerDao.delete(corporateCustomer);
		return new SuccessResult(Messages.CUSTOMERDELETE);
	}

	@Override
	public DataResult<CorporateCustomerSearchListDto> getById(int corporateCustomerId) {

		var existsIndividualCustomer = this.corporateCustomerDao.existsById(corporateCustomerId);
		if (!existsIndividualCustomer) {
			return new ErrorDataResult(Messages.CUSTOMERNOTFOUND,null);
		}
		CorporateCustomer corporateCustomer = this.corporateCustomerDao.getById(corporateCustomerId);
		CorporateCustomerSearchListDto corporateCustomerSearchListDto = modelMapperService.forDto()
				.map(corporateCustomer, CorporateCustomerSearchListDto.class);
		return new SuccessDataResult<CorporateCustomerSearchListDto>(corporateCustomerSearchListDto,
				Messages.CUSTOMERGET);
	}

	private Result checkIsCorporateCustomerEmailExists(String email){
		var result = this.userService.isUserEmailExists(email);
		if(!result){
			return new ErrorResult(Messages.CUSTOMERISALREADYEXISTS);
		}
		return new SuccessResult();		
	}
	private Result checkCorporateCustomerExistsBuId(int customerId) {
		var result = this.corporateCustomerDao.existsById(customerId);
		if (!result) {
			return new ErrorResult(Messages.CUSTOMERNOTFOUND);
		}
		return new SuccessResult();
	}


	private Result checkCorporateTaxNumberExists(String taxNumber){
		var result = this.corporateCustomerDao.existsByTaxNumber(taxNumber);
		if (result){
			return new ErrorResult(Messages.CUSTOMERTAXNUMBEREXISTS);
		}
		return new SuccessResult();
	}

}
