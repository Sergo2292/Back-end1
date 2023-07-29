package org.example.HW5.api;

import org.example.HW5.dto.GetCategoryResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface CategoryService {
    @GET("categories/{id}")
    Call<GetCategoryResponse> getCategoryById(@Path("id") int id);
}
