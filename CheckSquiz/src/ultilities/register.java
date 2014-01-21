/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ultilities;

import elements.ElementsCheck;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author Admin
 */
public class register {

    private ElementsCheck ec;
    private WebDriver driver;

    public register(WebDriver driver) {
        this.driver = driver;
        this.ec = new ElementsCheck();
    }

    public void registerNewUser(String username) {
        try {
            //String target = "http://test.viettamduc.com/quiz/registerlogin.php";
            String target="file:///C:/Users/Admin/Desktop/quiz.htm";
            driver.get(target);
            if (ec.isElementPresent(driver, "//input[@name='username']", "xpath")) {
                driver.findElement(By.xpath("//input[@name='username']")).sendKeys(username);
                driver.findElement(By.xpath("//input[@name='password']")).sendKeys("123456");
                driver.findElement(By.xpath("//input[@name='fullname']")).sendKeys("sgstest");
                driver.findElement(By.xpath("//input[@name='address']")).sendKeys("sgs vietnam");
                driver.findElement(By.xpath("//input[@name='phone']")).sendKeys("1234567890");
                driver.findElement(By.xpath("//input[@name='email']")).sendKeys("sgs@vietnam.com");
                driver.findElement(By.xpath("//input[@value='Ðăng Ký']")).click();
            }
        } catch (Exception ex) {
            Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
