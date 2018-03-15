package com.example.android.waitlist.data;

import android.provider.BaseColumns;

public class WaitlistContract {



    // COMPLETE (1) Create an inner class named WaitlistEntry class that implements the BaseColumns interface
        // COMPLETE (2) Inside create a static final members for the table name and each of the db columns
        // TABLE_NAME -> waitlist;
        // COLUMN_GUEST_NAME -> guestName
        // COLUMN_PARTY_SIZE -> partySize
        // COLUMN_TIMESTAMP -> timestamp

    public static final class WaitlistEntry implements BaseColumns{

      public static final String WAITLIST_TABLE = "waitlist";
      public static final String COLUMN_GUESTNAME = "guestName";
      public static final String COLUMN_PARTYSIZE = "partySize";
      public static final String COLUMN_TIMESTAMP = "timestamp";
      public static final String COLUMN_ID = "index";

    }
}
