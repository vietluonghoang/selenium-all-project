/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

/**
 *
 * @author Admin
 */
import com.thoughtworks.selenium.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class RedeemGiftCode extends Thread {

    private WebDriver driver;
    private WebDriver driver2;
    private String authorizedUsername = "aeria";
    private String authorizedPassword = "Play2Win";
    private String env = "qa1";
    private String branch = "trunk";
    private String adminPwd = "Gioxanh!23";
    private String adminUser = "sgsvietnam";
    private String eventID = "1118";
    private String targetPwd = "Password1";
    private String targetUser = "phuong003";
    private String targetUserID = "";
    private int repeatedTime = 1000;
    private String accessToken = "b4f9c3fc9e53e226816a791826dd15d2c9a911fe124d1819adf6fbff964b666d";
    private String key = "";
    private int success = 0;
    private String message;
    private JTextArea taResult;
    private String currentWindow1;
    private String currentWindow2;

    public JTextArea getTaResult() {
        return taResult;
    }

    public void setTaResult(JTextArea taResult) {
        this.taResult = taResult;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getTargetPwd() {
        return targetPwd;
    }

    public void setTargetPwd(String targetPwd) {
        this.targetPwd = targetPwd;
    }

    public String getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(String targetUser) {
        this.targetUser = targetUser;
    }

    public int getRepeatedTime() {
        return repeatedTime;
    }

    public void setRepeatedTime(int repeatedTime) {
        this.repeatedTime = repeatedTime;
    }

    public String getMessage() {
        return message;
    }

    public RedeemGiftCode(WebDriver driver, WebDriver driver2) {
        this.driver = driver;
        this.driver2 = driver2;
    }

    public RedeemGiftCode() {
        try {
            CreateWebDrivers cwd = new CreateWebDrivers();
            this.driver = cwd.createFirefoxDriver();
            this.driver2 = cwd.createChromeDriver();
        } catch (Exception e) {
            taResult.append("\nError:\n" + e.getMessage());
        }
    }

    public void Redeem(JTextArea taResult1) {

        try {
            taResult.append("\nStart Redeeming....");

            currentWindow1 = driver.getWindowHandle();
            currentWindow2 = driver2.getWindowHandle();

            LoginToAeria la = new LoginToAeria();

            la.setTestBranchAndEnvironment(driver2, env, branch);
            la.logIntoTestAeria(driver2, targetUser, targetPwd, authorizedUsername, authorizedPassword);

            driver2.get("http://" + authorizedUsername + ":" + authorizedPassword + "@test.aeriagames.com/weblet/apiexplorer/");

            if (isElementPresent(driver2, "accesstoken")) {
                driver2.findElement(By.xpath("//button[@id='getaccesstoken']")).click();
            }
            if (isElementPresent(driver2, "accesstoken") && (!"".equals(driver2.findElement(By.id("accesstoken")).getAttribute(
                    "value")))) {
                accessToken = driver2.findElement(
                        By.xpath("//input[@id='accesstoken']")).getAttribute("value");
            }

            taResult.append("\nAccess Token: " + accessToken);

            la.setTestBranchAndEnvironment(driver, env, branch);
            la.logIntoTestAeria(driver, adminUser, adminPwd, authorizedUsername, authorizedPassword);
            targetUserID = getUserID(driver, authorizedUsername, authorizedPassword, targetUser, eventID);

            taResult.append("\nTarget User ID: " + targetUserID);

            setAccesToken(driver2, authorizedUsername, authorizedPassword, targetUserID, eventID);
            addMoreField(driver2);
        } catch (Exception e) {
            taResult.append("\nError:\n" + e.getMessage());
        }
        for (int i = 0; i < repeatedTime; i++) {
            try {
                List<WebElement> keys = driver.findElements(By
                        .xpath("//input[@name='edit[event_keys][]']"));
                key = keys.get(0).getAttribute("value");

                driver.findElement(
                        By.xpath("//input[@name='edit[key_chkboxes][0]']"))
                        .click();
                driver.findElement(By.xpath("//input[@id='btn_reissue_keys']"))
                        .click();

                driver2.findElement(
                        By.xpath("//div[@id='fields']//input[@class='names']"))
                        .clear();
                driver2.findElement(
                        By.xpath("//div[@id='fields']//input[@class='names']"))
                        .sendKeys("gift_code");
                driver2.findElement(
                        By.xpath("//div[@id='fields']//input[@class='values']"))
                        .clear();
                driver2.findElement(
                        By.xpath("//div[@id='fields']//input[@class='values']"))
                        .sendKeys(key);
                driver2.findElement(By.xpath("//button[@id='apicall']"))
                        .click();
                if (isElementPresent(driver2, "accesstoken")) {
                    success++;
                }

                taResult.append("\nRedeem successful: " + success + " time(s)");
            } catch (Exception e) {
                taResult.append("\nError:\n" + e.getMessage());
                if (isAlertPresent(driver2)) {
                    taResult.append("\nCurrent handled window: " + driver2.getWindowHandle());
                    driver2.switchTo().alert().dismiss();
                }
                try {
                    setAccesToken(driver2, authorizedUsername, authorizedPassword, targetUserID, eventID);
                    addMoreField(driver2);
                } catch (Exception ex) {
                    taResult.append("\nCurrent handled window: " + driver2.getWindowHandle());
                    taResult.append("\nError:\n" + ex.getMessage());
                    if (isAlertPresent(driver2)) {
                        taResult.append("\nCurrent handled window: " + driver2.getWindowHandle());
                        driver2.switchTo().alert().dismiss();
                    }
                }
            }
        }

        taResult.append("\nFinished redeeming!");
        driver.get("http://" + authorizedUsername + ":" + authorizedPassword + "@test.aeriagames.com/logout");
        driver2.get("http://" + authorizedUsername + ":" + authorizedPassword + "@test.aeriagames.com/logout");

        driver.close();
        driver2.close();
        try {
            Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
        } catch (IOException ex) {
            taResult.append("\nIO Exception: \n"+ex.getMessage());
        }
    }

    private boolean isAlertPresent(WebDriver driver) {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException Ex) {
            return false;
        }
    }

    private boolean isElementPresent(WebDriver driver, String id) throws Exception {
        for (int second = 0;; second++) {
            if (second >= 60) {
                System.out.println("timeout");
                return false;
            }
            if (driver.findElements(By.id(id)).size() > 0) {
                break;
            }

            Thread.sleep(1000);

        }

        return true;
    }

    private void setAccesToken(WebDriver driver, String authorizedUsername, String authorizedPassword, String targetUserID, String eventID) throws Exception {
        driver.get("http://" + authorizedUsername + ":" + authorizedPassword + "@test.aeriagames.com/weblet/apiexplorer/");

        System.out.println("\nAccess Token - Handle Window: " + driver.getWindowHandle());

        driver.findElement(By.xpath("//button[@id='getaccesstoken']"))
                .click();
        if (isElementPresent(driver, "accesstoken")) {
            driver.findElement(By.xpath("//input[@id='apiurl']")).clear();
            driver.findElement(By.xpath("//input[@id='apiurl']"))
                    .sendKeys(
                    "user/" + targetUserID + "/gift_event/"
                    + eventID);
        }
    }

    private void addMoreField(WebDriver driver) throws Exception {
        driver.findElement(By.xpath("//a[@id='method-button']"))
                .click();
        driver.findElement(
                By.xpath("//ul[@id='method-menu']//a[text()='POST']"))
                .click();

        driver.findElement(By.xpath("//button[@id='addfield']"))
                .click();
    }

    private String getUserID(WebDriver driver, String authorizedUsername, String authorizedPassword, String targetUser, String eventID) throws Exception {
        driver.get("http://" + authorizedUsername + ":" + authorizedPassword + "@admin.test.aeriagames.com/admin/lookup/account?keyword="
                + targetUser);
        String targetUserID = driver.findElement(By.id("edit-user"))
                .getAttribute("value").split(",")[0];
        driver.get("http://admin.test.aeriagames.com/admin/giftredemption/edit/"
                + eventID + "/keys");

        return targetUserID;
    }

    public void run() {
        Redeem(taResult);
    }
}
