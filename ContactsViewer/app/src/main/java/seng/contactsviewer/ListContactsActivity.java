package seng.contactsviewer;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;


public class ListContactsActivity extends ListActivity {
    Context ctx = this;
    private List<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contacts);

        ListView contactListView = getListView();

        populateList();

        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent contactDetailIntent = new Intent(view.getContext(), ContactDetailActivity.class);
                contactDetailIntent.putExtra("Contact", contacts.get(position));
                startActivityForResult(contactDetailIntent, 1);
            }
        });
    }

//    @Override
//    protected void onListItemClick(ListView l, View v, int position, long id) {
//        Intent contactDetailIntent = new Intent(ctx, ContactDetailActivity.class);
//        startActivityForResult(contactDetailIntent, 1);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_contacts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean handled = true;
        int id = item.getItemId();

        switch (id) {
            case R.id.action_add_contact:
                Intent addContactIntent = new Intent(ctx, AddContactActivity.class);
                startActivityForResult(addContactIntent,1);
                Log.d("ListActivity", "Going to AddActivity");
                break;
            case R.id.action_close:
                onClickMenuClose();
                break;
            default:
                handled = super.onOptionsItemSelected(item);
        }

        return handled;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        populateList();
    }

    void onClickMenuClose() {
        finish();
    }

    class ContactAdapter extends ArrayAdapter<Contact> {

        public ContactAdapter(Context context, int resource, List<Contact> objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            if (convertView == null) {
                view = getLayoutInflater().inflate(R.layout.contact_item, parent, false);
            } else {
                view = convertView;
            }

            Contact contact = getItem(position);

            TextView nameView = (TextView) view.findViewById(R.id.item_name);
            TextView titleView = (TextView) view.findViewById(R.id.item_title);
            TextView phoneView = (TextView) view.findViewById(R.id.item_phone);
            TextView emailView = (TextView) view.findViewById(R.id.item_email);
            TextView twitterIdView = (TextView) view.findViewById(R.id.item_twitterId);

            nameView.setText(contact.getName());
            titleView.setText(contact.getTitle());
            phoneView.setText(contact.getPhone());
            emailView.setText(contact.getEmail());
            twitterIdView.setText(contact.getTwitterId());

            return view;
        }
    }

    private void populateList() {
        SQLController dbcon = new SQLController(this);
        try {
            dbcon.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Cursor cr = dbcon.fetch();

        if (cr != null && cr.moveToFirst()) {
            cr.moveToFirst();
            contacts = new ArrayList<>();
            do {
                contacts.add(new Contact(
                        cr.getString(0),
                        cr.getString(1),
                        cr.getString(2),
                        cr.getString(3),
                        cr.getString(4),
                        cr.getString(5)));

            } while (cr.moveToNext());

            setListAdapter(new ContactAdapter(this, R.layout.contact_item, contacts));
        }
        dbcon.close();
    }
}

