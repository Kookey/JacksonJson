package com.steel.entity;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 
 * @author admin
 *
 */
public class JacksonTest {

	private JsonGenerator jsonGenerator = null;
	private ObjectMapper objectMapper = null;
	private AccountBean bean = null;

	@SuppressWarnings("deprecation")
	@Before
	public void init() {
		bean = new AccountBean();
		bean.setAddress("china-shanghai");
		bean.setEmai("wxlhdm@qq.com");
		bean.setId(1);
		bean.setName("wangxl");
		objectMapper = new ObjectMapper();
		try {
			jsonGenerator = objectMapper.getJsonFactory().createGenerator(System.out, JsonEncoding.UTF8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void destory() {

		try {
			if (jsonGenerator != null) {
				jsonGenerator.flush();
			}
			if (!jsonGenerator.isClosed()) {
				jsonGenerator.close();
			}
			jsonGenerator = null;
			objectMapper = null;
			bean = null;
			System.gc();
		} catch (Exception e) {
		}
	}
	
	/**
	 * Java对象转成json
	 */
	@Test
	public void writeEntityJson(){
		try {
			System.out.println("jsonGenerator");
			jsonGenerator.writeObject(bean);
			System.out.println();
			System.out.println("ObjectMapper");
			objectMapper.writeValue(System.out, bean);
		} catch (Exception e) {
		}
	}
}
