/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backpropagation;

/**
 *
 * @author vitor
 */
public class NeuronioEntrada extends Neuronio{
    
    private double entrada;
    
    @Override
    public void input(double input){
        this.entrada = input;
    }
    
    @Override
    public void calculateY(){
        setY(this.entrada);
    }
    
    @Override
    public void adjustWeight(double taxaAprendizagem, double esperado){
        
    }
    
    
}
