
package javaapplication14_unv;

import jgame.platform.JGEngine;

public class Switch {
    
    // Factory Methode
    public static Switch valueOf(JGEngine engine, int key) {                             //dependancy injection
        return new Switch(engine, key);
    }
    
    private boolean on = false;    // Instanzvariablen immer privat in Java
    private final JGEngine engine;
    private final int key;
    private boolean onceNotUsed = true;             // crerate field macht Instanzvariablen
    
    // Konstruktor
    private Switch(JGEngine engine, int key){
        this.engine = engine;
        this.key = key;
    }
    
    public boolean isOn(){     // Konvention bei boolean:  "is" statt "get"
        return on;
    }
    
    public boolean update(){
        if(engine.getKey(key) && onceNotUsed){
            on = !on;
            onceNotUsed = false;
        } else if(!engine.getKey(key)){
            onceNotUsed = true;
        }
        return on;
    }
}
