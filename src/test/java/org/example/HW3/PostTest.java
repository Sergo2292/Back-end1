package org.example.HW3;

import io.restassured.path.json.JsonPath;
import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class PostTest extends AbstractTest {

    @Test
    @DisplayName("Тест-кейс №1")
    @Description("Status code is 200")
    void getCuisineTest() {
        given()
                .queryParam("apikey", getApiKey())
                .queryParam("language", "de")
                .contentType("application/x-www-form-unlencoded")
                .formParams("title", "Pork roast with green beans")
                .formParams("ingredientList", "3 oz pork shoulder")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Тест-кейс №2")
    @Description("Status code name has HTTP/1.1 200 OK")
    void getClassifyTest() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Pork roast with green beans")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .statusLine("HTTP/1.1 200 OK");
    }

    @Test
    @DisplayName("Тест-кейс №3")
    @Description("Response time is more than 200ms")
    void getClassifyCuisineTest3() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("ingredientList", "avocado")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .time(greaterThan(200L));
    }

    @Test
    @DisplayName("Тест-кейс №4")
    @Description("Content-Type is present")
    void getClassifyCuisineTest4() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("ingredientList", "3 oz pork shoulder")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .contentType("application/json");
    }

    @Test
    @DisplayName("Тест-кейс №5")
    @Description("Cuisine = Korean")
    void getClassifyCuisineTest5() {
        JsonPath response = given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Kimchi")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .body()
                .jsonPath();
        assertThat(response.get("cuisine"), equalTo("Korean"));
    }
}