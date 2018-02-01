package com.wahana.wahanamarketingclub.fragments;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wahana.wahanamarketingclub.R;
import com.wahana.wahanamarketingclub.activities.MainActivity;
import com.wahana.wahanamarketingclub.adapter.MoreAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */

public class MoreFragment extends BaseFragment{


    public static BaseFragment newInstance(Context context) {
        MoreFragment f = new MoreFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_more, null);
        GridView gv1=(GridView)view.findViewById(R.id.gridview);
        gv1.setAdapter(new MoreAdapter(getActivity()));

        ( (MainActivity)getActivity()).updateToolbarTitle("More");

        return view;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        return null;
    }
}
