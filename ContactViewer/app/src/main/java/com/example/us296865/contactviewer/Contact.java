package com.example.us296865.contactviewer;

/**
 * Created by US296865 on 2/13/2015.
 */
public class Contact {

    public static String TABLE_NAME;

    public static Contact[] getAll(){
            return new Contact[]{
                    new Contact("Malcom Reynolds", "Captain", "612-555-1234","mreynolds@gmail.com","@mreynolds"),
                    new Contact("Jayne Cobb", "Muscle", "612-555-2345","jcobb@gmail.com","@jcobb"),
                    new Contact("Hoban Washburne", "Pilot", "612-555-3456","hwashburne@gmail.com","@mrwash"),
                    new Contact("Kaylee Frye", "Engineer", "612-555-4567","kfrye@gmail.com","@kfrye"),
                    new Contact("Zoe Washburne", "Engineer", "612-555-5678","zwashburne@gmail.com","@mrswash"),
                    new Contact("Simon Tam", "Doctor", "612-555-6789","zwashburne@gmail.com","@mrswash")
            };
        }

    public static Contact getById(){
        return new Contact("Kaylee Frye", "Engineer", "612-555-4567","kfrye@gmail.com","@kfrye");
    }

    public Contact(String name, String title, String phone, String email, String twitterId) {
        this.name = name;
        this.title = title;
        this.phone = phone;
        this.email = email;
        this.twitterId = twitterId;
    }

    private String name;
    private String title;
    private String email;
    private String phone;
    private String twitterId;

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
