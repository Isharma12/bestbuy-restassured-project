package testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class StoresAssertionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }
   //Verify if the total is equal to 1559
    @Test
    public void verifyTheTotal(){
        response.body("total", equalTo(1559));
    }

   //Verify if the stores of limit is equal to 10
    @Test
    public void  verifyTheStoreOfLimit(){
        response.body("limit", equalTo(10));

    }
    //Check the single ‘Name’ in the Array list (Inver Grove Heights)
    @Test
    public void checkTheSingleNameInTheArrayList(){
    response.body("data.name", hasItem("Inver Grove Heights"));
    }

    //Check the multiple ‘Names’ in the ArrayList (Burnsville", "Maplewood", "Oakdale)

    @Test
    public void checkTheMultiPleNamesInTheArrayList(){
   response.body("data.name",hasItems("Burnsville", "Maplewood", "Oakdale"));
    }

   //Verify the storied=10 inside storeservices of the third store of second services
    @Test
    public void verifyTheStoreIdInsideStoreService(){
    response.body("data[2].services[2].storeservices.storeId", equalTo(10));
    }

    //Check hash map values ‘createdAt’ inside storeservices map where store name = Burnsville
    @Test
    public void checkHahMapValuesInCreatedAtInsideStoreServices(){
        response.body("data.name", hasItem("Burnsville"))
            .body("data[1].services[1].storeservices", hasKey("createdAt"));
    }

   //Verify the state = MN of forth store
    @Test
    public void verifyTheState(){
        response.body("data[0].state", equalTo("MN"));

    }
    //Verify the store name = Rochester of 9th store
    @Test
    public void verifyTheStoreName(){
        response.body("data[6].name", equalTo("Rochester"));

    }
    //Verify the storeId = 11 for the 6th store
    @Test
    public void verifyTheStoreId(){
        response.body("data[3].id", equalTo(11));

    }
    //Verify the serviceId = 4 for the 7th store of forth service
    @Test
    public void verifyTheServiceId(){

response.body("data[4].services[3].storeservices.serviceId", equalTo(4));
    }
}
