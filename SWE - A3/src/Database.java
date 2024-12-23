import java.io.*;
import java.util.*;


public class Database {
    private Vector<Buyer> buyers;
    private Vector<Admin> admins;
//    private Vector<Item> items;
    private Catalogue catalogue;
    private Vector<Order> orders;


    public Database() {
        buyers = new Vector<>();
        admins = new Vector<>();
        orders = new Vector<>();
        catalogue = new Catalogue();
//        for (Category category : catalogue.getCategories()) {
//            category.s = new Vector<>();
//        }

        try {
            BufferedReader names = new BufferedReader(new FileReader("usernames.txt"));
            String line = names.readLine();
            BufferedReader emails = new BufferedReader(new FileReader("emails.txt"));
            String emailLine = emails.readLine();
            BufferedReader passwords = new BufferedReader(new FileReader("passwords.txt"));
            String passLine = passwords.readLine();
            BufferedReader addresses = new BufferedReader(new FileReader("addresses.txt"));
            String adrsLine = addresses.readLine();
            BufferedReader pNums = new BufferedReader(new FileReader("pNumbers.txt"));
            String phNumLine = pNums.readLine();
            BufferedReader points = new BufferedReader(new FileReader("LoyaltyPoints.txt"));
            String pointsLine = points.readLine();

            while (line != null) {
//                System.out.println(line);
//                System.out.println(emailLine);
//                System.out.println(passLine);
//                System.out.println(adrsLine);
//                System.out.println(phNumLine);
//                System.out.println(pointsLine);
                UserAccount newUserAccount = new UserAccount(line,emailLine,passLine,adrsLine,phNumLine);
                Buyer newBuyer = new Buyer(newUserAccount);
                if (pointsLine != null) {
                    newBuyer.addLoyaltyPoints(Integer.parseInt(pointsLine));
//                    System.out.println(Integer.parseInt(pointsLine));
                }
                this.addBuyer(newBuyer);
//                System.out.println(newBuyer.getAccount().getUserName());
//                System.out.println(newBuyer.getAccount().getPassword());
//                System.out.println(newBuyer.getAccount().getEmail());
//                System.out.println(newBuyer.getAccount().getAddress());
//                System.out.println(newBuyer.getAccount().getPhoneNumber());
//                System.out.println(newBuyer.getLoyaltyPoints());
//                System.out.println("\n\n");
                pointsLine = points.readLine();
                phNumLine = pNums.readLine();
                adrsLine = addresses.readLine();
                emailLine = emails.readLine();
                passLine = passwords.readLine();
                line = names.readLine();
            }

            names.close();
            emails.close();
            passwords.close();
            addresses.close();
            pNums.close();
            points.close();

//            for (Buyer buyer : buyers) {
//                System.out.println(buyer.getAccount().getUserName());
//                System.out.println(buyer.getAccount().getPassword());
//                System.out.println(buyer.getAccount().getEmail());
//                System.out.println(buyer.getAccount().getAddress());
//                System.out.println(buyer.getAccount().getPhoneNumber());
//                System.out.println(buyer.getLoyaltyPoints());
//                System.out.println("\n\n");
//            }

///////////////////////////////////////////////////////////////////////////////////////////////////////

            BufferedReader A_IDs = new BufferedReader(new FileReader("adminIDs.txt"));
            String aIdLine = A_IDs.readLine();
            BufferedReader A_names = new BufferedReader(new FileReader("adminUsernames.txt"));
            String aNameLine = A_names.readLine();
            BufferedReader A_emails = new BufferedReader(new FileReader("adminEmails.txt"));
            String aEmailLine = A_emails.readLine();
            BufferedReader A_passwords = new BufferedReader(new FileReader("adminPasswords.txt"));
            String aPassLine = A_passwords.readLine();
            BufferedReader A_addresses = new BufferedReader(new FileReader("adminAddresses.txt"));
            String aAdrsLine = A_addresses.readLine();
            BufferedReader A_phNum = new BufferedReader(new FileReader("adminPhoneNumbers.txt"));
            String aPhNumLine = A_phNum.readLine();


            while (aNameLine != null) {
                UserAccount newAdminUserAccount = new UserAccount(aNameLine,aEmailLine,aPassLine,aAdrsLine,aPhNumLine);
                Admin newAdmin = new Admin(newAdminUserAccount);
                newAdmin.setAdminID(Integer.parseInt(aIdLine));
                this.addAdmin(newAdmin);
                aIdLine = A_IDs.readLine();
                aNameLine = A_names.readLine();
                aEmailLine = A_emails.readLine();
                aPassLine = A_passwords.readLine();
                aAdrsLine = A_addresses.readLine();
                aPhNumLine = A_phNum.readLine();
            }

            A_IDs.close();
            A_names.close();
            A_emails.close();
            A_passwords.close();
            A_addresses.close();
            A_phNum.close();

///////////////////////////////////////////////////////////////////////////////////////////////////////

            BufferedReader Items = new BufferedReader(new FileReader("items.txt"));
            String itemLine = Items.readLine();

            while (itemLine != null) {
                Item newItem = new Item();

                newItem.setId(Integer.parseInt(itemLine));
                itemLine = Items.readLine();

                newItem.setName(itemLine);
                itemLine = Items.readLine();

                newItem.setBrand(itemLine);
                itemLine = Items.readLine();

                newItem.setPrice(Integer.parseInt(itemLine));
                itemLine = Items.readLine();

                newItem.setCategory(itemLine);
                itemLine = Items.readLine();

                newItem.setDiscountPercentage(Integer.parseInt(itemLine));
                itemLine = Items.readLine();

                newItem.setDescription(itemLine);

                this.addItem(newItem);

                for (Category category : catalogue.getCategories()) {
                    if (category.getName().equals(newItem.getCategory())) {
                        category.addItem(newItem);
                    }
                }

                itemLine = Items.readLine();
            }

            Items.close();


//            for (Item item : items) {
//                System.out.println("ID : " + item.getId());
//                System.out.println("Name : " + item.getName());
//                System.out.println("Brand : " + item.getBrand());
//                System.out.println("Price : " + item.getPrice());
//                System.out.println("Category : " + item.getCategory());
//                System.out.println("Discount percentage : " + item.getDiscountPercentage());
//                System.out.println("Description : " + item.getDescription());
//                System.out.println("\n\n");
//            }

///////////////////////////////////////////////////////////////////////////////////////////////////////

            BufferedReader itemsInOrders = new BufferedReader(new FileReader("itemsInOrders.txt"));
            String orderLine = itemsInOrders.readLine();

            while (orderLine != null) {

//                System.out.println("before 1 : " + orderLine);

//                int orderID = Integer.parseInt(orderLine);
//
//                for (Order order : orders) {
//                    if (order.getID() == orderID)
//                        Order newOrder = new Order(Integer.parseInt(orderLine));
//                }
                Order newOrder = new Order(Integer.parseInt(orderLine));
                orderLine = itemsInOrders.readLine();

//                System.out.println("after 1 : " + newOrder.getID());
//                System.out.println("before 2 : " + orderLine);

                newOrder.setShippingAddress(orderLine);
                orderLine = itemsInOrders.readLine();

//                System.out.println("after 2 : " + newOrder.getShippingAddress());
//                System.out.println("before 3 : " + orderLine);

                for (Category category : catalogue.getCategories()) {
                    for (Item item : category.getItems()) {
                        if (orderLine != null) {

//                            System.out.println("before 3 : " + orderLine);

                            if (item.getId() == Integer.parseInt(orderLine)) {

                                orderLine = itemsInOrders.readLine();

//                                System.out.println("after 3 : " + item.getId());
//                                System.out.println("before 4 : " + orderLine);

                                int quantity = Integer.parseInt(orderLine);

                                for (int i = 0; i < quantity; i++)
                                    newOrder.addItem(item);

                                orderLine = itemsInOrders.readLine();

//                                System.out.println("after 4 : " + quantity);
//                                System.out.println("before 5 : " + orderLine);

                                for (Buyer buyer : buyers) {
                                    if (buyer.getAccount().getUserName().equals(orderLine)) {
//                                        System.out.println("after 5 : " + buyer.getAccount().getUserName());
                                        newOrder.setBuyer(buyer);
                                        buyer.reOrder(newOrder, newOrder.getID());
//                                        System.out.println("after 5 : " + newOrder.getBuyer().getAccount().getUserName());
                                    }
                                }

                                orderLine = itemsInOrders.readLine();

//                                System.out.println("before 6 : " + orderLine);

                                if (orderLine.equals("processing"))
                                    newOrder.setStatus(Status.Processing);
                                else if (orderLine.equals("shipping"))
                                    newOrder.setStatus(Status.Shipping);
                                else if (orderLine.equals("delivered"))
                                    newOrder.setStatus(Status.Delivered);

                                orderLine = itemsInOrders.readLine();

//                                System.out.println("after 6 : " + newOrder.getStatus());
//                                System.out.println("before 7 : " + orderLine);

                                if (orderLine.equals("c")) {
                                    Cash_on_delivery payment = new Cash_on_delivery();
                                    newOrder.setPaymentMethod(payment);
                                } else if (orderLine.equals("v")) {
                                    giftVoucher payment = new giftVoucher();
                                    newOrder.setPaymentMethod(payment);
                                } else if (orderLine.equals("l")) {
                                    Loyalty_points payment = new Loyalty_points();
                                    newOrder.setPaymentMethod(payment);
                                } else if (orderLine.equals("w")) {
                                    E_wallet payment = new E_wallet();
                                    newOrder.setPaymentMethod(payment);
                                }

//                                System.out.println("after 7 : " + newOrder.getPaymentMethod());

                                newOrder.calculateTotalPrice();

//                                System.out.println("price : " + newOrder.getTotalPrice() + "\n\n");

                                this.addOrder(newOrder);

                                orderLine = itemsInOrders.readLine();

                            }
                        }
                    }
                }
            }

            itemsInOrders.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Vector<Buyer> getBuyers() {
        return buyers;
    }

    public Vector<Admin> getAdmins() {
        return admins;
    }

    public Catalogue getCatalogue() {
        return catalogue;
    }

    public Vector<Order> getOrders() {
        return orders;
    }

    public void addBuyer(Buyer buyer) {
        buyers.add(buyer);
    }

    public void addAdmin(Admin admin) {
        admins.add(admin);
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void addItem(Item item) {
        for (Category category : catalogue.getCategories()) {
            if (category.getName().equals(item.getCategory()))
                category.getItems().add(item);
        }
    }

    public void saveData() {
        try {
            BufferedWriter usernames = new BufferedWriter(new FileWriter("usernames.txt", false));
            BufferedWriter emails = new BufferedWriter(new FileWriter("emails.txt", false));
            BufferedWriter passwords = new BufferedWriter(new FileWriter("passwords.txt", false));
            BufferedWriter addresses = new BufferedWriter(new FileWriter("addresses.txt", false));
            BufferedWriter phNums = new BufferedWriter(new FileWriter("pNumbers.txt", false));
            BufferedWriter points = new BufferedWriter(new FileWriter("LoyaltyPoints.txt", false));
            BufferedWriter A_ids = new BufferedWriter(new FileWriter("adminIDs.txt" , false));
            BufferedWriter A_usernames = new BufferedWriter(new FileWriter("adminUsernames.txt", false));
            BufferedWriter A_emails = new BufferedWriter(new FileWriter("adminEmails.txt" , false));
            BufferedWriter A_passwords = new BufferedWriter(new FileWriter("adminPasswords.txt", false));
            BufferedWriter A_addresses = new BufferedWriter(new FileWriter("adminAddresses.txt", false));
            BufferedWriter A_phNums = new BufferedWriter(new FileWriter("adminPhoneNumbers.txt", false));
            BufferedWriter Items = new BufferedWriter(new FileWriter("items.txt", false));
            BufferedWriter itemsInOrders = new BufferedWriter(new FileWriter("itemsInOrders.txt", false));

            for (Buyer buyer : buyers) {
                usernames.write(buyer.getAccount().getUserName() + "\n");
                emails.write(buyer.getAccount().getEmail() + "\n");
                passwords.write(buyer.getAccount().getPassword() + "\n");
                addresses.write(buyer.getAccount().getAddress() + "\n");
                phNums.write(buyer.getAccount().getPhoneNumber() + "\n");
                points.write(buyer.getLoyaltyPoints() + "\n");
            }

            for (Admin admin : admins) {
                A_ids.write(admin.getAdminID() + "\n");
                A_usernames.write(admin.getAdminName() + "\n");
                A_emails.write(admin.getAccount().getEmail() + "\n");
                A_passwords.write(admin.getAccount().getPassword() + "\n");
                A_addresses.write(admin.getAccount().getAddress() + "\n");
                A_phNums.write(admin.getAccount().getPhoneNumber() + "\n");
            }

            for (Category category : catalogue.getCategories()) {
                for (Item item : category.getItems()) {
                    Items.write(item.getId() + "\n");
                    Items.write(item.getName() + "\n");
                    Items.write(item.getBrand() + "\n");
                    Items.write(item.getPrice() + "\n");
                    Items.write(item.getCategory() + "\n");
                    Items.write(item.getDiscountPercentage() + "\n");
                    Items.write(item.getDescription() + "\n");
                }
            }

            Vector<Item> duplicate = new Vector<>();

            for (Order order : orders) {
                for (Item item : order.getItems()) {
                    for (Item duplicateItem : duplicate) {
                        if (item != duplicateItem) {

                            int quantity = 0;

                            itemsInOrders.write(order.getID() + "\n");
                            itemsInOrders.write(order.getShippingAddress() + "\n");
                            itemsInOrders.write(item.getId() + "\n");

                            for (Item item2 : order.getItems()) {
                                if (item == item2)
                                    quantity++;
                            }

                            itemsInOrders.write(quantity + "\n");
                            itemsInOrders.write(order.getBuyer().getAccount().getUserName() + "\n");
                            itemsInOrders.write(order.getStatus() + "\n");
                            itemsInOrders.write(order.getPaymentMethod() + "\n");

                            duplicate.add(item);
                        }
                    }
                }
            }

            usernames.close();
            emails.close();
            passwords.close();
            phNums.close();
            addresses.close();
            points.close();
            A_ids.close();
            A_usernames.close();
            A_emails.close();
            A_passwords.close();
            A_addresses.close();
            A_phNums.close();
            Items.close();
            itemsInOrders.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
