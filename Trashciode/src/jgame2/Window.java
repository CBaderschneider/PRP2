package jgame2;

import Aufgabe1b.Physics.ParticleInterface;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import jgame.JGColor;
import jgame.platform.JGEngine;

public class Window extends JGEngine {
    private Timer timer;
    private Switch halloSwitch;
    private boolean isloaded = false;
    private Lever redHello;
    private Lever greenHello;
    private Player player;
    
    private Window(int width, int height, ParticleInterface particle) {
        
        timer = Timer.valueOf();
        // Erstellen des Swithes und injezieren der Engine
        // Achtung this im Konstrucktor zu übergeben ist gefährlich,
        // es Muss auf Jedenfall darauf geachtet werden, dass der Switch,
        // oder Unterklassen/InstanzVariable den Konstruktor von Window 
        // nicht Aufrufen
        halloSwitch = Switch.valueOf(this, KeyEvent.VK_H);
        //(keyUp, keyDown, engine, max, negativeValues, timeToMax, autoZero, timer)
        redHello = Lever.valueOf(KeyEvent.VK_Q, KeyEvent.VK_A, this, 255, false, 3.0, false, timer);
        greenHello = Lever.valueOf(KeyEvent.VK_W, KeyEvent.VK_S, this, 255, false, 3.0, true, timer);
        initEngine(width, height);
        player = Player.valueOf(this, timer, particle);
        
    }
    
    public static Window valueOf(int width, int height,ParticleInterface particle) {
        return new Window(width, height, particle);
    }
    

    @Override
    public void initCanvas() {
        setCanvasSettings(
                50, // Anzahl auf x-Achse
                37, // Anzahl auf y-Achse
                16, // Breite Tiles
                16, // Hoehe Tiles
                JGColor.blue, // Vordergrund
                new JGColor(0, 200, 100, 100), // Hintergrund (Rot=0, Gruen=100, Blue=100)
                null // Schriftart
        );
    }

    @Override
    public void initGame() {
        setFrameRate(10, 2);
        defineImage("PlayerSprite", "p", 0, "0.png", "-");
    }
    
    @Override
    public void doFrame() {
        // LOGIK
        
        // Update-Aufruf vom Timer
        double timeDiff = timer.update();
        // Update des Switches
        halloSwitch.update();
        redHello.update();
        greenHello.update();
        moveObjects();
        
    }
    
    @Override
    public void paintFrame() {
        //System.out.println(redHello.getLevel());
        setColor(new JGColor((int) redHello.getLevel(), (int) greenHello.getLevel(), 0));
        // RENDERN
        if(halloSwitch.isOn()){
            drawString("Hello world!",
                    viewWidth()/2, // x-Position
                    viewHeight()/2, // y-Position
                    0); // 0 = zentriert, -1 = linksbuendig, 1 = rechtsbuendig
    
        }
        
    }
}
