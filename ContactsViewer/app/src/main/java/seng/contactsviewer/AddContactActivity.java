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

import java.sql.SQLException;


public class AddContactActivity extends Activity {
    EditText NAME, TITLE, PHONE, EMAIL, TWITTER_ID;
    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

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
                Contact newContact = new Contact();

                newContact.setName(NAME.getText().toString());
                newContact.setTitle(TITLE.getText().toString());
                newContact.setPhone(PHONE.getText().toString());
                newContact.setEmail(EMAIL.getText().toString());
                newContact.setTwitterId(TWITTER_ID.getText().toString());
                returnIntent.putExtra("Contact", newContact);

                SQLController dbCon = new SQLController(ctx);
                try {
                    dbCon.open();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                dbCon.insert(newContact);
                Toast.makeText(getBaseContext(), "Contact Added", Toast.LENGTH_LONG).show();

                setResult(RESULT_OK);
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean handled = true;

        int id = item.getItemId();

        switch (id){
            case android.R.id.home:
                Intent listContactsActivityIntent = new Intent(this, ListContactsActivity.class);
                startActivityForResult(listContactsActivityIntent, 1);
                break;
            default:
                handled = super.onOptionsItemSelected(item);
        }

        return handled;
    }
}
