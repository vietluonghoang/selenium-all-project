/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import elements.ElementsCheck;
import entities.MenuItem;
import gui.CheckAeriaHomePage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 *
 * @author Admin
 */
public class CheckHeaderMenu extends Thread {

    private CheckAeriaHomePage main;
    private ElementsCheck ec;
    private WebDriver driver;
    private JTextArea taResult;
    private JTextArea taError;
    private String territory = "English";

    public void setTerritory(String territory) {
        this.territory = territory;
    }

    public CheckHeaderMenu() {
        ec = new ElementsCheck();
    }

    public CheckHeaderMenu(WebDriver driver, JTextArea taResult, String territory) {
        this.driver = driver;
        this.taResult = taResult;
        this.territory = territory;
        ec = new ElementsCheck();
    }

    public CheckHeaderMenu(WebDriver driver, JTextArea taResult, JTextArea taError, String territory) {
        this.driver = driver;
        this.taResult = taResult;
        this.taError = taError;
        this.territory = territory;
        ec = new ElementsCheck();
    }
    
    public CheckHeaderMenu(CheckAeriaHomePage main, WebDriver driver, JTextArea taResult, JTextArea taError, String territory) {
        this.main=main;
        this.driver = driver;
        this.taResult = taResult;
        this.taError = taError;
        this.territory = territory;
        ec = new ElementsCheck();
    }

    public CheckHeaderMenu(ElementsCheck ec, WebDriver driver, JTextArea taResult) {
        this.ec = ec;
        this.driver = driver;
        this.taResult = taResult;
    }

    public CheckHeaderMenu(WebDriver driver, JTextArea taResult) {
        ec = new ElementsCheck();
        this.driver = driver;
        this.taResult = taResult;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void setTaResult(JTextArea taResult) {
        this.taResult = taResult;
    }

    private void checkAllMenuLinks(WebDriver driver) {
        try {
            ArrayList<MenuItem> track = new ArrayList<MenuItem>();
            MenuItem headerMenu = new MenuItem(driver.findElement(By.xpath("//div[@id='header']")));
            taResult.append("\n\nChecking all header menu links.\n");
            ec.setMenuIChildElements(headerMenu, driver);
            checkMenuItems(headerMenu, track);
        } catch (Exception ex) {
            taResult.append("\nERROR\nError on checkingAllMenuLinks.\n" + ex.getMessage());
            taError.append("\nERROR\nError on checkingAllMenuLinks.\n" + ex.getMessage());
        }
    }

    private boolean checkMenuLinks(WebElement webElement) {
        // taResult.append("\n * " + webElement.getAttribute("title") + ": " + webElement.getAttribute("href") + ": ");
        try {
            taResult.append("\n - Response: ");
            int code = ec.getHttpResponseCode(webElement.getAttribute("href"));
            if (code == 200) {
                taResult.append("Passed.");
                return true;
            } else {
                taResult.append("Failed.\n\tResponse Code: " + code);
                taError.append("\n Error on check header menu:\n\t- "+webElement.getAttribute("title")+": "+webElement.getAttribute("href")+": Failed.\n\tResponse Code: " + code);
                return false;
            }
        } catch (Exception ex) {
            taResult.append("\nERROR\nError on checking menu.\n" + ex.getMessage());
            taError.append("\nERROR\nError on checking menu.\n" + ex.getMessage());
            return false;
        }
    }

    private void checkMenuItems(MenuItem menuItem, List<MenuItem> track) {
        try {
            Actions act = new Actions(driver);
            if ("a".equals(menuItem.getItem().getTagName())) {
                track.add(menuItem);
                if (!"javascript:void(0);".equals(menuItem.getItem().getAttribute("href"))) {
                    if (ec.isElementVisible(driver, menuItem.getItem())) {
                        act.moveToElement(menuItem.getItem(), 10, 10).build().perform();
                    } else {
                        for (MenuItem menuTrack : track) {
                            Thread.sleep(100);
                            act.moveToElement(menuTrack.getItem()).build().perform();
                        }
                    }
                    taResult.append("\n * " + menuItem.getItem().getAttribute("title") + " - " + menuItem.getItem().getAttribute("href") + ": ");
                    checkMenuLinks(menuItem.getItem());
                } else {
                    if ("".equals(menuItem.getItem().getAttribute("title"))) {
                        taResult.append("\n * " + menuItem.getItem().getAttribute("alt") + ": ");
                        act.moveToElement(driver.findElement(By.id("menu-search"))).build().perform();
                        Thread.sleep(200);
                        if (ec.isElementVisible(driver, menuItem.getItem())) {
                            menuItem.getItem().click();
                            Thread.sleep(200);
                        } else {
                            for (MenuItem menuTrack : track) {
                                Thread.sleep(100);
                                act.moveToElement(menuTrack.getItem()).build().perform();
                            }
                        }
                        String altAttr = menuItem.getItem().getAttribute("alt");
                        String label = driver.findElement(By.id("search-term-label")).getText();
                        String relAttr = menuItem.getItem().getAttribute("rel");
                        String action = driver.findElement(By.id("mSearch")).getAttribute("action");
                        if (altAttr.equals(label)
                                && ("http://test.aeriagames.com" + relAttr).equals(action)) {
                            taResult.append("Passed.");
                        } else {
                            taResult.append("Failed.");
                            taError.append("\n * Search Item - " + menuItem.getItem().getAttribute("alt") + ": Failed.");
                        }
                    }
                }
            }
            for (int i = 0; i < menuItem.size(); i++) {
                checkMenuItems(menuItem.getChildItem(i), track);
            }
        } catch (Exception ex) {
            taResult.append("\nERROR\nError on checkMenuItems.\n" + ex.getMessage());
            taError.append("\nERROR\nError on checkMenuItems.\n" + ex.getMessage());
        }
    }

    public void run() {
        try {
            if (!ec.isTerritorySelected(driver, territory)) {
                ec.selectTerritory(driver, territory);
            }
        } catch (Exception ex) {
            taResult.append("\nERROR\n\t" + ex.getMessage());
            taError.append("\nERROR\n\t" + ex.getMessage());
        }
        checkAllMenuLinks(driver);
        main.enableAll();
    }
}
