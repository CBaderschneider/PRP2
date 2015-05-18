/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Aufgabe1b.Physics;

/**
 *
 * @author ClausTorben
 */
public interface ParticleInterface {

    double getBreakLevel();

    double getPosX();

    double getPosY();

    double getPropLevel();

    double getSpeed();
    
    double getSteeringLevel();
    
    boolean isStartedUncontroled();
    
    double getDirection();

    double getTraction();

    boolean isAbsOn();

    boolean isAsrOn();
    
    boolean isUncontroled();
    
    double getPaintDirection();

    void set(double aTime, double aPosX, double aPosY, double aSpeed, double aPropLevel);

    void setAbsOn(boolean aAbsOn);

    void setAsrOn(boolean aAsrOn);

    void setBreakLevel(double aBreakLevel);

    void setDirection(double aDirection);

    void setPropLevel(double aPropLevel);

    void setSteeringLevel(double aSteeringLevel);

    void setTraction(double aTraction);

    /**
     * Step Method sollte bei jedem durchlauf gerufen werden
     * @param aDeltaTime - Zeit seit der letzten Ausführung
     */
    void step(double aDeltaTime);
    
}
