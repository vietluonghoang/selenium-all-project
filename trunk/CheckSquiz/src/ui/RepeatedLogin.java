/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ultilities.CompareText;
import ultilities.register;
import webdrivers.CreateWebDrivers;

/**
 *
 * @author Admin
 */
public class RepeatedLogin {

    public static void main(String[] args) {
        try {
//            CreateWebDrivers cd = new CreateWebDrivers();
//            WebDriver driver = cd.createChromeDriver();
//            register r = new register(driver);
//
//            int times = 10000;
//            String username="sgsvn";
//            
//            for (int i = 0; i < times; i++) {
//                r.registerNewUser(username+i);
//            }
//            CreateWebDrivers cd = new CreateWebDrivers();
//            WebDriver driver = cd.createChromeDriver();
//            
//            driver.get("http://aeria:Play2Win@test.aeriagames.com/account/login");
//            driver.findElement(By.id("edit-id")).sendKeys("sgs40");
//            driver.findElement(By.id("edit-pass")).sendKeys("Password1");
//            driver.findElement(By.id("account_login_submit")).click();
//            driver.get("http://aeria:Play2Win@test.aeriagames.com/user/23576/edit");
//            
//            CompareText ct=new CompareText(driver);
            
            String country = "viet nam";
            String mat ="viet nam";
            String source="v viet nam";
            
            
        } catch (Exception ex) {
            Logger.getLogger(RepeatedLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
