package eu8.spartan.admin;

import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.*;

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

}
