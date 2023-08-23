package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetAllBookingsTests extends BaseTests {

    @Test
    public void getAllBookingsTest(){

        given(spec)
                .when()
                .get("/booking")
                .then()
                .statusCode(200);
    }

    @Test
    public void getBookings_with_firstname_filter_test(){

        int bookingId = createBookingId();

        spec.queryParam("firstname","Ayhan");
        spec.queryParam("lastname","Kömesöğütlü");

        Response response = given(spec)
                .when()
                .get("/booking");
        response
                .then()
                .statusCode(200);

        List<Integer> filtrelenenRezervasyon = response.jsonPath().getList("bookingid");
        Assertions.assertTrue(filtrelenenRezervasyon.contains(bookingId));
    }

}
