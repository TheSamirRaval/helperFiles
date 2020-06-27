package com.scc.moneymanager.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Created by Keval on 14-03-2019.
 */

@Database(version = 1,
        exportSchema = false,
        entities = {Account.class, Budget.class, Category.class,
                Entry.class, Notification.class, SubCategory.class,
                Initial.class, ChangeLog.class, Remark.class})
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "app_database";

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, DATABASE_NAME)
                            .build();
                }
            }

        }
        return INSTANCE;
    }

    public static AppDatabase resetDatabase(Context context) {
        context.deleteDatabase(AppDatabase.DATABASE_NAME);
        INSTANCE = null;
        return getDatabase(context);
    }

    public abstract DatabaseDao databaseDao();
}