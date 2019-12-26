package com.example.ppmtool.controllers;

import com.example.ppmtool.models.Project;
import com.example.ppmtool.services.ProjectService;
import com.example.ppmtool.services.ValidationHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/project")
@CrossOrigin
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private ValidationHandlerService validationHandlerService;

    @PostMapping("")
    public ResponseEntity<?> createProject(@Valid @RequestBody Project project, BindingResult result) {
        ResponseEntity<?> validationErrors = validationHandlerService.getValidationErrors(result);
        if (validationErrors != null) {
            return validationErrors;
        }
        Project p = projectService.saveOrUpdate(project);
        return new ResponseEntity<Project>(p, HttpStatus.CREATED);
    }

    @GetMapping("/{identifier}")
    public ResponseEntity<?> getProjectByIdentifier(@PathVariable String identifier) {
        Project project = projectService.findByIdentifier(identifier);
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Project> getAllProject() {
        return projectService.findAll();
    }

    @DeleteMapping("/{identifier}")
    public ResponseEntity<String> deleteProjectByIdentifier(@PathVariable String identifier) {
        projectService.deleteByIdentifier(identifier);
        return new ResponseEntity <String> ("Project with identifier " + identifier + " was deleted", HttpStatus.OK);
    }
    
}
