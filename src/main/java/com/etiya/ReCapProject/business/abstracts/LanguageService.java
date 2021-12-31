package com.etiya.ReCapProject.business.abstracts;

import com.etiya.ReCapProject.business.dtos.LanguageSearchListDto;
import com.etiya.ReCapProject.business.requests.languageRequests.CreateLanguageRequest;
import com.etiya.ReCapProject.business.requests.languageRequests.DeleteLanguageRequest;
import com.etiya.ReCapProject.business.requests.languageRequests.UpdateLanguageRequest;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.concretes.Language;

import javax.xml.crypto.Data;
import java.util.List;

public interface LanguageService {

    DataResult<List<LanguageSearchListDto>> getAll();
    Result add(CreateLanguageRequest createLanguageRequest);
    Result delete(DeleteLanguageRequest deleteLanguageRequest);
    Result update(UpdateLanguageRequest updateLanguageRequests);

    DataResult<LanguageSearchListDto> getByLanguageName(String languageName);



}
