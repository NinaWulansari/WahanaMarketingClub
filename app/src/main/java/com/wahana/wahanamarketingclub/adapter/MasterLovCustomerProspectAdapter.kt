package com.wahana.wahanamarketingclub.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.wahana.wahanamarketingclub.R
import com.wahana.wahanamarketingclub.model.MasterLovCustomers

/**
 * Created by Lely
 */

class MasterLovCustomerProspectAdapter(var masterCustomerProspects: List<MasterLovCustomers>? = null,
                                       val listener: ((MasterLovCustomers?) -> Unit)? = null):
        RecyclerView.Adapter<MasterLovCustomerProspectAdapter.MasterCustomerProspectViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MasterCustomerProspectViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.row_lov_customer_prospect, parent, false)
        return MasterCustomerProspectViewHolder(view)
    }
    
    override fun getItemCount(): Int {
        return masterCustomerProspects?.size ?: 0
    }

    override fun onBindViewHolder(holder: MasterCustomerProspectViewHolder?, position: Int) {
        holder?.masterCustomerProspect = masterCustomerProspects?.get(position)
    }

    inner class MasterCustomerProspectViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var masterCustomerProspect: MasterLovCustomers? = null
            set(value) {
                itemView.findViewById<TextView>(R.id.nameCustTextView).text = value?.custNama
                itemView.findViewById<TextView>(R.id.alamatCustTextView).text = value?.custAlamat
                itemView.findViewById<TextView>(R.id.hpCustTextView).text = value?.custHp
                itemView.setOnClickListener {
                    listener?.invoke(value)
                }
            }

    }
}
