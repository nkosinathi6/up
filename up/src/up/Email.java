/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package up;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {

    public void sendEmail(String studNumber, String compName, String compIP, String AdminEmail) throws IOException {

        final String username = "s214212483@mandela.ac.za";
        final String password = "thebio93";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "outlook.office365.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {
            
            String labNo = compName.substring(0, 4);

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("s214212483@mandela.ac.za"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("s214212483@mandela.ac.za"));
            message.setSubject("Found USB Report");
            message.setText("A lost USB has been found in lab: " + labNo
                            + " on computer: " + compName
                            + " the current user that is using the USB is student number: " + studNumber
                            + "Computer ip address is: " + compIP);

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    public void reportLostUSB(String studNumber, String USBName, String AdminEmail) throws IOException {

        final String username = "s214212483@mandela.ac.za";
        final String password = "thebio93";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "outlook.office365.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(AdminEmail));
            message.setSubject("Lost USB Report");
            message.setText("USB: " + USBName + " \n of student: " + studNumber + " ,has been reported as lost.");

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
}