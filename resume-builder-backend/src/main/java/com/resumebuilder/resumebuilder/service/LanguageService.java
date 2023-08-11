package com.resumebuilder.resumebuilder.service;

import com.resumebuilder.resumebuilder.model.Education;
import com.resumebuilder.resumebuilder.model.Language;
import com.resumebuilder.resumebuilder.model.Objective;
import com.resumebuilder.resumebuilder.model.Resume;
import com.resumebuilder.resumebuilder.repository.EducationRepo;
import com.resumebuilder.resumebuilder.repository.LanguageRepo;
import com.resumebuilder.resumebuilder.repository.ResumeRepo;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LanguageService
{
    @Autowired
    private LanguageRepo languageRepo;
    @Autowired
    private ResumeRepo resumeRepo;

    //create
    public ResponseEntity<?> createLanguage(String resumeid, Language language)
    {
        Optional<Resume> resume = resumeRepo.findById(resumeid);
        if(resume.isPresent() )
        {
            Language newLanguage = languageRepo.save(language);

            Resume fetchResume = resume.get();
            fetchResume.getLanguageList().add(newLanguage);
            resumeRepo.save(fetchResume);
            return new ResponseEntity<>(newLanguage, HttpStatus.OK);

        }
        else return ResponseEntity.badRequest().body("failed to save objective");
    }

    //update
    public ResponseEntity<?> updateLanguage(String id,Language language )
    {
        Optional<Language> existed = languageRepo.getLanguageById(id);
        if(existed.isPresent())
        {
            Language oldLanguage = existed.get();
            oldLanguage.setLanguage(language.getLanguage());
            oldLanguage.setProfiency(language.getProfiency());
            Language updated = languageRepo.save(oldLanguage);
            return new ResponseEntity<>(updated, HttpStatus.OK);

        }
        else
        {
            return ResponseEntity.badRequest().body("Language not found with id:"+id);
        }
    }

    //get
    public ResponseEntity<?> getLanguageById(String id)
    {
        Optional<Language> res = languageRepo.getLanguageById(id);
        if (res.isPresent())
        {
            Language language = res.get();
            return new ResponseEntity<>(language, HttpStatus.OK);
        }
        else
        {
            return ResponseEntity.badRequest().body("Language not found with id:"+id);
        }
    }
    //delete
    public ResponseEntity<?> deleteLanguage(String id, String resumeid)
    {
        Optional<Language> res = languageRepo.findById(id);
        if(res.isPresent())
        {
            Language language = res.get();
            Optional<Resume> resume = resumeRepo.findById(resumeid);
            if (resume.isPresent())
            {
                Resume getResume = resume.get();
                List<Language> languageList = getResume.getLanguageList();
                languageList.remove(language);
                resumeRepo.save(getResume);
                languageRepo.delete(language);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
        {
            return ResponseEntity.badRequest().body(" not found with id:"+id);
        }
    }
}
