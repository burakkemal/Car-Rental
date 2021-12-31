package com.etiya.ReCapProject.dataAccess.abstracts;

import com.etiya.ReCapProject.entities.concretes.AdditionalService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdditionalServiceDao extends JpaRepository<AdditionalService, Integer> {

    boolean existsByServiceName(String serviceName);




}
