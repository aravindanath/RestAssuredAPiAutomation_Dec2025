package day1;

import com.google.gson.JsonObject;
import groovyjarjarpicocli.CommandLine;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AssignmentTC007 {
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

   @Getter
   @Setter
    public class UpdateUserRequest {

        private String firstName;
        private String lastName;
    }

    @Test(description = "PATCH Update User", dependsOnMethods = {"loginRequest"})
    public void updateUser() {

        RestAssured.baseURI = Contants.BASE_URL;
        RestAssured.basePath = Contants.updateUser; // "/users"

        UpdateUserRequest requestBody = new UpdateUserRequest();
        requestBody.setFirstName("UpdatedFirstName");
        requestBody.setLastName("UpdatedLastName");

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .log().all()
                .patch();

        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");
        response.prettyPrint();

        System.out.println("User updated successfully using PATCH + Lombok");
    }


}
