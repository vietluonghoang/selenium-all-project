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
public class GetUsernamesByIP {

    private CheckAeriaHomePage main;
    private ElementsCheck ec;
    private WebDriver driver;
    private JTextArea taResult;
    private JTextArea taError;

    public GetUsernamesByIP(CheckAeriaHomePage main, WebDriver driver, JTextArea taResult, JTextArea taError) {
        this.main = main;
        this.driver = driver;
        this.taResult = taResult;
        this.taError = taError;
        this.ec = new ElementsCheck();
    }

    public void getUsernames() {
        ArrayList<String> usernames = new ArrayList<String>();
        for (int page = 0; page < 5; page++) {
            driver.get("http://admin.test.aeriagames.com/admin/search/account?page=" + page + "&keyword=118.70.182.33");
            List<WebElement> table = driver.findElements(By.xpath("//div[@id='admin_search_user_result']/table/tbody/tr/td"));
            for (WebElement webElement : table) {
//            List<WebElement> data=webElement.findElements(By.xpath("/td"));
//            for (WebElement webElement1 : data) {
                usernames.add(webElement.getText());
//            }
            }
        }
        int count = 0;
        int base = 9;
        int i = 1;
        for (String string : usernames) {
            if (i == (count * base) + 2) {
                System.out.println(string + ",");
            }
            if (i % 9 == 0) {
                count++;
            }
            i++;
        }

    }
}
