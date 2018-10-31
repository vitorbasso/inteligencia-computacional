/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backpropagation;
import java.util.ArrayList;

/**
 *
 * @author vitor
 */
public class Treino {
    
    private ArrayList<Double> dados;
    
    public Treino(){
        dados = new ArrayList<>();
        inicializar();
    }
    
    private void inicializar(){
        dados.add(1.0);
        dados.add(1.0);
        dados.add(0.0);
        
        dados.add(1.0);
        dados.add(0.0);
        dados.add(1.0);
        
        dados.add(0.0);
        dados.add(1.0);
        dados.add(1.0);
        
        dados.add(0.0);
        dados.add(0.0);
        dados.add(0.0);
    }
    
    public int size(){
        return (dados.size() / 3) * 2;
    }
    
    public double getDado(int index){
        return dados.get(index);
    }
    
}
