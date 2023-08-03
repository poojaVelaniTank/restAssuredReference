package restAssuredReferencee;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;
import org.testng.Assert;
public class getPrac {
		public static void main(String[] args) {
		//Step1:Declare BaseURI
		String baseURI="https://reqres.in";
		//Configure API
		RestAssured.baseURI=baseURI;
		//Configure APi
		int statusCode=given().header("Content-Type","application/json").when().get("/api/users?page=2").
				then().extract().statusCode();
		String responsebody=given().header("Content-Type","application/json").when().get("/api/users?page=2")
				.then().extract().response().asString();
		System.out.println(statusCode);
		System.out.println(responsebody);
		
		//declare const results
		int id[] = {7,8,9,10,11,12};
		String email[] = {"michael.lawson@reqres.in","lindsay.ferguson@reqres.in","tobias.funke@reqres.in",
				"byron.fields@reqres.in","george.edwards@reqres.in","rachel.howell@reqres.in"};
		//validate
		JsonPath jsp=new JsonPath(responsebody);
		int count=jsp.getList("data").size();
		System.out.println(count);
		
		for(int i = 0; i<count ; i++)
		{
			//fetch exp results
			int exp_id=id[i];
			String exp_email=email[i];
			String res_email=jsp.getString("data["+i+"].email");
			String res_id=jsp.getString("data["+i+"].id");
			int res_int_id=Integer.parseInt(res_id);
			
			Assert.assertEquals(res_email, exp_email, "email at index" +i);
			Assert.assertEquals(res_int_id, exp_id,"id at index" +i);
		}
		
	}
		}
		
		
		
		
		 
		
	

