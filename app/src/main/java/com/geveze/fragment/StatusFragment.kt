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
import com.geveze.adapters.HistoryStatusAdapter
import com.geveze.models.Chat
import com.geveze.models.Status
import com.geveze.utilities.ImageConverter
import java.util.ArrayList


/**
 * Created by sametoztoprak on 25/09/2017.
 */

class StatusFragment : Fragment() {

    private lateinit var statuses: ArrayList<Status>
    private lateinit var fragmentStatusListView: ListView
    //private lateinit var listener: StatusFragment.OnStatusWindow

    interface OnStatusWindow {
        fun onStatusWindowClicked(item: Chat)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val icon = BitmapFactory.decodeResource(getResources(), R.mipmap.person)
        val bitmapdata = ImageConverter.bitmaptoByteArray(icon)

        statuses = ArrayList<Status>();
        statuses.add(Status(bitmapdata, "Samet Öztoprak", "Yesterday", "güzle sözler yaz"))
        statuses.add(Status(bitmapdata, "Bilal Yanık", "Today", "Ne olacak bu işler"))
        statuses.add(Status(bitmapdata, "Ali Halmaz", "Today", "Dur bakalım!"))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.fragment_status, container, false)
        fragmentStatusListView = view.findViewById<View>(R.id.fragmentStatusListView) as ListView

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentStatusListView.adapter = HistoryStatusAdapter(statuses, activity!!)
        fragmentStatusListView.setOnItemClickListener { parent, view, position, l ->
            var item = parent.getItemAtPosition(position) as Status
           // listener.onCallWindowClicked(item)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
       // listener = context as StatusFragment.OnStatusWindow
    }

}