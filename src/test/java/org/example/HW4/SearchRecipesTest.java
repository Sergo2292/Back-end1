package org.example.HW4;

import io.restassured.builder.RequestSpecBuilder;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SearchRecipesTest extends AbstractTest{
    @BeforeEach
    void requestTest(){
        requestSpecification = new RequestSpecBuilder()
                .addQueryParam("query", "light")
                .addQueryParam("cuisine", "European")
                .addQueryParam("excludeCuisine", "African")
                .addQueryParam("diet", "Vegetarian")
                .addQueryParam("intolerances", "Sesame")
                .addQueryParam("type", "salad")
                .addQueryParam("sort", "calories")
                .addQueryParam("sortDirection", "asc")
                .addQueryParam("maxCalories", 200)
                .addQueryParam("offset", 3)
                .addQueryParam("number", 3)
                .build();
    }
    @Test
    @DisplayName("Тест-кейс №1")
    void searchRecipesTest1() {
        given().spec(requestSpecification)
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .spec(responseSpecification);
    }
    @Test
    @DisplayName("Тест-кейс №2")
    @Description("Offset = 3")
    void searchRecipesTest2() {
        SearchRecipesResponse response = given().spec(requestSpecification)
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .extract()
                .response()
                .body()
                .as(SearchRecipesResponse.class);
        assertThat(response.getOffset(), equalTo(3));
    }
    @Test
    @DisplayName("Тест-кейс №3")
    @Description("Number = 3")
    void searchRecipesTest3() {
        SearchRecipesResponse response = given().spec(requestSpecification)
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .extract()
                .response()
                .body()
                .as(SearchRecipesResponse.class);
        assertThat(response.getNumber(), equalTo(3));
    }
    @Test
    @DisplayName("Тест-кейс №4")
    @Description("Count of results = number")
    void searchRecipesTest4() {
        SearchRecipesResponse response = given().spec(requestSpecification)
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .extract()
                .response()
                .body()
                .as(SearchRecipesResponse.class);
        assertThat(response.getResults().size(), equalTo(response.getNumber()));
    }
    @Test
    @DisplayName("Тест-кейс №5")
    @Description("Count of result object fields = 4")
    void searchRecipesTest6() {
        SearchRecipesResponse response = given().spec(requestSpecification)
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .extract()
                .response()
                .body()
                .as(SearchRecipesResponse.class);
        assertThat(response.getClass().getDeclaredFields().length, equalTo(4));
    }
}
