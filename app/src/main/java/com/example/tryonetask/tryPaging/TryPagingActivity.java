package com.example.tryonetask.tryPaging;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.tryonetask.R;
import com.example.tryonetask.pojo.MovieModel;

public class TryPagingActivity extends AppCompatActivity {

    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try_paging);

//        recyclerView = findViewById(R.id.recyclerview);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setHasFixedSize(true);
//
//        ItemViewModel itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
//
//        final ItemAdapter adapter = new ItemAdapter(this);
//
//        itemViewModel.itemPagedList.observe(this, new Observer<PagedList<MovieModel>>() {
//            @Override
//            public void onChanged(PagedList<MovieModel> movieModels) {
//                adapter.submitList(movieModels);
//            }
//        });
//
//        recyclerView.setAdapter(adapter);

    }
}
