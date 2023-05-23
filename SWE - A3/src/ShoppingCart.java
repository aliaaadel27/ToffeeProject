import java.util.Scanner;
import java.util.Vector;

public class ShoppingCart {
    private Vector<Item> items;
    private static int id = 10;

    public ShoppingCart(){
        items = new Vector<>();
    }

    public Vector<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
//        int i = 0
//        for (Item Item : items) {
//            if (Item == item)
                items.remove(item);
//        }
    }

    public void placeOrder(Buyer buyer , Website website) {
        Order newOrder = new Order(id);
        for (Item item : items) {
            newOrder.addItem(item);
        }
        newOrder.setStatus(Status.Processing);
        newOrder.setBuyer(buyer);

        Scanner input = new Scanner(System.in);

        String address = "";

        int choice1;
        boolean validChoice1 = false;
        while (!validChoice1) {
            System.out.print("\n\nDo you want the order to be shipped to your registered address :\n\n" +
                    " 1- Yes .\n" +
                    " 2- No , another one .\n\n" +
                    "Please , enter the number of the choice you want : ");
            choice1 = input.nextInt();
            if (choice1 == 1) {
                validChoice1 = true;
                address = buyer.getAccount().getAddress();
            } else if (choice1 == 2) {
                validChoice1 = true;
                System.out.print("\n\nEnter the address you want : ");
                address = input.nextLine();
            } else {
                website.displayErrorMessage(5);
            }
        }

        float price = newOrder.getTotalPrice();

        String phone , paymentMethod = "";
        int choice2;
        boolean validChoice2 = false;
        while (!validChoice2) {
            System.out.print("\n\nHow do you want to pay for the order ? ( " + price + " EGP ) :\n\n" +
                    " 1- Upon delivery .\n" +
                    " 2- Via e-wallet .\n" +
                    " 3- Gift vouchers .\n" +
                    " 4- Loyalty points .\n\n" +
                    "Please , enter the number of the choice you want : ");
            choice2 = input.nextInt();
            if (choice2 == 1) {
                validChoice2 = true;
                boolean matchedOTP = false;
                int choice3;
                boolean validChoice3 = false;
                while (!validChoice3) {
                    System.out.print("\n\nDo you want us to contact you via your registered phone number :\n\n" +
                            " 1- Yes .\n" +
                            " 2- No , another one .\n\n" +
                            "Please , enter the number of the choice you want : ");
                    choice3 = input.nextInt();
                    if (choice3 == 1) {
                        validChoice3 = true;
                        phone = buyer.getAccount().getPhoneNumber();
                    } else if (choice3 == 2) {
                        validChoice3 = true;
                        System.out.print("\n\nEnter the phone number you want : ");
                        phone = input.nextLine();
                    } else {
                        website.displayErrorMessage(5);
                    }
                }
                while (!matchedOTP) {
                    System.out.println("\n\nWe will send you an OTP via phone number .. ");
                    String sentOTP = website.sendOTP(buyer.getAccount().getEmail());
//                    String sentOTP = "bnrniudji";
                    if (website.checkOTP(sentOTP)) {
                        paymentMethod = "c";
                        matchedOTP = true;
                    } else {
                        website.displayErrorMessage(3);
                        int againOTP;
                        boolean validChoice = false;
                        while (!validChoice) {
                            System.out.print("\n\nDo you want us to send you another one ?\n\n" +
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
                                website.displayErrorMessage(5);
                            }
                        }
                    }
                }
            } else if (choice2 == 2) {
                paymentMethod = "w";
                validChoice2 = true;
                String code = "website.sendOTP()";
                System.out.print("\n\nThis is the code to pay with : " + code + "\n\n");
            } else if (choice2 == 3) {
                paymentMethod = "v";
                validChoice2 = true;

            } else if (choice2 == 4) {
                paymentMethod = "l";
                validChoice2 = true;

            } else {
                website.displayErrorMessage(5);
            }
        }

        if (paymentMethod.equals("c")) {
            Cash_on_delivery method = new Cash_on_delivery();
            newOrder.setPaymentMethod(method);
        } else if (paymentMethod.equals("w")) {
            E_wallet method = new E_wallet();
            newOrder.setPaymentMethod(method);
        } else if (paymentMethod.equals("v")) {
            giftVoucher method = new giftVoucher();
            newOrder.setPaymentMethod(method);
        } else if (paymentMethod.equals("l")) {
            Loyalty_points method = new Loyalty_points();
            newOrder.setPaymentMethod(method);
        }

        newOrder.setShippingAddress(address);

        buyer.addOrder(newOrder);

        this.emptyTheCart();

        id++;
    }

    public void viewItems() {
        System.out.println("\n--------------------------------------------------------------------------\n");
        for (Item item : items) {
            item.displayItem();
            System.out.println("\n\n");
        }
        System.out.println("--------------------------------------------------------------------------\n");

    }

    public void emptyTheCart() {
        items = new Vector<>();
//        for (Item item : items) {
//            this.removeItem(item);
//        }

    }
}
