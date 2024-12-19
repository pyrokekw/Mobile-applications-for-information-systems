package com.example.test1;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ImageApi {
    @GET("/api/")
    Call<ImageResponse> searchImages(
            @Query("key") String apiKey,
            @Query("q") String query,
            @Query("image_type") String imageType
    );
}
