package com.alfianyusufabdullah.alertdialog_kotlin

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDialog.setOnClickListener {

            val text = etText.text.toString()

            if (text.isEmpty()) {
                inputText.error = "Masukkan Text"
                return@setOnClickListener
            }

            showDialogs(false, text)
            inputText.isErrorEnabled = false
        }

        btnDialogWithButton.setOnClickListener {
            val text = etText.text.toString()

            if (text.isEmpty()) {
                inputText.error = "Masukkan Text"
                return@setOnClickListener
            }

            showDialogs(true, text)
            inputText.isErrorEnabled = false
        }


    }

    fun showDialogs(showButton: Boolean, text: String) {

        hideSoftkey()

        val builder = AlertDialog.Builder(this)
        builder.setTitle("AlertDialog")
        builder.setMessage(text)

        if (showButton) {

            builder.setPositiveButton("YES") { _, _ ->
                Toast.makeText(this@MainActivity, "YES Clicked", Toast.LENGTH_SHORT).show()
            }

            builder.setNegativeButton("NO") { _, _ ->
                Toast.makeText(this@MainActivity, "NO Clicked", Toast.LENGTH_SHORT).show()
            }

            builder.setNeutralButton("CANCEL") { _, _ ->
                Toast.makeText(this@MainActivity, "CANCEL Clicked", Toast.LENGTH_SHORT).show()
            }
        }

        builder.create().show()

    }

    fun hideSoftkey(){
        val im = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        im.hideSoftInputFromWindow(etText.windowToken , 0)
    }
}
