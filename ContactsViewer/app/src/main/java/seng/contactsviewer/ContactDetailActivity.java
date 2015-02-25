package seng.contactsviewer;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.sql.SQLException;


public class ContactDetailActivity extends Activity {
    TextView NAME, TITLE, PHONE, EMAIL, TWITTER_ID;
    Contact contact;
    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        NAME = (TextView) findViewById(R.id.item_name);
        TITLE = (TextView) findViewById(R.id.item_title);
        PHONE = (TextView) findViewById(R.id.item_phone);
        EMAIL = (TextView) findViewById(R.id.item_email);
        TWITTER_ID = (TextView) findViewById(R.id.item_twitterId);

        Serializable extra = getIntent().getSerializableExtra("Contact");
        if (extra != null) {
            contact = (Contact) extra;
            NAME.setText(contact.getName());
            TITLE.setText(contact.getTitle());
            PHONE.setText(contact.getPhone());
            EMAIL.setText(contact.getEmail());
            TWITTER_ID.setText(contact.getTwitterId());
        }

        final Button editButton = (Button) findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editContactsActivityIntent = new Intent(v.getContext(), EditContactActivity.class);
                editContactsActivityIntent.putExtra("Contact", contact);
                startActivityForResult(editContactsActivityIntent, 1);
            }
        });

        final Button deleteButton = (Button) findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteContact();
                setResult(RESULT_OK);
                finish();
            }
        });

    }

    private void deleteContact() {
        SQLController dbCon = new SQLController(ctx);
        try {
            dbCon.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbCon.delete(Long.parseLong(contact.get_Id()));
        Toast.makeText(ctx, "Contact Deleted", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean handled = true;

        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                Intent listContactsActivityIntent = new Intent(this, ListContactsActivity.class);
                startActivityForResult(listContactsActivityIntent, 1);
                break;
            case R.id.action_edit:
                Intent editContactsActivityIntent = new Intent(this, EditContactActivity.class);
                editContactsActivityIntent.putExtra("Contact", contact);
                startActivityForResult(editContactsActivityIntent, 1);
                break;
            case R.id.action_delete:
                deleteContact();
                setResult(RESULT_OK);
                finish();
                break;
            default:
                handled = super.onOptionsItemSelected(item);
        }
        return handled;
    }
}
