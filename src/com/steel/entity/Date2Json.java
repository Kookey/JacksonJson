package com.steel.entity;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Date2Json {

	@Test
	public void Date2json() {
		Company comm = new Company();
		comm.setId(1);
		comm.setPrice(4000.5);
		comm.setCompany("上海工商");
		comm.setSize("small");
		comm.setDate(new Date());
		comm.setVisible((byte) 0);

		ObjectMapper mapper = new ObjectMapper();
		StringWriter sw = new StringWriter();
		try {
			mapper.writeValue(sw, comm);
			System.out.println(sw.toString());
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
