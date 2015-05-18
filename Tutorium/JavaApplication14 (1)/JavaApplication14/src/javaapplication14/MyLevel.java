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
public class MyLevel {
    public static MyLevel valueOf(double max, int keyUp, int keyDown, double timeToMax, boolean autoZero, boolean neagtiv, JGEngine engine, Timer timer){
        return new MyLevel(max, keyUp, keyDown, timeToMax, autoZero, neagtiv, engine, timer);
    }
    
    private double level = 0.0;
    private final double max;
    private final double min;
    private final int keyUp;
    private final int keyDown;
    private final double timeToMax;
    private final boolean autoZero;
    private final boolean neagtiv;
    private final JGEngine engine;
    private final Timer timer;

    private MyLevel(double max, int keyUp, int keyDown, double timeToMax, boolean autoZero, boolean neagtiv, JGEngine engine, Timer timer) {
        this.max = max;
        this.keyUp = keyUp;
        this.keyDown = keyDown;
        this.timeToMax = timeToMax;
        this.autoZero = autoZero;
        this.neagtiv = neagtiv;
        this.engine = engine;
        this.timer = timer;
        if(neagtiv){
            min = -max;
        }else{
            min = 0.0;
        }
    }

    private void setLevel(double level) {
        if (level > max)
            level = max;
        else if(level < min)
            level = min;
        this.level = level;
    }
    
    public double getLevel(){
        return level;
    }
    
    public double update(){
        if (keyUp >0 && engine.getKey(keyUp)){
            if (level >= 0.0)
                setLevel(level + ((Math.abs(max)/timeToMax) * timer.getTimeElapsed()));
            else 
                setLevel(level + ((Math.abs(min)/timeToMax) * timer.getTimeElapsed()));
        }else if (keyDown > 0 && engine.getKey(keyDown))  {
            if (level >= 0.0)
                setLevel(level - ((Math.abs(max)/timeToMax) * timer.getTimeElapsed()));
            else 
                setLevel(level - ((Math.abs(min)/timeToMax) * timer.getTimeElapsed()));
        }else if (autoZero){
            setLevel(level + (Math.signum(-level) * ((max/timeToMax) * timer.getTimeElapsed())));
        }
        return level;
    }
    
}
