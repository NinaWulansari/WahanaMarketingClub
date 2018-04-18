package com.wahana.wahanamarketingclub.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wahana.wahanamarketingclub.R;
import com.wahana.wahanamarketingclub.connect.API;
import com.wahana.wahanamarketingclub.model.ActivitySalesmenAdd;
import com.wahana.wahanamarketingclub.model.ActivitySalesmenIndex;
import com.wahana.wahanamarketingclub.model.CustomerProspectAdd;
import com.wahana.wahanamarketingclub.model.CustomerProspectIndex;
import com.wahana.wahanamarketingclub.model.LoginUser;
import com.wahana.wahanamarketingclub.model.StatusActivity;
import com.wahana.wahanamarketingclub.model.TypeActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.wahana.wahanamarketingclub.activities.CustomerProspectIndexActivity.CUST_ID;
import static com.wahana.wahanamarketingclub.activities.CustomerProspectIndexActivity.CUST_NAME;
import static com.wahana.wahanamarketingclub.activities.LoginActivity.MY_LOGIN_PREF;
import static com.wahana.wahanamarketingclub.activities.LoginActivity.MY_LOGIN_PREF_KEY;

/**
 * Created by Nina on 4/10/2018.
 */

public class ActivitySalesmanAddActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @BindView(R.id.date_activity)
    EditText date_activity;
    @BindView(R.id.time_activity)
    EditText time_activity;
    @BindView(R.id.type_activity)
    Spinner spinnerType;
    @BindView(R.id.status_activity)
    Spinner spinnerStatus;
    @BindView(R.id.note_activity)
    EditText notes;
    @BindView(R.id.subject_activity)
    EditText subject;

    Integer tipe;
    Integer statuss;

    Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_activity_salesman);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.add_activity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ButterKnife.bind(this);

        //dropdown status activity
        API.getStatusActivity().enqueue(new Callback<ArrayList<StatusActivity>>() {
            @Override
            public void onResponse(Call<ArrayList<StatusActivity>> call, Response<ArrayList<StatusActivity>> response) {
                if (response.code() == 200){
                    final ArrayList<StatusActivity> status_act = response.body();
                    String[] values = new String[status_act.size()];
                    for (int i = 0; i < status_act.size(); i++) {
                        String status_activity = status_act.get(i).getStatus();
                        values[i] = status_activity;
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(ActivitySalesmanAddActivity.this, R.layout.spinner_custom, values);
                    adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
                    spinnerStatus.setAdapter(adapter);

                    spinnerStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            String status_selected = adapterView.getItemAtPosition(i).toString();
                            statuss = status_act.get(i).getCode();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ArrayList<StatusActivity>> call, Throwable t) {
                Toast.makeText(ActivitySalesmanAddActivity.this, "gagal", Toast.LENGTH_SHORT).show();
            }
        });

        //dropdown type activity
        API.getTypeActivity().enqueue(new Callback<ArrayList<TypeActivity>>() {
            @Override
            public void onResponse(Call<ArrayList<TypeActivity>> call, Response<ArrayList<TypeActivity>> response) {
                if(response.code() == 200){
                    final ArrayList<TypeActivity> type_act = response.body();
                    String[] values = new String[type_act.size()];
                    for (int i = 0; i < type_act.size(); i++) {
                        String type_activity = type_act.get(i).getType();
                        values[i] = type_activity;
                    }
                    ArrayAdapter<String> adapater = new ArrayAdapter<String>(ActivitySalesmanAddActivity.this, R.layout.spinner_custom, values);
                    adapater.setDropDownViewResource(R.layout.spinner_dropdown_item);
                    spinnerType.setAdapter(adapater);

                    spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                            String type_selected = adapterView.getItemAtPosition(pos).toString();
                            tipe = type_act.get(pos).getCode();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ArrayList<TypeActivity>> call, Throwable t) {
                Toast.makeText(ActivitySalesmanAddActivity.this, "gagal", Toast.LENGTH_SHORT).show();
            }
        });

        //dialog date picker
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        date_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(ActivitySalesmanAddActivity.this, R.style.DatePickerDialogTheme, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //dialog time picker
        time_activity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(ActivitySalesmanAddActivity.this, R.style.DatePickerDialogTheme, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        time_activity.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
    }

    @OnClick(R.id.add_activity)
    void doAdd() {
        doAddActivity();
    }

    //when button save clicked
    private void doAddActivity() {
        if (isValid()) {
            ActivitySalesmenAdd activitySalesmenAdd= new ActivitySalesmenAdd();
            activitySalesmenAdd.setActivityStatus(statuss);
            activitySalesmenAdd.setActivityType(tipe);
            activitySalesmenAdd.setActivityTgl(date_activity.getText().toString());
            activitySalesmenAdd.setActivityTime(time_activity.getText().toString());
            activitySalesmenAdd.setActivitySubject(subject.getText().toString());
            activitySalesmenAdd.setActivityNote(notes.getText().toString());

            CustomerProspectIndex savecp = new Gson().fromJson(ActivitySalesmanAddActivity.this.getSharedPreferences(CUST_ID, Context.MODE_PRIVATE).getString(CUST_NAME, ""), CustomerProspectIndex.class);
            String id_cp = savecp.getId();
            activitySalesmenAdd.setLeadId(id_cp);
            String salesman_id = savecp.getSalesmanId();
            activitySalesmenAdd.setSalesmanId(salesman_id);

            String date_act = activitySalesmenAdd.getActivityTgl();
            DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            DateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
            inputFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            try {
                Date dates = inputFormat.parse(date_act);
                String outputText = outputFormat.format(dates);
                date_activity.setText(outputText);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            activitySalesmenAdd.setActivityTgl(date_activity.getText().toString());

            String date_act2 = activitySalesmenAdd.getActivityTgl();
            DateFormat outputFormat2 = new SimpleDateFormat("dd/MM/yyyy");
            DateFormat inputFormat2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            inputFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            try {
                Date dates2 = inputFormat2.parse(date_act2);
                String outputText2 = outputFormat2.format(dates2);
                date_activity.setText(outputText2);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            API.insertActivity(activitySalesmenAdd).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Log.i("TAMBAH", "onResponse: " + response);
                    if (response.code()==200){
                        Toast.makeText(ActivitySalesmanAddActivity.this, "success", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(ActivitySalesmanAddActivity.this, "failed", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(ActivitySalesmanAddActivity.this, "check connection", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    public static void startActivity(FragmentActivity activity) {
        Intent intent =  new Intent(activity, ActivitySalesmanAddActivity.class);
        activity.startActivity(intent);
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        date_activity.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public boolean isValid() {
        boolean valid = true;
        String sub = subject.getText().toString();
        String not = notes.getText().toString();

        //validasi subject
        if(sub.isEmpty()){
            subject.setError("Subject belum diisi");
            valid = false;
        } else if (sub.length()<5) {
            subject.setError("Subject minimal 5 karakter");
        } else {
            subject.setError(null);
        }

        //validasi subject
        if(sub.isEmpty()){
            notes.setError("Subject belum diisi");
            valid = false;
        } else if (not.length()<5) {
            notes.setError("Subject minimal 5 karakter");
        } else {
            notes.setError(null);
        }

        return valid;
    }

}
