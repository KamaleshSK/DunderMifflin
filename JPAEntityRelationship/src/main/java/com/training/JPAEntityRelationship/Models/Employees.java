package com.training.JPAEntityRelationship.Models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity	
@Table(name = "Employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employees {
	
	@Id
	@SequenceGenerator(name = "employee_sequence", sequenceName = "employee_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_sequence")
	private Long employeeId;
	
	@Column(nullable = false)
	private String name;
	
	private Date birthday;
	
	private String gender;
	
	@Column(nullable = false)
	private Double salary;
	
	@ManyToOne(
			cascade = CascadeType.PERSIST,
			fetch = FetchType.LAZY
	)
	@JoinColumn(
			name = "branch_id",
			referencedColumnName = "branchId"
	)
	@OneToOne(
			mappedBy = "mgrId"
	)
	@JsonIgnoreProperties({"mgrId", "hibernateLazyInitializer"})
	private Branch branchId;
	
	private Long superId;
	
	@ManyToMany(
			cascade = CascadeType.PERSIST,
			fetch = FetchType.EAGER
	)
	@JoinTable(
			name = "works_with",
			joinColumns = @JoinColumn(
					name = "employee_id",
					referencedColumnName = "employeeId"
			),
			inverseJoinColumns = @JoinColumn(
					name = "client_id",
					referencedColumnName = "clientId"
			)
	)
	private List<Client> clients;
	
	public void addClient(Client client) {
		if (clients == null) clients = new ArrayList<>();
		clients.add(client);
	}
}
