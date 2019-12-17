package com.example.ppmtool.services;

import com.example.ppmtool.exceptions.ProjectIdException;
import com.example.ppmtool.models.Project;
import com.example.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdate(Project project) {
        try {
            project.setIdentifier(project.getIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Project id '" + project.getIdentifier() + "' already exists");
        }
    }

    public Project findByIdentifier(String identifier) {
        Project project = projectRepository.findByIdentifier(identifier);
        if (project == null) {
            throw new ProjectIdException("Project id '" + identifier + "' does not exist");
        }
        return project;
    }

    public Iterable<Project> findAll(){
        return projectRepository.findAll();
    }

    public void deleteByIdentifier(String identifier){
        Project project = projectRepository.findByIdentifier(identifier.toUpperCase());
        if (project == null) {
            throw new ProjectIdException("Project id '" + identifier + "' does not exist");
        }
        projectRepository.delete(project);
    }
}
