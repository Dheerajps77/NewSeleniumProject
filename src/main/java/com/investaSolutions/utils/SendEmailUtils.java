package com.investaSolutions.utils;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmailUtils {
    private static final PropertiesManager properties = PropertiesManager.getInstance();

    public static void sendEmailNow(String hostName, String portID, String senderEmailID, String senderPassword,
                                     String[] receiverEmailArray, String[] ccEmailArray, String reportPath) {
        // Prepare SMTP properties
        Properties smtpProperties = new Properties();
        smtpProperties.put("mail.smtp.host", hostName);
        smtpProperties.put("mail.smtp.port", portID);
        smtpProperties.put("mail.smtp.auth", "true");
        smtpProperties.put("mail.smtp.starttls.enable", "true");

        // Email message details
        String subject = properties.getConfig("EMAIL_SUBJECT");
        String body = buildEmailBody();

        // Inline images map
        Map<String, String> inlineImages = new HashMap<>();
        inlineImages.put("report", reportPath);
        addFailedScreenshots(inlineImages);

        // Send email
        try {
            send(smtpProperties, senderEmailID, senderPassword, receiverEmailArray, ccEmailArray, subject, body, inlineImages);
            System.out.println("Email sent.");
        } catch (MessagingException ex) {
            System.err.println("Could not send email.");
            ex.printStackTrace();
        }
    }

    private static String buildEmailBody() {
        return properties.getConfig("EMAIL_BODY_LINE1") +
                properties.getConfig("EMAIL_BODY_LINE2") +
                properties.getConfig("EMAIL_BODY_LINE3") +
                properties.getConfig("EMAIL_BODY_LINE4");
    }

    private static void addFailedScreenshots(Map<String, String> inlineImages) {
        List<String> listOfFiles = SeleniumUtils.getfileNamesFromFolder(System.getProperty("user.dir") + 
                properties.getConfig("FAILEDTEST_SCREENSHOTS_PATH"));
        for (String fileName : listOfFiles) {
            if (!"extentScreenshot.png".equals(fileName)) {
                String screenShotPath = System.getProperty("user.dir") + properties.getConfig("FAILEDTEST_SCREENSHOTS_PATH") + "/" + fileName;
                inlineImages.put("FailedScreenshot" + listOfFiles.indexOf(fileName), screenShotPath);
            }
        }
    }

    private static void send(Properties properties, final String userName, final String password, String[] toAddressArray,
                             String[] ccEmailArray, String subject, String htmlBody, Map<String, String> inlineImages)
            throws MessagingException {

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
        Session session = Session.getInstance(properties, auth);
        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(userName));
        addRecipients(msg, Message.RecipientType.TO, toAddressArray);
        addRecipients(msg, Message.RecipientType.CC, ccEmailArray);

        msg.setSubject(subject);
        msg.setSentDate(new Date());

        Multipart multipart = createMultipart(htmlBody, inlineImages);
        msg.setContent(multipart);

        Transport.send(msg);
    }

    private static void addRecipients(Message msg, Message.RecipientType type, String[] addresses) throws MessagingException {
        for (String address : addresses) {
            msg.addRecipient(type, new InternetAddress(address));
        }
    }

    private static Multipart createMultipart(String htmlBody, Map<String, String> inlineImages) throws MessagingException {
        Multipart multipart = new MimeMultipart();
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(htmlBody, "text/html");
        multipart.addBodyPart(messageBodyPart);

        addInlineImages(multipart, inlineImages);
        return multipart;
    }

    private static void addInlineImages(Multipart multipart, Map<String, String> inlineImages) {
        if (inlineImages != null && !inlineImages.isEmpty()) {
            inlineImages.forEach((contentId, imagePath) -> {
                try {
                    MimeBodyPart imagePart = new MimeBodyPart();
                    imagePart.setHeader("Content-ID", "<" + contentId + ">");
                    imagePart.setDisposition(MimeBodyPart.INLINE);
                    imagePart.attachFile(imagePath);
                    multipart.addBodyPart(imagePart);
                } catch (IOException | MessagingException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
