package com.wahana.wahanamarketingclub.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.wahana.wahanamarketingclub.R
import com.wahana.wahanamarketingclub.model.MasterPosCode

/**
 * Created by Lely
 */
class MasterPosCodeAdapter(var masterPosCodes: List<MasterPosCode>? = null, val listener: ((MasterPosCode?) -> Unit)? = null): RecyclerView.Adapter<MasterPosCodeAdapter.MasterPosCodeViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MasterPosCodeViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.row_master_pos_code, parent, false)
        return MasterPosCodeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return masterPosCodes?.size ?: 0
    }

    override fun onBindViewHolder(holder: MasterPosCodeViewHolder?, position: Int) {
        holder?.masterPosCode = masterPosCodes?.get(position)
    }

    inner class MasterPosCodeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var masterPosCode: MasterPosCode? = null
        set(value) {
            itemView.findViewById<TextView>(R.id.posCodeTextView).text = value?.posKode
            itemView.findViewById<TextView>(R.id.kecamatanTextView).text = value?.posCamat
            itemView.findViewById<TextView>(R.id.kelurahanTextView).text = value?.posLurah
            itemView.setOnClickListener { listener?.invoke(value) }
        }

    }
}