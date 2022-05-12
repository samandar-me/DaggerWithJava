package com.example.daggermvvm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.example.daggermvvm.R;
import com.example.daggermvvm.adapter.RvAdapter;
import com.example.daggermvvm.databinding.ActivityMainBinding;
import com.example.daggermvvm.model.RecyclerList;
import com.example.daggermvvm.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private RvAdapter rvAdapter;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViews();

    }

    private void initViews() {
        rvAdapter = new RvAdapter();
        setupRv();
        getData();
    }

    private void setupRv() {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(rvAdapter);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void getData() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.getLiveDataListObserver().observe(this, recyclerList -> {
            if (recyclerList != null) {
                rvAdapter.setListData(recyclerList.getItems());
                rvAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
        viewModel.makeApiCall();
    }
}