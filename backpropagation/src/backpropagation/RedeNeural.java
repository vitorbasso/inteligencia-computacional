/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backpropagation;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author vitor
 */
public class RedeNeural {
    private ArrayList<Neuronio> neuroniosEntrada;
    private ArrayList<ArrayList> camadaOculta;
    private ArrayList<Neuronio> neuroniosSaida;
    private Scanner scanner;
    private Treino dadosTreino;
    //private NeuronioSaida saida;
    private double saidaEsperada;
    private double taxaAprendizado;
    private double erroAceitavel = 0.01;
    
    public RedeNeural(double taxa, int tamanhoEntrada, int camadas, int ocultoPorCamada, int tamanhoSaida){
        
        this.taxaAprendizado = taxa;
        
        neuroniosEntrada = new ArrayList<Neuronio>();
        camadaOculta = new ArrayList<ArrayList>();
        neuroniosSaida = new ArrayList<Neuronio>();
        
        for(int i = 0; i < tamanhoEntrada; i++){
            neuroniosEntrada.add(new NeuronioEntrada());
        }
        
        for(int i = 0; i < tamanhoSaida; i++){
            neuroniosSaida.add(new NeuronioSaida());
        }
        
        for(int i = 0; i < camadas; i++){
            camadaOculta.add(new ArrayList<Neuronio>());
        }
        
 
        for(ArrayList camada : camadaOculta){
            for(int i = 0; i < ocultoPorCamada; i++){
                camada.add(new NeuronioOculto());
            }
        }
        
        
        this.linkarNeuronios(neuroniosEntrada, camadaOculta, neuroniosSaida);
        
        
       /* NeuronioEntrada entrada1 = new NeuronioEntrada();
        NeuronioEntrada entrada2 = new NeuronioEntrada();
        
        NeuronioOculto oculto1 = new NeuronioOculto();
        NeuronioOculto oculto2 = new NeuronioOculto();
        
        this.saida = new NeuronioSaida();
        
        entrada1.addNext(oculto1);
        entrada1.addNext(oculto2);
        entrada2.addNext(oculto1);
        entrada2.addNext(oculto2);
        
        oculto1.addNext(this.saida);
        oculto2.addNext(this.saida);
        
        
        this.neuroniosEntrada.add(entrada1);
        this.neuroniosEntrada.add(entrada2);
        */
        
        this.dadosTreino = new Treino();
        
        this.scanner = new Scanner(System.in);
        
    }
    
    public void linkarNeuronios(ArrayList<Neuronio> entradas, ArrayList<ArrayList> ocultos, ArrayList<Neuronio> saidas){
        ArrayList<Neuronio> camada = ocultos.get(0);
        
        for(Neuronio neuronio : camada){
            for (Neuronio entrada : entradas){
                entrada.addNext(neuronio);
            }
        }
        
        camada = ocultos.get(ocultos.size()-1);
        for(Neuronio neuronio : camada){
            for(Neuronio saida : saidas){
                neuronio.addNext(saida);
            }
        }
        
        ArrayList<Neuronio> proximaCamada;
        for(int i = 0; i < ocultos.size() - 1; i ++){
            camada = ocultos.get(i);
            proximaCamada = ocultos.get(i+1);
            for(Neuronio neuronio : camada){
                for(Neuronio proximoNeuronio : proximaCamada){
                    neuronio.addNext(proximoNeuronio);
                }
            }
        }
        
    }
    
    public void setDadosTreino(Treino dados){
        this.dadosTreino = dados;
    }
    
    public void treinar(){
        
        boolean continuarTreino = true;
        int ciclo = 0;
        
        while(continuarTreino){
            int count = 0;
        
            for(int i = 0 ; i < this.dadosTreino.size() - 1; i += this.neuroniosEntrada.size()){

                for(Neuronio neuronio : this.neuroniosEntrada){
                    neuronio.input(this.dadosTreino.getDado(count));
                    count ++;
                }
                this.saidaEsperada = this.dadosTreino.getDado(count++);
                this.neuroniosSaida.get(0).adjustWeight(this.taxaAprendizado, this.saidaEsperada);

            }
            ciclo++;
            if(Math.abs(this.getResultado() - this.saidaEsperada) <= this.erroAceitavel){
                continuarTreino = false;
            }
        }
        
        System.out.println("\nTREINAMENTO CONCLUIDO COM " + ciclo + " CICLOS.\n");
        
    }
    
    
    public void input(ArrayList<Double> inputs){
        
        int index = 0;
        if(inputs.size() == this.neuroniosEntrada.size()){
            for(Neuronio neuronio : this.neuroniosEntrada){
                neuronio.input(inputs.get(index++));
            }
        }
        
    }
    
    public double getResultado(){
        return this.neuroniosSaida.get(0).getY();
    }
    
    public int showResultado(){
        if(getResultado() > 0.5){
            return 1;
        }else
            return 0;
    }
    
}
