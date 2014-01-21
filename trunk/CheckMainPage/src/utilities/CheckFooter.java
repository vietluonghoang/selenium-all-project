/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import elements.ElementsCheck;
import gui.CheckAeriaHomePage;
import java.util.List;
import javax.swing.JTextArea;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author Admin
 */
public class CheckFooter extends Thread {

    private CheckAeriaHomePage main;
    private ElementsCheck ec;
    private WebDriver driver;
    private JTextArea taResult;
    private JTextArea taError;
    private String territory = "English";

    public CheckFooter(WebDriver driver, JTextArea taResult, String territory) {
        this.driver = driver;
        this.taResult = taResult;
        ec = new ElementsCheck();
        this.territory = territory;
    }

    public CheckFooter(WebDriver driver, JTextArea taResult, JTextArea taError, String territory) {
        this.driver = driver;
        this.taResult = taResult;
        this.taError = taError;
        ec = new ElementsCheck();
        this.territory = territory;
    }
    
    public CheckFooter(CheckAeriaHomePage main, WebDriver driver, JTextArea taResult, JTextArea taError, String territory) {
        this.main=main;
        this.driver = driver;
        this.taResult = taResult;
        this.taError = taError;
        ec = new ElementsCheck();
        this.territory = territory;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void setTaResult(JTextArea taResult) {
        this.taResult = taResult;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }

    private void checkFooterLinks() {
        taResult.append("\n\nChecking footer links:\n");
        List<WebElement> allLinks = driver.findElements(By.xpath("//div[@id='footer-wrap']//a"));
        for (WebElement webElement : allLinks) {
            int code = 0;
            try {
                taResult.append("\n* " + webElement.getAttribute("title") + ": ");
                code = ec.getHttpResponseCode(webElement.getAttribute("href"));
            } catch (Exception ex) {
                taResult.append("\nERROR:\n\t" + ex.getMessage());
                taError.append("\n\nChecking footer links:\n* " + webElement.getAttribute("title") + ": \nERROR:\n\t" + ex.getMessage());
            }
            if (code == 200) {
                taResult.append("Passed.");
            } else {
                taResult.append("Failed.\n\tResponse code: " + code);
                taError.append("\n\nChecking footer links:\n* " + webElement.getAttribute("title") + ": Failed.\n\tResponse code: " + code);
            }
        }
    }

    @Override
    public void run() {
        try {
            if (!ec.isTerritorySelected(driver, territory)) {
                ec.selectTerritory(driver, territory);
            }
        } catch (Exception ex) {
            taResult.append("\nERROR\n\t" + ex.getMessage());
            taError.append("\nERROR\n\t" + ex.getMessage());
        }
        checkFooterLinks();
        main.enableAll();
    }
}
