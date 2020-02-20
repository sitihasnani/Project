package com.example.yasmin.redditclone.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static  final  String DATABASE_NAME ="reddit.db";
    public static  String DATABASE_PATH ="";
    public static  final  String TABLE_NAME ="topic_table";
    public static  final  String COL_1 ="id";
    public static  final  String COL_2 ="title";
    public static  final  String COL_3 ="description";
    public static  final  String COL_4 ="upvote";
    public static  final  String COL_5 ="downvote";
    private final Context myContext;
    SQLiteDatabase db;


    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
        if (Build.VERSION.SDK_INT >= 15){
            DATABASE_PATH = context.getApplicationInfo().dataDir + "/database/";
        } else {
            DATABASE_PATH = Environment.getDataDirectory() + "/data/data/" + context.getPackageName() + "/database/";
        }

        SQLiteDatabase db = this.getWritableDatabase();
        this.myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE table " + TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT, upvote INTEGER, downvote INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME );
        onCreate(sqLiteDatabase);

    }

//    public void checkAndCopyDatabase(){
//        boolean dbExist = checkDatabase();
//
//        if (dbExist){
//            Log.d("TAG", "db already exist");
//        }else {
//            this.getReadableDatabase();
//        }
//        try {
//            copyDatabase();
//        } catch (IOException e) {
//            e.printStackTrace();
//            Log.e("TAG", "Error copy database");
//        }
//
//    }
//
//    public boolean checkDatabase(){
//        SQLiteDatabase checkdb = null;
//        String myPath = DATABASE_PATH+DATABASE_NAME;
//        checkdb = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
//
//        if (checkdb != null){
//            checkdb.close();
//        }
//        return checkdb != null ? true : false;
//
//    }
//
//    public void copyDatabase() throws IOException{
//        InputStream myInput = myContext.getAssets().open(DATABASE_NAME);
//        String outputFile = DATABASE_PATH + DATABASE_NAME;
//        OutputStream myoutput = new FileOutputStream(outputFile);
//        byte[] buffer = new byte[1024];
//        int length;
//        while ((length = myInput.read(buffer))>0){
//            myoutput.write(buffer, 0, length);
//        }
//        myoutput.flush();
//        myoutput.close();
//        myInput.close();
//    }
//
//    public synchronized void close(){
//        if (db != null){
//            db.close();
//        }
//        super.close();
//    }
//
//
//    public void openDatabase(){
//
//        String myPath = DATABASE_PATH + DATABASE_NAME;
//        db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
//
//    }
//
//    public Cursor QueryData(String query){
//        return db.rawQuery(query, null);
//    }
//
    public DatabaseHelper open() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();

        return this;
    }

    public Cursor loadData(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME+" ORDER BY upvote DESC limit 20", null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;

    }


    public boolean insertData(String title, String description, int upvote, int downvote){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, title);
        contentValues.put(COL_3, description);
        contentValues.put(COL_4, upvote);
        contentValues.put(COL_5, downvote);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1)
            return false;
            else
                return true;
    }

    public Cursor updateUpvote(int upvote, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("UPDATE "+ TABLE_NAME+ " SET " + COL_4 + "="+upvote+" WHERE "+COL_1+"="+id, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor updateDownvote(int downvote, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("UPDATE "+ TABLE_NAME+ " SET " + COL_5 + "="+downvote+" WHERE "+COL_1+"="+id, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }


}
