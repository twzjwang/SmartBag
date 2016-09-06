package com.example.wang.smartbag;

/**
 * Created by Wang on 2016/8/26.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteImplement extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Record.db";
    public static final String TABLE_NAME = "record_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "DAY";
    public static final String COL_4 = "TAG";
    public static final String COL_5 = "STATE";
    public static final String COL_6 = "REMARK1";
    public static final String COL_7 = "REMARK2";

    public SQLiteImplement(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, DAY TEXT, TAG TEXT, STATE INTEGER, REMARK1 TEXT, REMARK2 TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public Data insertData(Data data) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, data.getName());
        contentValues.put(COL_3, data.getDay());
        contentValues.put(COL_4, data.getTag());
        contentValues.put(COL_5, data.getState());
        contentValues.put(COL_6, data.getRemark1());
        contentValues.put(COL_7, data.getRemark2());
        long id = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        data.setId(id);
        return data;
    }

    public boolean updateData(Data data) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, data.getId());
        contentValues.put(COL_2, data.getName());
        contentValues.put(COL_3, data.getDay());
        contentValues.put(COL_4, data.getTag());
        contentValues.put(COL_5, data.getState());
        contentValues.put(COL_6, data.getRemark1());
        contentValues.put(COL_7, data.getRemark2());
        String where = COL_1 + "=" + data.getId();
        return sqLiteDatabase.update(TABLE_NAME, contentValues, where, null) > 0;
    }

    public boolean deleteData(long id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String where = COL_1 + "=" + id;
        return sqLiteDatabase.delete(TABLE_NAME, where, null) > 0;
    }

    public Data getData(long id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Data result = new Data(null, null, null, -1, null, null);
        String where = COL_1 + "=" + id;
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, where, null, null, null, null);

        if (cursor.moveToFirst()) {
            result.setId(cursor.getLong(0));
            result.setName(cursor.getString(1));
            result.setDay(cursor.getString(2));
            result.setTag(cursor.getString(3));
            result.setState(cursor.getInt(4));
            result.setRemark1(cursor.getString(5));
            result.setRemark2(cursor.getString(6));
            Log.d("Get Event", cursor.getLong(0) + cursor.getString(1) + cursor.getString(2) + cursor.getString(3) + cursor.getInt(4) + cursor.getString(5) + cursor.getString(6));
        }

        cursor.close();
        return result;
    }

    public int getCount() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int result = 0;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT MAX(" + COL_1 + ") FROM " + TABLE_NAME, null);

        if (cursor.moveToNext())
            result = cursor.getInt(0);
        return result;
    }

    public void closeDatabase() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.close();
    }
}
