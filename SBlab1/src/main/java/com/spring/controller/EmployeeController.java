package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.spring.entity.Employee;
import com.spring.repo.EmployeeRepository;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/add-employee")
    public String showAddEmployeeForm(Employee employee) {
        return "add-employee";
    }

    @PostMapping("/add-employee")
    public String addEmployee(Employee employee) {
        employeeRepository.save(employee);
        return "redirect:/add-employee/?msg=insert";
    }

    @GetMapping("/show-employees")
    public String showEmployees(Model model) {
        model.addAttribute("employees", employeeRepository.getAllEmp());
        return "show-employees";
    }
    
    
    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeRepository.save(employee);
        return "redirect:/show-employees/?msg=update";
    }
    
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") Long id, Model model) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid employee ID: " + id));
        System.out.println(employee);
        model.addAttribute("employee", employee);
        return "update-employee";
    }
    
    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid employee ID: " + id));
        try {
        	employeeRepository.delete(employee);
            return "redirect:/show-employees/?msg=delete";
        }catch (Exception e) {
			System.out.println(e);
		}
        return "redirect:/show-employees/?msg=error";
    }
 
    
    // Other CRUD operations for employees
}
