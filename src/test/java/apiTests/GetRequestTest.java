package apiTests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class GetRequestTest {
    @Test
    public void getRequestTest() {
        given()
                .contentType("json")
                .when()
                .get("https://postman-echo.com/get?foo1=bar1&foo2=bar2")
                .then()
                .body("args.foo1", is("bar1"))
                .body("args.foo2", is("bar2"))
                .statusCode(200)
                .log().all();
    }
}
