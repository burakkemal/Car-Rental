package com.etiya.ReCapProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import com.etiya.ReCapProject.core.utilities.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.BrandService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.business.dtos.BrandSearchListDto;
import com.etiya.ReCapProject.business.requests.brandRequests.CreateBrandRequest;
import com.etiya.ReCapProject.business.requests.brandRequests.DeleteBrandRequest;
import com.etiya.ReCapProject.business.requests.brandRequests.UpdateBrandRequest;
import com.etiya.ReCapProject.core.utilities.business.BusinessRules;
import com.etiya.ReCapProject.core.utilities.mapping.ModelMapperService;
import com.etiya.ReCapProject.dataAccess.abstracts.BrandDao;
import com.etiya.ReCapProject.entities.concretes.Brand;

@Service
//@ConfigurationProperties
public class BrandManager implements BrandService{
	private BrandDao brandDao;
	private ModelMapperService modelMapperService;
	
	//dsadsasds
	
	@Autowired
	public BrandManager(BrandDao brandDao, ModelMapperService modelMapperService) {
		super();
		this.brandDao = brandDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<BrandSearchListDto>> getAll() {
		List<Brand> result = this.brandDao.findAll();
		List<BrandSearchListDto> response = result.stream().map(brand -> modelMapperService.forDto().map(brand, BrandSearchListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<BrandSearchListDto>>(response, Messages.BRANDLIST);
	}

	@Override
	public Result add(CreateBrandRequest createBrandRequest) {
		Result result=BusinessRules.run(existsBrandName(createBrandRequest.getBrandName()));
		if (result!=null) {
			return result;
		}
		Brand brand = modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		this.brandDao.save(brand);
		
		return new SuccessResult(Messages.BRANDADD);
	}

	@Override
	public Result delete(DeleteBrandRequest deleteBrandRequest) {
		Result result=BusinessRules.run(existsBrandId(deleteBrandRequest.getId()));
		if (result!=null) {
			return result;
		}
		Brand brand = modelMapperService.forRequest().map(deleteBrandRequest, Brand.class);
		this.brandDao.delete(brand);
		return new SuccessResult(Messages.BRANDDELETE);
	}
	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) {
		Result result=BusinessRules.run(existsBrandId(updateBrandRequest.getId()),existsBrandName(updateBrandRequest.getBrandName()));
		if (result!=null) {
			return result;
		}
		Brand brand = modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		this.brandDao.save(brand);
		return new SuccessResult(Messages.BRANDUPDATE);
	}
	@Override
	public DataResult<BrandSearchListDto> getByBrandId(int brandId) {
		boolean existResult = this.brandDao.existsById(brandId);
		if (!existResult){
			return new ErrorDataResult<BrandSearchListDto>(Messages.BRANDNOTFOUND,null);
		}
		Brand brand= this.brandDao.findById(brandId).get();
		BrandSearchListDto brandSearchListDto=modelMapperService.forDto().map(brand, BrandSearchListDto.class);
		return new SuccessDataResult<BrandSearchListDto>(brandSearchListDto,Messages.BRANDFOUND);
		
	}
	private Result existsBrandName(String brandName){
		boolean result= this.brandDao.existsBrandByBrandName(brandName);
		if (result) {
			return new ErrorResult(Messages.BRANDNAMEERROR);
		}
		return new SuccessResult();
	}
	 public Result existsBrandId(int brandId) {
		var result = this.brandDao.existsById(brandId);
		if (!result) {
			return new ErrorResult(Messages.BRANDNOTFOUND);
		}
		return new SuccessResult();
	}
}
