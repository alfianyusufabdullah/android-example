package com.alfianyusufabdullah.crudsqlite;

import android.app.Application;

import com.alfianyusufabdullah.crudsqlite.database.DatabaseHelper;

/**
 * Created by jonesrandom on 12/2/17.
 * <p>
 * AA
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DatabaseHelper.initDatabaseHelper(this);
    }
}
