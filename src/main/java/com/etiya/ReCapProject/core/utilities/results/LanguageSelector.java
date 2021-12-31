package com.etiya.ReCapProject.core.utilities.results;

import com.etiya.ReCapProject.Utilities.contants.Language;
import com.etiya.ReCapProject.business.abstracts.LanguageService;
import com.etiya.ReCapProject.business.abstracts.TranslationService;
import com.etiya.ReCapProject.business.abstracts.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class LanguageSelector {

    private static LanguageService languageService;
    private static WordService wordService;
    private static TranslationService translationService;
    private static Environment environment;

    @Autowired
    public LanguageSelector(LanguageService languageService, WordService wordService, TranslationService translationService, Environment environment) {
        this.languageService = languageService;
        this.wordService = wordService;
        this.translationService = translationService;
        this.environment = environment;
    }

   public static String languageSelector(String message){
        String result = environment.getProperty("current_language",Language.ENGLISH);

        var language= languageService.getByLanguageName(result);
        if (language==null){
            language.getData().setName(Language.ENGLISH);
        }
       var word= wordService.getByKey(message);
       var translation= translationService.getTranslationByLanguage_IdAndWord_Id(language.getData().getId(),word.getData().getId());
       return translation.getTranslation();
   }

}
