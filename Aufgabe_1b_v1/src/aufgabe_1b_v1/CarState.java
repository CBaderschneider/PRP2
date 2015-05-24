
package aufgabe_1b_v1;

public enum CarState {
//    Auto als Argument in CarState step() übergeben
//    CarState.STEHEND.name(); CarState.STEHEND.ordinal(); CarState.values(); 
//    for (CarState state : CarState.values()){...}
    
    STEHEND  {
        @Override
        public CarState step(double deltaTime, double level) {
//            if (level <= 0.0 && getSpeed <= epsilon)
            return CarState.STEHEND;
        }
        
    },
    
    FAHREND {
        @Override
        public CarState step(double deltaTime, double level){
            return CarState.FAHREND;
        }
    };

    public abstract CarState step(double deltaTime, double level);
    
    
    public static CarState getSTEHEND() {return STEHEND;}
    public static CarState getFAHREND() {return FAHREND;}
    
}
