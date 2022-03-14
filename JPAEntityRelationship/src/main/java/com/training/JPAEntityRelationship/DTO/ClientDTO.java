package com.training.JPAEntityRelationship.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import lombok.Data;

@Data
public class ClientDTO {

	@JsonProperty("client_name")
	private String clientName;
	
	@JsonProperty("branch_id")
	private Long branchId;
	
	public static ClientDTO newInstance(String jsonObject) {
		ClientDTO client = null;
		try {
			ObjectMapper om = JsonMapper.builder().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).build();
			client = om.readValue(jsonObject, ClientDTO.class);
		} catch (Exception e) {
			System.out.println("#### Exception on Employee Object Creation from json ####");
			e.printStackTrace();
		}
		return client;
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
