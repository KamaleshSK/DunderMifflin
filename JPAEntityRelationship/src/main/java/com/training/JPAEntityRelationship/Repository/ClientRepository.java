package com.training.JPAEntityRelationship.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.JPAEntityRelationship.Models.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
	
	Client findByClientId(Long ClientId);
	
	void deleteById(Long id);
	
	List<Client> findAllByBranchId(Long branchId);
	
	List<Client> findAll();
	
}
