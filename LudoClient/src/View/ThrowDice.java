package View;



import java.util.Random;
import java.util.TimerTask;
import javax.swing.Icon;
import javax.swing.JLabel;

/**
 * ThrowDice.java
 * Created by Stijn Strickx on Feb. 16 2008
 * Copyright 2008 Stijn Strickx, All rights reserved
 */
class ThrowDice extends TimerTask {
    private JLabel dice1;
   
    private JLabel text;
    private Random rg = new Random();
    private IconGetter getter;
    private int count;

    public ThrowDice(JLabel dice1, JLabel text) {
        this.dice1 = dice1;
      
        this.text = text;
        count = 25;
        getter = new IconGetter();
    }
    @Override
    public void run(){
        if(count > 0){
            count --;
            int num1 = rg.nextInt(6);
         
           Icon icon1 = getter.getIcon("stone"+ (num1+1) + ".gif");
        
            dice1.setIcon(icon1);
      
            text.setText("Total: " + (num1+1));
        }
        else{
            this.cancel();
        }
    }

}

