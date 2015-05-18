
package javaapplication14;

public class Timer {
    private long timeLast;
    private double timeElapsed;

    private Timer() {
        timeLast = System.currentTimeMillis();
        timeElapsed = 0.0;
    }
    
    public static Timer valueOf() {
        return new Timer();
    }

    private long getTimeLast() {
        return timeLast;
    }

    private void setTimeLast(long timeLast) {
        this.timeLast = timeLast;
    }

    public double getTimeElapsed() {
        return timeElapsed;
    }

    private void setTimeElapsed(double timeElapsed) {
        this.timeElapsed = timeElapsed;
    }
    
    public double update() {
        long timeCurrent = System.currentTimeMillis();
        double diff = (timeCurrent - getTimeLast())/1000.0;
        
        setTimeElapsed(diff);
        setTimeLast(timeCurrent);
        
        return diff;
    }
}
