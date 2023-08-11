package com.resumebuilder.resumebuilder.controller;

import com.resumebuilder.resumebuilder.model.Language;
import com.resumebuilder.resumebuilder.service.HobbiesService;
import com.resumebuilder.resumebuilder.service.LanguageService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/resumebuilder/language")
public class LanguageController
{
    @Autowired
    private LanguageService languageService;


    @GetMapping("/{id}")
    public ResponseEntity<?> getLanguage(@PathVariable("id") String id)
    {
        return languageService.getLanguageById(id);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> addLanguage(@PathVariable("id") String id, @RequestBody Language language)
    {
        return languageService.createLanguage(id,language);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLanguage(@PathVariable("id") String id,@RequestBody Language language)
    {
        return languageService.updateLanguage(id, language);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLanguage(@PathVariable String id, @RequestParam("resumeid") String resumeid)
    {
       return languageService.deleteLanguage(id, resumeid);
    }
}
