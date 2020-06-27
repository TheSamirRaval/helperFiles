package com.scc.moneymanager.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Keval on 29-03-2019.
 */

@Entity(tableName = Identifiers.TABLE_INITIAL,
        foreignKeys = @ForeignKey(
                entity = Account.class,
                parentColumns = "_id",
                childColumns = "accountId",
                onDelete = ForeignKey.CASCADE))
public class Initial {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    @SerializedName("id")
    private long id;

    @ColumnInfo(name = "accountId", index = true)
    @SerializedName("accountId")
    private long accountId;

    @ColumnInfo(name = "amount")
    @SerializedName("amount")
    private double amount;

    @ColumnInfo(name = "timestamp")
    @SerializedName("timestamp")
    private long timestamp = System.currentTimeMillis();

    public Initial() {
    }

    @Ignore
    public Initial(long accountId, long amount, long timestamp) {
        this.accountId = accountId;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Initial{" +
                "id=" + id +
                ", accountId=" + accountId +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                '}';
    }
}
