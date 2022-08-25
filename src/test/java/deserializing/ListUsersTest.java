package deserializing;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import java.util.List;

public class ListUsersTest {
	
	@Test
	public void deserializeTest() {
		baseURI = "https://reqres.in/api";
		
		ListUsers users = when().
							get("/users?page=2").
							as(ListUsers.class);
	
		System.out.println(users.toString());
		
		List<ListUsersData> data = users.getData();
		
		System.out.println(data);
	}
}
