package com.geveze.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.geveze.R
import com.geveze.models.Chat
import com.geveze.utilities.ImageConverter
import java.util.ArrayList

/**
 * Created by sametoztoprak on 17/12/2017.
 */

class HistoryChatAdapter(private val chats: ArrayList<Chat>, private val context: Context) : BaseAdapter() {

    private val layoutInflater: LayoutInflater

    init {
        layoutInflater = LayoutInflater.from(this.context)
    }

    override fun getCount(): Int {
        return chats.size
    }

    override fun getItem(position: Int): Any {
        return chats[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView = convertView

        val holder: HistoryChatAdapter.ViewHolder

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_common_person, null)
            holder = HistoryChatAdapter.ViewHolder()
            holder.itemCommonImage = convertView.findViewById<View>(R.id.itemCommonImage) as ImageView
            holder.itemCommonName = convertView.findViewById<View>(R.id.itemCommonName) as TextView
            holder.itemCommonDate = convertView.findViewById<View>(R.id.itemCommonDate) as TextView
            holder.itemCommonStatus = convertView.findViewById<View>(R.id.itemCommonStatus) as TextView
            holder.itemCommonIconLayout = convertView.findViewById<View>(R.id.itemCommonIconLayout) as LinearLayout
            convertView.tag = holder
        } else {
            holder = convertView.tag as HistoryChatAdapter.ViewHolder
        }

        holder.itemCommonIconLayout.visibility = View.GONE

        val chat = chats[position]
        holder.itemCommonName.text = chat.name
        holder.itemCommonDate.text = chat.date
        holder.itemCommonStatus.text = chat.sentence

        val bitmap = ImageConverter.byteArraytoBitmap(chat.image)
        holder.itemCommonImage.setImageBitmap(ImageConverter.getRoundedCornerBitmap(bitmap,600))
        return convertView
    }

    class ViewHolder {
        internal lateinit var itemCommonImage: ImageView
        internal lateinit var itemCommonName: TextView
        internal lateinit var itemCommonDate: TextView
        internal lateinit var itemCommonStatus: TextView
        internal lateinit var itemCommonIconLayout: LinearLayout
    }
}