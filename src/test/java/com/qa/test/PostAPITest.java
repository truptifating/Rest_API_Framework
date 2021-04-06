package com.qa.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

import junit.framework.Assert;

public class PostAPITest extends TestBase
{
	public PostAPITest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	TestBase testBase;
	String serviceurl;
	String apiurl;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	
	
	@BeforeMethod
	  public void setup() throws IOException 
	  {
		testBase= new TestBase();  
		serviceurl=prop.getProperty("URL");
		apiurl=prop.getProperty("serviceURL");
		url=serviceurl+apiurl;	
		
	  }
	
@Test
public void postApiTest() throws JsonGenerationException, JsonMappingException, IOException
{
	restClient = new RestClient();
	HashMap<String, String> headerMap= new HashMap<String, String>();
	headerMap.put("Content-Type", "application/json");
	
	//Jackson API for marshaling and unmarshling i.e. converting java to jason and jason to java
	//converting users java to json
	
	ObjectMapper mapper= new ObjectMapper();
	Users users= new Users("trupti","Leader");
	
	//Object to jason file i.e. converting the code to json
	mapper.writeValue(new File("/home/vishnu/Documents/Trupti_Test_Automation/Workspace/RestApi/src/main/java/com/qa/data/users.json"), users);
	
	//Object to jasonString file i.e. converting the code to jsonString
	String usersJsonSTring=mapper.writeValueAsString(users);
	System.out.println(usersJsonSTring);
	
	//Calling the post call from Rest Client class
	closeableHttpResponse=restClient.post(url, usersJsonSTring, headerMap);
	
	//1. Checking the status code
	int statusCode=closeableHttpResponse.getStatusLine().getStatusCode();
	Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_201);
	
	
	//2.Checking the Json String
	String responseString=EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
	System.out.println("Response String is= "+responseString);
	
	//Converting String to json
	JSONObject responseJson=new JSONObject(responseString);
	System.out.println("Response from API after converting to jsonis= "+responseJson);
	
	// Converting Json to Java Object
	Users usersObj=mapper.readValue(responseString, Users.class);//It will read users class and its value
	System.out.println("Response from Users= "+usersObj);
	
}
	
	
	
	
	
	
	

}
