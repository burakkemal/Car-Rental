package com.etiya.ReCapProject.dataAccess.abstracts;

import java.util.List;

import com.etiya.ReCapProject.business.dtos.CarSearchListDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.etiya.ReCapProject.entities.concretes.Car;
import com.etiya.ReCapProject.entities.concretes.complexTypes.CarDetail;

public interface CarDao extends JpaRepository<Car, Integer> {

    @Query("Select new com.etiya.ReCapProject.entities.concretes.complexTypes.CarDetail "
            + "(c.id,b.brandName,co.colorName,c.dailyPrice,c.description,ci.cityName,c.kilometer)"
            + "From Car c Inner Join c.brand b Inner Join c.color co inner join c.city ci")
    List<CarDetail> CarWithBrandAndColorDetails();

    List<Car> getByBrand_Id(int brandId);

    List<Car> getByColor_Id(int colorId);

    List<Car> getByCity_Id(int cityId);


    //updated
    List<CarDetail> getByBrand_BrandName(String brandName);


    @Query("Select new com.etiya.ReCapProject.business.dtos.CarSearchListDto" + "(c.id,c.modelYear,c.dailyPrice,c.description,c.findexScore,c.city.id,c.kilometer) "
            + "From Car c left join c.rentals r Left Join  c.carMaintenances cm WHERE (cm.returnDate is null and cm.car.id is null) and (r.returnDate is null and r.car.id is null) ")
    List<CarSearchListDto> getAllCarsWhichAreNotInMaintenance();
/*
    @Query("Select new com.etiya.ReCapProject.business.dtos.CarSearchListDto" + "(c.id,c.modelYear,c.dailyPrice,c.description,c.findexScore,c.city.id,c.kilometer) "
            + "From Car c Left Join  c.carMaintenances cm WHERE (cm.returnDate is null AND cm.car.id is null)")
    List<CarSearchListDto> getAllWithoutMaintenanceOfCar();
*/

    //

}
