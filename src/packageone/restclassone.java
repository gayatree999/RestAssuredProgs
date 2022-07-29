package packageone;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
//import junit.framework.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.ReuseableMethods;
import files.data;
public class restclassone {

		//@SuppressWarnings("deprecation")
		public static void main(String[] args) {
			
			//validate if add place API is working as expected

			// given- All input details
			// when- Submit the API -resource , http method
			// Then - Validate the response

			RestAssured.baseURI = "https://rahulshettyacademy.com";
			String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
					.body(data.AddPlace())
					.when().post("maps/api/place/add/json").then().assertThat().statusCode(200)
					.body("scope", equalTo("APP")).header("Server", "Apache/2.4.18 (Ubuntu)").extract().asString();
			System.out.println(response);
			
			JsonPath js = new JsonPath(response);
			String placeId = js.getString("place_id");
			System.out.println(placeId);
			
			String newAddress ="70 winter walk, USA3";
			//Code for PUT Api - update the address
			given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
			.body("{\r\n"
					+ "\"place_id\":\""+placeId+"\",\r\n"
					+ "\"address\":\""+newAddress+"\",\r\n"
					+ "\"key\":\"qaclick123\"\r\n"
					+ "}")
			.when().put("maps/api/place/update/json")
			.then().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
			
			//Get place
			String getPlaceResponse= given().queryParam("key", "qaclick123").queryParam("place_id", placeId)
			.when().get("maps/api/place/get/json")
			.then().assertThat().log().all().statusCode(200).extract().response().asString();
			
			JsonPath js1=ReuseableMethods.rawToJson(getPlaceResponse);
			String actualAdress = js1.getString("address");
			System.out.println(actualAdress);
			
			Assert.assertEquals(actualAdress, newAddress);
			//Cucumber Juint, TestNG
			
			
			
			
			
			
			
		}
	}
