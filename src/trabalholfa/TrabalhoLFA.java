/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalholfa;

import alice.tuprolog.*;
import alice.tuprolog.event.OutputEvent;
import alice.tuprolog.event.OutputListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TrabalhoLFA {
static String Result = "";
static String Aux = "";
static String Final = "";
    public static void main(String[] args) throws Exception {
        Prolog engine = new Prolog();
        int aux=0;
        String nome = "C:\\Users\\João\\Documents\\NetBeansProjects\\TrabalhoLFA\\src\\LFA.txt";
        String theory=null;
        
        // Ler e abrir Arquivo
        
       try { 
           FileReader arq = new FileReader(nome);
           BufferedReader lerArq = new BufferedReader(arq);
          String linha = lerArq.readLine(); 
          
       while (linha != null) { 
           theory=theory+linha+".\n";
           linha = lerArq.readLine(); 
    } arq.close();
       }
    catch (IOException e) { 
    System.err.printf("Erro na abertura do arquivo: %s.\n", 
        e.getMessage());
}

            // Completar o código prolog
                theory = theory+ "aceita(Palavra):-inicial(In),reconhece(Palavra,In), write(In).\n"
                +"reconhece([],EstCorr):-final(EstCorr),write(EstCorr).\n"
                +"reconhece([S|R],EstCorr):-fprograma(EstCorr,S,ProxEst),reconhece(R,ProxEst),write(EstCorr).\n"
                +"reconhece(P,EstCorr):-vazio(EstCorr, ProxEst),reconhece(P,ProxEst).\n";


     
        engine.setTheory( new Theory(theory)); 
           
         // Métodopara ler o Write
        
        engine.addOutputListener(new OutputListener() {
        @Override
            public void onOutput(OutputEvent e) {
                Result += e.getMsg();
            }
        });
           
   
        // Passagem do que se deseja ler.
        
        SolveInfo res = res = engine.solve("aceita([a,a,a]).");
       // res = engine.solve("aceita([a,a,a]).");
        if(res.isSuccess()){
            
                   // Inverter String Recebida, não consegui usar o reverse() direto
            
                 int len=Result.length();
                 char[] tempCharArray = new char[len];  
                 char[] charArray = new char[len];  
  
   
                for (int i = 0; i < len; i++) {  
                  tempCharArray[i] = Result.charAt(i);  
                }  


                for (int j = 0; j < len; j++) { 
                  charArray[j] = tempCharArray[len - 1 - j];
                } 

                for (int j = 0; j < len; j++) { 
                   tempCharArray[j]=charArray[j] ;
                } 

                    Final = new String(charArray);

                        for (int j = 0; j < len; j++) {  ;
                } 
  
                        
                        // Tentativa falha de  separar os caminhos.
                        
                    for (int j = 0; j < len-1; j++) { 

                      if(charArray[j]>charArray[j+1]){
                          Aux=Final.substring(aux,j+1);
                          Aux=Aux+",";
                          aux=j+1;

                      }
                      else
                          if(j==len-2)
                          {
                              Aux=Aux+Final.substring(aux,len);
                          }
                    }
        
                        
                       //Impressão
        System.out.println("Automato Aceito ! O(s) Caminho(s) é(são): " + Aux);
        if (engine.hasOpenAlternatives()){
            
        if (res.isSuccess()){
            res=engine.solveNext();

            System.out.println("An other substitution: "+Result);
            }
}
 
        }  
        else
            System.out.println("Automato Não Aceito ! ");
    }
}