package com.alfianyusufabdullah.alertdialog;

import android.content.DialogInterface;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextInputLayout inputText;
    TextInputEditText etText;

    Button btnDialog, btnDialogWithButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.inputText);
        etText = findViewById(R.id.etText);

        btnDialog = findViewById(R.id.btnDialog);
        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = etText.getText().toString();

                if (text.isEmpty()) {
                    inputText.setError("Masukkan Text");
                    return;
                }

                showDialog(false, text);
                inputText.setErrorEnabled(false);
            }
        });

        btnDialogWithButton = findViewById(R.id.btnDialogWithButton);
        btnDialogWithButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = etText.getText().toString();

                if (text.isEmpty()) {
                    inputText.setError("Masukkan Text");
                    return;
                }

                showDialog(true, text);
                inputText.setErrorEnabled(false);
            }
        });
    }

    private void showDialog(boolean showButton, String Text) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("AlertDialog");
        builder.setMessage("Ini Message : " + Text);

        if (showButton) {

            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    Toast.makeText(MainActivity.this , "YES Clicked" , Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    Toast.makeText(MainActivity.this , "NO Clicked" , Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNeutralButton("CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    Toast.makeText(MainActivity.this , "CANCEL Clicked" , Toast.LENGTH_SHORT).show();
                }
            });
        }

        builder.create().show();
    }
}
