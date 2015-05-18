/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication14;

import jgame.platform.JGEngine;

/**
 *
 * @author abq329
 */
public class Switch {
    public static Switch valueOf(int key, JGEngine engine){
        return new Switch(key, engine);
    }
    
    public static Switch valueOf(int key, JGEngine engine, boolean on){
        return new Switch(key, engine, on);
    }
    
    private int key;
    private JGEngine engine;
    private boolean used = false;
    private boolean on = true;
    private Switch(int key, JGEngine engine){
        this(key, engine, false);
    }
    
    private Switch(int key, JGEngine engine, boolean on){
        this.key = key;
        this.engine = engine;
        this.on = on;
    }
    
    public boolean isOn(){
        return on;
    }
    
    public boolean update(){
        if(!used && engine.getKey(key)){
            on = !on;
            used = true;
        }else if(!engine.getKey(key)){
            used = false;
        }
        return on;
    }
}
