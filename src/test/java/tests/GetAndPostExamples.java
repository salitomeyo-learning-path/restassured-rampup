package tests;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class GetAndPostExamples {
	
	@Test
	public void testGet() {
		baseURI = "https://reqres.in/api/";
		
		given().
			get("/users/2").
		then().
			statusCode(200).
			body("data.id", equalTo(2)).
			body("data.first_name", equalTo("Janet"));
	}
	
	@Test
	public void testPost() {
		Map<String, Object> map = new HashMap<String, Object>();
		
//		map.put("name", "Salome");
//		map.put("job", "Game Developer");
//		
//		System.out.println(map);
//		
//		JSONObject request = new JSONObject(map);
		
		JSONObject request = new JSONObject();
		request.put("name", "Salome");
		request.put("job", "Game Developer");
		
		System.out.println(request.toJSONString());
		
		baseURI = "https://reqres.in/api/";
		
		given().
			header("Content-Type", "application/json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			post("/users").
		then().
			statusCode(201).
			log().all();
	}
}
