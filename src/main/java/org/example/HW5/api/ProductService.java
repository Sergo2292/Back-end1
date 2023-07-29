package org.example.HW5.api;

import okhttp3.ResponseBody;
import org.example.HW5.dto.Product;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.*;

import java.awt.*;
import java.util.ListIterator;

public interface ProductService {
    @POST("products")
    Call<Product> createProduct(@Body Product createProductRequest);
    @DELETE("products/{id}")
    Call<ResponseBody> deleteProduct(@Path("id") int id);
    @PUT("products")
    Call<Product> modifyProduct(@Body Product modifyProductRequest);
    @GET("products/{id}")
    Call<Product> getProductById(@Path("id") int id);


}
