package org.example.HW4;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "result",
        "offset",
        "number",
        "totalResults"
})
@Data

public class SearchRecipesResponse {
    @JsonProperty("results")
    private List<Result> results;
    @JsonProperty("offset")
    private Integer offset;
    @JsonProperty("number")
    private Integer number;
    @JsonProperty("totalResult")
    private Integer totalResult;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "id",
            "title",
            "image",
            "imageType",
            "nutrition"
    })
    @Data
    protected static class Result {
        @JsonProperty("id")
        private Integer id;
        @JsonProperty("title")
        private String title;
        @JsonProperty("image")
        private String image;
        @JsonProperty("imageType")
        private String imageType;
        @JsonProperty("nutrition")
        private Nutrition nutrition;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonPropertyOrder({
               "nutrients"
        })
        @Data
        protected static class Nutrition {
            @JsonProperty("nutrients")
            private List<Nutrient> nutrients;

            @JsonInclude(JsonInclude.Include.NON_NULL)
            @JsonPropertyOrder({
                    "name",
                    "amount",
                    "unit"
            })
            @Data
            protected static class Nutrient {
                @JsonProperty("name")
                private String name;
                @JsonProperty("amount")
                private Double amount;
                @JsonProperty("unit")
                private String unit;
            }
        }
    }
}
