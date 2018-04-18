package com.wahana.wahanamarketingclub.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.gson.Gson;
import com.wahana.wahanamarketingclub.R;
import com.wahana.wahanamarketingclub.adapter.ActivitySalesmenAdapter;
import com.wahana.wahanamarketingclub.connect.API;
import com.wahana.wahanamarketingclub.model.ActivitySalesmenIndex;
import com.wahana.wahanamarketingclub.model.LoginUser;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.wahana.wahanamarketingclub.activities.LoginActivity.MY_LOGIN_PREF;
import static com.wahana.wahanamarketingclub.activities.LoginActivity.MY_LOGIN_PREF_KEY;

public class TwoFragment extends Fragment {

    @BindView(R.id.simple_recycler)
    RecyclerView recyclerView;

    private Toolbar toolbar;
    public ArrayList<ActivitySalesmenIndex> data;
    public ArrayList<ActivitySalesmenIndex> tempData;
    private TextView contentView;

    public static TwoFragment newInstance(){
        return new TwoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_doing, container, false);
        ButterKnife.bind(this, rootView);
        getData();
        return rootView;
    }

    public void getData() {
        LoginUser savedUser = new Gson()
                .fromJson(getActivity()
                        .getSharedPreferences(MY_LOGIN_PREF, Context.MODE_PRIVATE)
                        .getString(MY_LOGIN_PREF_KEY, ""), LoginUser.class);

        String id = savedUser.getSalesmanId();
        API.getActivityToday(id).enqueue(new Callback<ArrayList<ActivitySalesmenIndex>>() {
            @Override
            public void onResponse(Call<ArrayList<ActivitySalesmenIndex>> call, Response<ArrayList<ActivitySalesmenIndex>> response) {
                Log.i("bebet tanjung", "onResponse: "+response.body());
                if (response.code()==200){
                    data = response.body();
                    tempData = data;
                    recyclerView.setHasFixedSize(true);
                    recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

                    recyclerView.setAdapter(new ActivitySalesmenAdapter(tempData));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ActivitySalesmenIndex>> call, Throwable t) {
                Toast.makeText(getActivity(), "gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }

}