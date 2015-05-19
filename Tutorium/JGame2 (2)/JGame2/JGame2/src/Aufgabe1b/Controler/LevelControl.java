package Aufgabe1b.Controler;

import Aufgabe1b.GUI.GEngine;

public class LevelControl {
    private double _min;
    private double _max;
    private double _timeToMax;
    private double _level;
    private Integer _keyUp;
    private Integer _keyDown;
    private boolean _toggleToZero;
    private GEngine _gEngine;

    /**
     * Standart Konstruktor
     * @param aMin - Minimum
     * @param aMax - Maximum
     * @param aTimeToMax - Zeit bis erreichen des Minimums/Maximums
     * @param aKeyUp - Taste zum erhöhen des Levels (can be Null : means not Functional)
     * @param aKeyDown - Taste zum verringern des Levels (can be Null : means not Functional)
     * @param aToggleToZero - Automatisch auf 0.0 zurücksinken?
     */
    public LevelControl(double aMin, double aMax, double aTimeToMax, Integer aKeyUp, Integer aKeyDown, boolean aToggleToZero) {
        _gEngine = GEngine.getInstance();
        _min = aMin;
        _max = aMax;
        _timeToMax = aTimeToMax;
        _keyUp = aKeyUp;
        _keyDown = aKeyDown;
        _toggleToZero = aToggleToZero;   
    }

    public double getMin() {
        return this._min;
    }

    public double getMax() {
        return this._max;
    }

    public double getTimeToMax() {
        return this._timeToMax;
    }

    public double getLevel() {
        return this._level;
    }

    public Integer getKeyUp() {
        return this._keyUp;
    }

    public Integer getKeyDown() {
        return this._keyDown;
    }

    public boolean isToggleToZero() {
        return this._toggleToZero;
    }

    public GEngine getGEngine() {
        return this._gEngine;
    }

    public void setToggleToZero(boolean aToggleToZero) {
        this._toggleToZero = aToggleToZero;
    }

    public void setKeyDown(Integer aKeyDown) {
        this._keyDown = aKeyDown;
    }

    public void setKeyUp(Integer aKeyUp) {
        this._keyUp = aKeyUp;
    }
    
    /**
     * Setzt das Level und verhindert das setzen des Levels 
     * außerhalb des angegebenen Bereichs
     * @param aLevel - double Level
     */
    private void setLevel(double aLevel) {
        if (aLevel > getMax())
            aLevel = getMax();
        else if(aLevel < getMin())
            aLevel = getMin();
        this._level = aLevel;
    }
    
    /**
     * Diese funktion sollte bei jedem Framedurchlauf aufgerufen werden.
     * Sie berechnet die Levelveränderung
     * @param aDeltaTime - Zeit seit der Letzten Ausführung
     */
    public void step(double aDeltaTime) {
        if (getKeyUp() != null && getGEngine().getKey(getKeyUp())){
            if (getLevel() >= 0.0)
                setLevel(getLevel() + ((Math.abs(getMax())/getTimeToMax()) * aDeltaTime));
            else 
                setLevel(getLevel() + ((Math.abs(getMin())/getTimeToMax()) * aDeltaTime));
        }else if (getKeyDown() != null && getGEngine().getKey(getKeyDown()))  {
            if (getLevel() >= 0.0)
                setLevel(getLevel() - ((Math.abs(getMax())/getTimeToMax()) * aDeltaTime));
            else 
                setLevel(getLevel() - ((Math.abs(getMin())/getTimeToMax()) * aDeltaTime));
        }else if (isToggleToZero()){
            setLevel(getLevel() + (Math.signum(-getLevel()) * ((getMax()/getTimeToMax()) * aDeltaTime)));
        }
    }
}