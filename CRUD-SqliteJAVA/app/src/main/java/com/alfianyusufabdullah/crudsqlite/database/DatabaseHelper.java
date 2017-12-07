package com.alfianyusufabdullah.crudsqlite.database;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import com.alfianyusufabdullah.crudsqlite.model.ModelMahasiswa;
import com.alfianyusufabdullah.crudsqlite.ui.DaftarMahasiswaActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonesrandom on 12/2/17.
 * <p>
 * AA
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static boolean databaseIsOpen = false;
    private static DatabaseHelper INSTANCE;
    private static SQLiteDatabase database;

    private DatabaseHelper(Context context) {
        super(context, DatabaseConstant.DATABASE_NAME, null, DatabaseConstant.DATABASE_VERSION);
    }

    public static void initDatabaseHelper(Context context) {
        INSTANCE = new DatabaseHelper(context);
    }

    public static void closeDatabase() {

        if (databaseIsOpen && database.isOpen()) {
            database.close();
            databaseIsOpen = false;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DatabaseConstant.QUERY_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DatabaseConstant.QUERY_UPGRADED);
        onCreate(sqLiteDatabase);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static void readData(DatabaseCallback databaseCallback) {

        if (!databaseIsOpen) {
            database = INSTANCE.getWritableDatabase();
            databaseIsOpen = true;
        }

        try (Cursor cur = database.rawQuery("SELECT * FROM " + DatabaseConstant.TABLE_NAME, null)) {
            if (cur.moveToFirst()) {
                List<ModelMahasiswa> data = new ArrayList<>();

                do {
                    ModelMahasiswa mahasiswa = new ModelMahasiswa();
                    mahasiswa.id = cur.getInt(cur.getColumnIndex(DatabaseConstant.ROW_ID));
                    mahasiswa.Nama = cur.getString(cur.getColumnIndex(DatabaseConstant.ROW_NAMA_MAHASISWA));
                    mahasiswa.nim = cur.getString(cur.getColumnIndex(DatabaseConstant.ROW_NIM_MAHASISWA));
                    mahasiswa.semester = cur.getString(cur.getColumnIndex(DatabaseConstant.ROW_SEMESTER_MAHASISWA));

                    data.add(mahasiswa);
                } while (cur.moveToNext());

                databaseCallback.onSuccess(data);
            }

        } catch (Exception e) {
            databaseCallback.onFailed();
        }

    }

    public static void updateData(ModelMahasiswa mahasiswa, DatabaseCallback callback) {
        if (!databaseIsOpen) {
            database = INSTANCE.getWritableDatabase();
            databaseIsOpen = true;
        }

        ContentValues values = new ContentValues();
        values.put(DatabaseConstant.ROW_ID, mahasiswa.id);
        values.put(DatabaseConstant.ROW_NAMA_MAHASISWA, mahasiswa.Nama);
        values.put(DatabaseConstant.ROW_NIM_MAHASISWA, mahasiswa.nim);
        values.put(DatabaseConstant.ROW_SEMESTER_MAHASISWA, mahasiswa.semester);

        long stat = database.update(DatabaseConstant.TABLE_NAME, values, DatabaseConstant.ROW_ID + "=" + mahasiswa.id, null);

        if (stat > 0) {
            callback.onSuccess();
        } else {
            callback.onFailed();
        }
    }

    public static void insertData(ModelMahasiswa mahasiswa, DatabaseCallback callback) {
        if (!databaseIsOpen) {
            database = INSTANCE.getWritableDatabase();
            databaseIsOpen = true;
        }

        ContentValues values = new ContentValues();
        values.put(DatabaseConstant.ROW_NAMA_MAHASISWA, mahasiswa.Nama);
        values.put(DatabaseConstant.ROW_NIM_MAHASISWA, mahasiswa.nim);
        values.put(DatabaseConstant.ROW_SEMESTER_MAHASISWA, mahasiswa.semester);

        long stat = database.insertOrThrow(DatabaseConstant.TABLE_NAME, null, values);

        if (stat > 0) {
            callback.onSuccess();
        } else {
            callback.onFailed();
        }

    }

    public static void deleteData(long id, DatabaseCallback callback) {
        if (!databaseIsOpen) {
            database = INSTANCE.getWritableDatabase();
            databaseIsOpen = true;
        }

        long stat = database.delete(DatabaseConstant.TABLE_NAME, DatabaseConstant.ROW_ID + "=" + id, null);

        if (stat > 0) {
            callback.onSuccess();
        } else {
            callback.onFailed();
        }
    }
}
