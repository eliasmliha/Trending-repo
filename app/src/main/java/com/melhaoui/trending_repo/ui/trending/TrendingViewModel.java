package com.melhaoui.trending_repo.ui.trending;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.melhaoui.trending_repo.module.Item;
import com.melhaoui.trending_repo.repository.RepositoriesDataFactory;


public class TrendingViewModel extends ViewModel {
    LiveData RepoPagedList;
    LiveData<PageKeyedDataSource<Long, Item>> sourceLiveData;

    public TrendingViewModel() {
        RepositoriesDataFactory repositoriesDataFactory = new RepositoriesDataFactory();
        sourceLiveData = repositoriesDataFactory.getItemLiveDataSource();

        PagedList.Config config = (new PagedList.Config.Builder()).setEnablePlaceholders(false)
                .setPageSize(29)
                .build();
        RepoPagedList = (new LivePagedListBuilder(repositoriesDataFactory, config)).build();

    }
}