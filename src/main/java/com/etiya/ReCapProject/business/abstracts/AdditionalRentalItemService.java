package com.etiya.ReCapProject.business.abstracts;

import com.etiya.ReCapProject.business.dtos.AdditionalRentalItemSearchListDto;
import com.etiya.ReCapProject.business.requests.additionalRentalItemRequests.CreateAdditionalRentalItemRequest;
import com.etiya.ReCapProject.business.requests.additionalRentalItemRequests.DeleteAdditionalRentalItemRequest;
import com.etiya.ReCapProject.business.requests.additionalRentalItemRequests.UpdateAdditionalRentalItemRequest;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.concretes.AdditionalRentalItem;

import java.util.List;

public interface AdditionalRentalItemService {

    DataResult<List<AdditionalRentalItemSearchListDto>> getAll();
    Result add(CreateAdditionalRentalItemRequest createAdditionalRentalItemRequest);
    Result delete(DeleteAdditionalRentalItemRequest deleteAdditionalRentalItemRequest);
    Result update(UpdateAdditionalRentalItemRequest updateAdditionalRentalItemRequest);
    DataResult<List<AdditionalRentalItemSearchListDto>> getByRentalId(int rentalId);
}
