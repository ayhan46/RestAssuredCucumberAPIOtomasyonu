package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class GetBookingByIdTests extends BaseTests {

    @Test
    public void getBookingById(){

        Response response = given(spec)
                .when()
                .get("/booking/" + createBookingId());

        response
                .then()
                .statusCode(200);

        String firstname = response.jsonPath().getJsonObject("firstname");
        String lastname = response.jsonPath().getJsonObject("lastname");
        int totalprice = response.jsonPath().getJsonObject("totalprice");

        Assertions.assertEquals("Ayhan",firstname);
        Assertions.assertEquals("Kömesöğütlü",lastname);
        Assertions.assertEquals(200,totalprice);
    }


}
