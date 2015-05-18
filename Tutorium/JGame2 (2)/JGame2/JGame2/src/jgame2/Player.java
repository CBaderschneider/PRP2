/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jgame2;

import jgame.JGObject;
import jgame.platform.JGEngine;

/**
 *
 * @author torben
 */
public class Player extends JGObject{
    public static Player valueOf(JGEngine engine, Timer timer, ParticleInterface particle){
        return new Player(engine, timer, particle);
    }
    private final JGEngine engine;
    private final Timer timer;
    private final ParticleInterface particle;

    private Player(JGEngine engine, Timer timer, ParticleInterface particle) {
        super("PlayerModel", true, 0.0, 0.0, 1, "PlayerSprite");
        this.engine = engine;
        this.timer = timer;
        this.particle = particle;
    }
    
    @Override
    public void move(){
        particle.step(timer.getTimeElapsed());
        x = particle.getPosX();
        y = particle.getPosY();
    }
    
}
