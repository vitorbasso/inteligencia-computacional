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
    private Scanner scanner;
    private Treino dadosTreino;
    private NeuronioSaida saida;
    private double saidaEsperada;
    private double taxaAprendizado;
    private double erroAceitavel = 0.01;
    
    public RedeNeural(double taxa){
        
        this.taxaAprendizado = taxa;
        
        neuroniosEntrada = new ArrayList<Neuronio>();
        NeuronioEntrada entrada1 = new NeuronioEntrada();
        NeuronioEntrada entrada2 = new NeuronioEntrada();
        
        NeuronioOculto oculto1 = new NeuronioOculto();
        NeuronioOculto oculto2 = new NeuronioOculto();
        
        saida = new NeuronioSaida();
        
        entrada1.addNext(oculto1);
        entrada1.addNext(oculto2);
        entrada2.addNext(oculto1);
        entrada2.addNext(oculto2);
        
        oculto1.addNext(saida);
        oculto2.addNext(saida);
        
        
        neuroniosEntrada.add(entrada1);
        neuroniosEntrada.add(entrada2);
        
        dadosTreino = new Treino();
        
        scanner = new Scanner(System.in);
        
    }
    
    public void setDadosTreino(Treino dados){
        this.dadosTreino = dados;
    }
    
    public void treinar(){
        
        boolean continuarTreino = true;
        int ciclo = 0;
        
        while(continuarTreino){
            int count = 0;
        
            for(int i = 0 ; i < dadosTreino.size() - 1; i += neuroniosEntrada.size()){

                for(Neuronio neuronio : neuroniosEntrada){
                    neuronio.input(dadosTreino.getDado(count));
                    count ++;
                }
                saidaEsperada = dadosTreino.getDado(count++);
                this.saida.adjustWeight(taxaAprendizado, saidaEsperada);

            }
            ciclo++;
            if(Math.abs(this.getResultado() - saidaEsperada) <= erroAceitavel){
                continuarTreino = false;
            }
        }
        
        System.out.println("\nTREINAMENTO CONCLUIDO COM " + ciclo + " CICLOS.\n");
        
    }
    
    
    public void input(ArrayList<Double> inputs){
        
        int index = 0;
        if(inputs.size() == neuroniosEntrada.size()){
            for(Neuronio neuronio : neuroniosEntrada){
                neuronio.input(inputs.get(index++));
            }
        }
        
    }
    
    public double getResultado(){
        return this.saida.getY();
    }
    
    public int showResultado(){
        if(getResultado() > 0.5){
            return 1;
        }else
            return 0;
    }
    
}
