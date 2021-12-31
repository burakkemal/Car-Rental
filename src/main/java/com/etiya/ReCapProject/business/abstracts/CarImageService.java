package com.etiya.ReCapProject.business.abstracts;

import java.io.IOException;
import java.util.List;

import com.etiya.ReCapProject.business.dtos.CarImageSearchListDto;
import com.etiya.ReCapProject.business.requests.carImageRequests.CreateCarImageRequest;
import com.etiya.ReCapProject.business.requests.carImageRequests.DeleteCarImageRequest;
import com.etiya.ReCapProject.business.requests.carImageRequests.UpdateCarImageRequest;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;

public interface CarImageService {
	DataResult<List<CarImageSearchListDto>> getAll();
	Result add(CreateCarImageRequest createCarImageRequest) throws IOException;
	Result delete(DeleteCarImageRequest deleteCarImageRequest) throws IOException;
	Result update(UpdateCarImageRequest dpdateCarImageRequest) throws IOException;
	DataResult<List<CarImageSearchListDto>> getCarImagesByCarId(int id);
}
