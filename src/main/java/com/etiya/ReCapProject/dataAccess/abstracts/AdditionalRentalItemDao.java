package com.etiya.ReCapProject.dataAccess.abstracts;

import com.etiya.ReCapProject.entities.concretes.AdditionalRentalItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdditionalRentalItemDao extends JpaRepository<AdditionalRentalItem, Integer> {


    List<AdditionalRentalItem> getByRentalId(int rentalId);


}
