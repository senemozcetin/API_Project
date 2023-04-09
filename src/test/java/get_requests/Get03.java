package get_requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Get03 {

  /*  Given
            https://jsonplaceholder.typicode.com/todos/23
    When
            User send GET Request to the URL
    Then
            HTTP Status Code should be 200
    And
            Response format should be “application/json”
    And
		    “title” is “et itaque necessitatibus maxime molestiae qui quas velit”,
    And
		    “completed” is false
    And
		    “userId” is 2

   */


    @Test
    public void get03() {


        // 1. Adim: Set the URL
        String url = "https://jsonplaceholder.typicode.com/todos/23";

        // 2. Adim: Set the expected data ==> (post, put, patch)

        // 3. Adim: Send the request and get the response
        Response response = given().when().get(url);
        response.prettyPrint();  // Not Found


        // 4. Adim: Do Assertion
        /*
           Api üzerinde Body içerisinde yer almayan değerler için
        herhangi bir ekstra methoda gerek duymadan assert yapılırken,
        Body içerisinde yer alan değerler için body() methodu ile birlikte
        Matchers kullanılarak assert yapılır.
        */
        // 1. yol: Hard assert
        response.
                then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit")).
                body("completed",equalTo(false)).  // “completed” is false
                body("userId",equalTo(2));  // “userId” is 2
        // Coklu body() methodu ile assert yapildiginda ilk fail durumunda
        // Java bir sonraki body() methodu oncesi calismayi durdurur yani butun sorgulari yapamamis oluruz.


        // 2. yol: Soft assert
        response.
                then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                        "completed",equalTo(false),
                        "userId",equalTo(2));
        // Tek body icinde coklu assertion yaparak hard assert'u soft asserte cevirebiliriz
        // Fail durumunda body() icerisinde Java calismayi durdurmaz devam eder






    }
}
