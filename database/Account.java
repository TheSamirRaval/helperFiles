package com.scc.moneymanager.database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Keval on 14-03-2019.
 */

@Entity(tableName = Identifiers.TABLE_ACCOUNT)
public class Account implements Parcelable {

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Account> CREATOR = new Parcelable.Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    @SerializedName("id")
    private long id;

    @ColumnInfo(name = "name")
    @SerializedName("name")
    private String name;

    @ColumnInfo(name = "balance")
    @SerializedName("balance")
    private double balance;

    @ColumnInfo(name = "freq_count")
    @SerializedName("freq_count")
    private int freqCount;

    @ColumnInfo(name = "icon")
    @SerializedName("icon")
    private String icon;

    @ColumnInfo(name = "rearrangeIndex")
    @SerializedName("rearrangeIndex")
    private int rearrangeIndex;

    @ColumnInfo(name = "timestamp")
    @SerializedName("timestamp")
    private long timestamp = System.currentTimeMillis();

    public Account() {
    }

    @Ignore
    public Account(String name, String icon) {
        this.name = name;
        this.icon = icon;
    }

    @Ignore
    protected Account(Parcel in) {
        id = in.readLong();
        name = in.readString();
        balance = in.readDouble();
        freqCount = in.readInt();
        icon = in.readString();
        rearrangeIndex = in.readInt();
        timestamp = in.readLong();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getFreqCount() {
        return freqCount;
    }

    public void setFreqCount(int freqCount) {
        this.freqCount = freqCount;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getRearrangeIndex() {
        return rearrangeIndex;
    }

    public void setRearrangeIndex(int rearrangeIndex) {
        this.rearrangeIndex = rearrangeIndex;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeDouble(balance);
        dest.writeInt(freqCount);
        dest.writeString(icon);
        dest.writeInt(rearrangeIndex);
        dest.writeLong(timestamp);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", freqCount=" + freqCount +
                ", icon='" + icon + '\'' +
                ", rearrangeIndex=" + rearrangeIndex +
                '}';
    }
}
