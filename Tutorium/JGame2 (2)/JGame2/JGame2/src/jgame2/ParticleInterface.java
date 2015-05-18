/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jgame2;

/**
 *
 * @author ClausTorben
 * nicht vollständig!
 */
public interface ParticleInterface {

    double getPosX();

    double getPosY();

    /**
     * Step Method sollte bei jedem durchlauf gerufen werden
     * @param aDeltaTime - Zeit seit der letzten Ausführung
     */
    void step(double aDeltaTime);
    
}
