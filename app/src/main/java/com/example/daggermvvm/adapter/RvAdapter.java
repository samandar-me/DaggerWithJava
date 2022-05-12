package com.example.daggermvvm.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.daggermvvm.R;
import com.example.daggermvvm.databinding.ItemLayoutBinding;
import com.example.daggermvvm.model.RecyclerData;

import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.RvViewHolder> {

    private List<RecyclerData> listData;

    @NonNull
    @Override
    public RvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RvViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RvViewHolder holder, int position) {
        holder.bind(listData.get(position));
    }

    @Override
    public int getItemCount() {
        if (listData == null)
            return 0;
        else
            return listData.size();
    }

    static class RvViewHolder extends RecyclerView.ViewHolder {

        ItemLayoutBinding binding;

        public RvViewHolder(View view) {
            super(view);
            binding = ItemLayoutBinding.bind(view);
        }

        public void bind(RecyclerData data) {
            binding.tvTitle.setText(data.getName());
            binding.tvDesc.setText(data.getDescription());

            Glide.with(binding.imageView)
                    .load(data.getOwner().getAvatar_url())
                    .placeholder(R.drawable.ic_launcher_background)
                    .circleCrop()
                    .into(binding.imageView);
        }
    }

    public void setListData(List<RecyclerData> listData) {
        this.listData = listData;
    }
}
