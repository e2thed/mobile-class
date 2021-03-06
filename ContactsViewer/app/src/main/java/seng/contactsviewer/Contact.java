package seng.contactsviewer;

import java.io.Serializable;

/**
 * Created by US296865 on 2/20/2015.
 */
public class Contact implements Serializable{

    public Contact()
    {

    }

    public Contact(String _id, String name, String title, String phone, String email, String twitterId) {
        this._id = _id;
        this.name = name;
        this.title = title;
        this.phone = phone;
        this.email = email;
        this.twitterId = twitterId;
    }

    private String _id;
    private String name;
    private String title;
    private String email;
    private String phone;
    private String twitterId;

    public String get_Id() {
        return _id;
    }

    public void set_Id(String _Id) {
        this._id = _Id;
    }


    public String getTwitterId() {
        return twitterId;
    }

    public void setTwitterId(String twitterId) {
        this.twitterId = twitterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
