import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class NewAPITest {
	
	@Test
    public void TC01_getUserDetailsTest() {
        //The base URI to be used
        RestAssured.baseURI = "https://reqres.in/api/users/";
        //Define the specification of request. Server is specified by baseURI above.
        RequestSpecification httpRequest = RestAssured.given();
        //Makes calls to the server using Method type.
        Response response = httpRequest.request(Method.GET, "2");
        //Checks the Status Code
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        ResponseBody body = response.getBody();
        System.out.println(body.asString());
        
        JsonPath json = response.jsonPath();
        //String firstName = jsonPathEvaluator.get("first_name");
        System.out.println(json.toString());
        
     
        
    }
	
	@Test
    public void TC02_updateUserDetailsTest() {
        RestAssured.baseURI = "https://reqres.in/api/users/";
        RequestSpecification httpRequest = RestAssured.given();
        JSONObject updateData = new JSONObject();
        updateData.put("first_name", "Aarna");
        httpRequest.header("Content-Type", "application/json");
 
        httpRequest.body(updateData.toJSONString());
        Response response = httpRequest.request(Method.PUT, "5");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
 
        JsonPath newData = response.jsonPath();
        String name = newData.get("first_name");
        Assert.assertEquals(name, "Aarna");
    }
	
	@Test
	public void TC03_deleteUserTest() {
	   RestAssured.baseURI = "https://reqres.in/api/users/";
	   RequestSpecification httpRequest = RestAssured.given();
	   Response response = httpRequest.request(Method.DELETE, "4");
	   int statusCode = response.getStatusCode();
	   Assert.assertEquals(statusCode, 204);
	}
	
	@Test
	public void TC04_CreateEmployee() { 
	 RestAssured.baseURI ="https://reqres.in/";
	 RequestSpecification request = RestAssured.given();
	 
	 JSONObject requestParams = new JSONObject();
	 requestParams.put("name", "TestName");
	 requestParams.put("job", "QA Enginner");
	 request.body(requestParams.toJSONString());
	 Response response = request.post("/api/users");
	 int statusCode = response.getStatusCode();
	 Assert.assertEquals(statusCode, 201);
	}
	
}
