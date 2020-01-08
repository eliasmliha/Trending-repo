package com.melhaoui.trending_repo.repository;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;


import com.melhaoui.trending_repo.module.Item;
import com.melhaoui.trending_repo.module.Result;
import com.melhaoui.trending_repo.network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoriesData extends PageKeyedDataSource<Long, Item> {
    private static final Long FIRST_PAGE = 0L;
    private String dateQuery;

    public RepositoriesData(String dateQuery) {
        this.dateQuery = dateQuery;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, Item> callback) {
        RetrofitInstance.getInstance().getApi().getTrendingRepos(dateQuery, FIRST_PAGE).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                callback.onResult(response.body().getItems(), null, FIRST_PAGE + 1);
                System.out.println("RepositoriesData.onResponse\n"+response.body().getItems());

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                System.out.println("RepositoriesData.onFailure | Throwable : " + t +" call : "+ call);
            }
        });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, Item> callback) {
        RetrofitInstance.getInstance().getApi().getTrendingRepos(dateQuery, params.key).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.body() != null) {
                    Long key = (params.key > 1) ? params.key - 1 : null;
                    callback.onResult(response.body().getItems(), key);
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, Item> callback) {
        RetrofitInstance.getInstance().getApi().getTrendingRepos(dateQuery, params.key).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.body() != null) {
                    final Long ITEMS_PER_RESULT = 29l;
                    Long totalPages = response.body().getTotalCount().longValue() - ITEMS_PER_RESULT;

                    Long key = totalPages.equals(params.key) ? null : params.key + 1;
                    callback.onResult(response.body().getItems(), key);
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }
}
