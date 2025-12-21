package day1;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class AssignmentTC001 {

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

        for(int i=0;i<5;i++) {

            RestAssured.baseURI = Contants.BASE_URL;
            RestAssured.basePath = Contants.contacts;

            HashMap<String, String> path = new HashMap<>();
            path.put("firstName", Utils.name());
            path.put("lastName", Utils.lastname());
            path.put("email", Utils.email());
            path.put("phone", Utils.phone());
            path.put("street1", Utils.street1());
            path.put("street2", Utils.street2());
            path.put("birthdate", Utils.birthDate());
            path.put("city", Utils.city());
            path.put("stateProvince", Utils.state());
            path.put("postalCode", Utils.postalCode());
            path.put("country", Utils.country());


            // Request
            Response response = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON)
                    .body(path).log().all().post();
            response.prettyPrint();
            System.out.println("Contact added successfully with status code: " + response.statusCode());
            System.out.println("Count: " + (i + 1));
        }



    }
}


