package com.alfianyusufabdullah.crudsqlite.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.alfianyusufabdullah.crudsqlite.R;
import com.alfianyusufabdullah.crudsqlite.adapter.AdapterMahasiswa;
import com.alfianyusufabdullah.crudsqlite.adapter.OnItemClickListener;
import com.alfianyusufabdullah.crudsqlite.database.DatabaseCallback;
import com.alfianyusufabdullah.crudsqlite.database.DatabaseConstant;
import com.alfianyusufabdullah.crudsqlite.database.DatabaseHelper;
import com.alfianyusufabdullah.crudsqlite.model.ModelMahasiswa;

import java.util.ArrayList;
import java.util.List;

public class DaftarMahasiswaActivity extends AppCompatActivity implements DetailMahasiswaDialog.OnDialogCallback {

    private List<ModelMahasiswa> dataMahasiswa = new ArrayList<>();
    private AdapterMahasiswa adapterDaftarMahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_mahasiswa);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Daftar Mahasiswa");

        adapterDaftarMahasiswa = new AdapterMahasiswa(dataMahasiswa, new OnItemClickListener() {
            @Override
            public void itemClick(ModelMahasiswa modelMahasiswa) {
                Log.d("DATABASE", "onMenuItemClick: " + modelMahasiswa.id);
                DetailMahasiswaDialog.newInstance(modelMahasiswa, DaftarMahasiswaActivity.this).show(getSupportFragmentManager(), "DETAIL");

            }
        });

        RecyclerView daftarMahasiswa = findViewById(R.id.daftarMahasiswa);
        daftarMahasiswa.setHasFixedSize(true);
        daftarMahasiswa.setLayoutManager(new LinearLayoutManager(this));
        daftarMahasiswa.setAdapter(adapterDaftarMahasiswa);

    }

    @Override
    protected void onStart() {
        super.onStart();

        DatabaseHelper.readData(new DatabaseCallback() {
            @Override
            public void onSuccess(List<ModelMahasiswa> mahasiswas) {
                super.onSuccess(mahasiswas);
                dataMahasiswa.clear();
                dataMahasiswa.addAll(mahasiswas);
                adapterDaftarMahasiswa.notifyDataSetChanged();
            }

            @Override
            public void onFailed() {
                super.onFailed();
                Toast.makeText(DaftarMahasiswaActivity.this, "Terjadi Kesalahan Saat Memuat Data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onEdit(ModelMahasiswa mahasiswa) {

        Bundle bind = new Bundle();
        bind.putInt(DatabaseConstant.ROW_ID, mahasiswa.id);
        bind.putString(DatabaseConstant.ROW_NAMA_MAHASISWA, mahasiswa.Nama);
        bind.putString(DatabaseConstant.ROW_NIM_MAHASISWA, mahasiswa.nim);
        bind.putString(DatabaseConstant.ROW_SEMESTER_MAHASISWA, mahasiswa.semester);

        Intent update = new Intent(DaftarMahasiswaActivity.this, UpdateMahasiswaActivity.class);
        update.putExtras(bind);
        startActivity(update);
    }

    @Override
    public void onDelete(final ModelMahasiswa mahasiswa) {

        AlertDialog.Builder builder = new AlertDialog.Builder(DaftarMahasiswaActivity.this);
        builder.setTitle("Detail Mahasiswa");
        builder.setMessage("Apakah Kamu Ingin Menghapus Data " + mahasiswa.Nama + " ?");
        builder.setNegativeButton("BATAL", null);
        builder.setPositiveButton("HAPUS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                DatabaseHelper.deleteData(mahasiswa.id, new DatabaseCallback() {
                    @Override
                    public void onSuccess() {
                        super.onSuccess();

                        dataMahasiswa.remove(mahasiswa);
                        adapterDaftarMahasiswa.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailed() {
                        super.onFailed();

                        Toast.makeText(DaftarMahasiswaActivity.this, "Gagal Menghapus Data", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        builder.create().show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        DatabaseHelper.closeDatabase();
    }

}
