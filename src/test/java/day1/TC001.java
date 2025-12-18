package day1;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class TC001 {

    String token;

    @Test(description = "Login Request")
    public void loginRequest() {

        RestAssured.baseURI = Contants.BASE_URL;
        RestAssured.basePath = Contants.loginUser;

        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("email", "stevj@test.com");
        requestBody.addProperty("password", "Test@1234");

        // Request
        Response response =  given().contentType(ContentType.JSON)
               .body(requestBody).log().all().post();
        System.out.println(response.statusCode());
        Assert.assertEquals(response.statusCode(),200,"Status code is not 200");
        token =  response.then().extract().path("token");
       response.prettyPrint();



    }


    @Test(description = "Add Contacts Request", dependsOnMethods = {"loginRequest"})
    public void addContactsRequest() {

        RestAssured.baseURI = Contants.BASE_URL;
        RestAssured.basePath = Contants.contacts;

        HashMap<String, String> path = new HashMap<>();
        path.put("firstName", "Arvind");
        path.put("lastName", "Smith");
        path.put("email", "as@test.com");
        path.put("phone", "1234567890");
            path.put("street1", "123 Main St");
            path.put("street2", "Apt 4B");
            path.put("birthdate", "1970-01-01");
            path.put("city", "Metropolis");
            path.put("stateProvince", "State");
            path.put("postalCode", "12345");
            path.put("country", "Country");


        // Request
        Response response = given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .body(path).log().all().post();

        response.prettyPrint();



    }
}
