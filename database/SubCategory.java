package com.scc.moneymanager.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Keval on 14-03-2019.
 */

@Entity(tableName = Identifiers.TABLE_SUB_CATEGORY,
        foreignKeys = @ForeignKey(
                entity = Category.class,
                parentColumns = "_id",
                childColumns = "cat_id",
                onDelete = ForeignKey.CASCADE),
        indices = @Index(value = {"cat_id", "name"}, unique = true))
public class SubCategory {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    @SerializedName("id")
    private long id;

    @ColumnInfo(name = "cat_id", index = true)
    @SerializedName("cat_id")
    private long categoryId;

    @ColumnInfo(name = "name")
    @SerializedName("name")
    private String name;

    @ColumnInfo(name = "timestamp")
    @SerializedName("timestamp")
    private long timestamp = System.currentTimeMillis();

    public SubCategory() {
    }

    @Ignore
    public SubCategory(long categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "SubCategory{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", name='" + name + '\'' +
                '}';
    }
}
