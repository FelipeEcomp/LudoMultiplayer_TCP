package View;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bianca
 */


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import javax.swing.JLabel;

/**
 * ButtonListener.java
 * Created by Stijn Strickx on Feb. 16 2008
 * Copyright 2008 Stijn Strickx, All rights reserved
 */
class ButtonListener implements ActionListener {
    
    private JLabel dice1;

    private JLabel text;


    public ButtonListener(JLabel dice1, JLabel text) {
        this.dice1 = dice1;
      
        this.text = text;
    }

   
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new ThrowDice(dice1, text), 0, 100);
        
    }
}
