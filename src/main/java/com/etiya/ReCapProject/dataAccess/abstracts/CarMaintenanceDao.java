package com.etiya.ReCapProject.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.etiya.ReCapProject.entities.concretes.CarMaintenance;

public interface CarMaintenanceDao extends JpaRepository<CarMaintenance, Integer> {

	CarMaintenance getByCar_Id(int carId);

	boolean existsByCarId(int carId);

//	@Query(value = "Select rentals.car_id, car_maintenances.car_id"
//			+ "from rentals"
//			+ "left join car_maintenances"
//			+ "on rentals.car_id=car_maintenances.car_id", nativeQuery = true)
//	CarMaintenance findByRentalCarId();
}
