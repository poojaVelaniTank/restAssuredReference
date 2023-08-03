package restAssuredReferencee;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;
import org.testng.Assert;
public class sample {

	public static void main(String[] args) {
		String baseURL="https://petstore.swagger.io";
		String requestbody="{\r\n"
				+ "  \"id\": 1,\r\n"
				+ "  \"category\": {\r\n"
				+ "    \"id\": 0,\r\n"
				+ "    \"name\": \"string\"\r\n"
				+ "  },\r\n"
				+ "  \"name\": \"testing\",\r\n"
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
		RestAssured.baseURI=baseURL;
		//COnfigure
		
		String responsebody=given().header("Content-Type","application/json").body(requestbody
				).when().post("v2/pet").then().extract().response().asString();
		System.out.println(responsebody);
		
		//Parse 
		JsonPath jsp = new JsonPath(responsebody);
		String res_name=jsp.getString("string");
		int res_id=jsp.getList("data.id").size();
		int res_int_id=Integer.parseInt("res_id");
		String res_status=jsp.getString("res_status");
		
	//Validate
		Assert.assertEquals(res_name, "string");
		Assert.assertNotNull(res_id,"0");
		Assert.assertEquals(res_status, "available");
	 
		}

}
