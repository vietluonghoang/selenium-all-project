/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.io.File;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 *
 * @author Admin
 */
public class CreateWebDrivers {

    private WebDriver driver = null;

    public WebDriver createChromeDriver() throws Exception {

        DesiredCapabilities caps = DesiredCapabilities.chrome();
        caps.setJavascriptEnabled(true);

        String filePathX86 = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";
        String filePathX64 = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe";

        if ((new File(filePathX86)).isFile()) {
            caps.setCapability("chrome.binary", filePathX86);
        } else if ((new File(filePathX64)).isFile()) {
            caps.setCapability("chrome.binary", filePathX64);
        } else {
            System.out.println("Google Chrome is not installed!");
        }
        String dir = (new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath())).getParent();
        String driverSrc = dir + "\\chromedriver.exe";
        System.out.println(driverSrc);
        if ((new File(dir + "\\chromedriver.exe")).isFile()) {
            System.setProperty("webdriver.chrome.driver", driverSrc);
        } else {
            System.out.println("Google driver is not found!");
        }

        driver = new ChromeDriver(caps);

        return driver;
    }

    public WebDriver createFirefoxDriver() throws Exception{

        driver = new FirefoxDriver();

        return driver;
    }
}
