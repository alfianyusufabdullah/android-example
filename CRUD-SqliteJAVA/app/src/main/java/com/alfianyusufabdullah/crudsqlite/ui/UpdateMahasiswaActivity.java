package com.alfianyusufabdullah.crudsqlite.ui;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alfianyusufabdullah.crudsqlite.R;
import com.alfianyusufabdullah.crudsqlite.database.DatabaseCallback;
import com.alfianyusufabdullah.crudsqlite.database.DatabaseConstant;
import com.alfianyusufabdullah.crudsqlite.database.DatabaseHelper;
import com.alfianyusufabdullah.crudsqlite.model.ModelMahasiswa;

public class UpdateMahasiswaActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout inputNama, inputNim, inputSemester;
    private TextInputEditText editNama, editNim, editSemester;

    private String Nama, Nim, Semester;
    private int Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_mahasiswa);

        initView();
    }

    private void initView() {

        Bundle bind = getIntent().getExtras();

        if (bind != null) {
            Id = bind.getInt(DatabaseConstant.ROW_ID);
            Nama = bind.getString(DatabaseConstant.ROW_NAMA_MAHASISWA);
            Nim = bind.getString(DatabaseConstant.ROW_NIM_MAHASISWA);
            Semester = bind.getString(DatabaseConstant.ROW_SEMESTER_MAHASISWA);
        }

        inputNama = findViewById(R.id.inputNama);
        inputNim = findViewById(R.id.inputNim);
        inputSemester = findViewById(R.id.inputSemester);

        editNama = findViewById(R.id.editNama);
        editNim = findViewById(R.id.editNim);
        editSemester = findViewById(R.id.editSemester);

        editNama.setText(Nama);
        editNim.setText(Nim);
        editSemester.setText(Semester);

        editNama.addTextChangedListener(myWatcher(inputNama));
        editNim.addTextChangedListener(myWatcher(inputNim));
        editSemester.addTextChangedListener(myWatcher(inputSemester));

        Button btnUpdateData = findViewById(R.id.btnUpdateData);
        btnUpdateData.setOnClickListener(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Update Data");

    }

    @Override
    public void onClick(View view) {
        Nama = editNama.getText().toString();
        Nim = editNim.getText().toString();
        Semester = editSemester.getText().toString();

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
        mahasiswa.id = Id;
        mahasiswa.Nama = Nama;
        mahasiswa.nim = Nim;
        mahasiswa.semester = Semester;

        DatabaseHelper.updateData(mahasiswa, new DatabaseCallback() {
            @Override
            public void onSuccess() {
                super.onSuccess();

                Toast.makeText(UpdateMahasiswaActivity.this, "Berhasil Update Data", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailed() {
                super.onFailed();

                Toast.makeText(UpdateMahasiswaActivity.this, "Gagal Update Data", Toast.LENGTH_SHORT).show();
            }
        });

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
    protected void onDestroy() {
        super.onDestroy();

        DatabaseHelper.closeDatabase();
    }
}
