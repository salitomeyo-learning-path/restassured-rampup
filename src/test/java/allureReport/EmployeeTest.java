package allureReport;

import org.testng.annotations.Test;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;


public class EmployeeTest {
 
    String BaseURL = "http://dummy.restapiexample.com/api";
 
    @Test
    public void verifyUser() {
 
        // Given
        given()
              .filter(new AllureRestAssured()).
                 
      // WHEN
      when()
             .get(BaseURL + "/v1/employee/2")
                 
      // THEN
      .then()
             .statusCode(200)
             .statusLine("HTTP/1.1 200 OK")
            // To verify booking id at index 2
             .body("data.employee_name", equalTo("Garrett Winters"))
             .body("message", equalTo("Successfully! Record has been fetched."));
    }
 
    @Test
    public void createUser() {
 
        JSONObject data = new JSONObject();
 
        // Map<String, String> map = new HashMap<String, String>();
 
        data.put("employee_name", "APITest");
        data.put("employee_salary", "99999");
        data.put("employee_age", "30");
 
        // GIVEN
        given()
               .filter(new AllureRestAssured())
               .contentType(ContentType.JSON)
               .body(data.toString())
 
        // WHEN
        .when()
              .post(BaseURL + "/v1/create")
 
        // THEN
        .then()
              .statusCode(200)
              .body("data.employee_name", equalTo("APITest"))
             .body("message", equalTo("Successfully! Record has been added.")).log().all();
 
    }
 
}