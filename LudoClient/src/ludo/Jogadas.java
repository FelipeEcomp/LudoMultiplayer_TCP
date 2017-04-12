/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ludo;

import View.JanelaTabuleiro;
import View.SalaHost;
import java.awt.Component;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Felipe
 */
public class Jogadas implements Runnable{
    private String IP;
    JanelaTabuleiro janela;
    
    
    
  public void enviaJogada(String Cor, int Posicao, int proximo) throws IOException{
      System.out.println("Cor: "+Cor);
      System.out.println("Posicao: "+Posicao);
      System.out.println("Proximo: "+proximo);
      
      System.err.println("IP "+IP);
        //Ouvinte na porta 4000 (jogada)
      
      for(int i=0; i<Tabela.getTamanhoTabela();i++){
      
         IP=Tabela.getTabela()[i][1];
           Socket enviaSocket = new Socket(IP, 4000);
        
     
        //cria um canal para enviar dados
       DataOutputStream out = new DataOutputStream(enviaSocket.getOutputStream());
       
      InetAddress meuIP = enviaSocket.getInetAddress();
       
   
       
       //envia a string
       out.writeUTF(Cor);
       out.writeInt(Posicao);
       out.writeInt(proximo);
      
      }
      // out.close();
  //     enviaSocket.close();
    }
      
 
  
  
  public void recebeJogada() throws IOException{
        //Ouvinte na porta 4000 (tabela)
       ServerSocket recebeSocket = new ServerSocket(4000);
           
       System.out.println("Jogada na area parceiro");
       
       //aguarda novas conexões
       Socket socket = recebeSocket.accept();

        //cria um canal para receber dados
       DataInputStream in = new DataInputStream(socket.getInputStream());
       
       //Aguarda o recebimento
       String Cor = in.readUTF();
       int Posicao = in.readInt();
       int proximo = in.readInt();
               
       System.out.println("recebeCor: "+Cor);
       System.out.println("recebePosicao: "+Posicao);
       System.out.println("recebeProximo: "+proximo);      
       
      if(janela==null){
               
        JFrame frame = new JFrame("Ludo Multiplayer");
        
        frame.add(new JanelaTabuleiro());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
         this.setJanela(frame.add(new JanelaTabuleiro()));
      }
       
       
      janela.setPosicao(Cor, Posicao, proximo);
      
    
      in.close();
      socket.close();
      recebeSocket.close();
      recebeJogada();
  }

    @Override
    public void run() {
        try {
            recebeJogada();
        } catch (IOException ex) {
            Logger.getLogger(Jogadas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setJanela(Component janela) {
       this.janela = (JanelaTabuleiro) janela;
    }
  
  
  
      
      
      
  }
    
    
    
    
    
    
