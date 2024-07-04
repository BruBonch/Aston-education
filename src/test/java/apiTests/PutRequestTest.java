package apiTests;

import org.testng.annotations.Test;

import static specification.Specifications.*;
import static io.restassured.RestAssured.*;

public class PutRequestTest {
    @Test
    public void putRequestTest() {
        given()
                .spec(requestSpecification())
                .when()
                .body("This is expected to be sent back as part of response body.")
                .put("put")
                .then()
                .spec(responseSpecification())
                .log().all();
    }
}
