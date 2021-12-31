package com.etiya.ReCapProject.business.dtos;

import com.etiya.ReCapProject.entities.concretes.Language;
import com.etiya.ReCapProject.entities.concretes.Word;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranslationSearchListDto {

    private int id;
    private String translation;
    private Word word;
    private Language language;
}
