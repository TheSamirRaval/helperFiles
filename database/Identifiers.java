package com.scc.moneymanager.database;

/**
 * Created by Keval on 14-03-2019.
 */
public class Identifiers {

    public static final String TABLE_ACCOUNT = "Tbl_account";
    public static final String TABLE_BUDGET = "Tbl_budget";
    public static final String TABLE_CATEGORY = "Tbl_category";
    public static final String TABLE_ENTRY = "Tbl_entry";
    public static final String TABLE_INITIAL = "Tbl_initial";
    public static final String TABLE_NOTIFICATION = "Tbl_notification";
    public static final String TABLE_SUB_CATEGORY = "Tbl_sub_category";

    public static final String TABLE_REMARK = "Tbl_remark";
    public static final String TABLE_CHANGE_LOG = "Tbl_changeLog";

    public static final int CAT_TYPE_INCOME = 0;
    public static final int CAT_TYPE_EXPANSE = 1;
    public static final int CAT_TYPE_LOAN = 2;
    public static final int CAT_TYPE_DEBT = 3;
    public static final int CAT_TYPE_REPAYMENT = 4;
    public static final int CAT_TYPE_DEBT_COLLECTION = 5;
    public static final int CAT_TYPE_MONEY_TRANSFER = 6;

    public static final String ACTION_INSERT = "insert";
    public static final String ACTION_UPDATE = "update";
    public static final String ACTION_DELETE = "delete";
}
