/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package questionfourb;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nayem
 */
public class md5 {
    public static String getMd5(String input){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            
            // digest() method is called to calculate message digest
            // of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());
            
            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);
            
            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            } 
            return hashtext;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(md5.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
}
