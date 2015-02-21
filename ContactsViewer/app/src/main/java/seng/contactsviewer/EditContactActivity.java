package seng.contactsviewer;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;


public class EditContactActivity extends ActionBarActivity {
    EditText NAME, TITLE, PHONE, EMAIL, TWITTER_ID;
    String name, title, phone, email, twitter_id;
    Context ctx = this;

    private boolean isInEditMode = false, isEditable = true;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        NAME = (EditText) findViewById(R.id.item_name);
        TITLE  = (EditText) findViewById(R.id.item_title);
        PHONE = (EditText) findViewById(R.id.item_phone);
        EMAIL = (EditText) findViewById(R.id.item_email);
        TWITTER_ID = (EditText) findViewById(R.id.item_twitterId);

        Serializable extra = getIntent().getSerializableExtra("Contact");
        if (extra != null)
        {
            Contact contact = (Contact)extra;
            NAME.setText(contact.getName());
            TITLE.setText(contact.getTitle());
            PHONE.setText(contact.getPhone());
            EMAIL.setText(contact.getEmail());
            TWITTER_ID.setText(contact.getTwitterId());

            initializeEditState();
        }

        saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleControlEditState();

                if(saveButton.getText().toString() == "Edit") {
                    Contact newContact = new Contact();
                    newContact.setName(NAME.getText().toString());
                    newContact.setTitle(TITLE.getText().toString());
                    newContact.setPhone(PHONE.getText().toString());
                    newContact.setEmail(EMAIL.getText().toString());
                    newContact.setTwitterId(TWITTER_ID.getText().toString());

                    DatabaseOperations db = new DatabaseOperations(ctx);
                    db.insertContact(db, newContact);
                    Toast.makeText(getBaseContext(), "Contact Added", Toast.LENGTH_LONG).show();
                    finish();
                }
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

    private void toggleControlEditState()
    {
        String editMode = isInEditMode ? "Edit" : "Save" ;
        saveButton.setText(editMode);


        NAME.setEnabled(isEditable);
        TITLE.setEnabled(isEditable);
        PHONE.setEnabled(isEditable);
        EMAIL.setEnabled(isEditable);
        TWITTER_ID.setEnabled(isEditable);

        isInEditMode = !isInEditMode;
        isEditable = !isEditable;
    }

    private void initializeEditState()
    {
        NAME.setEnabled(false);
        TITLE.setEnabled(false);
        PHONE.setEnabled(false);
        EMAIL.setEnabled(false);
        TWITTER_ID.setEnabled(false);
    }
}
