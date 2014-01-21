/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.text.DefaultCaret;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.CheckCarousel;
import utilities.CheckFooter;
import utilities.CheckHeaderMenu;
import utilities.CheckLanguageSelector;
import utilities.CheckMainContent;
import utilities.GetUsernamesByIP;
import utilities.ThreadQueue;
import utilities.ThreadSequence;
import webdrivers.CreateWebDrivers;

/**
 *
 * @author Admin
 */
public class CheckAeriaHomePage extends javax.swing.JFrame {

    /**
     * Creates new form CheckAeriaHomePage
     */
    public CheckAeriaHomePage() {
        initComponents();
        DefaultCaret caret = (DefaultCaret) taResult.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        DefaultCaret caret1 = (DefaultCaret) taError.getCaret();
        caret1.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    }

    public void enableAll() {
        ready = true;
        btnCheckAll.setEnabled(true);
        btnCheckCarousel.setEnabled(true);
        btnCheckHeader.setEnabled(true);
        btnCheckMainContent.setEnabled(true);
        btnCheckFooter.setEnabled(true);
        btnCheckLanguageSelector.setEnabled(true);
        cbLanguage.setEnabled(true);
    }

    public void disableAll() {
        ready = false;
        btnCheckAll.setEnabled(false);
        btnCheckCarousel.setEnabled(false);
        btnCheckHeader.setEnabled(false);
        btnCheckMainContent.setEnabled(false);
        btnCheckFooter.setEnabled(false);
        btnCheckLanguageSelector.setEnabled(false);
        cbLanguage.setEnabled(false);
    }

    private void setLanguageComboBox(ArrayList<String> allTerritories) {
        DefaultComboBoxModel model = (DefaultComboBoxModel) this.cbLanguage.getModel();
        model.removeAllElements();
        for (String territory : allTerritories) {
            model.addElement(territory);
        }
        this.cbLanguage.setModel(model);
    }

    private String getTerritory() {
        return cbLanguage.getSelectedItem().toString();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnStart = new javax.swing.JButton();
        cbLanguage = new javax.swing.JComboBox();
        btnCheckAll = new javax.swing.JButton();
        btnCheckHeader = new javax.swing.JButton();
        btnCheckLanguageSelector = new javax.swing.JButton();
        btnCheckCarousel = new javax.swing.JButton();
        btnCheckMainContent = new javax.swing.JButton();
        btnCheckFooter = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        taResult = new javax.swing.JTextArea();
        lblOutput = new javax.swing.JLabel();
        lblError = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taError = new javax.swing.JTextArea();
        lblTerritory = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Check Aeria Home Page");
        setAlwaysOnTop(true);

        btnStart.setText("Start");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        cbLanguage.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "English", " " }));
        cbLanguage.setEnabled(false);

        btnCheckAll.setText("Check All");
        btnCheckAll.setEnabled(false);
        btnCheckAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckAllActionPerformed(evt);
            }
        });

        btnCheckHeader.setText("Check Header");
        btnCheckHeader.setEnabled(false);
        btnCheckHeader.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckHeaderActionPerformed(evt);
            }
        });

        btnCheckLanguageSelector.setText("Check Language Selector");
        btnCheckLanguageSelector.setEnabled(false);
        btnCheckLanguageSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckLanguageSelectorActionPerformed(evt);
            }
        });

        btnCheckCarousel.setText("Check Carousel");
        btnCheckCarousel.setEnabled(false);
        btnCheckCarousel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckCarouselActionPerformed(evt);
            }
        });

        btnCheckMainContent.setText("Check Main Content");
        btnCheckMainContent.setEnabled(false);
        btnCheckMainContent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckMainContentActionPerformed(evt);
            }
        });

        btnCheckFooter.setText("Check Footer");
        btnCheckFooter.setEnabled(false);
        btnCheckFooter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckFooterActionPerformed(evt);
            }
        });

        taResult.setEditable(false);
        taResult.setColumns(20);
        taResult.setRows(5);
        jScrollPane1.setViewportView(taResult);

        lblOutput.setText("Output:");

        lblError.setText("Error:");

        taError.setEditable(false);
        taError.setColumns(20);
        taError.setRows(5);
        jScrollPane2.setViewportView(taError);

        lblTerritory.setText("Territory:");

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblOutput))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCheckLanguageSelector)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCheckHeader))
                            .addComponent(btnCheckAll, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCheckCarousel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCheckMainContent)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCheckFooter))
                            .addComponent(jButton1))))
                .addContainerGap(83, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblError)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(btnStart)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblTerritory)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStart)
                    .addComponent(cbLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTerritory))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCheckAll)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCheckLanguageSelector)
                    .addComponent(btnCheckCarousel)
                    .addComponent(btnCheckMainContent)
                    .addComponent(btnCheckFooter)
                    .addComponent(btnCheckHeader))
                .addGap(23, 23, 23)
                .addComponent(lblOutput)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(lblError)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        // TODO add your handling code here:
        try {
            btnStart.setEnabled(false);
            CreateWebDrivers cw = new CreateWebDrivers();
            String src = "";
            while (src == "") {
                fileChooser = new JFileChooser();
                fileChooser.showOpenDialog(this);
                src = fileChooser.getSelectedFile().getAbsolutePath();
            }            
            driver = cw.createChromeDriver(src);
            driver.get("http://" + authorizedUsername + ":" + authorizedPassword + "@test.aeriagames.com");
            btnStart.setEnabled(false);
            enableAll();
            List<WebElement> allTerritoryItems = driver.findElements(By.xpath("//span[@id='lang-select-container']//a"));
            ArrayList<String> allTerritories = new ArrayList<String>();
            for (WebElement territory : allTerritoryItems) {
                allTerritories.add(territory.getAttribute("title"));
            }
            setLanguageComboBox(allTerritories);
        } catch (Exception ex) {
            taResult.append("\nERROR\nError on creating webdriver.\n" + ex.getMessage());
            taError.append("\nERROR\nError on creating webdriver.\n" + ex.getMessage());
        }
    }//GEN-LAST:event_btnStartActionPerformed

    private void btnCheckLanguageSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckLanguageSelectorActionPerformed
        // TODO add your handling code here:
        disableAll();
        CheckLanguageSelector cls = new CheckLanguageSelector(this, driver, taResult, taError, getTerritory());
        cls.start();
    }//GEN-LAST:event_btnCheckLanguageSelectorActionPerformed

    private void btnCheckHeaderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckHeaderActionPerformed
        // TODO add your handling code here:
        disableAll();
        CheckHeaderMenu chm = new CheckHeaderMenu(this, driver, taResult, taError, getTerritory());
        chm.start();
    }//GEN-LAST:event_btnCheckHeaderActionPerformed

    private void btnCheckCarouselActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckCarouselActionPerformed
        // TODO add your handling code here:
        disableAll();
        CheckCarousel cc = new CheckCarousel(this, driver, taResult, taError, getTerritory());
        cc.start();
    }//GEN-LAST:event_btnCheckCarouselActionPerformed

    private void btnCheckMainContentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckMainContentActionPerformed
        // TODO add your handling code here:
        disableAll();
        CheckMainContent cmc = new CheckMainContent(this, driver, taResult, taError, getTerritory());
        cmc.start();
    }//GEN-LAST:event_btnCheckMainContentActionPerformed

    private void btnCheckFooterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckFooterActionPerformed
        // TODO add your handling code here:
        disableAll();
        CheckFooter cf = new CheckFooter(this, driver, taResult, taError, getTerritory());
        cf.start();
    }//GEN-LAST:event_btnCheckFooterActionPerformed

    private void btnCheckAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckAllActionPerformed
        // TODO add your handling code here:
        disableAll();
        ThreadSequence ts = new ThreadSequence(this, driver, taResult, taError, territory);
        ts.start();
    }//GEN-LAST:event_btnCheckAllActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        GetUsernamesByIP gubi=new GetUsernamesByIP(this, driver, taResult, taError);
        gubi.getUsernames();
    }//GEN-LAST:event_jButton1ActionPerformed
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;


                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CheckAeriaHomePage.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CheckAeriaHomePage.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CheckAeriaHomePage.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CheckAeriaHomePage.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CheckAeriaHomePage().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCheckAll;
    private javax.swing.JButton btnCheckCarousel;
    private javax.swing.JButton btnCheckFooter;
    private javax.swing.JButton btnCheckHeader;
    private javax.swing.JButton btnCheckLanguageSelector;
    private javax.swing.JButton btnCheckMainContent;
    private javax.swing.JButton btnStart;
    private javax.swing.JComboBox cbLanguage;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblError;
    private javax.swing.JLabel lblOutput;
    private javax.swing.JLabel lblTerritory;
    private javax.swing.JTextArea taError;
    private javax.swing.JTextArea taResult;
    // End of variables declaration//GEN-END:variables
    private WebDriver driver;
    private String authorizedUsername = "aeria";
    private String authorizedPassword = "Play2Win";
    private String territory = "English";
    private boolean ready = false;
    private JFileChooser fileChooser;
}
