package com.alfianyusufabdullah.gridview_kotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

/**
 * Created by jonesrandom on 12/15/17.
 *
 *
 * AA
 */

class AdapterAndroidVersion(context: Context, resource: Int, objects: MutableList<ModelAndroidVersion>) : ArrayAdapter<ModelAndroidVersion>(context, resource, objects) {

    private val data: MutableList<ModelAndroidVersion> = objects

    override fun getCount(): Int {
        return data.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {

        var view = convertView
        val holder: GridHolder

        if (view == null) {

            view = LayoutInflater.from(parent.context).inflate(R.layout.row_android_version, parent, false)

            holder = GridHolder()
            holder.version = view.findViewById(R.id.rowVer)
            holder.codeName = view.findViewById(R.id.rowCodeName)
            holder.apiLevel = view.findViewById(R.id.rowLevel)

            view.tag = holder
        } else {
            holder = view.tag as GridHolder
        }

        val android = data[position]

        holder.apiLevel.text = android.apiLevel
        holder.codeName.text = android.codeName
        holder.version.text = android.version

        return view
    }

    class GridHolder {
        lateinit var version: TextView
        lateinit var codeName: TextView
        lateinit var apiLevel: TextView
    }
}
