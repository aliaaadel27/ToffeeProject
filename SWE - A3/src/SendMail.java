import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
    private String email; //Email to send to
    private String OTP;

    //constructor
    SendMail(String email, String otp){
        this.email = email;
        this.OTP = otp;
    }

    public void send() {

        // Recipient's email ID needs to be mentioned.
        String send_to = email;

        // Sender's email ID needs to be mentioned
//        String sent_from = "Add your email"; //the OTP emails will be sent from this email

        String sent_from = "aadel9711@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("aadel9711@gmail.com", "dloukqadjwxfioah"); //app password

            }

        });

        // Used to debug SMTP issues
        //session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(sent_from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(send_to));

            // Set Subject: header field
            message.setSubject("Toffee Project ( OTP )");

            // Now set the actual message
            message.setText("Hello !\n\n" +
                    "  We hope this email finds you well .\n" +
                    "We are sending this email to you to make sure that you have an access to the entered email .\n" +
                    "This is just a security routine that you do not have to worry about .\n\n" +
                    "The OTP you have to enter to verify your email : \n" +
                    "   " + OTP + "  \n\n" +
                    "Note :\n" +
                    "   If this wasn't you , please , ignore this mail .\n\n\n" +
                    "Best regards ,\n");

            System.out.println("sending OTP...\n\n");
            // Send message
            Transport.send(message);
            System.out.println("OTP Sent Successfully");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }

}