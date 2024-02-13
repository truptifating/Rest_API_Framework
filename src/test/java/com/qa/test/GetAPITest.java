package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

import junit.framework.Assert;
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
	  closeableHttpResponse=restClient.get(url);  
	  
	  
	  		// a. Getting Status Code		
			int statusCode= closeableHttpResponse.getStatusLine().getStatusCode();
			System.out.println("Status Code is-->"+statusCode);
			
			// b. Validating the status code
		    Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200);
			
			// b.Getting Json String		
			String responseString= EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8"); // The entire json code will convert to string and it will store in object i.e. responseS tring	 //UTF is the standard	
			JSONObject responseJson=new JSONObject(responseString);             // Whole code will again convert to json and will save in responseJson object.
			System.out.println("Respone Json from API-->"+responseJson); 
			
			//single value assertion:
			//per_page:// We will get the body, header, status code from the server in json format
			String perPageValue=TestUtil.getvalueByJPath(responseJson, "/per_page"); 
			System.out.println("value of per page is-->"+ perPageValue);
			Assert.assertEquals(Integer.parseInt(perPageValue), 3);
			
			
			
			//total:
			String totalValue = TestUtil.getvalueByJPath(responseJson, "/total");
			System.out.println("value of total is-->"+ totalValue);		
			Assert.assertEquals(Integer.parseInt(totalValue), 12);

			//get the value from JSON ARRAY:
			String lastName = TestUtil.getvalueByJPath(responseJson, "/data[0]/last_name");
			String id = TestUtil.getvalueByJPath(responseJson, "/data[0]/id");
			String avatar = TestUtil.getvalueByJPath(responseJson, "/data[0]/avatar");
			String firstName = TestUtil.getvalueByJPath(responseJson, "/data[0]/first_name");

			System.out.println(lastName);
			System.out.println(id);
			System.out.println(avatar);
			System.out.println(firstName);
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			// c. Getting all the Headers for headers testing
			Header[] headerArray=closeableHttpResponse.getAllHeaders();				
			HashMap<String, String> allHeaders= new HashMap<String, String>();	 // Converting header array to hashmap	
			for(Header header:headerArray)                                       // for iterating array
			{
				allHeaders.put(header.getName(), header.getValue());
			}		
			System.out.println("Headers Array-->"+allHeaders);
  }
}
