package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {
	
	//1.GET METHOD without headder
	
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException{
	
		// "createDefault" method will create a default client connection
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
	
	// create a get connection with http url
		
	HttpGet httpget = new HttpGet(url);   // Http "GET" request // hit the "GET" URL
	
	CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget);  
	
	return closeableHttpResponse;
		
	}

	//1.GET METHOD with header
	
		public CloseableHttpResponse get(String url,HashMap<String,String> headerMap) throws ClientProtocolException, IOException{
		
			// "createDefault" method will create a default client connection
			
			CloseableHttpClient httpClient =	HttpClients.createDefault();
		
		// create a get connection with http url
			
		HttpGet httpget = new HttpGet(url);   // Http "GET" request // hit the "GET" URL
		
		
		for(Entry<String, String> entry:headerMap.entrySet()){
			httpget.addHeader(entry.getValue(),entry.getValue());
		}
		
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget);  
		
		return closeableHttpResponse;
		
		
		}
}
