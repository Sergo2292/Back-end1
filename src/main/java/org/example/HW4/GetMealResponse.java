package org.example.HW4;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "aisles",
        "cost",
        "startDate",
        "endDate"
})
@Data
public class GetMealResponse {

    @JsonProperty("aisles")
    private List<Aisle> aisles;
    @JsonProperty("cost")
    private Double cost;
    @JsonProperty("startDate")
    private Integer startDate;
    @JsonProperty("endDate")
    private Integer endDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "aisle",
            "items"
    })
    @Data
    protected static class Aisle {

        @JsonProperty("aisle")
        private String aisle;
        @JsonProperty("items")
        private List<Item> items;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "id",
            "name",
            "measures",
            "usages",
            "usageRecipeIds",
            "pantryItem",
            "aisle",
            "cost",
            "ingredientId"
    })
    @Data
    protected static class Item {
        @JsonProperty("id")
        private Integer id;
        @JsonProperty("name")
        private String name;
        @JsonProperty("measures")
        private Measures measures;
        @JsonProperty("usages")
        private List<Object> usages;
        @JsonProperty("usageRecipeIds")
        private List<Object> usageRecipeIds;
        @JsonProperty("pantryItem")
        private Boolean pantryItem;
        @JsonProperty("aisle")
        private String aisle;
        @JsonProperty("cost")
        private Double cost;
        @JsonProperty("ingredientId")
        private Integer ingredientId;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonPropertyOrder({
                "original",
                "metric",
                "us"
        })
        @Data
        protected static class Measures {
            @JsonProperty("original")
            private Original original;
            @JsonProperty("metric")
            private Metric metric;
            @JsonProperty("us")
            private Us us;

            @JsonInclude(JsonInclude.Include.NON_NULL)
            @JsonPropertyOrder({
                    "amount",
                    "unit"
            })
            @Data
            protected static class Original {
                @JsonProperty("amount")
                private Double amount;
                @JsonProperty("unit")
                private String unit;
            }

            @JsonInclude(JsonInclude.Include.NON_NULL)
            @JsonPropertyOrder({
                    "amount",
                    "unit"
            })
            @Data
            protected static class Metric{
                @JsonProperty("amount")
                private Double amount;
                @JsonProperty("unit")
                private String unit;
            }
            @JsonInclude(JsonInclude.Include.NON_NULL)
            @JsonPropertyOrder({
                    "amount",
                    "unit"
            })
            @Data
            protected static class Us {
                @JsonProperty("amount")
                private Double amount;
                @JsonProperty("unit")
                private String unit;
            }
        }
    }
}
