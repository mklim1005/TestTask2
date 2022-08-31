import io.restassured.RestAssured;
import io.restassured.response.Response;
import objects.WeatherResponse;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertTrue;

public class GetWeatherDataTest {

    public String key = "X75ZZ29F8AMRZCBQJH9YHSGUU";

    @Before
    public void setUp() {

        RestAssured.baseURI = "https://weather.visualcrossing.com/";
    }

    @Test
    public void getWeatherShouldReturnWeatherData() {
        Response response = given()
                .queryParam("unitGroup", "metric")
                .queryParam("key", key)
                .queryParam("contentType", "json")
                .get("/VisualCrossingWebServices/rest/services/timeline/Tallinn");

        WeatherResponse weatherResponse = response.body().as(WeatherResponse.class);

        response.then().assertThat().statusCode(200)
                .and()
                .assertThat().body("address", equalTo("Tallinn"));
        assertTrue(weatherResponse.days.size() > 0);
        assertTrue(weatherResponse.days.get(0).getTemp() != 0);
    }
}
