package eu8.spartan.editor;

import utilities.SpartanNewBase;

import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.SpartanUtils;

import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.*;
import static net.serenitybdd.rest.SerenityRest.given;

@SerenityTest
public class SpartanEditorPostTest extends SpartanNewBase {

    @DisplayName("Editor should be able to POST")
    @Test
    public void postSpartanAsEditor(){

        //create one spartan using util
        Map<String, Object> requestMap = SpartanUtils.getMap();
        System.out.println("requestMap = " + requestMap);

        //send a post request as editor
        given()
                .auth().basic("editor", "editor")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(requestMap).log().all()
        .when()
                .post("/spartans").prettyPrint();


        // status code is 201
        Ensure.that("Status code is 201", validatableResponse -> validatableResponse.statusCode(201));

        // content type is Json
        Ensure.that("Content-Type is JSON", validatableResponse -> validatableResponse.contentType(ContentType.JSON));

        // success message is A Spartan is Born!
        Ensure.that("Success message is A Spartan is Born!", validatableResponse -> validatableResponse.body("success", is("A Spartan is Born!")));

        // id is not null
        Ensure.that("ID is not null", validatableResponse -> validatableResponse.body("data.id", notNullValue()));

        // name is correct
        Ensure.that("Name is correct", validatableResponse -> validatableResponse.body("data.name", is(requestMap.get("name"))));

        // gender is correct
        Ensure.that("Gender is correct", validatableResponse -> validatableResponse.body("data.gender", is(requestMap.get("gender"))));

        // phone is correct
        Ensure.that("Phone is correct", validatableResponse -> validatableResponse.body("data.phone", is(requestMap.get("phone"))));

        // check location header ends with newly generated id
        String id = lastResponse().jsonPath().getString("data.id");
        Ensure.that("Check location header ends with newly generated id", validatableResponse -> validatableResponse.header("Location", endsWith(id)));
    }


}
