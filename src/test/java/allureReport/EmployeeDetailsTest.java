package allureReport;

import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;



@Epic("REST API Regression Testing using JUnit4")
@Feature("Verify CRUID Operations on Employee module")
public class EmployeeDetailsTest {
 
    String BaseURL = "http://dummy.restapiexample.com/api";
 
    @Test
    @Story("GET Request")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description : Verify the details of employee of id-2")
    public void verifyUser() {
 
        // Given
        given()
              .filter(new AllureRestAssured())
                 
      // WHEN
      .when()
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
    @Story("POST Request")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description : Verify the creation of a new employee")
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
             .body("message", equalTo("Successfully! Record has been added."));
 
    }
 
}