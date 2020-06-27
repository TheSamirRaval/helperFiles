package com.scc.moneymanager.database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Keval on 14-03-2019.
 */
@Entity(tableName = Identifiers.TABLE_CATEGORY,
        indices = @Index(value = {"name", "ie_type"}, unique = true))
public class Category implements Parcelable {

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    @SerializedName("id")
    private long id;

    @ColumnInfo(name = "name")
    @SerializedName("name")
    private String name;

    @ColumnInfo(name = "icon")
    @SerializedName("icon")
    private String icon;

    @ColumnInfo(name = "ie_type")
    @SerializedName("ie_type")
    private int type;

    @ColumnInfo(name = "freq_count")
    @SerializedName("freq_count")
    private int freqCount;

    @ColumnInfo(name = "date")
    @SerializedName("date")
    private String date;

    @ColumnInfo(name = "is_remind")
    @SerializedName("is_remind")
    private boolean remind;

    @ColumnInfo(name = "repeat_date")
    @SerializedName("repeat_date")
    private String repeatDate;

    @ColumnInfo(name = "repeat_interval")
    @SerializedName("repeat_interval")
    private int repeatInterval;

    @ColumnInfo(name = "rearrangeIndex")
    @SerializedName("rearrangeIndex")
    private int rearrangeIndex;

    @ColumnInfo(name = "timestamp")
    @SerializedName("timestamp")
    private long timestamp = System.currentTimeMillis();

    public Category() {
    }

    @Ignore
    public Category(String name, String icon, int type) {
        this.name = name;
        this.icon = icon;
        this.type = type;
    }

    @Ignore
    protected Category(Parcel in) {
        id = in.readLong();
        name = in.readString();
        icon = in.readString();
        type = in.readInt();
        freqCount = in.readInt();
        date = in.readString();
        remind = in.readByte() != 0x00;
        repeatDate = in.readString();
        repeatInterval = in.readInt();
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getFreqCount() {
        return freqCount;
    }

    public void setFreqCount(int freqCount) {
        this.freqCount = freqCount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isRemind() {
        return remind;
    }

    public void setRemind(boolean remind) {
        this.remind = remind;
    }

    public String getRepeatDate() {
        return repeatDate;
    }

    public void setRepeatDate(String repeatDate) {
        this.repeatDate = repeatDate;
    }

    public int getRepeatInterval() {
        return repeatInterval;
    }

    public void setRepeatInterval(int repeatInterval) {
        this.repeatInterval = repeatInterval;
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
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", type=" + type +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(icon);
        dest.writeInt(type);
        dest.writeInt(freqCount);
        dest.writeString(date);
        dest.writeByte((byte) (remind ? 0x01 : 0x00));
        dest.writeString(repeatDate);
        dest.writeInt(repeatInterval);
        dest.writeInt(rearrangeIndex);
        dest.writeLong(timestamp);
    }

}
