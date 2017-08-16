package com.client;

import java.io.IOException;



import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {
	
	 static String json1;
	/*public  String JsontoString(Block1 json) {
		JSONObject	 json1=new JSONObject();
		 str=json1.toString();
		 System.out.println("String is"+str);
		
		return str;
				
	}*/
	/*public  JSONObject StingtoJson() throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(str);
		
		return json;
		
	}*/
	public static  String PopulateJson(Block bl)  {
		
		 ObjectMapper mapper = new ObjectMapper();
	       
	           
				try {
					json1 = mapper.writeValueAsString(bl);
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	           
	         System.out.println(json1);
			return json1;
		
	         }
		
	
	public static <Block1>  Block1 populateString(String jsonString, Class<Block1> bl )   {
		
		 ObjectMapper mapper = new ObjectMapper();
		 Block1 b=null;
		try {
			b= mapper.readValue(jsonString, bl);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return b;
		
	}
		
}