package com.spring.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	@Query(value="select * from employee",nativeQuery=true)
	List<Employee> getAllEmp(); 
	
	@Query(value="select * from employee where eid=?1",nativeQuery=true)
	public Employee getFindById(long eid);

}
