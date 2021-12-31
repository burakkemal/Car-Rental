package com.etiya.ReCapProject.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.ReCapProject.entities.concretes.CarImage;

public interface CarImageDao extends JpaRepository<CarImage, Integer>{
	
	List<CarImage> getByCar_Id(int id);
	
	int countCarImagesByCar_Id(int id);

}
