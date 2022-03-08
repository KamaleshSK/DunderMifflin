package com.training.JPAEntityRelationship.Models;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Client")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {
	
	@Id
	@SequenceGenerator(name = "client_sequence", sequenceName = "client_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_sequence")
	private Long clientId;
	
	private String clientName;
	
	@ManyToOne(
			cascade = CascadeType.PERSIST,
			fetch = FetchType.LAZY
	)
	@JoinColumn(
			name = "branch_id",
			referencedColumnName = "branchId"
	)
	private Branch branchId;
	
}
