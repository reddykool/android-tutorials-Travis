package com.example.reddyz.travistutorials1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Reddyz on 21-10-2016.
 */
public class SqlHandleData {

    public static final String KEY_ROW_ID = "_id";
    public static final String KEY_NAME = "person_name";
    public static final String KEY_AGE = "person_age";

    private static final String DATABASE_NAME = "Profiledb";
    private static final String DATABASE_TABLE = "PeopleTable";
    private static final int DATABASE_VERSION = 1;

    private DbHelper myDbHelper;
    private final Context myContext;
    private SQLiteDatabase myDatabase;

    public SqlHandleData(Context cntx) {
        myContext = cntx;
    }

    public SqlHandleData open() throws SQLException{
        myDbHelper = new DbHelper(myContext);
        myDatabase = myDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        myDbHelper.close();
    }

    public long createEntry(String name, String age) {
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_NAME, name);
        cValues.put(KEY_AGE, age);
        return myDatabase.insert(DATABASE_TABLE, null,cValues);
    }

    public String getData() {
        String [] columns = new String[] {KEY_ROW_ID, KEY_NAME, KEY_AGE};
        Cursor c = myDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
        String result = "";

        int iRow = c.getColumnIndex(KEY_ROW_ID);
        int iName = c.getColumnIndex(KEY_NAME);
        int iAge = c.getColumnIndex(KEY_AGE);

        for(c.moveToFirst(); !c.isAfterLast();c.moveToNext()) {
            result = result + c.getString(iRow) +" "+ c.getString(iName)+" "+c.getString(iAge)+"\n";
        }

        return result;
    }

    public String getName(long id) throws SQLException{
        String [] columns = new String[] {KEY_ROW_ID, KEY_NAME, KEY_AGE};
        Cursor c = myDatabase.query(DATABASE_TABLE, columns, KEY_ROW_ID + "=" + id, null, null, null, null);
        int iName = c.getColumnIndex(KEY_NAME);
        if(c != null){
            c.moveToFirst();
            String name = c.getString(iName);
            return name;
        }
        return null;
    }

    public String getAge(long id) throws SQLException{
        String [] columns = new String[] {KEY_ROW_ID, KEY_NAME, KEY_AGE};
        Cursor c = myDatabase.query(DATABASE_TABLE, columns, KEY_ROW_ID + "=" + id, null, null, null, null);
        int iAge = c.getColumnIndex(KEY_AGE);
        if(c != null){
            c.moveToFirst();
            String age = c.getString(iAge);
            return age;
        }
        return null;
    }

    public void EditEntry(long id, String name, String age) throws SQLException{
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_NAME, name);
        cValues.put(KEY_AGE, age);
        myDatabase.update(DATABASE_TABLE,cValues,KEY_ROW_ID+"="+id,null);
    }

    public void deleteEntry(long id) throws SQLException{
        myDatabase.delete(DATABASE_TABLE, KEY_ROW_ID+"="+id,null);
    }

    private static class DbHelper extends SQLiteOpenHelper {

        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String query =  "CREATE TABLE " + DATABASE_TABLE + " (" +
                    KEY_ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    KEY_NAME + " TEXT NOT NULL, " +
                    KEY_AGE + " TEXT NOT NULL);";
            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String query = "DROP TABLE IF EXISTS " + DATABASE_TABLE;
            db.execSQL(query);
            onCreate(db);
        }
    }
}
