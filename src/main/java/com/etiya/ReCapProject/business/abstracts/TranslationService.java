package com.etiya.ReCapProject.business.abstracts;

import com.etiya.ReCapProject.business.dtos.TranslationSearchListDto;
import com.etiya.ReCapProject.business.requests.translationRequests.CreateTranslationRequest;
import com.etiya.ReCapProject.business.requests.translationRequests.DeleteTranslationRequest;
import com.etiya.ReCapProject.business.requests.translationRequests.UpdateTranslationRequest;

import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.concretes.Translation;

import java.util.List;

public interface TranslationService {
    DataResult<List<TranslationSearchListDto>> getAll();
    Result add(CreateTranslationRequest createTranslationRequest);
    Result delete(DeleteTranslationRequest deleteTranslationRequest);
    Result update(UpdateTranslationRequest updateTranslationRequest);

    Translation getTranslationByLanguage_IdAndWord_Id(int languageId,int wordId);
}
