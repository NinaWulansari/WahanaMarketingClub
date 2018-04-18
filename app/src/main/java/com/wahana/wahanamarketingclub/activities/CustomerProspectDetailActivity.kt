package com.wahana.wahanamarketingclub.activities

/**
 * Created by lely
 */

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.wahana.wahanamarketingclub.R
import com.wahana.wahanamarketingclub.connect.API
import com.wahana.wahanamarketingclub.fragments.ActivitySalesmanFragment
import com.wahana.wahanamarketingclub.fragments.ProfileCustomerProspectFragment
import com.wahana.wahanamarketingclub.model.*
import kotlinx.android.synthetic.main.activity_tab_detail_customer_prospect.*
import kotlinx.android.synthetic.main.toolbar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import java.util.ArrayList

class CustomerProspectDetailActivity : AppCompatActivity() {

    var dataDetail : CustomerProspectAdd? = null

//    lateinit var id: String
    lateinit var code: String

    private val tabIcons = intArrayOf(R.drawable.ic_call_white_24dp, R.drawable.ic_person_white_36dp)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_detail_customer_prospect)

        titleSearch.setText("Customer Prospect")
        titleSearch.setTextColor(Color.WHITE)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        var id = getIntent().getStringExtra(CUST_ID)

        API.getCustomerProspectDetail(id).enqueue(object : Callback<CustomerProspectAdd> {
            override fun onResponse(call: Call<CustomerProspectAdd>?, response: Response<CustomerProspectAdd>?) {
                if (response?.code() == 200) {
                    dataDetail = response.body()
                    custCode.setText(dataDetail?.custCode)
                    child.setText(dataDetail?.custNama)
                    custAlamatTextview.setText(dataDetail?.custAlamat)
                    custHpTextview.setText(dataDetail?.custHp)
                }
            }

            override fun onFailure(call: Call<CustomerProspectAdd>?, t: Throwable?) {
                Toast.makeText(this@CustomerProspectDetailActivity, "Mohon Cek Koneksi Internet Anda", Toast.LENGTH_LONG).show()
            }

        })

        setupViewPager(viewpager)
        tabs.setupWithViewPager(viewpager)
//        setupTabIcons()
    }

//    private fun setupTabIcons() {
//        tabs.getTabAt(0)?.setIcon(tabIcons[0])
//        tabs.getTabAt(1)?.setIcon(tabIcons[1])
//    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFrag(ActivitySalesmanFragment(), "Activities")
        adapter.addFrag(ProfileCustomerProspectFragment(), "Customer Profile")
        tabs.setTabTextColors(ContextCompat.getColorStateList(this, R.color.white));
        tabs.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.white));

        viewPager.adapter = adapter
    }

    internal inner class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFrag(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence {
            return mFragmentTitleList[position]
        }
    }

    private operator fun String.invoke(toString: String) {}

    companion object {
        const val CUST_ID = "id"
//        const val CUST_NAME = "name"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}