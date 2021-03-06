package seng.contactsviewer;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.sql.SQLException;


public class EditContactActivity extends Activity {
    EditText NAME, TITLE, PHONE, EMAIL, TWITTER_ID;
    Contact contact;
    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        NAME = (EditText) findViewById(R.id.item_name);
        TITLE  = (EditText) findViewById(R.id.item_title);
        PHONE = (EditText) findViewById(R.id.item_phone);
        EMAIL = (EditText) findViewById(R.id.item_email);
        TWITTER_ID = (EditText) findViewById(R.id.item_twitterId);

        Serializable extra = getIntent().getSerializableExtra("Contact");
        if (extra != null)
        {
            contact = (Contact)extra;
            NAME.setText(contact.getName());
            TITLE.setText(contact.getTitle());
            PHONE.setText(contact.getPhone());
            EMAIL.setText(contact.getEmail());
            TWITTER_ID.setText(contact.getTwitterId());
        }

        final Button saveButton = (Button)findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NAME = (EditText) findViewById(R.id.item_name);
                TITLE = (EditText) findViewById(R.id.item_title);
                PHONE = (EditText) findViewById(R.id.item_phone);
                EMAIL = (EditText) findViewById(R.id.item_email);
                TWITTER_ID = (EditText) findViewById(R.id.item_twitterId);

                Intent returnIntent = new Intent();
                Contact updatedContact = new Contact();
                updatedContact.set_Id(contact.get_Id());
                updatedContact.setName(NAME.getText().toString());
                updatedContact.setTitle(TITLE.getText().toString());
                updatedContact.setPhone(PHONE.getText().toString());
                updatedContact.setEmail(EMAIL.getText().toString());
                updatedContact.setTwitterId(TWITTER_ID.getText().toString());
                returnIntent.putExtra("Contact", updatedContact);

                SQLController dbCon = new SQLController(ctx);
                try {
                    dbCon.open();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                dbCon.update(Long.parseLong(contact.get_Id()), updatedContact);
                Toast.makeText(getBaseContext(), "Contact Updated", Toast.LENGTH_LONG).show();

                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean handled = true;

        int id = item.getItemId();

        switch (id){
            case android.R.id.home:
                Intent contactDetailActivityIntent = new Intent(this, ContactDetailActivity.class);
                startActivityForResult(contactDetailActivityIntent, 1);
                break;
            default:
                handled = super.onOptionsItemSelected(item);
        }

        return handled;
    }
}
