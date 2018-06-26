package app;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GoogleResultsParser {
    public static String getIp(String results) throws GoogleResultsParserException {
        if (results == null || results.trim().isEmpty()) {
            throw new GoogleResultsParserException("No HTML to parse.");
        }        String start = "<div id=\"ires\"><ol><div";
        if (results.indexOf(start) < 0) {
            throw new GoogleResultsParserException("Unable to find \"" + start + "\" in HTML.");
        }
        String end = "Your public IP address</div>";
        if (results.indexOf(end) < 0) {
            throw new GoogleResultsParserException("Unable to find \"" + end + "\" in HTML.");
        }
        String section = results.substring(results.indexOf(start), results.lastIndexOf(end));
        Pattern pattern = Pattern.compile(".+>(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})</div>.+", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(section);
        String ip;
        if (matcher.matches() && matcher.groupCount() == 1) {
            ip = matcher.group(1);
        } else {
            throw new GoogleResultsParserException("Unable to match IPv4 pattern in HTML: " + section);
        }
        System.out.println("GoogleResultsParser " + ip);
        return ip;
    }
}
