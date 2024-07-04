package apiTests;

import org.testng.annotations.Test;
import static specification.Specifications.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetRequestTest {
    @Test
    public void getRequestTest() {
        given()
                .spec(requestSpecification())
                .when()
                .get("get?foo1=bar1&foo2=bar2")
                .then()
                .body("args.foo1", is("bar1"))
                .body("args.foo2", is("bar2"))
                .statusCode(200)
                .log().all();
    }
}
