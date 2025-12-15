package day1;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PostRequest_AddUser {

    @Test(description = "Add User Request")
    public void postRequestTest() {

        RestAssured.baseURI = Contants.BASE_URL;
        RestAssured.basePath = Contants.addUser;

        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("firstName", "John");
        requestBody.addProperty("email", "stevj@test.com");
        requestBody.addProperty("password", "Test@1234");
        requestBody.addProperty("lastName", "Doe");
        // Request
       Response response =  given().contentType(ContentType.JSON)
               .body(requestBody).log().all().post();

       response.prettyPrint();


    }
}
