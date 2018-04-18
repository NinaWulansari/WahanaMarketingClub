package com.wahana.wahanamarketingclub.activities

/**
 * Created by lely
 */

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.gson.Gson
import java.util.ArrayList
import com.wahana.wahanamarketingclub.R
import com.wahana.wahanamarketingclub.activities.CustomerProspectDetailActivity.Companion.CUST_ID
import com.wahana.wahanamarketingclub.connect.API
import com.wahana.wahanamarketingclub.model.CustomerProspectIndex
import com.wahana.wahanamarketingclub.utils.AlphabetItem
import kotlinx.android.synthetic.main.activity_customer_prospect_index.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.wahana.wahanamarketingclub.adapter.CustomerProspectIndexAdapter
import com.wahana.wahanamarketingclub.model.LoginUser
import com.wahana.wahanamarketingclub.model.NotifActivitySalesman
import com.wahana.wahanamarketingclub.model.SectionHeader
import com.wahana.wahanamarketingclub.utils.RecyclerItemClickListener
import kotlinx.android.synthetic.main.toolbar_search.*

class CustomerProspectIndexActivity : AppCompatActivity() {

    val CUST_NAMA = "name"
    val CUST_ALAMAT = "alamat"
    private var searchActive = false

    private var mDataArray: List<String>? = null
    private var mDataCustomers: ArrayList<SectionHeader>? = null
    private var mAlphabetItems: MutableList<AlphabetItem>? = null
    var datas: List<CustomerProspectIndex>? = null
    var tempDatas: List<CustomerProspectIndex>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_prospect_index)
        initialiseData()
//        getDataNotif()

        swipeRefreshLayout.setOnRefreshListener { initialiseData() }
        //Set Toolbar
        titleSearch.setText("Customer Prospect")
        titleSearch.setTextColor(Color.WHITE)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        fast_scroller_recycler.setOnClickListener {
            fun onItemClick(position: Int, view: View){
                val intent = Intent(this@CustomerProspectIndexActivity, CustomerProspectDetailActivity::class.java)
                startActivity(intent)
                startActivity(intent)
            }

        }

        fab.setOnClickListener {
            val intent = Intent(this@CustomerProspectIndexActivity, CustomerProspectAddActivity::class.java)
            startActivity(intent)
        }

        search_button.setOnClickListener{
            searchActive = !searchActive
            if (searchActive) {
                search_edittext.requestFocus()
                val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.showSoftInput(search_edittext, 0)
            } else {
                if (search_edittext.getText().toString().length > 0) {
                    search_edittext.setText("")
                    searchActive = !searchActive
                }
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(search_edittext.getWindowToken(), 0)
            }
            search_button.setImageResource(if (searchActive) R.drawable.ic_close_white_24dp else R.drawable.ic_search_white_24dp)
            search_edittext.setVisibility(if (searchActive) View.VISIBLE else View.GONE)
            titleSearch.setVisibility(if (searchActive) View.INVISIBLE else View.VISIBLE)
        }

        search_edittext.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
//                 val adapterIndex = CustomerProspectIndexAdapter(this@CustomerProspectIndexActivity,datas,)
                val data = ArrayList<SectionHeader>()

                val filterResult = datas?.filter {
                    val nameSearch = it.name.toLowerCase()
                    val alamatSearch = it.alamat.toLowerCase()
                    val hpSearch = it.no_hp.toLowerCase()
                    nameSearch.contains(s.toString().toLowerCase()) || alamatSearch.contains(s.toString().toLowerCase())
                            ||  hpSearch.contains(s.toString().toLowerCase())
                }

                filterResult?.forEach { customerProspectIndex ->
                    val dataOnArray = data.firstOrNull {
                        it.sectionText.toUpperCase() == customerProspectIndex.name.first().toString().toUpperCase()
                    }
                    val isFoundOnArray = dataOnArray != null
                    if (!isFoundOnArray) {
                        val childs = ArrayList<CustomerProspectIndex>()
                        childs.add(customerProspectIndex)
                        data.add(SectionHeader(childs, customerProspectIndex.name.toUpperCase().first().toString()))
                    } else {
                        dataOnArray?.childItems?.add(customerProspectIndex)
                    }
                }

                (fast_scroller_recycler.adapter as? CustomerProspectIndexAdapter)?.swap(data)

            }

            override fun afterTextChanged(s: Editable) {

            }
        })


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    protected fun initialiseData() {
        //Recycler view data

        val savedUser = Gson().fromJson<LoginUser>(this@CustomerProspectIndexActivity.getSharedPreferences(LoginActivity.MY_LOGIN_PREF, Context.MODE_PRIVATE).getString(LoginActivity.MY_LOGIN_PREF_KEY, ""), LoginUser::class.java)
        val id = savedUser.salesmanId
        API.getCustomerProspectIndex(id).enqueue(object : Callback<ArrayList<CustomerProspectIndex>> {

            override fun onResponse(call: Call<ArrayList<CustomerProspectIndex>>, response: Response<ArrayList<CustomerProspectIndex>>) {
                if (response.code() == 200) {
                    datas = response.body()

                    mDataArray = datas?.map { it.name }


                    val data = ArrayList<SectionHeader>()
                    datas?.forEach { customerProspectIndex ->
                        val dataOnArray = data.firstOrNull { it.sectionText.toUpperCase() == customerProspectIndex.name.first().toString().toUpperCase() }
                        val isFoundOnArray = dataOnArray != null
                        if (!isFoundOnArray) {
                            val childs = ArrayList<CustomerProspectIndex>()
                            childs.add(customerProspectIndex)
                            data.add(SectionHeader(childs, customerProspectIndex.name.toUpperCase().first().toString()))
                        } else {
                            dataOnArray?.childItems?.add(customerProspectIndex)
                        }
                    }
                    mDataCustomers = data

                    //Alphabet fast scroller data
                    mAlphabetItems = ArrayList<AlphabetItem>()
                    val strAlphabets = ArrayList<String>()

                    mDataArray?.let {

                        it.forEachIndexed { i, name ->
                            if (name.trim { it <= ' ' }.isEmpty()) {
                                return
                            }

                            val word = name.substring(0, 1)
                            if (!strAlphabets.contains(word)) {
                                strAlphabets.add(word)
                                mAlphabetItems?.add(AlphabetItem(i, word, false))
                            }
                        }
                    }
                    initialiseUI()
                    Log.i("wahanaapp", "LelyCantik: " + datas)
//                    recyclerView.setAdapter(AdminIndexAdapter(datas))
                } else {

                    Toast.makeText(this@CustomerProspectIndexActivity, "Data Kosong", Toast.LENGTH_LONG).show()
                }
                swipeRefreshLayout.setRefreshing(false)
            }

            override fun onFailure(call: Call<ArrayList<CustomerProspectIndex>>, throwable: Throwable) {
                Toast.makeText(this@CustomerProspectIndexActivity, "Error", Toast.LENGTH_LONG).show()
                swipeRefreshLayout.setRefreshing(false)
            }
        })

    }

    protected fun initialiseUI() {
        fast_scroller_recycler.layoutManager = LinearLayoutManager(this)

        fast_scroller_recycler.adapter = CustomerProspectIndexAdapter(this, mDataCustomers, onClickCustomer)// RecyclerViewAdapter(mDataArray)
//        fast_scroller_recycler.setIndexTextSize(14)
//        fast_scroller_recycler.setIndexBarColor(R.color.grey_900)
//        fast_scroller_recycler.setIndexBarCornerRadius(2)
////        fast_scroller_recycler.setIndexBarTransparentValue(0.4.toFloat())
//        fast_scroller_recycler.setIndexbarMargin(2F)
////        fast_scroller_recycler.setIndexbarWidth(40F)
        fast_scroller_recycler.setIndexBarTextColor(R.color.black_1000)
        fast_scroller_recycler.setIndexBarVisibility(true)
        fast_scroller_recycler.setIndexbarHighLateTextColor(R.color.colorPrimary)
        fast_scroller_recycler.setIndexBarHighLateTextVisibility(true)

    }

    private val onClickCustomer: (CustomerProspectIndex) -> Unit = {
        val intent = Intent(this@CustomerProspectIndexActivity, CustomerProspectDetailActivity::class.java)
        intent.putExtra(CUST_ID, it.id)
        startActivity(intent)
    }

    companion object {
        const val CUST_ID = "id"
        const val CUST_NAME = "name"
    }


//    fun getDataNotif(){
//        val savedUser = Gson().fromJson<LoginUser>(this@CustomerProspectIndexActivity.getSharedPreferences(LoginActivity.MY_LOGIN_PREF, Context.MODE_PRIVATE).getString(LoginActivity.MY_LOGIN_PREF_KEY, ""), LoginUser::class.java)
//        val salesman_id = savedUser.salesmanId
//        API.notificationActivitySalesman(salesman_id).enqueue(object : Callback<NotifActivitySalesman>{
//            override fun onResponse(call: Call<NotifActivitySalesman>?, response: Response<NotifActivitySalesman>?) {
//                val datas = response?.body()
//                val title = datas?.activitySubject
//                Toast.makeText(this@CustomerProspectIndexActivity, title, Toast.LENGTH_LONG).show()
//            }
//            override fun onFailure(call: Call<NotifActivitySalesman>?, t: Throwable?) {
//
//                Toast.makeText(this@CustomerProspectIndexActivity, "error", Toast.LENGTH_LONG).show()
//            }
//        })
//    }
}