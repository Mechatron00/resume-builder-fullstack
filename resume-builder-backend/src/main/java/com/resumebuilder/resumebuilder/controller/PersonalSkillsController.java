package com.resumebuilder.resumebuilder.controller;

import com.resumebuilder.resumebuilder.model.Objective;
import com.resumebuilder.resumebuilder.model.PersonalSkills;
import com.resumebuilder.resumebuilder.service.ObjectiveService;
import com.resumebuilder.resumebuilder.service.PersonalSkillsService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/resumebuilder/personalskills")
public class PersonalSkillsController
{
    @Autowired
    private PersonalSkillsService personalSkillsService;


    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonalSkill(@PathVariable("id") String id)
    {
        return personalSkillsService.getPersonalSkillsById(id);
    }

    /*
     private String description
        */

    @PostMapping("/{id}")
    public ResponseEntity<?> addPersonalSkill(@PathVariable("id") String id, @RequestBody PersonalSkills personalSkills)
    {
        return personalSkillsService.createPersonalSkills(id,personalSkills);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateObjective(@PathVariable("id") String id, @RequestBody PersonalSkills personalSkills)
    {
        return personalSkillsService.updatePersonalSkills(id, personalSkills);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteObjective(@PathVariable String id, @RequestParam("resumeid") String resumeid)
    {
        return personalSkillsService.deletePersonalSkills(id, resumeid);
    }
}
