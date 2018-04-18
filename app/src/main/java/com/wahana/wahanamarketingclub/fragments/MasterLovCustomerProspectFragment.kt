package com.wahana.wahanamarketingclub.fragments
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast

import com.wahana.wahanamarketingclub.R
import com.wahana.wahanamarketingclub.connect.API
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.wahana.wahanamarketingclub.R.id.recyclerView
import android.support.v7.widget.DividerItemDecoration
import com.wahana.wahanamarketingclub.adapter.MasterLovCustomerProspectAdapter
import com.wahana.wahanamarketingclub.model.MasterLovCustomers
import kotlinx.android.synthetic.main.fragment_lov_customer_prospect.*

/**
 * A simple [Fragment] subclass.
 */
class MasterLovCustomerProspectFragment : DialogFragment() {

    lateinit var listener: (MasterLovCustomers?) -> Unit

    var masterLovCustomers: ArrayList<MasterLovCustomers>? = null
        set(value) { 
            field = value
            tempMasterLovCustomers = value
        }

    var tempMasterLovCustomers: ArrayList<MasterLovCustomers>? = null
        set(value) {
            (recyclerView.adapter as? MasterLovCustomerProspectAdapter)?.masterCustomerProspects = value
            recyclerView.adapter.notifyDataSetChanged()
        }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_lov_customer_prospect, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        getMasterLovCustomers()
        searchEditText.addTextChangedListener(onSearch)
        fabClose.setOnClickListener { dialog.dismiss() }

    }

    private val onSearch: TextWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            val keyword = s.toString().toLowerCase()
            if (keyword == "") return

            val masterLovCustomersResults = masterLovCustomers?.filter {
                it.custNama.toLowerCase().contains(keyword)
                        || it.custAlamat.toLowerCase().contains(keyword)
                        || it.custHp.toLowerCase().contains(keyword) }

            tempMasterLovCustomers = ArrayList(masterLovCustomersResults ?: ArrayList())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

    }

    private fun setupRecyclerView() {

        recyclerView.adapter = MasterLovCustomerProspectAdapter(listener = { customerProspect ->
            listener(customerProspect)
            dismiss()
        })
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.addItemDecoration(DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL))
    }


    private fun getMasterLovCustomers() {

        API.getLovCustomerProspect().enqueue(object : Callback<java.util.ArrayList<MasterLovCustomers>> {

            override fun onResponse(call: Call<java.util.ArrayList<MasterLovCustomers>>, response: Response<java.util.ArrayList<MasterLovCustomers>>) {
                if (response.code() == 200) {
                    masterLovCustomers = response.body()
                }
            }

            override fun onFailure(call: Call<java.util.ArrayList<MasterLovCustomers>>, throwable: Throwable) {
                Toast.makeText(context, "Mohon Cek Koneksi Internet Anda", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = (activity.resources.displayMetrics.heightPixels*0.70).toInt()
            dialog.window.setLayout(width, height)
        }
    }


    companion object {
        fun createInstance(listener: (MasterLovCustomers?) -> Unit): MasterLovCustomerProspectFragment {
            val fragment = MasterLovCustomerProspectFragment()
            fragment.listener = listener

            return fragment
        }
    }

}// Required empty public constructor
