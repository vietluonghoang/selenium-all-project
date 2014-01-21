/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net;

import java.io.IOException;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class HttpRequestResponseManipulator {

    public HttpURLConnection passAuthenticationPopup(String targetUrl) {
        try {
            URL url = new URL(targetUrl);
            Authenticator.setDefault(new ProxyAuthenticator("aeria", "Play2Win"));
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("username", "aeria");
            con.setRequestProperty("password", "Play2Win");            
            return con;
        } catch (MalformedURLException ex) {
            Logger.getLogger(HttpRequestResponseManipulator.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(HttpRequestResponseManipulator.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
