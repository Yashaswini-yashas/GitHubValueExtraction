import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CommitterNameDateMessage {
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
	public void committerNameDateMessage() {
	//public static void main(String args[]) {
		RestAssured.baseURI="https://api.github.com";
		Response res = given().param("username","USERNAME").
				param("password","PASSWORD").
				when().
			    get("/repos/Yashaswini-yashas/petclinic/commits").
			    then().assertThat().
			    and().
			    extract().response();
		     String response=res.asString();	
		     System.out.println(response);
		     JsonPath js = new JsonPath(response);
		     String committer = js.getString("commit.committer.name");
		     String commit_message = js.getString("commit.message");
		     String commit_time = js.getString("commit.committer.date");
		     System.out.println(committer+" : "+"\n"+commit_message+" : "+"\n"+commit_time);
}}
