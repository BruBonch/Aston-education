package apiTests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class PutRequestTest {
    @Test
    public void putRequestTest() {
        given()
                .contentType("text/plain")
                .when()
                .body("This is expected to be sent back as part of response body.")
                .put("https://postman-echo.com/put")
                .then()
                .statusCode(200)
                .body("data", equalTo("This is expected to be sent back as part of response body."))
                .log().all();
    }
}
