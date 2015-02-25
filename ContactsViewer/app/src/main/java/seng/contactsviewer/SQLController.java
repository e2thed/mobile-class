package seng.contactsviewer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

public class SQLController {

    private DBhelper dbHelper;
    private Context ourContext;
    private SQLiteDatabase database;

    public SQLController(Context c) {
        ourContext = c;
    }

    public SQLController open() throws SQLException {
        dbHelper = new DBhelper(ourContext);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(Contact contact) {
        ContentValues cv = new ContentValues();
        cv.put(DBhelper.NAME, contact.getName());
        cv.put(DBhelper.TITLE, contact.getTitle());
        cv.put(DBhelper.PHONE, contact.getPhone());
        cv.put(DBhelper.EMAIL, contact.getEmail());
        cv.put(DBhelper.TWITTER_ID, contact.getTwitterId());
        database.insert(DBhelper.TABLE_NAME, null, cv);
    }

    public Cursor fetch() {
        String[] columns = new String[]{
                DBhelper._ID,
                DBhelper.NAME,
                DBhelper.TITLE,
                DBhelper.PHONE,
                DBhelper.EMAIL,
                DBhelper.TWITTER_ID,
        };

        Cursor cursor = database.query(
                DBhelper.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, Contact contact) {
        ContentValues cv = new ContentValues();
        cv.put(DBhelper.NAME, contact.getName());
        cv.put(DBhelper.TITLE, contact.getTitle());
        cv.put(DBhelper.PHONE, contact.getPhone());
        cv.put(DBhelper.EMAIL, contact.getEmail());
        cv.put(DBhelper.TWITTER_ID, contact.getTwitterId());

        int i = database.update(
                DBhelper.TABLE_NAME,
                cv,
                DBhelper._ID + " = " + _id,
                null);

        return i;
    }

    public void delete(long _id) {
        database.delete(DBhelper.TABLE_NAME, DBhelper._ID + " = " + _id, null);
    }
}

