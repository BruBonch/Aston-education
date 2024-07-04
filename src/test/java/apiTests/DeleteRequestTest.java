package apiTests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class DeleteRequestTest {
    @Test
    public void  deleteRequestTest() {
        given()
                .when()
                .body("This is expected to be sent back as part of response body.")
                .delete("https://postman-echo.com/delete")
                .then()
                .statusCode(200)
                .body("data", equalTo("This is expected to be sent back as part of response body."))
                .log().all();
    }
}
