package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class UpdateBookingTest extends BaseTests {

    @Test
    public void updateBookingTest() {

       Response response = given(spec)
               .contentType(ContentType.JSON)
               .header("Cookie", "token="+createToken())
               .body(bookingObject("Cansu","Kömesöğütlü",500,false))
               .put("/booking/" + createBookingId());


       String firstName = response.jsonPath().getJsonObject("firstname");
       String lastName = response.jsonPath().getJsonObject("lastname");
       int totalprice = response.jsonPath().getJsonObject("totalprice");


       Assertions.assertEquals("Cansu",firstName);
       Assertions.assertEquals("Kömesöğütlü",lastName);
       Assertions.assertEquals(500,totalprice);
       Assertions.assertEquals(false,response.jsonPath().getJsonObject("depositpaid"));
    }





}