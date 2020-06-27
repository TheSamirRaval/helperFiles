package com.scc.moneymanager.database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Created by Keval on 15-05-2019.
 */

@Entity(tableName = Identifiers.TABLE_REMARK)
public class Remark implements Parcelable {

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Remark> CREATOR = new Parcelable.Creator<Remark>() {
        @Override
        public Remark createFromParcel(Parcel in) {
            return new Remark(in);
        }

        @Override
        public Remark[] newArray(int size) {
            return new Remark[size];
        }
    };

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private long id;

    @ColumnInfo(name = "message")
    private String message;

    @ColumnInfo(name = "image")
    private String image;

    @ColumnInfo(name = "timestamp")
    private long timestamp = System.currentTimeMillis();

    public Remark() {
    }

    @Ignore
    public Remark(String message, String image) {
        this.message = message;
        this.image = image;
    }

    protected Remark(Parcel in) {
        id = in.readLong();
        message = in.readString();
        image = in.readString();
        timestamp = in.readLong();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
        dest.writeString(message);
        dest.writeString(image);
        dest.writeLong(timestamp);
    }

}
