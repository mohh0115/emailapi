package com.email.service;

import org.springframework.stereotype.Service;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {


    /**
     * Service to send the email
     *
     * @param message
     * @param subject
     * @param to
     * @return
     */
    public boolean sendEmail(String message, String subject, String to) {

        //Variable for gmail host
        String host = "smtp.gmail.com";
        String from = "callmerahmath@gmail.com";
        boolean emailFlag = false;

        //Get the system properties
        Properties properties = System.getProperties();
        System.out.println("Properties " + properties);

        //setting important information to properties object

        //set Host
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        //Step 1: To get the Session object
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("callmerahmath@gmail.com", "jamie@123");
            }
        });

        session.setDebug(true);

        //Step 2 : Compose the message (Text, Multi Media)
        MimeMessage m = new MimeMessage(session);
        try {
            m.setFrom(from);

            // adding recipient
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            //adding subject
            m.setSubject(subject);

            //adding text to message
            m.setText(message);

            //Step 3: Send the message using Transport class
            Transport.send(m);
            emailFlag = true;

            System.out.println("Sent successfully ...");


        } catch (Exception e) {
            e.printStackTrace();
        }
        return emailFlag;
    }

}



