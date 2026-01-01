package day1;

import com.google.gson.JsonObject;
import groovyjarjarpicocli.CommandLine;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AssignmentTC007 {
    String token;

    @Test(description = "Login Request")
    public void loginRequest() {

        RestAssured.baseURI = Contants.BASE_URL;
        RestAssured.basePath = Contants.loginUser;

        Lombok_Login requestBody = new Lombok_Login();
        requestBody.setEmail("stevj@test.com");
        requestBody.setPassword("Test@1234");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .log().all()
                .post();

        Assert.assertEquals(response.getStatusCode(), 200);
        token = response.then().extract().path("token");
        response.prettyPrint();
    }

    @Test(description = "PATCH Update User", dependsOnMethods = {"loginRequest"})
    public void updateUser() {

        RestAssured.baseURI = Contants.BASE_URL;
        RestAssured.basePath = Contants.updateUser; // "/users"

        Lombok_UpdateUser requestBody = new Lombok_UpdateUser();
        requestBody.setFirstName("UpdatedFirstName");
        requestBody.setLastName("UpdatedLastName");
        requestBody.setEmail("stevj@test.com");
        requestBody.setPassword("Test@1234");


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
