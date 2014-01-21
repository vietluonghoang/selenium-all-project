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
public class CheckMainContent extends Thread {

    private CheckAeriaHomePage main;
    private ElementsCheck ec;
    private WebDriver driver;
    private JTextArea taResult;
    private JTextArea taError;
    private String territory = "English";

    public CheckMainContent(WebDriver driver, JTextArea taResult, String territory) {
        this.driver = driver;
        this.taResult = taResult;
        this.ec = new ElementsCheck();
        this.territory = territory;
    }

    public CheckMainContent(WebDriver driver, JTextArea taResult, JTextArea taError, String territory) {
        this.driver = driver;
        this.taResult = taResult;
        this.taError = taError;
        this.ec = new ElementsCheck();
        this.territory = territory;
    }

    public CheckMainContent(CheckAeriaHomePage main, WebDriver driver, JTextArea taResult, JTextArea taError, String territory) {
        this.main=main;
        this.driver = driver;
        this.taResult = taResult;
        this.taError = taError;
        this.ec = new ElementsCheck();
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

    private void CheckGameCategories() {
        List<WebElement> root = null;
        root = driver.findElements(By.id("feature-games"));
        for (WebElement webElement : root) {
            if (!"".equals(webElement.getAttribute("title"))) {
                CheckGameCategory(webElement);
            }
        }
        taResult.append("\n\nCheck Game Categories:\n");
        root = driver.findElements(By.xpath("//div[@id='category-games']//div"));
        for (WebElement webElement : root) {
            if (!"".equals(webElement.getAttribute("title"))) {
                CheckGameCategory(webElement);
            }
        }
    }

    private void CheckGameCategory(WebElement root) {
        taResult.append("\n\nCheck " + root.getAttribute("title") + ":\n");
        try {
//            WebElement root = driver.findElement(By.xpath("//div[@id='category-games']//div[@title='PC Games']"));
            List<WebElement> allLinks = ec.getAllLinks(driver, root);
            for (WebElement webElement : allLinks) {
                String title = webElement.getAttribute("title");
                String text = webElement.getText();
                if (!"".equals(title)) {
                    taResult.append("\n* " + title + ": ");
                } else {
                    taResult.append("\n* " + text + ": ");
                }
                int code = 0;
                try {
                    code = ec.getHttpResponseCode(webElement.getAttribute("href"));
                } catch (Exception ex) {
                    taResult.append("\nERROR\n" + ex.getMessage() + "\n");
                    taError.append("\n\nCheck " + root.getAttribute("title") + ":\n\nERROR\n" + ex.getMessage() + "\n");
                }
                if (code == 200) {
                    taResult.append("Passed.");
                } else {
                    taResult.append("Failed.\n\tResponse code: " + code);
                    taError.append("\n\nCheck " + root.getAttribute("title") + ":\n");
                    if (!"".equals(title)) {
                        taError.append("\n* " + title + ": Failed.\n\tResponse code: " + code);
                    } else {
                        taError.append("\n* " + text + ": Failed.\n\tResponse code: " + code);
                    }
                }
                List<WebElement> allImages = ec.getAllImages(driver, webElement);
                for (WebElement webElement1 : allImages) {
                    taResult.append("\n* " + webElement1.getAttribute("alt") + " - " + webElement1.getAttribute("src") + ": ");
                    int code1 = 0;
                    try {
                        code1 = ec.getHttpResponseCode(webElement1.getAttribute("src"));
                    } catch (Exception ex) {
                        taResult.append("\nERROR\n" + ex.getMessage() + "\n");
                        taError.append("\n\nCheck " + root.getAttribute("title") + "\n* " + webElement1.getAttribute("alt") + " - " + webElement1.getAttribute("src") + ":\n\nERROR\n" + ex.getMessage() + "\n");
                    }
                    if (code1 == 200) {
                        taResult.append("Passed.");
                    } else {
                        taResult.append("Failed.\n\tResponse code: " + code);
                        taError.append("\n\nCheck " + root.getAttribute("title") + "\n* " + webElement1.getAttribute("alt") + " - " + webElement1.getAttribute("src") + ": Failed.\n\tResponse code: " + code);
                    }
                }
            }
        } catch (Exception ex) {
            taResult.append("\nERROR\n" + ex.getMessage());
            taError.append("\n\nCheck " + root.getAttribute("title") + "\nERROR\n" + ex.getMessage());
        }
    }

    private void checkPromoBanner() {
        taResult.append("\nCheck Promo Banner:\n");

        try {
            List<WebElement> root = driver.findElements(By.xpath("//div[@id='main-content']/div"));
            for (WebElement webElement : root) {
                String classAttr = webElement.getAttribute("class");
                if ("promo-block promo-dark".equals(classAttr)
                        || "promo-block promo-light".equals(classAttr)
                        || "promo-img promo-first-img promo-light".equals(classAttr)
                        || "promo-img promo-first-img promo-dark".equals(classAttr)
                        || "promo-img promo-dark".equals(classAttr)
                        || "promo-img promo-light".equals(classAttr)) {

                    String imageURL = ec.getURLFromStyle(webElement.getAttribute("style"));

                    List<WebElement> allLinks = ec.getAllLinks(driver, webElement);
                    for (WebElement webElement2 : allLinks) {
                        String url = webElement2.getAttribute("href");
                        String text = webElement2.getText();
                        taResult.append("\n* " + text + " - " + url + ": ");
                        int code = ec.getHttpResponseCode(url);
                        if (code == 200) {
                            taResult.append("Passed.");
                        } else {
                            taResult.append("Failed.\n\tResponse code: " + code);
                            taError.append("\nCheck Promo Banner:\n* " + text + " - " + url + ": Failed.\n\tResponse code: " + code);
                        }
                    }
                    taResult.append("\n\tImage - " + imageURL + ": ");
                    if (ec.isImageLoaded(imageURL)) {
                        taResult.append("Passed.");
                    } else {
                        taResult.append("Failed.");
                        taError.append("\nCheck Promo Banner:\n\tImage - " + imageURL + ": Failed.");
                    }
                } else {
//                    taResult.append("\nNo promo banner with button found!");
                }
            }
            List<WebElement> aTags = driver.findElements(By.xpath("//div[@id='main-content']/a"));
            for (WebElement webElement : aTags) {
                String aTag = webElement.getAttribute("class");
                if ("promo-block promo-dark".equals(aTag)
                        || "promo-block promo-dark".equals(aTag)) {

                    String url = webElement.getAttribute("href");
                    taResult.append("* Promo banner with a tag - " + url + ": ");
                    int code = ec.getHttpResponseCode(url);
                    if (code == 200) {
                        taResult.append("Passed.");
                    } else {
                        taResult.append("Failed.\n\tResponse code: " + code);
                        taError.append("\nCheck Promo Banner:\n* Promo banner with a tag - " + url + ": Failed.\n\tResponse code: " + code);
                    }
                    String imageURL = ec.getURLFromStyle(webElement.getAttribute("style"));
                    taResult.append("\n\tImage - " + imageURL + ": ");
                    if (ec.isImageLoaded(imageURL)) {
                        taResult.append("Passed.");
                    } else {
                        taResult.append("Failed.");
                        taError.append("\nCheck Promo Banner:\n* Promo banner with a tag - Image - " + imageURL + ":  Failed.\n\tResponse code: " + code);
                    }
                } else {
                    // taResult.append("\nNo promo banner with button found!");
                }
            }
        } catch (Exception ex) {
            taResult.append("\nERROR\n" + ex.getMessage());
            taError.append("\nCheck Promo Banner:\nERROR\n" + ex.getMessage());
        }

    }

    private void checkSEOBlock() {
        try {
            taResult.append("\n\nChecking SEO block:\n");
            if (ec.isElementPresent(driver, "full-seo") && ec.isElementPresent(driver, "seo-read-more")) {
                if (!ec.isElementVisible(driver, driver.findElement(By.id("full-seo")))) {
                    if (ec.isElementVisible(driver, driver.findElement(By.id("seo-read-more")))) {
                        if (ec.isElementPresent(driver, "//div[@id='seo-read-more']//a", "xpath")) {
                            taResult.append("\n- Check Read more button: ");
                            if (ec.isElementVisible(driver, driver.findElement(By.xpath("//div[@id='seo-read-more']//a")))) {
                                driver.findElement(By.xpath("//div[@id='seo-read-more']//a")).click();
                                Thread.sleep(100);
                            }
                            if (ec.isElementVisible(driver, driver.findElement(By.id("full-seo")))) {
                                taResult.append("Passed.");
                            } else {
                                taResult.append("Failed.");
                                taError.append("\n\nChecking SEO block:\n- Check Read more button: Failed.");
                            }
                        } else {
                            taResult.append("\n- SEO Block displays.");
                        }
                    }
                }
                if (ec.isElementVisible(driver, driver.findElement(By.id("full-seo")))) {
                    if (!ec.isElementVisible(driver, driver.findElement(By.id("seo-read-more")))) {
                        taResult.append("\n- Check Close button: ");
                        driver.findElement(By.xpath("//div[@id='full-seo']//a")).click();
                        Thread.sleep(100);
                    }
                    if (ec.isElementVisible(driver, driver.findElement(By.id("seo-read-more")))) {
                        taResult.append("Passed.");
                    } else {
                        taResult.append("Failed.");
                        taError.append("\n\nChecking SEO block:\n- Check Close button: Failed.");
                    }
                }
            } else {
                taResult.append("\n\tNo SEO Block found.");
            }
        } catch (Exception ex) {
            taResult.append("\n\nError\n\t" + ex.getMessage());
            taError.append("\n\nChecking SEO block:\nError\n\t" + ex.getMessage());
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
        CheckGameCategories();
        checkPromoBanner();
        checkSEOBlock();
        main.enableAll();
    }
}
