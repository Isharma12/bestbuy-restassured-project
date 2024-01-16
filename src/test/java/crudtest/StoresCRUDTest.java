package crudtest;

import com.bestbuy.model.StoresPojo;
import com.bestbuy.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresCRUDTest extends TestUtils {
    static ValidatableResponse response;
    static int storeId;
    static String name = "Prime" + TestUtils.getRandomValue();
    static String UpdatedName = "UpdatedName" + TestUtils.getRandomValue();
    static String type = "Testing";
    static String address = "home";

    static String address2 = "wembley";
    static String city = "diu";

    static String state = "diu";
    static String zip = "362570";

    static List<String> services;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
    }
//cre
    @Test
    public void test001() {
        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName(name);
        storesPojo.setType(type);
        storesPojo.setAddress(address);
        storesPojo.setAddress2(address2);
        storesPojo.setCity(city);
        storesPojo.setState(state);
        storesPojo.setZip(zip);

        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .body(storesPojo)
                .post("/stores");
        response.then().log().all().statusCode(201);
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        storeId = jsonPath.getInt("id");

    }
    @Test
    public void test002() {
        System.out.println("=============" + storeId);
        response = given()
                .when()
                .get("/stores/" + storeId)
                .then().statusCode(200);
    }

    @Test
    public void test003() {

        StoresPojo storePojo = new StoresPojo();
        storePojo.setName(UpdatedName);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);

        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .body(storePojo)
                .patch("/stores/" + storeId);
        response.then().log().all().statusCode(200);

    }

    @Test
    public void test004() {

        given()
                .pathParam("id", storeId)
                .when()
                .delete("/stores/{id}")
                .then()
                .statusCode(200);
    }






}