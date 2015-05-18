
package jgame2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andreas
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
		// Annonymes Object, nur zur Demo
        ParticleInterface particle = new ParticleInterface() {
            double x = 0.0;
            double y = 0.0;
            @Override
            public double getPosX() {
                return x;
            }

            @Override
            public double getPosY() {
                return y;
            }

            @Override
            public void step(double aDeltaTime) {
                x = x + (3*aDeltaTime);
                y = y + (3*aDeltaTime);
            }
        };
        Window window = Window.valueOf(800, 600, particle);
    }
    
}
