package com.resumebuilder.resumebuilder.service;

import com.resumebuilder.resumebuilder.model.Objective;
import com.resumebuilder.resumebuilder.model.Projects;
import com.resumebuilder.resumebuilder.model.Resume;
import com.resumebuilder.resumebuilder.repository.ProjectsRepo;
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
public class ProjectsService
{
    @Autowired
    private ProjectsRepo projectsRepo;
    @Autowired
    private ResumeRepo resumeRepo;

    //create
    public ResponseEntity<?> createProjects(String resumeid, Projects projects)
    {
        Optional<Resume> resume = resumeRepo.findById(resumeid);
        if(resume.isPresent() )
        {
            Projects newProject = projectsRepo.save(projects);

            Resume fetchResume = resume.get();
            fetchResume.getProjectsList().add(newProject);
            resumeRepo.save(fetchResume);
            return new ResponseEntity<>(newProject, HttpStatus.OK);

        }
        else return ResponseEntity.badRequest().body("failed to save objective");

    }

    //update
    public ResponseEntity<?> updateProjects(String id, Projects projects )
    {
        Optional<Projects> existed = projectsRepo.findById(id);
        if(existed.isPresent())
        {
            Projects oldProject = existed.get();
            oldProject.setTitle(projects.getTitle());
            oldProject.setStartDate(projects.getStartDate());
            oldProject.setEndDate(projects.getEndDate());
            oldProject.setWeblink(projects.getWeblink());
            oldProject.setDescription(projects.getDescription());
            Projects updated = projectsRepo.save(oldProject);
            return new ResponseEntity<>(updated, HttpStatus.OK);

        }
        else
        {
            return ResponseEntity.badRequest().body("Project not found with id:"+id);
        }
    }

    //get
    public ResponseEntity<?> getProjectsById(String id)
    {
        Optional<Projects> res = projectsRepo.findById(id);
        if (res.isPresent())
        {
            Projects projects = res.get();
            return new ResponseEntity<>(projects, HttpStatus.OK);
        }
        else
        {
            return ResponseEntity.badRequest().body("Project not found with id:"+id);
        }
    }
    //delete
    public ResponseEntity<?> deleteProjects(String id, String resumeid)
    {
        Optional<Projects> res = projectsRepo.findById(id);
        if(res.isPresent())
        {
            Projects projects = res.get();
            Optional<Resume> resume = resumeRepo.findById(resumeid);
            if (resume.isPresent())
            {
                Resume getResume = resume.get();
                List<Projects> projectsList = getResume.getProjectsList();
                projectsList.remove(projects);
                resumeRepo.save(getResume);
                projectsRepo.delete(projects);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
        {
            return ResponseEntity.badRequest().body(" not found with id:"+id);
        }
    }
}
