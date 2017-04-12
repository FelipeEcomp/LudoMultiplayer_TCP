/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ludo;

import View.ServidorIP;
import java.io.IOException;
import java.net.*;

/**
 *
 * @author Bianca
 */
public class Login {
    private String IPServidor=ServidorIP.getIPServer(); 
    
    
    public void envia(String Nick, String IP, String Acao) throws SocketException, UnknownHostException, IOException{
    
     System.err.println("IP "+IP);
        
       DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName(IPServidor); //endereço
         
     String texto= Nick+"&"+IP+"&"+Acao;
        byte[] buffer = texto.getBytes();
              

//Envia datagrama para destinatario na porta 3000
        DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, IPAddress, 5000);
                        
//Envia o datagrama
        clientSocket.send(sendPacket);
    
    }
    
    
    
    
}
