package com.resumebuilder.resumebuilder.controller;

import com.resumebuilder.resumebuilder.model.Objective;
import com.resumebuilder.resumebuilder.service.HobbiesService;
import com.resumebuilder.resumebuilder.service.ObjectiveService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/resumebuilder/objective")
public class ObjectiveController
{
    @Autowired
    private ObjectiveService objectiveService;


    @GetMapping("/{id}")
    public ResponseEntity<?> getObjective(@PathVariable("id") String id)
    {
        return objectiveService.getObjectiveById(id);
    }

    /*
     private String objective
        */

    @PostMapping("/{id}")
    public ResponseEntity<?> addObjective(@PathVariable("id") String id,@RequestBody Objective objective)
    {

        System.out.println(id);
        return objectiveService.createObjective(id, objective);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateObjective(@PathVariable("id") String id,@RequestBody Objective objective)
    {
        return objectiveService.updateObjective(id, objective);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteObjective(@PathVariable String id, @RequestParam("resumeid") String resumeid)
    {
        return objectiveService.deleteObjective(id, resumeid);

    }
}
