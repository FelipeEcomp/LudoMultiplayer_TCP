/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ludo;


/**
 *
 * @author Felipe
 */
public class Jogo {
   private String[] Posicoes = new String[55];
   private String[] Base = new String[16];
    
    public  String getPosicao(int Posicao){
        return Posicoes[Posicao];
        
    }
    
    public void setPosicao(String Pino, int Posicao){
        this.Posicoes[Posicao] = Pino;
        
    }
    
   public  String getBase(int Posicao){
        return Base[Posicao];
        
    }
    
   
    
    
}