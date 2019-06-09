package com.geveze.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView

import com.geveze.R
import com.geveze.components.TextViewCustom
import com.geveze.models.Friend
import com.geveze.utilities.ImageConverter

import java.util.ArrayList

/**
 * Created by sametoztoprak on 22/10/2017.
 */

class FriendAdapter(private val friends: ArrayList<Friend>, private val isChat: Boolean, private val context: Context) : BaseAdapter() {

    private val layoutInflater: LayoutInflater

    init {
        layoutInflater = LayoutInflater.from(this.context)
    }

    override fun getCount(): Int {
        return friends.size
    }

    override fun getItem(position: Int): Any {
        return friends[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView = convertView

        val holder: FriendAdapter.ViewHolder

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_common_person, null)
            holder = FriendAdapter.ViewHolder()
            holder.itemCommonImage = convertView.findViewById<View>(R.id.itemCommonImage) as ImageView
            holder.itemCommonName = convertView.findViewById<View>(R.id.itemCommonName) as TextViewCustom
            holder.itemCommonDate = convertView.findViewById<View>(R.id.itemCommonDate) as TextViewCustom
            holder.itemCommonStatus = convertView.findViewById<View>(R.id.itemCommonStatus) as TextViewCustom
            holder.itemCommonPhone = convertView.findViewById<View>(R.id.itemCommonPhone) as ImageView
            holder.itemCommonChat = convertView.findViewById<View>(R.id.itemCommonChat) as ImageView

            convertView.tag = holder
        } else {
            holder = convertView.tag as FriendAdapter.ViewHolder
        }

        holder.itemCommonDate.visibility = View.GONE

        setChat(holder, isChat)

        val friend = friends[position]

        holder.itemCommonName.text = friend.name
        holder.itemCommonStatus.text = friend.status

        val bitmap = ImageConverter.byteArraytoBitmap(friend.image)
        holder.itemCommonImage.setImageBitmap(ImageConverter.getRoundedCornerBitmap(bitmap, 600))
        return convertView
    }

    private fun setChat(holder: FriendAdapter.ViewHolder, visibility: Boolean) {
        holder.itemCommonPhone.visibility = if (visibility) View.GONE else View.VISIBLE
        holder.itemCommonChat.visibility = if (visibility) View.VISIBLE else View.GONE
    }

    private class ViewHolder {
        internal lateinit var itemCommonImage: ImageView
        internal lateinit var itemCommonName: TextViewCustom
        internal lateinit var itemCommonDate: TextViewCustom
        internal lateinit var itemCommonStatus: TextViewCustom
        internal lateinit var itemCommonPhone: ImageView
        internal lateinit var itemCommonChat: ImageView
    }
}