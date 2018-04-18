/**
 * Created by Nina
 */

package com.wahana.wahanamarketingclub.activities;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.wahana.wahanamarketingclub.R;
import com.wahana.wahanamarketingclub.adapter.SectionsPagerAdapter;
import com.wahana.wahanamarketingclub.fragments.DoingFragment;
import com.wahana.wahanamarketingclub.fragments.DoneFragment;
import com.wahana.wahanamarketingclub.fragments.HomeFragment;
import com.wahana.wahanamarketingclub.fragments.TodoFragment;

import butterknife.BindView;

public class ActivitySalesmanActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.titleSearch)
    TextView titleSearch;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPager upViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salesman);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Activity Salesman");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tabLayout = (TabLayout) findViewById(R.id.tabs_activity);
        viewPager = (ViewPager) findViewById(R.id.forViewPager);

        init();

    }

    private void init() {
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void updateToolbarTitle(String title) {
        titleSearch.setText(title);
        titleSearch.setTextColor(Color.WHITE);

    }

    private void setupViewPager(final ViewPager viewPager) {
        SectionsPagerAdapter viewPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOffscreenPageLimit(3);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setTabsFromPagerAdapter(viewPagerAdapter);
    }
}
