import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class User {

    private UserAccount account;

    public UserAccount register(String n, String e, String pass, String adrs, String phNum) {
        account = new UserAccount(n,e,pass,adrs,phNum);
        //Add the data in the file
        try {
            BufferedWriter name = new BufferedWriter(new FileWriter("usernames.txt",true));
            name.write(n + '\n');
            name.close();
            BufferedWriter email = new BufferedWriter(new FileWriter("emails.txt",true));
            email.write(e + '\n');
            email.close();
            BufferedWriter password = new BufferedWriter(new FileWriter("passwords.txt",true));
            password.write(pass + '\n');
            password.close();
            BufferedWriter address = new BufferedWriter(new FileWriter("addresses.txt",true));
            address.write(adrs + '\n');
            address.close();
            BufferedWriter pNumber = new BufferedWriter(new FileWriter("pNumbers.txt",true));
            pNumber.write(phNum + '\n');
            pNumber.close();
        } catch (IOException E) {
            E.printStackTrace();
        }
        return account;

    }

}

