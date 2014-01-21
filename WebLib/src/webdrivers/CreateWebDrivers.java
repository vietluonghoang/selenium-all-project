/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webdrivers;

import java.io.File;
import java.util.Arrays;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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

        
        String dir = (new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath())).getParent();
        String driverSrc = dir + "\\chromedriver.exe";
        System.out.println(driverSrc);
        
        if ((new File(filePathX86)).isFile()) {
            System.setProperty("webdriver.chrome.driver", driverSrc);
            caps.setCapability("chrome.binary", filePathX86);
        } else if ((new File(filePathX64)).isFile()) {
            System.setProperty("webdriver.chrome.driver", driverSrc);
            caps.setCapability("chrome.switches", Arrays.asList("–disable-extensions"));
            caps.setCapability("chrome.binary", filePathX64);
        } else {
            System.out.println("Google Chrome is not installed!");
        }
        if ((new File(dir + "\\chromedriver.exe")).isFile()) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("user-data-dir=C:/Users/"
                    + System.getProperty("user.name")
                    + "/AppData/Local/Google/Chrome/User Data/Default");
            driver = new ChromeDriver(caps);
        } else {
            System.out.println("Google driver is not found!");
        }
        return driver;
    }

    public WebDriver createChromeDriver(String driverSource) throws Exception {

        DesiredCapabilities caps = DesiredCapabilities.chrome();
//        caps.setJavascriptEnabled(true);

        String filePathX86 = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";
        String filePathX64 = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe";

        
//        String dir = (new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath())).getParent();
//        String driverSrc = dir + "\\chromedriver.exe";
        System.out.println(driverSource);
        
        if ((new File(filePathX86)).isFile()) {
            System.setProperty("webdriver.chrome.driver", driverSource);
            caps.setCapability("chrome.binary", filePathX86);
        } else if ((new File(filePathX64)).isFile()) {
            System.setProperty("webdriver.chrome.driver", driverSource);
//            caps.setCapability("chrome.switches", Arrays.asList("–disable-extensions"));
            caps.setCapability("chrome.binary", filePathX64);
        } else {
            System.out.println("Google Chrome is not installed!");
        }
        if (new File(driverSource).isFile()) {
//            ChromeOptions options = new ChromeOptions();
//            options.addArguments("user-data-dir=C:/Users/"
//                    + System.getProperty("user.name")
//                    + "/AppData/Local/Google/Chrome/User Data/Default");
            driver = new ChromeDriver(caps);
        } else {
            System.out.println("Google driver is not found!");
        }
        return driver;
    }
    
    
    public WebDriver createFirefoxDriver() throws Exception {

        driver = new FirefoxDriver();

        return driver;
    }
}
