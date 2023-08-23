package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Booking;
import models.BookingDates;
import models.BookingResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class CreateBookingTests extends BaseTests {

    @Test
    public void createBookingTest(){



        Response response = createBooking();

        Assertions.assertEquals("Ayhan",response.jsonPath().getJsonObject("booking.firstname"));
        Assertions.assertEquals("Kömesöğütlü",response.jsonPath().getJsonObject("booking.lastname"));
        Assertions.assertEquals(200,(Integer) response.jsonPath().getJsonObject("booking.totalprice"));
        Assertions.assertEquals(true,response.jsonPath().getJsonObject("booking.depositpaid"));
    }

    @Test
    public void createBookingWithPojo(){
        BookingDates bookingDates = new BookingDates("2023-03-01","2023-03-05");
        Booking booking = new Booking("Cansu","Kömesöğütlü",500,false,bookingDates,"Kahvalti");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .body(booking)
                .when()
                .post("/booking");
        response
                .then()
                .statusCode(200);

        BookingResponse bookingResponse = response.as(BookingResponse.class);
        System.out.println(bookingResponse + " Booking response kaydedildi ");

        Assertions.assertEquals("Cansu",bookingResponse.getBooking().getFirstname());
        Assertions.assertEquals("Kömesöğütlü",bookingResponse.getBooking().getLastname());
        Assertions.assertEquals("Kahvalti",bookingResponse.getBooking().getAdditionalneeds());
    }

}
