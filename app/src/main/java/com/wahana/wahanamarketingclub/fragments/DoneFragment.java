package com.wahana.wahanamarketingclub.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wahana.wahanamarketingclub.R;

public class DoneFragment extends Fragment {

    public static DoneFragment newInstance(){
        return new DoneFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_doing, container, false);

        return rootView;
    }
}