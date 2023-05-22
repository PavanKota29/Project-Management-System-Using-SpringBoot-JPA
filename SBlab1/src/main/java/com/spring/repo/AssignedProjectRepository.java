package com.spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.entity.AssignedProject;

public interface AssignedProjectRepository extends JpaRepository<AssignedProject, Long>{
}
