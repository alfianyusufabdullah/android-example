package com.alfianyusufabdullah.alertsnackbar;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextInputEditText etText;
    TextInputLayout inputLayout;

    Button btnSnackbar, btnSanckbarWithButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etText = findViewById(R.id.etText);

        inputLayout = findViewById(R.id.inputText);

        btnSnackbar = findViewById(R.id.btnSnackbar);
        btnSnackbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = etText.getText().toString();

                if (text.isEmpty()) {
                    inputLayout.setError("Masukkan Text");
                    return;
                }

                showSnackBar(false, text);
                inputLayout.setErrorEnabled(false);

            }
        });

        btnSanckbarWithButton = findViewById(R.id.btnSnackbarWithButton);
        btnSanckbarWithButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = etText.getText().toString();

                if (text.isEmpty()) {
                    inputLayout.setError("Masukkan Text");
                    return;
                }

                showSnackBar(true, text);
                inputLayout.setErrorEnabled(false);
            }
        });

    }

    private void showSnackBar(boolean showButton, String text) {

        hideSoftkey();

        Snackbar snackbar = Snackbar.make(findViewById(R.id.rootView), text, Snackbar.LENGTH_SHORT);

        if (showButton) {
            snackbar.setAction("OK", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
                }
            });
        }

        snackbar.show();
    }

    private void hideSoftkey() {
        InputMethodManager im = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (im != null) {
            im.hideSoftInputFromWindow(etText.getWindowToken(), 0);
        }
    }
}
