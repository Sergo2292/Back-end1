package org.example.HW3;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class MealPlan extends AbstractTest{
    @Test
    void addMealTest() {
        //add
        String resposeID = given()
                .queryParam("apikey", getApiKey())
                .pathParam("username", getUsername())
                .queryParam("hash", getHash())
                .body("{\n"
                        + " \"item\": \"1 package baking powder\",\n"
                        + " \"aisle\": \"Baking\",\n"
                        + " \"parse\": true,\n"
                        + "}")
                .when()
                .post(getBaseUrl() + "mealplanner/{username}/shopping-list/items")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("id")
                .toString();
        int id = Integer.parseInt(resposeID);
        //delete
        given()
                .queryParam("apiKey", getApiKey())
                .pathParam("username", getUsername())
                .pathParam("id", id)
                .queryParam("hash", getHash())
                .when()
                .delete(getBaseUrl() + "mealplanner/{username}/shopping-list/items/{id}")
                .then()
                .statusCode(200);
    }
}

