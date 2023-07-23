package org.example.HW3;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class LogTest extends AbstractTest {
    @BeforeAll
    static void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void getResponseLogSearchRecipesTest() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("query", "light")
                .queryParam("cuisine", "European")
                .queryParam("excludeCuisine", "African")
                .queryParam("diet", "Vegetarian")
                .queryParam("intolerances", "Sesame")
                .queryParam("type", "salad")
                .queryParam("sort", "calories")
                .queryParam("sortDirection", "asc")
                .queryParam("maxCalories", 200)
                .queryParam("offset", 3)
                .queryParam("number", 3)
                .log().all()
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .prettyPeek();
    }

    @Test
    void getResponseLogClassifyCuisineTest() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("language", "en")
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Pork roast with green beans")
                .formParam("ingredientList", "3 oz pork shoulder")
                .log().all()
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .prettyPeek();
    }

    @Test
    void getResponseLogConnectUserTest() {
        given()
                .queryParam("apiKey", getApiKey())
                .body("{\n"
                        + " \"username\": \"SerGB\",\n"
                        + " \"email\": \"muhamet77737@gmail.com\",\n"
                        + "}")
                .log().all()
                .when()
                .post(getBaseUrl() + "users/connect")
                .prettyPeek();
    }
}
