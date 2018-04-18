package com.wahana.wahanamarketingclub.activities;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wahana.wahanamarketingclub.R;
import com.wahana.wahanamarketingclub.connect.API;
import com.wahana.wahanamarketingclub.model.LoginUser;
import com.wahana.wahanamarketingclub.model.MyProfile;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.wahana.wahanamarketingclub.activities.LoginActivity.MY_LOGIN_PREF;
import static com.wahana.wahanamarketingclub.activities.LoginActivity.MY_LOGIN_PREF_KEY;

/**
 * Created by Anggi on 1/9/2018.
 */


public class EditProfileActivity extends AppCompatActivity {

    @BindView(R.id.editText_display_name)
    EditText eDisplay_name;
    @BindView(R.id.editText_phone_number)
    EditText ePhone_number;
    @BindView(R.id.editText_birth)
    EditText eBirth;
    @BindView(R.id.editText_email)
    EditText eEmail;
    @BindView(R.id.radioGroupGender)
    RadioGroup rbgGender;
    @BindView(R.id.genderTV)
    TextView eGender;

    @BindView(R.id.id_profile)
    EditText eIdProfile;

    private RadioButton radioButton;
    private Toolbar toolbar;
    String id;

    TextView textView_change_password;
    Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        ButterKnife.bind(this);

        LoginUser savedUser = new Gson()
                .fromJson(EditProfileActivity
                        .this.getSharedPreferences(MY_LOGIN_PREF, Context.MODE_PRIVATE)
                        .getString(MY_LOGIN_PREF_KEY, ""), LoginUser.class);

        String userID = savedUser.getId();
        String userDisplayName = savedUser.getName();
        String phoneNumber = savedUser.getPhoneNumber();
        String birthUser = savedUser.getBirthDate();
        String genderUser = savedUser.getGender();
        DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        inputFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            Date dates = inputFormat.parse(birthUser);
            String outputText = outputFormat.format(dates);
            eBirth.setText(outputText+ " ");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String emailUser = savedUser.getEmail();

        eIdProfile.setText(userID);
        eDisplay_name.setText(userDisplayName);
        ePhone_number.setText(phoneNumber);
        eEmail.setText(emailUser);
        eGender.setText(genderUser);

        textView_change_password = (TextView) findViewById(R.id.textView_change_password);
        textView_change_password.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(EditProfileActivity.this, ChangePasswordActivity.class);
                        startActivity(intent);
                    }
                });

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.edit_profile);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


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

        eBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(EditProfileActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        rbgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButton = findViewById(checkedId);
                String genderr = radioButton.getText().toString();

                if (genderr.equals("Male")){
                    eGender.setText("L");
                }else if(genderr.equals("Female")){
                    eGender.setText("P");
                }else if(!genderr.equals("Male")||!genderr.equals("Female")) {
                    radioButton.setError("Must be choosen");
                }else {
                    radioButton.setError("Must be choosen");
                }
            }
        });

    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        eBirth.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public static void startActivity(FragmentActivity activity) {
        Intent intent =  new Intent(activity, EditProfileActivity.class);
        activity.startActivity(intent);
    }

    @OnClick(R.id.textView_change_password)
    void changePassword(){
        Intent intent = new Intent(EditProfileActivity.this, ChangePasswordActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.button_save_edit_profile)
    void saveEditProfile(){
        doEditProfile();
    }

    private boolean isValid() {
        boolean valid = true;
        String name = eDisplay_name.getText().toString();
        String email = eEmail.getText().toString();
        String phone_number = ePhone_number.getText().toString();

        //NAME VALIDATION
        if(name.isEmpty()){
            eDisplay_name.setError("Name must be filled");
            valid = false;
        }
        else if (name.length()<3)
        {
            eDisplay_name.setError("Name at least 3 character");
            valid = false;
        }
        else
        {
            eDisplay_name.setError(null);
        }

        //VALIDATION PHONE NUMBER
        if(phone_number.isEmpty()){
            ePhone_number.setError("Phone number must be filled");
            valid = false;
        }
        else
        {
            ePhone_number.setError(null);
        }

        //VALIDATION GENDER
//        if(gender.isEmpty()){
//            eGender.setError("Gender must be filled");
//            valid = false;
//        }
//        else if (!gender.equals(genderL)&&!gender.equals(genderP))
//        {
//            eGender.setError("Gender can only contain L or P");
//            valid = false;
//        }
//        else
//        {
//            eGender.setError(null);
//        }

        //VALIDATION EMAIL
//        if(email.isEmpty()){
//            emailProfile.setError("Email belum diisi");
//            valid = false;
//        }
//        else if(!isValidEmail(email))
//        {
//            emailProfile.setError("Invalid Email");
//            valid = false;
//        }
//        else
//        {
//            emailProfile.setError(null);
//        }
        return valid;
    }

    public void doEditProfile(){
        if (isValid()){
            MyProfile dataUser = new MyProfile();

            dataUser.setId(eIdProfile.getText().toString());
            dataUser.setName(eDisplay_name.getText().toString());
            dataUser.setEmail(eEmail.getText().toString());
            dataUser.setPhoneNumber(ePhone_number.getText().toString());
            dataUser.setBirthDate(eBirth.getText().toString());
            dataUser.setGender(eGender.getText().toString());

            String birthUser = dataUser.getBirthDate();
            DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            DateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
            inputFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            try {
                Date dates = inputFormat.parse(birthUser);
                String outputText = outputFormat.format(dates);
                eBirth.setText(outputText);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            dataUser.setBirthDate(eBirth.getText().toString());

            String birthUser2 = dataUser.getBirthDate();
            DateFormat outputFormat2 = new SimpleDateFormat("dd/MM/yyyy");
            DateFormat inputFormat2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            inputFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            try {
                Date dates2 = inputFormat2.parse(birthUser2);
                String outputText2 = outputFormat2.format(dates2);
                eBirth.setText(outputText2);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            API.editProfile(dataUser).enqueue(new Callback<MyProfile>() {
                @Override
                public void onResponse(Call<MyProfile> call, Response<MyProfile> response) {
                    if (response.code() == 200) {
                        Toast.makeText(EditProfileActivity.this, "Edit success ! Swipe down to refresh", Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        Toast.makeText(EditProfileActivity.this, "failed", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<MyProfile> call, Throwable t) {
                    Toast.makeText(EditProfileActivity.this, "Check your connection", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }



}