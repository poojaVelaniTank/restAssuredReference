package restAssuredReferencee;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

import java.time.LocalDateTime;

import org.testng.Assert;
public class praccput {

	public static void main(String[] args) {
		String baseURI="https://petstore.swagger.io";
		String requestbody="{\r\n"
				+ "  \"id\": 1,\r\n"
				+ "  \"category\": {\r\n"
				+ "    \"id\": 2,\r\n"
				+ "    \"name\": \"testng\"\r\n"
				+ "  },\r\n"
				+ "  \"name\": \"doggie\",\r\n"
				+ "  \"photoUrls\": [\r\n"
				+ "    \"string\"\r\n"
				+ "  ],\r\n"
				+ "  \"tags\": [\r\n"
				+ "    {\r\n"
				+ "      \"id\": 0,\r\n"
				+ "      \"name\": \"string\"\r\n"
				+ "    }\r\n"
				+ "  ],\r\n"
				+ "  \"status\": \"available\"\r\n"
				+ "}";
		System.out.println(requestbody);
		RestAssured.baseURI=baseURI;
		//Configure
		int statusCode=given().header("Content-Type","application/json").body(requestbody).when().
				put("v2/pet").then().extract().statusCode();
		String responsebody=given().header("Content-Type","application/json").body(requestbody).when().
				put("v2/pet").then().extract().response().asString();
		System.out.println(statusCode);
		System.out.println(responsebody);
		
		//date
		LocalDateTime Date=LocalDateTime.now();
		String expdate=Date.toString().substring(0,13);
		
		//Parse requestbody
		JsonPath jspreq= new JsonPath(requestbody);
		String req_name=jspreq.getString("name");
		String req_id=jspreq.getString("id");
		
		//Parse responsebody
		JsonPath jsp= new JsonPath(responsebody);
		String res_name=jsp.getString("name");
		String res_id=jsp.getString("id");
		
		//validate
		Assert.assertEquals(res_name,req_name);
		Assert.assertEquals(res_id,	req_id);
	}
}
