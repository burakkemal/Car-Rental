package com.etiya.ReCapProject.ws;

import com.etiya.ReCapProject.business.abstracts.AdditionalServiceService;
import com.etiya.ReCapProject.business.dtos.AdditionalServiceSearchListDto;
import com.etiya.ReCapProject.business.requests.additionalServiceRequests.CreateAdditionalServiceRequest;
import com.etiya.ReCapProject.business.requests.additionalServiceRequests.DeleteAdditionalServiceRequest;
import com.etiya.ReCapProject.business.requests.additionalServiceRequests.UpdateAdditionalServiceRequest;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.ReCapProject.entities.concretes.AdditionalService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/additionalServices")
public class AdditionalServicesController {

    private AdditionalServiceService additionalServiceService;


    public AdditionalServicesController( AdditionalServiceService additionalServiceService) {
        this.additionalServiceService = additionalServiceService;
    }

    @GetMapping("getAll")
    public DataResult<List<AdditionalServiceSearchListDto>> getAll() {
        return this.additionalServiceService.getAll();
    }

    @PostMapping("add")
    public Result add(@RequestBody @Valid CreateAdditionalServiceRequest createAdditionalServiceRequest) {
        return this.additionalServiceService.add(createAdditionalServiceRequest);

    }

    @PutMapping("update")
    public Result update(@RequestBody @Valid UpdateAdditionalServiceRequest updateAdditionalServiceRequest) {
        return this.additionalServiceService.update(updateAdditionalServiceRequest);
    }

    @DeleteMapping("delete")
    public Result delete(@RequestBody @Valid DeleteAdditionalServiceRequest deleteAdditionalServiceRequest) {
        return this.additionalServiceService.delete(deleteAdditionalServiceRequest);
    }

}
