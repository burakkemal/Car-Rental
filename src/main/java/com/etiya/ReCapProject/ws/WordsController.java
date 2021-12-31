package com.etiya.ReCapProject.ws;

import com.etiya.ReCapProject.business.abstracts.TranslationService;
import com.etiya.ReCapProject.business.abstracts.WordService;
import com.etiya.ReCapProject.business.dtos.TranslationSearchListDto;
import com.etiya.ReCapProject.business.dtos.WordSearchListDto;
import com.etiya.ReCapProject.business.requests.translationRequests.CreateTranslationRequest;
import com.etiya.ReCapProject.business.requests.translationRequests.DeleteTranslationRequest;
import com.etiya.ReCapProject.business.requests.translationRequests.UpdateTranslationRequest;
import com.etiya.ReCapProject.business.requests.wordRequests.CreateWordRequest;
import com.etiya.ReCapProject.business.requests.wordRequests.DeleteWordRequest;
import com.etiya.ReCapProject.business.requests.wordRequests.UpdateWordRequest;
import com.etiya.ReCapProject.core.utilities.results.DataResult;
import com.etiya.ReCapProject.core.utilities.results.Result;
import com.etiya.ReCapProject.entities.concretes.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/words")
public class WordsController {
    private WordService wordService;

    @Autowired
    public WordsController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping("getByKey")
    public DataResult<WordSearchListDto> getByKey(String key){
        return this.wordService.getByKey(key);
    }


    @PostMapping("add")
    public Result add(@RequestBody @Valid CreateWordRequest createWordRequest) {
        return this.wordService.add(createWordRequest);
    }
    @DeleteMapping("delete")
    public Result delete(@RequestBody @Valid DeleteWordRequest deleteWordRequest) {
        return this.wordService.delete(deleteWordRequest);
    }

    @PutMapping("update")
    public Result update(@RequestBody @Valid UpdateWordRequest updateWordRequest) {
        return this.wordService.update(updateWordRequest);
    }
    @GetMapping("getAll")
    public DataResult<List<WordSearchListDto>> getAll(){
        return this.wordService.getAll();
    }
}
