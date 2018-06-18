package app;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Properties;

public class WrapMail {
    private String server;
    private int port;
    private String username;
    private String password;
    private String sender;
    private ArrayList<String> recipientList;
    private String subject;
    private ArrayList<String> messageLines;
    private int timeout;

    public WrapMail(LocalRunConfig localRunConfig, String subject, ArrayList<String> messageLines) {
        this.server = localRunConfig.EMAIL_SERVER;
        this.port = localRunConfig.EMAIL_PORT;
        this.username = localRunConfig.EMAIL_USERNAME;
        this.password = localRunConfig.EMAIL_PASSWORD;
        this.sender = localRunConfig.EMAIL_SENDER;
        this.recipientList = localRunConfig.EMAIL_RECIPIENT_LIST;
        this.subject = subject;
        this.messageLines = messageLines;
        this.timeout = localRunConfig.EMAIL_TIMEOUT;
    }

    void sendUsingSsl() {
        Properties properties = new Properties();
        properties.put("mail.smtps.host", server);
        properties.put("mail.smtps.ssl.trust", server);
        properties.put("mail.smtps.port", port);
        properties.put("mail.smtps.socketFactory.port", port);
        properties.put("mail.smtps.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtps.auth", true);
        properties.put("mail.smtps.connectiontimeout", timeout);
        properties.put("mail.smtps.timeout", timeout);
        Session session = Session.getInstance(
                properties,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        session.setDebug(true);
        try {
            String text = "";
            for (int i = 0; i < messageLines.size(); i += 1) {
                text = text + messageLines.get(i) + System.getProperty("line.separator");
            }
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            for (int i = 0; i < recipientList.size(); i += 1) {
                message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientList.get(i)));
            }
            message.setSubject(subject);
            message.setText(text);

            Transport transport = session.getTransport("smtps");
            transport.connect(server, port, username, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Message sent");
    }
}
