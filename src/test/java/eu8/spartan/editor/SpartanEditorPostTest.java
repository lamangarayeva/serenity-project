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
                .when().post("/spartans").prettyPrint();
    }


}
