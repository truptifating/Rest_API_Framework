package com.qa.test;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
public class GetAPITest extends TestBase 
{
	public GetAPITest() throws IOException 
	{
		super();
		
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
  
  @Test(priority=1)
  public void getApiTest() throws ClientProtocolException, IOException 
  {
	  restClient= new RestClient();
	  restClient.get(url);  
  }
}
