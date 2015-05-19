/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jgame2;

import jgame.platform.JGEngine;

/**
 *
 * @author Admin
 */
public class Switch {
    public static Switch valueOf(JGEngine engine, int key){
        return new Switch(engine, key);
    } 
    
    private boolean on = false;
    private final JGEngine engine;
    private final int key;
    private boolean onceNotUsed = true;
    
    
    private Switch(JGEngine engine, int key) {
        // Depedency Injection für die Engine, 
        // zur Umgehung von Globalen variablen, oder Singletons
        this.engine = engine;
        this.key = key;
    }
    
    public boolean isOn(){
        return on;
    }
    
    public boolean update(){
        if(engine.getKey(key) && onceNotUsed){
            on = !on;
            onceNotUsed = false;
        }else if(!engine.getKey(key)){
            onceNotUsed = true;
        }
        return on;
    }
    
    
}
