package com.spring.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

	@Query(value="select * from project",nativeQuery=true)
	public List<Project> getAllPro();
}
