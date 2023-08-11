package com.resumebuilder.resumebuilder.controller;

import com.resumebuilder.resumebuilder.model.Experience;
import com.resumebuilder.resumebuilder.service.ExperienceService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@CrossOrigin
@RequestMapping("/resumebuilder/experience")

public class ExperienceController
{
    @Autowired
    private ExperienceService experienceService;


    @GetMapping("/{id}")
    public ResponseEntity<?> getExperience(@PathVariable("id") String id)
    {
        return experienceService.getExperienceById(id);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> addEducation(@PathVariable("id") String id, @RequestBody Experience experience)
    {
            return experienceService.createExperience(id,experience);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEducation(@PathVariable("id") String id,@RequestBody Experience experience)
    {
            return experienceService.updateExperience(id,experience);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExperience(@PathVariable String id,  @RequestParam("resumeid") String resumeid)
    {
       return experienceService.deleteExperience(id, resumeid);

    }
}
