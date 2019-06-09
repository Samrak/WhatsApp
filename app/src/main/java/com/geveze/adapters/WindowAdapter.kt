package com.geveze.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.geveze.R

import com.geveze.models.Message

import java.util.ArrayList

/**
 * Created by sametoztoprak on 17/12/2017.
 */

class WindowAdapter(private val messages: ArrayList<Message>, private val context: Context) : BaseAdapter() {

    private var chatText: TextView? = null
    private val layoutInflater: LayoutInflater

    init {
        layoutInflater = LayoutInflater.from(this.context)

    }

    override fun getCount(): Int {
        return messages.size
    }

    override fun getItem(position: Int): Message {
        return messages[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val chatMessageObj = getItem(position)
        var row = convertView
        val inflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        if (chatMessageObj.isLeft) {
            row = inflater.inflate(R.layout.item_chat_right_row, parent, false)
        } else {
            row = inflater.inflate(R.layout.item_chat_left_row, parent, false)
        }
        chatText = row.findViewById<View>(R.id.message) as TextView
        chatText!!.text = chatMessageObj.message
        return row
    }
}