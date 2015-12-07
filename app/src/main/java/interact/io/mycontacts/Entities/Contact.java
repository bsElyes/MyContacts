package interact.io.mycontacts.Entities;

import java.util.List;

/**
 * Created by ElyesL on 07/12/2015.
 */
public class Contact {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String displayName;
    private String profilePicture;
    private List<EmailContact> listEmails;
    private List<PhoneContact> listPhones;
    public Contact() {
    }

    public Contact(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<EmailContact> getListEmails() {
        return listEmails;
    }

    public void setListEmails(List<EmailContact> listEmails) {
        this.listEmails = listEmails;
    }

    public List<PhoneContact> getListPhones() {
        return listPhones;
    }

    public void setListPhones(List<PhoneContact> listPhones) {
        this.listPhones = listPhones;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    @Override
    public String toString() {
        return "Contact{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", displayName='" + displayName + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", listEmails=" + listEmails +
                ", listPhones=" + listPhones +
                '}';
    }
}
