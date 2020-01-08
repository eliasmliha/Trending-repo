package com.melhaoui.trending_repo.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;


import com.melhaoui.trending_repo.module.Item;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class RepositoriesDataFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Long, Item>> itemLiveData = new MutableLiveData<>();


    @NonNull
    @Override
    public DataSource create() {
        getDateQuery();
        RepositoriesData repositoriesData = new RepositoriesData(getDateQuery());
        itemLiveData.postValue(repositoriesData);
        return repositoriesData;
    }


    public MutableLiveData<PageKeyedDataSource<Long, Item>> getItemLiveDataSource() {
        return itemLiveData;
    }

    public String getDateQuery() {

        Date today = new Date();
        Calendar cal = new GregorianCalendar();
        cal.setTime(today);
        cal.add(Calendar.DAY_OF_MONTH, -30);
        Date today30 = cal.getTime();
        String query = "q=created:>" + new SimpleDateFormat("yyyy-MM-dd").format(today30);
        System.out.println("RepositoriesDataFactory.getDateQuery : " + query);
        return query;
    }
}
