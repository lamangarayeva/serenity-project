package eu8.spartan.admin;

import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.*;
import static net.serenitybdd.rest.SerenityRest.given;

@SerenityTest
public class SpartanAdminGetTest {

    @BeforeAll
    public static void init() {
        baseURI = "http://44.201.107.188:7000";
    }

    @Test
    public void getAllSpartan(){
        given().accept(ContentType.JSON)
                .auth().basic("admin", "admin")
        .when()
                .get("/api/spartans")
        .then().statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Test
    public void getOneSpartan(){
        given().accept(ContentType.JSON)
                .auth().basic("admin", "admin")
                .pathParam("id", 15)
                .when()
                .get("/api/spartans/{id}");

        // if you send a request using serenity rest, the response object can be obtained from
        // the method called lastResponse() without saved separately
        System.out.println("Status code = " + lastResponse().statusCode());

        //print id
        System.out.println("lastResponse().path(\"id\") = " + lastResponse().path("id"));

        // use json path with lastResponse and get the name
        String name = lastResponse().jsonPath().getString("name");
        System.out.println("name = " + name);
    }

    @DisplayName("GET request with Serenity Assertion way")
    @Test
    public void getOneSpartanAssertion(){

        given().accept(ContentType.JSON)
                .auth().basic("admin", "admin")
                .pathParam("id", 15)
                .when()
                .get("/api/spartans/{id}");

        // Serenity way of assertion

        Ensure.that("Status code is 200",validatableResponse -> validatableResponse.statusCode(200));

        Ensure.that("Content-type is JSON",validatableResponse -> validatableResponse.contentType(ContentType.JSON));

        Ensure.that("ID is 15", validatableResponse -> validatableResponse.body("id", is(15)));


    }

}
