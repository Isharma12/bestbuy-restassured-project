package testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/Stores")
                .then().statusCode(200);
    }

    //Extract the limit
    @Test
    public void extractTheLimit() {
        int limit = response.extract().path("limit");
        System.out.println("-----------------StartingTest-------------");
        System.out.println("The value of limit is: " + limit);
        System.out.println("--------------------EndingTest----------------------");
    }

    //Extract the total
    @Test
    public void extractTheTotal() {
        int total = response.extract().path("total");
        System.out.println("--------------------StaringTest---------------------");
        System.out.println("The total value is: " + total);
        System.out.println("-------------------EndingTest-----------------------");
    }

    //Extract the name of 5th store
    @Test
    public void extractTheName() {
        String name = response.extract().path("data[5].name");
        System.out.println("--------------------StartingTest----------------------");
        System.out.println("The name of 5th store is : " + name);
        System.out.println("----------------------------EndingTest-----------------");
    }

    //Extract the names of all the store
    @Test
    public void extractAllTheStoresNames() {
        List<String> storesNames = response.extract().path("data.services.name");
        System.out.println("----------------------StartingTest--------------");
        System.out.println("The name of all the stores are : " + storesNames);
        System.out.println("-----------------------EndingTest-------------------");
    }

    //Extract the storeId of all the store
    @Test
    public void extractAllTheStoresIds() {
        List<String> storeIds = response.extract().path("data.services.storeservices.storeId");
        System.out.println("---------------StartingTest---------------");
        System.out.println("All the storeIds are: " + storeIds);
        System.out.println("------------------EndingTest--------------------");
    }

    //Print the size of the data list
    @Test
    public void printTheSizeOfTheDataList() {
        List<Integer> objectData = response.extract().path("data.length");
        int sizeOfData = objectData.size();
        System.out.println("----------------------StartingTest---------------");
        System.out.println("The Size of the dataList is: " + sizeOfData);
        System.out.println("--------------------EndingTest---------------------");
    }

    //Get all the value of the store where store name = St Cloud
    @Test
    public void getAllTheValueOfStoreName() {
        List<HashMap<String, Object>> StCloudStoreData = response.extract().path("data.findAll{it.name == 'St Cloud'}");
        System.out.println("-------------------StartingTest----------------");
        System.out.println("Get all the value of the store where store name = St Cloud is :" + StCloudStoreData);
        System.out.println("-----------------------EndingTest---------------");
    }

    //Get the address of the store where store name = Rochester
    @Test
    public void getAddressOfStoreRochester() {
        List<String> address = response.extract().path("data.findAll{it.name == 'Rochester'}.address");
        System.out.println("-------------------StartingTest--------------------");
        System.out.println("Get the address the store where store name = Rochester : " + address);
        System.out.println("---------------------EndingTest------------------------");
    }
    //Get all the services of 8th store
    @Test
    public void getAllTheServices(){
        List<String> allServices = response.extract().path("data[7].services");
        System.out.println("----------------------------StartingTest----------------");
        System.out.println("All the services of 8th store is : " + allServices);
        System.out.println("------------------------EndingTest------------------");
    }
    //Get storeservices of the store where service name = Windows Store
    @Test
    public void getStoreServices(){
        List<HashMap<String,?>> storeServicesWindowsStore = response.extract().path("data.services*.findAll{it.name == 'Windows Store'}.storeservices");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The storeservices of the store where service name = Windows Store is : " +storeServicesWindowsStore);
        System.out.println("------------------End of Test---------------------------");
    }

    //11. Get all the storeId of all the store
    @Test
    public void testGetAllTheStoreId() {
        List<Integer> allStoreId = response.extract().path("data.services.storeservices.storeId");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Get all storeId of all the store is : " + allStoreId);
        System.out.println("------------------End of Test---------------------------");
    }

    //12. Get id of all the store
    @Test
    public void testGetId() {
        List<Integer> storeId = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The storeId of all the store is : " + storeId);
        System.out.println("------------------End of Test---------------------------");
    }

    //13. Find the store names Where state = ND
    @Test
    public void testFindTheStoreName() {
        List<String> state = response.extract().path("data.findAll{it.state == 'ND'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The store names Where state = ND is : " + state);
        System.out.println("------------------End of Test---------------------------");
    }


    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void testFindTheTotalNumber() {
        List<Object> services = response.extract().path("data.findAll{it.name == 'Rochester'}.services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Total number of services for the store where store name = Rochester is : " + services.size());
        System.out.println("------------------End of Test---------------------------");
    }


    //15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void testFindTheCreatedAtAllServices() {
        List<String> allServices = response.extract().path("data.services*.findAll{it.name == 'Windows Store'}.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The createdAt for all services whose name = Windows Store : " + allServices);
        System.out.println("------------------End of Test---------------------------");
    }

    // 16. Find the name of all services Where store name = “Fargo”
    @Test
    public void testFindNameAllServiceStoreNameFargo() {
        List<Object> servicesName = response.extract().path("data.findAll{it.name == 'Fargo'}.services.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of all services Where store name = “Fargo” is : " + servicesName);
        System.out.println("------------------End of Test---------------------------");
    }

    // 17. Find the zip of all the store
    @Test
    public void testFindTheZip() {
        List<Object> zipOfAllStore = response.extract().path("data.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The  zip of all the store = zip is : " + zipOfAllStore);
        System.out.println("------------------End of Test---------------------------");
    }


    //18. Find the zip of store name = Roseville
    @Test
    public void testZipStoreName() {
        List<String> zipOfStore = response.extract().path("data.findAll{it.name == 'Roseville'}.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The  zip of store name = Roseville is : " + zipOfStore);
        System.out.println("------------------End of Test---------------------------");
    }

    //19. Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void testFindStoreServicesDetails() {
        List<HashMap<String,?>> storeServices = response.extract().path("data.services*.findAll{it.name == 'Magnolia Home Theater'}.storeservices");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The storeservices details of the service name = Magnolia Home Theater is : " + storeServices);
        System.out.println("------------------End of Test---------------------------");
    }
    //20. Find the lat of all the stores
    @Test
    public void testFindTheLateAllStore() {
        List<Integer> latOfAllStore = response.extract().path("data.lat");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The lat of all the stores is : " + latOfAllStore);
        System.out.println("------------------End of Test---------------------------");

    }


}