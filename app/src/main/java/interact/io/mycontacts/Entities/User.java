package interact.io.mycontacts.Entities;

/**
 * Created by ElyesL on 06/12/2015.
 */
public class User {
    private String id ;
    private String username;
    private String accountStatus;
    private Long  expirationDate;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String timeZone;
    private String organizationId;
    private String organizationName;
    private String signupClient;
    private Long dateCreated;
    private Boolean active;

    private String firebaseAuthToken;
    private String authToken;
    private Long authTokenexpires;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Long getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Long expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getSignupClient() {
        return signupClient;
    }

    public void setSignupClient(String signupClient) {
        this.signupClient = signupClient;
    }

    public Long getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Long dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getFirebaseAuthToken() {
        return firebaseAuthToken;
    }

    public void setFirebaseAuthToken(String firebaseAuthToken) {
        this.firebaseAuthToken = firebaseAuthToken;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public Long getAuthTokenexpires() {
        return authTokenexpires;
    }

    public void setAuthTokenexpires(Long authTokenexpires) {
        this.authTokenexpires = authTokenexpires;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", accountStatus='" + accountStatus + '\'' +
                ", expirationDate=" + expirationDate +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", timeZone='" + timeZone + '\'' +
                ", organizationId='" + organizationId + '\'' +
                ", organizationName='" + organizationName + '\'' +
                ", signupClient='" + signupClient + '\'' +
                ", dateCreated=" + dateCreated +
                ", active=" + active +
                '}';
    }

    public User() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getId() != null ? !getId().equals(user.getId()) : user.getId() != null) return false;
        if (getUsername() != null ? !getUsername().equals(user.getUsername()) : user.getUsername() != null)
            return false;
        if (getAccountStatus() != null ? !getAccountStatus().equals(user.getAccountStatus()) : user.getAccountStatus() != null)
            return false;
        if (getExpirationDate() != null ? !getExpirationDate().equals(user.getExpirationDate()) : user.getExpirationDate() != null)
            return false;
        if (getEmail() != null ? !getEmail().equals(user.getEmail()) : user.getEmail() != null)
            return false;
        if (getFirstName() != null ? !getFirstName().equals(user.getFirstName()) : user.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(user.getLastName()) : user.getLastName() != null)
            return false;
        if (getPhoneNumber() != null ? !getPhoneNumber().equals(user.getPhoneNumber()) : user.getPhoneNumber() != null)
            return false;
        if (getTimeZone() != null ? !getTimeZone().equals(user.getTimeZone()) : user.getTimeZone() != null)
            return false;
        if (getOrganizationId() != null ? !getOrganizationId().equals(user.getOrganizationId()) : user.getOrganizationId() != null)
            return false;
        if (getOrganizationName() != null ? !getOrganizationName().equals(user.getOrganizationName()) : user.getOrganizationName() != null)
            return false;
        if (getSignupClient() != null ? !getSignupClient().equals(user.getSignupClient()) : user.getSignupClient() != null)
            return false;
        if (getDateCreated() != null ? !getDateCreated().equals(user.getDateCreated()) : user.getDateCreated() != null)
            return false;
        if (getActive() != null ? !getActive().equals(user.getActive()) : user.getActive() != null)
            return false;
        if (getFirebaseAuthToken() != null ? !getFirebaseAuthToken().equals(user.getFirebaseAuthToken()) : user.getFirebaseAuthToken() != null)
            return false;
        if (getAuthToken() != null ? !getAuthToken().equals(user.getAuthToken()) : user.getAuthToken() != null)
            return false;
        return !(getAuthTokenexpires() != null ? !getAuthTokenexpires().equals(user.getAuthTokenexpires()) : user.getAuthTokenexpires() != null);

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
        result = 31 * result + (getAccountStatus() != null ? getAccountStatus().hashCode() : 0);
        result = 31 * result + (getExpirationDate() != null ? getExpirationDate().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getPhoneNumber() != null ? getPhoneNumber().hashCode() : 0);
        result = 31 * result + (getTimeZone() != null ? getTimeZone().hashCode() : 0);
        result = 31 * result + (getOrganizationId() != null ? getOrganizationId().hashCode() : 0);
        result = 31 * result + (getOrganizationName() != null ? getOrganizationName().hashCode() : 0);
        result = 31 * result + (getSignupClient() != null ? getSignupClient().hashCode() : 0);
        result = 31 * result + (getDateCreated() != null ? getDateCreated().hashCode() : 0);
        result = 31 * result + (getActive() != null ? getActive().hashCode() : 0);
        result = 31 * result + (getFirebaseAuthToken() != null ? getFirebaseAuthToken().hashCode() : 0);
        result = 31 * result + (getAuthToken() != null ? getAuthToken().hashCode() : 0);
        result = 31 * result + (getAuthTokenexpires() != null ? getAuthTokenexpires().hashCode() : 0);
        return result;
    }
}
