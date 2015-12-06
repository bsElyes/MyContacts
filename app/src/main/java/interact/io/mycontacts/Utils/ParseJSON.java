package interact.io.mycontacts.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import interact.io.mycontacts.Entities.User;

/**
 * Created by ElyesL on 06/12/2015.
 */
public class ParseJSON {


    public ParseJSON() {
    }

    public  static User getUser(JSONObject response){
        User user = new User();
        try {
            JSONObject userObject = new JSONObject(response.getString("user"));
            //private String id ;
            user.setId(userObject.getString("id"));
            //private String username;
            user.setUsername(userObject.getString("username"));
            //private String accountStatus;
            user.setAccountStatus(userObject.getString("accountStatus"));
            //private Date expirationDate;
            user.setExpirationDate(userObject.getLong("expirationDate"));
            //private String email;
            user.setEmail(userObject.getString("email"));
            //private String firstName;
            user.setFirstName(userObject.getString("firstName"));
            //private String lastName;
            user.setLastName(userObject.getString("lastName"));
            //private String phoneNumber;
            user.setPhoneNumber(userObject.getString("phoneNumber"));
            //private String timeZone;
            user.setTimeZone(userObject.getString("timeZone"));
            //private String organizationId;
            user.setOrganizationId(userObject.getString("organizationId"));
            //private String organizationName;
            user.setOrganizationName(userObject.getString("organizationName"));
            //private String signupClient;
            user.setSignupClient(userObject.getString("signupClient"));
            //private Date dateCreated;
            user.setDateCreated(userObject.getLong("dateCreated"));
            //private Boolean active;
            user.setActive(userObject.getBoolean("active"));

            //private String firebaseAuthToken;
            user.setSignupClient(response.getString("firebaseAuthToken"));

            JSONObject tokenObject = new JSONObject(response.getString("token"));
            //private String authToken;
            user.setSignupClient(tokenObject.getString("authToken"));
            //private Long authTokenexpires;
            user.setDateCreated(tokenObject.getLong("authTokenexpires"));


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return user;
    }
}
