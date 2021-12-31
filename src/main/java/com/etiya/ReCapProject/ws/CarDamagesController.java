package com.etiya.ReCapProject.ws;

import com.etiya.ReCapProject.business.abstracts.CarDamageService;
import com.etiya.ReCapProject.business.dtos.CarDamageSearchListDto;
import com.etiya.ReCapProject.business.requests.carDamageRequests.CreateCarDamageRequest;
import com.etiya.ReCapProject.business.requests.carDamageRequests.DeleteCarDamageRequest;
import com.etiya.ReCapProject.business.requests.carDamageRequests.UpdateCarDamageRequest;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carDamages")
public class CarDamagesController {

    private CarDamageService carDamageService;

    @Autowired
    public CarDamagesController(CarDamageService carDamageService) {
        this.carDamageService = carDamageService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody CreateCarDamageRequest createCarDamageRequest){
        return this.carDamageService.add(createCarDamageRequest);
    }

    @PutMapping("/update")
    public Result update(@RequestBody UpdateCarDamageRequest updateCarDamageRequest){
        return this.carDamageService.update(updateCarDamageRequest);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody DeleteCarDamageRequest deleteCarDamageRequest){
        return this.carDamageService.delete(deleteCarDamageRequest);
    }

    @GetMapping("/getAll")
    public DataResult<List<CarDamageSearchListDto>> getAll(){
        return this.carDamageService.getAll();
    }

    @GetMapping("/getById")
    public DataResult<CarDamageSearchListDto> getById(int id){
        return this.carDamageService.getById(id);
    }

    @GetMapping("/getByCarId")
    public DataResult<List<CarDamageSearchListDto>> getByCarId(int carId){
        return this.carDamageService.getByCarId(carId);
    }



}
