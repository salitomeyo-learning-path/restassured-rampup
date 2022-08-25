package tests;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

public class AuthenticationTest {
	
	@Test
	public void test1() {
		baseURI = "http://restapi.demoqa.com";
		
		given().
			auth().
			preemptive().
			basic("ToolsQA", "TestPassword").
		when().
			get("/authentication/CheckForAuthentication").
		then().
			statusCode(200);
	}
	
	public void setup() {
		baseURI = "http://restapi.demoqa.com";
		
		authentication = preemptive().basic("ToolsQA", "TestPassword");
	}
}
