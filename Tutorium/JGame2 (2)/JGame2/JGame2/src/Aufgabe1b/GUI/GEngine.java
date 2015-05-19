package Aufgabe1b.GUI;

import Aufgabe1b.Controler.GControl;
import java.util.Calendar;
import jgame.JGColor;
import jgame.platform.JGEngine;

public class GEngine extends JGEngine{
    private static GEngine _instance;
    private long _timeLastFrame;
    private long _currentTime;

    /**
     * Standart Konstruktor für Aplets
     */
    private GEngine() {
        this.initEngineApplet();
    }
    
    /**
     * Konstructor für PC's
     * @param aWidth - Fenster Breite
     * @param aHeight - Fenster Höhe
     */
    private GEngine(int aWidth, int aHeight) {
        this.initEngine(aWidth, aHeight);
    }
    
    /**
     * Singleton Getter
     * @return - Singleton Engine
     */
    public static GEngine getInstance() {
        if (_instance == null)
            _instance = new GEngine(800,600);
        return GEngine._instance;
    }

    public long getCurrentTime() {
        return _currentTime;
    }

    private void setCurrentTime(long _currentTime) {
        this._currentTime = _currentTime;
    }

    public long getTimeLastFrame() {
            return this._timeLastFrame;
    }

    private void setTimeLastFrame(long aTimeLastFrame) {
            this._timeLastFrame = aTimeLastFrame;
    }

    /**
     * Errechneter DeltaTime Wert
     * @return DeltaTime in millisec
     */
    public double getDeltaTime(){
        return ((double) (getCurrentTime() - getTimeLastFrame()))/1000;
    }

    /**
     * Intialisierung der Spielfläche
     */
    @Override
    public void initCanvas() {
        this.setCanvasSettings(40, 30, 16, 16, null, null, null);
    }

    /**
     * Initialisierung des Spiels
     */
    @Override
    public void initGame() {
        setTimeLastFrame(Calendar.getInstance().getTimeInMillis());
        this.setFrameRate(60, 1);
        setPFSize(80, 60);
        setPFWrap(true, true, -2, -2);
        setBGColor(JGColor.white);
    }
    /**
     * Hier wird alles eingetragen, was bei jedem Durchlauf ausfgeführt werden soll.
     */
    @Override
    public void doFrame() {
        setCurrentTime(Calendar.getInstance().getTimeInMillis());
        moveObjects();
        GControl.getInstance().step();
        setTimeLastFrame(getCurrentTime());
    }
}