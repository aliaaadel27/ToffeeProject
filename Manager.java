import java.util.Scanner;
import java.util.Vector;

public class Manager {

    public void startTheApp() {
        Website myWeb = new Website();
        boolean inTheApp = false;
        System.out.println("\n\nWelcome in Toffee .. \n");
        while (!inTheApp) {
            int choice = myWeb.displayOuterPage();
            if (choice == 1) {
                myWeb.displayLoginMenu();
                inTheApp = true;
            } else if (choice == 2) {
                Scanner input = new Scanner(System.in);
                boolean validData = false;
                while (!validData) {
                    boolean uniqueName = false;
                    while (!uniqueName) {
                        myWeb.displayRegisterForm();
                        String name = input.nextLine();
                        String email = input.nextLine();
                        String pass = input.nextLine();
                        String address = input.nextLine();
                        String phone = input.nextLine();
                        if (myWeb.checkData(name, email, pass, address, phone)) {
                            if (myWeb.uniqueName(name)) {
                                boolean matchedOTP = false;
//                                String OTPin;
                                while (!matchedOTP) {
                                    System.out.println("\n\nWe will send you an OTP to check if you have an access to entered email .. ");
                                    String sentOTP = myWeb.sendOTP();
//                                    System.out.print("\nPlease , enter the OTP sent to you : ");
//                                    String OTPin = input.nextLine();
                                    if (myWeb.checkOTP(sentOTP)) {
                                        User newUser = new User();
                                        myWeb.users = new Vector<>();
                                        myWeb.users.add(newUser);
                                        newUser.register(name, email, pass, address, phone);
                                        System.out.println("\n\n\nCongratulations! \n" +
                                                "You have registered successfully .. \n\n\n\n");
                                        matchedOTP = true;
                                    } else {
                                        myWeb.displayErrorMessage(3);
                                        int againOTP;
                                        boolean validChoice = false;
                                        while (!validChoice) {
                                            System.out.print("Do you want us to send you another one ?\n\n" +
                                                    " 1- Yes .\n" +
                                                    " 2- No , exit .\n\n" +
                                                    "Enter the number of the choice you want : ");
                                            againOTP = input.nextInt();
                                            if (againOTP == 1) {
                                                validChoice = true;
                                            } else if (againOTP == 2) {
                                                validChoice = true;
                                                matchedOTP = true;
                                            } else {
                                                myWeb.displayErrorMessage(5);
                                            }
                                        }
                                    }
                                }
                                uniqueName = true;
                            } else {
                                myWeb.displayErrorMessage(2);
                            }
                            validData = true;
                        } else {
                            myWeb.displayErrorMessage(1);
                        }
                    }
                }
            } else if (choice == 3) {
                System.out.println("\n\nThank you for choosing Toffee .\n" +
                        "We hope this isn't the last time :) \n\n\n");
                System.exit(1);
            }
        }
    }

}

