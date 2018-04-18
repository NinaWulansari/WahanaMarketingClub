package com.wahana.wahanamarketingclub.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.squareup.picasso.Picasso;
import com.wahana.wahanamarketingclub.R;
import com.wahana.wahanamarketingclub.connect.API;
import com.wahana.wahanamarketingclub.model.MasterAboutUs;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.wahana.wahanamarketingclub.connect.API.baseURL;
import static com.wahana.wahanamarketingclub.connect.API.baseURLPicasso;


/**
 * Created by ratriwow on 10/01/2018.
 */

public class AboutUs extends AppCompatActivity {

    @BindView(R.id.text_title_aboutUs) TextView title;
    @BindView(R.id.text_content_aboutUs) WebView content;
    @BindView(R.id.ImageTitle) ImageView image;
    @BindView(R.id.toolbar)
    android.support.v7.widget.Toolbar toolbar;
    @BindView(R.id.titleSearch) TextView titleSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        ButterKnife.bind(this);
        getData();

        content.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getAction() == MotionEvent.ACTION_MOVE);
            }
        });

        titleSearch.setText("About Us");
        titleSearch.setTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }


    public void getData() {
        API.getMasterAboutUs().enqueue(new Callback<MasterAboutUs>() {
            @Override
            public void onResponse(Call<MasterAboutUs> call, Response<MasterAboutUs> response) {
                Log.i("DATA ABOUT US", "onResponse: "+response.body());
                MasterAboutUs masterAboutUs = response.body();
                if (masterAboutUs.getStatus().equals("200")) {
                    String titleAboutUs = masterAboutUs.getResult().get(0).getTitle();
                    String contentAboutUs = masterAboutUs.getResult().get(0).getContent();
                    String imageAboutUs = masterAboutUs.getResult().get(0).getImage();

                    title.setText(titleAboutUs);
                    content.loadData("<p style=\"text-align:justify\">"+contentAboutUs+"</p>","text/html","UTF-8");
                    Picasso.with(AboutUs.this)
                            .load(baseURLPicasso +imageAboutUs)
                            .error(R.drawable.logo).centerCrop().fit()
                            .into(image);
                } else if(masterAboutUs.getStatus().equals("400")){
                    Toast.makeText(AboutUs.this,"No Data", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MasterAboutUs> call, Throwable t) {
                Toast.makeText(AboutUs.this,"Please check your internet connection", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
