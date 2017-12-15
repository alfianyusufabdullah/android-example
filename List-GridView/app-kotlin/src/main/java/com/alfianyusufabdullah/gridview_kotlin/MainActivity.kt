package com.alfianyusufabdullah.gridview_kotlin

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.GridView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = AdapterAndroidVersion(this, 0, getAndroid())

        val grid = findViewById<GridView>(R.id.mainGrid)
        grid.adapter = adapter
        grid.setOnItemClickListener { _, _, position, _ ->

            val android = getAndroid()[position].codeName
            Snackbar.make(findViewById<View>(R.id.rootView) , android , Snackbar.LENGTH_SHORT).show()

        }
    }

    private fun getAndroid(): MutableList<ModelAndroidVersion> {

        val data: MutableList<ModelAndroidVersion> = ArrayList()
        data.add(ModelAndroidVersion("Oreo", "8.1", "API 27"))
        data.add(ModelAndroidVersion("Oreo", "8.0", "API 26"))
        data.add(ModelAndroidVersion("Nougat", "7.1", "API 25"))
        data.add(ModelAndroidVersion("Nougat", "7.0", "API 24"))
        data.add(ModelAndroidVersion("Marshmallow", "6.0", "API 23"))
        data.add(ModelAndroidVersion("Lollipop", "5.1", "API 22"))
        data.add(ModelAndroidVersion("Lollipop", "5.0", "API 21"))
        data.add(ModelAndroidVersion("Kitkat", "4.4", "API 19"))
        data.add(ModelAndroidVersion("Jelly Bean", "4.3", "API 18"))
        data.add(ModelAndroidVersion("Jelly Bean", "4.2", "API 17"))
        data.add(ModelAndroidVersion("Jelly Bean", "4.1", "API 16"))
        data.add(ModelAndroidVersion("Ice Cream Sandwich", "4.0", "API 15"))
        data.add(ModelAndroidVersion("Gingerbread", "2.3", "API 10"))

        return data
    }
}
