import CredentialsData.CredentialsProvider;
import CredentialsData.CredentialsRequestDataObject;
import CredentialsData.FailedLoginResponseDataObject;
import CredentialsData.FailedRegisterResponseDataObject;
import OtherDataObjects.FetchResponseDataObject;
import OtherDataObjects.ResourceDataObject;
import UsersData.CreateUserResponseDataObject;
import UsersData.UpdateUserResponseDataObject;
import UsersData.UserRequestDataObject;
import UsersData.UsersProvider;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.testng.annotations.Test;

public class UserTest {

    @Test(dataProvider = "usersProvider", dataProviderClass = UsersProvider.class)
    public void createUser(UserRequestDataObject user) {

        CreateUserResponseDataObject response = RestAssured.given().body(user).when().post("https://reqres.in/api/users").then().statusCode(201).extract().as(CreateUserResponseDataObject.class);

        assert (response.id != null);
        assert (response.createdAt != null);

    }


    @Test(dataProvider = "usersProvider", dataProviderClass = UsersProvider.class)
    public void updateUser(UserRequestDataObject user) {

        UpdateUserResponseDataObject response = RestAssured.given().body(user).when().put("https://reqres.in/api/users/2").then().statusCode(200).extract().as(UpdateUserResponseDataObject.class);

        assert (response.updatedAt != null);

    }


    @Test(dataProvider = "credentialsProvider", dataProviderClass = CredentialsProvider.class)
    public void registerUser(CredentialsRequestDataObject credentials) {

        FailedRegisterResponseDataObject response = RestAssured.given().body(credentials).when().post("https://reqres.in/api/register").then().statusCode(400).extract().as(FailedRegisterResponseDataObject.class);

        assert (response.error.equalsIgnoreCase("Missing email or username"));

    }


    @Test(dataProvider = "credentialsProvider", dataProviderClass = CredentialsProvider.class)
    public void loginUser(CredentialsRequestDataObject credentials) {

        FailedLoginResponseDataObject response = RestAssured.given().body(credentials).when().post("https://reqres.in/api/login").then().statusCode(400).extract().as(FailedLoginResponseDataObject.class);

        assert (response.error.equalsIgnoreCase("Missing email or username"));

    }

    @Test
    public void deleteUser() {

      RestAssured.given().when().delete("https://reqres.in/api/users/2").then().assertThat().statusCode(204);

    }

    @Test
    public void getUsers() {

        Response response = RestAssured.given().when().get("https://reqres.in/api/users?page=2").then().statusCode(200).extract().response();

        String usersData = response.body().asString();

        String [] userDataArray = usersData.split("data");

        assert (userDataArray[1].contains("id"));

    }

    @Test
    public void getUser() {

       Response response = RestAssured.given().when().get("https://reqres.in/api/users/2").then().statusCode(200).extract().response();

       String userData = response.body().asString();

       String [] userDataArray = userData.split("},");

       assert (userDataArray[0].contains("Janet"));

    }

    @Test
    public void userNotFound() {

        assert (RestAssured.given().when().get("https://reqres.in/api/users/23").then().statusCode(404).extract().body() != null);

    }

    @Test
    public void getResource() {

        ResourceDataObject dataObject = RestAssured.given().when().get("https://reqres.in/api/unknown/2").then().extract().response().body().as(ResourceDataObject.class);

        assert (dataObject.data.containsValue("2"));

    }

    @Test
    public void fetchRequestTest() {

        Response response = RestAssured.given().when().get("https://jsonplaceholder.typicode.com/todos/1").then().statusCode(200).extract().response();

        FetchResponseDataObject fetchResponse = response.as(FetchResponseDataObject.class);

        System.out.println(response.body().asString());

        assert (fetchResponse.userId.equalsIgnoreCase("1"));
        assert (fetchResponse.id.equalsIgnoreCase("1"));
        assert (fetchResponse.title.equalsIgnoreCase("delectus aut autem"));
        assert (fetchResponse.completed.equalsIgnoreCase("false"));

    }


}
