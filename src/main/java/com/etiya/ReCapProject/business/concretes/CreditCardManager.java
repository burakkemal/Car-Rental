package com.etiya.ReCapProject.business.concretes;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.etiya.ReCapProject.business.abstracts.UserService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.CreditCardService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.business.requests.creditCardRequests.CreateCreditCardRequest;
import com.etiya.ReCapProject.business.requests.creditCardRequests.DeleteCreditCardRequest;
import com.etiya.ReCapProject.business.requests.creditCardRequests.UpdateCreditCardRequest;
import com.etiya.ReCapProject.core.utilities.business.BusinessRules;
import com.etiya.ReCapProject.core.utilities.mapping.ModelMapperService;
import com.etiya.ReCapProject.core.utilities.results.ErrorResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.core.utilities.results.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.CreditCardDao;
import com.etiya.ReCapProject.entities.concretes.CreditCard;

@Service
public class CreditCardManager implements CreditCardService {
	private CreditCardDao creditCardDao;
	private ModelMapperService modelMapperService;
	private UserService userService;

	@Autowired
	public CreditCardManager(CreditCardDao creditCardDao, ModelMapperService modelMapperService,UserService userService) {
		super();
		this.creditCardDao = creditCardDao;
		this.modelMapperService = modelMapperService;
		this.userService=userService;
	}
	@Override
	public Result delete(DeleteCreditCardRequest deleteCreditCardRequest) {
		CreditCard creditCard = modelMapperService.forRequest().map(deleteCreditCardRequest, CreditCard.class);
		this.creditCardDao.delete(creditCard);
		return new SuccessResult(Messages.CREDITCARDELETE);
	}

	@Override
	public Result update(UpdateCreditCardRequest updateCreditCardRequest) {
		CreditCard creditCard = modelMapperService.forRequest().map(updateCreditCardRequest, CreditCard.class);
		this.creditCardDao.save(creditCard);
		return new SuccessResult(Messages.CREDITCARDUPDATE);
	}

	@Override
	public Result add(CreateCreditCardRequest createCreditCardRequest) {
		Result result=BusinessRules.run(checkCreditCardNumber(createCreditCardRequest.getCardNumber()),
				checkIfCreditCardNumberExists(createCreditCardRequest.getCardNumber()),
				checkCreditCardExpiryDate(createCreditCardRequest.getExpirationDate()),
				checkIfUserIdExists(createCreditCardRequest.getCustomerId())
		);
		if (result!=null) {
			return result;
		}
		CreditCard creditCard = modelMapperService.forRequest().map(createCreditCardRequest, CreditCard.class); 
		this.creditCardDao.save(creditCard);
		return new SuccessResult(Messages.CREDITCARDADD);
	}
	
	private Result checkCreditCardNumber(String cardNumber) {
		String regex = "((?:(?:\\d{4}[- ]){3}\\d{4}|\\d{16}))(?![\\d])";
		Pattern pattern = Pattern.compile(regex);
		java.util.regex.Matcher matcher = pattern.matcher(cardNumber);
		if (!matcher.matches()) {
			return new ErrorResult(Messages.CREDITCARDNUMBERERROR);
		}
		return new SuccessResult();
	}
	private Result checkIfCreditCardNumberExists(String cardNumber){
		var result=this.creditCardDao.existsByCardNumber(cardNumber);
		if (result){
			return new ErrorResult(Messages.CREDITCARDALREADYEXISTS);
		}
		return new SuccessResult();
	}

	public Result checkCreditCardExpiryDate(String expiryDate) {

		String regex = "(0[1-9]|1[0-2])/?(([0-9]{2}|[0-9]{2})$)";

		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(expiryDate);

		if (!matcher.matches()) {
			return new ErrorResult(Messages.CREDITCARDDATEERROR);
		}
		return new SuccessResult();
	}

	public Result checkIfUserIdExists (int customerId){
		var result = this.userService.isUserExists(customerId);
		if(!result.isSuccess()){
			return new ErrorResult(Messages.USERNOTFOUND);
		}
		return new SuccessResult();
	}
}


