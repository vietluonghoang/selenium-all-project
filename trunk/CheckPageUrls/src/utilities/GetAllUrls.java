/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import elements.ElementsCheck;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JTextArea;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author Admin
 */
public class GetAllUrls {

    private ElementsCheck ec;
    private WebDriver driver;
    private JTextArea taResult;
    private JTextArea taError;

    public GetAllUrls(WebDriver driver, JTextArea taResult, JTextArea taError) {
        this.driver = driver;
        this.taResult = taResult;
        this.taError = taError;
        this.ec = new ElementsCheck();
    }

    public HashMap getAllUrlsFromPage() {
        HashMap allUrls = new HashMap();
        List<WebElement> allATags = driver.findElements(By.xpath("//a"));
        String url = "";
        String text = "";
        for (WebElement webElement : allATags) {
            int i = 0;
            url = webElement.getAttribute("href");
            if ("".equals(url)) {
                url = "no url";
            }
            text = webElement.getText();
            if ("".equals(text)) {
                String title = webElement.getAttribute("title");
                if ("".equals(title)) {
                    text = "no text";
                } else {
                    text = title;
                }
            }
            if (allUrls.containsKey(text)) {
                i++;
                text = text + i;
            }
            allUrls.put(text, url);
        }
        return allUrls;
    }

    public ArrayList<String> getAllLinksFromPage() {
        List<WebElement> allATags = driver.findElements(By.xpath("//a"));
        ArrayList<String> allUrls = new ArrayList<String>();

        for (WebElement webElement : allATags) {
            String url = webElement.getAttribute("href");
            if ("".equals(url)) {
                url = "no url";
            }
            if (!allUrls.contains(url)) {
                allUrls.add(url);
            }
        }
        return allUrls;
    }
}
