package interact.io.mycontacts.Entities;

/**
 * Created by ElyesL on 07/12/2015.
 */
public class PhoneContact {
    private String type;
    private String numbler;

    public PhoneContact() {
    }

    public PhoneContact(String type, String numbler) {
        this.type = type;
        this.numbler = numbler;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumbler() {
        return numbler;
    }

    public void setNumbler(String numbler) {
        this.numbler = numbler;
    }

    @Override
    public String toString() {
        return "PhoneContact{" +
                "type='" + type + '\'' +
                ", numbler='" + numbler + '\'' +
                '}';
    }
}