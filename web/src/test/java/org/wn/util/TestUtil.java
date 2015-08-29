package org.wn.util;

import org.wn.model.User;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtil {

	public static final String DEFAULT_USER_FIRST_NAME = "Johny";
	public static final String DEFAULT_USER_LAST_NAME = "Johny";
	public static final String DEFAULT_USER_EMAIL = "jonhy@test.do";
	
	public static User buildUser(){
		return buildUser(DEFAULT_USER_FIRST_NAME, DEFAULT_USER_LAST_NAME, DEFAULT_USER_EMAIL);
	}
	
	public static User buildUser(String firstName, String lastName, String email){
		User u = new User();
		u.setName(firstName);
		u.setLastName(lastName);
		u.setEmail(email);
		return u;
	}
		
	public static byte[] serializeObjectToJSON(Object o) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        byte[] content = mapper.writeValueAsBytes(o);
		return content;
	}
	
}
