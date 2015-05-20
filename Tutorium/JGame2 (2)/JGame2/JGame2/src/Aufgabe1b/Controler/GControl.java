package Aufgabe1b.Controler;

import Aufgabe1b.GUI.BG.Background;
import Aufgabe1b.GUI.GEngine;
import Aufgabe1b.GUI.GObject;
import Aufgabe1b.Physics.Particle;
import java.awt.event.KeyEvent;

public class GControl{
    private static GControl _instance;
    private GEngine _engine;
    private boolean createdCar;
    private boolean tabed;

    public static void startGame() {
        GControl.getInstance(); 
        GControl.getInstance().loadSprites();
        Background.generateBackground();
    }

    public static GControl getInstance() {
        if (_instance == null)
            _instance = new GControl();
        return GControl._instance;
    }

    private GControl() {
        _engine = GEngine.getInstance();
    }

    public GEngine getEngine() {
        return this._engine;
    }

    private boolean isCreatedCar() {
        return createdCar;
    }

    public boolean isTabed() {
        return tabed;
    }

    public void setTabed(boolean tabed) {
        this.tabed = tabed;
    }

    private void setCreatedCar(boolean createdCar) {
        this.createdCar = createdCar;
    }

    private void loadSprites(){
        for (int i = 0; i<360; i++){
         
            getEngine().defineImage("Player" + i, "p", 0, "./car_sprite/" + i + ".png", "-");
        }
        getEngine().defineImage("ice" , "i", 0, "./backgroud_sprite/ice.png", "-");
        getEngine().defineImage("wet" , "w", 0, "./backgroud_sprite/wet.png", "-");
        getEngine().defineImage("street" , "s", 0, "./backgroud_sprite/street.png", "-");
        getEngine().defineImage("snow" , "sn", 0, "./backgroud_sprite/snow.png", "-");
    }

    public void step(){
        if (!isCreatedCar() && getEngine().getKey(KeyEvent.VK_N)){
            GObject.newGObject(Particle.newParticle(1445.0,456000.0,330/3.6,20.0));
            setCreatedCar(true);
        }else if (!getEngine().getKey(KeyEvent.VK_N)){
            setCreatedCar(false);
        }    
        if (!isTabed() && getEngine().getKey(KeyEvent.VK_A) && GObject.getGObjects().size()>0){
            int focusedGObject = GObject.getGObjects().indexOf(GObject.getFocused());
            GObject.setFocused(GObject.getGObjects().get((focusedGObject + 1) % GObject.getGObjects().size()));
            setTabed(true);
        }else if (!getEngine().getKey(KeyEvent.VK_A)){
            setTabed(false);
        }    
    }
}