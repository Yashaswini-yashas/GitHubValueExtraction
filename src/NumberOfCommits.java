import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class NumberOfCommits {
	Properties prop = new Properties();
	@BeforeTest
	public void getData() throws IOException
	{
		
		FileInputStream fis = new FileInputStream("C:\\Users\\yashass\\eclipse-workspace\\UpdatedGitHub\\src\\files\\env.properties");
		prop.load(fis);
		prop.get("USERNAME");
		prop.get("PASSWORD");
	}
	@Test
	public void noOfCommits() {
//public static void main(String args[]) {
	RestAssured.baseURI="https://api.github.com";
	Response res = given().param("username","USERNAME").
			param("password","PASSWORD").
			when().
		    get("/repos/Yashaswini-yashas/ValueExtractionFromJIRA/stats/contributors").
		    then().assertThat().
		    and().
		    extract().response();
	     String response=res.asString();	
	     System.out.println(response);
	     JsonPath js = new JsonPath(response);
	     String no_of_commits = js.getString("weeks");
	     System.out.println(no_of_commits);
	     String committer = js.getString("author.login");
	     System.out.println(committer);
	     
}
}
