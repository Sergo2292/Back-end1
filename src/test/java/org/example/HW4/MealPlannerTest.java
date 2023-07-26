package org.example.HW4;

import io.restassured.builder.RequestSpecBuilder;
import jdk.jfr.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MealPlannerTest extends AbstractTest {

    private static int id;
    private static String bodyItem = "1 package baking powder";
    private static String bodyAisle = "Baking";

    @BeforeEach
    void requestTest() {
        requestSpecification = new RequestSpecBuilder()
                .addPathParams("username", getUsername())
                .addQueryParam("hash", getHash())
                .build();
    }
    @Test
    @DisplayName("Тест-кейс №1")
    @Description("Add to Shopping List & Get Shopping List")
    void mealTest() {
        AddMealResponse addResponse = given().spec(requestSpecification)
                .body("{\n"
                        + " \"item\": " + "\"" + bodyItem + "\",\n"
                        + " \"aisle\": " + "\"" + bodyAisle + "\",\n"
                        + " \"parse\": true \n"
                        + "}")
                .when()
                .post(getBaseUrl() + "mealplanner/{username}/shopping-list/items")
                .then()
                .spec(responseSpecification)
                .extract()
                .response()
                .body()
                .as(AddMealResponse.class);
        id = addResponse.getId();
        assertThat(bodyItem, containsString(addResponse.getName()));
        assertThat(addResponse.getAisle(), equalTo(bodyAisle));

        GetMealResponse getResponse = given().spec(requestSpecification)
                .when()
                .get(getBaseUrl() + "mealplanner/{username}/shopping-list")
                .then()
                .spec(responseSpecification)
                .extract()
                .response()
                .body()
                .as(GetMealResponse.class);
        assertThat(getResponse.getAisles().size(), equalTo(0));
        assertThat(bodyItem, containsString(getResponse.getAisles().get(0).getItems().get(0).getName()));
        assertThat(getResponse.getAisles().get(0).getAisle(), equalTo(bodyAisle));
    }
    @AfterEach
    @Description("Delete from Shopping List & Get Shopping List")
    void delMealTest() {
        DeleteMealResponse delResponse = given().spec(requestSpecification)
                .pathParam("id", id)
                .when()
                .delete(getBaseUrl() + "mealplanner/{username}/shopping-list/items/{id}")
                .then()
                .spec(responseSpecification)
                .extract()
                .response()
                .body()
                .as(DeleteMealResponse.class);
        assertThat(delResponse.getStatus(), containsString("success"));

        GetMealResponse getResponse = given().spec(requestSpecification)
                .when()
                .get(getBaseUrl() + "mealplanner/{username}/shopping-list")
                .then()
                .spec(responseSpecification)
                .extract()
                .response()
                .body()
                .as(GetMealResponse.class);
        assertThat(getResponse.getAisles().size(), equalTo(0));

    }
}

