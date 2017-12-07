package com.alfianyusufabdullah.crudsqlite.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alfianyusufabdullah.crudsqlite.R;
import com.alfianyusufabdullah.crudsqlite.model.ModelMahasiswa;

/**
 * Created by jonesrandom on 12/5/17.
 * <p>
 * AA
 */

public class ViewHolderMahasiswa extends RecyclerView.ViewHolder {

    private TextView row_tick, row_nama, row_nim;

    public ViewHolderMahasiswa(View itemView) {
        super(itemView);

        row_tick = itemView.findViewById(R.id.row_tick);
        row_nama = itemView.findViewById(R.id.row_nama);
        row_nim = itemView.findViewById(R.id.row_nim);
    }

    public void setContent(final ModelMahasiswa mahasiswa, final OnItemClickListener listener) {
        row_tick.setText(mahasiswa.Nama.substring(0, 1).toUpperCase());
        row_nama.setText(mahasiswa.Nama);
        row_nim.setText(mahasiswa.nim);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.itemClick(mahasiswa);
            }
        });
    }
}
