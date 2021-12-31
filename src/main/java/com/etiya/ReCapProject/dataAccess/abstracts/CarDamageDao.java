package com.etiya.ReCapProject.dataAccess.abstracts;

import com.etiya.ReCapProject.entities.concretes.CarDamage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarDamageDao  extends JpaRepository<CarDamage,Integer> {

    List<CarDamage> getByCar_Id(int carId);


}
