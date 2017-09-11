
import Utils.ResponseParser;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ApiTest {

    private static Response response;
    private static String homeWorldUrl,
            lukeSkywalkerUrl = "https://swapi.co/api/people/1/";

    @Test
    public void findPerson() {

        response = given()
                .contentType(JSON)
                .when()
                .get(lukeSkywalkerUrl)
                .then()
                .statusCode(200)
                .extract()
                .response();

        assertThat(response.path("name").toString()).isEqualTo("Luke Skywalker");
    }

    @Test(dependsOnMethods = "findPerson")
    public void getPlanetName() {

        homeWorldUrl = response.path("homeworld");
        System.out.println(homeWorldUrl);

        response = given()
                .contentType(JSON)
                .when()
                .get(homeWorldUrl)
                .then()
                .statusCode(200)
                .extract()
                .response();

        /**There is a bug:
         * Wrong type of output date
         * Recomedation: change returning type from String to int
         */

        assertThat(response.path("name").toString()).isEqualTo("Tatooine");
        assertThat(response.path("population").toString()).isEqualTo("200000");
    }

    @Test(dependsOnMethods = "getPlanetName")
    public void getFilm(){

        String filmUrl = response.path("films[0]");

        response = given()
                .contentType(JSON)
                .when()
                .get(filmUrl)
                .then()
                .statusCode(200)
                .extract()
                .response();


        assertThat(response.path("title").toString()).isEqualTo("Attack of the Clones");
    }

    @Test(dependsOnMethods = "getFilm", priority = 2)
    public void isPlanetInFilm(){

        ResponseParser parser = new ResponseParser();
        List<String> planetsList = response.jsonPath().getList("planets");
        boolean actualCharacter = parser.isElementInArray(planetsList, homeWorldUrl);

        assertThat(actualCharacter).isEqualTo(true);
    }

    @Test(dependsOnMethods = "getFilm", priority = 1)
    public void isCharacterInFilm(){

        ResponseParser parser = new ResponseParser();
        List<String> charactersList = response.jsonPath().getList("characters");
        boolean actualCharacter = parser.isElementInArray(charactersList, lukeSkywalkerUrl);
        System.out.println(lukeSkywalkerUrl);

        assertThat(actualCharacter).isEqualTo(false);
        /**
         * Bug:
         * Luke Skywalker api url in not present in the film
         */
    }
}

