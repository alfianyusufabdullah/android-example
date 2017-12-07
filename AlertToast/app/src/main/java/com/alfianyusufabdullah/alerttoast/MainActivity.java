package com.alfianyusufabdullah.alerttoast;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnShortToast, btnLongToast;
    TextInputEditText etText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etText = findViewById(R.id.etText);

        btnShortToast = findViewById(R.id.btnShortToast);
        btnShortToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = etText.getText().toString();
                Toast.makeText(MainActivity.this , text , Toast.LENGTH_SHORT).show();

            }
        });

        btnLongToast = findViewById(R.id.btnLongToast);
        btnLongToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = etText.getText().toString();
                Toast.makeText(MainActivity.this , text , Toast.LENGTH_LONG).show();

            }
        });


    }
}
