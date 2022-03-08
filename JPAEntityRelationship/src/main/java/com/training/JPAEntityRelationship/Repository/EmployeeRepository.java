package com.training.JPAEntityRelationship.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.training.JPAEntityRelationship.Models.Employees;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Long>, CustomEmployeeRepository {
	
	Employees findByEmployeeId(Long EmployeeId);
	
	@Query(
			value = "SELECT * FROM employees ORDER BY salary DESC;",
			nativeQuery = true
	)
	List<Employees> getAllEmployeesOrderedBySalary();
	
	@Query(
			value = "SELECT * FROM employees WHERE gender = :gender ;",
			nativeQuery = true
	)
	List<Employees> getEmployeesByGender(@Param("gender") String gender);
	
	
	
}
