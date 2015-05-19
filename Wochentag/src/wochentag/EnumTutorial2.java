package wochentag;

import java.util.Locale;

public class EnumTutorial2 {
    /**
     * Nur in dieser Klasse und ihren Unterklassen nutzbares Enum.
     * @author Morph
     */
    private enum PrivateEnum {
        KONSTANTE_1,
        KONSTANTE_2,
        KONSTANTE_3;
    }
    /**
     * Nur von Klassen nutzbar, die sich entweder im selben Paket wie diese befinden 
     * oder von Unterklassen die von diesen Klassen erben.
     * @author Morph
     * 
     */
    protected enum ProtectedEnum {
        KONSTANTE_1,
        KONSTANTE_2,
        KONSTANTE_3;
    }
    
    /**
     * Dieses Enum ist immer und von allen Klassen nutzbar.
     * @author Morph
     * 
     */
    public enum PublicEnum {
        KONSTANTE_1,
        KONSTANTE_2,
        KONSTANTE_3;
    }
    
    /**
     * Dieses Enum verfügt über eine benutzerdefinierte Methode.
     * @author Morph
     * 
     */
    public enum ErweitertesEnum_1 {
        DEUTSCHLAND,
        FRANKREICH,
        KANADA;
        
        /**
         * Gibt das Kürzel für das Land als {@link String} zurück.
         * @return Kürzel für das Land
         */
        public String getISO3Country() {
            // Wir prüfen, welche Konstante des Enums genutzt wird
            // Siehe auch: Wochentag.java
            if (this == DEUTSCHLAND) {
                return Locale.GERMANY.getISO3Country();
            } else if (this == FRANKREICH) {
                return Locale.FRANCE.getISO3Country();
            } else {
                return Locale.CANADA.getISO3Country();
            }
        }
    }
    
    /** Dieses Enum verfügt über eine abstrakte, benutzerdefinierte Methode, 
     * welche von allen Konstanten implementiert werden muss.
     * @author Morph
     * 
     */
    public enum WAEHRUNGEN_ENUM {
        EURO() {
            @Override
            public double convertTo(WAEHRUNGEN_ENUM waehrung, double value) {
                return waehrung == EURO ? value : value / 0.768994156;
            }
        },
        DOLLAR() {
            @Override
            public double convertTo(WAEHRUNGEN_ENUM waehrung, double value) {
                return waehrung == DOLLAR ? value : value * 0.7768994156;
            }
        };
        public abstract double convertTo(WAEHRUNGEN_ENUM waehrung, double value);
    }
}

















