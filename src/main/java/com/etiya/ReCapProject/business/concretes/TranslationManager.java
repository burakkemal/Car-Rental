package com.etiya.ReCapProject.business.concretes;

import com.etiya.ReCapProject.business.abstracts.TranslationService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.business.dtos.TranslationSearchListDto;
import com.etiya.ReCapProject.business.requests.translationRequests.CreateTranslationRequest;
import com.etiya.ReCapProject.business.requests.translationRequests.DeleteTranslationRequest;
import com.etiya.ReCapProject.business.requests.translationRequests.UpdateTranslationRequest;
import com.etiya.ReCapProject.core.utilities.mapping.ModelMapperService;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.results.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.TranslationDao;
import com.etiya.ReCapProject.entities.concretes.Translation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TranslationManager implements TranslationService {

    private TranslationDao translationDao;
    private ModelMapperService modelMapperService;

    @Autowired
    public TranslationManager(TranslationDao translationDao, ModelMapperService modelMapperService) {
        this.translationDao = translationDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<TranslationSearchListDto>> getAll() {
       List<Translation> translations=this.translationDao.findAll();
       List<TranslationSearchListDto> translationSearchListDtos=translations.stream()
               .map(translation-> modelMapperService.forDto().map(translation, TranslationSearchListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<TranslationSearchListDto>>(translationSearchListDtos,Messages.TRANSLATIONSLISTED);
    }

    @Override
    public Result add(CreateTranslationRequest createTranslationRequest) {
        Translation translation = modelMapperService.forRequest().map(createTranslationRequest, Translation.class);
        translationDao.save(translation);
        return new SuccessResult(Messages.TRANSLATIONADD);
    }

    @Override
    public Result delete(DeleteTranslationRequest deleteTranslationRequest) {
        Translation translation = modelMapperService.forRequest().map(deleteTranslationRequest, Translation.class);
        translationDao.delete(translation);
        return new SuccessResult(Messages.TRANSLATIONDELETE);
    }

    @Override
    public Result update(UpdateTranslationRequest updateTranslationRequest) {
        Translation translation = modelMapperService.forRequest().map(updateTranslationRequest, Translation.class);
        translationDao.save(translation);
        return new SuccessResult(Messages.TRANSLATIONUPDATE);
    }

    @Override
    public Translation getTranslationByLanguage_IdAndWord_Id(int languageId, int wordId) {
        return this.translationDao.getTranslationByLanguage_IdAndWord_Id(languageId,wordId);
    }
}
