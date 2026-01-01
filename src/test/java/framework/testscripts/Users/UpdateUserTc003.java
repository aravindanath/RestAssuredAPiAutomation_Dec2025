package framework.testscripts.Users;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.google.gson.JsonObject;
import day1.Contants;
import day1.Lombok_UpdateUser;
import day1.Utils;
import day2.Lombok_AddContact;
import framework.lombok.Lombok_Login;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Listeners(ChainTestListener.class)
public class UpdateUserTc003 {

    String token;

    @Test(description = "Login Request")
    public void loginRequest() {

        RestAssured.baseURI = Contants.BASE_URL;
        RestAssured.basePath = Contants.loginUser;

        Lombok_Login lombokLogin = new Lombok_Login();
        lombokLogin.setEmail("stevj@test.com");
        lombokLogin.setPassword("Test@1234");


        // Request
        Response response =  given().contentType(ContentType.JSON)
                .body(lombokLogin).log().all().post();

        ChainTestListener.log("Status Code "+response.statusCode());
        Assert.assertEquals(response.statusCode(),200,"Status code is not 200");
        token =  response.then().extract().path("token");
        ChainTestListener.log(response.prettyPrint());



    }

    @Test(description = "Update User Request", dependsOnMethods = {"loginRequest"})
        public void updateUserRequest() {
        RestAssured.baseURI = Contants.BASE_URL;
        RestAssured.basePath = Contants.updateUser;

        Lombok_UpdateUser requestBody = new Lombok_UpdateUser();
        requestBody.setFirstName(Utils.name());
        requestBody.setLastName(Utils.lastname());
        requestBody.setEmail("stevj@test.com");
        requestBody.setPassword("Test@1234");

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .log().all()
                .patch();
        ChainTestListener.log("Status Code "+response.statusCode());
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");
        ChainTestListener.log(response.prettyPrint());

    }
}
