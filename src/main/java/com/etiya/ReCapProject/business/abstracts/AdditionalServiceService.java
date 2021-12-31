package com.etiya.ReCapProject.business.abstracts;

import com.etiya.ReCapProject.business.dtos.AdditionalServiceSearchListDto;
import com.etiya.ReCapProject.business.requests.additionalServiceRequests.CreateAdditionalServiceRequest;
import com.etiya.ReCapProject.business.requests.additionalServiceRequests.DeleteAdditionalServiceRequest;
import com.etiya.ReCapProject.business.requests.additionalServiceRequests.UpdateAdditionalServiceRequest;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;

import javax.sound.sampled.DataLine;
import java.util.List;

public interface AdditionalServiceService {
    DataResult<List<AdditionalServiceSearchListDto>> getAll();
    Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest);
    Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest);
    Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest);
    DataResult<AdditionalServiceSearchListDto> getById(int id);
    Result checkIsAdditionalServiceExists(int id);
}
