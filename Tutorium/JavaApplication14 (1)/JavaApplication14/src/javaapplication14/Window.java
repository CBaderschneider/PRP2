package javaapplication14;

import com.sun.glass.events.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import jgame.JGColor;
import jgame.platform.JGEngine;

public class Window extends JGEngine {
    private Timer timer;
    private Switch halloSwitch;
    private boolean initialized = false;
    private MyLevel red;
    private MyLevel green;
    private MyLevel blue;
    private JGColor halloColor;
    private Window(int width, int height) {
        initEngine(width, height);
        timer = Timer.valueOf();
        halloSwitch = Switch.valueOf(KeyEvent.VK_H, this);
        red = MyLevel.valueOf(255, KeyEvent.VK_Q, KeyEvent.VK_A, 2.0, true, false, this, timer);
        green = MyLevel.valueOf(255, KeyEvent.VK_W, KeyEvent.VK_S, 2.0, false, false, this, timer);
        blue = MyLevel.valueOf(255, KeyEvent.VK_E, KeyEvent.VK_D, 2.0, false, false, this, timer);
        halloColor = new JGColor(red.getLevel(), green.getLevel(), blue.getLevel());
        
        initialized = true;
    }
    
    public static Window valueOf(int width, int height) {
        return new Window(width, height);
    }
    

    @Override
    public void initCanvas() {
        setCanvasSettings(
                50, // Anzahl auf x-Achse
                37, // Anzahl auf y-Achse
                16, // Breite Tiles
                16, // Hoehe Tiles
                JGColor.blue, // Vordergrund
                new JGColor(25, 100, 150), // Hintergrund (Rot=0, Gruen=100, Blue=100)
                null // Schriftart
        );
    }

    @Override
    public void initGame() {
        while(!initialized){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        setFrameRate(10, 2);
    }
    
    @Override
    public void doFrame() {
        // LOGIK
        
        // Update-Aufruf vom Timer
        double timeDiff = timer.update();
        System.out.println(timeDiff);
        halloSwitch.update();
        red.update();
        green.update();
        blue.update();
        // Step-Aufruf von Porsche
        //porsche.step(timeDiff, 1.0);
    }
    
    @Override
    public void paintFrame() {
        halloColor.r = (int) red.getLevel();
        halloColor.g = (int) green.getLevel();
        halloColor.b = (int) blue.getLevel();
        setColor(halloColor);
        // RENDERN
        if(halloSwitch.isOn()){
            drawString("Hello world!",
                    viewWidth()/2, // x-Position
                    viewHeight()/2, // y-Position
                    0); // 0 = zentriert, -1 = linksbuendig, 1 = rechtsbuendig
        }
    }
}
