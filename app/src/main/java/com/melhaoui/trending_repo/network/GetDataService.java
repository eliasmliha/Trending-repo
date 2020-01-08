package com.melhaoui.trending_repo.network;

import com.melhaoui.trending_repo.module.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDataService {
    @GET("search/repositories?sort=stars&order=desc")
    Call<Result> getTrendingRepos(@Query("q") String q, @Query("page") Long page);

}

