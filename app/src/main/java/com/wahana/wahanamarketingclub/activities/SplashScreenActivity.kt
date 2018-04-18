package com.wahana.wahanamarketingclub.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity

import com.google.gson.Gson
import com.wahana.wahanamarketingclub.model.LoginUser

import java.util.Calendar
import java.util.Date
import com.wahana.wahanamarketingclub.R

import com.wahana.wahanamarketingclub.activities.LoginActivity.Companion.MY_LOGIN_PREF
import com.wahana.wahanamarketingclub.activities.LoginActivity.Companion.MY_LOGIN_PREF_KEY

/**
 * Created by lely
 */

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen_activity)

        Handler().postDelayed({
            val savedUser = Gson().fromJson(getSharedPreferences(MY_LOGIN_PREF, Context.MODE_PRIVATE).getString(MY_LOGIN_PREF_KEY, ""), LoginUser::class.java)

            if (savedUser != null && savedUser.status == "success" && savedUser.loginDateTime != null) {
                val loginDateTime = savedUser.loginDateTime
                val now = Calendar.getInstance()
                val loginCalendar = Calendar.getInstance()
                loginCalendar.time = loginDateTime
                loginCalendar.add(Calendar.HOUR, -6)

                if (loginCalendar.before(now)) {
                        startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
                        finish()
                } else {
                    val i = Intent(this@SplashScreenActivity, LoginActivity::class.java)
                    startActivity(i)
                    finish()
                }

            } else {
                val i = Intent(this@SplashScreenActivity, LoginActivity::class.java)
                startActivity(i)
                finish()
            }
        }, TIME_OUT.toLong())
    }

    companion object {
        val TIME_OUT = 2000
    }
}
