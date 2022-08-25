package serialization;

import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class UserTest {
	
	@Test
	public void createUserTest() {
		User user = new User("Pepe", "Student");
		
		baseURI = "https://reqres.in/api";
		
		given().
			contentType("application/json").
			body(user).
		when().
			post("/users").
		then().
			statusCode(201).
			body("name", equalTo(user.getName())).
			body("job", equalTo(user.getJob())).
			log().all();
	}
}
