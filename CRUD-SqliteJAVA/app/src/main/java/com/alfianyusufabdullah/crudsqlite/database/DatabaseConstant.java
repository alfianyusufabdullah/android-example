package com.alfianyusufabdullah.crudsqlite.database;

/**
 * Created by jonesrandom on 12/2/17.
 * <p>
 * AA
 */

public class DatabaseConstant {

    public static final String DATABASE_NAME = "database";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "table_mahasiswa";

    public static final String ROW_ID = "_id";
    public static final String ROW_NAMA_MAHASISWA = "nama";
    public static final String ROW_NIM_MAHASISWA = "nim";
    public static final String ROW_SEMESTER_MAHASISWA = "semester";

    public static final String QUERY_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
            + ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ROW_NAMA_MAHASISWA + " TEXT,"
            + ROW_NIM_MAHASISWA + " TEXT,"
            + ROW_SEMESTER_MAHASISWA + " TEXT)";

    public static final String QUERY_UPGRADED = "DROP TABLE IF NOT EXISTS " + TABLE_NAME;
}
