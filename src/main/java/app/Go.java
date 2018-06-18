package app;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Go {

    public static void main(String[] args) throws WorkDirException, WrapJsonException, WrapHttpsConnectException, GoogleResultsParserException, NoSuchAlgorithmException, KeyManagementException, IOException {

        String workDir = new WorkDir(args, "app.jar").dir();

        File localRunConfigFile = new WrapJson(workDir).findLocalRunConfig("LocalRunConfig.json");
        LocalRunConfig localRunConfig = new WrapJson(workDir).readLocalRunConfig(localRunConfigFile);

        String logFile = Log.find(localRunConfig.LOGS_DIRECTORY, localRunConfig.LOG_NAME);

        File localStatusCacheFile = new WrapJson(workDir).findLocalStatusCache("LocalStatusCache.json");
        LocalStatusCache localStatusCache = new WrapJson(workDir).readLocalStatusCache(localStatusCacheFile);

        LocalDateTime startClock = LocalDateTime.now();

        String googleResponce = new WrapHttpsConnect(localRunConfig.IPCHECK_HOST, localRunConfig.IPCHECK_RESOURCE).useSslSocket();

        String ipAddress = new GoogleResultsParser(googleResponce).getIp();

        new WrapMail(localRunConfig, localRunConfig.DEVICE_NAME, new ArrayList<>(Arrays.asList("This is a test from "+ ipAddress))).sendUsingSsl();

        //        String currentIp = "";
//
//        try {
//
//            WrapGet.waitPage(driver, ipCheckUrl, Loc.XPATH, ipCheckXpath, 10);
//
//            WebElement ipReport;
//            ipReport = WrapLocator.waitDisplay(driver, Loc.XPATH, ipCheckXpath, 10);
//            currentIp = ipReport.getText();
//
//            WrapGet.waitPage(driver, ipNotifyUrl, Loc.XPATH, ipNotifyFormXpath, 10);
//
//        } catch (CustomExceptLocatorType | CustomExceptPageTimeout | CustomExceptElementWait e) {
//            Log.save(logFile, "Failed to check public IP");
//            e.printStackTrace();
//            driver.quit();
//            terminateWebDriver(localRunConfig, localBinConfig.TASKLIST, localBinConfig.TASKKILL, exceptPidList);
//            System.exit(1);
//        }
//
//        if (currentIp.equals(localStatusCache.IP_CACHE)){
//
//            Log.save(logFile, "No change in reported IP "+ currentIp);
//
//        } else {
//
//            Log.save(logFile, "The reported IP "+ currentIp +" is different from the cached IP "+ localStatusCache.IP_CACHE);
//
//            try {
//
//                WebElement notifyForm;
//                notifyForm = WrapLocator.waitClickable(driver, Loc.XPATH, ipNotifyFormXpath, 10);
//                Actions actionForm = new Actions(driver);
//                actionForm.moveToElement(notifyForm).build().perform();
//                notifyForm.clear();
//                notifyForm.sendKeys(localRunConfig.DEVICE_NAME);
//
//                WebElement submitButton;
//                submitButton = WrapLocator.waitClickable(driver, Loc.XPATH, ipNotifySubmitXpath, 10);
//                Actions actionSubmit = new Actions(driver);
//                actionSubmit.moveToElement(submitButton).build().perform();
//                submitButton.click();
//
//                WebElement confirmMessage;
//                confirmMessage = WrapLocator.waitDisplay(driver, Loc.XPATH, ipNotifyConfirmXpath, 10);
//                confirmMessage.getText();
//
//                new WrapJson(workDir).updateLocalStatusCache(localStatusCacheFile, currentIp);
//
//            } catch (CustomExceptLocatorType | CustomExceptElementWait e) {
//                Log.save(logFile, "Failed to send update notification");
//                e.printStackTrace();
//                driver.quit();
//                terminateWebDriver(localRunConfig, localBinConfig.TASKLIST, localBinConfig.TASKKILL, exceptPidList);
//                System.exit(1);
//            }
//        }
//
//        driver.quit();
//
//        terminateWebDriver(localRunConfig, localBinConfig.TASKLIST, localBinConfig.TASKKILL, exceptPidList);

        LocalDateTime stopClock = LocalDateTime.now();
        Duration duration = Duration.between(startClock, stopClock);
        Log.save(logFile, "Process time " + duration.getSeconds() + " sec");
    }
}

