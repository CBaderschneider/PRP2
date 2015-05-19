/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jgame2;

import jgame.platform.JGEngine;

/**
 *
 * @author torben
 */
public class Lever {
    
    public static Lever valueOf(int keyUp, int keyDown, JGEngine engine, double max, boolean negativeValues, double timeToMax, boolean autoZero, Timer timer){
        return new Lever(keyUp, keyDown, engine, max, negativeValues, timeToMax, autoZero, timer);
    }
    
    private double level = 0.0;
    private final int keyUp;
    private final int keyDown;
    private final JGEngine engine;
    private final double max;
    private final boolean allowNegativeValues;
    private final double timeToMax;
    private final boolean autoZero;
    private final Timer timer;

    private Lever(int keyUp, int keyDown, JGEngine engine, double max, boolean negativeValues, double timeToMax, boolean autoZero, Timer timer) {
        this.keyUp = keyUp;
        this.keyDown = keyDown;
        this.engine = engine;
        this.max = max;
        this.allowNegativeValues = negativeValues;
        this.timeToMax = timeToMax;
        this.autoZero = autoZero;
        this.timer = timer;
    }

    private void setLevel(double level) {
        if(level > max){
            level = max;
        }
        else if(allowNegativeValues && level < -max){
            level = -max;
        }
        else if(!allowNegativeValues && level < 0.0){
            level = 0.0;
        }
        this.level = level;
    }

    public double getLevel() {
        return level;
    }

    public double update(){
        if (keyUp > 0 && engine.getKey(keyUp)){
            setLevel( getLevel() + ((Math.abs(max)/timeToMax) * timer.getTimeElapsed()) );
        }else if(keyDown > 0 && engine.getKey(keyDown)){
            setLevel( getLevel() - ((Math.abs(max)/timeToMax) * timer.getTimeElapsed()) );
        }else if(autoZero){
            setLevel( getLevel() + Math.signum(-getLevel()) * ((Math.abs(max)/timeToMax) * timer.getTimeElapsed()) );
        }
        return level;
    }
    
    
    
}
