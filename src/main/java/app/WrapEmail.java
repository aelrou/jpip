package app;

import app.model.EmailModel;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class WrapEmail {

    public static void sendSecure(EmailModel emailModel) throws WrapEmailException {

        boolean ssl = false;
        boolean tls = false;
        if (emailModel.getProtocol().toLowerCase().contains("ssl")) {
            ssl = true;
        } else if (emailModel.getProtocol().toLowerCase().contains("tls")) {
            tls = true;
        } else {
            throw new WrapEmailException("Email protocol must be \"SSL\" or \"TLS\". Found: " + emailModel.getProtocol());
        }
        Properties properties = new Properties();
        properties.put("mail.smtps.host", emailModel.getServer());
        properties.put("mail.smtps.ssl.trust", emailModel.getServer());
        properties.put("mail.smtps.port", emailModel.getPort());
        properties.put("mail.smtps.socketFactory.port", emailModel.getPort());
        if (ssl) {
            properties.put("mail.smtps.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        }
        if (tls) {
            properties.put("mail.smtp.starttls.required", true); // This property must be "smtp" instead of "smtps" like the others
        }
        properties.put("mail.smtps.auth", true);
        properties.put("mail.smtps.connectiontimeout", emailModel.getTimeout());
        properties.put("mail.smtps.timeout", emailModel.getTimeout());
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailModel.getUsername(), emailModel.getPassword());
            }
        });
        session.setDebug(true);
        try {
            String text = "";
            for (int i = 0; i < emailModel.getMessageLines().size(); i += 1) {
                text = text + emailModel.getMessageLines().get(i) + System.getProperty("line.separator");
            }
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailModel.getSender()));
            for (int i = 0; i < emailModel.getRecipientList().size(); i += 1) {
                message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(emailModel.getRecipientList().get(i)));
            }
            message.setSubject(emailModel.getSubject());
            message.setText(text);

            Transport transport = null;
            if (ssl) {
                transport = session.getTransport("smtps");
            }
            if (tls) {
                transport = session.getTransport("smtp");
            }
            transport.connect(emailModel.getServer(), emailModel.getPort(), emailModel.getUsername(), emailModel.getPassword());
            message.saveChanges();
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Email sent");
    }
}
