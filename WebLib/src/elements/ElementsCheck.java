/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import entities.MenuItem;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import net.HttpRequestResponseManipulator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 *
 * @author Admin
 */
public class ElementsCheck {

    public ElementsCheck() {
        //System.out.println("Creating...");
    }

    public boolean isAlertPresent(WebDriver driver) throws Exception {
        try {
            driver.switchTo().alert();
            return true;
        } catch (Exception Ex) {
            return false;
        }
    }

    public boolean isElementVisible(WebDriver driver, WebElement element) throws Exception {
        for (int second = 0;; second++) {
            if (second >= 5) {
                System.out.println("timeout");
                return false;
            }
            if (element.isDisplayed()) {
                break;
            }
            Thread.sleep(1000);
        }
        return true;
    }

    public boolean isElementPresent(WebDriver driver, String id) throws Exception {
        for (int second = 0;; second++) {
            if (second >= 5) {
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

    public boolean isElementPresent(WebDriver driver, String param, String type) throws Exception {
        for (int second = 0;; second++) {
            if (second >= 5) {
                System.out.println("timeout");
                return false;
            }
            if ("class".equals(type)) {
                if (driver.findElements(By.className(param)).size() > 0) {
                    break;
                }
            }
            if ("tagname".equals(type)) {
                if (driver.findElements(By.tagName(param)).size() > 0) {
                    break;
                }
            }
            if ("xpath".equals(type)) {
                if (driver.findElements(By.xpath(param)).size() > 0) {
                    break;
                }
            }
            Thread.sleep(1000);
        }
        return true;
    }

    public boolean isPropertyPresent(WebElement element, String property) throws Exception {
        for (int second = 0;; second++) {
            if (second >= 5) {
                System.out.println("timeout");
                return false;
            }
            if (!"".equals(element.getAttribute(property))) {
                break;
            }
            Thread.sleep(1000);
        }
        return true;
    }

    public boolean isImageLoaded(String src) {
        for (int second = 0;; second++) {
            if (second >= 15) {
                System.out.println("timeout");
                return false;
            }
            try {
                BufferedImage img = ImageIO.read(new URL(src));
                break;
            } catch (IOException e) {
                return false;
            }
        }
        return true;
    }

    public String getURLFromStyle(String styleStr) {
        styleStr = styleStr.replaceAll("\"", "").trim();
        String pattern = "(?<=url\\()(.*)(?=\\))";
        String patternCheck = "^(http|https)";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(styleStr);
        String result = "";
        while (m.find()) {
            Pattern pc = Pattern.compile(patternCheck);
            Matcher mc = pc.matcher(m.group());
            if (mc.find()) {
                result = m.group();
            } else {
                result = "http:" + m.group();
            }
        }

        return result;
    }

    public boolean isVisible(String styleStr) {
        styleStr = styleStr.replaceAll("\"", "").trim();
        String pattern = "(visibility: visible)";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(styleStr);
        boolean result = false;
        if (m.find()) {
            return result = true;
        }

        return result;
    }

    public int getHttpResponseCode(String url) throws Exception {
        int code = 0;
        String patternCheck = "^(http|https)";
        Pattern pc = Pattern.compile(patternCheck);
        Matcher mc = pc.matcher(url);
        if (!mc.find()) {
            url = "http:" + url;
        }
        HttpURLConnection con = (new HttpRequestResponseManipulator()).passAuthenticationPopup(url);
        if (con != null) {
            con.connect();
            code = con.getResponseCode();
        } else {
            return 0;
        }
        return code;
    }

    public boolean isValidLink(WebElement element) {
        String url = element.getAttribute("href");
        String patternCheck = "^(http://|https://)";
        Pattern pc = Pattern.compile(patternCheck);
        Matcher mc = pc.matcher(url);
        if (mc.find()) {
            return true;
        }
        return false;
    }

    public boolean isValidLink(String url) {
        String patternCheck = "^(http://|https://)";
        Pattern pc = Pattern.compile(patternCheck);
        Matcher mc = pc.matcher(url);
        if (mc.find()) {
            return true;
        }
        return false;
    }

    public boolean isLinkOpenCorrect(WebDriver driver, WebElement webElement) throws Exception {
        String url = webElement.getAttribute("href");
        String mainWindow = driver.getWindowHandle();
        String targetUrl = "";
        Actions act = new Actions(driver);
        act.keyDown(Keys.SHIFT).click(webElement).perform();
        act.keyUp(Keys.SHIFT).perform();
        Set handledWindows = driver.getWindowHandles();
        Iterator it = handledWindows.iterator();
        while (it.hasNext()) {
            String targetWindow = it.next().toString();
            if (!mainWindow.equals(targetWindow)) {
                driver.switchTo().window(targetWindow);
                typeCredentials(driver, targetWindow);
                if (isAlertPresent(driver)) {
                    driver.switchTo().alert().dismiss();
                }
                targetUrl = driver.getCurrentUrl();
                if (url.equals(targetUrl)) {
                    driver.close();
                    driver.switchTo().window(mainWindow);
                    return true;
                } else {
                    driver.close();
                    driver.switchTo().window(mainWindow);
                    return false;
                }
            }
        }
        return false;
    }

    public void setMenuIChildElements(MenuItem parentMenu, WebDriver driver) throws Exception {
        WebElement parentElement = parentMenu.getItem();
        List<WebElement> childNodes = parentElement.findElements(By.xpath("*"));
        for (WebElement webElement : childNodes) {
            MenuItem child = new MenuItem(webElement);
            parentMenu.addChildItem(child);
            setMenuIChildElements(child, driver);
        }
    }

    public void getMenuLinkItems(MenuItem menu, MenuItem parentItem) throws Exception {
        if ("a".equals(parentItem.getItem().getTagName())) {
            MenuItem newItem = new MenuItem(parentItem.getItem());
            menu.addChildItem(newItem);
        }
        if (parentItem.hasChilds()) {
            for (int i = 0; i < parentItem.size(); i++) {
                getMenuLinkItems(menu, parentItem.getChildItem(i));
            }
        }
    }

    public void getMenuLinkUrls(ArrayList<String> menu, MenuItem parentItem) throws Exception {
        if ("a".equals(parentItem.getItem().getTagName())) {
            String url = parentItem.getItem().getAttribute("href");
            if (!menu.contains(url)) {
                menu.add(url);
            }
        }
        if (parentItem.hasChilds()) {
            for (int i = 0; i < parentItem.size(); i++) {
                getMenuLinkUrls(menu, parentItem.getChildItem(i));
            }
        }
    }

    public List<WebElement> getAllLinks(WebDriver driver, WebElement root) throws Exception {
        String id = root.getAttribute("id");
        String classAttr = root.getAttribute("class");
        String title = root.getAttribute("title");
        String xpath="//" + root.getTagName();
        List<WebElement> allLinks = null;
        
        if(!"".equals(id)){
            xpath=xpath + "[@id='" + id + "']//a";
        }else{
            if(!"".equals(title)&&!"".equals(classAttr)){
                xpath=xpath+"[@title='" + title + "' and @class='" + classAttr + "']//a";
            }
            if(!"".equals(title)&&"".equals(classAttr)){
                xpath=xpath+"[@title='" + title + "']//a";
            }
            if("".equals(title)&&!"".equals(classAttr)){
                xpath=xpath+"[@class='" + classAttr + "']//a";
            }
        }
//        if (!"".equals(id)) {
//            allLinks = driver.findElements(By.xpath("//" + root.getTagName() + "[@id='" + id + "']//a"));
//            return allLinks;
//        } else if (!"".equals(title)) {
//            allLinks = driver.findElements(By.xpath("//" + root.getTagName() + "[@title='" + title + "']//a"));
//            return allLinks;
//        } else if (!"".equals(classAttr)) {
//            allLinks = driver.findElements(By.xpath("//" + root.getTagName() + "[@class='" + classAttr + "']//a"));
//            return allLinks;
//        }
        allLinks = driver.findElements(By.xpath(xpath));
        return allLinks;
    }

    public List<WebElement> getAllImages(WebDriver driver, WebElement root) throws Exception {
        String id = root.getAttribute("id");
        String classAttr = root.getAttribute("class");
        String title = root.getAttribute("title");
        String xpath="//" + root.getTagName();
        List<WebElement> allImages = null;

        if(!"".equals(id)){
            xpath=xpath + "[@id='" + id + "']//img";
        }else{
//            if(!"".equals(title)&&!"".equals(classAttr)){
//                xpath=xpath+"[@title='" + title + "' and @class='" + classAttr + "']//img";
//            }
//            if(!"".equals(title)&&"".equals(classAttr)){
//                xpath=xpath+"[@title='" + title + "']//img";
//            }
//            if("".equals(title)&&!"".equals(classAttr)){
//                xpath=xpath+"[@class='" + classAttr + "']//img";
//            }
            xpath=xpath+"[@title='" + title + "' and @class='" + classAttr + "']//img";
        }
//        if (!"".equals(id)) {
//            allImages = driver.findElements(By.xpath("//" + root.getTagName() + "[@id='" + id + "']//img"));
//            return allImages;
//        } else if (!"".equals(title)) {
//            allImages = driver.findElements(By.xpath("//" + root.getTagName() + "[@title='" + title + "']//img"));
//            return allImages;
//        } else if (!"".equals(classAttr)) {
//            allImages = driver.findElements(By.xpath("//" + root.getTagName() + "[@class='" + classAttr + "']//img"));
//            return allImages;
//        }
        allImages = driver.findElements(By.xpath(xpath));
        return allImages;
    }

    public void typeCredentials(WebDriver driver, String window) throws AWTException {
        driver.switchTo().window(window);
        Robot ro = new Robot();
        int[] username = {KeyEvent.VK_A, KeyEvent.VK_E, KeyEvent.VK_R, KeyEvent.VK_I, KeyEvent.VK_A};
        for (int c : username) {
            ro.keyPress(c);
            ro.keyRelease(c);
        }
        ro.keyPress(KeyEvent.VK_TAB);
        ro.keyRelease(KeyEvent.VK_TAB);
        int[] password = {KeyEvent.VK_P, KeyEvent.VK_L, KeyEvent.VK_A, KeyEvent.VK_Y, KeyEvent.VK_2, KeyEvent.VK_W, KeyEvent.VK_I, KeyEvent.VK_N};
        for (int c : password) {
            if (c == KeyEvent.VK_P || c == KeyEvent.VK_W) {
                ro.keyPress(KeyEvent.VK_SHIFT);
                ro.keyPress(c);
                ro.keyRelease(c);
                ro.keyRelease(KeyEvent.VK_SHIFT);
            } else {
                ro.keyPress(c);
                ro.keyRelease(c);
            }

        }
        ro.keyPress(KeyEvent.VK_ENTER);
        ro.keyRelease(KeyEvent.VK_ENTER);
    }

    public void selectTerritory(WebDriver driver, String territory) throws Exception {
        if (isElementPresent(driver, "language-select")) {
            Actions act = new Actions(driver);
            act.moveToElement(driver.findElement(By.id("language-select"))).build().perform();
            Thread.sleep(100);
            if (isElementVisible(driver, driver.findElement(By.xpath("//ul[@id='language-select']//a[@title='" + territory + "']")))) {
                driver.findElement(By.xpath("//ul[@id='language-select']//a[@title='" + territory + "']")).click();
            } else {
                act.moveToElement(driver.findElement(By.id("language-select"))).build().perform();
                Thread.sleep(100);
                driver.findElement(By.xpath("//ul[@id='language-select']//a[@title='" + territory + "']")).click();
            }
        }
    }

    public boolean isTerritorySelected(WebDriver driver, String territory) throws Exception {
        if (isElementPresent(driver, "lang-select-container")) {
            String classAttr = driver.findElement(By.xpath("//ul[@id='language-select']//a[@title='" + territory + "']")).getAttribute("class");
            if ("lang-select-linkselected".equals(classAttr)) {
                return true;
            }
        }
        return false;
    }
}
