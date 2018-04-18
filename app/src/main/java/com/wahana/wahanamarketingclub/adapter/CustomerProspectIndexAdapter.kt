package com.wahana.wahanamarketingclub.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SectionIndexer

import com.amulyakhare.textdrawable.TextDrawable
import com.intrusoft.sectionedrecyclerview.SectionRecyclerViewAdapter
import com.wahana.wahanamarketingclub.R
import com.wahana.wahanamarketingclub.model.CustomerProspectIndex
import com.wahana.wahanamarketingclub.model.SectionHeader

import java.util.ArrayList
import android.R.attr.data
import android.text.method.TextKeyListener.clear
import android.util.TypedValue

/**
 * Created by lely
 */

class CustomerProspectIndexAdapter
(internal var context: Context, private val sectionItemList: ArrayList<SectionHeader>?, private val listener: ((CustomerProspectIndex) -> Unit)? = null) :
        SectionRecyclerViewAdapter<SectionHeader, CustomerProspectIndex, SectionViewHolder, ChildViewHolder>(context, sectionItemList), SectionIndexer {
    private var mSectionPositions: ArrayList<Int>? = null
    val res = context.getResources()

    override fun onCreateSectionViewHolder(sectionViewGroup: ViewGroup, viewType: Int): SectionViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.activity_customer_prospect_row_header, sectionViewGroup, false)
        return SectionViewHolder(view)
    }

    override fun onCreateChildViewHolder(childViewGroup: ViewGroup, viewType: Int): ChildViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.activity_customer_prospect_row, childViewGroup, false)
        return ChildViewHolder(view)
    }

    override fun onBindSectionViewHolder(sectionViewHolder: SectionViewHolder, sectionPosition: Int, section: SectionHeader) {
        sectionViewHolder.name.text = section.sectionText
//        sectionViewHolder.name.setTextSize(TypedValue.COMPLEX_UNIT_PX, res.getDimension(R.dimen.textsize_14sp))
    }

    override fun onBindChildViewHolder(childViewHolder: ChildViewHolder, sectionPosition: Int, childPosition: Int, child: CustomerProspectIndex) {
        val name = child.name
        val alamat = child.alamat
        val hp = child.no_hp
        val status_profile = child.status_profile

        childViewHolder.name.text = name
//        childViewHolder.name.setTextSize(TypedValue.COMPLEX_UNIT_PX, res.getDimension(R.dimen.textsize_medium))

        childViewHolder.alamat.text = alamat
//        childViewHolder.alamat.setTextSize(TypedValue.COMPLEX_UNIT_PX, res.getDimension(R.dimen.textsize_small))

        childViewHolder.no_hp.text = hp
//        childViewHolder.no_hp.setTextSize(TypedValue.COMPLEX_UNIT_PX, res.getDimension(R.dimen.textsize_small))

        childViewHolder.status.text = status_profile
//        childViewHolder.name.setTextSize(TypedValue.COMPLEX_UNIT_PX, res.getDimension(R.dimen.textsize_medium))

        if (status_profile == "HOT") {
            childViewHolder.status.setTextColor(context.resources.getColor(R.color.colorPrimary))
        } else if (status_profile == "WARM") {
            childViewHolder.status.setTextColor(context.resources.getColor(R.color.blue_700))
        } else {
            childViewHolder.status.setTextColor(context.resources.getColor(R.color.green_700))
        }

        val word = name.substring(0, 1)
        val drawable = TextDrawable.builder()
                .beginConfig()
                .textColor(Color.WHITE)
                .fontSize((16 * res.displayMetrics.density).toInt()) /* size in px */
                .bold()
                .toUpperCase()
                .endConfig()
                .buildRoundRect(word, Color.parseColor("#616161"), 60)
        childViewHolder.icon.setImageDrawable(drawable)

        childViewHolder.itemView.setOnClickListener { listener?.invoke(child) }
    }

    override fun getSectionForPosition(position: Int): Int {
        return 0
    }

    override fun getSections(): Array<String> {
        val sections = ArrayList<String>(26)
        mSectionPositions = ArrayList(26)
        var total = 0
        sectionItemList?.let {
            for (i in sectionItemList.indices) {
                val sectionHeader = sectionItemList[i]
                val section = sectionHeader.sectionText[0].toString().toUpperCase()
                if (!sections.contains(section)) {
                    sections.add(section)
                    mSectionPositions!!.add(total)
                }
                total += 1 + sectionItemList[i].childItems.size
            }
        }
        return sections.toTypedArray<String>()
    }

    override fun getPositionForSection(sectionIndex: Int): Int {
        return mSectionPositions!![sectionIndex]
    }


    fun swap(datas: ArrayList<SectionHeader>?) {
        if (datas == null || datas.size == 0)
            return
        if (sectionItemList != null && sectionItemList.size > 0)
            sectionItemList.clear()
        sectionItemList?.addAll(datas)
        notifyDataChanged(sectionItemList)

    }

}
