package com.example.android.android_import_it_application.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.android.android_import_it_application.R

class MessageAdapter(context: Context, messages: ArrayList<String>) :
    ArrayAdapter<String>(context, R.layout.item_message, messages) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        val message = getItem(position)

        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_message, parent, false)
        }

        val textViewMessage: TextView = itemView!!.findViewById(R.id.textViewMessage)
        textViewMessage.text = message

        return itemView
    }
}