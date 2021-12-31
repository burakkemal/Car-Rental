package com.etiya.ReCapProject.business.requests.translationRequests;

import com.etiya.ReCapProject.entities.concretes.Language;
import com.etiya.ReCapProject.entities.concretes.Word;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTranslationRequest {

    @JsonIgnore
    private int id;

    private String translation;

    private Word word;

    private Language language;
}
