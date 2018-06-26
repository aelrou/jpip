package app.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JsonCacheModel {
    public String IP_CACHE;
    public LocalDateTime LAST_UPDATE;

    public JsonCacheModel(boolean useDefault) {
        if (useDefault) {
            this.IP_CACHE = "0.0.0.0";
            this.LAST_UPDATE = LocalDateTime.parse("2001-01-01T01:01:01.1", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } else {
            try { IP_CACHE.trim(); } catch (Exception e) { System.out.println("IP_CACHE "+ e.getMessage()); }
            try { LAST_UPDATE.getYear(); } catch (Exception e) { System.out.println("LAST_UPDATE "+ e.getMessage()); }
        }
    }
}
