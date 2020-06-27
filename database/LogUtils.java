package com.scc.moneymanager.database;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scc.moneymanager.util.SharedPrefs;

import java.util.List;

import io.reactivex.schedulers.Schedulers;

import static com.scc.moneymanager.database.Identifiers.ACTION_DELETE;
import static com.scc.moneymanager.database.Identifiers.ACTION_INSERT;
import static com.scc.moneymanager.database.Identifiers.ACTION_UPDATE;

/**
 * Created by Keval on 18-07-2019.
 */
public class LogUtils {

    private static DatabaseDao mDatabaseDao;
    private static Gson mGson;

    private static boolean canTakeLog = false;

    public static void init(Context context) {
        mDatabaseDao = AppDatabase.getDatabase(context).databaseDao();
        mGson = new GsonBuilder().create();

        canTakeLog = SharedPrefs.getBoolean(context, SharedPrefs.PREF_CAN_TAKE_LOG, false);
    }

    public static void setCanTakeLog(boolean canTakeLog) {
        LogUtils.canTakeLog = canTakeLog;
    }

    public static void insertBudget(Budget budget) {
        if (canTakeLog) {
            ChangeLog log = new ChangeLog(Identifiers.TABLE_BUDGET, ACTION_INSERT, mGson.toJson(budget));
            mDatabaseDao.insertLog(log).subscribeOn(Schedulers.io()).subscribe();
        }
    }

    public static void updateBudget(Budget budget) {
        if (canTakeLog) {
            ChangeLog log = new ChangeLog(Identifiers.TABLE_BUDGET, ACTION_UPDATE, mGson.toJson(budget));
            mDatabaseDao.insertLog(log).subscribeOn(Schedulers.io()).subscribe();
        }
    }

    public static void deleteBudget(long id) {
        if (canTakeLog) {
            ChangeLog log = new ChangeLog(Identifiers.TABLE_BUDGET, ACTION_DELETE, String.valueOf(id));
            mDatabaseDao.insertLog(log).subscribeOn(Schedulers.io()).subscribe();
        }
    }


    public static void insertEntry(Entry entry) {
        if (canTakeLog) {
            ChangeLog log = new ChangeLog(Identifiers.TABLE_ENTRY, ACTION_INSERT, mGson.toJson(entry));
            mDatabaseDao.insertLog(log).subscribeOn(Schedulers.io()).subscribe();
        }
    }

    public static void updateEntry(Entry entry) {
        if (canTakeLog) {
            ChangeLog log = new ChangeLog(Identifiers.TABLE_ENTRY, ACTION_UPDATE, mGson.toJson(entry));
            mDatabaseDao.insertLog(log).subscribeOn(Schedulers.io()).subscribe();
        }
    }

    public static void deleteEntry(long id) {
        if (canTakeLog) {
            ChangeLog log = new ChangeLog(Identifiers.TABLE_ENTRY, ACTION_DELETE, String.valueOf(id));
            mDatabaseDao.insertLog(log).subscribeOn(Schedulers.io()).subscribe();
        }
    }

    public static void deleteEntry(List<Entry> list) {
        if (canTakeLog) {
            ChangeLog[] logs = new ChangeLog[list.size()];
            for (int i = 0; i < list.size(); i++) {
                logs[i] = new ChangeLog(Identifiers.TABLE_ENTRY, ACTION_DELETE, String.valueOf(list.get(i).getId()));
            }
            mDatabaseDao.insertLog(logs).subscribeOn(Schedulers.io()).subscribe();
        }
    }


    public static void insertSubCategory(SubCategory subCategory) {
        if (canTakeLog) {
            ChangeLog log = new ChangeLog(Identifiers.TABLE_SUB_CATEGORY, ACTION_INSERT, mGson.toJson(subCategory));
            mDatabaseDao.insertLog(log).subscribeOn(Schedulers.io()).subscribe();
        }
    }

    public static void insertSubCategory(List<SubCategory> list) {
        if (canTakeLog) {
            ChangeLog[] logs = new ChangeLog[list.size()];
            for (int i = 0; i < list.size(); i++) {
                logs[i] = new ChangeLog(Identifiers.TABLE_SUB_CATEGORY, ACTION_INSERT, mGson.toJson(list.get(i)));
            }
            mDatabaseDao.insertLog(logs).subscribeOn(Schedulers.io()).subscribe();
        }
    }

    public static void updateSubCategory(List<SubCategory> list) {
        if (canTakeLog) {
            ChangeLog[] logs = new ChangeLog[list.size()];
            for (int i = 0; i < list.size(); i++) {
                logs[i] = new ChangeLog(Identifiers.TABLE_SUB_CATEGORY, ACTION_UPDATE, mGson.toJson(list.get(i)));
            }
            mDatabaseDao.insertLog(logs).subscribeOn(Schedulers.io()).subscribe();
        }
    }

    public static void updateSubCategory(SubCategory subCategory) {
        if (canTakeLog) {
            ChangeLog log = new ChangeLog(Identifiers.TABLE_SUB_CATEGORY, ACTION_UPDATE, mGson.toJson(subCategory));
            mDatabaseDao.insertLog(log).subscribeOn(Schedulers.io()).subscribe();
        }
    }

    public static void deleteSubCategory(long id) {
        if (canTakeLog) {
            ChangeLog log = new ChangeLog(Identifiers.TABLE_SUB_CATEGORY, ACTION_DELETE, String.valueOf(id));
            mDatabaseDao.insertLog(log).subscribeOn(Schedulers.io()).subscribe();
        }
    }


    public static void insertInitial(Initial initial) {
        if (canTakeLog) {
            ChangeLog log = new ChangeLog(Identifiers.TABLE_INITIAL, ACTION_INSERT, mGson.toJson(initial));
            mDatabaseDao.insertLog(log).subscribeOn(Schedulers.io()).subscribe();
        }
    }

    public static void updateInitial(Initial initial) {
        if (canTakeLog) {
            ChangeLog log = new ChangeLog(Identifiers.TABLE_INITIAL, ACTION_UPDATE, mGson.toJson(initial));
            mDatabaseDao.insertLog(log).subscribeOn(Schedulers.io()).subscribe();
        }
    }

    public static void deleteInitial(long id) {
        if (canTakeLog) {
            ChangeLog log = new ChangeLog(Identifiers.TABLE_INITIAL, ACTION_DELETE, String.valueOf(id));
            mDatabaseDao.insertLog(log).subscribeOn(Schedulers.io()).subscribe();
        }
    }
}

/*public static void insertAccount(Account account) {
        ChangeLog log = new ChangeLog(Identifiers.TABLE_ACCOUNT, ACTION_INSERT, mGson.toJson(account));
        mDatabaseDao.insertLog(log).subscribeOn(Schedulers.io()).subscribe();
    }

    public static void updateAccount(Account account) {
        ChangeLog log = new ChangeLog(Identifiers.TABLE_ACCOUNT, ACTION_UPDATE, mGson.toJson(account));
        mDatabaseDao.insertLog(log).subscribeOn(Schedulers.io()).subscribe();
    }

    public static void updateAccount(List<Account> list) {
        ChangeLog[] logs = new ChangeLog[list.size()];
        for (int i = 0; i < list.size(); i++) {
            logs[i] = new ChangeLog(Identifiers.TABLE_ACCOUNT, ACTION_UPDATE, mGson.toJson(list.get(i)));
        }
        mDatabaseDao.insertLog(logs).subscribeOn(Schedulers.io()).subscribe();
    }

    public static void deleteAccount(long id) {
        ChangeLog log = new ChangeLog(Identifiers.TABLE_ACCOUNT, ACTION_DELETE, String.valueOf(id));
        mDatabaseDao.insertLog(log).subscribeOn(Schedulers.io()).subscribe();
    }


    public static void insertCategory(Category category) {
        ChangeLog log = new ChangeLog(Identifiers.TABLE_CATEGORY, ACTION_INSERT, mGson.toJson(category));
        mDatabaseDao.insertLog(log).subscribeOn(Schedulers.io()).subscribe();
    }

    public static void updateCategory(Category category) {
        ChangeLog log = new ChangeLog(Identifiers.TABLE_CATEGORY, ACTION_UPDATE, mGson.toJson(category));
        mDatabaseDao.insertLog(log).subscribeOn(Schedulers.io()).subscribe();
    }

    public static void deleteCategory(long id) {
        ChangeLog log = new ChangeLog(Identifiers.TABLE_CATEGORY, ACTION_DELETE, String.valueOf(id));
        mDatabaseDao.insertLog(log).subscribeOn(Schedulers.io()).subscribe();
    }*/