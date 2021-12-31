package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.business.dtos.RentalSearchListDto;
import com.etiya.ReCapProject.business.requests.rentalRequests.CreateRentalRequest;
import com.etiya.ReCapProject.business.requests.rentalRequests.DeleteRentalRequest;
import com.etiya.ReCapProject.business.requests.rentalRequests.UpdateRentalRequest;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.concretes.Rental;

public interface RentalService {
	DataResult<List<RentalSearchListDto>> getAll();
	Result add(CreateRentalRequest createRentalRequest);
	Result delete(DeleteRentalRequest deleteRentalRequest);
	Result update(UpdateRentalRequest updateRentalRequest);
	DataResult<Rental> getByCar_Id(int carId);
	DataResult<Rental> getById(int id);
	Result checkIfReturnDateIsNull(int carId);
	int getAdditionalItemsTotalPriceByRentalId(int rentalId);
	Result isRentalExistsById(int id);
	Result isRentalExistsByCarId(int carId);
	DataResult<RentalSearchListDto> getByRentalId(int id);


}	
