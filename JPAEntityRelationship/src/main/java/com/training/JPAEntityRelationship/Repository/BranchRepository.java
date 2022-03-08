package com.training.JPAEntityRelationship.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.JPAEntityRelationship.Models.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
	
	Branch findByBranchId(Long branchId);
	
}
