package com.etiya.ReCapProject.ws;

import com.etiya.ReCapProject.business.abstracts.TranslationService;
import com.etiya.ReCapProject.business.dtos.TranslationSearchListDto;

import com.etiya.ReCapProject.business.requests.translationRequests.CreateTranslationRequest;
import com.etiya.ReCapProject.business.requests.translationRequests.DeleteTranslationRequest;
import com.etiya.ReCapProject.business.requests.translationRequests.UpdateTranslationRequest;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/translations")
public class TranslationsController {

    private TranslationService translationService;

    @Autowired
    public TranslationsController(TranslationService translationService) {
        this.translationService = translationService;
    }

    @PostMapping("add")
    public Result add(@RequestBody @Valid CreateTranslationRequest createTranslationRequest) {
        return this.translationService.add(createTranslationRequest);
    }
    @DeleteMapping("delete")
    public Result delete(@RequestBody @Valid DeleteTranslationRequest deleteTranslationRequest) {
        return this.translationService.delete(deleteTranslationRequest);
    }

    @PutMapping("update")
    public Result update(@RequestBody @Valid UpdateTranslationRequest updateTranslationRequest) {
        return this.translationService.update(updateTranslationRequest);
    }
    @GetMapping("getAll")
    public DataResult<List<TranslationSearchListDto>> getAll(){
        return this.translationService.getAll();
    }

}
