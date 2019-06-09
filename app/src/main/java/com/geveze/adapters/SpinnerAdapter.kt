package com.geveze.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import com.geveze.R
import com.geveze.components.TextViewCustom
import com.geveze.models.Country

/**
 * Created by sametoztoprak on 23/12/2017.
 */

class SpinnerAdapter(private val mContext: Context, resource: Int, private val itms: List<Country>) : ArrayAdapter<Country>(mContext, resource, itms) {

    private val layoutInflater: LayoutInflater
    private val items: List<Country>

    init {
        layoutInflater = LayoutInflater.from(mContext)
        items = itms
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View? {
        return getRow(position, convertView, parent)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {

        return getRow(position, convertView, parent)
    }

    private fun getRow(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView = convertView
        val holder: SpinnerAdapter.ViewHolder

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_spinner_row, parent, false)

            holder = SpinnerAdapter.ViewHolder()
            holder.countryCode = convertView.findViewById<View>(R.id.countryCode) as TextViewCustom
            holder.countryName = convertView.findViewById<View>(R.id.countryName) as TextViewCustom

            convertView.tag = holder
        } else {
            holder = convertView.tag as SpinnerAdapter.ViewHolder
        }
        val country = items[position]
        holder.countryCode.text = country.countryCode
        holder.countryName.text = country.countryName
        return convertView
    }

    private class ViewHolder {
        internal lateinit var countryCode: TextViewCustom
        internal lateinit var countryName: TextViewCustom
    }
}
