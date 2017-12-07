package com.alfianyusufabdullah.crudsqlite.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alfianyusufabdullah.crudsqlite.R;
import com.alfianyusufabdullah.crudsqlite.model.ModelMahasiswa;

import java.util.List;

/**
 * Created by jonesrandom on 12/5/17.
 * <p>
 * AA
 */

public class AdapterMahasiswa extends RecyclerView.Adapter<ViewHolderMahasiswa> {

    private List<ModelMahasiswa> data;
    private OnItemClickListener listener;

    public AdapterMahasiswa(List<ModelMahasiswa> data, OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @Override
    public ViewHolderMahasiswa onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_daftar_mahasiswa, parent, false);
        return new ViewHolderMahasiswa(v);
    }

    @Override
    public void onBindViewHolder(ViewHolderMahasiswa holder, int position) {
        holder.setContent(data.get(position) , listener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
