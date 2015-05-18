package Aufgabe1b.Physics;

import Aufgabe1b.GUI.GEngine;
import Utils.MyUtils;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import jgame.JGColor;

public class Particle implements ParticleInterface {
    private static ArrayList<Particle> _particles;
    private static final double _accEarth; //m/s
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
    private double _direction; // rad
    private double _paintDirection; // rad
    private double _time; // s
    private double _minKurvenRadius; //m
    private boolean _startedUncontroled;

    static {
        _accEarth = 9.81; //m/s
        _particles = new ArrayList<>();
    }
    
    /**
     * Factory Method für die Partikel
     * @param aMass - Masse des Partikels in Kg
     * @param aMaxSpeed - Max Speed des Partikels im m/s
     * @param aPowerPropMax - Maximale Antriebsleistung in W
     * @param aMinKurvenRadius - Minimaler Kurvenradius in m
     * @return Particle
     */
    public static Particle newParticle(double aMass, double aMaxSpeed, double aPowerPropMax, double aMinKurvenRadius) {
        Particle particle = new Particle(aMass, aMaxSpeed, aPowerPropMax, aMinKurvenRadius);
        Particle.getParticles().add(particle);
        return particle;
    }

    public static ArrayList<Particle> getParticles() {
        return Particle._particles;
    }
    public static double getAccEarth(){
        return Particle._accEarth; // m/s
    }
    /**
     * Standart Konstruktor
     * @param aMass - Masse des Partikels in Kg
     * @param aMaxSpeed - Max Speed des Partikels im m/s
     * @param aPowerPropMax - Maximale Antriebsleistung in W 
     */
    private Particle(double aMass, double aMaxSpeed, double aPowerPropMax, double aMinKurvenRadius) {
        _mass = aMass;
        _maxSpeed = aMaxSpeed;
        _powerPropMax = aPowerPropMax;
        _absOn = true;
        _asrOn = true;
        _minKurvenRadius = aMinKurvenRadius;
        setDirection(0.0);
        setTraction(1.0);
    }

    public double getTime() {
        return _time;
    }

    public void setTime(double _time) {
        this._time = _time;
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

    public void setPosX(double aPosX) {
        this._posX = aPosX;
    }

    public void setPosY(double aPosY) {
        this._posY = aPosY;
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

    @Override
    public void setDirection(double aDirection) {
        aDirection = MyUtils.MyMod(aDirection, 2*Math.PI);
        if(!isUncontroled())
            setPaintDirection(aDirection);
        this._direction = aDirection;
    }

    private double forcePropMax(boolean withTraction){
        if (withTraction) {
            return getMass() * Particle.getAccEarth() * getTraction(); //N
        }
        else {
            return getMass() * Particle.getAccEarth(); //N
        }
    }
    private double forcePropAbs(){
        if (getSpeed() == 0.0){ //m/s
            return forcePropMax(true); //N
        } else {    
            return Math.min(forcePropMax(isAsrOn()),powerProp() / getSpeed()); //N 
        }
    }
    @Override
    public boolean isUncontroled(){
        return Math.abs(forcePropAbs()) > forcePropMax(true) ||
               Math.abs(forceBreak()) > forcePropMax(true);
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
        //System.out.println(getBreakLevel());
        return getBreakLevel() * Math.signum(-getSpeed()) * forcePropMax(isAbsOn());
    }
    private double force(){
        return forceProp() + forceDrag() + forceBreak(); //N
    }
    private double acc(){
        return force()/getMass();//m/s**2
        
    }
    private double speed(double deltaTime){
        return getSpeed() + (acc() * deltaTime); //m/s
    }
    private double posX(double deltaTime){
        return getPosX() + (getSpeedX() * deltaTime); //m
    }
    private double posY(double deltaTime){
        return getPosY() + (getSpeedY() * deltaTime); //m
    }
    private void uncontroledHandler(double aDeltaTime){
        setAbsOn(true);
        setPropLevel(0.0);
        setBreakLevel(1.0);
        setPaintDirection(getPaintDirection() + ((0.5 * Math.PI) * aDeltaTime));
        straightForwardHandler(aDeltaTime);
        if(getSpeed() < 2.0){
            setDirection(getPaintDirection());
            setStartedUncontroled(false);
        }
    }
    private void straightForwardHandler(double aDeltaTime){
        this.setPosX(posX(aDeltaTime));
        this.setPosY(posY(aDeltaTime));
    }
    private void kreisfahrt(double aDeltaTime){
        double r = getMinKurvenRadius() / getSteeringLevel();
        double normalenWinkel = getDirection() + (0.5 * Math.PI);
        double x = getPosX();
        double y = getPosY();
        double xm = x + (Math.cos(normalenWinkel) * r);
        double ym = y + (Math.sin(normalenWinkel) * r);
        double gamma = (getSpeed() * aDeltaTime)/r;
        //x' = (x-u) cos(beta) - (y-v) sin(beta) + u
        //y' = (x-u) sin(beta) + (y-v) cos(beta) + v 
        double xn = (((x - xm) * Math.cos(gamma)) - ((y - ym) * Math.sin(gamma))) + xm;
        double yn = (((x - xm) * Math.sin(gamma)) + ((y - ym) * Math.cos(gamma))) + ym;
        //System.out.println(Math.abs(getMass() * ((getSpeed() * getSpeed())/r)));
        boolean c = Math.abs(getMass() * ((getSpeed() * getSpeed())/r)) < forcePropMax(true);
        setStartedUncontroled(!c);
        setPosX(xn);
        setPosY(yn);
        setDirection(getDirection() + gamma);
        //System.out.println("c: " + c + " r: " + r + " xm: " + xm + " ym: " + ym + " xn: " + xn + " yn: " + yn + " g: " + gamma);
    }
    private double time(double deltaTime){
        return getTime() + deltaTime; //s
    }
    
    @Override
    public void set(double aTime, double aPosX, double aPosY, double aSpeed, double aPropLevel){
        setTime(aTime); //s
        setPosX(aPosX); //m
        setPosY(aPosY); //m
        setSpeed(aSpeed);//m/s
        setPropLevel(aPropLevel);
    }
    
    public void reset(){
        this.set(0.0,0.0,0.0,0.0,0.0);
    }
    /**
     * Step Method sollte bei jedem durchlauf gerufen werden
     * @param aDeltaTime - Zeit seit der letzten Ausführung
     */
    @Override
    public void step(double aDeltaTime) {
        //System.out.println("Dir: " + getDirection() + " AsrOn: " + isAsrOn() + " AbsOn: " + isAbsOn() + " uncontroled: " + isUncontroled() + " force: " + force() + " ForceBreak: " + forceBreak() + " ForceProp: " + forceProp() + " ForceDrag: " + forceDrag() + " Speed: " + getSpeed() * 3.6 + " PowerLevel: " + getPropLevel() + " BreakLevel: " + getBreakLevel());
        this.setSpeed(speed(aDeltaTime));
        if (!isStartedUncontroled()){
            setStartedUncontroled(isUncontroled());
        }
        if (isStartedUncontroled()){
            uncontroledHandler(aDeltaTime);
        }else if(!(-0.02 < getSteeringLevel() && getSteeringLevel() < 0.02)){
            kreisfahrt(aDeltaTime);
        }else{
            straightForwardHandler(aDeltaTime);
        }
        this.setTime(time(aDeltaTime));
    }   
}