package day1;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AssignmentTC003 {

    String token;

    @Test(description = "Login Request")
    public void loginRequest() {

        RestAssured.baseURI = Contants.BASE_URL;
        RestAssured.basePath = Contants.loginUser;

        Lombok_Login requestBody = new Lombok_Login();
        requestBody.setEmail("stevj@test.com");
        requestBody.setPassword("Test@1234");

        Response response = given().contentType(ContentType.JSON).body(requestBody)
                .log().all().post();

        Assert.assertEquals(response.getStatusCode(), 200);
        token = response.then().extract().path("token");
        response.prettyPrint();
    }

    @Test(description = "Get User Profile", dependsOnMethods = {"loginRequest"})
    public void getUserProfile() {

        RestAssured.baseURI = Contants.BASE_URL;
        RestAssured.basePath = Contants.userProfile;

        Response response = given().log().all().header("Authorization", "Bearer " + token).get();

        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");

        response.prettyPrint();
    }


}
