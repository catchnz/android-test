package com.example.chanx.test.data.source.local;

import android.provider.BaseColumns;

/**
 * Created by chanx on 01/10/2017.
 */

public class EssaysPersistenceContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private EssaysPersistenceContract() {
    }

    /* Inner class that defines the table contents */
    public static abstract class EssayEntry implements BaseColumns {
        public static final String TABLE_NAME = "essay";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";
        public static final String COLUMN_NAME_CONTENT = "content";
    }

}
