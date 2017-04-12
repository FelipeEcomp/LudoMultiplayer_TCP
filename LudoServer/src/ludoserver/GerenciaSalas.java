/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ludoserver;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bianca
 */
public class GerenciaSalas implements Runnable {

    private static ArrayList<String[]> NomesEIPs;
    private static ArrayList<ArrayList> TabelaSalas;

    public GerenciaSalas() {
        String [] Sala1=new String[2];
        Sala1[0]= "Biaaa";
        Sala1[1]="192.168.220.14";
        ArrayList<String[]> Sala=new ArrayList<>();
        Sala.add(Sala1);
        TabelaSalas = new ArrayList<>();
        TabelaSalas.add(Sala);
        
       GerenciaSalas.NomesEIPs = GerenciaNick.getNomes();
        
        
    }

    public void atualizarSala() throws IOException {
        ServerSocket recebeSocket = new ServerSocket(7000);
           
       System.out.println("Jogada na area parceiro");
       
       //aguarda novas conexões
       Socket socket = recebeSocket.accept();

        //cria um canal para receber dados
       DataInputStream in = new DataInputStream(socket.getInputStream());
       
       System.out.println("posicaoAKIIIIIIIIII");
       //Aguarda o recebimento
       String Nick = in.readUTF();
       System.out.println("posicaoAKIIIIIIIIII2");
       String IP = in.readUTF();
       System.out.println("posicaoAKIIIIIIIIII3");
       String Acao = in.readUTF();
       System.out.println("posicaoAKIIIIIIIIII4");
       int posicao = in.readInt();
       
    System.out.println("posicaoAKIIIIIIIIII5");
       
       String[] NomeSala = new String[2];
       NomeSala[0] = Nick;
       NomeSala[1] = IP;
       
        if("entrar".equals(Acao)){
            
          
            System.out.println("numero: "+posicao);
            System.out.println("Alguem quer entrar na sala");
             
           
            if(TabelaSalas.get(posicao).size()<4){//como nosso querido tutor é sacana Se a sala selecionada já tiver 4 jogadores ele não vei entrar
           System.out.println("Alguem quer entrar na sala" + TabelaSalas.get(posicao).size());
                TabelaSalas.get(posicao).add(NomeSala);
               System.out.println("Alguem quer entrar na sala2" + TabelaSalas.get(posicao).size());  
            }
        }
        if ("criar".equals(Acao)) {
            System.out.println("posicaoAKIIIIIIIIII6");
            ArrayList<String[]> Sala = new ArrayList<>();
            Sala.add(NomeSala);
            TabelaSalas.add(Sala);
        } 
        if("Se for isso eu me mato".equals(Acao)){
            //se naum tiver o aviso pra entrar vai ter o numero da sala pra sair
            
            TabelaSalas.remove(posicao);
        }

      in.close();
      socket.close();
      recebeSocket.close();
       
        
        repassarAtualizacao();
               
        atualizarSala();
              
        
        
        //Fecha Socket
        

    }

    public void repassarAtualizacao() throws IOException {
        
        
        for (int i = 0; i < NomesEIPs.size(); i++) {//mandar a ista de IPs pra todo mundo
            //transformando a lista de usuarios online em array de bytes
            System.out.println("vou verificar");
            System.out.println("verifica o q mandou" + TabelaSalas.get(0).get(0));  
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(bos);
            out.writeObject(TabelaSalas);
            byte[] ListaByte = bos.toByteArray();


            DatagramSocket tabelSocket = new DatagramSocket();


            String IP1 = (String) NomesEIPs.get(i)[1];
            InetAddress IPAddress = InetAddress.getByName(IP1); //endereço

            //Envia datagrama para destinatario na porta 3004
            System.err.println("TAMANHO" + ListaByte.length);
            DatagramPacket sendPacket = new DatagramPacket(ListaByte, ListaByte.length, IPAddress, 6000);

            //Envia o datagrama
            tabelSocket.send(sendPacket);
          
            tabelSocket.close();

            out.close();
            bos.close();
     
        }
      
        
        
     
        
    }

    @Override
    public void run() {
        try {
            atualizarSala();
        } catch (IOException ex) {
            Logger.getLogger(GerenciaSalas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
