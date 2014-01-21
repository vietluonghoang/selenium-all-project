/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import elements.ElementsCheck;
import gui.CheckAeriaHomePage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author Admin
 */
public class CheckCarousel extends Thread {

    private CheckAeriaHomePage main;
    private ElementsCheck ec;
    private WebDriver driver;
    private JTextArea taResult;
    private JTextArea taError;
    private String territory = "English";

    public void checkImagesVisibility(WebDriver driver) {

        taResult.append("\n\nChecking Carousel Images load:");
        ArrayList<String> imgLinks = new ArrayList<String>();
        List<WebElement> elements = driver.findElements(By.xpath("//div[@id='carousel-content']/div"));
        for (WebElement webElement : elements) {
            String url = ec.getURLFromStyle(webElement.findElement(By.xpath("//div[@class='slide-wrapper']")).getAttribute("style"));
            imgLinks.add(url);
        }
        elements = driver.findElements(By.xpath("//div[@id='carousel-nav']/a"));
        for (WebElement webElement : elements) {
            String url = ec.getURLFromStyle(webElement.getAttribute("style"));
            imgLinks.add(url);
        }

        for (String str : imgLinks) {
            boolean isLoaded = ec.isImageLoaded(str);
            if (!isLoaded) {
                taResult.append("\n* " + str + " is failed to load.");
                taError.append("\n\nChecking Carousel Images load:\n* " + str + " is failed to load.");
            } else {
                taResult.append("\n* " + str + " is loaded successfully.");
            }
        }
    }

    public void checkVideoVisibility(WebDriver driver) {
        taResult.append("\n\nChecking Carousel Video load:");
        ArrayList<String> imgLinks = new ArrayList<String>();
        List<WebElement> elements = driver.findElements(By.xpath("//video/source"));
        for (WebElement webElement : elements) {
            String url = webElement.getAttribute("src");
            imgLinks.add(url);
        }
        for (String str : imgLinks) {
            try {
                if (ec.getHttpResponseCode(str) != 200) {
                    taResult.append("\n* " + str + " is failed to load.");
                    taError.append("\n\nChecking Carousel Video load:\n* " + str + " is failed to load.");
                } else {
                    taResult.append("\n* " + str + " is loaded successfully.");
                }
            } catch (Exception ex) {
                taResult.append("\nERROR\nError on checking video.\n" + ex.getMessage());
                taError.append("\nERROR\nError on checking video.\n" + ex.getMessage());
            }
        }
    }

    public void checkCarouselLink(List<WebElement> elements) {

        taResult.append("\n\nCheck link response:");
        for (WebElement webElement : elements) {
            String url = webElement.getAttribute("href");
            taResult.append("\n* " + webElement.getText() + " - " + url + ": ");
            try {
                int result = ec.getHttpResponseCode(url);
                if (result == 200) {
                    taResult.append(Integer.toString(result));
                } else {
                    taResult.append("Failed!");
                    taError.append("\n\nCheck link response:\n* " + webElement.getText() + " - " + url + ": Failed!");
                }
            } catch (Exception ex) {
                taResult.append("Failed!\n ERROR\n\t" + ex.getMessage());
                taError.append("\n\nError on check carousel link response:\nERROR\n\t"+ webElement.getText() + "\n\t" + ex.getMessage());
            }
        }
    }

    public void checkCarouselLinkClick(WebElement element, WebDriver driver) {
        try {
            if (ec.isVisible(element.getAttribute("style"))) {
                String xPath = "//div[@id='" + element.getAttribute("id") + "']//a";
                List<WebElement> links = element.findElements(By.xpath(xPath));
                String mainWindow = driver.getWindowHandle();
                String targetUrl = "";
                for (WebElement webElement : links) {
                    taResult.append("\nCheck link click for: " + element.getAttribute("id"));
                    String url = webElement.getAttribute("href");
                    taResult.append("\n - " + url);
                    if (ec.isLinkOpenCorrect(driver, webElement)) {
                        taResult.append(": passed!");
                    } else {
                        taResult.append(": failed!\n   Response code: " + ec.getHttpResponseCode(url));
                        taError.append("\nCheck link click for: " + element.getAttribute("id")
                                + ": failed!\n   Response code: " + ec.getHttpResponseCode(url));
                    }
                }
            }
        } catch (Exception ex) {
            taResult.append("\nERROR\n\t" + ex.getMessage());
            taError.append("\nError on checking carousel link click\nERROR\n\t" + ex.getMessage());
        }

    }

    public void checkCarouselNavClick(WebDriver driver, List<WebElement> navButtons, List<WebElement> navThumbnails, List<WebElement> slides) {
        try {
            if (navThumbnails.size() > 4 || slides.size() > 4 || navThumbnails.size() != slides.size()) {
                taResult.append("\nERROR\nIncorrect number of thumbnails or slides.");
                taError.append("\nERROR\nIncorrect number of thumbnails or slides.");
            } else {
                boolean isAllElementAvailable = true;
                for (WebElement webElement : slides) {
                    try {
                        if (!ec.isPropertyPresent(webElement, "style")) {
                            isAllElementAvailable = false;
                            break;
                        }
                    } catch (Exception ex) {
                        isAllElementAvailable = false;
                        taResult.append("\nFailed to check property if presented.");
                        taError.append("\nFailed to check property if presented.");
                    }
                }
                for (WebElement webElement : navButtons) {
                    try {
                        if (!ec.isPropertyPresent(webElement, "style")) {
                            isAllElementAvailable = false;
                            break;
                        }
                    } catch (Exception ex) {
                        isAllElementAvailable = false;
                        taResult.append("\nFailed to check property if presented.");
                        taError.append("\nFailed to check property if presented.");
                    }
                }
                for (WebElement webElement : navThumbnails) {
                    try {
                        if (!ec.isPropertyPresent(webElement, "style")) {
                            isAllElementAvailable = false;
                            break;
                        }
                    } catch (Exception ex) {
                        isAllElementAvailable = false;
                        taResult.append("\nFailed to check property if presented.");
                        taError.append("\nFailed to check property if presented.");
                    }
                }
                if (isAllElementAvailable) {
                    taResult.append("\n\nCheck by clicking on the direction button.");
                    for (int i = 0; i < 4; i++) {
                        taResult.append("\n click on right button #" + i + ": ");
                        if (isCarouselNavChangedByDirectionBtn(slides, navThumbnails, navButtons, true)) {
                            taResult.append("Passed");
                        } else {
                            taResult.append("Failed");
                            taError.append("\n Failed to click on right button #" + i);
                        }
                    }
                    for (int i = 0; i < 4; i++) {
                        taResult.append("\n click on left button #" + i + ": ");
                        if (isCarouselNavChangedByDirectionBtn(slides, navThumbnails, navButtons, false)) {
                            taResult.append("Passed");
                        } else {
                            taResult.append("Failed");
                            taError.append("\n Failed to click on right button #" + i);
                        }
                    }
                    taResult.append("\n\nCheck by clicking on thumbnails.");
                    for (int i = 0; i < 4; i++) {
                        taResult.append("\n click on thumbnail #" + i + ": ");
                        if (isCarouselNavChangedByThumbnail(navThumbnails.get(i), slides, i)) {
                            taResult.append("Passed");
                        } else {
                            taResult.append("Failed");
                            taError.append("\n Failed to click on thumbnail #" + i);
                        }
                    }
                } else {
                    taResult.append("\n\nFailed to check by clicking on the direction button because some elements are not visible.");
                    taError.append("\n\nFailed to check by clicking on the direction button because some elements are not visible.");
                }
            }
        } catch (Exception ex) {
            taResult.append("\nERROR\nFailed to check navigator click.");
            taError.append("\nERROR\nFailed to check navigator click.");
        }
    }

    public boolean isCarouselNavChangedByThumbnail(WebElement navThumbnail, List<WebElement> slides, int position) {
        navThumbnail.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            taResult.append("\nERROR\n\t" + ex.getMessage());
            taError.append("\nError on checking carousel change by clicking on thumbnail\nERROR\n\t" + ex.getMessage());
        }
        if ("inactiveNavBtn".equals(navThumbnail.getAttribute("class"))
                && ec.isVisible(slides.get(position).getAttribute("style"))) {
            return true;
        }
        return false;
    }

    public boolean isCarouselNavChangedByDirectionBtn(List<WebElement> slides, List<WebElement> navThumbnails, List<WebElement> navButtons, boolean direction) {
        int pos = 0;
        try {
            for (WebElement webElement : navThumbnails) {
                if ("inactiveNavBtn".equals(webElement.getAttribute("class"))) {
                    break;
                }
                pos++;
            }
            if (direction) {
                navButtons.get(1).click();
                try {
                    Thread.sleep(1000);


                } catch (InterruptedException ex) {
                    taResult.append("\nERROR\n\t" + ex.getMessage());
                    taError.append("\nError on checking carousel change by clicking on direction button\nERROR\n\t" + ex.getMessage());
                }
                if (pos == 3) {
                    pos = -1;
                }
                if ("inactiveNavBtn".equals(navThumbnails.get(pos + 1).getAttribute("class")) && ec.isVisible(slides.get(pos + 1).getAttribute("style"))) {
                    return true;
                }
            } else {
                navButtons.get(0).click();
                try {
                    Thread.sleep(1000);


                } catch (InterruptedException ex) {
                    taResult.append("\nERROR\n\t" + ex.getMessage());
                    taError.append("\nError on checking carousel change by clicking on direction button\nERROR\n\t" + ex.getMessage());
                }
                if (pos == 0) {
                    pos = 4;
                }
                if ("inactiveNavBtn".equals(navThumbnails.get(pos - 1).getAttribute("class")) && ec.isVisible(slides.get(pos - 1).getAttribute("style"))) {
                    return true;
                }
            }
        } catch (Exception ex) {
            taResult.append("\nERROR:\n" + ex.getMessage());
            taError.append("\nError on checking carousel change by clicking on direction button\nERROR\n\t" + ex.getMessage());
        }
        return false;
    }

    public List<WebElement> getAllCarouselLinks(WebDriver driver) {
        List<WebElement> elements = driver.findElements(By.xpath("//div[@id='carousel-content']//a"));
        return elements;
    }

    public void checkCarousel(WebDriver driver) {
        try {
            List<WebElement> navThumbnails = driver.findElements(By.xpath("//div[@id='carousel-nav']//a"));
            for (int i = 3; i >= 0; i--) {
                navThumbnails.get(i).click();
                Thread.sleep(500);
            }
            List<WebElement> navButtons = driver.findElements(By.xpath("//div[@id='carousel-control-wrap']/a"));
            List<WebElement> slides = driver.findElements(By.xpath("//div[@id='carousel-content']/div"));

            checkImagesVisibility(driver);
            checkVideoVisibility(driver);
            for (int i = 0; i < 4; i++) {
                navThumbnails.get(i).click();
                //            checkCarouselLinkClick(slides.get(i), driver, taResult);  
                checkCarouselLink(driver.findElements(By.xpath("//div[@id='" + slides.get(i).getAttribute("id") + "']//a")));
            }
            checkCarouselNavClick(driver, navButtons, navThumbnails, slides);
        } catch (Exception ex) {
            taResult.append("\nERROR\n\t" + ex.getMessage());
            taError.append("\nError on checking carousel\nERROR\n\t" + ex.getMessage());
        }
    }

    public CheckCarousel() {
        ec = new ElementsCheck();
    }

    public CheckCarousel(ElementsCheck ec) {
        this.ec = ec;
    }

    public CheckCarousel(WebDriver driver, JTextArea taResult, String territory) {
        this.driver = driver;
        this.taResult = taResult;
        ec = new ElementsCheck();
        this.territory = territory;
    }

    public CheckCarousel(WebDriver driver, JTextArea taResult, JTextArea taError, String territory) {
        this.driver = driver;
        this.taResult = taResult;
        this.taError = taError;
        ec = new ElementsCheck();
        this.territory = territory;
    }

    public CheckCarousel(CheckAeriaHomePage main, WebDriver driver, JTextArea taResult, JTextArea taError, String territory) {
        this.main = main;
        this.driver = driver;
        this.taResult = taResult;
        this.taError = taError;
        ec = new ElementsCheck();
        this.territory = territory;
    }

    public CheckCarousel(ElementsCheck ec, WebDriver driver, JTextArea taResult) {
        this.ec = ec;
        this.driver = driver;
        this.taResult = taResult;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
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
        checkCarousel(this.driver);
        main.enableAll();
    }
}
