/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Aufgabe1b.Controler;

import Aufgabe1b.GUI.GEngine;

/**
 *
 * @author ClausTorben
 */
public class SwitchControl {
    private boolean _on;
    private int _key;
    private boolean _used;
    public SwitchControl(int aKey){
        _on = true;
        _key = aKey;
        _used = false;
    }

    public boolean isOn() {
        return _on;
    }

    public void setOn(boolean _on) {
        this._on = _on;
    }

    public int getKey() {
        return _key;
    }

    public void setKey(int _key) {
        this._key = _key;
    }

    public boolean isUsed() {
        return _used;
    }

    public void setUsed(boolean used) {
        this._used = used;
    }
    
    public void step(){
        if(!isUsed() && GEngine.getInstance().getKey(getKey())){
            setOn(!isOn());
            setUsed(true);
        }
        else if(!GEngine.getInstance().getKey(getKey())){
            setUsed(false);
        }
    }
}
