import java.io.*;

public class User {
    protected UserAccount account;

    public User() {}
    public User(UserAccount userAccount) {
        account = new UserAccount(userAccount.getUserName(),userAccount.getEmail(),userAccount.getPassword(),userAccount.getAddress(),userAccount.getPhoneNumber());
    }
    public UserAccount getAccount() {
        return account;
    }
    public void register(Buyer buyer , Database database) {

        database.addBuyer(buyer);

    }

    public UserAccount login(String username , String pass , int buyerOrAdmin , Database database) {
        String email = "" , address = "" , phNum = "";

        if (buyerOrAdmin == 1) {

            for (Buyer buyer : database.getBuyers()) {
                if (username == buyer.getAccount().getUserName() && pass == buyer.getAccount().getPassword())
                    return buyer.getAccount();
            }

        } else if (buyerOrAdmin == 2) {
            for (Admin admin : database.getAdmins()) {
                if (username == admin.getAdminName() && pass == admin.getAccount().getPassword())
                    return admin.getAccount();
            }
        }

        try {
            BufferedReader names = new BufferedReader(new FileReader("usernames.txt"));
            String line = names.readLine();
            int nLine = 0;
            while (line != null) {
                if (username.equals(line))
                    break;
                line = names.readLine();
                nLine++;
            }
            names.close();

            BufferedReader emails = new BufferedReader(new FileReader("emails.txt"));
            String emailLine = emails.readLine();
            for (int i = 0; i<= nLine; i++) {
                if (i == nLine) {
                    email = emailLine;
                }
                emailLine = emails.readLine();
            }
            emails.close();

            BufferedReader addresses = new BufferedReader(new FileReader("addresses.txt"));
            String adrsLine = addresses.readLine();
            for (int i = 0; i<= nLine; i++) {
                if (i == nLine) {
                    address = adrsLine;
                }
                adrsLine = addresses.readLine();
            }
            addresses.close();

            BufferedReader pNums = new BufferedReader(new FileReader("pNumbers.txt"));
            String phNumLine = pNums.readLine();
            for (int i = 0; i<= nLine; i++) {
                if (i == nLine) {
                    phNum = phNumLine;
                }
                phNumLine = pNums.readLine();
            }
            pNums.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        account = new UserAccount(username , email , pass , address , phNum);

        return account;
    }
}
