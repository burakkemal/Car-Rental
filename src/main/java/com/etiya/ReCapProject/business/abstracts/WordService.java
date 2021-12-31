package com.etiya.ReCapProject.business.abstracts;

import com.etiya.ReCapProject.business.dtos.WordSearchListDto;
import com.etiya.ReCapProject.business.requests.wordRequests.CreateWordRequest;
import com.etiya.ReCapProject.business.requests.wordRequests.DeleteWordRequest;
import com.etiya.ReCapProject.business.requests.wordRequests.UpdateWordRequest;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.concretes.Word;

import java.util.List;

public interface WordService {
    DataResult<WordSearchListDto> getByKey(String key);
    DataResult<List<WordSearchListDto>> getAll();
    Result add(CreateWordRequest createWordRequest);
    Result delete(DeleteWordRequest deleteWordRequest);
    Result update(UpdateWordRequest updateWordRequest);
}
