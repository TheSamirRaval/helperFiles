package com.scc.moneymanager.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Update;
import androidx.sqlite.db.SupportSQLiteQuery;

import com.scc.moneymanager.model.BudgetEntry;
import com.scc.moneymanager.model.ChartEntry;
import com.scc.moneymanager.model.LoanDebtTransaction;
import com.scc.moneymanager.model.OverviewAccount;
import com.scc.moneymanager.model.OverviewEntry;
import com.scc.moneymanager.model.SubEntry;
import com.scc.moneymanager.model.Total;
import com.scc.moneymanager.model.Transaction;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Keval on 14-03-2019.
 */

@Dao
public interface DatabaseDao {

    //region Account
    @Insert
    Maybe<Long> insertAccountGetId(Account account);

    @Insert
    Completable insertAccount(List<Account> list);

    @Update
    Completable updateAccount(Account... accounts);

    @Update
    Completable updateAccount(List<Account> list);

    @Delete
    Completable deleteAccount(Account... accounts);

    @Query("SELECT * FROM " + Identifiers.TABLE_ACCOUNT + " WHERE _id=:id")
    Observable<Account> getAccountLive(long id);

    @Query("SELECT * FROM " + Identifiers.TABLE_ACCOUNT + " WHERE _id=:id")
    Single<Account> getAccount(long id);

    @RawQuery(observedEntities = Account.class)
    Observable<List<Account>> getAllAccountLive(SupportSQLiteQuery query);

    @Query("SELECT * FROM " + Identifiers.TABLE_ACCOUNT + " WHERE _id!=:id")
    Maybe<List<Account>> getOtherAccount(long id);

    @RawQuery
    Maybe<List<Account>> getAccount(SupportSQLiteQuery query);

    @Query("SELECT COUNT(_id) FROM " + Identifiers.TABLE_ACCOUNT)
    Maybe<Integer> getAccountCount();

    @Query("SELECT * FROM " + Identifiers.TABLE_ACCOUNT + " WHERE _id IN (:ids)")
    Maybe<List<Account>> getAccountsById(List<Long> ids);

    @Query("SELECT SUM(balance) as totalAmount FROM " + Identifiers.TABLE_ACCOUNT)
    Observable<Total> getTotalBalanceLive();
    //endregion


    //region Budget
    @Insert
    Maybe<Long> insertBudgetGetId(Budget budget);

    @Insert
    Completable insertBudget(List<Budget> list);

    @Update
    Completable updateBudget(Budget budget);

    @Query("DELETE FROM " + Identifiers.TABLE_BUDGET + " WHERE _id=:id")
    Completable deleteBudget(long id);

    @Query("SELECT * FROM " + Identifiers.TABLE_BUDGET + " WHERE cat_id=:id AND (start_date<:millis AND end_date>:millis)")
    Maybe<List<Budget>> getBudgetByCategory(long id, long millis);

    @Query("SELECT budget._id as id, budget.amount as amount, budget.start_date as startDate, budget.end_date as endDate, budget.note as note," +
            " budget.cat_id as categoryId, category.ie_type as categoryType, category.name as categoryName, category.icon as categoryIcon" +
            " FROM Tbl_budget budget INNER JOIN Tbl_category category ON budget.cat_id=category._id WHERE end_date>=:startMillis")
    Maybe<List<BudgetEntry>> getCurrentBudget(long startMillis);

    @Query("SELECT budget._id as id, budget.amount as amount, budget.start_date as startDate, budget.end_date as endDate, budget.note as note," +
            " budget.cat_id as categoryId, category.ie_type as categoryType, category.name as categoryName, category.icon as categoryIcon" +
            " FROM Tbl_budget budget INNER JOIN Tbl_category category ON budget.cat_id=category._id WHERE end_date<:startMillis")
    Maybe<List<BudgetEntry>> getPastBudget(long startMillis);

    @Query("SELECT COUNT(_id) FROM " + Identifiers.TABLE_BUDGET + " WHERE cat_id=:id")
    Maybe<Integer> getCategoryBudgetCount(long id);

    @Query("SELECT COUNT(_id) FROM " + Identifiers.TABLE_BUDGET + " WHERE end_date>:date")
    Maybe<Integer> getBudgetCount(long date);

    @Query("SELECT SUM(amount) as totalAmount FROM " + Identifiers.TABLE_ENTRY + " WHERE cat_id=:id AND date BETWEEN :start AND :end")
    Maybe<Total> getEntryTotalAmountByBudget(long id, long start, long end);
    //endregion


    //region Category
    @Insert
    Maybe<Long> insertCategoryGetId(Category category);

    @Insert
    Maybe<List<Long>> insertCategoryGetId(List<Category> list);

    @Insert
    Completable insertCategory(List<Category> list);

    @Update
    Completable updateCategory(Category category);

    @Update
    Completable updateCategory(List<Category> list);

    @Delete
    Completable deleteCategory(Category... categories);

    @Query("SELECT * FROM " + Identifiers.TABLE_CATEGORY + " WHERE _id=:id")
    Single<Category> getCategory(long id);


    @Query("SELECT COUNT(_id) FROM " + Identifiers.TABLE_CATEGORY)
    Maybe<Integer> getCategoryCount();

    @Query("SELECT * FROM " + Identifiers.TABLE_CATEGORY + " WHERE ie_type=:type ORDER BY name")
    Maybe<List<Category>> getCategoryFromType(int type);

    @Query("SELECT * FROM " + Identifiers.TABLE_CATEGORY + " WHERE ie_type=:type ORDER BY ie_type LIMIT 1")
    Observable<Category> getCategoryFromTypeLive(int type);

    @RawQuery(observedEntities = Category.class)
    Observable<List<Category>> getCategoryLive(SupportSQLiteQuery query);

    @RawQuery
    Maybe<List<Category>> getCategory(SupportSQLiteQuery query);
    //endregion


    //region Entry
    @Insert
    Maybe<Long> insertEntryGetId(Entry entry);

    @Insert
    Completable insertEntry(List<Entry> list);

    @Update
    Completable updateEntry(Entry... entries);

    @Update
    Completable updateEntry(Entry entry);

    @Delete
    Completable deleteEntry(List<Entry> list);

    // TODO: 27-09-2019 add  entry.recieve_acc as receiveId by SAM
    @Query("SELECT entry._id as entryId, entry.amount, entry.date, entry.remaining_amount as remainingAmount, entry.person_name as personName, entry.notes as notes, entry.return_id as referenceId," +
            " entry.account_id as accountId, entry.reminder_date as reminderDate, entry.recieve_acc as receiveId, account.name as accountName, account.icon as accountIcon, account.balance as balance," +
            " entry.cat_id as categoryId, category.ie_type as categoryType, category.name as categoryName, category.icon as categoryIcon," +
            " entry.sub_cat_id as subCategoryId, subCategory.name as subCategoryName" +
            " FROM Tbl_entry entry" +
            " INNER JOIN Tbl_account account ON entry.account_id=account._id" +
            " INNER JOIN Tbl_category category ON entry.cat_id=category._id" +
            " LEFT JOIN Tbl_sub_category subCategory ON entry.sub_cat_id=subCategory._id" +
            " WHERE entry.remaining_amount>0 AND category.ie_type=:type")
    Observable<List<Transaction>> getPayeeEntryLive(int type);

    @RawQuery
    Maybe<List<Transaction>> getTransactionEntry(SupportSQLiteQuery query);

    @RawQuery
    Maybe<List<LoanDebtTransaction>> getLoanDebtTransactionEntry(SupportSQLiteQuery query);

    @Query("SELECT entry.date as date,entry.amount as amount,account.icon as accountIcon FROM "
            + Identifiers.TABLE_ENTRY + " entry  INNER JOIN " + Identifiers.TABLE_ACCOUNT + " account ON entry.account_id=account._id " +
            " WHERE entry.return_id=:id ORDER BY entry.date ASC")
    Maybe<List<SubEntry>> getLoanDebtSubEntry(long id);

    @Query("SELECT entry.date as date,entry.amount as amount,account.icon as accountIcon FROM "
            + Identifiers.TABLE_ENTRY + " entry  INNER JOIN " + Identifiers.TABLE_ACCOUNT + " account ON entry.account_id=account._id " +
            " WHERE entry.cat_id=:id AND entry.date BETWEEN :start AND :end ORDER BY entry.date ASC")
    Maybe<List<SubEntry>> getBudgetSubEntry(long id, long start, long end);

    @Query("DELETE FROM " + Identifiers.TABLE_ENTRY + " WHERE _id=:id")
    Completable deleteEntry(long id);

    @Query("SELECT * FROM " + Identifiers.TABLE_ENTRY + " WHERE _id=:id")
    Single<Entry> getEntry(long id);

    @Query("SELECT * FROM " + Identifiers.TABLE_ENTRY + " WHERE _id=:referenceId")
    Maybe<Entry> getReferenceEntry(long referenceId);

    @Query("SELECT * FROM " + Identifiers.TABLE_ENTRY + " WHERE return_id=:referenceId")
    Maybe<List<Entry>> getEntryByReferenceId(long referenceId);

    @Query("SELECT * FROM " + Identifiers.TABLE_ENTRY + " ORDER BY _id DESC LIMIT 1")
    Maybe<Entry> getLastEntry();

    // TODO: 08-10-2019 create by SAM for get last updated entry
    @Query("SELECT * FROM " + Identifiers.TABLE_ENTRY + " ORDER BY timestamp DESC LIMIT 1")
    Maybe<Entry> getLastUpdateEntry();

    @Query("SELECT COUNT(_id) FROM " + Identifiers.TABLE_ENTRY)
    Maybe<Integer> getEntryCount();

    @Query("SELECT COUNT(_id) FROM " + Identifiers.TABLE_ENTRY + " WHERE account_id=:id")
    Maybe<Integer> getAccountEntryCount(long id);

    @Query("SELECT COUNT(_id) FROM " + Identifiers.TABLE_ENTRY + " WHERE cat_id=:id")
    Maybe<Integer> getCategoryEntryCount(long id);

    @Query("SELECT COUNT(_id) FROM " + Identifiers.TABLE_ENTRY + " WHERE sub_cat_id=:id")
    Maybe<Integer> getSubCategoryEntryCount(long id);
    //endregion


    //region Notification
    @Insert
    Completable insertNotification(Notification... notifications);

    @Delete
    Completable deleteNotification(Notification... notifications);

    @Query("SELECT * FROM " + Identifiers.TABLE_NOTIFICATION + " ORDER BY dateTime DESC")
    Maybe<List<Notification>> getNotification();
    //endregion


    //region SubCategory
    @Insert
    Maybe<Long> insertSubCategory(SubCategory subCategory);

    @Insert
    Completable insertSubCategory(List<SubCategory> list);

    @Insert
    Maybe<List<Long>> insertSubCategoryGetIds(List<SubCategory> list);

    @Update
    Completable updateSubCategory(List<SubCategory> list);

    @Delete
    Completable deleteSubCategory(SubCategory... subCategories);

    @Query("SELECT * FROM " + Identifiers.TABLE_SUB_CATEGORY + " WHERE _id=:id")
    Single<SubCategory> getSubCategory(long id);

    @Query("SELECT * FROM " + Identifiers.TABLE_SUB_CATEGORY + " WHERE cat_id=:category")/*ORDER BY name ASC*/
    Maybe<List<SubCategory>> getSubCategoryByType(long category);
    //endregion


    //region Initial
    @Insert
    Maybe<Long> insertInitial(Initial initial);

    @Insert
    Completable insertInitial(List<Initial> list);
    //endregion


    //region Remark
    @Insert
    Completable insertRemark(Remark... remarks);

    @Delete
    Completable deleteRemark(Remark... remarks);

    @Query("SELECT * FROM " + Identifiers.TABLE_REMARK + " ORDER BY timestamp DESC")
    Maybe<List<Remark>> getRemark();
    //endregion


    //region widget
    @Query("SELECT SUM(balance) as totalAmount FROM " + Identifiers.TABLE_ACCOUNT)
    Maybe<Total> getTotalBalance();

    @Query("SELECT SUM(entry.amount) as totalAmount" +
            " FROM Tbl_entry entry INNER JOIN Tbl_category category ON entry.cat_id=category._id" +
            " WHERE category.ie_type=:type AND entry.date BETWEEN :start AND :end")
    Maybe<Total> getTodayAmount(int type, long start, long end);
    //endregion

    //region for chart
    @RawQuery
    Maybe<List<ChartEntry>> getChartEntry(SupportSQLiteQuery query);

    @Query("SELECT entry.amount" +
            " FROM Tbl_entry entry INNER JOIN Tbl_category category ON entry.cat_id=category._id" +
            " WHERE category.ie_type=:type AND entry.date BETWEEN :start AND :end")
    Maybe<List<Double>> getTimeIntervalAmountList(int type, long start, long end);
    //endregion

    //region For overview
    // TODO: 25-09-2019 by SAM
    @Query("SELECT initial.accountId as accountId,initial.amount as amount,initial.timestamp as timestamp,account.name as name,account.icon as icon FROM Tbl_initial initial INNER JOIN Tbl_account account ON initial.accountId=account._id WHERE initial.timestamp BETWEEN :start AND :end")
    Maybe<List<OverviewAccount>> getInitialAccount(long start, long end);// TODO: 25-09-2019 by SAM


    @Query("SELECT * FROM " + Identifiers.TABLE_INITIAL + " WHERE accountId=:id")
    Observable<Initial> getAccountInitialData(long id);

    @Query("SELECT amount FROM " + Identifiers.TABLE_INITIAL + " WHERE timestamp BETWEEN :start AND :end")
    Maybe<List<Double>> getInitialAmountEntry(long start, long end);

    @Query("SELECT amount FROM " + Identifiers.TABLE_INITIAL + " WHERE timestamp<=:end")
    Maybe<List<Double>> getInitialAmountEntry(long end);

    @Query("SELECT category.ie_type as type, entry.amount as amount, entry.account_id as accountId" +
            " FROM Tbl_entry entry INNER JOIN Tbl_category category ON entry.cat_id=category._id" +
            " WHERE entry.date BETWEEN :start AND :end")
    Maybe<List<OverviewEntry>> getOverviewEntry(long start, long end);

    // TODO: 06-09-2019 change =@ end
    @Query("SELECT category.ie_type as type, entry.amount as amount, entry.account_id as accountId" +
            " FROM Tbl_entry entry INNER JOIN Tbl_category category ON entry.cat_id=category._id" +
            " WHERE entry.date<:end")
    Maybe<List<OverviewEntry>> getOverviewEntry(long end);

    @Query("SELECT category.ie_type as type, entry.remaining_amount as amount, entry.account_id as accountId" +
            " FROM Tbl_entry entry INNER JOIN Tbl_category category ON entry.cat_id=category._id" +
            " WHERE category.ie_type=2 OR category.ie_type=3")
    Maybe<List<OverviewEntry>> getLoanDebtOverviewEntry();
    //endregion

    //region For changeLog
    @Insert
    Completable insertLog(ChangeLog... changeLogs);

    @Query("DELETE FROM " + Identifiers.TABLE_CHANGE_LOG)
    Completable clearChangeLog();
    /*@Update
    Completable updateLog(ChangeLog... changeLogs);

    @Delete
    Completable deleteLog(ChangeLog... changeLogs);*/
    //endregion

    //region For backup
    @Query("SELECT * FROM " + Identifiers.TABLE_ACCOUNT)
    Maybe<List<Account>> getAllAccount();

    @Query("SELECT * FROM " + Identifiers.TABLE_CATEGORY)
    Maybe<List<Category>> getAllCategory();

    @Query("SELECT * FROM " + Identifiers.TABLE_SUB_CATEGORY)
    Maybe<List<SubCategory>> getAllSubCategory();

    @Query("SELECT * FROM " + Identifiers.TABLE_ENTRY)
    Maybe<List<Entry>> getAllEntry();

    @Query("SELECT * FROM " + Identifiers.TABLE_BUDGET)
    Maybe<List<Budget>> getAllBudget();

    @Query("SELECT * FROM " + Identifiers.TABLE_INITIAL)
    Maybe<List<Initial>> getAllInitial();

    @Query("SELECT * FROM " + Identifiers.TABLE_CHANGE_LOG)
    Maybe<List<ChangeLog>> getAllChangeLog();

    @Insert
    Maybe<List<Long>> insertAccountGetIds(List<Account> list);
    //endregion
}