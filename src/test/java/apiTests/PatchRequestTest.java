package apiTests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class PatchRequestTest {
    @Test
    public void patchRequestTest() {
        given()
                .when()
                .body("This is expected to be sent back as part of response body.")
                .patch("https://postman-echo.com/patch")
                .then()
                .statusCode(200)
                .body("data", equalTo("This is expected to be sent back as part of response body."))
                .log().all();

    }
}
