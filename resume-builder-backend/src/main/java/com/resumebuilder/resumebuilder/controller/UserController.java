package com.resumebuilder.resumebuilder.controller;

import com.resumebuilder.resumebuilder.model.User;
import com.resumebuilder.resumebuilder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/resumebuilder/user")
public class UserController
{
    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<?> getUser(@RequestParam("userEmail") String userEmail, @RequestParam("password") String password)
    {
        return service.getUserByEmailAndPassword(userEmail, password);
    }

    //signup
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user)
    {
        return service.createUser(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> SetResumeId(@PathVariable String id, @RequestBody String resumeId)
    {
        return service.setResumeId(id,resumeId);
    }
}
