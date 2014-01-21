/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import elements.ElementsCheck;
import entities.MenuItem;
import gui.CheckAeriaHomePage;
import java.util.ArrayList;
import javax.swing.JTextArea;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

/**
 *
 * @author Admin
 */
public class CheckLanguageSelector extends Thread {

    private CheckAeriaHomePage main;
    private ElementsCheck ec;
    private WebDriver driver;
    private JTextArea taResult;
    private JTextArea taError;
    private String territory = "English";

    public CheckLanguageSelector(WebDriver driver, JTextArea taResult, String territory) {
        this.driver = driver;
        this.taResult = taResult;
        this.territory = territory;
        this.ec = new ElementsCheck();
    }

    public CheckLanguageSelector(WebDriver driver, JTextArea taResult, JTextArea taError, String territory) {
        this.driver = driver;
        this.taResult = taResult;
        this.taError = taError;
        this.territory = territory;
        this.ec = new ElementsCheck();
    }

    public CheckLanguageSelector(CheckAeriaHomePage main,WebDriver driver, JTextArea taResult, JTextArea taError, String territory) {
        this.main=main;
        this.driver = driver;
        this.taResult = taResult;
        this.taError = taError;
        this.territory = territory;
        this.ec = new ElementsCheck();
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

    private void checkLanguageSelect(WebDriver driver) {
        try {
            taResult.append("\nCheck Language Selector:\n");
            MenuItem languageSelector = new MenuItem(driver.findElement(By.id("language-select")));
            ec.setMenuIChildElements(languageSelector, driver);
            ArrayList<String> titles = new ArrayList<String>();
            getLanguageSelectorTitle(titles, languageSelector);
            Actions act = new Actions(driver);
            for (String title : titles) {
                try {
                    taResult.append("\n * " + title + ": ");
                    act.moveToElement(driver.findElement(By.id("language-select"))).build().perform();
                    Thread.sleep(100);
                    if (ec.isElementVisible(driver, driver.findElement(By.xpath("//ul[@id='language-select']//a[@title='" + title + "']")))) {
                        driver.findElement(By.xpath("//ul[@id='language-select']//a[@title='" + title + "']")).click();
                    } else {
                        act.moveToElement(driver.findElement(By.id("language-select"))).build().perform();
                        Thread.sleep(100);
                        driver.findElement(By.xpath("//ul[@id='language-select']//a[@title='" + title + "']")).click();
                    }
                    if (ec.isElementPresent(driver, "lang-select-container")) {
                        act.moveToElement(driver.findElement(By.id("language-select"))).build().perform();
                        Thread.sleep(100);
                        String classAttr = driver.findElement(By.xpath("//ul[@id='language-select']//a[@title='" + title + "']")).getAttribute("class");
                        if ("lang-select-linkselected".equals(classAttr)) {
                            taResult.append("Passed.");
                        } else {
                            taResult.append("Failed.");
                            taError.append("\nCheck Language Selector:\n * " + title + ": Failed.");
                        }
                    } else {
                        taResult.append("Not found.");
                        taError.append("\nCheck Language Selector:\n * " + title + ": Not found.");
                    }
                } catch (Exception ex) {
                    taError.append("\nCheck Language Selector:\n ERROR\n"+ex.getMessage());
                }
            }
        } catch (Exception ex) {
            taResult.append("\nERROR\nError on checkLanguageSelect.\n" + ex.getMessage());
            taError.append("\nERROR\nError on checkLanguageSelect.\n" + ex.getMessage());
        }
    }

    private void getLanguageSelectorTitle(ArrayList<String> menu, MenuItem parentItem) throws Exception {
        if ("a".equals(parentItem.getItem().getTagName())) {
            String url = parentItem.getItem().getAttribute("title");
            if (!menu.contains(url)) {
                menu.add(url);
            }
        }
        if (parentItem.hasChilds()) {
            for (int i = 0; i < parentItem.size(); i++) {
                getLanguageSelectorTitle(menu, parentItem.getChildItem(i));
            }
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
        checkLanguageSelect(driver);
        main.enableAll();
    }
}
