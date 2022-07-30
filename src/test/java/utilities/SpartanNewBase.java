package utilities;

import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class SpartanNewBase {

    public static RequestSpecification requestSpec;
    public static ResponseSpecification responseSpec;
    public static RequestSpecification userSpec;
    public static RequestSpecification adminSpec;

    @BeforeAll
    public static void init(){
        // save baseurl inside this variable so that we don't need to type each http method
        baseURI = "http://44.201.107.188";
        basePath = "/api";
        port = 7000;

        requestSpec = given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin", "admin")
                .log().all();

        userSpec = given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin", "admin")
                .log().all();

        responseSpec = expect().statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .logDetail(LogDetail.ALL); //logging with response specification

    }

    @AfterAll
    public static void close(){
        //reset the info we set above, method comes from RestAssured
        reset();
    }


}
