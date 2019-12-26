package com.example.ppmtool.repositories;

import com.example.ppmtool.models.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Long> {

    Project findByIdentifier(String identifier);

}
