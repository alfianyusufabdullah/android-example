package com.alfianyusufabdullah.crudsqlite.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alfianyusufabdullah.crudsqlite.R;
import com.alfianyusufabdullah.crudsqlite.model.ModelMahasiswa;

/**
 * Created by jonesrandom on 12/6/17.
 * <p>
 * AA
 */

public class DetailMahasiswaDialog extends BottomSheetDialogFragment {

    private static OnDialogCallback callback;
    private static ModelMahasiswa mahasiswa;

    public static DetailMahasiswaDialog newInstance(ModelMahasiswa mahasiswas, OnDialogCallback callbacks) {
        callback = callbacks;
        mahasiswa = mahasiswas;
        return new DetailMahasiswaDialog();
    }

    public DetailMahasiswaDialog() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bindView(view);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle("Detail Mahasiswa");
        toolbar.setSubtitle(mahasiswa.Nama);
        toolbar.inflateMenu(R.menu.detail_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.detail_hapus:

                        callback.onDelete(mahasiswa);
                        getDialog().dismiss();
                        break;
                    case R.id.detail_edit:

                        callback.onEdit(mahasiswa);
                        getDialog().dismiss();
                        break;
                }
                return false;
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void bindView(View view) {

        TextView tvID = view.findViewById(R.id.detailID);
        TextView tvNama = view.findViewById(R.id.detailNama);
        TextView tvNim = view.findViewById(R.id.detailNim);
        TextView tvSemester = view.findViewById(R.id.detailSemester);

        tvID.setText("ID" + mahasiswa.id);
        tvNama.setText(mahasiswa.Nama);
        tvNim.setText("NIM" + mahasiswa.nim);
        tvSemester.setText("Semester " + mahasiswa.semester);
    }

    public interface OnDialogCallback {
        void onEdit(ModelMahasiswa mahasiswa);

        void onDelete(ModelMahasiswa mahasiswa);
    }
}
