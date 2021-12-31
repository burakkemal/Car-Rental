package com.etiya.ReCapProject.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.etiya.ReCapProject.business.abstracts.PaymentService;
import com.etiya.ReCapProject.business.dtos.PaymentSearchListDto;
import com.etiya.ReCapProject.business.requests.paymentRequests.CreatePaymentRequest;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;

@RestController
@RequestMapping("api/payments")
public class PaymentController {
	private PaymentService paymentService;

	@Autowired
	public PaymentController(PaymentService paymentService) {
		super();
		this.paymentService = paymentService;
	}
	@PostMapping("add")
	public Result add(@RequestBody CreatePaymentRequest createPaymentRequest) {
		return this.paymentService.add(createPaymentRequest);
	}
	@GetMapping("getAll")
	public DataResult<List<PaymentSearchListDto>> getAll(){
		return this.paymentService.getAll();
	}

}
