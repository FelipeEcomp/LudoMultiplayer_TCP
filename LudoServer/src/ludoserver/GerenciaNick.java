/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ludoserver;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bianca
 */
public class GerenciaNick implements Runnable{
    
    private static ArrayList <String[]> NomesEIPs;
    
    
    public GerenciaNick(){
    
        NomesEIPs=new ArrayList<>();
        
    }
    
    public static ArrayList<String[]> getNomes(){
    
        return NomesEIPs;
    }
    
    public void receberNick() throws IOException{
   
        System.out.println("receberNick");
        DatagramSocket msgRecebidaSocket = new DatagramSocket(5000);
            DatagramPacket answer = new DatagramPacket(new byte[1024], 1024);
            
            //Recebe Datagrama
            msgRecebidaSocket.receive(answer);
         
            //Lendo a mensagem
            String NickEIP = new String (answer.getData(), "UTF-8"); 
            
            System.out.println("receberNick");
      
           // a posição 1 tem o nick, a 2 tem o IP e a 3 avisa se é pra colocar ou retirar o nome do user na tabela 
           String[] tudoSeparado = new String[3];
           tudoSeparado = NickEIP.split("&");
           
           System.out.println(tudoSeparado[0]);
           System.out.println(tudoSeparado[1]);
           System.out.println(tudoSeparado[2]);
           
           String[] NomeEIP = new String[2];
           NomeEIP[0]=tudoSeparado[0];
           NomeEIP[1]=tudoSeparado[1];
           
           if("tira".equals(tudoSeparado[2])){
             int i= NomesEIPs.size();
             String[] key = new String[2];
             for (int j=0; j<i;j++){ 
                 
                 key = NomesEIPs.get(i);
                 if(key[1] == null ? NomeEIP[1] == null : key[1].equals(NomeEIP[1])){
                     NomesEIPs.remove(i);
                 }
             }
           }
           else{ 
           NomesEIPs.add(tudoSeparado);
           }
        
         GerenciaSalas gerencia= new GerenciaSalas();
                 
                 gerencia.repassarAtualizacao();
         msgRecebidaSocket.close();
         
       receberNick();
    
    }

    @Override
    public void run() {
        try {
            receberNick();
        } catch (IOException ex) {
            Logger.getLogger(GerenciaNick.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}