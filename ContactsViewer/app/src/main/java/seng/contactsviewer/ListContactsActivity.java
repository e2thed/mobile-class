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

import java.util.ArrayList;
import java.util.List;


public class ListContactsActivity extends ListActivity {
    Context ctx = this;

    private List<Contact> contacts = new ArrayList<Contact>();
    private ListView contactListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contacts);
        contactListView = getListView();

        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent editContactIntent = new Intent(view.getContext(), EditContactActivity.class);
                editContactIntent.putExtra("Contact", contacts.get(position));
                startActivity(editContactIntent);
            }
        });

//        contacts.add(new Contact("Kaylee", "Engineer", "555-1212", "k@g.com", "@k"));
//        contacts.add(new Contact("Rog", "Golfer", "555-1212", "k@g.com", "@k"));
//        contacts.add(new Contact("Alabama Jones", "Engineer", "555-1212", "k@g.com", "@k"));

        DatabaseOperations dop = new DatabaseOperations(ctx);
        Cursor cr = dop.getContacts(dop);

        cr.moveToFirst();
        do {
            contacts.add(new Contact(cr.getString(0), cr.getString(1), cr.getString(2), cr.getString(3), cr.getString(4)));

        } while (cr.moveToNext());


        setListAdapter(new ContactAdapter(this, R.layout.contact_item, contacts));
    }

//    @Override
//    protected void onListItemClick(ListView l, View v, int position, long id){
//        Intent editContactIntent = new Intent(this, EditContactActivity.class);
//        startActivityForResult(editContactIntent, 1);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_contacts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
}

