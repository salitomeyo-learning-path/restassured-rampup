package files;
import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class MultiPartTest {
	
	@Test
	public void uploadTest() {
		given().
		multiPart("file", new File("C:\\Users\\salome.aristizabalg\\Downloads\\whatever.txt")).
		post("https://the-internet.herokuapp.com/upload").
		then().log().all();
	}
	
	@Test
	public void DownloadTest() {
		Response response = given().
								get("https://reqres.in/api/users").
								andReturn();
		
		byte[] bytes = response.getBody().asByteArray();
		File file = new File("C:\\Users\\salome.aristizabalg\\Downloads\\user-response.txt");
		try {
			Files.write(file.toPath(), bytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
