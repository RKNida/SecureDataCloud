/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package securedatatocloud1;

import java.awt.Dimension;

/**
 *
 * @author welcome
 */
public class Securedatatocloud1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Dimension d=java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        Login lf=new Login();
        lf.setVisible(true);
        lf.setSize(d);
    }
    
}
