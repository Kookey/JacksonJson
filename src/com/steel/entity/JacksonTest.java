package com.steel.entity;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

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
	// @Test
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
	// @Test
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
	// @Test
	public void writeListJSON() {
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
			// list转成JSON字符串
			jsonGenerator.writeObject(list);
			System.out.println();
			System.out.println("objectMappper");
			// 用objectMapper直接返回list转成的JSON字符串
			System.out.println("1、" + objectMapper.writeValueAsString(list));
			System.out.println("2、");
			// objectMapper list转成JSON字符串
			objectMapper.writeValue(System.out, list);
		} catch (Exception e) {
		}
	}

	// @Test
	public void writeOthersJSON() {
		String[] arr = { "a", "b", "c" };
		System.out.println("jsonGenerator");
		String str = "hello world jackson";
		try {
			// byte
			jsonGenerator.writeBinary(str.getBytes());
			// boolean
			jsonGenerator.writeBoolean(true);
			// null
			jsonGenerator.writeNull();
			// float
			jsonGenerator.writeNumber(2.2f);
			// char
			jsonGenerator.writeRaw("c");
			// String
			jsonGenerator.writeRaw(str, 5, 10);
			// String
			jsonGenerator.writeRawValue(str, 5, 5);
			// String
			jsonGenerator.writeString(str);

			jsonGenerator.writeTree(JsonNodeFactory.instance.POJONode(str));
			System.out.println();

			// Object
			jsonGenerator.writeStartObject();
			jsonGenerator.writeObjectFieldStart("user");
			jsonGenerator.writeStringField("name", "jackson");
			jsonGenerator.writeBooleanField("sex", true);
			jsonGenerator.writeNumberField("age", 22);
			jsonGenerator.writeEndObject();

			jsonGenerator.writeArrayFieldStart("infos");
			jsonGenerator.writeNumber(22);
			jsonGenerator.writeString("this is array");
			jsonGenerator.writeEndArray();
			jsonGenerator.writeEndObject();

			AccountBean bean = new AccountBean();
			bean.setAddress("address");
			bean.setEmai("email");
			bean.setId(1);
			// complex Object
			jsonGenerator.writeStartObject();
			jsonGenerator.writeObjectField("user", bean);
			jsonGenerator.writeObjectField("infos", arr);
			jsonGenerator.writeEndObject();
		} catch (Exception e) {

		}
	}

	/**
	 * JSON转成Java对象
	 */
	//@Test
	public void readJson2Entity() {
		String json = "{\"address\":\"address\",\"name\":\"wxlhdm\",\"id\":1,\"emai\":\"email\"}";
		AccountBean acc;
		try {
			acc = objectMapper.readValue(json, AccountBean.class);
			System.out.println(acc.getName());
			System.out.println(acc);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 将json字符串转成List<Map>集合
	 */
	//@Test
	@SuppressWarnings("unchecked")
	public void readJson2List(){
		String json = "[{ \"address\":\"address2\",\"name\":\"jackson2\",\"id\":2,\"emai\":\"email\"},"+"{\"address\":\"address\",\"name\":\"jackson\",\"id\":1,\"emai\":\"email\"}]";
		try {
			List<LinkedHashMap<String,Object>> list = objectMapper.readValue(json, List.class);
			System.out.println(list.size());
			for(int i=0; i<list.size();i++){
				Map<String,Object> map = list.get(i);
				Set<String> set = map.keySet();
				for(Iterator<String> it = set.iterator();it.hasNext();){
					String key = it.next();
					System.out.println(key+":"+map.get(key));
				}
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Json字符串转成Array数组
	 */
	//@Test
	public void readJson2Array(){
		String json = "[{ \"address\":\"address2\",\"name\":\"jackson2\",\"id\":2,\"emai\":\"email\"},"
					  +"{\"address\":\"address\",\"name\":\"jackson\",\"id\":1,\"emai\":\"email\"}]";
		try {
			AccountBean[] arr = objectMapper.readValue(json, AccountBean[].class);
			System.out.println("数组长度:"+arr.length);
			for(int i=0;i<arr.length;i++){
				System.out.println(arr[i]);
			}
			List<AccountBean> list = Arrays.asList(arr);
			System.out.println(list.size());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * json字符串转成Map
	 */
	//@Test
	@SuppressWarnings("unchecked")
	public void readJson2Map(){
		String json = "{\"success\":true,\"A\":{\"address\":\"address2\",\"name\":\"haha2\",\"id\":2,\"emai\":\"email2\"},"+"\"B\":{\"address\":\"address\",\"name\":\"haha\",\"id\":1,\"emai\":\"email\"}}";
		try {
			Map<String,Map<String,Object>> maps = objectMapper.readValue(json, Map.class);
			System.out.println(maps.size());
			Set<String> key = maps.keySet();
			Iterator<String> iter = key.iterator();
			while(iter.hasNext()){
				String field = iter.next();
				System.out.println(field+":"+maps.get(field));
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Jackson对XML的支持
	@Test
	public void writeObject2Xml(){
		System.out.println("XmlMapper");
		XmlMapper xml = new XmlMapper();
		
		StringWriter sw = new StringWriter();
		try {
			xml.writeValue(sw, bean);
			System.out.println(sw.toString());
			List<AccountBean> list = new ArrayList<AccountBean>();
			list.add(bean);
			list.add(bean);
			System.out.println(xml.writeValueAsString(list));
			Map<String,AccountBean> map = new HashMap<String,AccountBean>();
			map.put("A", bean);
			map.put("B", bean);
			System.out.println(xml.writeValueAsString(map));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
