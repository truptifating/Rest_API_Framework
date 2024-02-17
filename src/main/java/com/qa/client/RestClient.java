

package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient 
{
  // 1. Get Method Without Headers
	
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException 
	{
		CloseableHttpClient httpClient= HttpClients.createDefault(); 	// CreateDEfault method will create one client connection which return one closeable http client object		
		HttpGet httpGet=new HttpGet(url);  								// It will create one get connection with URL so this is http get request		
		CloseableHttpResponse closeableHttpResponse= httpClient.execute(httpGet); // For executing the URL
		return closeableHttpResponse;								
	}	
	// 2. Get Method With Headers
	
		public CloseableHttpResponse get(String url, HashMap<String, String> headerMap) throws ClientProtocolException, IOException 
		{
			CloseableHttpClient httpClient= HttpClients.createDefault(); 	// CreateDEfault method will create one client connection which return one closeable http client object		
			HttpGet httpGet=new HttpGet(url);  								// It will create one get connection with URL so this is http get request	
			
			for(Map.Entry<String, String> entry :headerMap.entrySet())
			{
				httpGet.addHeader(entry.getKey(), entry.getValue());
				
			}
			CloseableHttpResponse closeableHttpResponse= httpClient.execute(httpGet); // For executing the URL
			return closeableHttpResponse;								
		}
	public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException
	{
		CloseableHttpClient httpClient= HttpClients.createDefault(); 	// CreateDEfault method will create one client connection which return one closeable http client object		
		HttpPost httpPost=new HttpPost(url);  
		httpPost.setEntity(new StringEntity(entityString)); //for payload
		
		//for headers
		for(Map.Entry<String, String> entry: headerMap.entrySet())
		{
			httpPost.addHeader(entry.getKey(),entry.getValue());
		}
		CloseableHttpResponse closeableHttpResponse=httpClient.execute(httpPost);
		return closeableHttpResponse;
	}
	
}
