package apiTests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static specification.Specifications.requestSpecification;
import static specification.Specifications.responseSpecification;

public class DeleteRequestTest {
    @Test
    public void  deleteRequestTest() {
        given()
                .spec(requestSpecification())
                .when()
                .body("This is expected to be sent back as part of response body.")
                .delete("delete")
                .then()
                .spec(responseSpecification())
                .log().all();
    }
}
