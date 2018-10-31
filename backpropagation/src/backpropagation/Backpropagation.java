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
public class Backpropagation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean repetir = true;
        RedeNeural backpropagation = new RedeNeural(0.5, 2, 1, 2, 1);
        Scanner scanner = new Scanner(System.in);
        
        backpropagation.treinar();
        
        ArrayList<Double> inputs = new ArrayList<>();
        inputs.add(0.0);
        inputs.add(0.0);
        
        while(repetir){
            System.out.println("Quais valores deseja testar a rede neural? \nValor 1: ");
            inputs.set(0, scanner.nextDouble());
            System.out.println("valor 2: ");
            inputs.set(1, scanner.nextDouble());
            backpropagation.input(inputs);
        
            System.out.println("Resultado: " + backpropagation.showResultado());
            
            System.out.println("Deseja testar mais valores? (1/0): ");
            repetir = (1 == scanner.nextInt());
        }
        
        
    }
    
}
