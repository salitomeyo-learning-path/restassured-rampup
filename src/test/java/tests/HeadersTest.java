package tests;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import io.restassured.http.Cookie;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class HeadersTest {
	@Test
	public void whenUsePathParam_thenOK() {
	    given().pathParam("user", "eugenp")
	      .when().get("/users/{user}/repos")
	      .then().statusCode(200);
	}
	
	@Test
	public void whenUseMultiplePathParam_thenOK() {
	    given().pathParams("owner", "eugenp", "repo", "tutorials")
	      .when().get("/repos/{owner}/{repo}")
	      .then().statusCode(200);

	    given().pathParams("owner", "eugenp")
	      .when().get("/repos/{owner}/{repo}","tutorials")
	      .then().statusCode(200);
	}
	
	@Test
	public void whenUseQueryParam_thenOK() {
	    given().queryParam("q", "john").when().get("/search/users")
	      .then().statusCode(200);

	    given().param("q", "john").when().get("/search/users")
	      .then().statusCode(200);
	}
	
	@Test
	public void whenUseMultipleQueryParam_thenOK() {
	 
	    int perPage = 20;
	    given().queryParam("q", "john").queryParam("per_page",perPage)
	      .when().get("/search/users")
	      .then().body("items.size()", is(perPage));   
	     
	    given().queryParams("q", "john","per_page",perPage)
	      .when().get("/search/users")
	      .then().body("items.size()", is(perPage));
	}
	
	@Test
	public void whenUseFormParam_thenSuccess() {
	 
	    given().formParams("username", "john","password","1234").post("/");

	    given().params("username", "john","password","1234").post("/");
	}
	
	@Test
	public void whenUseCustomHeader_thenOK() {
	 
	    given().header("User-Agent", "MyAppName").when().get("/users/eugenp")
	      .then().statusCode(200);
	}
	
	@Test
	public void whenUseMultipleHeaderValues_thenOK() {
	 
	    given().header("My-Header", "val1", "val2")
	      .when().get("/users/eugenp")
	      .then().statusCode(200);
	}
	
	@Test
	public void whenUseMultipleHeaders_thenOK() {
	 
	    given().headers("User-Agent", "MyAppName", "Accept-Charset", "utf-8")
	      .when().get("/users/eugenp")
	      .then().statusCode(200);
	}
	
	@Test
	public void whenUseCookie_thenOK() {
	 
	    given().cookie("session_id", "1234").when().get("/users/eugenp")
	      .then().statusCode(200);
	}
	
	@Test
	public void whenUseCookieBuilder_thenOK() {
	    Cookie myCookie = new Cookie.Builder("session_id", "1234")
	      .setSecured(true)
	      .setComment("session id cookie")
	      .build();

	    given().cookie(myCookie)
	      .when().get("/users/eugenp")
	      .then().statusCode(200);
	}
	
	@Test 
	public void IteratingHeaders() 
	{ baseURI = "https://demoqa.com/BookStore/v1/Books"; 
	 RequestSpecification httpRequest = given(); 
	 Response response = httpRequest.get(""); 
	 // Get all the headers and then iterate over allHeaders to print each header 
	 Headers allHeaders = response.headers(); 
	 // Iterate over all the Headers 
	 for(Header header : allHeaders) { 
	   System.out.println("Key: " + header.getName() + " Value: " + header.getValue()); 
	 } 
	}
}
