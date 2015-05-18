package javaapplication14_unv;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import jgame.JGColor;
import jgame.platform.JGEngine;

public class Window extends JGEngine{                   // in ruby mit << nach Klassennamen
    private Timer timer;
    private Switch halloSwitch;
    private Switch hallo2Switch;
    private boolean isloaded = false;
    
    private Window(int width, int height){
        initEngine(width, height);
        timer = Timer.valueOf();
        halloSwitch = Switch.valueOf(this, KeyEvent.VK_H);
        hallo2Switch = Switch.valueOf(this, KeyEvent.VK_G);
        isloaded = true;
    }
    
    public static Window valueOf(int width, int height){
        return new Window(width, height);
    }
    
    
    @Override                                   // @Override ist optional, sollte aber verwendet werden wenn Methoden aus einer Oberklasse implementiert werden.
    public void initCanvas() {                                                  // Spielfeldinitialisierung   Festlegen wie groß die Tiles(Kästchen) sein müssen/sollen
        setCanvasSettings(
                50, // Anzahlt Tiles auf x-Achse
                37, // Anzahl Tiles auf y-Achse
                16, //Breite Tiles
                16, //Höhe Tiles
                JGColor.blue, // Farbe Vordergrund
                new JGColor(0, 200, 100, 100), // Hintergrund (Rot=100, Gruen=100, Blue=100 vierter Parameter ggf für Transparenz
                null // Schriftart (null=Standard)
              
        );
        //setMaximumSize und setMinimumSize
//        setMinimumSize(new Dimension(800,600));
//        setMaximumSize(new Dimension(800,600));       //funktioniert nicht wie gehofft
    }

    @Override
    public void initGame() {
        while (!isloaded){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        setFrameRate(60, 2);     // 100= FPS (kann man frei wählen,    2=???   weiss keiner so genau    JGame-Alternative  lipgdx (oder so ähnlich)
    }
    
    @Override
    public void doFrame() {                                                     // zuständig für die gesamte Logik im Programm!!!
        // Logik
//        getKey(KeyEvent.VK_H);
        // Update-Aufruf vom Timer
        double timeDiff = timer.update();
//        System.out.println(timeDiff);
        // Step-Aufruf von Porsche
//        porsche.step(timeDiff, 1.0);
        hallo2Switch.update();
        halloSwitch.update();

    }
    
    @Override
    public void paintFrame() {                                                  // zuständig für die grafische Darstellung (Rendering)
        setColor(JGColor.red);
        // Rendering
        if (halloSwitch.isOn()){
            
        
          drawString("Hello world!",         // String der Angezeigt werden soll
                viewWidth()/2,               // x-Position
                viewHeight()/2,              // y-Position
                0);                          // 0=zentriert, -1=linksbuendig, 1=rechtsbuendig
   
        }
    }
    
}
