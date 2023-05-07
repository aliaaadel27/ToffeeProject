import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Website {

    public Vector<User> users;

    public void displayHomePage() {}

    public void displayErrorMessage(int error) {
        //1  =====>  for incorrect data format in registration
        if (error == 1)
            System.out.println("\n\nInvalid format! \nPlease , follow the instructions when entering your data .. \n");
        //2  =====>  for not unique data in registration
        else if (error == 2)
            System.out.println("\n\nThis username is invalid :( \nIt must be unique .\n");
        //3  =====>  for incorrect OTP
        else if (error == 3)
            System.out.println("\n\nThe OTP you've entered is incorrect! \n");
        //4  =====>  for not matching username and password in login
        else if (error == 4)
            System.out.println("\n\nThe username or password is incorrect! \n");
        //5  =====>  for invalid choice
        else if (error == 5)
            System.out.println("\n\nInvalid choice!\n");
    }

    public void login(String n, String pass) {}

    public void displayLoginMenu() {}

    public void displayRegisterForm() {
        System.out.println("\n\nPlease , enter your data ..\n\n" +
                " Username       ( must be unique and more than 5 characters ) \n" +
                " Email          ( valid (ends with '.com') )\n" +
                " Password       ( strong and >= 5 characters )\n" +
                " Address        ( >= 5 characters )\n" +
                " Phone Number   ( Egyptian (starts with '01') )\n");
    }

    public void displayCatalogue() {}

    public int displayOuterPage() {
        Scanner input = new Scanner(System.in);
        System.out.print("\n 1- Login     (if you already have an account) .\n" +
                " 2- Register  (if you don't have an account) . \n" +
                " 3- Quit the application . \n\n");
        int choice = 0;
        boolean validChoice = false;
        while (!validChoice) {
            System.out.print("Please, enter the number of the choice you want : ");
            choice = input.nextInt();
            if (choice == 1 || choice == 2 || choice == 3)
                validChoice = true;
            else
                displayErrorMessage(5);
        }
        return choice;
    }

    public String sendOTP() {
        String scope = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz" + "0123456789";
        StringBuilder OTP = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            int index = (int)(scope.length() * Math.random());
            OTP.append(scope.charAt(index));
        }
        System.out.println("\nThe OTP : " + OTP); //to be deleted
        //sending OTP via email
        return OTP.toString();
    }

    public boolean checkOTP(String otpSent) {
        Scanner input = new Scanner(System.in);
        System.out.print("\nPlease , enter the OTP sent to you : ");
        String OTPin = input.nextLine();
        if (OTPin.equals(otpSent))
            return true;
        return false;
    }

    public boolean checkData(String n, String e, String pass, String adrs, String phNum) {
        Pattern validEmail = Pattern.compile("[a-zA-Z]+[_]?[a-zA-Z]+[0-9]*[a-zA-Z]*[@][a-zA-Z]+[.][c][o][m]");
        Matcher validE = validEmail.matcher(e);
        Pattern validPass = Pattern.compile(".{5,}");  //adding strong password conditions
        Matcher validP = validPass.matcher(pass);
        Pattern validPhoneNum = Pattern.compile("^01+([1]|[2]|[0]|[5])+[0-9]{8}");
        Matcher validPHN = validPhoneNum.matcher(phNum);
        if ((n.length() >= 3) && (validE.matches()) && (validP.matches()) && (validPHN.matches()) && (adrs.length() >= 5))
            return true;
        return false;
    }

    public boolean uniqueName(String name) {
        try {
            BufferedReader names = new BufferedReader(new FileReader("usernames.txt"));
            String line = names.readLine();
            
        //if 'name' exists in the file ======> false
            
            while (line != null) {
                if (name.equals(line))
                    return false;
                line = names.readLine();
            }

            names.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //else ======> true
        
        return true;
    }

}
