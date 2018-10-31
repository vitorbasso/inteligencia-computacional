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
public class NeuronioOculto extends Neuronio{
    
    private double somatoriaDeltaAux(double esperado){
        
        double somatorio = 0;
        
        for(Neuronio neuronio : next){
            somatorio += neuronio.delta(esperado) * neuronio.getYourPeso(this);
        }
        
        return somatorio;
    }
   
    @Override
    public double delta(double esperado){
        
        double D = this.getY() * (1 - this.getY()) * somatoriaDeltaAux(esperado);
        
        return D;
    }
    
}
