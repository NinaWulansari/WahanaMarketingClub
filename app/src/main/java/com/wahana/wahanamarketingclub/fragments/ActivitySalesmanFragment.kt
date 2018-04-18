package com.wahana.wahanamarketingclub.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.gson.Gson

import com.wahana.wahanamarketingclub.R
import com.wahana.wahanamarketingclub.R.id.recyclerView
import com.wahana.wahanamarketingclub.activities.ActivitySalesmanAddActivity
import com.wahana.wahanamarketingclub.activities.CustomerProspectAddActivity
import com.wahana.wahanamarketingclub.activities.CustomerProspectIndexActivity.Companion.CUST_ID
import com.wahana.wahanamarketingclub.activities.CustomerProspectIndexActivity.Companion.CUST_NAME
import com.wahana.wahanamarketingclub.activities.LoginActivity
import com.wahana.wahanamarketingclub.activities.LoginActivity.Companion.MY_LOGIN_PREF
import com.wahana.wahanamarketingclub.activities.LoginActivity.Companion.MY_LOGIN_PREF_KEY
import com.wahana.wahanamarketingclub.adapter.ActivitySalesmenAdapter
import com.wahana.wahanamarketingclub.connect.API
import com.wahana.wahanamarketingclub.model.ActivitySalesmenIndex
import com.wahana.wahanamarketingclub.model.CustomerProspectIndex
import com.wahana.wahanamarketingclub.model.LoginUser
import kotlinx.android.synthetic.main.activity_customer_prospect_index.*
import kotlinx.android.synthetic.main.fragment_activity_salesman.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList


class ActivitySalesmanFragment : Fragment() {

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }

    var datas: ArrayList<ActivitySalesmenIndex>? = null
    var tempDatas: ArrayList<ActivitySalesmenIndex>? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        getData()
        return inflater!!.inflate(R.layout.fragment_activity_salesman, container, false)

    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        floating.setOnClickListener {
            val intent = Intent(activity, ActivitySalesmanAddActivity::class.java)
            startActivity(intent)
        }
    }

    protected fun getData() {

        val savedUser = Gson().fromJson<LoginUser>(activity.getSharedPreferences(LoginActivity.MY_LOGIN_PREF, Context.MODE_PRIVATE).getString(LoginActivity.MY_LOGIN_PREF_KEY, ""), LoginUser::class.java)
        val id = savedUser.salesmanId
        API.getActivitySalesIndex(id).enqueue(object : Callback<ArrayList<ActivitySalesmenIndex>> {
            override fun onResponse(call: Call<ArrayList<ActivitySalesmenIndex>>, response: Response<ArrayList<ActivitySalesmenIndex>>) {
                if (response.code() == 200) {
                    datas = response.body()
                    tempDatas = datas

                    recycler.setHasFixedSize(true)
                    recycler.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
                    recycler.setLayoutManager(LinearLayoutManager(activity))

                    recycler.setAdapter(ActivitySalesmenAdapter(tempDatas))
                }
            }

            override fun onFailure(call: Call<ArrayList<ActivitySalesmenIndex>>, t: Throwable) {
                Toast.makeText(activity, "Error", Toast.LENGTH_LONG).show()
                }
        })

    }
}
