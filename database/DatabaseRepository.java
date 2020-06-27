package com.scc.moneymanager.database;

import android.content.Context;

/**
 * Created by Keval on 15-03-2019.
 */
public class DatabaseRepository {

    private DatabaseDao databaseDao;

    public DatabaseRepository(Context context) {
        AppDatabase appDatabase = AppDatabase.getDatabase(context.getApplicationContext());
        this.databaseDao = appDatabase.databaseDao();
    }

    public DatabaseDao getDatabaseDao() {
        return databaseDao;
    }
}