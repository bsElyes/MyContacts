package interact.io.mycontacts.Entities;

/**
 * Created by ElyesL on 07/12/2015.
 */
public class EmailContact {
    private String type;
    private String email;

    public EmailContact(String type, String email) {
        this.type = type;
        this.email = email;
    }

    public EmailContact() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "EmailContact{" +
                "type='" + type + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}