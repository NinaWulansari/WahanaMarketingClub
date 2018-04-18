/**
 * Created by Nina
 */

package com.wahana.wahanamarketingclub.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wahana.wahanamarketingclub.R;
import com.wahana.wahanamarketingclub.fragments.MoreFragmentCabang;
import com.wahana.wahanamarketingclub.fragments.MoreFragmentMainDealer;
import com.wahana.wahanamarketingclub.fragments.MoreFragmentSupervisor;
import com.wahana.wahanamarketingclub.fragments.NewsFragment;
import com.wahana.wahanamarketingclub.fragments.BaseFragment;
import com.wahana.wahanamarketingclub.fragments.ChatFragment;
import com.wahana.wahanamarketingclub.fragments.HomeFragment;
import com.wahana.wahanamarketingclub.fragments.ProfileFragment;
import com.wahana.wahanamarketingclub.fragments.MoreFragment;
import com.wahana.wahanamarketingclub.model.LoginUser;
import com.wahana.wahanamarketingclub.utils.FragmentHistory;
import com.wahana.wahanamarketingclub.utils.Utils;
import com.wahana.wahanamarketingclub.views.FragNavController;

import java.util.Calendar;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.ajsmsig.pointofsale.utils.DisplayUtil;

import static com.wahana.wahanamarketingclub.activities.LoginActivity.MY_LOGIN_PREF;
import static com.wahana.wahanamarketingclub.activities.LoginActivity.MY_LOGIN_PREF_KEY;

public class MainActivity extends BaseActivity implements BaseFragment.FragmentNavigation, FragNavController.TransactionListener, FragNavController.RootFragmentListener {
    @BindView(R.id.content_frame)
    FrameLayout contentFrame;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.titleSearch)
    TextView titleSearch;

    private int[] mTabIconsSelected = {
            R.drawable.ic_home,
            R.drawable.ic_public,
            R.drawable.ic_chat,
            R.drawable.ic_person,
            R.drawable.ic_more};


    @BindArray(R.array.tab_name)
    String[] TABS;

    @BindView(R.id.bottom_tab_layout)
    TabLayout bottomTabLayout;

    private FragNavController mNavController;

    private FragmentHistory fragmentHistory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initToolbar();
        initTab();

//        Calendar cal = Calendar.getInstance();
//        cal.add( Calendar.SECOND, 1);
//
//        NotificationScheduler.Companion.setReminder(this, AlarmReceiver.class, "Lely","C",cal.getTime());

        Toast.makeText(this, DisplayUtil.INSTANCE.getScreenSize(), Toast.LENGTH_SHORT).show();

        fragmentHistory = new FragmentHistory();


        mNavController = FragNavController.newBuilder(savedInstanceState, getSupportFragmentManager(), R.id.content_frame)
                .transactionListener(this)
                .rootFragmentListener(this, TABS.length)
                .build();
        switchTab(0);

        bottomTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                fragmentHistory.push(tab.getPosition());
                switchTab(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

                mNavController.clearStack();
                switchTab(tab.getPosition());
            }
        });
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
    }

    private void initTab() {
        if (bottomTabLayout != null) {
            for (int i = 0; i < TABS.length; i++) {
                bottomTabLayout.addTab(bottomTabLayout.newTab());
                TabLayout.Tab tab = bottomTabLayout.getTabAt(i);
                if (tab != null)
                    tab.setCustomView(getTabView(i));
            }
        }
    }

    private View getTabView(int position) {
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.tab_item_bottom, null);
        ImageView icon = (ImageView) view.findViewById(R.id.tab_icon);
        icon.setImageDrawable(Utils.setDrawableSelector(MainActivity.this, mTabIconsSelected[position], mTabIconsSelected[position]));
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {

        super.onStop();
    }


    private void switchTab(int position) {
        mNavController.switchTab(position);
//        updateToolbarTitle(position);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_aboutUs){
            Intent intent = new Intent(getApplicationContext(),AboutUs.class);
            startActivity(intent);
            return true;
        } else if (id==R.id.action_logout){
            SharedPreferences preferences = getSharedPreferences(MY_LOGIN_PREF, Context.MODE_PRIVATE);
            preferences.edit().remove(MY_LOGIN_PREF_KEY).apply();
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        }

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void updateTabSelection(int currentTab){

        for (int i = 0; i <  TABS.length; i++) {
            TabLayout.Tab selectedTab = bottomTabLayout.getTabAt(i);
            if(currentTab != i) {
                selectedTab.getCustomView().setSelected(false);
            }else{
                selectedTab.getCustomView().setSelected(true);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mNavController != null) {
            mNavController.onSaveInstanceState(outState);
        }
    }

    @Override
    public void pushFragment(Fragment fragment) {
        if (mNavController != null) {
            mNavController.pushFragment(fragment);
        }
    }

    @Override
    public void onTabTransaction(Fragment fragment, int index) {
        // If we have a backstack, show the back button
        if (getSupportActionBar() != null && mNavController != null) {
            updateToolbar();

        }
    }

    private void updateToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(!mNavController.isRootFragment());
        getSupportActionBar().setDisplayShowHomeEnabled(!mNavController.isRootFragment());
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
    }


    @Override
    public void onFragmentTransaction(Fragment fragment, FragNavController.TransactionType transactionType) {
        //do fragmentty stuff. Maybe change title, I'm not going to tell you how to live your life
        // If we have a backstack, show the back button
        if (getSupportActionBar() != null && mNavController != null) {

            updateToolbar();

        }
    }

    @Override
    public Fragment getRootFragment(int index) {
        switch (index) {

            case FragNavController.TAB1:
                return new HomeFragment();
            case FragNavController.TAB2:
                return new NewsFragment();
            case FragNavController.TAB3:
                return new ChatFragment();
            case FragNavController.TAB4:
                return new ProfileFragment();
            case FragNavController.TAB5:
                LoginUser savedUser = new Gson().fromJson(MainActivity
                        .this.getSharedPreferences(MY_LOGIN_PREF, Context.MODE_PRIVATE).getString(MY_LOGIN_PREF_KEY, ""), LoginUser.class);
                String name = savedUser.getTitle();
                if(name.equals("Admin Dealer")){
                    return new MoreFragmentCabang();
                }else if(name.equals("Admin Main Dealer")){
                    return new MoreFragmentMainDealer();
                }else if(name.equals("Supervisor")){
                    return new MoreFragmentSupervisor();
                }else if(name.equals("Salesman")) {
                    return new MoreFragment();
                }
        }
        throw new IllegalStateException("Need to send an index that we know");
    }

    public void updateToolbarTitle(String title) {
        titleSearch.setText(title);
        titleSearch.setTextColor(Color.WHITE);

    }

}
