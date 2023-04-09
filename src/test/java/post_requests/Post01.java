package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post01 extends JsonPlaceHolderBaseUrl {

    /*
         Given
           1)  https://jsonplaceholder.typicode.com/todos
           2)  {
                 "userId": 55,
                 "title": "Tidy your room",
                 "completed": false
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */

    @Test
    public void post01(){

        // Set the URL
        spec.pathParam("first","todos");

        // Set the expected data ==> Payland
        Map<String,Object> expectedData = new HashMap<>();
        expectedData.put("userId",55.0);
        expectedData.put("title","Tidy your room");
        expectedData.put("completed",false);
        System.out.println("expectedData = " + expectedData);


        // Send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).when().body(expectedData).post("/{first}");
        response.prettyPrint();

        // ** serialization(javayi jsona cevirmek) veya de-serializatin(jsondan javaya cevirmek) icin "gson" dependency'sini ekledik,
        // ** java objemizi json objemize cevirmek icin kullandik
        // ==> 2 turlu De-Serialization vardir
        //       i) Gson: Google tarafindan uretilmistir
        //      ii) Object Mapper: En populer olani

        // Do Assertion
        Map<String,Object> actualData = response.as(HashMap.class);  // DE-Serialization ==> JSON to JAVA ==> as() methodu ile gerceklestirdik
        System.out.println("actualData = " + actualData);

        assertEquals(201,response.statusCode());
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("userId"),actualData.get("userId"));


    }
}
