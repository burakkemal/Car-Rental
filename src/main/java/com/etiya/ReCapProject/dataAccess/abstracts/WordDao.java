package com.etiya.ReCapProject.dataAccess.abstracts;

import com.etiya.ReCapProject.entities.concretes.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordDao extends JpaRepository<Word, Integer> {
    Word getWordsByKey(String key);
    boolean existsByKey(String key);
}
