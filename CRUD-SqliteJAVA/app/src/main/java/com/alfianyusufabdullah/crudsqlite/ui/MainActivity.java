package com.alfianyusufabdullah.crudsqlite.ui;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alfianyusufabdullah.crudsqlite.R;
import com.alfianyusufabdullah.crudsqlite.database.DatabaseCallback;
import com.alfianyusufabdullah.crudsqlite.database.DatabaseHelper;
import com.alfianyusufabdullah.crudsqlite.model.ModelMahasiswa;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout inputNama, inputNim, inputSemester;
    private TextInputEditText editNama, editNim, editSemester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {

        inputNama = findViewById(R.id.inputNama);
        inputNim = findViewById(R.id.inputNim);
        inputSemester = findViewById(R.id.inputSemester);

        editNama = findViewById(R.id.editNama);
        editNim = findViewById(R.id.editNim);
        editSemester = findViewById(R.id.editSemester);

        editNama.addTextChangedListener(myWatcher(inputNama));
        editNim.addTextChangedListener(myWatcher(inputNim));
        editSemester.addTextChangedListener(myWatcher(inputSemester));

        Button btnLihatData = findViewById(R.id.btnLiatData);
        Button btnTambahData = findViewById(R.id.btnTambahData);

        btnLihatData.setOnClickListener(this);
        btnTambahData.setOnClickListener(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("CRUD SQLite");

    }

    private TextWatcher myWatcher(final TextInputLayout textInputLayout) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                textInputLayout.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btnTambahData) {

            String Nama = editNama.getText().toString();
            String Nim = editNim.getText().toString();
            String Semester = editSemester.getText().toString();

            if (Nama.isEmpty()) {
                inputNama.setError("Masukkan Nama Mahasiswa");
                editNama.requestFocus();
                return;
            }

            if (Nim.isEmpty()) {
                inputNim.setError("Masukkan Nomor Induk Mahasiswa");
                editNim.requestFocus();
                return;
            }

            if (Semester.isEmpty()) {
                inputSemester.setError("Masukkan Semester");
                editSemester.requestFocus();
                return;
            }

            if (Semester.length() > 1) {
                inputSemester.setError("Maksimal Semester 8");
                editSemester.requestFocus();
                return;
            }

            ModelMahasiswa mahasiswa = new ModelMahasiswa();
            mahasiswa.Nama = Nama;
            mahasiswa.nim = Nim;
            mahasiswa.semester = Semester;

            DatabaseHelper.insertData(mahasiswa, new DatabaseCallback() {
                @Override
                public void onSuccess() {
                    super.onSuccess();

                    Toast.makeText(MainActivity.this, "Berhasil Memasukan Data", Toast.LENGTH_SHORT).show();

                    editNama.setText("");
                    editNim.setText("");
                    editSemester.setText("");

                    editNama.clearFocus();
                    editNim.clearFocus();
                    editSemester.clearFocus();
                }

                @Override
                public void onFailed() {
                    super.onFailed();

                    Toast.makeText(MainActivity.this, "Terjadi Kesalahan Saat Memasukan Data", Toast.LENGTH_SHORT).show();
                }
            });

        } else if (view.getId() == R.id.btnLiatData) {
            startActivity(new Intent(MainActivity.this, DaftarMahasiswaActivity.class));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DatabaseHelper.closeDatabase();
    }

}
