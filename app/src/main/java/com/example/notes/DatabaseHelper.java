package com.example.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "notes.db";
    public static final String TABLE_NAME = "notes_TABLE";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "SUBJECT";
    public static final String COL_3 = "NOTES";
    public static final String COL_4 = "IMAGE";
    public static final String COL_5 = "LATTITUDE";
    public static final String COL_6 = "LONGITUDE";




    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

        SQLiteDatabase db = this.getWritableDatabase();


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME +"(IDN INTEGER PRIMARY KEY AUTOINCREMENT ,SUBJECT TEXT,NOTES TEXT,IMAGE BLOB,LATTITUDE DOUBLE,LONGITUDE DOUBLE)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);


    }
    public boolean insertdata(String subject,String notes,byte[] image,Double lattitude,Double longitude){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues  contentValues = new ContentValues();
        contentValues.put(COL_2,subject);
        contentValues.put(COL_3,notes);
        contentValues.put(COL_4, image);
        contentValues.put(COL_5, lattitude);
        contentValues.put(COL_6, longitude);

        long result = db.insert(TABLE_NAME,null,contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM "+TABLE_NAME ,null);
        Log.d("Chk","SELECT * FROM ");
        return  data;

    }

    public Cursor getListContentsSingle(String name){
        SQLiteDatabase db = this.getWritableDatabase();

        //  return  data;
       return db.rawQuery("SELECT * FROM "+TABLE_NAME + " Where " + COL_2 + " = '" + name + "'",null);
    }
    public Cursor getListContentsSearch(String name){
        SQLiteDatabase db = this.getWritableDatabase();

        //  return  data;
        //Select * from table where subject like '%%'
        return db.rawQuery("SELECT * FROM "+TABLE_NAME + " Where " + COL_2 + " like '%" + name + "%'",null);
    }


    public Cursor deletesinglecontent(String name){
        SQLiteDatabase db = this.getWritableDatabase();

        //  return  data;
        return db.rawQuery("delete from "+TABLE_NAME + " Where " + COL_2 + " = '" + name + "'",null);
    }

}
