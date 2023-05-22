package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.entity.AssignedProject;
import com.spring.entity.Employee;
import com.spring.entity.Project;
import com.spring.repo.AssignedProjectRepository;
import com.spring.repo.EmployeeRepository;
import com.spring.repo.ProjectRepository;

@Controller
public class AssignedProjectController {
   
    private AssignedProjectRepository assignedProjectRepository;
   
    private EmployeeRepository employeeRepository;
  
    private ProjectRepository projectRepository;
    
    @Autowired
    public AssignedProjectController(EmployeeRepository emp,ProjectRepository pro,AssignedProjectRepository assign) {
    	this.employeeRepository=emp;
    	this.projectRepository=pro;
    	this.assignedProjectRepository=assign;
    }
    
    @GetMapping("/assign-project")
    public String showAssignProjectForm(Model model) {
    	
    	List<Employee> employees=employeeRepository.getAllEmp();
    	List<Project> projects=projectRepository.getAllPro();
    
        model.addAttribute("assignedProject", new AssignedProject());
        model.addAttribute("employees", employees);
        model.addAttribute("projects", projects);
        return "assign-project";
    }

    @PostMapping("/assign-project")
    public String assignProject(AssignedProject assignedProject) {
    	try {
    		assignedProjectRepository.save(assignedProject);
            return "redirect:/assign-project/?msg=assigned";
    	}catch (Exception e) {
    		System.out.println(e);
		}
    	return "redirect:/assign-project/?msg=error";
        
    }

    @GetMapping("/show-assigned-projects")
    public String showAssignedProjects(Model model) {
    	model.addAttribute("employees",employeeRepository.findAll());
    	model.addAttribute("projects",projectRepository.findAll());
        model.addAttribute("assignedProjects", assignedProjectRepository.findAll());
        return "show-assigned-projects";
    }
    
    
    @GetMapping("/deleteAssignedProject/{id}")
    public String deleteEmployee(@PathVariable(value = "id") Long id) {
        AssignedProject employee = assignedProjectRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid employee ID: " + id));
        assignedProjectRepository.delete(employee);
        return "redirect:/show-assigned-projects";
    }

    // Other CRUD operations for assigned projects
}

