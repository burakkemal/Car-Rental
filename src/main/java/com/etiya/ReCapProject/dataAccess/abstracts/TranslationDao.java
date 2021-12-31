package com.etiya.ReCapProject.dataAccess.abstracts;

import com.etiya.ReCapProject.entities.concretes.Translation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TranslationDao extends JpaRepository<Translation,Integer> {
    Translation getTranslationByLanguage_IdAndWord_Id(int languageId,int wordId);
}
