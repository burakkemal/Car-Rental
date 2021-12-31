package com.etiya.ReCapProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.PaymentService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.business.dtos.PaymentSearchListDto;
import com.etiya.ReCapProject.business.requests.paymentRequests.CreatePaymentRequest;
import com.etiya.ReCapProject.business.requests.paymentRequests.DeletePaymentRequest;
import com.etiya.ReCapProject.business.requests.paymentRequests.UpdatePaymentRequest;
import com.etiya.ReCapProject.core.utilities.mapping.ModelMapperService;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.results.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.PaymentDao;
import com.etiya.ReCapProject.entities.concretes.Payment;

@Service
public class PaymentManager implements PaymentService{
	private PaymentDao paymentDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public PaymentManager(PaymentDao paymentDao, ModelMapperService modelMapperService) {
		super();
		this.paymentDao = paymentDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<PaymentSearchListDto>> getAll() {
		List<Payment> result=this.paymentDao.findAll();
		List<PaymentSearchListDto> response=result.stream().map(payment-> modelMapperService.forDto()
				.map(payment, PaymentSearchListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<PaymentSearchListDto>>(response, Messages.PAYMENTLIST);
	}

	@Override
	public Result add(CreatePaymentRequest createPaymentRequest) {
		Payment payment = modelMapperService.forRequest().map(createPaymentRequest, Payment.class);
		this.paymentDao.save(payment);
		return new SuccessResult(Messages.PAYMENTADD);
	}

	@Override
	public Result delete(DeletePaymentRequest deletePaymentRequest) {
		Payment payment = modelMapperService.forRequest().map(deletePaymentRequest, Payment.class);
		this.paymentDao.delete(payment);
		return new SuccessResult(Messages.PAYMENTDELETE);
	}

	@Override
	public Result update(UpdatePaymentRequest updatePaymentRequest) {
		Payment payment = modelMapperService.forRequest().map(updatePaymentRequest, Payment.class);
		this.paymentDao.save(payment);
		return new SuccessResult(Messages.PAYMENTUPDATE);
	}


	
	
}
