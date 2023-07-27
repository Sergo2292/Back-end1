package org.example.HW5;

import com.github.javafaker.Faker;
import io.restassured.response.ResponseBody;
import lombok.SneakyThrows;
import org.example.HW5.api.ProductService;
import org.example.HW5.dto.Product;
import org.example.HW5.utils.RetrofitUtils;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.awt.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.equalTo;

public class CRUDProductTest {
    static ProductService productService;
    Product product;
    Faker faker = new Faker();
    Response<Product> response;
    int idProduct;

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit().create(ProductService.class);
    }

    @SneakyThrows
    @Test
    void crudProductTest() {
        System.out.println("================================");
        System.out.println("Create new product");
        product = new Product()
                .withTitle(faker.food().ingredient())
                .withCategoryTitle("Food")
                .withPrice((int) (Math.random() * 1000));
        response = productService.createProduct(product).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.code(), equalTo(201));
        assertThat(response.body().getTitle(), containsStringIgnoringCase(product.getTitle()));
        assertThat(response.body().getCategoryTitle(), containsStringIgnoringCase(product.getCategoryTitle()));
        assertThat(response.body().getPrice(), equalTo(product.getPrice()));
        idProduct = response.body().getId();
        System.out.println("id = " + idProduct);
        System.out.println("Title = " + product.getTitle());
        System.out.println("CategoryTitle = " + product.getCategoryTitle());
        System.out.println("Price = " + product.getPrice());

        System.out.println("================================");
        System.out.println("Get new product!");
        response = productService.getProductById(idProduct).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.code(), equalTo(200));
        assertThat(response.body().getTitle(), containsStringIgnoringCase(product.getTitle()));
        assertThat(response.body().getCategoryTitle(), containsStringIgnoringCase(product.getCategoryTitle()));
        assertThat(response.body().getPrice(), equalTo(product.getPrice()));

        System.out.println("==========================================");
        System.out.println("Modify new product!");
        product = new Product()
                .withId(idProduct)
                .withTitle(faker.food().ingredient())
                .withCategoryTitle("Food")
                .withPrice((int) (Math.random() * 1000));
        response = productService.modifyProduct(product).execute();
        System.out.println("id = " + idProduct);
        System.out.println("Title = " + product.getTitle());
        System.out.println("CategoryTitle = " + product.getCategoryTitle());
        System.out.println("Price = " + product.getPrice());
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.code(), equalTo(200));
        assertThat(response.body().getId(), equalTo(idProduct));
        assertThat(response.body().getTitle(), containsStringIgnoringCase(product.getTitle()));
        assertThat(response.body().getCategoryTitle(), containsStringIgnoringCase(product.getCategoryTitle()));
        assertThat(response.body().getPrice(), equalTo(product.getPrice()));
    }
}
