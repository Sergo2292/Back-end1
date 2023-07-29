package org.example.HW3;


import io.restassured.path.json.JsonPath;
import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;


public class GetTest extends AbstractTest {
    @Test
    @DisplayName("Тест-кейс №1")
    @Description("Status code is 200")
    void getRecipesTest() {
        given()
                .queryParam("apikey", getApiKey())
                .queryParam("cuisine", "European")
                .queryParam("type", "salad")
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Тест-кейс №2")
    @Description("Status code name has HTTP/1.1 200 OK")
    void getStatusRecipesTest () {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("intolerance", "Soy")
                .queryParam("sort", "iron")
                .queryParam("sortDirection", "asc")
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .statusLine("HTTP/1.1 200 OK");
    }

    @Test
    @DisplayName("Тест-кейс №3")
    @Description("Response time is less than 300ms")
    void getTimeIsLessTest () {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("includeIngredients", "cheese")
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .time(lessThan(3000L));
    }

    @Test
    @DisplayName("Тест-кейс №4")
    @Description("Content-Type is present")
    void getContentTypeTest () {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("excludeCuisine", "African")
                .queryParam("diet", "Vegetarian")
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .contentType("application/json");
    }


    @Test
    @DisplayName("Тест-кейс №5")
    @Description("Offset = 3")
    void getMaxCaloriesRecipesTest() {
        JsonPath response = given()
                .queryParam("apiKey", getApiKey())
                .queryParam("query", "light")
                .queryParam("maxCalories", 200)
                .queryParam("sort", "calories")
                .queryParam("sortDirection", "asc")
                .queryParam("offset", "3")
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .body()
                .jsonPath();
        assertThat(response.get("offset"), equalTo(3));
    }


}

