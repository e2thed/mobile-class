package seng.contactsviewer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import seng.contactsviewer.TableData.TableInfo;

/**
 * Created by us296865 on 2/20/2015.
 */
public class DatabaseOperations extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public String CREATE_QUERY = "CREATE TABLE " + TableInfo.TABLE_NAME + " ("
            + TableInfo.NAME + " TEXT,"
            + TableInfo.TITLE + " TEXT,"
            + TableInfo.PHONE + " TEXT,"
            + TableInfo.EMAIL + " TEXT,"
            + TableInfo.TWITTER_ID + " TEXT );";


    public DatabaseOperations(Context context) {
        super(context, TableInfo.DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("Database operations", "Database created");

        // edc - This is my attempt to seed for initial use.
        // It looks like it will run each time and use the last data as well
        // not quite want I wanted to do
        // seedDB();
    }

    /// edc - This is intended to populate the database on initial use
    /// perhaps there should be logic in the list activity that handles the initial load
    private void seedDB() {
        List<Contact> contacts = new ArrayList<Contact>();

        contacts.add(new Contact("Malcom Reynolds", "Captain", "612-555-1234", "mreynolds@gmail.com", "@mreynolds"));
        contacts.add(new Contact("Jayne Cobb", "Muscle", "612-555-2345", "jcobb@gmail.com", "@jcobb"));
        contacts.add(new Contact("Hoban Washburne", "Pilot", "612-555-3456", "hwashburne@gmail.com", "@mrwash"));
        contacts.add(new Contact("Kaylee Frye", "Engineer", "612-555-4567", "kfrye@gmail.com", "@kfrye"));
        contacts.add(new Contact("Zoe Washburne", "Engineer", "612-555-5678", "zwashburne@gmail.com", "@mrswash"));
        contacts.add(new Contact("Simon Tam", "Doctor", "612-555-6789", "zwashburne@gmail.com", "@mrswash"));

        for (Contact contact : contacts) {
            insertContact(this, contact);
        }
        Log.d("Database operations", "Database seeded");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_QUERY);
        Log.d("Database operations", "Table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertContact(DatabaseOperations dop, Contact contact) {
        SQLiteDatabase sq = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TableInfo.NAME, contact.getName());
        cv.put(TableInfo.TITLE, contact.getTitle());
        cv.put(TableInfo.PHONE, contact.getPhone());
        cv.put(TableInfo.EMAIL, contact.getEmail());
        cv.put(TableInfo.TWITTER_ID, contact.getTwitterId());

        long k = sq.insert(TableInfo.TABLE_NAME, null, cv);
        Log.d("Database operations", "One row inserted");

    }

    public Cursor getContacts(DatabaseOperations databaseOperations) {
        SQLiteDatabase sq = databaseOperations.getReadableDatabase();
        String[] columns =
                {
                        TableInfo.NAME,
                        TableInfo.TITLE,
                        TableInfo.PHONE,
                        TableInfo.EMAIL,
                        TableInfo.TWITTER_ID
                };
        Cursor cr = sq.query(TableInfo.TABLE_NAME, columns, null, null, null, null, columns[0]);
        return cr;
    }
}

