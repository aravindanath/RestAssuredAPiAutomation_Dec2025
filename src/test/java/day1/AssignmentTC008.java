package day1;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AssignmentTC008 {

    String token;

    @Test(description = "Login Request")
    public void loginRequest() {

        RestAssured.baseURI = Contants.BASE_URL;
        RestAssured.basePath = Contants.loginUser;

        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("email", "stevj@test.com");
        requestBody.addProperty("password", "Test@1234");

        // Request
        Response response = given().contentType(ContentType.JSON)
                .body(requestBody).log().all().post();
        System.out.println(response.statusCode());
        Assert.assertEquals(response.statusCode(), 200, "Status code is not 200");
        token = response.then().extract().path("token");
        response.prettyPrint();

    }

        @Test(description = "POST Logout User", dependsOnMethods = {"loginRequest"})
        public void logoutUser() {

            RestAssured.baseURI = Contants.BASE_URL;
            RestAssured.basePath = Contants.logoutUser;

            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .log().all().post();

            Assert.assertEquals(response.getStatusCode(), 200, "Logout failed");

            response.prettyPrint();
            System.out.println("User logged out successfully");
        }
    }


