package com.alfianyusufabdullah.alerttoast_kotlin

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnShortToast.setOnClickListener {
            val text = etText.text.toString()

            if (text.isEmpty()) {
                inputText.error = "Masukkan Text"
                return@setOnClickListener
            }

            hideSoftkey()

            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
            inputText.isErrorEnabled = false

        }

        btnLongToast.setOnClickListener {
            val text = etText.text.toString()

            if (text.isEmpty()) {
                inputText.error = "Masukkan Text"
                return@setOnClickListener
            }

            hideSoftkey()

            Toast.makeText(this, text, Toast.LENGTH_LONG).show()
            inputText.isErrorEnabled = false
        }
    }

    fun hideSoftkey() {
        val im = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        im.hideSoftInputFromWindow(etText.windowToken, 0)
    }
}
