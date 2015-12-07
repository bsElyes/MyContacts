package interact.io.mycontacts.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import interact.io.mycontacts.Entities.Contact;
import interact.io.mycontacts.Entities.EmailContact;
import interact.io.mycontacts.Entities.PhoneContact;

/**
 * Created by ElyesL on 07/12/2015.
 */
public class GetContact {

    public static Contact getContact(JSONObject jsonObject){
        Contact c = new Contact();
        try {
            JSONArray emailsArray = jsonObject.getJSONArray("emails");
            JSONArray phonesArray = jsonObject.getJSONArray("phoneNumbers");
            c.setDisplayName(jsonObject.getString("displayName"));
            c.setProfilePicture(jsonObject.getString("profilePicture"));
            c.setListEmails(getEmails(emailsArray));
            c.setListPhones(getPhones(phonesArray));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return c;
    }

    public static List<PhoneContact> getPhones (JSONArray jsonArray){
        List<PhoneContact> phones = new ArrayList<>();
        for(int i = 0 ; i< jsonArray.length();i++){
            try {
                JSONObject phoneObject = (JSONObject) jsonArray.get(i);
                PhoneContact phoneContact = new PhoneContact();
                phoneContact.setType(phoneObject.getString("type"));
                phoneContact.setNumbler(phoneObject.getString("number"));
                phones.add(phoneContact);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return phones;
    }

    public static List<EmailContact> getEmails (JSONArray jsonArray){
        List<EmailContact> emails = new ArrayList<>();
        for(int i = 0 ; i< jsonArray.length();i++){
            try {
                JSONObject phoneObject = (JSONObject) jsonArray.get(i);
                EmailContact emailContact = new EmailContact();
                emailContact.setType(phoneObject.getString("type"));
                emailContact.setEmail(phoneObject.getString("email"));
                emails.add(emailContact);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return emails;
    }
}
