package com.geveze.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.geveze.R
import com.geveze.models.Call
import com.geveze.utilities.ImageConverter
import java.util.ArrayList

/**
 * Created by sametoztoprak on 17/12/2017.
 */

class HistoryCallAdapter(private val calls: ArrayList<Call>, private val context: Context) : BaseAdapter() {

    private val layoutInflater: LayoutInflater

    init {
        layoutInflater = LayoutInflater.from(this.context)
    }

    override fun getCount(): Int {
        return calls.size
    }

    override fun getItem(position: Int): Any {
        return calls[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView = convertView

        val holder: HistoryCallAdapter.ViewHolder

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_common_person, null)
            holder = HistoryCallAdapter.ViewHolder()
            holder.itemCommonImage = convertView.findViewById<View>(R.id.itemCommonImage) as ImageView
            holder.itemCommonName = convertView.findViewById<View>(R.id.itemCommonName) as TextView
            holder.itemCommonDate = convertView.findViewById<View>(R.id.itemCommonDate) as TextView
            holder.itemCommonStatus = convertView.findViewById<View>(R.id.itemCommonStatus) as TextView
            holder.itemCommonPhone = convertView.findViewById<View>(R.id.itemCommonPhone) as ImageView
            holder.itemCommonChat = convertView.findViewById<View>(R.id.itemCommonChat) as ImageView


            convertView.tag = holder
        } else {
            holder = convertView.tag as HistoryCallAdapter.ViewHolder
        }

        holder.itemCommonDate.visibility = View.GONE
        holder.itemCommonChat.visibility = View.GONE

        val call = calls[position]

        holder.itemCommonName.text = call.name
        holder.itemCommonStatus.text = call.date

        val bitmap = ImageConverter.byteArraytoBitmap(call.image)
        holder.itemCommonImage.setImageBitmap(ImageConverter.getRoundedCornerBitmap(bitmap, 600))
        return convertView
    }

    private class ViewHolder {
        internal lateinit var itemCommonImage: ImageView
        internal lateinit var itemCommonName: TextView
        internal lateinit var itemCommonDate: TextView
        internal lateinit var itemCommonStatus: TextView
        internal lateinit var itemCommonPhone: ImageView
        internal lateinit var itemCommonChat: ImageView
    }
}