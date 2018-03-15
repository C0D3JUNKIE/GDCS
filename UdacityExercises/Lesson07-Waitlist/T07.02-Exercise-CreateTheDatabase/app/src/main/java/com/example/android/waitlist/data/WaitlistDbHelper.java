package com.example.android.waitlist.data;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.waitlist.data.WaitlistContract.WaitlistEntry;

// COMPLETE (1) extend the SQLiteOpenHelper class
public class WaitlistDbHelper extends SQLiteOpenHelper{

    // COMPLETE (2) Create a static final String called DATABASE_NAME and set it to "waitlist.db"
    // COMPLETE (3) Create a static final int called DATABASE_VERSION and set it to 1
    public static final String DATABASE_NAME = "waitlist.db";
    public static final int DATABASE_VERSION = 1;

    // COMPLETE (4) Create a Constructor that takes a context and calls the parent constructor
  public WaitlistDbHelper(Context context, String name, CursorFactory factory, int version) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  // COMPLETE (5) Override the onCreate method
  @Override
  public void onCreate(SQLiteDatabase db) {
    // COMPLETE (6) Inside, create an String query called SQL_CREATE_WAITLIST_TABLE that will create the table
    // COMPLETE (7) Execute the query by calling execSQL on sqLiteDatabase and pass the string query SQL_CREATE_WAITLIST_TABLE
    final String SQL_CREATE_WAITLIST_TABLE = "CREATE TABLE " + WaitlistEntry.TABLE_NAME + " (" +
        WaitlistEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + WaitlistEntry.COLUMN_GUEST_NAME +
        " TEXT NOT NULL, " + WaitlistEntry.COLUMN_PARTY_SIZE + " INTEGER NOT NULL, " +
        WaitlistEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" + ");";
  }

    // COMPLETE (8) Override the onUpgrade method
    // COMPLETE (9) Inside, execute a drop table query, and then call onCreate to re-create it
  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + WaitlistEntry.TABLE_NAME);
    onCreate(db);
  }

}