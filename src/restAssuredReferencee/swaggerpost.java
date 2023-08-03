package restAssuredReferencee;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;
import java.time.LocalDateTime;
import org.hamcrest.core.SubstringMatcher;
import org.testng.Assert;
public class swaggerpost {

	public static void main(String[] args) {
		//Declare baseURL and requestbody
		String baseURI="https://petstore.swagger.io";
		String requestbody= "{\r\n"
				+ "  \"id\": 0,\r\n"
				+ "  \"category\": {\r\n"
				+ "    \"id\": 1,\r\n"
				+ "    \"name\": \"pooja\"\r\n"
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
		//COnfigure API
		int statuscode= given().header("Content-Type","application/json").body(requestbody).when().
	    post("/v2/pet").then().extract().statusCode();
		String responsebody=given().header("Content-type","application/json").body(requestbody).when()
		.post("/v2/pet").then().extract().response().asString();
		System.out.println(statuscode);
		System.out.println(responsebody);
		
		//generate date
        LocalDateTime Date= LocalDateTime.now();
        String exp_date = Date.toString().substring(0,13);
        
		//Parse responsebody
		JsonPath jsp = new JsonPath(responsebody);
		String res_name = jsp.getString("name");
		String res_job = jsp.getString("job");
		String res_id= jsp.getString("id");
		String res_createdAt = jsp.getString("createdAt");
	
		JsonPath req= new JsonPath(requestbody);
		String req_name= req.getString("name");
		String req_job = req.getString("job");
		
		//validate
		Assert.assertEquals(res_name, req_name);
		Assert.assertEquals(res_job,  req_job);
		Assert.assertNotNull("res_id");
		Assert.assertEquals(res_createdAt, exp_date);
		}
	}
