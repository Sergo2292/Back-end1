package org.example.HW4;

import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class ClassifyCuisineTest extends AbstractTest {
    @Test
    @DisplayName("Тест-кейс №1")
    @Description("")
    void classifyCuisineTest1() {
        given().spec(requestSpecification)
                .queryParam("language", "en")
                .formParams("title", "Pork roast with green beans")
                .formParam("ingredientList", "3 oz pork shoulder")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .spec(responseSpecification);
    }

    @Test
    @DisplayName("Тест-кейс №2")
    @Description("Field Cuisine contains Korean")
    void ClassifyCuisineTest2() {
        ClassifyCuisineResponse response = given().spec(requestSpecification)
                .formParam("title", "Kimchi")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .extract()
                .response()
                .body()
                .as(ClassifyCuisineResponse.class);
        assertThat(response.getCuisine(), containsString("Korean"));
    }

    @Test
    @DisplayName("Тест-кейс №3")
    @Description("Field Confidence = 0.5")
    void ClassifyCuisineTest3() {
        ClassifyCuisineResponse response = given().spec(requestSpecification)
                .formParam("title", "Easy Chicken Tikka Masala")
                .when()
                .post(getBaseUrl() + "recipes/cuisine").prettyPeek()
                .then()
                .extract()
                .response()
                .body()
                .as(ClassifyCuisineResponse.class);
        assertThat(response.getConfidence(), equalTo(0.85));
    }

    @Test
    @DisplayName("Тест-кейс №4")
    @Description("Count of Cuisines values = 2")
    void classifyCuisineTest4() {
        ClassifyCuisineResponse response = given().spec(requestSpecification)
                .formParam("title", "Korean Bibimbab (Rice w Vegetables & Beef)")
                .when()
                .post(getBaseUrl() + "recipes/cuisine").prettyPeek()
                .then()
                .extract()
                .response()
                .body()
                .as(ClassifyCuisineResponse.class);
        assertThat(response.getCuisines().size(), equalTo(2));
    }

    @Test
    @DisplayName("Тест-кейс №5")
    @Description("Result object contains correct field names")
    void classifyCuisineTest5() {
        ClassifyCuisineResponse response = given().spec(requestSpecification)
                .formParam("title", "Curry Beef Over Rice Noodles")
                .when()
                .post(getBaseUrl() + "recipes/cuisine").prettyPeek()
                .then()
                .extract()
                .response()
                .body()
                .as(ClassifyCuisineResponse.class);
        String[] arrFields = {"cuisine", "cuisines", "confidence"};
        Field[] fields = response.getClass().getDeclaredFields();
        int count = response.getClass().getDeclaredFields().length;
        for (int i = 0; i < count; i++) {
            assertThat(fields[i].getName(), containsString(arrFields[i]));
        }
    }
}