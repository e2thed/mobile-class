package seng.contactsviewer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {
    // Table Name
    public static final String TABLE_NAME = "contact_info";

    // Table Columns
    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String TITLE = "title";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    public static final String TWITTER_ID = "twitterId";

    // Database Information
    public static final String DB_NAME = "contact_db";

    // Database Version
    static final int DB_VERSION = 2;

    // Create Table Query
    public String CREATE_QUERY = "CREATE TABLE " + TABLE_NAME + " ("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NAME + " TEXT,"
            + TITLE + " TEXT,"
            + PHONE + " TEXT,"
            + EMAIL + " TEXT,"
            + TWITTER_ID + " TEXT );";

    public DBhelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
