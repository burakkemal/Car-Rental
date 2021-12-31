package com.etiya.ReCapProject.business.concretes;

import com.etiya.ReCapProject.business.abstracts.AdditionalServiceService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.business.dtos.AdditionalServiceSearchListDto;
import com.etiya.ReCapProject.business.requests.additionalServiceRequests.CreateAdditionalServiceRequest;
import com.etiya.ReCapProject.business.requests.additionalServiceRequests.DeleteAdditionalServiceRequest;
import com.etiya.ReCapProject.business.requests.additionalServiceRequests.UpdateAdditionalServiceRequest;
import com.etiya.ReCapProject.core.utilities.business.BusinessRules;
import com.etiya.ReCapProject.core.utilities.mapping.ModelMapperService;
import com.etiya.ReCapProject.core.utilities.results.*;
import com.etiya.ReCapProject.dataAccess.abstracts.AdditionalServiceDao;
import com.etiya.ReCapProject.entities.concretes.AdditionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdditionalServiceManager implements AdditionalServiceService {

    private ModelMapperService modelMapperService;
    private AdditionalServiceDao additionalServiceDao;


    @Autowired
    public AdditionalServiceManager(ModelMapperService modelMapperService, AdditionalServiceDao additionalServiceDao) {
        this.modelMapperService = modelMapperService;
        this.additionalServiceDao = additionalServiceDao;
    }

    @Override
    public DataResult<List<AdditionalServiceSearchListDto>> getAll() {
        List<AdditionalService> result = this.additionalServiceDao.findAll();
        List<AdditionalServiceSearchListDto> response = result.stream().map(additional -> modelMapperService.forDto().map(additional, AdditionalServiceSearchListDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<AdditionalServiceSearchListDto>>(response, Messages.ADDITIONALSERVICELIST);
    }

    @Override
    public Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest) {
        var businessResult = BusinessRules.run(checkIsServiceNameExists(createAdditionalServiceRequest.getServiceName()));
        if (businessResult!= null){
            return businessResult;
        }
        AdditionalService additionalService = modelMapperService.forRequest().map(createAdditionalServiceRequest, AdditionalService.class);
        this.additionalServiceDao.save(additionalService);
        return new SuccessResult(Messages.ADDITIONALSERVICEADD);
    }

    @Override
    public Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest) {
        var businessResult = BusinessRules.run(isAdditionalServiceExists(deleteAdditionalServiceRequest.getId()));
        if (businessResult!= null){
            return businessResult;
        }
        AdditionalService additionalService = modelMapperService.forRequest().map(deleteAdditionalServiceRequest, AdditionalService.class);
        this.additionalServiceDao.delete(additionalService);
        return new SuccessResult(Messages.ADDITIONALSERVICEDELETE);
    }

    @Override
    public Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest) {
        var businessResult = BusinessRules.run(isAdditionalServiceExists(updateAdditionalServiceRequest.getId()));
        if (businessResult!= null){
            return businessResult;
        }
        AdditionalService additionalService = modelMapperService.forRequest()
                .map(updateAdditionalServiceRequest, AdditionalService.class);
        this.additionalServiceDao.save(additionalService);
        return new SuccessResult(Messages.ADDITIONALSERVICEUPDATE);
    }

    @Override
    public DataResult<AdditionalServiceSearchListDto> getById(int id) {
        var businessResult = BusinessRules.run(isAdditionalServiceExists(id));
        if (businessResult!= null){
            return new ErrorDataResult(businessResult);
        }
        AdditionalService additionalService = this.additionalServiceDao.getById(id);
        AdditionalServiceSearchListDto response = modelMapperService.forDto().map(additionalService,AdditionalServiceSearchListDto.class);
        return new SuccessDataResult<AdditionalServiceSearchListDto>(response);
    }

    @Override
    public Result checkIsAdditionalServiceExists(int id) {
        var exists = isAdditionalServiceExists(id);
        if (!exists.isSuccess()){
            return new ErrorResult(Messages.ADDITIONALSERVICENOTFOUND);
        }
        return new SuccessResult();
    }

    private Result isAdditionalServiceExists(int id){
        var existsResult = this.additionalServiceDao.existsById(id);
        if (!existsResult){
            return new ErrorResult(Messages.ADDITIONALSERVICENOTFOUND);
        }
        return new SuccessResult();
    }

    private Result checkIsServiceNameExists(String serviceName){
        var existsResult = this.additionalServiceDao.existsByServiceName(serviceName);
        if (existsResult){
            return new ErrorResult(Messages.ADDITIONALSERVICENOTFOUND);
        }
        return new SuccessResult();

    }







}

