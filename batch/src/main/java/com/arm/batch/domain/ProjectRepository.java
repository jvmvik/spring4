package com.arm.batch.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 *
 *
 * @author vicben01
 */
public interface ProjectRepository extends CrudRepository<Project, Long>
{
    public List<Project> findAllByName(String name);
}
