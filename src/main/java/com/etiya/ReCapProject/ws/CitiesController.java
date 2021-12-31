package com.etiya.ReCapProject.ws;

import com.etiya.ReCapProject.business.abstracts.CityService;
import com.etiya.ReCapProject.business.dtos.CitySearchListDto;
import com.etiya.ReCapProject.business.requests.cityRequests.CreateCityRequest;
import com.etiya.ReCapProject.business.requests.cityRequests.DeleteCityRequest;
import com.etiya.ReCapProject.business.requests.cityRequests.UpdateCityRequest;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CitiesController {

    private CityService cityService;
    @Autowired
    public CitiesController(CityService cityService){
        this.cityService = cityService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateCityRequest createCityRequest){
    return this.cityService.add(createCityRequest);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Valid UpdateCityRequest updateCityRequest){
return this.cityService.update(updateCityRequest);
    }

    @DeleteMapping("/delete")
    public Result delete (@RequestBody @Valid DeleteCityRequest deleteCityRequest){
    return this.cityService.delete(deleteCityRequest);
    }

    @GetMapping("/getAll")
        public DataResult<List<CitySearchListDto>> getAll(){
    return this.cityService.getAll();
        }

        @GetMapping("/getById")
        public DataResult<CitySearchListDto> getByCityId(@RequestParam("cityId") int cityId){
     return this.cityService.getById(cityId);
        }


}
