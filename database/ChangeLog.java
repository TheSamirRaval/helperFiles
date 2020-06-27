package com.scc.moneymanager.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Keval on 03-05-2019.
 */

@Entity(tableName = Identifiers.TABLE_CHANGE_LOG)
public class ChangeLog {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    @SerializedName("id")
    @Expose(deserialize = false, serialize = false)
    private long id;

    @ColumnInfo(name = "timestamp")
    @Expose()
    private long timestamp = System.currentTimeMillis();

    @ColumnInfo(name = "tableName")
    @SerializedName("tableName")
    @Expose
    private String tableName;

    @ColumnInfo(name = "action")
    @SerializedName("action")
    @Expose
    private String action;

    @ColumnInfo(name = "data")
    @SerializedName("data")
    @Expose()
    private String data;

    public ChangeLog() {
    }

    @Ignore
    public ChangeLog(String tableName, String action, String data) {
        this.tableName = tableName;
        this.action = action;
        this.data = data;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
