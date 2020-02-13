package com.example.tryonetask.ui.ViewPager;

import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tryonetask.R;
import com.example.tryonetask.tryPaging.ItemAdapter;
import com.example.tryonetask.tryPaging.ItemViewModel;
import com.example.tryonetask.tryPaging.OnTextClickListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Alaa Moaataz on 2020-01-28.
 */
public abstract class BaseFragment extends Fragment implements OnTextClickListener {


    RecyclerView recyclerView;
    ItemViewModel itemViewModel;
    ItemAdapter adapter;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = provideYourFragmentView(inflater,container,savedInstanceState);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setHasFixedSize(true);
        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);

        if(!isTablet(view.getContext())){

            adapter = new ItemAdapter(view.getContext(), null);

            recyclerView.setAdapter(adapter);
        }
        else {

            adapter = new ItemAdapter(view.getContext(), this);

        }





    }

    public abstract View provideYourFragmentView(LayoutInflater inflater,ViewGroup parent, Bundle savedInstanceState);

    Boolean isNetworkConnected(Context context){
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && cm.getActiveNetworkInfo().isConnected();
    }
    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

}
