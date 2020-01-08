package com.melhaoui.trending_repo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.melhaoui.trending_repo.R;
import com.melhaoui.trending_repo.module.Item;
import com.squareup.picasso.Picasso;

public class RecyclerAdapter extends PagedListAdapter<Item, RecyclerAdapter.myViewHolder> {

    private Context context;

    public RecyclerAdapter(Context context) {
        super(diffCallBack);
        this.context = context;
    }

    private static DiffUtil.ItemCallback<Item> diffCallBack = new DiffUtil.ItemCallback<Item>() {
        @Override
        public boolean areItemsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
            return oldItem.equals(newItem);
        }
    };

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        final Item repository = getItem(position);

        holder.repoName.setText(repository.getName());
        holder.repoDescription.setText(repository.getDescription());
        holder.numberOfStars.setText(String.valueOf(repository.getStargazersCount()));
        holder.repoOwnerName.setText(repository.getOwner().getLogin());

        try {
            Picasso.get().load(repository.getOwner().getAvatarUrl()).resizeDimen(R.dimen.avatar_size_w, R.dimen.avatar_size_h).onlyScaleDown().centerCrop().into(holder.avatar);
        } catch (Exception ignored) {

        }


    }

    class myViewHolder extends RecyclerView.ViewHolder {

        TextView repoName, repoDescription, repoOwnerName, numberOfStars;
        ImageView avatar;

        myViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.imageViewAvatar);
            repoName = itemView.findViewById(R.id.textViewRepoName);
            repoDescription = itemView.findViewById(R.id.textViewRepoDescription);
            repoOwnerName = itemView.findViewById(R.id.textViewRepoOwnerName);
            numberOfStars = itemView.findViewById(R.id.textViewNumberOfStars);

        }
    }
}
