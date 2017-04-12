/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ludo;

/**
 *
 * @author Felipe
 */
public class Pino {
    
   private String cor;
   private int[][] posicao = new int[56][2];
   private String[] Base = new String[16];
  
   
       
    public void setBase(String Peao,int Posicao){
        this.Base[Posicao] = Peao;
                
    }
    
     public String getBase(int Posicao){
        return Base[Posicao];
                
    }
}
