package com.wahana.wahanamarketingclub.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wahana.wahanamarketingclub.fragments.DoingFragment;
import com.wahana.wahanamarketingclub.fragments.DoneFragment;
import com.wahana.wahanamarketingclub.fragments.TodoFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    final Context context;

    public SectionsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position){
            case 0:
                fragment = DoingFragment.newInstance();
                break;
            case 1:
                fragment = TodoFragment.newInstance();
                break;
            case 2:
                fragment = DoneFragment.newInstance();
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Doing";
            case 1:
                return "To do";
            case 2:
                return "Done";
        }
        return null;
    }
}
