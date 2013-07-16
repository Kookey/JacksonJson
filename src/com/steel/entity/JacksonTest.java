package com.steel.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

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
			jsonGenerator = objectMapper.getJsonFactory().createGenerator(
					System.out, JsonEncoding.UTF8);
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
	//@Test
	public void writeEntityJson() {
		try {
			System.out.println("jsonGenerator");
			jsonGenerator.writeObject(bean);
			System.out.println();
			System.out.println("ObjectMapper");
			objectMapper.writeValue(System.out, bean);
		} catch (Exception e) {
		}
	}
	/**
	 * 将Map集合转成Json字符串
	 */
	//@Test
	public void writeMapJson() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", bean.getName());
			map.put("account", bean);
			bean = new AccountBean();
			bean.setAddress("china-beijing");
			bean.setEmai("wxlhdm@163.com");
			map.put("account2", bean);
			System.out.println("jsonGenerator");
			jsonGenerator.writeObject(map);
			System.out.println("");
			System.out.println("objectMapper");
			objectMapper.writeValue(System.out, map);
		} catch (Exception e) {
		}
	}
	
	/**
	 * 将List集合转成json
	 */
	@Test
	public void writeListJSON(){
		List<AccountBean> list = new ArrayList<AccountBean>();
		list.add(bean);
		bean = new AccountBean();
		bean.setId(2);
		bean.setAddress("address2");
		bean.setEmai("email2");
		bean.setName("houdm");
		list.add(bean);
		
		System.out.println("jsonGenerator");
		try {
			//list转成JSON字符串
			jsonGenerator.writeObject(list);
			System.out.println();
			System.out.println("objectMappper");
			//用objectMapper直接返回list转成的JSON字符串
			System.out.println("1、" + objectMapper.writeValueAsString(list));
			System.out.println("2、");
			//objectMapper list转成JSON字符串
			objectMapper.writeValue(System.out, list);
		} catch (Exception e) {
		}
	}
	
	public void writeOthersJSON() {
		String[] arr = {"a" ,"b" ,"c"};
		System.out.println("jsonGenerator");
		String str = "hello world jackson";
		try {
			//byte
			jsonGenerator.writeBinary(str.getBytes());
			//boolean
			jsonGenerator.writeBoolean(true);
			//null
			jsonGenerator.writeNull();
			//float
			jsonGenerator.writeNumber(2.2f);
			//char
			jsonGenerator.writeRaw("c");
			//String
			jsonGenerator.writeRaw(str, 5, 10);
			//String
			jsonGenerator.writeRawValue(str, 5, 5);
			//String
			jsonGenerator.writeString(str);
			
			jsonGenerator.writeTree(JsonNodeFactory.instance.POJONode(str));
			System.out.println();
			
			//Object
			jsonGenerator.writeStartObject();
			jsonGenerator.writeObjectFieldStart("user");
			jsonGenerator.writeStringField("name", "jackson");
			jsonGenerator.writeBooleanField("sex", true);
			jsonGenerator.writeNumberField("age", 22);
		} catch (Exception e) {
			
		}
	}
}
