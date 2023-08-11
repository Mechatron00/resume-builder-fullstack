package com.resumebuilder.resumebuilder.controller;

import com.resumebuilder.resumebuilder.model.Projects;
import com.resumebuilder.resumebuilder.service.ProjectsService;
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
@RequestMapping("/resumebuilder/project")
public class ProjectsController
{
    @Autowired
    private ProjectsService projectsService;


    @GetMapping("/{id}")
    public ResponseEntity<?> getProject(@PathVariable("id") String id)
    {
        return projectsService.getProjectsById(id);
    }


    @PostMapping("/{id}")
    public ResponseEntity<?> addProject(@PathVariable("id") String id, @RequestBody Projects projects)
    {
            return projectsService.createProjects(id, projects);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProject(@PathVariable("id") String id,@RequestBody Projects projects

    )
    {
            return projectsService.updateProjects(id,projects);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable String id,  @RequestParam("resumeid") String resumeid)
    {
       return projectsService.deleteProjects(id,resumeid);
    }
}
