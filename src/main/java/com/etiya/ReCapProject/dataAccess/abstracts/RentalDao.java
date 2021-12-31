package com.etiya.ReCapProject.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.etiya.ReCapProject.business.dtos.RentalSearchListDto;
import com.etiya.ReCapProject.entities.concretes.Rental;

public interface RentalDao extends JpaRepository<Rental, Integer> {

	@Query("select new com.etiya.ReCapProject.business.dtos.RentalSearchListDto"
			+ "(c.id, r.rentDate, r.returnDate,r.car.id ,r.customer.id,r.rentedCityId,r.returnCityId) "
			+ "From Car c Inner Join c.rentals r where c.id=:carId and r.returnDate > r.rentDate")
	RentalSearchListDto getByCarIdWhereRentDateIsBeforeReturnDate(int carId);
	
	Rental getByCarId(int carId);

	boolean existsByCarId(int id);


//	@Query(value = "select SUM(ass.daily_price)  from Rental r inner join AdditionalRentalItem ari ON r.id=ari.rental_id inner Join AdditioanlService ass on ari.additional_service_id=ass.id",nativeQuery = true)
//	List<Integer> getAdditionalItemsOfRelevantRental();


	@Query(" select ass.dailyPrice from Rental r inner join AdditionalRentalItem ari on r.id = ari.rental.id inner join AdditionalService ass on ari.additionalService.id = ass.id where r.id=:rentalId")
	List<Integer> getAdditionalItemsOfRelevantRental(int rentalId);
}
