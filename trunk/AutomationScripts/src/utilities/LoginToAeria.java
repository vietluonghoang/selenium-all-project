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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginToAeria {

    private WebDriver driver;
    private String username;
    private String pwd;
    private String authorizedUsername = "aeria";
    private String authorizedPassword = "Play2Win";

    public LoginToAeria() {
    }

    public LoginToAeria(WebDriver driver, String username, String pwd) {
        this.driver = driver;
        this.username = username;
        this.pwd = pwd;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setTestBranchAndEnvironment(String env, String branch) throws Exception {
        driver.get("http://" + authorizedUsername + ":" + authorizedPassword + "@test.aeriagames.com/test_env_select.php?test_env="
                + env + "&test_branch=" + branch);
    }

    public void setTestBranchAndEnvironment(WebDriver driver, String env, String branch) throws Exception {
        driver.get("http://" + authorizedUsername + ":" + authorizedPassword + "@test.aeriagames.com/test_env_select.php?test_env="
                + env + "&test_branch=" + branch);
    }

    public boolean logIntoTestAeria() throws Exception {
        driver.get("http://" + authorizedUsername + ":" + authorizedPassword + "@test.aeriagames.com/logout");
        driver.get("http://" + authorizedUsername + ":" + authorizedPassword + "@test.aeriagames.com/account/login");
        for (int second = 0;; second++) {
            if (second >= 60) {
                System.out.println("Time out!");
                return false;
            }
            if (driver.findElements(By.xpath("//input[@id='edit-id']")).size() > 0) {
                break;
            }
            Thread.sleep(1000);
        }

        driver.findElement(By.xpath("//input[@id='edit-id']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@id='edit-pass']")).sendKeys(pwd);
        driver.findElement(By.xpath("//input[@id='account_login_submit']")).click();

        return true;
    }

    public boolean logIntoTestAeria(WebDriver driver) throws Exception {
        driver.get("http://" + authorizedUsername + ":" + authorizedPassword + "@test.aeriagames.com/logout");
        driver.get("http://" + authorizedUsername + ":" + authorizedPassword + "@test.aeriagames.com/account/login");
        for (int second = 0;; second++) {
            if (second >= 60) {
                System.out.println("Time out!");
                return false;
            }

            if (driver.findElements(By.xpath("//input[@id='edit-id']")).size() > 0) {
                break;
            }

            Thread.sleep(1000);

        }
        driver.findElement(By.xpath("//input[@id='edit-id']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@id='edit-pass']")).sendKeys(pwd);
        driver.findElement(By.xpath("//input[@id='account_login_submit']")).click();

        return true;
    }

    public boolean logIntoTestAeria(WebDriver driver, String username, String pwd) throws Exception {
        driver.get("http://" + authorizedUsername + ":" + authorizedPassword + "@test.aeriagames.com/logout");
        driver.get("http://" + authorizedUsername + ":" + authorizedPassword + "@test.aeriagames.com/account/login");
        for (int second = 0;; second++) {
            if (second >= 60) {
                System.out.println("Time out!");
                return false;
            }
            if (driver.findElements(By.xpath("//input[@id='edit-id']")).size() > 0) {
                break;
            }

            Thread.sleep(1000);

        }
        driver.findElement(By.xpath("//input[@id='edit-id']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@id='edit-pass']")).sendKeys(pwd);
        driver.findElement(By.xpath("//input[@id='account_login_submit']")).click();

        return true;
    }

    public boolean logIntoTestAeria(WebDriver driver, String username, String pwd, String authorizedUsername, String authorizedPassword) throws Exception {
        driver.get("http://" + authorizedUsername + ":" + authorizedPassword + "@test.aeriagames.com/logout");
        driver.get("http://" + authorizedUsername + ":" + authorizedPassword + "@test.aeriagames.com/account/login");
        for (int second = 0;; second++) {
            if (second >= 60) {
                System.out.println("Time out!");
                return false;
            }
            if (driver.findElements(By.xpath("//input[@id='edit-id']")).size() > 0) {
                break;
            }

            Thread.sleep(1000);

        }
        driver.findElement(By.xpath("//input[@id='edit-id']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@id='edit-pass']")).sendKeys(pwd);
        driver.findElement(By.xpath("//input[@id='account_login_submit']")).click();

        return true;
    }
}
