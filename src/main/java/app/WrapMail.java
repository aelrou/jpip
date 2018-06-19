package app;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Properties;

public class WrapMail {
    private String server;
    private String protocol;
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
        this.protocol = localRunConfig.EMAIL_PROTOCOL;
        this.port = localRunConfig.EMAIL_PORT;
        this.username = localRunConfig.EMAIL_USERNAME;
        this.password = localRunConfig.EMAIL_PASSWORD;
        this.sender = localRunConfig.EMAIL_SENDER;
        this.recipientList = localRunConfig.EMAIL_RECIPIENT_LIST;
        this.subject = subject;
        this.messageLines = messageLines;
        this.timeout = localRunConfig.EMAIL_TIMEOUT;
    }

    void sendSecure() throws WrapMailException {
        boolean ssl = false;
        boolean tls = false;
        if (protocol.toLowerCase().contains("ssl")) {
            ssl = true;
        } else if (protocol.toLowerCase().contains("tls")) {
            tls = true;
        } else {
            throw new WrapMailException("Email protocol must be \"SSL\" or \"TLS\". Found: " + protocol);
        }
        Properties properties = new Properties();
        properties.put("mail.smtps.host", server);
        properties.put("mail.smtps.ssl.trust", server);
        properties.put("mail.smtps.port", port);
        properties.put("mail.smtps.socketFactory.port", port);
        if (ssl) {
            properties.put("mail.smtps.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        }
        if (tls) {
            properties.put("mail.smtp.starttls.required", true); // This property must be "smtp" instead of "smtps" like the others
        }
        properties.put("mail.smtps.auth", true);
        properties.put("mail.smtps.connectiontimeout", timeout);
        properties.put("mail.smtps.timeout", timeout);
        Session session = Session.getInstance(properties, new Authenticator() {
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

            Transport transport = null;
            if (ssl) {
                transport = session.getTransport("smtps");
            }
            if (tls) {
                transport = session.getTransport("smtp");
            }
            transport.connect(server, port, username, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Email sent");
    }
}
