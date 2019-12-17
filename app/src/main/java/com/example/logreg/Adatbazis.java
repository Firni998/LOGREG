package com.example.firni.zado;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Adatbazis extends SQLiteOpenHelper{


    public static final String DATABASE_NAME = "felhasznalok.db";

    public static final String TABLE_NAME = "felhasznalo";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "felhnev";
    public static final String COL_3 = "jelszo";
    public static final String COL_4 = "email";
    public static final String COL_5 = "teljesnev";



    public Adatbazis(Context context){
        super(context,DATABASE_NAME,null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table " + TABLE_NAME + " " + "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " felhnev text UNIQUE not null," +
                "jelszo text not null," +
                "email text UNIQUE not null," +
                "teljesnev text not null)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
    }
    public boolean beolvas(String felhnev,String jelszo, String email, String teljesnev){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,felhnev);
        contentValues.put(COL_3,jelszo);
        contentValues.put(COL_4,email);
        contentValues.put(COL_5,teljesnev);


        long beolvasas = db.insert(TABLE_NAME, null,contentValues);

        if(beolvasas == -1)
            {
                return false;
            }
        else
            {
                return true;
            }
        }
        public boolean ellenorzesFelhnev(String felhnev){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from felhasznalok where felhasznalo=?", new String []{felhnev});

        if(cursor.getCount()>0)
        {
            return false;
        }
        else
            {
            return true;
            }
        }

        public boolean felhasznaloesjelszo(String felhnev,String jelszo){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from felhasznalo where felhnev=? and jelszo=?", new String[]{felhnev,jelszo});
        if(cursor.getCount()>0)
        {
            return false;
        }
        else
            {
            return true;
            }
        }
        public boolean ellenorzesNevEmail(String felhnev,String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from felhasznalo where felhnev=? and email=?", new String[]{felhnev,email});
        if(cursor.getCount()>0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }




}
