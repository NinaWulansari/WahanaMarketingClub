/**
 * Created by Nina
 */

package com.wahana.wahanamarketingclub.fragments;


import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wahana.wahanamarketingclub.R;
import com.wahana.wahanamarketingclub.activities.MainActivity;
import com.wahana.wahanamarketingclub.model.ImageModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeFragment extends BaseFragment{

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<ImageModel> imageModelArrayList;

    private int[] myImageList = new int[]{R.drawable.quote1, R.drawable.quote2,
            R.drawable.quote3,R.drawable.quote4
            ,R.drawable.quote5};

    @BindView(R.id.tab_activity)
    TabLayout tab_activity;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this, view);

        ( (MainActivity)getActivity()).updateToolbarTitle("Home");

        tab_activity.addTab(tab_activity.newTab().setText("Doing Activity"));
        tab_activity.addTab(tab_activity.newTab().setText("To do Activity"));
        viewPager.setAdapter(new PagerAdapter(getFragmentManager(), tab_activity.getTabCount()));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab_activity));
        tab_activity.setSelectedTabIndicatorColor(Color.parseColor("#ffffff"));
        tab_activity.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#ffffff"));
        tab_activity.setupWithViewPager(viewPager);
        tab_activity.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

    public class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;

        public PagerAdapter(FragmentManager fm,int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new TwoFragment();
                case 1:
                    return new ThreeFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
                return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Doing";
                case 1:
                    return "To do";
                default:
                    return "";
            }
        }
    }
}

