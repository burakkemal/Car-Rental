package com.etiya.ReCapProject.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.etiya.ReCapProject.business.abstracts.BrandService;
import com.etiya.ReCapProject.business.dtos.BrandSearchListDto;
import com.etiya.ReCapProject.business.requests.brandRequests.CreateBrandRequest;
import com.etiya.ReCapProject.business.requests.brandRequests.DeleteBrandRequest;
import com.etiya.ReCapProject.business.requests.brandRequests.UpdateBrandRequest;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/brands")
public class BrandsController {
	private BrandService brandService;

	@Autowired
	public BrandsController(BrandService brandService) {
		super();
		this.brandService = brandService;
	}

	@GetMapping("getAll")
	public DataResult<List<BrandSearchListDto>> getAll() {
		return this.brandService.getAll();
	}
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateBrandRequest createBrandRequest) {
		return this.brandService.add(createBrandRequest);
	}
	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateBrandRequest updateBrandRequest) {
		return this.brandService.update(updateBrandRequest);
	}
	@DeleteMapping("delete")
	public Result delete(@RequestBody @Valid DeleteBrandRequest deleteBrandRequest) {
		return this.brandService.delete(deleteBrandRequest);
	}
	@GetMapping("getById")
	public DataResult<BrandSearchListDto> getByBrandId(@RequestParam("brandId") int brandId){
		return this.brandService.getByBrandId(brandId);
	}
}
