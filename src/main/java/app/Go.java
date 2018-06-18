package app;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Go {

    public static void main(String[] args) throws WorkDirException, WrapJsonException {

        String workDir = new WorkDir(args, "app.jar").dir();

        File localRunConfigFile = new WrapJson(workDir).findLocalRunConfig("LocalRunConfig.json");
        LocalRunConfig localRunConfig = new WrapJson(workDir).readLocalRunConfig(localRunConfigFile);


        File localLogConfigFile = new WrapJson(workDir).findLocalLogConfig("LocalLogConfig.json");
        LocalLogConfig localLogConfig = new WrapJson(workDir).readLocalLogConfig(localLogConfigFile);

        File localStatusCacheFile = new WrapJson(workDir).findLocalStatusCache("LocalStatusCache.json");
        LocalStatusCache localStatusCache = new WrapJson(workDir).readLocalStatusCache(localStatusCacheFile);

        Log log = new Log(localLogConfig);
        log.find();

        LocalDateTime startClock = LocalDateTime.now();

        try {
            String googleResponse = new WrapHttpsConnect(localRunConfig.IPCHECK_HOST, localRunConfig.IPCHECK_RESOURCE).useSslSocket();
            String ipAddress = new GoogleResultsParser(googleResponse).getIp();
            if (ipAddress.equals(localStatusCache.IP_CACHE)) {
                log.save("No change in IP " + ipAddress);
            } else {
                log.save("The current IP " + ipAddress + " is different from the cached IP " + localStatusCache.IP_CACHE);
                try {
                    ArrayList<String> messageLines = new ArrayList<>();
                    messageLines.add(ipAddress);
                    new WrapMail(localRunConfig, localRunConfig.DEVICE_NAME, messageLines).sendUsingSsl();
                } catch (Exception e) {
                    e.printStackTrace();
                    log.save("Failed to send email");
                }
            }
            new WrapJson(workDir).updateLocalStatusCache(localStatusCacheFile, ipAddress);
        } catch (WrapHttpsConnectException whce) {
            whce.printStackTrace();
            log.save("Failed to get results from Google");
        } catch (GoogleResultsParserException grpe) {
            grpe.printStackTrace();
            log.save("Failed to parse IP from Google results");
        } catch (Exception e) {
            e.printStackTrace();
            log.save(e.getMessage());
        }
        LocalDateTime stopClock = LocalDateTime.now();
        Duration duration = Duration.between(startClock, stopClock);
        System.out.print("Process time " + duration.getSeconds() + " sec");
    }
}

