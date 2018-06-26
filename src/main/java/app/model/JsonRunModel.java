package app.model;

import java.util.ArrayList;
import java.util.Arrays;

public class JsonRunModel {
    public String DEVICE_NAME;
    public String IPCHECK_HOST;
    public String IPCHECK_RESOURCE;

    public String EMAIL_SERVER;
    public String EMAIL_PROTOCOL;
    public int EMAIL_PORT;
    public String EMAIL_USERNAME;
    public String EMAIL_PASSWORD;
    public String EMAIL_SENDER;
    public ArrayList<String> EMAIL_RECIPIENT_LIST;
    public int EMAIL_TIMEOUT;

    public JsonRunModel(boolean useDefault) {
        if (useDefault) {
            this.DEVICE_NAME = "My Unique Device Name";
            this.IPCHECK_HOST = "www.google.com";
            this.IPCHECK_RESOURCE = "/search?q=what+is+my+ip";

            this.EMAIL_SERVER = "smtp.server.com";
            this.EMAIL_PROTOCOL = "SSL";
            this.EMAIL_PORT = 465;
            this.EMAIL_USERNAME = "sender@address.com";
            this.EMAIL_PASSWORD = "password";
            this.EMAIL_SENDER = "My Unique Device Name < sender@address.com >";
            this.EMAIL_RECIPIENT_LIST = new ArrayList<>(Arrays.asList("recipient-1@address.com", "recipient-2@address.com"));
            this.EMAIL_TIMEOUT = 30000;
        } else {
            try { DEVICE_NAME.trim(); } catch (Exception e) { System.out.println("DEVICE_NAME "+ e.getMessage()); }
            try { IPCHECK_HOST.trim(); } catch (Exception e) { System.out.println("IPCHECK_HOST "+ e.getMessage()); }
            try { IPCHECK_RESOURCE.trim(); } catch (Exception e) { System.out.println("IPCHECK_RESOURCE "+ e.getMessage()); }

            try { EMAIL_SERVER.trim(); } catch (Exception e) { System.out.println("EMAIL_SERVER "+ e.getMessage()); }
            try { EMAIL_PROTOCOL.trim(); } catch (Exception e) { System.out.println("EMAIL_PROTOCOL "+ e.getMessage()); }
            try { if (EMAIL_PORT > 0); } catch (Exception e) { System.out.println("EMAIL_PORT "+ e.getMessage()); }
            try { EMAIL_USERNAME.trim(); } catch (Exception e) { System.out.println("EMAIL_USERNAME "+ e.getMessage()); }
            try { EMAIL_PASSWORD.trim(); } catch (Exception e) { System.out.println("EMAIL_PASSWORD "+ e.getMessage()); }
            try { EMAIL_SENDER.trim(); } catch (Exception e) { System.out.println("EMAIL_SENDER "+ e.getMessage()); }
            try { EMAIL_RECIPIENT_LIST.get(0).trim(); } catch (Exception e) { System.out.println("EMAIL_RECIPIENT_LIST "+ e.getMessage()); }
            try { if (EMAIL_TIMEOUT > 0); } catch (Exception e) { System.out.println("EMAIL_TIMEOUT "+ e.getMessage()); }
        }
    }
}
