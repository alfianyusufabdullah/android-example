package com.alfianyusufabdullah.alertdialog_kotlin

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSnackbar.setOnClickListener {

            val text = etText.text.toString()

            if (text.isEmpty()) {
                inputText.error = "Masukkan Text"
                return@setOnClickListener
            }

            showSnackbar(false, text)
            inputText.isErrorEnabled = false

        }

        btnSnackbarWithButton.setOnClickListener {
            val text = etText.text.toString()

            if (text.isEmpty()) {
                inputText.error = "Masukkan Text"
                return@setOnClickListener
            }

            showSnackbar(true, text)
            inputText.isErrorEnabled = false
        }
    }

    fun showSnackbar(showButton: Boolean, text: String) {

        hideSoftkey()

        val snackbar = Snackbar.make(findViewById<View>(R.id.rootView), text, Snackbar.LENGTH_SHORT);
        if (showButton) {
            snackbar.setAction("OK", {
                Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show()
            })
        }

        snackbar.show()

    }

    fun hideSoftkey() {
        val iml = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        iml.hideSoftInputFromWindow(etText.windowToken, 0)
    }
}
