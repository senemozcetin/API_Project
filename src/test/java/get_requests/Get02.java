package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Get02 {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/0
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404  ---> NEGATIVE TEST
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Response body contains "Not Found"
        And
            Response body does not contain "TechProEd"
        And
            Server is "Cowboy"
     */


    @Test
    public void get02() {

        // 1. Adim: Set the URL
        String url = "https://restful-booker.herokuapp.com/booking/0";

        // 2. Adim: Set the expected data ==> (post, put, patch)

        // 3. Adim: Send the request and get the response
        Response response = given().when().get(url);
        response.prettyPrint();  // Not Found

        // 4. Adim: Do Assertion
        response.
                then().
                statusCode(404). // HTTP Status code should be 404
                statusLine("HTTP/1.1 404 Not Found"); // Status Line should be HTTP/1.1 404 Not Found

        // 5. Adim: Response body countains "Not Found"
        assertTrue(response.asString().contains("Not Found"));
        // asserTrue() methodu, method paraztezi icindeki degerin false oldugu durumda test "fail" olur


        // 6. Adim: Response body does not countains "TechProEd"
        assertFalse(response.asString().contains("TechProEd"));
        System.out.println(response.asString().contains("TechProEd")); // false doner


        // 7. Adim: Server is "Cowboy"
        assertEquals("CowboyX",response.header("Server"));



    }
}
