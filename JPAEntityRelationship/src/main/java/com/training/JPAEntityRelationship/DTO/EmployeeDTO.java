package com.training.JPAEntityRelationship.DTO;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import lombok.Data;

@Data
public class EmployeeDTO {
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("birthday")
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date birthday;
	
	@JsonProperty("gender")
	private String gender;
	
	@JsonProperty("salary")
	private Double salary;

	@JsonProperty("branch_id")
	private Long branchId;
	
	@JsonProperty("super_id")
	private Long superId;
	
	public static EmployeeDTO newInstance(String jsonObject) {
		EmployeeDTO employee = null;
		try {
			ObjectMapper om = JsonMapper.builder().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).build();
			employee = om.readValue(jsonObject, EmployeeDTO.class);
		} catch (Exception e) {
			System.out.println("#### Exception on Employee Object Creation from json ####");
			e.printStackTrace();
		}
		return employee;
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
