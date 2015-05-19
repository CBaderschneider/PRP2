/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aufgabe1b.Physics;
import Utils.MyUtils;
import java.util.ArrayList;

/**
 *
 * @author abq304
 */
public class Particle implements ParticleInterface{
    
    private static ArrayList<Particle> _particles;
    private static final double _accEarth = 9.81 ; //m/s
    private double _posX; //m
    private double _posY; //m
    private double _propLevel; 
    private double _breakLevel;
    private double _steeringLevel;
    private final double _mass; //kg
    private final double _powerPropMax; // W
    private final double _maxSpeed; // m/s
    private boolean _absOn;
    private boolean _asrOn;
    private double _traction;
    private double _speed; // m/s
    private double _direction = 0; // rad
    private double _paintDirection; // rad
    private double _time; // s
    private double _minKurvenRadius; //m
    
    private double _tractCoeff = 1; // sehr merkwürdig
    private double _maxAcc = _accEarth * _tractCoeff;
    private double _curveAcc = Math.pow(_speed, 2) / _minKurvenRadius;
    
    
    
    private boolean _startedUncontroled;
        public State mState = State.STANDING;

    @Override
    public void setDirection(double aDirection) {
       this._direction= aDirection;
    }
        
        
  


            
        
        
public enum State {  
    DRIVING{

        @Override
        State step(double aDeltaTime, Particle party) {
            party.straightForwardHandler(aDeltaTime);
            party._speed = party.speed(aDeltaTime);
            return State.DRIVING;
        }
    },
    
    ANFAHREN{
        @Override
            State step(double aDeltaTime, Particle party) {
                    party._speed = 1;
                    return State.DRIVING;
            }
        },
    
        /**
         *
         */
        STANDING{

        @Override
        State step(double aDeltaTime, Particle party) {
            if(party._propLevel > 0){
                return State.ANFAHREN;
            }
            return State.STANDING;
        }
    },
    UNCONTROLLED{

        @Override
        State step(double aDeltaTime, Particle party) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    },
    KURVE{

        @Override
        State step(double aDeltaTime, Particle party) {
          party.kreisfahrt(aDeltaTime);
          return State.KURVE;
        }
    };
    abstract State step(double aDeltaTime, Particle party);   
    }
    
       // static{
        //_accEarth = 9.81; //m/s
        
     //)}
        public Particle(double _mass, double _powerPropMax, double _maxSpeed,double minCircle) {
        this._mass = _mass;
        this._powerPropMax = _powerPropMax;
        this._maxSpeed = _maxSpeed;
        this._minKurvenRadius = minCircle;
    } 
        
        
        
 private void kreisfahrt(double aDeltaTime){
        double deltaPos = _speed * aDeltaTime;
        double deltaX = deltaPos + Math.sin(_direction);
        double deltaY = deltaPos * Math.cos(_direction);
     
     
        _direction = _direction + (deltaPos / (_minKurvenRadius / _steeringLevel));
        this.setPosX(_posX + deltaX);
        this.setPosY(_posY + deltaY);
 }
public static Particle newParticle(double mass, double speed, double watt, double circle){
    return new Particle(mass,speed,watt,circle);
}
        //System.out.println("c: " + c + " r: " + r + " xm: " + xm + " ym: " + ym + " xn: " + xn + " yn: " + yn + " g: " + gamma);
    
    private void straightForwardHandler(double aDeltaTime){
        this.setPosX(posX(aDeltaTime));
        this.setPosY(posY(aDeltaTime));
    }   
        public void setPosX(double aPosX) {
        this._posX = aPosX;
    }
    public void setPosY(double aPosY) {
        this._posY = aPosY;
    }
        /**
     * Errechneter XSpeed
     * @return - XSpeed in m/s
     */
    public double getSpeedX() {
        return Math.cos(getDirection()) * getSpeed();
    }
    /**
     * Errechneter YSpeed
     * @return -YSpeed in m/s
     */
    public double getSpeedY() {
        return Math.sin(getDirection()) * getSpeed();
    }
        private double posX(double deltaTime){
        return getPosX() + (getSpeedX() * deltaTime); //m
    }
    private double posY(double deltaTime){
        return getPosY() + (getSpeedY() * deltaTime); //m
    }
   private void setTime(double time){
       this._time = time;
   }
    
    @Override
    public void set(double aTime, double aPosX, double aPosY, double aSpeed, double aPropLevel){
        setTime(aTime); //s
        setPosX(aPosX); //m
        setPosY(aPosY); //m
        setSpeed(aSpeed);//m/s
        setPropLevel(aPropLevel);
    }

    @Override
        public double getPosX() {
    return this._posX;
    }
    @Override
    public double getPosY() {
    return this._posY;
    }
    @Override
    public double getPropLevel() {
    return this._propLevel;
    }
    @Override
    public double getBreakLevel() {
    return this._breakLevel;
    }
    @Override
    public double getSteeringLevel() {
    return this._steeringLevel;
    }
    public double getMass() {
    return _mass;
    }
    public double getPowerPropMax() {
    return this._powerPropMax;
    }
    public double getMaxSpeed() {
    return this._maxSpeed;
    }
    @Override
    public double getTraction() {
    return this._traction;
    }
    @Override
    public double getSpeed() {
    return this._speed;
    }
    @Override
    public double getDirection() {
    return this._direction;
    }
    @Override
    public double getPaintDirection() {
    return _paintDirection;
    }
    @Override
    public boolean isStartedUncontroled() {
    return _startedUncontroled;
    }
    private void setStartedUncontroled(boolean startedUncontroled) {
    this._startedUncontroled = startedUncontroled;
    }
    public void setPaintDirection(double _paintDirection) {
    _paintDirection = MyUtils.MyMod(_paintDirection, 2*Math.PI);
    this._paintDirection = _paintDirection;
    }
    /**
    * Errechneter XSpeed
    * @return - XSpeed in m/s
    */

    /**
    * Errechneter YSpeed
    * @return -YSpeed in m/s
    */

    @Override
    public boolean isAbsOn() {
    return this._absOn;
    }
    @Override
    public boolean isAsrOn() {
    return this._asrOn;
    }
    public double getMinKurvenRadius() {
    return _minKurvenRadius;
    }
    public void setMinKurvenRadius(double _minKurvenRadius) {
    this._minKurvenRadius = _minKurvenRadius;
    }
    @Override
    public void setPropLevel(double aPropLevel) {
    this._propLevel = aPropLevel;
    }
    @Override
    public void setBreakLevel(double aBreakLevel) {
    this._breakLevel = aBreakLevel;
    }
    @Override
    public void setSteeringLevel(double aSteeringLevel) {
    this._steeringLevel = aSteeringLevel;
    }
    @Override
    public void setAbsOn(boolean aAbsOn) {
    this._absOn = aAbsOn;
    }
    @Override
    public void setAsrOn(boolean aAsrOn) {
    this._asrOn = aAsrOn;
    }
    @Override
    public void setTraction(double aTraction) {
    this._traction = aTraction;
    }
    public void setSpeed(double aSpeed) {
    if (aSpeed < 0.0)
    aSpeed = 0.0;
    this._speed = aSpeed;
    }
    
//    @Override
//    public void setDirection(double aDirection) {
//    aDirection = MyUtils.MyMod(aDirection, 2*Math.PI);
//    if(!isUncontroled())
//    setPaintDirection(aDirection);
//    this._direction = aDirection;
//    }
    
    private double forcePropMax(boolean withTraction){
    if (withTraction) {
    return getMass() * Particle._accEarth * getTraction(); //N
    }
    else {
    return getMass() * Particle._accEarth; //N
    }
    }
    @Override
    public void step(double aDeltaTime) {
       System.out.println("State : " + mState.toString());
        System.out.println("Acc " + this.forceProp());
        System.out.println("_propLevel : " + _propLevel);
       mState = mState.step(aDeltaTime,this);

    }
    
    private double speed(double deltaTime){
                System.out.println("getSpeed() : " + getSpeed() * 3.6 );
                System.err.println("acc()" + acc());
                System.out.println("");
                System.out.println("acc * delta *3.6" + acc() * deltaTime * 3.6);
                
        return getSpeed() + (acc() * deltaTime); //m/s
    }
    private double acc(){
        return force()/getMass();//m/s**2
    }
    private double powerProp(){
        return getPropLevel() * getPowerPropMax(); //N
    }
    private double forceProp(){
        return forcePropAbs() * Math.signum(getPropLevel()); //N
    }
    private double dragConst(){
        return Math.abs(getPowerPropMax()/Math.pow(getMaxSpeed(),3)); //N/((m/s)**3)
    }
    private double forceDrag(){
        return dragConst() * Math.pow(getSpeed(),2) * Math.signum(-getSpeed()); //N
    }
    public double forceBreak() {
    System.out.println(getBreakLevel());
        return getBreakLevel() * Math.signum(-getSpeed()) * forcePropMax(isAbsOn());
    }
    private double force(){
        return forceProp() + forceDrag() + forceBreak(); //N
    }
    private double forcePropAbs(){
        if (getSpeed() == 0.0){ //m/s
        return forcePropMax(true); //N
    } else {
        return Math.min(forcePropMax(isAsrOn()),powerProp() / getSpeed()); //N
    }
    }
    
}