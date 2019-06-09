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
import com.geveze.adapters.HistoryCallAdapter
import com.geveze.models.Call
import com.geveze.utilities.ImageConverter
import java.util.ArrayList


/**
 * Created by sametoztoprak on 25/09/2017.
 */

class CallsFragment : Fragment() {

    private lateinit var calls: ArrayList<Call>
    private lateinit var fragmentCallsListView: ListView
    private lateinit var listener: CallsFragment.OnCallWindow

    interface OnCallWindow {
        fun onCallWindowClicked(item: Call)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        calls = ArrayList<Call>();

        val icon = BitmapFactory.decodeResource(getResources(), R.mipmap.person)
        val bitmapdata = ImageConverter.bitmaptoByteArray(icon)

        calls.add(Call(bitmapdata, "Samet Öztoprak", "Yesterday,14:34"))
        calls.add(Call(bitmapdata, "Samet Öztoprak", "Today, 12:11"))
        calls.add(Call(bitmapdata, "Samet Öztoprak", "Today, 17:03"))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.fragment_calls, container, false)
        fragmentCallsListView = view.findViewById<View>(R.id.fragmentCallsListView) as ListView

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentCallsListView.adapter = HistoryCallAdapter(calls, activity!!)
        fragmentCallsListView.setOnItemClickListener { parent, view, position, l ->
            var item = parent.getItemAtPosition(position) as Call
            listener.onCallWindowClicked(item)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listener = context as CallsFragment.OnCallWindow
    }
}