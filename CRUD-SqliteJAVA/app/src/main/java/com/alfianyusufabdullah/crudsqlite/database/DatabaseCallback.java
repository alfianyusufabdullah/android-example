package com.alfianyusufabdullah.crudsqlite.database;

import com.alfianyusufabdullah.crudsqlite.model.ModelMahasiswa;

import java.util.List;

/**
 * Created by jonesrandom on 12/6/17.
 * <p>
 * AA
 */

public abstract class DatabaseCallback {

    public void onSuccess() {
    }

    public void onFailed() {
    }

    public void onSuccess(List<ModelMahasiswa> mahasiswas) {
    }
}
