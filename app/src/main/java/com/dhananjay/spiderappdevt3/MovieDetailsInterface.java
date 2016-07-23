package com.dhananjay.spiderappdevt3;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDetailsInterface {
    @GET("?")
    Call<MovieDetails> getDetails(@Query("t")String title);
}
