package com.training.JPAEntityRelationship.RepositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.training.JPAEntityRelationship.DTO.EmployeeDTO;
import com.training.JPAEntityRelationship.Repository.CustomEmployeeRepository;

public class CustomEmployeeRepositoryImpl implements CustomEmployeeRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void updateEmployeeDetailsByEmployeeId(Long employeeId, EmployeeDTO updatedEmployeeDTO) {
		
		Query query = entityManager.createNativeQuery("UPDATE employees SET name = :name , birthday = :birthday , "
				+ "gender = :gender , salary = :salary , branch_id = :branchId , super_id = :superId WHERE employee_id = :employeeId ;");
		query.setParameter("name", updatedEmployeeDTO.getName());
		query.setParameter("birthday", updatedEmployeeDTO.getBirthday());
		query.setParameter("gender", updatedEmployeeDTO.getGender());
		query.setParameter("salary", updatedEmployeeDTO.getSalary());
		query.setParameter("branchId", updatedEmployeeDTO.getBranchId());
		query.setParameter("superId", updatedEmployeeDTO.getSuperId());
		query.setParameter("employeeId", employeeId);
		
		query.executeUpdate();
	}

	@Override
	public void updateEmployeeBranchByEmployeeId(Long employeeId, Long branchId) {
		
		Query query = entityManager.createNativeQuery("UPDATE employees SET branch_id = :branchId WHERE employee_id = :employeeId ;");
		query.setParameter("branchId", branchId);
		query.setParameter("employeeId", employeeId);
		
		query.executeUpdate();
	}
}
