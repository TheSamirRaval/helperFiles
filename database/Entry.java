package com.scc.moneymanager.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Keval on 14-03-2019.
 */

@Entity(tableName = Identifiers.TABLE_ENTRY)
public class Entry implements Cloneable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    @SerializedName("id")
    @Expose
    private long id;

    @ColumnInfo(name = "amount")
    @SerializedName("amount")
    @Expose
    private double amount;

    @ColumnInfo(name = "remaining_amount")
    @SerializedName("remaining_amount")
    @Expose
    private double remainingAmount;

    @ColumnInfo(name = "date")
    @SerializedName("date")
    @Expose
    private long date;

    @ColumnInfo(name = "account_id")
    @SerializedName("account_id")
    @Expose
    private long accountId;

    @ColumnInfo(name = "cat_id")
    @SerializedName("cat_id")
    @Expose
    private long categoryId;

    @ColumnInfo(name = "sub_cat_id")
    @SerializedName("sub_cat_id")
    @Expose
    private long subCategoryId;

    @ColumnInfo(name = "notes")
    @SerializedName("notes")
    @Expose
    private String notes;

    @ColumnInfo(name = "person_name")
    @SerializedName("person_name")
    @Expose
    private String personName;

    @ColumnInfo(name = "reminder_date")
    @SerializedName("reminder_date")
    @Expose
    private long reminderDate;

    @ColumnInfo(name = "calender_id")
    @SerializedName("calender_id")
    @Expose
    private String calenderId;

    @ColumnInfo(name = "cn_id")
    @SerializedName("cn_id")
    @Expose
    private String contactNumber;

    @ColumnInfo(name = "return_id")
    @SerializedName("return_id")
    @Expose
    private long entryReferenceId;

    @ColumnInfo(name = "image1")
    @SerializedName("image1")
    @Expose
    private String image1;

    @ColumnInfo(name = "image2")
    @SerializedName("image2")
    @Expose
    private String image2;

    @ColumnInfo(name = "timestamp")
    @SerializedName("timestamp")
    @Expose
    private long timestamp = System.currentTimeMillis();

    // TODO: 26-09-2019 add by SAM
    @ColumnInfo(name = "recieve_acc")
    @SerializedName("recieve_acc")
    @Expose
    private int receiveAccId;


    public Entry() {
    }

    public int getReceiveAccId() {
        return receiveAccId;
    }

    public void setReceiveAccId(int receiveAccId) {
        this.receiveAccId = receiveAccId;
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

    public double getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(double remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(long subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public long getReminderDate() {
        return reminderDate;
    }

    public void setReminderDate(long reminderDate) {
        this.reminderDate = reminderDate;
    }

    public String getCalenderId() {
        return calenderId;
    }

    public void setCalenderId(String calenderId) {
        this.calenderId = calenderId;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public long getEntryReferenceId() {
        return entryReferenceId;
    }

    public void setEntryReferenceId(long entryReferenceId) {
        this.entryReferenceId = entryReferenceId;
    }

    public String getImage1() {
        return image1 == null ? "" : image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2 == null ? "" : image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "id=" + id +
                ", amount=" + amount +
                ", remainingAmount=" + remainingAmount +
                ", date=" + date +
                ", accountId=" + accountId +
                ", categoryId=" + categoryId +
                ", subCategoryId=" + subCategoryId +
                ", notes='" + notes + '\'' +
                ", personName='" + personName + '\'' +
                ", reminderDate=" + reminderDate +
                ", calenderId='" + calenderId + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", entryReferenceId=" + entryReferenceId +
                ", image1='" + image1 + '\'' +
                ", image2='" + image2 + '\'' +
                ", timestamp=" + timestamp +
                ", receiveAccId=" + receiveAccId +
                '}';
    }
}