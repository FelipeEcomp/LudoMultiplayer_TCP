/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import ludo.*;

/**
 *
 * @author Felipe
 */
public class JanelaTabuleiro extends JPanel {

    

    Image tabuleiro;
    Image dice1;
    Image dice2;
    Image dice3;
    Image dice4;
    Image dice5;
    Image dice6;
    int nDado = 0;
    Dimension size;
    JLabel dice;
    
    int vezJogador = 1; //numero do jogador da vez
    Jogador njogador = new Jogador(); //contem o numero do jogador
    int QTPlayers=2; //quantidade de jogadores na partida TIRAR O 2
    
    Jogadas jogad = new Jogadas();
    
    private Image mapabr;
    static private int[][] mapeamentoTabuleiro = new int[56][2];
    static private int[][] mapeamentoPorCores = new int[24][2]; //mapeamento das casas internas de cada cor
    static private int[][] Base = new int[16][2]; //mapeamento da base
    
    
    static private Ellipse2D Azul1;
    static private Ellipse2D Azul2;
    static private Ellipse2D Azul3;
    static private Ellipse2D Azul4;
    static private Ellipse2D Vermelho1;
    static private Ellipse2D Vermelho2;
    static private Ellipse2D Vermelho3;
    static private Ellipse2D Vermelho4;
    static private Ellipse2D Verde1;
    static private Ellipse2D Verde2;
    static private Ellipse2D Verde3;
    static private Ellipse2D Verde4;
    static private Ellipse2D Amarelo1;
    static private Ellipse2D Amarelo2;
    static private Ellipse2D Amarelo3;
    static private Ellipse2D Amarelo4;
    
    
    static private Ellipse2D teste;
    static private IconGetter getter;
int a;
    
 
    Pino A1zul = new Pino();
    Pino A2zul = new Pino();
    Pino A3zul = new Pino();
    Pino A4zul = new Pino();
    Pino V1ermelho = new Pino();
    Pino V2ermelho = new Pino();
    Pino V3ermelho = new Pino();
    Pino V4ermelho = new Pino();
    Pino V1erde = new Pino();
    Pino V2erde = new Pino();
    Pino V3erde = new Pino();
    Pino V4erde = new Pino();
    Pino A1marelo = new Pino();
    Pino A2marelo = new Pino();
    Pino A3marelo = new Pino();
    Pino A4marelo = new Pino();
       
    
  //variaveis temporarias para movimentar o pino
    //AZUIS
 int temp1 = (nDado+27);
 int temp2 = (nDado+27);
 int temp3 = (nDado+27);
 int temp4 = (nDado+27);
 
 //VERMELHAS
 int temp5 = (nDado+41);
 int temp6 = (nDado+41);
 int temp7 = (nDado+41);
 int temp8 = (nDado+41);
 
 //VERDES
 int temp9 = (nDado);
 int temp10 = (nDado);
 int temp11 = (nDado);
 int temp12 = (nDado);
 
 //AMARELAS
 int temp13 = (nDado+13);
 int temp14 = (nDado+13);
 int temp15 = (nDado+13);
 int temp16 = (nDado+13);
 
 Jogo jog = new Jogo(); //regras do jogo
 
    public JanelaTabuleiro() { //A janela possui o tamanho da Imagem

        getter = new IconGetter();
        dice = new JLabel("stone1.gif");
        JButton button = new JButton("Jogue o dado");
        JLabel text = new JLabel("Total: 1");

        this.setLayout(new FlowLayout());
        this.add(dice);

        this.add(button);
        this.add(text);



        


        size = new Dimension();
        tabuleiro = new ImageIcon(this.getClass().getResource("tela.jpg")).getImage();
        dice1 = new ImageIcon(this.getClass().getResource("stone1.gif")).getImage();
        size.width = 1000;
        size.height = tabuleiro.getHeight(null);


        setPreferredSize(size);

// Cria ButtonHandler para tratamento de evento do botão

        ButtonHandler handler = new ButtonHandler();

        button.addActionListener(handler);


//button.addActionListener(new ButtonListener(dice1, text));

njogador.setNumero(Tabela.Posicao); //TIRAR ESSE TESTE

        this.addMouseListener( new MouseAdapter() {

        
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                
if(nDado>0) {  //checa se o jogador rolou o dado
         if(njogador.getNumero() == vezJogador){ //caso ele seja o jogador da vez
                
             if(vezJogador ==1){ //JOGADOR 1 = AZUL
                 
             if (Azul1.contains(x, y)) {
                 
                 if((Azul1.contains(Base[0][0], Base[0][1]) && nDado==6 || nDado==1) || (Azul1.contains(Base[0][0], Base[0][1])==false)){ //se o pino ta na base
                    System.out.println("clicou certo parceiro");
                     
                    vezJogador = 2;//proximo jogador
                    
                    temp1 = temp1+nDado;                    
                    if(temp1>55)  temp1 = temp1-56; //caso chegue no fim do vetor (casa verde)
                    
                   Azul1 = new Ellipse2D.Double(mapeamentoTabuleiro[temp1][0], mapeamentoTabuleiro[temp1][1], 20, 20);
                   
                   
                    if(jog.getPosicao(temp1)!=null)mandaPraBase("Azul",temp1); //verifica se há algum pino nessa posição                      
                   
                 
                    try {
                        jogad.enviaJogada("Azul1", temp1, vezJogador);
                    } catch (IOException ex) {
                        Logger.getLogger(JanelaTabuleiro.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    jog.setPosicao("Azul1",temp1);
                    repaint();
                     nDado=0; //zera o valor do dado
                 }
                }

                if (Azul2.contains(x, y)) {
                    System.out.println(Azul1.contains(701, 91));
                    if((Azul2.contains(Base[1][0], Base[1][1]) && nDado==6 || nDado==1) || (Azul2.contains(Base[1][0], Base[1][1])==false)){ //se o pino ta na base
                    System.out.println("clicou certo parceiro");
                    
                     temp2 = temp2+nDado;                    
                    if(temp2>55)  temp2 = temp2-56; //caso chegue no fim do vetor (casa verde)                        
                    Azul2 = new Ellipse2D.Double(mapeamentoTabuleiro[temp2][0], mapeamentoTabuleiro[temp2][1], 20, 20);
                    
                    if(jog.getPosicao(temp2)!=null)mandaPraBase("Azul", temp2); //verifica se há algum pino nessa posição                      
                    
                    try {
                        jogad.enviaJogada("Azul2", temp2, vezJogador);
                    } catch (IOException ex) {
                        Logger.getLogger(JanelaTabuleiro.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    jog.setPosicao("Azul2",temp2);
                    repaint();
                     nDado=0; //zera o valor do dado

                }}

                if (Azul3.contains(x, y)) {
                    if((Azul3.contains(Base[2][0], Base[2][1]) && nDado==6 || nDado==1) || (Azul3.contains(Base[2][0], Base[2][1])==false)){ //se o pino ta na base
                    System.out.println("clicou certo parceiro");

                    temp3 = temp3+nDado;                    
                    if(temp3>55)  temp3 = temp3-56; //caso chegue no fim do vetor (casa verde)
                        
                    Azul3 = new Ellipse2D.Double(mapeamentoTabuleiro[temp3][0], mapeamentoTabuleiro[temp3][1], 20, 20);
                    
                    if(jog.getPosicao(temp3)!=null)mandaPraBase("Azul",temp3); //verifica se há algum pino nessa posição                      
                   
                   try {
                        jogad.enviaJogada("Azul3", temp3, vezJogador);
                    } catch (IOException ex) {
                        Logger.getLogger(JanelaTabuleiro.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    jog.setPosicao("Azul3",temp3);
                    repaint();
                     nDado=0; //zera o valor do dado
                }}

                if (Azul4.contains(x, y)) {
                    if((Azul4.contains(Base[3][0], Base[3][1]) && nDado==6 || nDado==1) || (Azul4.contains(Base[3][0], Base[3][1])==false)){ //se o pino ta na base
                    System.out.println("clicou certo parceiro");
                    
                    temp4 = temp4+nDado;                    
                    if(temp4>55)  temp4 = temp4-56; //caso chegue no fim do vetor (casa verde)                        
                    Azul4 = new Ellipse2D.Double(mapeamentoTabuleiro[temp4][0], mapeamentoTabuleiro[temp4][1], 20, 20);
                    
                    if(jog.getPosicao(temp4)!=null)mandaPraBase("Azul",temp4); //verifica se há algum pino nessa posição                      
                    
                    try {
                        jogad.enviaJogada("Azul4", temp4, vezJogador);
                    } catch (IOException ex) {
                        Logger.getLogger(JanelaTabuleiro.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    jog.setPosicao("Azul4",temp4);
                    repaint();
                     nDado=0; //zera o valor do dado

                }}
                
             }//JOGADOR 1

             if(vezJogador ==2){ //JOGADOR 2 = VERMELHO
                if (Vermelho1.contains(x, y)) {
                    if((Vermelho1.contains(Base[4][0], Base[4][1]) && nDado==6 || nDado==1) || (Vermelho1.contains(Base[4][0], Base[4][1])==false)){ //se o pino ta na base
                    System.out.println("clicou certo parceiro");
                    
                    if(QTPlayers ==2)   vezJogador = 1;//proximo jogador
                    if(QTPlayers >2)   vezJogador = 3;//proximo jogador
                    
                    temp5 = temp5+nDado;                    
                    if(temp5>55)  temp5 = temp5-56; //caso chegue no fim do vetor (casa verde)                        
                    Vermelho1 = new Ellipse2D.Double(mapeamentoTabuleiro[temp5][0], mapeamentoTabuleiro[temp5][1], 20, 20);
                    
                    if(jog.getPosicao(temp5)!=null)mandaPraBase("Vermelho",temp5); //verifica se há algum pino nessa posição                      
                    
                    try {
                        jogad.enviaJogada("Vermelho1", temp5, vezJogador);
                    } catch (IOException ex) {
                        Logger.getLogger(JanelaTabuleiro.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    jog.setPosicao("Vermelho1",temp5);
                    repaint();
                     nDado=0; //zera o valor do dado

                }}


                if (Vermelho2.contains(x, y)) {
                    if((Vermelho2.contains(Base[5][0], Base[5][1]) && nDado==6 || nDado==1) || (Vermelho2.contains(Base[5][0], Base[5][1])==false)){ //se o pino ta na base
                    System.out.println("clicou certo parceiro");
                    
                    temp6 = temp6+nDado;                    
                    if(temp6>55)  temp6 = temp6-56; //caso chegue no fim do vetor (casa verde)                        
                    Vermelho2 = new Ellipse2D.Double(mapeamentoTabuleiro[temp6][0], mapeamentoTabuleiro[temp6][1], 20, 20);
                    
                    if(jog.getPosicao(temp6)!=null)mandaPraBase("Vermelho",temp6); //verifica se há algum pino nessa posição                      
                   
                    try {
                        jogad.enviaJogada("Vermelho2", temp6, vezJogador);
                    } catch (IOException ex) {
                        Logger.getLogger(JanelaTabuleiro.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    jog.setPosicao("Vermelho2",temp6);
                    repaint();
                     nDado=0; //zera o valor do dado

                }}

                if (Vermelho3.contains(x, y)) {
                    if((Vermelho3.contains(Base[6][0], Base[6][1]) && nDado==6 || nDado==1) || (Vermelho3.contains(Base[6][0], Base[6][1])==false)){ //se o pino ta na base
                    System.out.println("clicou certo parceiro");
                    
                    temp7 = temp7+nDado;                    
                    if(temp7>55)  temp7 = temp7-56; //caso chegue no fim do vetor (casa verde)                        
                    Vermelho3 = new Ellipse2D.Double(mapeamentoTabuleiro[temp7][0], mapeamentoTabuleiro[temp7][1], 20, 20);
                    
                    if(jog.getPosicao(temp7)!=null)mandaPraBase("Vermelho",temp7); //verifica se há algum pino nessa posição                      
                    
                    try {
                        jogad.enviaJogada("Vermelho3", temp7, vezJogador);
                    } catch (IOException ex) {
                        Logger.getLogger(JanelaTabuleiro.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    jog.setPosicao("Vermelho3",temp7);
                    repaint();
                     nDado=0; //zera o valor do dado

                }}

                if (Vermelho4.contains(x, y)) {
                    if((Vermelho4.contains(Base[7][0], Base[7][1]) && nDado==6 || nDado==1) || (Vermelho4.contains(Base[7][0], Base[7][1])==false)){ //se o pino ta na base
                    System.out.println("clicou certo parceiro");
                    
                    temp8 = temp8+nDado;                    
                    if(temp8>55)  temp8 = temp8-56; //caso chegue no fim do vetor (casa verde)                        
                    Vermelho4 = new Ellipse2D.Double(mapeamentoTabuleiro[temp8][0], mapeamentoTabuleiro[temp8][1], 20, 20);
                    
                    if(jog.getPosicao(temp8)!=null)mandaPraBase("Vermelho",temp8); //verifica se há algum pino nessa posição                      
                    
                    try {
                        jogad.enviaJogada("Vermelho4", temp8, vezJogador);
                    } catch (IOException ex) {
                        Logger.getLogger(JanelaTabuleiro.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    jog.setPosicao("Vermelho4",temp8);
                    repaint();
                     nDado=0; //zera o valor do dado

                }}
                vezJogador = 1;//TIRAR ISSO
             }//JOGADOR 2

             if(vezJogador ==3){ //JOGADOR 3 = VERDE
                if (Verde1.contains(x, y)) {
                    if((Verde1.contains(Base[8][0], Base[8][1]) && nDado==6 || nDado==1) || (Verde1.contains(Base[8][0], Base[8][1])==false)){ //se o pino ta na base
                    System.out.println("clicou certo parceiro");
                    
                    if(QTPlayers ==3)   vezJogador = 1;//proximo jogador
                    if(QTPlayers >3)   vezJogador = 4;//proximo jogador
                    
                    temp9 = temp9+nDado;                    
                    if(temp9>55)  temp9 = temp9-56; //caso chegue no fim do vetor (casa verde)                        
                    Verde1 = new Ellipse2D.Double(mapeamentoTabuleiro[temp9][0], mapeamentoTabuleiro[temp9][1], 20, 20);
                    
                    if(jog.getPosicao(temp9)!=null)mandaPraBase("Verde",temp9); //verifica se há algum pino nessa posição
                    
                    try {
                        jogad.enviaJogada("Verde1", temp9, vezJogador);
                    } catch (IOException ex) {
                        Logger.getLogger(JanelaTabuleiro.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    jog.setPosicao("Verde1",temp9);
                    repaint();
                     nDado=0; //zera o valor do dado


                }}

                if (Verde2.contains(x, y)) {
                    if((Verde2.contains(Base[9][0], Base[9][1]) && nDado==6 || nDado==1) || (Verde2.contains(Base[9][0], Base[9][1])==false)){ //se o pino ta na base
                    System.out.println("clicou certo parceiro");
                    
                    temp10 = temp10+nDado;                    
                    if(temp10>55)  temp10 = temp10-56; //caso chegue no fim do vetor (casa verde)                        
                    Verde2 = new Ellipse2D.Double(mapeamentoTabuleiro[temp10][0], mapeamentoTabuleiro[temp10][1], 20, 20);
                    
                    if(jog.getPosicao(temp10)!=null)mandaPraBase("Verde",temp10); //verifica se há algum pino nessa posição
                    
                    try {
                        jogad.enviaJogada("Verde2", temp10, vezJogador);
                    } catch (IOException ex) {
                        Logger.getLogger(JanelaTabuleiro.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    jog.setPosicao("Verde2",temp10);
                    repaint();
                     nDado=0; //zera o valor do dado

                }}
                    
                if (Verde3.contains(x, y)) {
                    if((Verde3.contains(Base[10][0], Base[10][1]) && nDado==6 || nDado==1) || (Verde3.contains(Base[10][0], Base[10][1])==false)){ //se o pino ta na base
                    System.out.println("clicou certo parceiro");
                    
                    temp11 = temp11+nDado;                    
                    if(temp11>55)  temp11 = temp11-56; //caso chegue no fim do vetor (casa verde)                        
                    Verde3 = new Ellipse2D.Double(mapeamentoTabuleiro[temp11][0], mapeamentoTabuleiro[temp11][1], 20, 20);
                    
                    if(jog.getPosicao(temp11)!=null)mandaPraBase("Verde",temp11); //verifica se há algum pino nessa posição
                    
                    try {
                        jogad.enviaJogada("Verde3", temp11, vezJogador);
                    } catch (IOException ex) {
                        Logger.getLogger(JanelaTabuleiro.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    jog.setPosicao("Verde3",temp11);
                    repaint();
                     nDado=0; //zera o valor do dado


                }}

                if (Verde4.contains(x, y)) {
                    if((Verde4.contains(Base[11][0], Base[11][1]) && nDado==6 || nDado==1) || (Verde4.contains(Base[11][0], Base[11][1])==false)){ //se o pino ta na base
                    System.out.println("clicou certo parceiro");
                    
                    temp12 = temp12+nDado;                    
                    if(temp12>55)  temp12 = temp12-56; //caso chegue no fim do vetor (casa verde)                        
                    Verde4 = new Ellipse2D.Double(mapeamentoTabuleiro[temp12][0], mapeamentoTabuleiro[temp12][1], 20, 20);
                    
                    if(jog.getPosicao(temp12)!=null)mandaPraBase("Verde",temp12); //verifica se há algum pino nessa posição
                    
                    try {
                        jogad.enviaJogada("Verde4", temp12, vezJogador);
                    } catch (IOException ex) {
                        Logger.getLogger(JanelaTabuleiro.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    jog.setPosicao("Verde4",temp12);
                    repaint();
                     nDado=0; //zera o valor do dado

                }}
                
             }//JOGADOR 3

             if(vezJogador ==4){ //JOGADOR 4 = AMARELO
                if (Amarelo1.contains(x, y)) {
                    if((Amarelo1.contains(Base[12][0], Base[12][1]) && nDado==6 || nDado==1) || (Amarelo1.contains(Base[12][0], Base[12][1])==false)){ //se o pino ta na base
                    System.out.println("clicou certo parceiro");
                    
                    temp13 = temp13+nDado;                    
                    if(temp13>55)  temp13 = temp13-56; //caso chegue no fim do vetor (casa verde)                        
                    Amarelo1 = new Ellipse2D.Double(mapeamentoTabuleiro[temp13][0], mapeamentoTabuleiro[temp13][1], 20, 20);
                    
                    if(jog.getPosicao(temp13)!=null)mandaPraBase("Amarelo",temp13); //verifica se há algum pino nessa posição
                   
                    
                    try {
                        jogad.enviaJogada("Amarelo1", temp13, 1);
                    } catch (IOException ex) {
                        Logger.getLogger(JanelaTabuleiro.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    jog.setPosicao("Amarelo1",temp13);
                    repaint();
                     nDado=0; //zera o valor do dado


                }}

                if (Amarelo2.contains(x, y)) {
                    if((Amarelo2.contains(Base[13][0], Base[13][1]) && nDado==6 || nDado==1) || (Amarelo2.contains(Base[13][0], Base[13][1])==false)){ //se o pino ta na base
                    System.out.println("clicou certo parceiro");
                    
                    temp14 = temp14+nDado;                    
                    if(temp14>55)  temp14 = temp14-56; //caso chegue no fim do vetor (casa verde)                        
                    Amarelo2 = new Ellipse2D.Double(mapeamentoTabuleiro[temp14][0], mapeamentoTabuleiro[temp14][1], 20, 20);
                    
                    if(jog.getPosicao(temp14)!=null)mandaPraBase("Amarelo",temp14); //verifica se há algum pino nessa posição
                    
                    try {
                        jogad.enviaJogada("Amarelo2", temp14, 1);
                    } catch (IOException ex) {
                        Logger.getLogger(JanelaTabuleiro.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    jog.setPosicao("Amarelo2",temp14);
                    repaint();      
                     nDado=0; //zera o valor do dado


                }}

                if (Amarelo3.contains(x, y)) {
                    if((Amarelo3.contains(Base[14][0], Base[14][1]) && nDado==6 || nDado==1) || (Amarelo3.contains(Base[14][0], Base[14][1])==false)){ //se o pino ta na base
                    System.out.println("clicou certo parceiro");
                    
                    temp15 = temp15+nDado;                    
                    if(temp15>55)  temp15 = temp15-56; //caso chegue no fim do vetor (casa verde)                        
                    Amarelo3 = new Ellipse2D.Double(mapeamentoTabuleiro[temp15][0], mapeamentoTabuleiro[temp15][1], 20, 20);
                    
                    if(jog.getPosicao(temp15)!=null)mandaPraBase("Amarelo",temp15); //verifica se há algum pino nessa posição
                    
                    try {
                        jogad.enviaJogada("Amarelo3", temp15, 1);
                    } catch (IOException ex) {
                        Logger.getLogger(JanelaTabuleiro.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    jog.setPosicao("Amarelo3",temp15);
                    repaint();
                     nDado=0; //zera o valor do dado


                }}

                if (Amarelo4.contains(x, y)) {
                    if((Amarelo4.contains(Base[15][0], Base[15][1]) && nDado==6 || nDado==1) || (Amarelo4.contains(Base[15][0], Base[15][1])==false)){ //se o pino ta na base
                    System.out.println("clicou certo parceiro");
                    
                    temp16 = temp16+nDado;                    
                    if(temp16>55)  temp16 = temp16-56; //caso chegue no fim do vetor (casa verde)                        
                    Amarelo4 = new Ellipse2D.Double(mapeamentoTabuleiro[temp16][0], mapeamentoTabuleiro[temp16][1], 20, 20);
                    
                    if(jog.getPosicao(temp16)!=null)mandaPraBase("Amarelo",temp16); //verifica se há algum pino nessa posição
                    
                    try {
                        jogad.enviaJogada("Amarelo4", temp16, 1);
                    } catch (IOException ex) {
                        Logger.getLogger(JanelaTabuleiro.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    jog.setPosicao("Amarelo4",temp16);
                    repaint();
                     nDado=0; //zera o valor do dado
                }}
                
             }//JOGADOR 4

            
             
                //coordenadas
                System.out.println("x: " + x + " y: " + y);
         }
         
            }
            }//checa se o dado foi rolado

            
        });



      
    //coodenadas e tamanho da elipse(pino)
        //seta cor e posição dos pinos
               
        Azul1 = new Ellipse2D.Double(660, 80, 20, 20);             
        Base[0][0] = 672;
        Base[0][1] = 87;
        A1zul.setBase("Azul1",0);

        Azul2 = new Ellipse2D.Double(690, 80, 20, 20);    
        Base[1][0] = 704;
        Base[1][1] = 88;
        A2zul.setBase("Azul2",1);

        Azul3 = new Ellipse2D.Double(660, 110, 20, 20);            
        Base[2][0] = 672;
        Base[2][1] = 117;
        A3zul.setBase("Azul3",2);

        Azul4 = new Ellipse2D.Double(690, 110, 20, 20);              
        Base[3][0] = 702;
        Base[3][1] = 117;
        A4zul.setBase("Azul4",3);

        Vermelho1 = new Ellipse2D.Double(275, 80, 20, 20);      
        Base[4][0] = 287;
        Base[4][1] = 87;
        V1ermelho.setBase("Vermelho1",4);

        Vermelho2 = new Ellipse2D.Double(305, 80, 20, 20);       
        Base[5][0] = 317;
        Base[5][1] = 87;
        V2ermelho.setBase("Vermelho2",5);

        Vermelho3 = new Ellipse2D.Double(275, 110, 20, 20);      
        Base[6][0] = 287;
        Base[6][1] = 117;
        V3ermelho.setBase("Vermelho3",6);

        Vermelho4 = new Ellipse2D.Double(305, 110, 20, 20);       
        Base[7][0] = 317;
        Base[7][1] = 117;
        V4ermelho.setBase("Vermelho4",7);
        
        Verde1 = new Ellipse2D.Double(275, 465, 20, 20);       
        Base[8][0] = 287;
        Base[8][1] = 473;
        V1erde.setBase("Verde1",8);

        Verde2 = new Ellipse2D.Double(305, 465, 20, 20);       
        Base[9][0] = 312;
        Base[9][1] = 477;
        V2erde.setBase("Verde2",9);

        Verde3 = new Ellipse2D.Double(275, 495, 20, 20);      
        Base[10][0] = 287;
        Base[10][1] = 503;
        V3erde.setBase("Verde3",10);

        Verde4 = new Ellipse2D.Double(305, 495, 20, 20);            
        Base[11][0] = 312;
        Base[11][1] = 502;
        V4erde.setBase("Verde4",11);

        Amarelo1 = new Ellipse2D.Double(660, 465, 20, 20);       
        Base[12][0] = 672;
        Base[12][1] = 472;
        A1marelo.setBase("Amarelo1",12);

        Amarelo2 = new Ellipse2D.Double(690, 465, 20, 20);       
        Base[13][0] = 702;
        Base[13][1] = 472;
        A2marelo.setBase("Amarelo2",13);

        Amarelo3 = new Ellipse2D.Double(660, 495, 20, 20);      
        Base[14][0] = 672;
        Base[14][1] = 502;
        A3marelo.setBase("Amarelo3",14);

        Amarelo4 = new Ellipse2D.Double(690, 495, 20, 20);     
        Base[15][0] = 702;
        Base[15][1] = 502;
        A4marelo.setBase("Amarelo4",15);

      
        
        
        //começa da saída do verde
        mapeamentoTabuleiro[0][0] = 450;
        mapeamentoTabuleiro[0][1] = 500;
        mapeamentoTabuleiro[1][0] = 450;
        mapeamentoTabuleiro[1][1] = 535;
        mapeamentoTabuleiro[2][0] = 485;
        mapeamentoTabuleiro[2][1] = 535;
        mapeamentoTabuleiro[3][0] = 515;
        mapeamentoTabuleiro[3][1] = 535;
        mapeamentoTabuleiro[4][0] = 515;
        mapeamentoTabuleiro[4][1] = 500;
        mapeamentoTabuleiro[5][0] = 515;
        mapeamentoTabuleiro[5][1] = 465;
        mapeamentoTabuleiro[6][0] = 515;
        mapeamentoTabuleiro[6][1] = 430;
        mapeamentoTabuleiro[7][0] = 515;
        mapeamentoTabuleiro[7][1] = 395;
        mapeamentoTabuleiro[8][0] = 515;
        mapeamentoTabuleiro[8][1] = 360;
        mapeamentoTabuleiro[9][0] = 550;
        mapeamentoTabuleiro[9][1] = 360;
        mapeamentoTabuleiro[10][0] = 550;
        mapeamentoTabuleiro[10][1] = 325;
        mapeamentoTabuleiro[11][0] = 585;
        mapeamentoTabuleiro[11][1] = 325;
        mapeamentoTabuleiro[12][0] = 620;
        mapeamentoTabuleiro[12][1] = 325;
        mapeamentoTabuleiro[13][0] = 655;
        mapeamentoTabuleiro[13][1] = 325;

        //começa a saída do amarelo
        mapeamentoTabuleiro[14][0] = 695;
        mapeamentoTabuleiro[14][1] = 325;
        mapeamentoTabuleiro[15][0] = 730;
        mapeamentoTabuleiro[15][1] = 325;
        mapeamentoTabuleiro[16][0] = 730;
        mapeamentoTabuleiro[16][1] = 290;
        mapeamentoTabuleiro[17][0] = 730;
        mapeamentoTabuleiro[17][1] = 255;
        mapeamentoTabuleiro[18][0] = 695;
        mapeamentoTabuleiro[18][1] = 255;
        mapeamentoTabuleiro[19][0] = 660;
        mapeamentoTabuleiro[19][1] = 255;
        mapeamentoTabuleiro[20][0] = 625;
        mapeamentoTabuleiro[20][1] = 255;
        mapeamentoTabuleiro[21][0] = 590;
        mapeamentoTabuleiro[21][1] = 255;
        mapeamentoTabuleiro[22][0] = 555;
        mapeamentoTabuleiro[22][1] = 255;
        mapeamentoTabuleiro[23][0] = 555;
        mapeamentoTabuleiro[23][1] = 220;
        mapeamentoTabuleiro[24][0] = 520;
        mapeamentoTabuleiro[24][1] = 220;
        mapeamentoTabuleiro[25][0] = 520;
        mapeamentoTabuleiro[25][1] = 185;
        mapeamentoTabuleiro[26][0] = 520;
        mapeamentoTabuleiro[26][1] = 150;
        mapeamentoTabuleiro[27][0] = 520;
        mapeamentoTabuleiro[27][1] = 115;

        //aqui começa o azul
        mapeamentoTabuleiro[28][0] = 520;
        mapeamentoTabuleiro[28][1] = 80;
        mapeamentoTabuleiro[29][0] = 520;
        mapeamentoTabuleiro[29][1] = 45;
        mapeamentoTabuleiro[30][0] = 485;
        mapeamentoTabuleiro[30][1] = 45;
        mapeamentoTabuleiro[31][0] = 450;
        mapeamentoTabuleiro[31][1] = 45;
        mapeamentoTabuleiro[32][0] = 450;
        mapeamentoTabuleiro[32][1] = 80;
        mapeamentoTabuleiro[33][0] = 450;
        mapeamentoTabuleiro[33][1] = 115;
        mapeamentoTabuleiro[34][0] = 450;
        mapeamentoTabuleiro[34][1] = 150;
        mapeamentoTabuleiro[35][0] = 450;
        mapeamentoTabuleiro[35][1] = 185;
        mapeamentoTabuleiro[36][0] = 450;
        mapeamentoTabuleiro[36][1] = 220;
        mapeamentoTabuleiro[37][0] = 415;
        mapeamentoTabuleiro[37][1] = 220;
        mapeamentoTabuleiro[38][0] = 415;
        mapeamentoTabuleiro[38][1] = 255;
        mapeamentoTabuleiro[39][0] = 380;
        mapeamentoTabuleiro[39][1] = 255;
        mapeamentoTabuleiro[40][0] = 345;
        mapeamentoTabuleiro[40][1] = 255;
        mapeamentoTabuleiro[41][0] = 310;
        mapeamentoTabuleiro[41][1] = 255;


        //aqui o vermelho
        mapeamentoTabuleiro[42][0] = 275;
        mapeamentoTabuleiro[42][1] = 255;
        mapeamentoTabuleiro[43][0] = 240;
        mapeamentoTabuleiro[43][1] = 255;
        mapeamentoTabuleiro[44][0] = 240;
        mapeamentoTabuleiro[44][1] = 290;
        mapeamentoTabuleiro[45][0] = 240;
        mapeamentoTabuleiro[45][1] = 325;
        mapeamentoTabuleiro[46][0] = 275;
        mapeamentoTabuleiro[46][1] = 325;
        mapeamentoTabuleiro[47][0] = 310;
        mapeamentoTabuleiro[47][1] = 325;
        mapeamentoTabuleiro[48][0] = 345;
        mapeamentoTabuleiro[48][1] = 325;
        mapeamentoTabuleiro[49][0] = 380;
        mapeamentoTabuleiro[49][1] = 325;
        mapeamentoTabuleiro[50][0] = 415;
        mapeamentoTabuleiro[50][1] = 325;
        mapeamentoTabuleiro[51][0] = 415;
        mapeamentoTabuleiro[51][1] = 360;
        mapeamentoTabuleiro[52][0] = 450;
        mapeamentoTabuleiro[52][1] = 360;
        mapeamentoTabuleiro[53][0] = 450;
        mapeamentoTabuleiro[53][1] = 395;
        mapeamentoTabuleiro[54][0] = 450;
        mapeamentoTabuleiro[54][1] = 430;
        mapeamentoTabuleiro[55][0] = 450;
        mapeamentoTabuleiro[55][1] = 465;
        
       //MAPEAMENTO CASAS INTERNAS 
        //AZUL
        mapeamentoPorCores[0][0] = 485;
        mapeamentoPorCores[0][1] = 80;       
        mapeamentoPorCores[1][0] = 485;
        mapeamentoPorCores[1][1] = 115;       
        mapeamentoPorCores[2][0] = 485;
        mapeamentoPorCores[2][1] = 150;       
        mapeamentoPorCores[3][0] = 485;
        mapeamentoPorCores[3][1] = 185;       
        mapeamentoPorCores[4][0] = 485;
        mapeamentoPorCores[4][1] = 220;       
        mapeamentoPorCores[5][0] = 485;
        mapeamentoPorCores[5][1] = 255;
                
        //VERMELHO
        mapeamentoPorCores[6][0] = 275;
        mapeamentoPorCores[6][1] = 290;      
        mapeamentoPorCores[7][0] = 310;
        mapeamentoPorCores[7][1] = 290;       
        mapeamentoPorCores[8][0] = 345;
        mapeamentoPorCores[8][1] = 290;       
        mapeamentoPorCores[9][0] = 380;
        mapeamentoPorCores[9][1] = 290;       
        mapeamentoPorCores[10][0] = 415; 
        mapeamentoPorCores[10][1] = 290;         
        mapeamentoPorCores[11][0] = 450;
        mapeamentoPorCores[11][1] = 290;    
        
        //VERDE
        mapeamentoPorCores[12][0] = 485;
        mapeamentoPorCores[12][1] = 500;      
        mapeamentoPorCores[13][0] = 485;
        mapeamentoPorCores[13][1] = 465;      
        mapeamentoPorCores[14][0] = 485;
        mapeamentoPorCores[14][1] = 430;       
        mapeamentoPorCores[15][0] = 485;
        mapeamentoPorCores[15][1] = 395;       
        mapeamentoPorCores[16][0] = 485; 
        mapeamentoPorCores[16][1] = 360;       
        mapeamentoPorCores[17][0] = 485;
        mapeamentoPorCores[17][1] = 325;                     
                       
        //AMARELO
        mapeamentoPorCores[18][0] = 695;
        mapeamentoPorCores[18][1] = 390;       
        mapeamentoPorCores[19][0] = 660;  
        mapeamentoPorCores[19][1] = 390;       
        mapeamentoPorCores[20][0] = 625;
        mapeamentoPorCores[20][1] = 390;       
        mapeamentoPorCores[21][0] = 590;
        mapeamentoPorCores[21][1] = 390;       
        mapeamentoPorCores[22][0] = 555;
        mapeamentoPorCores[22][1] = 390;       
        mapeamentoPorCores[23][0] = 520;
        mapeamentoPorCores[23][1] = 390;       
        
                        
        
        
        
        

    }

    public void setMapeamento() {
    }

    public void paint(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(tabuleiro, 0, 0, null);
        g2d.drawImage(dice1, 83, 495, null);
        g2d.drawString("Agora é a vez do jogador:", 20, 206);
        
        g2d.setColor(Color.blue);
    
        
        
        g2d.setColor(Color.red);
        




        int a = size.width;
        int b = size.height;

        //g2d.drawString("largura " +a + "altura " +b, 50, 50);

        g2d.setColor(Color.BLUE);
        g2d.fill(Azul1);// o desenho eh feito na tela
        g2d.fill(Azul2);// o desenho eh feito na tela
        g2d.fill(Azul3);// o desenho eh feito na tela
        g2d.fill(Azul4);// o desenho eh feito na tela

        g2d.setColor(Color.PINK);
        g2d.fill(Vermelho1);// o desenho eh feito na tela
        g2d.fill(Vermelho2);// o desenho eh feito na tela
        g2d.fill(Vermelho3);// o desenho eh feito na tela
        g2d.fill(Vermelho4);// o desenho eh feito na tela

        g2d.setColor(Color.GREEN);
        g2d.fill(Verde1);// o desenho eh feito na tela
        g2d.fill(Verde2);// o desenho eh feito na tela
        g2d.fill(Verde3);// o desenho eh feito na tela
        g2d.fill(Verde4);// o desenho eh feito na tela

        g2d.setColor(Color.ORANGE);
        g2d.fill(Amarelo1);// o desenho eh feito na tela
        g2d.fill(Amarelo2);// o desenho eh feito na tela
        g2d.fill(Amarelo3);// o desenho eh feito na tela
        g2d.fill(Amarelo4);// o desenho eh feito na tela



    }
    /*
    public void movePino(Ellipse2D Pino) {
    
    
    System.out.println("qq há velhinho?");
    Pino = new Ellipse2D.Double(60, 80, 20, 20);
    repaint();
    
    
    }
     */

 

    private class ButtonHandler implements ActionListener {

//Trata evento do botão
        public void actionPerformed(ActionEvent event) {
            System.out.println("ola jovem");
            Image nCapa = Toolkit.getDefaultToolkit().getImage("stone2.gif");
            Image novaCapa = nCapa.getScaledInstance(dice1.getHeight(dice), dice1.getWidth(dice), Image.SCALE_DEFAULT);
            dice.setIcon(new ImageIcon(novaCapa)); // simples assim =]  

            rolaDado(); //função que faz o dado girar

        }
    }

    public void rolaDado() {

        Random diceRoller = new Random();
        for (int i = 1; i <= 10; i++) {
            nDado = diceRoller.nextInt(6) + 1; //o 1 deve ser adicionado pois o
            dice1 = new ImageIcon(this.getClass().getResource("stone"+nDado+".gif")).getImage();
            repaint();
            System.out.println(nDado);              //método vai ate o (parâmetro -1)
        }
        
     
    }
    
public void setPosicao(String Cor, int Posicao, int proximo) {
        if("Azul1".equals(Cor)) Azul1 = new Ellipse2D.Double(mapeamentoTabuleiro[Posicao][0], mapeamentoTabuleiro[Posicao][1], 20, 20);
        if("Azul2".equals(Cor)) Azul2 = new Ellipse2D.Double(mapeamentoTabuleiro[Posicao][0], mapeamentoTabuleiro[Posicao][1], 20, 20);
        if("Azul3".equals(Cor)) Azul3 = new Ellipse2D.Double(mapeamentoTabuleiro[Posicao][0], mapeamentoTabuleiro[Posicao][1], 20, 20);
        if("Azul4".equals(Cor)) Azul4 = new Ellipse2D.Double(mapeamentoTabuleiro[Posicao][0], mapeamentoTabuleiro[Posicao][1], 20, 20);
        
        if("Vermelho1".equals(Cor)) Vermelho1 = new Ellipse2D.Double(mapeamentoTabuleiro[Posicao][0], mapeamentoTabuleiro[Posicao][1], 20, 20);
        if("Vermelho2".equals(Cor)) Vermelho2 = new Ellipse2D.Double(mapeamentoTabuleiro[Posicao][0], mapeamentoTabuleiro[Posicao][1], 20, 20);
        if("Vermelho3".equals(Cor)) Vermelho3 = new Ellipse2D.Double(mapeamentoTabuleiro[Posicao][0], mapeamentoTabuleiro[Posicao][1], 20, 20);
        if("Vermelho4".equals(Cor)) Vermelho4 = new Ellipse2D.Double(mapeamentoTabuleiro[Posicao][0], mapeamentoTabuleiro[Posicao][1], 20, 20);

        if("Verde1".equals(Cor)) Verde1 = new Ellipse2D.Double(mapeamentoTabuleiro[Posicao][0], mapeamentoTabuleiro[Posicao][1], 20, 20);
        if("Verde2".equals(Cor)) Verde2 = new Ellipse2D.Double(mapeamentoTabuleiro[Posicao][0], mapeamentoTabuleiro[Posicao][1], 20, 20);
        if("Verde3".equals(Cor)) Verde3 = new Ellipse2D.Double(mapeamentoTabuleiro[Posicao][0], mapeamentoTabuleiro[Posicao][1], 20, 20);
        if("Verde4".equals(Cor)) Verde4 = new Ellipse2D.Double(mapeamentoTabuleiro[Posicao][0], mapeamentoTabuleiro[Posicao][1], 20, 20);
        
        if("Amarelo1".equals(Cor)) Amarelo1 = new Ellipse2D.Double(mapeamentoTabuleiro[Posicao][0], mapeamentoTabuleiro[Posicao][1], 20, 20);
        if("Amarelo2".equals(Cor)) Amarelo2 = new Ellipse2D.Double(mapeamentoTabuleiro[Posicao][0], mapeamentoTabuleiro[Posicao][1], 20, 20);
        if("Amarelo3".equals(Cor)) Amarelo3 = new Ellipse2D.Double(mapeamentoTabuleiro[Posicao][0], mapeamentoTabuleiro[Posicao][1], 20, 20);
        if("Amarelo4".equals(Cor)) Amarelo4 = new Ellipse2D.Double(mapeamentoTabuleiro[Posicao][0], mapeamentoTabuleiro[Posicao][1], 20, 20);

        repaint();
        
        vezJogador = 2; //passa a vez ao proximo jogador
        
        
}    
    
   
    public void mandaPraBase(String Pino, int posicao) {
        
        if(Pino!="Azul"){
                if("Azul1".equals(jog.getPosicao(posicao)))Azul1 = new Ellipse2D.Double(660, 80, 20, 20); 
                if("Azul2".equals(jog.getPosicao(posicao)))Azul2 = new Ellipse2D.Double(690, 80, 20, 20); 
                if("Azul3".equals(jog.getPosicao(posicao)))Azul3 = new Ellipse2D.Double(660, 110, 20, 20);
                if("Azul4".equals(jog.getPosicao(posicao)))Azul4 = new Ellipse2D.Double(690, 110, 20, 20); 
        }
                
         if(Pino!="Vermelho"){
                if("Vermelho1".equals(jog.getPosicao(posicao)))Vermelho1 = new Ellipse2D.Double(275, 80, 20, 20); 
                if("Vermelho2".equals(jog.getPosicao(posicao)))Vermelho2 = new Ellipse2D.Double(305, 80, 20, 20); 
                if("Vermelho3".equals(jog.getPosicao(posicao)))Vermelho3 = new Ellipse2D.Double(275, 110, 20, 20);
                if("Vermelho4".equals(jog.getPosicao(posicao)))Vermelho4 = new Ellipse2D.Double(305, 110, 20, 20);
         }   
                
          if(Pino!="Verde"){       
                if("Verde1".equals(jog.getPosicao(posicao))) Verde1 = new Ellipse2D.Double(275, 465, 20, 20);   
                if("Verde2".equals(jog.getPosicao(posicao)))Verde2 = new Ellipse2D.Double(305, 465, 20, 20);  
                if("Verde3".equals(jog.getPosicao(posicao)))Verde3 = new Ellipse2D.Double(275, 495, 20, 20);  
                if("Verde4".equals(jog.getPosicao(posicao)))Verde4 = new Ellipse2D.Double(305, 495, 20, 20);
          }
                
            if(Pino!="Amarelo"){     
                if("Amarelo1".equals(jog.getPosicao(posicao)))Amarelo1 = new Ellipse2D.Double(660, 465, 20, 20); 
                if("Amarelo2".equals(jog.getPosicao(posicao)))Amarelo2 = new Ellipse2D.Double(690, 465, 20, 20);
                if("Amarelo3".equals(jog.getPosicao(posicao)))Amarelo3 = new Ellipse2D.Double(660, 495, 20, 20);  
                if("Amarelo4".equals(jog.getPosicao(posicao)))Amarelo4 = new Ellipse2D.Double(690, 495, 20, 20); 
            }
            
            
            
               //AZUIS
 temp1 = (nDado+27);
 temp2 = (nDado+27);
 temp3 = (nDado+27);
 temp4 = (nDado+27);
 
 //VERMELHAS
 temp5 = (nDado+41);
 temp6 = (nDado+41);
 temp7 = (nDado+41);
  temp8 = (nDado+41);
 
 //VERDES
 temp9 = (nDado);
 temp10 = (nDado);
 temp11 = (nDado);
 temp12 = (nDado);
 
 //AMARELAS
 temp13 = (nDado+13);
 temp14 = (nDado+13);
 temp15 = (nDado+13);
 temp16 = (nDado+13);
 
            
            
            
            
            
            
                repaint();
            }

}
