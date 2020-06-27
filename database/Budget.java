package com.scc.moneymanager.database;

import android.annotation.SuppressLint;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Keval on 14-03-2019.
 */

@Entity(tableName = Identifiers.TABLE_BUDGET,
        foreignKeys = @ForeignKey(
                entity = Category.class,
                parentColumns = "_id",
                childColumns = "cat_id",
                onDelete = ForeignKey.CASCADE),
        indices = @Index(value = {"cat_id", "start_date", "end_date"}, unique = true))
public class Budget {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    @SerializedName("id")
    private long id;

    @ColumnInfo(name = "amount")
    @SerializedName("amount")
    private double amount;

    @ColumnInfo(name = "cat_id")
    @SerializedName("cat_id")
    private long categoryId;

    @ColumnInfo(name = "start_date")
    @SerializedName("start_date")
    private long startDate;

    @ColumnInfo(name = "end_date")
    @SerializedName("end_date")
    private long endDate;

    @ColumnInfo(name = "note")
    @SerializedName("note")
    private String note;

    @ColumnInfo(name = "timestamp")
    @SerializedName("timestamp")
    private long timestamp = System.currentTimeMillis();

    public Budget() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MM yyyy HH:mm:ss");
        return "Budget{" +
                "id=" + id +
                ", amount=" + amount +
                ", categoryId=" + categoryId +
                ", startDate=" + simpleDateFormat.format(new Date(startDate)) +
                ", endDate=" + simpleDateFormat.format(new Date(endDate)) +
                ", note='" + note + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
