/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ludo;

import View.Inicial;
import View.JanelaTabuleiro;
import View.ServidorIP;

/**
 *
 * @author Felipe
 */
public class Ludo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      ServidorIP serverIP = new ServidorIP();
      serverIP.setVisible(true);
            
    }
}
