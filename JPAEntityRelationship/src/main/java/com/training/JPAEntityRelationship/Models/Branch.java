package com.training.JPAEntityRelationship.Models;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity	
@Table(name = "Branch")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "mgrId") // [ uncomment FetchType of mgrId is changed to LAZY ]
public class Branch {
	
	@Id
	@SequenceGenerator(name = "branch_sequence", sequenceName = "branch_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "branch_sequence")
	private Long branchId;
	
	@Column(nullable = false)
	private String branchName;
	
	@OneToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.EAGER
	)
	@JoinColumn(
			name = "mgr_id",
			referencedColumnName = "employeeId"
	)
	@OneToMany(
			mappedBy = "branchId"
	)
	@JsonIgnoreProperties("branchId")
	private Employees mgrId;
		
	private Date mgrStartDate;
	
}
