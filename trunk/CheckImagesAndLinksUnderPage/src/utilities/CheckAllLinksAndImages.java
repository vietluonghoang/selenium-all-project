/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import elements.ElementsCheck;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
/**
 *
 * @author Admin
 */
public class CheckAllLinksAndImages extends Thread{
    private ElementsCheck ec;
    private WebDriver driver;
    private JTextArea taResult;
    private JTextArea taError;
    private List<WebElement> allLinks;
    private List<WebElement> allImages;

    public CheckAllLinksAndImages(WebDriver driver, JTextArea taResult, JTextArea taError) {
        this.driver = driver;
        this.taResult = taResult;
        this.taError = taError;
        ec=new ElementsCheck();
    }
    
    private void getAllLinksAndImages(){
        try {
            allLinks=ec.getAllLinks(driver, driver.findElement(By.id("main")));
            allImages=ec.getAllImages(driver, driver.findElement(By.id("main")));
        } catch (Exception ex) {
            taResult.append("\nERROR\nError on getting all links.\n" + ex.getMessage());
            taError.append("\nERROR\nError on getting all links.\n" + ex.getMessage());
        }
    }
}
