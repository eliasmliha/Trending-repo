package com.melhaoui.trending_repo.ui.trending;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.melhaoui.trending_repo.R;
import com.melhaoui.trending_repo.adapter.RecyclerAdapter;
import com.melhaoui.trending_repo.module.Item;

public class TrendingFragment extends Fragment {

    private TrendingViewModel trendingViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        trendingViewModel =
                ViewModelProviders.of(this).get(TrendingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_trending, container, false);

        initRecyclerView(root);


        return root;
    }


    private void initRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        final RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getContext());

        final TrendingViewModel trendingViewModel = ViewModelProviders.of(this).get(TrendingViewModel.class);

        trendingViewModel.RepoPagedList.observe(this, new Observer<PagedList<Item>>() {
            @Override
            public void onChanged(PagedList<Item> items) {
                recyclerAdapter.submitList(items);
            }
        });
        recyclerView.setAdapter(recyclerAdapter);
    }


}