import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiServiceTest {

    @Test
    public void brewerydbTest()
    {
        Response response = RestAssured.given().baseUri("https://api.openbrewerydb.org/breweries/autocomplete").and().queryParam("query", "lagunitas").get();
        Cerveceria[] cerves = response.getBody().as(Cerveceria[].class);
        for(Cerveceria c : cerves) {
            if (c.getName().equals("Lagunitas Brewing Co")) {
                Cerveceria finalResult = RestAssured.given().baseUri("https://api.openbrewerydb.org/breweries/" + c.getId()).get().getBody().as(Cerveceria.class);
                if ( finalResult.getState().equals("California")) {
                    Assert.assertEquals(finalResult.getId(), "761");
                    Assert.assertEquals(finalResult.getName(), "Lagunitas Brewing Co");
                    Assert.assertEquals(finalResult.getStreet(), "1280 N McDowell Blvd");
                    Assert.assertEquals(finalResult.getPhone(), "7077694495");
                }
            }
        }
    }
}

