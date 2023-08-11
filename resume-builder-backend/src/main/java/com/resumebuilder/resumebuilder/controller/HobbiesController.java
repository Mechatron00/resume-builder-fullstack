package com.resumebuilder.resumebuilder.controller;

import com.resumebuilder.resumebuilder.model.Hobbies;
import com.resumebuilder.resumebuilder.service.HobbiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/resumebuilder/hobbies")
public class HobbiesController
{
    @Autowired
    private HobbiesService hobbiesService;


    @GetMapping("/{id}")
    public ResponseEntity<?> getHobby(@PathVariable("id") String id)
    {
        return hobbiesService.getHobbyById(id);
    }

    /*
     private String hobby;
        */

    @PostMapping("/{id}")
    public ResponseEntity<?> addHobby(@PathVariable("id") String id, @RequestBody Hobbies hobbies)
    {
        return hobbiesService.createHobby(id, hobbies);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateHobby(@PathVariable("id") String id,@RequestBody Hobbies hobbies)
    {
        return hobbiesService.updateHobbies(id, hobbies);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHobby(@PathVariable String id, @RequestParam("resumeid") String resumeid)
    {
        return hobbiesService.deleteHobby(id, resumeid);
    }
}
