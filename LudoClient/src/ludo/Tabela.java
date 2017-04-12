/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ludo;

import View.Sala;
import View.SalaGuest;
import View.SalaHost;
import View.ServidorIP;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Felipe
 */
public class Tabela implements Runnable {

    private static String[][] Tabela = new String[1][2];
    public static  int Posicao=0;
    private String IPServidor = ServidorIP.getIPServer();
    private String ultimaAcao = "nada ainda";
    private ArrayList<ArrayList<String[]>> TabelaSalas;
    private int posicao;
    private static int tamanho;
    private Sala sala=null;
    private SalaGuest salaGuest=null;
    private SalaHost salaHost=null;

    

    public String getAcao() {
        return ultimaAcao;

    }

    public void setAcao(String acao) {
        this.ultimaAcao = acao;
    }

    public static int getTamanhoTabela() {
        return tamanho;

    }

    public void recebe() throws SocketException, IOException, ClassNotFoundException {
        //Ouvinte na porta 3000
        DatagramSocket LserverSocket = new DatagramSocket(6000);

        //cria o datagrama pra receber a msg 
        DatagramPacket ListaPacket = new DatagramPacket(new byte[10000], 10000);


        LserverSocket.receive(ListaPacket);
        ByteArrayInputStream bis = new ByteArrayInputStream(ListaPacket.getData());
        ObjectInput in = new ObjectInputStream(bis);


        ArrayList<ArrayList<String[]>> Salas = (ArrayList) in.readObject();
        TabelaSalas = Salas;
        


        System.out.print(ultimaAcao);
        if ("nada ainda".equals(ultimaAcao)) {
            System.out.println("verifica o q chegou1" +TabelaSalas.get(0).get(0)[0]);  
            int quantidade = TabelaSalas.size();

            Tabela = new String[quantidade][2];
            for (int i = 0; i < TabelaSalas.size(); i++) {
                Tabela[i] = TabelaSalas.get(i).get(0);
            }
            System.out.println("quantidadeeeeeeeeeeee: " + quantidade);
             if(sala!=null){
                sala.dispose();
                
            }
            
            
            Sala janela = new Sala(quantidade, this);
            sala=janela;
            janela.setVisible(true);


        }
        if ("criar".equals(ultimaAcao)) {
            
            for (int i = 0; i < TabelaSalas.size(); i++) {
                if (InetAddress.getLocalHost().getHostAddress().equals(TabelaSalas.get(i).get(0)[1])) {
                    Tabela = new String[TabelaSalas.get(i).size()][2];
                    posicao = i;

                }
            }
            Posicao=1;
            ArrayList<String[]> salaSelecionada = TabelaSalas.get(posicao);
            
            for (int i = 0; i < salaSelecionada.size(); i++) {
                Tabela[i] = salaSelecionada.get(i);
            }

            tamanho = salaSelecionada.size();
            if(salaHost!=null){
                salaHost.dispose();
                
            }
            SalaHost janela = new SalaHost(salaSelecionada.size());
            salaHost=janela;
            janela.setVisible(true);
        }
        
        if ("entrar".equals(ultimaAcao)) {
            System.out.println("verifica o q chegou2" +TabelaSalas.get(0).get(0)[0]);  
            System.out.println("Posicao aqui " + posicao);
            ArrayList<String[]> salaSelecionada = TabelaSalas.get(posicao);
          
            for (int i = 0; i < salaSelecionada.size(); i++) {
                
                Tabela = new String[salaSelecionada.size()][2];
            }

            for (int i = 0; i < salaSelecionada.size(); i++) {
                Tabela[i] = salaSelecionada.get(i);
                if(Tabela[i][1] == null ? InetAddress.getLocalHost().getHostAddress() == null : Tabela[i][1].equals(InetAddress.getLocalHost().getHostAddress())){
                    Posicao=i;
                }
            }

            tamanho = salaSelecionada.size();
            if(salaGuest!=null){
                salaGuest.dispose();
                
            }
            
            
            SalaGuest janela = new SalaGuest(salaSelecionada.size());
            salaGuest=janela;
            janela.setVisible(true);
        }



        LserverSocket.close();
        bis.close();
        in.close();


        recebe();
    }

    @Override
    public void run() {
        try {
            try {
                recebe();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Tabela.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SocketException ex) {
            Logger.getLogger(Tabela.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Tabela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void envia(String Nick, String IP, String Acao, int posicao) throws IOException {

        //if(posicao!=null) this.posicao = Integer.parseInt(posicao);
        System.err.println("IP " + IP);

        this.posicao = posicao;
        System.err.println("posicao: "+posicao);
        Socket enviaSocket = new Socket(IPServidor, 7000);
        DataOutputStream out = new DataOutputStream(enviaSocket.getOutputStream());

        out.writeUTF(Nick);
        out.writeUTF(IP);
        out.writeUTF(Acao);
        out.writeInt(posicao);


        String texto = Nick + "&" + IP + "&" + Acao + "&" + posicao;
        System.out.print(texto);



    }

    public static String[][] getTabela() {

        return Tabela;
    }
}
