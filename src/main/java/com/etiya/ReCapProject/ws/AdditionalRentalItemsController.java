package com.etiya.ReCapProject.ws;

import com.etiya.ReCapProject.business.abstracts.AdditionalRentalItemService;
import com.etiya.ReCapProject.business.dtos.AdditionalRentalItemSearchListDto;
import com.etiya.ReCapProject.business.requests.additionalRentalItemRequests.CreateAdditionalRentalItemRequest;
import com.etiya.ReCapProject.business.requests.additionalRentalItemRequests.DeleteAdditionalRentalItemRequest;
import com.etiya.ReCapProject.business.requests.additionalRentalItemRequests.UpdateAdditionalRentalItemRequest;
import com.etiya.ReCapProject.business.requests.additionalServiceRequests.UpdateAdditionalServiceRequest;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/additionalRentalItems")
public class AdditionalRentalItemsController {

    private AdditionalRentalItemService additionalRentalItemService;

    @Autowired
    public AdditionalRentalItemsController(AdditionalRentalItemService additionalRentalItemService) {
        this.additionalRentalItemService = additionalRentalItemService;
    }

    @GetMapping("getAll")
    public DataResult<List<AdditionalRentalItemSearchListDto>> getAll(){
        return this.additionalRentalItemService.getAll();    }

    @GetMapping("getByRentalId")
    public DataResult<List<AdditionalRentalItemSearchListDto>> getByRentalId(int id){
        return this.additionalRentalItemService.getByRentalId(id);    }


    @PostMapping("add")
    public Result add(@RequestBody @Valid CreateAdditionalRentalItemRequest createAdditionalRentalItemRequest){
        return this.additionalRentalItemService.add(createAdditionalRentalItemRequest);
    }

    @DeleteMapping("delete")
    public Result delete(@RequestBody @Valid DeleteAdditionalRentalItemRequest deleteAdditionalRentalItemRequest){
        return  this.additionalRentalItemService.delete(deleteAdditionalRentalItemRequest);
    }

    @PutMapping("update")
    public Result update(@RequestBody @Valid UpdateAdditionalRentalItemRequest updateAdditionalRentalItemRequest){
        return this.additionalRentalItemService.update(updateAdditionalRentalItemRequest);
    }
}
