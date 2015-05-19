package enums;

public enum CarState {
    
    RUTSCHEND {
        @Override
        public CarState step(){
            return CarState.FAHREND;
        }
    },
    FAHREND{
        @Override
        public CarState step(){
            if (getGeschwindigkeit()<=0.0)
                return CarState.FAHREND;
            
            return CarState.FAHREND;
        }
    },
    SUEDEN{
        @Override
        public CarState step(){
            return CarState.WESTEN;
        }
    },
    WESTEN{
        @Override
        public CarState step(){
            return CarState.SUEDEN;
        }
    };
    
    private double geschwindigkeit;     // setter private oder ganz raus

    public static CarState getRUTSCHEND() {
        return RUTSCHEND;
    }

    public static CarState getFAHREND() {
        return FAHREND;
    }

    public static CarState getSUEDEN() {
        return SUEDEN;
    }

    public static CarState getWESTEN() {
        return WESTEN;
    }

    public double getGeschwindigkeit() {
        return geschwindigkeit;
    }
    public abstract CarState step();
}







// in particle main       CarState state...

