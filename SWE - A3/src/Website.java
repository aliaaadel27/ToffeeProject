import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Website {
    private Buyer theUser;
    private Admin theAdmin;
//    private Vector<User> users;
    private Database database;
    private Statistics stat;
//    private Catalogue catalogue;

    /**
     * The constructor to initialize the vectors inside to be able to add elements in it .
     */
    public Website() {
        database = new Database();
        theUser = new Buyer();
        stat = new Statistics();
    }

    /**
     * getter
     * @return the user that uses the system at this moment .
     */
    public Buyer getTheUser() {
        return theUser;
    }

//    public Vector<User> getUsers() {
//        return users;
//    }

    /**
     * getter
     * @return the database that have the data for the system .
     */
    public Database getDatabase() {
        return database;
    }

    public Statistics getStat() {
        return stat;
    }

//    public Catalogue getCatalogue() {
//        return catalogue;
//    }

    public void setTheUser(UserAccount theUser) {
        this.theUser = new Buyer(theUser);
    }

//    public void setUsers(Vector<User> users) {
//        this.users = users;
//    }

    public void setStat(Statistics stat) {
        this.stat = stat;
    }

//    public void setCatalogue(Catalogue catalogue) {
//        this.catalogue = catalogue;
//    }

    /**
     * Function to display the home page for the buyer
     * @param username the name of the buyer to say hello by his name .
     */
    public void displayBuyerHomePage(String username) {
        System.out.print("\n\n--------------------------------------------------------------------------\n" +
                "Hello , " + username + " !\n" +
                "--------------------------------------------------------------------------\n" +
                " 1- View Catalogue .\n" +
                "     (with the ability to choose items , add them to your shopping cart and make an order ) .\n" +
                " 2- My vouchers .\n" +
                " 3- My loyalty points .\n" +
                " 4- Search items .\n" +
                " 5- My account info .\n" +
                " 6- My past orders .\n" +
                " 7- Contact us .\n" +
                " 8- Log out .\n\n" +
                "Please , enter the number of the choice you want : ");
    }

    /**
     * Function to display the home page of the admin .
     * @param name the name of the admin to say hello by his name .
     */
    public void displayAdminHomePage(String name) {
        System.out.print("\n\n--------------------------------------------------------------------------\n" +
                "Hello , " + name + " !\n" +
                "--------------------------------------------------------------------------\n" +
                " 1- Edit Catalogue .\n" +
                " 2- View Statistics .\n" +
                " 3- Change the way to calculate loyalty points .\n" +
                " 4- Suspend a user .\n" +
                " 5- My account info .\n" +
                " 6- View all orders .\n" +
                " 7- Add a new admin .\n" +
                " 8- Log out .\n\n" +
                "Please , enter the number of the choice you want : ");
    }

    /**
     * Function to display the error message
     * @param error the specific type for the error to display the suitable error message
     */
    public void displayErrorMessage(int error) {
        System.out.println("\n--------------------------------------------------------------------------\n");
        //1  =====>  for incorrect data format in registration
        if (error == 1)
            System.out.println("\nInvalid format! \nPlease , follow the instructions when entering your data ..");
        //2  =====>  for not unique data in registration
        else if (error == 2)
            System.out.println("\nThis username is invalid :( \nIt must be unique .");
        //3  =====>  for incorrect OTP
        else if (error == 3)
            System.out.println("\nThe OTP you've entered is incorrect!");
        //4  =====>  for not matching username and password in login
        else if (error == 4)
            System.out.println("\nThe username or password is incorrect!");
        //5  =====>  for invalid choice
        else if (error == 5)
            System.out.println("\nInvalid choice!");
        System.out.println("\n--------------------------------------------------------------------------\n\n");
    }

    /**
     * Function to check if the username and password is correct
     * @param n the username of the user wants to log in
     * @param pass the password of the user wants to log in
     * @param buyerOrAdmin number to know if the user is admin or buyer ( 1 ---> Buyer , 2 ---> Admin )
     * @return true ---> if the data is correct , false ---> if the data is incorrect
     */
    public boolean login(String n, String pass, int buyerOrAdmin) {

        UserAccount account = theUser.login(n , pass , buyerOrAdmin , database);

        if (buyerOrAdmin == 1) {
            if (account != null) {
                theUser = new Buyer(account);
                return true;
            }
        }
        else if (buyerOrAdmin == 2) {
            if (account != null) {
                theAdmin = new Admin(account);
                return true;
            }
        }
        return false;

//            try {
//                BufferedReader names = new BufferedReader(new FileReader("usernames.txt"));
//                String line = names.readLine();
//                int nLine = 0;
//                while (line != null) {
//                    if (n.equals(line))
//                        break;
//                    line = names.readLine();
//                    nLine++;
//                }
//
//                names.close();
//
//                BufferedReader passwords = new BufferedReader(new FileReader("passwords.txt"));
//                String passLine = passwords.readLine();
//                for (int i = 0; i <= nLine; i++) {
//                    if (i == nLine && pass.equals(passLine))
//                        return true;
//                }
//
//                passwords.close();
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        else if (buyerOrAdmin == 2) {
//            try {
//                BufferedReader names = new BufferedReader(new FileReader("adminUsernames.txt"));
//                String line = names.readLine();
//                int nLine = 0;
//                while (line != null) {
//                    if (n.equals(line))
//                        break;
//                    line = names.readLine();
//                    nLine++;
//                }
//
//                names.close();
//
//                BufferedReader passwords = new BufferedReader(new FileReader("adminPasswords.txt"));
//                String passLine = passwords.readLine();
//                for (int i = 0; i <= nLine; i++) {
//                    if (i == nLine && pass.equals(passLine))
//                        return true;
//                }
//
//                passwords.close();
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return false;
    }

    /**
     * Function to know if the user wants to log in as a buyer of as an admin ,
     * and also displays the way that the username and password should be entered
     * @return the choice ( 1 ---> Buyer / Customer , 2 ---> Admin )
     */
    public int displayLoginMenu() {
        Scanner input = new Scanner(System.in);
        int choice = 0;
        boolean validChoice = false;
        while (!validChoice) {
            System.out.print("\n--------------------------------------------------------------------------\n" +
                    "Do you want to login as :\n\n" +
                    " 1- Customer .\n" +
                    " 2- Admin .\n\n" +
                    "Please , enter the number of the choice you want : ");
            choice = input.nextInt();
            if (choice == 1 || choice == 2) {
                System.out.println("\n--------------------------------------------------------------------------\n" +
                        "Enter :\n\n Username\n Password\n");
                validChoice = true;
            } else {
                displayErrorMessage(5);
            }
        }
        return choice;

    }

    /**
     * Function to display the way that the user should enter his data and the restrictions about it
     */
    public void displayRegisterForm() {
        System.out.println("\n--------------------------------------------------------------------------\n" +
                "Please , enter your data ..\n" +
                "--------------------------------------------------------------------------\n\n" +
                " Username       ( must be unique and more than 5 characters ) \n" +
                " Email          ( valid (ends with '.com') )\n" +
                " Password       ( strong and >= 5 characters )\n" +
                " Address        ( >= 5 characters )\n" +
                " Phone Number   ( Egyptian (starts with '01') )\n");
    }

    /**
     * Function to display the catalogue
     */
    public void displayCatalogue() {
        database.getCatalogue().viewCatalogue();
    }

    /**
     * Function to display the outer page that appears for the user that is not logged in
     * @return the choice for the option that the user chose
     */
    public int displayOuterPage() {
        Scanner input = new Scanner(System.in);
        System.out.print("\n--------------------------------------------------------------------------\n\n" +
                " 1- Login           (if you already have an account) .\n" +
                " 2- Register        (if you don't have an account) . \n" +
                " 3- View catalogue  (without the ability to choose items or make an order) . \n" +
                " 4- Contact us . \n" +
                " 5- Quit the application . \n\n");
        int choice = 0;
        boolean validChoice = false;
        while (!validChoice) {
            System.out.print("Please, enter the number of the choice you want : ");
            choice = input.nextInt();
            if (choice == 1 || choice == 2 || choice == 3 || choice == 4 || choice == 5)
                validChoice = true;
            else
                displayErrorMessage(5);
        }
        return choice;
    }

    /**
     * Function to generate the OTP and send it via email
     * @param email the email of the user to
     * @return
     */
    public String sendOTP_mail(String email) {
        String scope = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz" + "0123456789";
        StringBuilder OTP = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            int index = (int)(scope.length() * Math.random());
            OTP.append(scope.charAt(index));
        }
//        System.out.println("\nThe OTP : " + OTP); //to be deleted
        //sending OTP via email
        SendMail mail = new SendMail(email, OTP.toString());
        mail.send();
        return OTP.toString();
    }

    public String sendOTP(String email) {
        String scope = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz" + "0123456789";
        StringBuilder OTP = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            int index = (int)(scope.length() * Math.random());
            OTP.append(scope.charAt(index));
        }
        System.out.println("\nThe OTP : " + OTP); //to be deleted
        //sending OTP via email
//        SendMail mail = new SendMail(email, OTP.toString());
//        mail.send();
        return OTP.toString();
    }


    public boolean checkOTP(String otpSent) {
        Scanner input = new Scanner(System.in);
        System.out.print("\n\n--------------------------------------------------------------------------\n\n" +
                "Please , enter the OTP sent to you : ");
        String OTPin = input.nextLine();
        System.out.println("\n\n--------------------------------------------------------------------------\n\n");
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

            while (line != null) {
                if (name.equals(line))
                    return false;
                line = names.readLine();
            }

            names.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        //if 'name' exists in the file ======> false
        //else ======> true
        return true;
    }

    public void contactPage() {
        System.out.println("\n\n--------------------------------------------------------------------------\n\n" +
                "Hotline : 99999\n\n" +
                "Phone numbers : 01012345678\n" +
                "                01123456789\n" +
                "                01234567890\n\n" +
                "Email address : toffee@gmail.com\n\n");
    }
}
