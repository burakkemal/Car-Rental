package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.business.dtos.BrandSearchListDto;
import com.etiya.ReCapProject.business.requests.brandRequests.CreateBrandRequest;
import com.etiya.ReCapProject.business.requests.brandRequests.DeleteBrandRequest;
import com.etiya.ReCapProject.business.requests.brandRequests.UpdateBrandRequest;
import com.etiya.ReCapProject.business.requests.cityRequests.CreateCityRequest;
import com.etiya.ReCapProject.business.requests.cityRequests.DeleteCityRequest;
import com.etiya.ReCapProject.business.requests.cityRequests.UpdateCityRequest;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;

public interface BrandService {
	DataResult<List<BrandSearchListDto>> getAll();
	Result add(CreateBrandRequest createBrandRequest);
	Result delete(DeleteBrandRequest deleteBrandRequest);
	Result update(UpdateBrandRequest updateBrandRequest);
	DataResult<BrandSearchListDto> getByBrandId(int brandId);
	Result existsBrandId(int brandId) ;
}
