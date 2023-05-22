package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.entity.Project;
import com.spring.repo.ProjectRepository;

@Controller
public class ProjectController {
    @Autowired
    private ProjectRepository projectRepository;    
    
    @GetMapping("/add-project")
    public String showAddProjectForm(Project project) {
        return "add-project";
    }

    @PostMapping("/add-project")
    public String addProject(Project project) {
        projectRepository.save(project);
        return "redirect:/add-project/?msg=insert";
    }

    @GetMapping("/show-projects")
    public String showProjects(Model model) {
        model.addAttribute("projects", projectRepository.getAllPro());
        return "show-projects";
    }
    
    @PostMapping("/saveProject")
    public String saveProject(@ModelAttribute("project") Project project) {
        projectRepository.save(project);
        return "redirect:/show-projects/?msg=update";
    }
    
    @GetMapping("/showFormForUpdateProject/{id}")
    public String showFormForUpdateProject(@PathVariable(value = "id") Long id, Model model) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid employee ID: " + id));
        System.out.println(project);
        model.addAttribute("project", project);
        return "update-project";
    }
    
    @GetMapping("/deleteProject/{id}")
    public String deleteProject(@PathVariable(value = "id") Long id) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid employee ID: " + id));
        try {
        	projectRepository.delete(project);
            return "redirect:/show-projects/?msg=delete";
        }catch(Exception e) {
        	System.out.println(e);
        }
        return "redirect:/show-projects/?msg=error";
    }

    // Other CRUD operations for projects
}

