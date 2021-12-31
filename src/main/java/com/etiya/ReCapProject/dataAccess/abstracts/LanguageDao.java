package com.etiya.ReCapProject.dataAccess.abstracts;

import com.etiya.ReCapProject.entities.concretes.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LanguageDao extends JpaRepository<Language,Integer> {


    Language getLanguagesByName(String languageName);
}
