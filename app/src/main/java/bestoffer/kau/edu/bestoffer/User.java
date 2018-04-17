package bestoffer.kau.edu.bestoffer;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by user on 03/02/18.
 */

public class User {
    private String firstname ;
    private String lastname ;
    private String email ;
    private String password ;
    private static User instance = null;
    private LatLng latLng ;

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    private User(){}

    public static User getInstance() {
        if(instance == null) {
            instance = new User();
        }
        return instance;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
