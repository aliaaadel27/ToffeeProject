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
//        account = new UserAccount(buyer.getAccount().getUserName(),e,pass,adrs,phNum);
//        Buyer buyer = new Buyer(account);
        database.addBuyer(buyer);
        //Add the data in the file
//        try {
//            BufferedWriter name = new BufferedWriter(new FileWriter("usernames.txt",true));
//            name.write(n + '\n');
//            name.close();
//            BufferedWriter email = new BufferedWriter(new FileWriter("emails.txt",true));
//            email.write(e + '\n');
//            email.close();
//            BufferedWriter password = new BufferedWriter(new FileWriter("passwords.txt",true));
//            password.write(pass + '\n');
//            password.close();
//            BufferedWriter address = new BufferedWriter(new FileWriter("addresses.txt",true));
//            address.write(adrs + '\n');
//            address.close();
//            BufferedWriter pNumber = new BufferedWriter(new FileWriter("pNumbers.txt",true));
//            pNumber.write(phNum + '\n');
//            pNumber.close();
//        } catch (IOException E) {
//            E.printStackTrace();
//        }
//        return account;

    }

    public UserAccount login(String username , String pass , int buyerOrAdmin , Database database) {
        String email = "" , address = "" , phNum = "";

        if (buyerOrAdmin == 1) {

            for (Buyer buyer : database.getBuyers()) {
                if (username == buyer.getAccount().getUserName() && pass == buyer.getAccount().getPassword())
                    return buyer.getAccount();
//                    return true;
            }
//            return false;

        } else if (buyerOrAdmin == 2) {
            for (Admin admin : database.getAdmins()) {
                if (username == admin.getAdminName() && pass == admin.getAccount().getPassword())
                    return admin.getAccount();
//                    return true;
            }
//            return false;
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

//            BufferedReader passwords = new BufferedReader(new FileReader("passwords.txt"));
//            String passLine = passwords.readLine();
//            for (int i = 0; i <= nLine; i++) {
//                if (i == nLine)
//                    return true;
//            }
//            passwords.close();

            BufferedReader emails = new BufferedReader(new FileReader("emails.txt"));
            String emailLine = emails.readLine();
            for (int i = 0; i <= nLine; i++) {
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
//        if (email != null && address != null && phNum != null)
        account = new UserAccount(username , email , pass , address , phNum);

        return account;
    }
}
