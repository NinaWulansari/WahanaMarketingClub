package com.wahana.wahanamarketingclub.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wahana.wahanamarketingclub.R;
import com.wahana.wahanamarketingclub.connect.API;
import com.wahana.wahanamarketingclub.model.LoginUser;
import com.wahana.wahanamarketingclub.model.MyProfile;

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

public class ChangePasswordActivity extends AppCompatActivity {

    @BindView(R.id.editText_new_password)
    EditText newPassword;
    @BindView(R.id.editText_confrim_new_password)
    EditText confrimNewPassword;
    @BindView(R.id.id_password)
    EditText idPassword;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        ButterKnife.bind(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.change_password);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        LoginUser savedUser = new Gson()
                .fromJson(ChangePasswordActivity
                        .this.getSharedPreferences(MY_LOGIN_PREF, Context.MODE_PRIVATE)
                        .getString(MY_LOGIN_PREF_KEY, ""), LoginUser.class);

        String userID = savedUser.getId();
        idPassword.setText(userID);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private boolean isValid() {
        boolean valid = true;

        String newPass = newPassword.getText().toString();
        String confirmNewPass = confrimNewPassword.getText().toString();

        if(newPass.isEmpty()){
            newPassword.setError("New password must be filled");
            valid = false;
        }
        else if(newPass.length()<6)
        {
            newPassword.setError("At least 6 character");
            valid = false;
        }
        else
        {
            newPassword.setError(null);
        }

        if (confirmNewPass.isEmpty()){
            confrimNewPassword.setError("Confirm password must be filled");
        }
        else if (!confirmNewPass.equals(newPass))
        {
            confrimNewPassword.setError("Confirm Password and New Password must be the same");
        }
        else
        {
            confrimNewPassword.setError(null);
        }

        return valid;
    }

    @OnClick(R.id.button_save_change_password)
    void doSaveNewPassword(){
       if (isValid()){
           MyProfile dataUser = new MyProfile();

           dataUser.setId(idPassword.getText().toString());
           dataUser.setPassword(newPassword.getText().toString());

           final String newPass = newPassword.getText().toString();
           final String confirmNewPass = confrimNewPassword.getText().toString();

           Log.i("Password", "anggil: "+dataUser.getId());
           API.editPassword(dataUser).enqueue(new Callback<MyProfile>() {
               @Override
               public void onResponse(Call<MyProfile> call, Response<MyProfile> response) {
                   if (newPass.equals(confirmNewPass)) {
                       Toast.makeText(ChangePasswordActivity.this, "Succes change password", Toast.LENGTH_SHORT).show();
                       finish();
                   }else{
                       Toast.makeText(ChangePasswordActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                   }
               }

               @Override
               public void onFailure(Call<MyProfile> call, Throwable t) {

               }
           });
       }
    }
}