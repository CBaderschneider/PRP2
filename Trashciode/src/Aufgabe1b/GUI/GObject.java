package Aufgabe1b.GUI;

import Aufgabe1b.Controler.GControl;
import Aufgabe1b.Controler.LevelControl;
import Aufgabe1b.Controler.SwitchControl;
import Aufgabe1b.GUI.BG.Background;
import Aufgabe1b.Physics.Particle;
import Aufgabe1b.Physics.ParticleInterface;
import Utils.MyUtils;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import jgame.JGColor;
import jgame.JGFont;
import jgame.JGObject;

public class GObject extends JGObject{
    private static ArrayList<GObject> _gObjects;
    private static GObject _focused;
    private ParticleInterface _particle;
    private GEngine _gEngine;
    private LevelControl _power;
    private LevelControl _break;
    private LevelControl _steering;
    private SwitchControl _abs;
    private SwitchControl _asr;
    private boolean _created = false;

    static {
        GObject._gObjects = new ArrayList<>();
    }

    public static GObject newGObject(ParticleInterface aParticle){
        GObject gObject = new GObject(aParticle);
        GObject.getGObjects().add(gObject);
        GObject._focused = gObject;
        return gObject;
    }

    public static ArrayList<GObject> getGObjects() {
        return GObject._gObjects;
    }

    public static GObject getFocused() {
        return GObject._focused;
    }

    public static void setFocused(GObject aFocused) {
        GObject._focused = aFocused;
    }

    private GObject(ParticleInterface aParticle) {
        super("Player",true,300,300,1,"Player90");
        _particle = aParticle;
        _gEngine = GControl.getInstance().getEngine();
        _power = new LevelControl(0.0,1.0,1.0,KeyEvent.VK_UP,null,true);
        _break = new LevelControl(0.0,1.0,1.0,KeyEvent.VK_SPACE,null,true);
        _steering = new LevelControl(-1.0,1.0,2.0,KeyEvent.VK_RIGHT,KeyEvent.VK_LEFT,false);
        _abs = new SwitchControl(KeyEvent.VK_Y);
        _asr = new SwitchControl(KeyEvent.VK_X);
        setCreated(true);
    }

    private ParticleInterface getParticle() {
        return this._particle;
    }

    public GEngine getGEngine() {
        return this._gEngine;
    }

    public LevelControl getPower() {
        return this._power;
    }

    public LevelControl getBreak() {
        return this._break;
    }

    public LevelControl getSteering() {
        return this._steering;
    }

    public SwitchControl getAbs() {
        return _abs;
    }

    public void setAbs(SwitchControl _abs) {
        this._abs = _abs;
    }

    public SwitchControl getAsr() {
        return _asr;
    }

    public void setAsr(SwitchControl _asr) {
        this._asr = _asr;
    }

    
    
    public boolean isCreated() {
        return _created;
    }

    public void setCreated(boolean _created) {
        this._created = _created;
    }

    public void setSprite(String aSprite) {
        throw new UnsupportedOperationException();
    }

    private void setGEngine(GEngine aGEngine) {
        this._gEngine = aGEngine;
    }

    private void setPower(LevelControl aPower) {
        this._power = aPower;
    }

    private void setBreak(LevelControl aBreak_15) {
        _break = aBreak_15;
    }

    private void setSteering(LevelControl aSteering) {
        this._steering = aSteering;
    }

    public boolean isFocused() {
        return this.equals(GObject.getFocused());
    }

    @Override
    public void move() {
        if (isCreated()){

            getParticle().step(getGEngine().getDeltaTime());
            this.x = getParticle().getPosX();
            this.y = getParticle().getPosY();
            setGraphic("Player" + MyUtils.MyMod((((int) ((getParticle().getPaintDirection()*(180/Math.PI)))) + 90), 360));
            getParticle().setTraction(Background.getBackgroundByPosition(
                    MyUtils.MyMod(((int) ((this.x)/16)),  GEngine.getInstance().pfTilesX()), 
                    MyUtils.MyMod(((int) ((this.y)/16)),  GEngine.getInstance().pfTilesY())
                    ).getBgtraction()); 
            if (isFocused()){
                getPower().step(getGEngine().getDeltaTime());
                getSteering().step(getGEngine().getDeltaTime());
                getBreak().step(getGEngine().getDeltaTime());
                if (!getParticle().isStartedUncontroled()){
                    getParticle().setPropLevel(getPower().getLevel());
                    getParticle().setSteeringLevel(getSteering().getLevel());
                    getParticle().setBreakLevel(getBreak().getLevel());
                }
                getGEngine().setViewOffset((int) x, (int) y, true);

                getAbs().step();
                getParticle().setAbsOn(getAbs().isOn());
                getAsr().step();
                getParticle().setAsrOn(getAsr().isOn());
            }
        }
    }
    @Override
    public void paint(){
        if (isFocused()){
            getGEngine().setColor(JGColor.white);
            DecimalFormat f = new DecimalFormat(" 000.00;-000.00");
            getGEngine().drawString("Speed: " + f.format(getParticle().getSpeed() * 3.6) + " km/h" , 10, 10, -1, new JGFont("Arial", 0, 20), JGColor.white);
            getGEngine().drawString("Traction: " + (getParticle().getTraction()) , 10, 30, -1, new JGFont("Arial", 0, 20), JGColor.white);
            getGEngine().drawString("ABS: " + (getParticle().isAbsOn()) , 10, 50, -1, new JGFont("Arial", 0, 20), JGColor.white);
            getGEngine().drawString("ASR: " + (getParticle().isAsrOn()) , 10, 70, -1, new JGFont("Arial", 0, 20), JGColor.white);
            getGEngine().drawString("UnControled: " + (getParticle().isStartedUncontroled()) , 10, 90, -1, new JGFont("Arial", 0, 20), JGColor.white);
            getGEngine().drawString("Power: " + f.format(getParticle().getPropLevel()), 10, 110, -1, new JGFont("Arial", 0, 20), JGColor.white);
            getGEngine().drawString("Break: " + f.format(getParticle().getBreakLevel()) , 10, 130, -1, new JGFont("Arial", 0, 20), JGColor.white);
            getGEngine().drawString("Steering: " + f.format(getParticle().getSteeringLevel()) , 10, 150, -1, new JGFont("Arial", 0, 20), JGColor.white);
        }
    }
   
}