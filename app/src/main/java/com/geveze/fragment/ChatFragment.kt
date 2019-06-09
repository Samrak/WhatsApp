package com.geveze.fragment

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.geveze.R
import com.geveze.adapters.HistoryChatAdapter
import com.geveze.models.Chat
import com.geveze.utilities.ImageConverter
import java.util.ArrayList


/**
 * Created by sametoztoprak on 25/09/2017.
 */

class ChatFragment : Fragment(), View.OnClickListener {

    private lateinit var chats: ArrayList<Chat>
    private lateinit var fragmentChatListView: ListView
    private lateinit var listener: OnChatWindow

    interface OnChatWindow {
        fun onChatWindowClicked(item: Chat)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        chats = ArrayList<Chat>();

        val icon = BitmapFactory.decodeResource(getResources(), R.mipmap.person)
        val bitmapdata = ImageConverter.bitmaptoByteArray(icon)

        chats.add(Chat(bitmapdata, "Samet Öztoprak", "Yesterday", "Geliyorum"))
        chats.add(Chat(bitmapdata, "Bilal Yanık", "Today", "Tamam oldu."))
        chats.add(Chat(bitmapdata, "Ali Halmaz", "Today", "Dur bakalım"))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.fragment_chat, container, false)
        fragmentChatListView = view.findViewById<View>(R.id.fragmentChatListView) as ListView

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentChatListView.adapter = HistoryChatAdapter(chats, activity!!)
        fragmentChatListView.setOnItemClickListener { parent, view, position, l ->
            var item = parent.getItemAtPosition(position) as Chat
            listener.onChatWindowClicked(item)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listener = context as ChatFragment.OnChatWindow
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.fab -> {
            }
        }
    }
}
