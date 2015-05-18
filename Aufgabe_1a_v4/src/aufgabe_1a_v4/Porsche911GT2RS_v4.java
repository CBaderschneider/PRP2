package aufgabe_1a_v4;

public class Porsche911GT2RS_v4 {
    // Konstanten
    static protected final double ACC_EARTH = 9.81;  //[m/s**2]
    static protected final int MIN_IN_SEK = 60;      //check Konventionen für Konstanten
    static protected final int HOUR_IN_MIN = 60;
    static protected final int HOUR_IN_SEK = HOUR_IN_MIN * MIN_IN_SEK;
    static protected final int KM_IN_M = 1000;
    static protected final double MPS_IN_KMH_COEFF = HOUR_IN_SEK / KM_IN_M;
   
    // Instanzvariablen
    private final double mass;                      // Masse in kg
    private final double powerPropMax;              // Maximalleistung in Watt
    private final double speedMax;                  // Höchstgeschwindigkeit in m/s
    private final double epsilon = 0.9;             // Epsilon
    
    // Attribute
    private double time;
    private double pos;
    private double speed;
    private double proplevel;
    private carState currState;

    // Konstruktor
    public Porsche911GT2RS_v4(double mass, double powerPropMax, double speedMax) {
        this.mass = mass;
        this.powerPropMax = powerPropMax;
        this.speedMax = speedMax;
        reset();                    // um die Attribute mit Standardwerten zu befüllen
    }
    
// Purpose:     Setzt Werte für "time", "pos", "speed" und "proplevel".     
    public void set(double time, double pos, double speed, double proplevel, carState currState) {
        this.time = time;
        this.pos = pos;
        this.speed = speed;
        this.proplevel = proplevel;
        this.currState = currState;
    }
    
// Purpose:     Setzt alle Werte wieder auf 0.
    public void reset() {
        set(0.0, 0.0, 0.000, 0.0, carState.Stopped);    
    }
    
    public enum carState{Stopped, Driving};    

// Purpose:     Liefert neue Werte für "time", "pos", "speed" und "proplevel".
    public void step(double deltaTime, double level) {
        // ... (FORMELN)
          if (level <= 0.0 && getSpeed() <= epsilon){
              set(newTime(getTime(),deltaTime), newPos(deltaTime), 0.0, level, carState.Stopped);
          }  else {
              set(newTime(getTime(),deltaTime), newPos(deltaTime), newSpeed(deltaTime,level), level, carState.Driving);
          }
        
    }
    
    public double newTime(double time, double delta){
        return time + delta;
    }
    public double newPos(double deltaTime){
        return getPos() + (getSpeed()*deltaTime);
    }
    public double newSpeed(double deltaTime, double level){
        return getSpeed() + (acc(level)*deltaTime);
    }
    public double acc(double level){
        return force(level)/getMass();
    }
    public double force(double level){
        return forceProp(level) + forceDrag();
    }
    public double forceDrag(){
        return (dragConst() * Math.pow(speed, 2)) * Math.signum(speed*(-1));
    }
    public double dragConst(){
        return Math.abs(getPowerPropMax() / Math.pow(getSpeedMax(), 3));
    }
    public double forceProp(double level){
        return forcePropAbs(level) * Math.signum(level);
    }
    public double forcePropAbs(double level){
        if (currState == carState.Driving) return Math.min(forcePropMax(), (powerProp(level)/getSpeed()));
        else return forcePropMax();
    }
    public double forcePropMax(){
        return getMass() * ACC_EARTH;
    }
    public double powerProp(double level){
        return level * getPowerPropMax();
    }

// Setter
    public void setTime(double time) {this.time = time;}
    public void setPos(double pos) {this.pos = pos;}
    public void setSpeed(double speed) {this.speed = speed;}
    public void setProplevel(double proplevel) {this.proplevel = proplevel;} 
// Getter
    public double getMass() {return mass;}
    public double getPowerPropMax() {return powerPropMax;}
    public double getSpeedMax() {return speedMax;}
    public double getTime() {return time;}
    public double getPos() {return pos;}
    public double getSpeed() {return speed;}
    public double getProplevel() {return proplevel;}
    public static double getACC_EARTH() {return ACC_EARTH;}
    public static int getMIN_IN_SEK() {return MIN_IN_SEK;}
    public static int getHOUR_IN_MIN() {return HOUR_IN_MIN;}
    public static int getHOUR_IN_SEK() {return HOUR_IN_SEK;}
    public static int getKM_IN_M() {return KM_IN_M;}
    public static double getMPS_IN_KMH_COEFF() {return MPS_IN_KMH_COEFF;}
    public double getAccEarth() {return ACC_EARTH;}  
    public double getEpsilon() {return epsilon;}
    public carState getCurrState() {return currState;}
    
   
    
// enums in die klasse oder in extra datei mit import
//    public enum carState{Stopped, Driving,...};
//    carState currState = carState.Stopped;
//    if currState = carState.Stopped...
    
}























