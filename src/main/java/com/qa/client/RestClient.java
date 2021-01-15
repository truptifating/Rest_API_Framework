package com.qa.client;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient 
{
  // 1. Get Method
	
	public void get(String url) throws ClientProtocolException, IOException 
	{
		CloseableHttpClient httpClient= HttpClients.createDefault(); 	// CreateDEfault method will create one client connection which return one closeable http client object		
		HttpGet httpGet=new HttpGet(url);  								// It will create one get connection with URL so this is http get request		
		CloseableHttpResponse httpRespone= httpClient.execute(httpGet); // For executing the URL
		
		// a. Getting Status Code		
		int statusCode= httpRespone.getStatusLine().getStatusCode();
		System.out.println("Status Code is-->"+statusCode);
		
		// b.Getting Json String		
		String responseString= EntityUtils.toString(httpRespone.getEntity(), "UTF-8"); // The entire json code will convert to string and it will store in object i.e. responseS tring	 //UTF is the standard	
		JSONObject responseJson=new JSONObject(responseString);             // Whole code will again convert to json and will save in responseJson object.
		System.out.println("Respone Json from API-->"+responseJson);             // We will get the body, header, status code from the server in json format
		
		// c. Getting all the Headers for headers testing
		Header[] headerArray=httpRespone.getAllHeaders();				
		HashMap<String, String> allHeaders= new HashMap<String, String>();	 // Converting header array to hashmap	
		for(Header header:headerArray)                                       // for iterating array
		{
			allHeaders.put(header.getName(), header.getValue());
		}		
		System.out.println("Headers Array-->"+allHeaders);
						
	}	
	
}
