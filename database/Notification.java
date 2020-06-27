package com.scc.moneymanager.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Keval on 14-03-2019.
 */

@Entity(tableName = Identifiers.TABLE_NOTIFICATION)
public class Notification {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private long id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "message")
    private String message;

    @ColumnInfo(name = "dateTime")
    private long dateTime;

    @ColumnInfo(name = "cat_name")
    private String categoryName;

    @ColumnInfo(name = "icon")
    private String icon;

    public Notification() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getDateTime() {
        return dateTime;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
