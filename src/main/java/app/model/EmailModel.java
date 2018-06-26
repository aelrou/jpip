package app.model;

import java.util.ArrayList;

public class EmailModel {
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


    public EmailModel(JsonRunModel jsonRunModel, String subject, ArrayList<String> messageLines) {
        this.server = jsonRunModel.EMAIL_SERVER;
        this.protocol = jsonRunModel.EMAIL_PROTOCOL;
        this.port = jsonRunModel.EMAIL_PORT;
        this.username = jsonRunModel.EMAIL_USERNAME;
        this.password = jsonRunModel.EMAIL_PASSWORD;
        this.sender = jsonRunModel.EMAIL_SENDER;
        this.recipientList = jsonRunModel.EMAIL_RECIPIENT_LIST;
        this.subject = subject;
        this.messageLines = messageLines;
        this.timeout = jsonRunModel.EMAIL_TIMEOUT;
    }

    public String getServer() {
        return server;
    }

    public String getProtocol() {
        return protocol;
    }

    public int getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSender() {
        return sender;
    }

    public ArrayList<String> getRecipientList() {
        return recipientList;
    }

    public String getSubject() {
        return subject;
    }

    public ArrayList<String> getMessageLines() {
        return messageLines;
    }

    public int getTimeout() {
        return timeout;
    }
}
