package com.wahana.wahanamarketingclub.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Spinner;

import com.wahana.wahanamarketingclub.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.wahana.wahanamarketingclub.fragments.DoingFragment.DATA_ACTIVITY;

public class ActivityDetailActivity extends AppCompatActivity {

    @BindView(R.id.status_activity)
    Spinner e_statusActivity;
    @BindView(R.id.date_activity)
    EditText e_dateActivity;
    @BindView(R.id.time_activity)
    EditText e_timeActivity;
    @BindView(R.id.subject_activity)
    EditText e_subjectActivity;
    @BindView(R.id.type_activity)
    Spinner e_typeActivity;
    @BindView(R.id.note_activity)
    EditText e_noteActivity;
    @BindView(R.id.nama_cp)
    EditText e_namaCp;
    @BindView(R.id.kode_cp)
    EditText e_kodeCp;

    String editActivitySales;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_activity);
        editActivitySales = getIntent().getStringExtra(DATA_ACTIVITY);
        ButterKnife.bind(this);
        getActivity();
    }

    void getActivity(){

    }

    public static void startActivity(FragmentActivity activity) {
        Intent intent =  new Intent(activity, ActivityDetailActivity.class);
        activity.startActivity(intent);
    }
}
