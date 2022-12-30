package com.example.projectplanner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

data class User(val dp: Int, val name: String)
class MyListAdapter(context: Context, resource: Int, objects: MutableList<User>) :
    ArrayAdapter<User>(context, resource, objects) {
    val cont = context
    val res = resource
    val data = objects

    override fun getView(position: Int, viewVar: View?, parent: ViewGroup): View {
        var view = viewVar

        if (view == null)
            view = LayoutInflater.from(cont).inflate(res, parent, false)

        val titleText = view?.findViewById(R.id.description) as TextView
        val img = view?.findViewById(R.id.imageView3) as ImageView
        titleText.text = data[position].name
        img.setImageResource(data[position].dp)

        return view!!
    }
}
