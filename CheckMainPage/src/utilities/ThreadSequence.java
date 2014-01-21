/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import elements.ElementsCheck;
import gui.CheckAeriaHomePage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author Admin
 */
public class ThreadSequence extends Thread {

    private CheckAeriaHomePage main;
    private ElementsCheck ec;
    private WebDriver driver;
    private JTextArea taResult;
    private JTextArea taError;
    private String territory = "English";

    public ThreadSequence() {
    }

    public ThreadSequence(CheckAeriaHomePage main, WebDriver driver, JTextArea taResult, JTextArea taError, String territory) {
        this.main = main;
        this.driver = driver;
        this.taResult = taResult;
        this.taError = taError;
        this.territory = territory;
    }

    @Override
    public void run() {
        try {
            CheckLanguageSelector cls = new CheckLanguageSelector(main, driver, taResult, taError, territory);
            cls.start();
            cls.join();
        } catch (InterruptedException ex) {
            taResult.append("\nError on checking language selector.");
            taError.append("\nERROR\n\tError on checking language selector.");
        }
        main.disableAll();
        try {
            CheckHeaderMenu chm = new CheckHeaderMenu(main, driver, taResult, taError, territory);
            chm.start();
            chm.join();
        } catch (InterruptedException ex) {
            taResult.append("\nError on checking header menu.");
            taError.append("\nERROR\n\tError on checking header menu.");
        }
        main.disableAll();
        try {
            CheckCarousel cc = new CheckCarousel(main, driver, taResult, taError, territory);
            cc.start();
            cc.join();
        } catch (InterruptedException ex) {
            taResult.append("\nError on checking Carousel.");
            taError.append("\nERROR\n\tError on checking Carousel.");
        }
        main.disableAll();
        try {
            CheckMainContent cmc = new CheckMainContent(main, driver, taResult, taError, territory);
            cmc.start();
            cmc.join();
        } catch (InterruptedException ex) {
            taResult.append("\nError on checking main content.");
            taError.append("\nERROR\n\tError on checking main content.");
        }
        main.disableAll();
        try {
            CheckFooter cf = new CheckFooter(main, driver, taResult, taError, territory);
            cf.start();
            cf.join();
        } catch (InterruptedException ex) {
            taResult.append("\nError on checking footer menu.");
            taError.append("\nERROR\n\tError on checking footer menu.");
        }
    }
}
