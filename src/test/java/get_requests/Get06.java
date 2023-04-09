package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get06 extends HerOkuAppBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/23
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is "application/json"
        And
            Response body should be like;
     {
    "firstname": "Bradley",
    "lastname": "Pearson",
    "totalprice": 132,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2022-10-27",
        "checkout": "2022-11-07"
    },
    "additionalneeds": "None"
}
     */


    @Test
    public void get06() {

        // Set the URL
        spec.pathParams("first","booking","second",23);

        //
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // Do Assertion
//        response.then().
//                statusCode(200).
//                contentType(ContentType.JSON).
//                body("firstname", equalTo("Josh"),
//                        "lastname",equalTo("Allen"),
//                        "totalprice",equalTo(111),
//                        "depositpaid",equalTo(true),
//                        "bookingdates.checkin",equalTo("2018-01-01"),
//                        "bookingdates.checkout",equalTo("2019-01-01"),
//                        "additionalneeds",equalTo("super bowls"));

        // 2. yol; JsonPath ile
//        JsonPath jsonPath = response.jsonPath();
//        assertEquals("Josh", jsonPath.getString("firstname"));
//        assertEquals("Allen", jsonPath.getString("lastname"));
//        assertEquals(111, jsonPath.getInt("totalprice"));
//        assertEquals(true, jsonPath.getBoolean("depositpaid"));
//        assertEquals("2018-01-01", jsonPath.getString("bookingdates.checkin"));
//        assertEquals("2019-01-01", jsonPath.getString("bookingdates.checkout"));
//        assertEquals("super bowls", jsonPath.getString("additionalneeds"));


        // 3. yol;
        // i) SoftAssert objesi olustur (testng dependency ekledik bunun icin)

        SoftAssert softAssert = new SoftAssert();

        // ii) Assertion (uyusmama durumunda yazilacak mesaj istege bagli zorunlu degil)
        JsonPath jsonPath = response.jsonPath();  // ==> actual degerlere ulasmak icin gerekli bu obje

        softAssert.assertEquals(jsonPath.getString("firstname"),"Josh","firstname uyusmadi");
        softAssert.assertEquals(jsonPath.getString("lastname"),"Allen","lastname uyusmadi");
        softAssert.assertEquals(jsonPath.getInt("totalprice"),111,"totalprice uyusmadi");
        softAssert.assertTrue(jsonPath.getBoolean("depositpaid"),"depositpaid uyusmadi"); // expected true ise assertEquals yerine assertTrue kullanilabilir
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkin"),"2018-01-01","bookingdates.checkin uyusmadi");
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkout"),"2019-01-01","bookingdates.checkout uyusmadi");
        softAssert.assertEquals(jsonPath.getString("additionalneeds"),"midnight snack","additionalneeds uyusmadi");

        // iii) softAssert.assertAll() diyerek dogrulamayi kontrol et, aksi taktirde test hep "PASS" olur
        softAssert.assertAll();
    }
}
