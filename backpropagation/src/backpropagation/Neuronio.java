/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backpropagation;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.lang.Math;

/**
 *
 * @author vitor
 */
public abstract class Neuronio {
    
    protected ArrayList<Double> pesos;
    protected double bias, a;
    protected ArrayList<Neuronio> next;
    protected ArrayList<Neuronio> before;
    private double y;
    
    public Neuronio(){
        pesos = new ArrayList<Double>();
        next = new ArrayList<Neuronio>();
        before = new ArrayList<Neuronio>();
        this.a = -1;
        this.bias = ThreadLocalRandom.current().nextDouble(-2,2);
    }
    
    //abstract
    
    
    //non abstract
    
    public void calculateY(){
        //Função Sigmoide
        
        int count = 0;
        double potencia = 0;
        
        for(Neuronio neuronio : before){
            potencia += neuronio.getY() * this.pesos.get(count);
            count++;
        }
        
        potencia += this.getBias() * this.getA();
        
        this.y = (1 / (1 + Math.exp(-potencia)));
    }
    
    
    
    
    
    
    
    
    
   
    
    public double error(double esperado){
        
        return (esperado - this.getY());     
    }
    
    public double delta(double esperado){
        
        double D = this.getY() * (1 - this.getY()) * error(esperado);
        return D;
    }
    
    public double weightCorrection(double taxaAprendizagem, double esperado, double yAnterior){
        
        double correction = taxaAprendizagem * yAnterior * delta(esperado);
        
        return correction;
    }
    
    public double biasCorrection(double taxaAprendizagem, double esperado){
        double correction = taxaAprendizagem * this.getA() * delta(esperado);
        return correction;
    }
    
    
    public double calculateCorrectWeight(double taxaAprendizagem, double esperado, Neuronio neuronioLigado, double pesoAtual){ 
        return pesoAtual + weightCorrection(taxaAprendizagem, esperado, neuronioLigado.getY());
    }
    
    public double calculateCorrectBias(double taxaAprendizagem, double esperado){
        return this.getBias() + biasCorrection(taxaAprendizagem, esperado);
    }
    
    
    public void adjustWeight(double taxaAprendizagem, double esperado){
        
        for(Neuronio neuronio : before){
            neuronio.adjustWeight(taxaAprendizagem, esperado);
          //  System.out.println("Mudando peso de " + getYourPeso(neuronio) + " para " + calculateCorrectWeight(taxaAprendizagem, esperado, neuronio, getYourPeso(neuronio)) + ".");
            this.setPeso(calculateCorrectWeight(taxaAprendizagem, esperado, neuronio, getYourPeso(neuronio)),neuronio);
        }
        
        //System.out.println("Mudando bias de " + getBias() + " para " + calculateCorrectBias(taxaAprendizagem, esperado) + "."); 
        this.setBias(calculateCorrectBias(taxaAprendizagem, esperado));
        
        
    }
    
    
    
    
    
    public double initializeWeight(){
        return ThreadLocalRandom.current().nextDouble(-2,2);
    }
    
    public void input(double i){
        
    }
    
    
    //==================================================================================================================================
    
    //===========    GETTERS AND SETTERS    =============================================================================================
    
    //==================================================================================================================================
    
    public double getY(){
        this.calculateY();
        return this.y;
    }
    
    protected void setY(double resultado){
        this.y = resultado;
    }
    
    public void addNext(Neuronio nextNeuronio){
        this.next.add(nextNeuronio);
        nextNeuronio.addBefore(this);
    }
    
    public void addBefore(Neuronio beforeNeuronio){
        this.before.add(beforeNeuronio);
        double r = ThreadLocalRandom.current().nextDouble(0,2);
        this.addPeso(initializeWeight());
    }
    
    public void addPeso(double w){
        this.pesos.add(w);
    }
    
    public int getYourIndex(Neuronio neuronio){
        return before.indexOf(neuronio);
    }
    
    private void setPeso(double w, Neuronio neuronio){
        pesos.set(getYourIndex(neuronio), w);
    }
    
    public double getYourPeso(Neuronio neuronio){
        return getPeso(getYourIndex(neuronio));
    }
    
    public double getPeso(int index){
        return this.pesos.get(index);
    }
    
    public void setBias(double bias){
        this.bias = bias;
    }
    
    public double getBias(){
        return this.bias;
    }
    
    public double getA(){
        return this.a;
    }
    
    
    //=====================================================================================================================================
}
