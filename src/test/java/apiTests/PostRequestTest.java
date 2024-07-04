package apiTests;

import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class PostRequestTest {

    private final String BASE_URL = "https://postman-echo.com/";
    @Test
    public void postRequestFormData() {
        RestAssuredConfig config = RestAssuredConfig.config().encoderConfig(EncoderConfig.encoderConfig()
                        .defaultContentCharset("UTF-8"));

        given()
                .config(config)
                .contentType("application/x-www-form-urlencoded")
                .when()
                .formParam("foo1", "bar1")
                .formParam("foo2", "bar2")
                .post(BASE_URL + "post")
                .then()
                .statusCode(200)
                .body("form.foo1", is("bar1"))
                .body("form.foo2", is("bar2"))
                .log().all();
    }

    @Test
    public void postRequestRawText() {
        given()
                .contentType("text/plain")
                .when()
                .body("This is expected to be sent back as part of response body.")
                .post(BASE_URL + "post")
                .then()
                .statusCode(200)
                .body("data", equalTo("This is expected to be sent back as part of response body."))
                .log().all();
    }
}
