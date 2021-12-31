package com.etiya.ReCapProject.ws;

import com.etiya.ReCapProject.business.abstracts.LanguageService;
import com.etiya.ReCapProject.business.dtos.LanguageSearchListDto;
import com.etiya.ReCapProject.business.requests.languageRequests.CreateLanguageRequest;
import com.etiya.ReCapProject.business.requests.languageRequests.DeleteLanguageRequest;
import com.etiya.ReCapProject.business.requests.languageRequests.UpdateLanguageRequest;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.core.utilities.results.SuccessDataResult;
import com.etiya.ReCapProject.entities.concretes.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/languages")
public class LanguagesController {
    private LanguageService languageService;

    @Autowired
    public LanguagesController(LanguageService languageService) {
        this.languageService = languageService;
    }


    @GetMapping("getALl")
    public DataResult<List<LanguageSearchListDto>> getAll(){
        return this.languageService.getAll();
    }

    @GetMapping("getByLanguageName")
    public DataResult<LanguageSearchListDto>  getByLanguageName(String languageName){
        var result=this.languageService.getByLanguageName(languageName);
        return result;
    }

    @PostMapping("add")
    public Result add(@RequestBody CreateLanguageRequest createLanguageRequest){
        return this.languageService.add(createLanguageRequest);
    }

    @DeleteMapping("delete")
    public Result delete(@RequestBody DeleteLanguageRequest deleteLanguageRequest){
        return this.languageService.delete(deleteLanguageRequest);
    }
    @PutMapping("update")
    public Result update(@RequestBody UpdateLanguageRequest updateLanguageRequest){
        return this.languageService.update(updateLanguageRequest);
    }
}
