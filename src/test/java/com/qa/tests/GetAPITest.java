package com.qa.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetAPITest extends TestBase {
	
	public GetAPITest() throws FileNotFoundException, IOException {
		super();
		
	}

	TestBase testBase;
	String serviceURL;
	String apiURL;
	String url;
	RestClient restClient;
	
	CloseableHttpResponse closeableHttpResponse;
	
	@BeforeMethod
	public void SetUp() throws ClientProtocolException, IOException {
		
		 testBase = new TestBase();
		 serviceURL = prop.getProperty("URL");
		 apiURL = prop.getProperty("serviceURL");
		 
	//https://reqres.in//api/users
		 
		url= serviceURL + apiURL;
	}

	@Test (priority=1)
	public void getApiTestWithoutHeader() throws ClientProtocolException, IOException{
	
	restClient = new RestClient();
	closeableHttpResponse= restClient .get(url);
	
		// a. Status Code
	
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		
		System.out.println("Status Code----->"+statusCode);
		
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,"Status code is not 200");
		
		//b. JSON String
		
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
		
		// converting responseString into JSON object
		
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response JSON from API--->"+responseJson);
		
		// Single Value Assertion
		// perpage:
		String perPageValue = TestUtil.getValueByJpath(responseJson,"/per_page");
		System.out.println("Vaule of per page --->"+perPageValue);
		
		Assert.assertEquals(Integer.parseInt(perPageValue),3);
		
		// Total value:
		
		String TotalValue = TestUtil.getValueByJpath(responseJson,"/total");
		System.out.println("Total Vaule --->"+TotalValue);
		
		Assert.assertEquals(Integer.parseInt(TotalValue),12);
		
		//GET the value from JSON Array:
		
		String lastName =TestUtil.getValueByJpath(responseJson, "/data[0]/last_name");
		String id =TestUtil.getValueByJpath(responseJson, "/data[0]/id");
		String avatar =TestUtil.getValueByJpath(responseJson, "/data[0]/avatar");
		String FirstName =TestUtil.getValueByJpath(responseJson, "/data[0]/first_name");
		
		System.out.println(lastName);
		System.out.println(id);
		System.out.println(avatar);
		System.out.println(FirstName);
		
		
		/*Headers need to be validated to get the header key and value pairs
		 create HashMap for key and value pairs */
		
		// c. All Headers
		
		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		
		HashMap<String,String> allHeaders =new HashMap<String,String>();
		
		for(Header header:headersArray){
			
			allHeaders.put(header.getName(), header.getValue());
			
		}
		
		System.out.println("Header Arrays--->"+allHeaders);
			
	}
	
	@Test (priority=2)
	public void getApiTestWithHeader() throws ClientProtocolException, IOException{
	
	restClient = new RestClient();
	
	HashMap<String,String> headerMap = new HashMap<String,String>();
	headerMap.put("Content_Type", "application/json");
		
	closeableHttpResponse= restClient .get(url,headerMap);
	
	
	// a. Status Code
	
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		
		System.out.println("Status Code----->"+statusCode);
		
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,"Status code is not 200");
		
		//b. JSON String
		
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
		
		// converting responseString into JSON object
		
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response JSON from API--->"+responseJson);
		
		// Single Value Assertion
		// perpage:
		String perPageValue = TestUtil.getValueByJpath(responseJson,"/per_page");
		System.out.println("Vaule of per page --->"+perPageValue);
		
		Assert.assertEquals(Integer.parseInt(perPageValue),3);
		
		// Total value:
		
		String TotalValue = TestUtil.getValueByJpath(responseJson,"/total");
		System.out.println("Total Vaule --->"+TotalValue);
		
		Assert.assertEquals(Integer.parseInt(TotalValue),12);
		
		//GET the value from JSON Array:
		
		String lastName =TestUtil.getValueByJpath(responseJson, "/data[0]/last_name");
		String id =TestUtil.getValueByJpath(responseJson, "/data[0]/id");
		String avatar =TestUtil.getValueByJpath(responseJson, "/data[0]/avatar");
		String FirstName =TestUtil.getValueByJpath(responseJson, "/data[0]/first_name");
		
		System.out.println(lastName);
		System.out.println(id);
		System.out.println(avatar);
		System.out.println(FirstName);
		
		
		/*Headers need to be validated to get the header key and value pairs
		 create HashMap for key and value pairs */
		
		// c. All Headers
		
		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		
		HashMap<String,String> allHeaders =new HashMap<String,String>();
		
		for(Header header:headersArray){
			
			allHeaders.put(header.getName(), header.getValue());
			
		}
		
		System.out.println("Header Arrays--->"+allHeaders);
		
	
	
	}

}
