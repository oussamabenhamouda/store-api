package com.buildstore.vermeg.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Mapper {
	public static String asJsonString(Object object) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(object);
	}
}
