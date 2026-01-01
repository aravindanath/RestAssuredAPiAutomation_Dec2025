package framework.testscripts.contacts;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.google.gson.JsonObject;
import day1.Contants;
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
public class AddContactTc002 {

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

        Assert.assertEquals(response.statusCode(),200,"Status code is not 200");
        ChainTestListener.log("Status Code "+response.statusCode());
        token =  response.then().extract().path("token");

        ChainTestListener.log(response.prettyPrint());



    }


    @Test(description = "Add Contacts Request", dependsOnMethods = {"loginRequest"})
    public void addContactsRequest() {



            RestAssured.baseURI = Contants.BASE_URL;
            RestAssured.basePath = Contants.contacts;

        Lombok_AddContact path = new Lombok_AddContact();
            path.setFirstName(Utils.name());
            path.setLastName(Utils.lastname());
            path.setBirthdate(Utils.randomBirthDate());
            path.setEmail(Utils.email());
            path.setPhone(Utils.phone());
            path.setStreet1(Utils.street1());
            path.setStreet2(Utils.street2());
            path.setCity(Utils.city());
            path.setStateProvince(Utils.state());
            path.setPostalCode(Utils.postalCode());
            path.setCountry(Utils.country());




            // Request
            Response response = given().header("Authorization", "Bearer " + token).contentType(ContentType.JSON)
                    .body(path).log().all().post();
            response.prettyPrint();
             ChainTestListener.log("Status Code "+response.statusCode());
             ChainTestListener.log(response.prettyPrint());
            Assert.assertEquals(response.statusCode(),201,"Status code is not 201");
            System.out.println("Contact added successfully with status code: " + response.statusCode());



    }
}
