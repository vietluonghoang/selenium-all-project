/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JFileChooser;
import org.openqa.selenium.WebDriver;
import utilities.GetAllUrls;
import webdrivers.CreateWebDrivers;

/**
 *
 * @author Admin
 */
public class CheckPageUrlsMain extends javax.swing.JFrame {

    /**
     * Creates new form CheckPageUrlsMain
     */
    public CheckPageUrlsMain() {
        initComponents();
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
        btnGetAllUrls = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        taError = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        taResult = new javax.swing.JTextArea();
        lblError = new javax.swing.JLabel();
        lblResult = new javax.swing.JLabel();
        lblUrl = new javax.swing.JLabel();
        txtUrl = new javax.swing.JTextField();
        btnGetAllUrlswoText = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnStart.setText("Start");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        btnGetAllUrls.setText("Get All Urls");
        btnGetAllUrls.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetAllUrlsActionPerformed(evt);
            }
        });

        taError.setColumns(20);
        taError.setRows(5);
        jScrollPane1.setViewportView(taError);

        taResult.setColumns(20);
        taResult.setRows(5);
        jScrollPane2.setViewportView(taResult);

        lblError.setText("Error");

        lblResult.setText("Result");

        lblUrl.setText("URL:");

        txtUrl.setText("http://test.aeriagames.com");

        btnGetAllUrlswoText.setText("Get All Urls w/o Text");
        btnGetAllUrlswoText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetAllUrlswoTextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(btnGetAllUrls)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGetAllUrlswoText))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnStart)
                        .addGap(78, 78, 78)
                        .addComponent(lblUrl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUrl, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblError)
                            .addComponent(lblResult))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStart)
                    .addComponent(lblUrl)
                    .addComponent(txtUrl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGetAllUrls)
                    .addComponent(btnGetAllUrlswoText))
                .addGap(23, 23, 23)
                .addComponent(lblError)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblResult)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        try {
            // TODO add your handling code here:
            btnStart.setEnabled(false);
                CreateWebDrivers cw = new CreateWebDrivers();
                String src = "";
                while (src == "") {
                    fileChooser = new JFileChooser();
                    fileChooser.showOpenDialog(this);
                    src = fileChooser.getSelectedFile().getAbsolutePath();
                }
                driver = cw.createChromeDriver(src);
        } catch (Exception ex) {
            taError.append("\nERROR:\n"+ex.getMessage());
        }
    }//GEN-LAST:event_btnStartActionPerformed

    private void btnGetAllUrlsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetAllUrlsActionPerformed
        // TODO add your handling code here:
        String url=txtUrl.getText();
        taResult.setText("");
        if(!"".equals(url)){
            url=url.replaceFirst("http://", "");
            driver.get("http://" + authorizedUsername + ":" + authorizedPassword + "@"+url);
            GetAllUrls gau=new GetAllUrls(driver, taResult, taError);
            allUrls=gau.getAllUrlsFromPage();
            for (Iterator it = allUrls.entrySet().iterator(); it.hasNext();) {
                Map.Entry entry = (Map.Entry) it.next();
                String text=(String) entry.getKey();
                String link=(String) entry.getValue();
                taResult.append("\n - "+text+" - "+link);
            }
        }else{
            taError.append("\nERROR:\nPlease enter url!");
        }
    }//GEN-LAST:event_btnGetAllUrlsActionPerformed

    private void btnGetAllUrlswoTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetAllUrlswoTextActionPerformed
        // TODO add your handling code here:
        String url=txtUrl.getText();
        taResult.setText("");
        if(!"".equals(url)){
            url=url.replaceFirst("http://", "");
            driver.get("http://" + authorizedUsername + ":" + authorizedPassword + "@"+url);
            GetAllUrls gau=new GetAllUrls(driver, taResult, taError);
            ArrayList<String> allLinks=gau.getAllLinksFromPage();
            for (String string : allLinks) {
                taResult.append(string+"\n");
            }
        }else{
            taError.append("\nERROR:\nPlease enter url!");
        }
    }//GEN-LAST:event_btnGetAllUrlswoTextActionPerformed

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
            java.util.logging.Logger.getLogger(CheckPageUrlsMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CheckPageUrlsMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CheckPageUrlsMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CheckPageUrlsMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CheckPageUrlsMain().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGetAllUrls;
    private javax.swing.JButton btnGetAllUrlswoText;
    private javax.swing.JButton btnStart;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblError;
    private javax.swing.JLabel lblResult;
    private javax.swing.JLabel lblUrl;
    private javax.swing.JTextArea taError;
    private javax.swing.JTextArea taResult;
    private javax.swing.JTextField txtUrl;
    // End of variables declaration//GEN-END:variables
    private JFileChooser fileChooser;
    private WebDriver driver;
    private HashMap allUrls;
    private String authorizedUsername = "aeria";
    private String authorizedPassword = "Play2Win";
}
