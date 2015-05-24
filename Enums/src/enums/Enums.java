/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enums;

/**
 *
 * @author miche_000
 */
public class Enums {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CarState my_val = CarState.NORDEN;
        CarState my_val_2 = my_val.dreheNachRechts();
        
        System.out.println(my_val_2.name());       // so, dann strg+space  (se geht auch)
        
        
    }
    
}
