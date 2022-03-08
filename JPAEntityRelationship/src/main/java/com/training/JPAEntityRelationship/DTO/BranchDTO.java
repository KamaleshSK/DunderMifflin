package com.training.JPAEntityRelationship.DTO;

import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import lombok.Data;

@Data
public class BranchDTO {
	
	@JsonProperty("branch_name")
	private String branchName;
	
	@JsonProperty("mgr")
	private Long mgrId;
	
	@JsonProperty("mgr_start_date")
	private Date mgrStartDate;
	
	public static BranchDTO newInstance(String jsonObject) {
		BranchDTO branch = null;
		try {
			ObjectMapper om = JsonMapper.builder().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).build();
			branch = om.readValue(jsonObject, BranchDTO.class);
		} catch (Exception e) {
			System.out.println("#### Exception on Employee Object Creation from json ####");
			e.printStackTrace();
		}
		return branch;
	}
	
	public String toJson() {
		String jsonObject = null;
		try {
			ObjectMapper om = JsonMapper.builder().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).build();
			jsonObject = om.writeValueAsString(this);
		} catch (Exception e) {
			System.out.println("#### Exception on converting Employee DTO to json string ####");
			e.printStackTrace();
		}
		return jsonObject;
	}
}
