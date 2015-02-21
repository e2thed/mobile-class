package seng.contactsviewer;

import android.provider.BaseColumns;

/**
 * Created by us296865 on 2/20/2015.
 */
public class TableData {
    public TableData()
    {
    }

    public static abstract class TableInfo implements BaseColumns
    {
        public static final String NAME = "name";
        public static final String TITLE = "title";
        public static final String EMAIL = "email";
        public static final String PHONE = "phone";
        public static final String TWITTER_ID = "twitterId";

        public static final String DATABASE_NAME = "contact_db";
        public static final String TABLE_NAME = "contact_info";

    }
}
