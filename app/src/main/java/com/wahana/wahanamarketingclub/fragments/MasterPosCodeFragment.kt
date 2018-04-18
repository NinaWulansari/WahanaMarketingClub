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
import com.wahana.wahanamarketingclub.adapter.MasterPosCodeAdapter
import com.wahana.wahanamarketingclub.connect.API
import com.wahana.wahanamarketingclub.model.MasterPosCode
import kotlinx.android.synthetic.main.fragment_master_pos_code.*
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.wahana.wahanamarketingclub.R.id.recyclerView
import android.support.v7.widget.DividerItemDecoration




/**
 * A simple [Fragment] subclass.
 */
class MasterPosCodeFragment : DialogFragment() {

    lateinit var listener: (MasterPosCode?) -> Unit

    var masterPosCodes: ArrayList<MasterPosCode>? = null
    set(value) {
        field = value
        tempMasterPosCodes = value
    }

    var tempMasterPosCodes: ArrayList<MasterPosCode>? = null
        set(value) {
            (recyclerView.adapter as? MasterPosCodeAdapter)?.masterPosCodes = value
            recyclerView.adapter.notifyDataSetChanged()
        }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_master_pos_code, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        getMasterKodePos()
        searchEditText.addTextChangedListener(onSearch)
        fabClose.setOnClickListener { dialog.dismiss() }

    }

    private val onSearch: TextWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            val keyword = s.toString().toLowerCase()
            if (keyword == "") return

            val masterPosCodeResults = masterPosCodes?.filter {
                it.posKode.toLowerCase().contains(keyword)
                        || it.posLurah.toLowerCase().contains(keyword)
                        || it.posCamat.toLowerCase().contains(keyword) }

            tempMasterPosCodes = ArrayList(masterPosCodeResults ?: ArrayList())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

    }

    private fun setupRecyclerView() {

        recyclerView.adapter = MasterPosCodeAdapter(listener = { posCode ->
            listener(posCode)
            dismiss()
        })
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.addItemDecoration(DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL))
    }


    private fun getMasterKodePos() {

        API.getMasterPosCode().enqueue(object : Callback<java.util.ArrayList<MasterPosCode>> {

            override fun onResponse(call: Call<java.util.ArrayList<MasterPosCode>>, response: Response<java.util.ArrayList<MasterPosCode>>) {
                if (response.code() == 200) {
                    masterPosCodes = response.body()
                }
            }

            override fun onFailure(call: Call<java.util.ArrayList<MasterPosCode>>, throwable: Throwable) {
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
        fun createInstance(listener: (MasterPosCode?) -> Unit): MasterPosCodeFragment {
            val fragment = MasterPosCodeFragment()
            fragment.listener = listener

            return fragment
        }
    }

}// Required empty public constructor
