package com.resumebuilder.resumebuilder.controller;

import com.resumebuilder.resumebuilder.model.Skills;
import com.resumebuilder.resumebuilder.service.HobbiesService;
import com.resumebuilder.resumebuilder.service.SkillsService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/resumebuilder/skills")
public class SkillsController
{
    @Autowired
    private SkillsService skillsService;


    @GetMapping("/{id}")
    public ResponseEntity<?> getSkill(@PathVariable("id") String id)
    {
        return skillsService.getSkillsById(id);
    }

    /*
      private String skill;
    private String comptetence;
        */

    @PostMapping("/{id}")
    public ResponseEntity<?> addSkill(@PathVariable("id") String id, @RequestBody Skills skills)
    {
        return skillsService.createSkills(id,skills);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSkill(@PathVariable("id") String id, @RequestBody Skills skill)
    {
        return skillsService.updateSkills(id, skill);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSkill(@PathVariable String id, @RequestParam("resumeid") String resumeid)
    {
        return skillsService.deleteSkills(id, resumeid);
    }
}
