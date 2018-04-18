package com.wahana.wahanamarketingclub.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import com.wahana.wahanamarketingclub.R
import com.wahana.wahanamarketingclub.connect.API
import com.wahana.wahanamarketingclub.model.LoginUser
import com.wahana.wahanamarketingclub.model.NotifActivitySalesman
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Lely on 1/3/18.
 */

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener(){
            btnLogin.visibility = View.INVISIBLE
            progressBar.visibility= View.VISIBLE
            val usernameEditText = username.text.toString()
            val passwordEditText = password.text.toString()

            if (usernameEditText.equals("") || passwordEditText.equals("")) {
                Toast.makeText(this, "Username & password Must Be Filled", Toast.LENGTH_SHORT).show()
            }

            API.login(LoginUser(usernameEditText, passwordEditText)).enqueue(object : Callback<LoginUser> {
                override fun onResponse(call: Call<LoginUser>, response: Response<LoginUser>) {
                    if (response.code() == 200) {
                        val user = response.body()
                        user!!.loginDateTime = Date()

                        // Save data login
                        getSharedPreferences(MY_LOGIN_PREF, Context.MODE_PRIVATE).edit().putString(MY_LOGIN_PREF_KEY, Gson().toJson(user)).apply()

                        if (user.status != "success") {
                            Toast.makeText(this@LoginActivity, user.status, Toast.LENGTH_LONG).show()
                        } else {
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                                    finish()         
                        }
                    } else {
                        Toast.makeText(this@LoginActivity, "Wrong Username Or Password", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<LoginUser>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "Error", Toast.LENGTH_LONG).show()
                }
            })
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    companion object {

        const val MY_LOGIN_PREF = "myLoginPref"
        const val MY_LOGIN_PREF_KEY = "loginPrefKey"
    }
}
