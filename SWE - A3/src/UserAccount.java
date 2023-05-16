public class UserAccount {
    private String userName , email , password , address , phoneNumber;
//    private int loyaltyPoints;
    public UserAccount(String n, String e, String pass, String adrs, String phNum) {
        userName = n;
        email = e;
        password = pass;
        address = adrs;
        phoneNumber = phNum;
//        loyaltyPoints = 0;
    }
    public String getUserName() {
        return userName;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getAddress() {
        return address;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
//    public int getLoyaltyPoints() {
//        return loyaltyPoints;
//    }
    public void setUserName(String username) {
        userName = username;
    }
    public void setEmail(String Email) {
        email = Email;
    }
    public void setPassword(String pass) {
        password = pass;
    }
    public void setAddress(String adrs) {
        address = adrs;
    }
    public void setPhoneNumber(String phNum) {
        phoneNumber = phNum;
    }
    public boolean verifyCredentials(String n , String pass) {
        return (userName == n && password == pass);
    }
//    public void addLoyaltyPoints(int points) {
//        loyaltyPoints = points;
//    }
}
