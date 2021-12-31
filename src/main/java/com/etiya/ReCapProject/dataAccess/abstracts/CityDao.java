package com.etiya.ReCapProject.dataAccess.abstracts;

import com.etiya.ReCapProject.entities.concretes.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityDao extends JpaRepository<City,Integer> {

    boolean existsByCityName(String cityName);

}
