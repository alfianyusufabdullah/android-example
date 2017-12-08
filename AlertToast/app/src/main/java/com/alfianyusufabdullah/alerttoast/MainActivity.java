package com.alfianyusufabdullah.alerttoast;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnShortToast, btnLongToast;
    TextInputEditText etText;
    TextInputLayout inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etText = findViewById(R.id.etText);

        inputText = findViewById(R.id.inputText);

        btnShortToast = findViewById(R.id.btnShortToast);
        btnShortToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = etText.getText().toString();

                if (text.isEmpty()) {
                    inputText.setError("Masukkan Text");
                    return;
                }

                hideSoftkey();

                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                inputText.setErrorEnabled(false);

            }
        });

        btnLongToast = findViewById(R.id.btnLongToast);
        btnLongToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = etText.getText().toString();
                if (text.isEmpty()) {
                    inputText.setError("Masukkan Text");
                    return;
                }

                hideSoftkey();

                Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG).show();
                inputText.setErrorEnabled(false);

            }
        });
    }

    private void hideSoftkey() {
        InputMethodManager im = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (im != null) {
            im.hideSoftInputFromWindow(etText.getWindowToken(), 0);
        }
    }
}
