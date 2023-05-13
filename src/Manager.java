import java.util.Scanner;
import java.util.Vector;

public class Manager {

    public void startTheApp() {
        Website myWeb = new Website();
        boolean inTheApp = false;
        System.out.print("\n\nWelcome in Toffee .. \n");
        while (!inTheApp) {
            Scanner input = new Scanner(System.in);
            int choice = myWeb.displayOuterPage();
            if (choice == 1) {
                inTheApp = true;
                boolean correctData = false;
                while (!correctData) {
                    int buyerOrAdmin = myWeb.displayLoginMenu();
                    String username = input.nextLine();
                    String password = input.nextLine();
                    User user = new User();
                    if (myWeb.login(username , password , buyerOrAdmin)) {
                        correctData = true;
                        boolean back = false;
                        int homeChoice;
                        while (!back) {
                            if (buyerOrAdmin == 1) {
                                myWeb.displayBuyerHomePage(username);
                                homeChoice = input.nextInt();
                                if (homeChoice == 1) {
                                    myWeb.displayCatalogue();
                                    int order = 0;
                                    System.out.println("\n--------------------------------------------------------------------------\n\n" +
                                            " 1- Enter '0' to start an order .\n" +
                                            " 2- Then , enter the 'ID' of the item you want to add to your shopping cart .\n" +
                                            " 3- Finally , enter '-1' to check out and confirm your order .\n" +
                                            "\n--------------------------------------------------------------------------\n");
                                    order = input.nextInt();
                                    ShoppingCart myCart = new ShoppingCart();
                                    while (order != -1) {
                                        if (order == 0) {
                                            int itemID , quantity;
                                            boolean availableItem = false;
                                            System.out.print("\n\nEnter the ID of the item you want : ");
                                            itemID = input.nextInt();
                                            System.out.print("\n\nEnter the quantity you want : ");
                                            quantity = input.nextInt();
                                            for (Category category : myWeb.getDatabase().getCatalogue().getCategories()) {
                                                for (Item item : category.getItems()) {
                                                    if (item.getId() == itemID) {
                                                        for (int i = 0; i < quantity; i++) {
                                                            myCart.addItem(item);
                                                        }
                                                        System.out.println("\n\n The item '" + itemID + "' added ..\n" +
                                                                "\n--------------------------------------------------------------------------\n");
                                                        availableItem = true;
                                                    }
                                                }
                                            }
                                            if (!availableItem)
                                                System.out.println("\n--------------------------------------------------------------------------\n" +
                                                        "This item is not available !\n" +
                                                        "Please choose from the catelogue ..\n" +
                                                        "--------------------------------------------------------------------------\n");
                                            int orderChoice;
                                            boolean validOrderChoice = false;
                                            while (!validOrderChoice) {
                                                System.out.print("\n\n 1- Add another item .\n" +
                                                        " 2- Display items added till now .\n" +
                                                        " 3- Remove an item .\n" +
                                                        " 4- Check out ( confirm your order ) .\n" +
                                                        " 5- Cancel the order and back .\n\n" +
                                                        "Enter the number of the choice you want : ");
                                                orderChoice = input.nextInt();
                                                if (orderChoice == 1) {
                                                    validOrderChoice = true;
                                                } else if (orderChoice == 2) {
                                                    myCart.viewItems();
                                                } else if (orderChoice == 3) {
                                                    System.out.print("\n\nEnter the ID of the item you want to remove : ");
                                                    int removedID = input.nextInt();
                                                    for (Item item : myCart.getItems()) {
                                                        if (item.getId() == removedID) {
                                                            myCart.removeItem(item);
                                                            System.out.println("\n\nThe item '" + removedID + "' deleted .\n");
                                                        }
                                                    }
                                                } else if (orderChoice == 4) {
                                                    validOrderChoice = true;
                                                    String confirmation;
                                                    System.out.println("\n\nYour order :\n");
                                                    myCart.viewItems();
                                                    System.out.println("\n\nEnter 'confirm' to confirm your order ( you can not cancel it after it starts to be shipped ) .. \n" +
                                                            "If you entered anything else , you will go back to the order ..\n");
                                                    confirmation = input.nextLine();
                                                    if (confirmation.equals("confirm")) {
                                                        myCart.placeOrder(myWeb.getTheUser(), myWeb);
                                                        System.out.println("\n\n--------------------------------------------------------------------------\n" +
                                                                "Your order has been sent successfully !\n" +
                                                                "--------------------------------------------------------------------------\n");
                                                        order = -1;
                                                    }
                                                    else {
                                                        validOrderChoice = false;
                                                    }
                                                } else if (orderChoice == 5) {
                                                    validOrderChoice = true;
                                                    myCart.emptyTheCart();
                                                    order = -1;
                                                } else {
                                                    myWeb.displayErrorMessage(5);
                                                }
                                            }

                                        }
                                        else {
                                            order = -1;
                                        }
                                    }
                                } else if (homeChoice == 2) {

                                } else if (homeChoice == 3) {

                                } else if (homeChoice == 4) {

                                } else if (homeChoice == 5) {

                                } else if (homeChoice == 6) {

                                } else if (homeChoice == 7) {
                                    myWeb.contactPage();
                                } else if (homeChoice == 8) {
                                    back = true;
                                    inTheApp = false;
                                } else {
                                    myWeb.displayErrorMessage(5);
                                }
                            }
                            else if (buyerOrAdmin == 2) {
                                myWeb.displayAdminHomePage(username);
                                homeChoice = input.nextInt();
                                if (homeChoice == 1) {
                                    myWeb.displayCatalogue();

                                } else if (homeChoice == 2) {

                                } else if (homeChoice == 3) {

                                } else if (homeChoice == 4) {

                                } else if (homeChoice == 5) {

                                } else if (homeChoice == 6) {

                                } else if (homeChoice == 7) {

                                } else if (homeChoice == 8) {
                                    inTheApp = false;
                                } else {
                                    myWeb.displayErrorMessage(5);
                                }
                            }
                        }
                    } else {
                        myWeb.displayErrorMessage(4);
                        int login;
                        boolean validChoice1 = false;
                        while (!validChoice1) {
                            System.out.print("\n\n 1- Re-enter username and password .\n" +
                                    " 2- Forgot password .\n" +
                                    " 3- Back .\n\n" +
                                    "Please ,  enter the number of the choice you want : ");
                            login = input.nextInt();
                            if (login == 1) {
                                validChoice1 = true;
                            } else if (login == 2) {
                                validChoice1 = true;
                                boolean matchedOTP = false;
                                while (!matchedOTP) {
                                    System.out.println("\n\nWe will send you an OTP to login with only one time .. ");
                                    String sentOTP = myWeb.sendOTP();
                                    if (myWeb.checkOTP(sentOTP)) {
                                        myWeb.displayBuyerHomePage(username);
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
                            } else if (login == 3) {
                                validChoice1 = true;
                                correctData = true;
                                inTheApp = false;
                            } else {
                                myWeb.displayErrorMessage(5);
                            }
                        }
                    }
                }
            } else if (choice == 2) {
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
                                while (!matchedOTP) {
                                    System.out.println("\n\nWe will send you an OTP to check if you have an access to entered email .. ");
                                    String sentOTP = myWeb.sendOTP();
                                    if (myWeb.checkOTP(sentOTP)) {
                                        UserAccount account = new UserAccount(name , email , pass , address , phone);
                                        Buyer newUser = new Buyer(account);
                                        newUser.register(newUser , myWeb.getDatabase());
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
                            int renterData;
                            boolean validChoice = false;
                            while (!validChoice) {
                                System.out.print("\n\n 1- Re-enter your data ." +
                                        "\n 2- Back to main menu .\n\n" +
                                        "Enter the number of the choice you want : ");
                                renterData = input.nextInt();
                                if (renterData == 1) {
                                    validChoice = true;
                                } else if (renterData == 2) {
                                    validChoice = true;
                                    validData = true;
                                    uniqueName = true;
                                } else {
                                    myWeb.displayErrorMessage(5);
                                }
                            }
                        }
                    }
                }
            } else if (choice == 3) {
                myWeb.displayCatalogue();
            } else if (choice == 4) {
                myWeb.contactPage();
            } else if (choice == 5) {
                myWeb.getDatabase().saveData();
                System.out.println("\n\nThank you for choosing Toffee .\n" +
                        "We hope this isn't the last time :) \n\n");
                System.exit(1);
            } else {
                myWeb.displayErrorMessage(5);
            }
        }
    }
}

