package View;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bianca
 */


import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import ludo.Ludo;


/**
 * IconGetter.java
 * Created by Stijn Strickx on Feb. 16 2008
 * Copyright 2008 Stijn Strickx, All rights reserved
 */
public class IconGetter {
    
      public Icon getIcon(String name){
             System.out.println("Name: "+name);
        return new ImageIcon(Ludo.class.getResource( name));
     
    }
}
