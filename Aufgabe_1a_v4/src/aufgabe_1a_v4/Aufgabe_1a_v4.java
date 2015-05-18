package aufgabe_1a_v4;

import java.text.DecimalFormat;

public class Aufgabe_1a_v4 {

    public static void main(String[] args) {
      Porsche911GT2RS_v4 porsche = new Porsche911GT2RS_v4(1445.0, 456.0*1000.0, 330.0/3.6);
        
      // 100 SIMULATIONSSCHRITTE
//        for(int i=0; i<100; i++) {
//            porsche.step(0.1, 1.0);
//        System.out.println("TIME: " + f.format(porsche.getTime()) + "     " + "\tSPEED: " + g.format(porsche.getSpeed()) + "  (=" + g.format(3.6*porsche.getSpeed()) + " km/h)" + "\tPOS: " + g.format(porsche.getPos()) + "\tSTATE: " + porsche.getCurrState());//        }
      
      DecimalFormat f = new DecimalFormat("#0.0"); 
      DecimalFormat g = new DecimalFormat("#0.00"); 

//double d1 = 1234.4843;
//double d2 = 0.2;
//System.out.println(f.format(d1));
//System.out.println(f.format(d2));
      
      // stopped/driving
      for(int i=0; i<100; i++){
          porsche.step(0.1, i);
          System.out.println("TIME: " + f.format(porsche.getTime()) + "     " + "\tSPEED: " + g.format(porsche.getSpeed()) + "  (=" + g.format(3.6*porsche.getSpeed()) + " km/h)" + "\tPOS: " + g.format(porsche.getPos()) + "\tSTATE: " + porsche.getCurrState());
      }
      
      // 0 AUF 100
//        while (porsche.getSpeed() < 100/3.6){
//            porsche.step(0.1, 1.0);
//            System.out.println("TIME: " + f.format(porsche.getTime()) + "     " + "\tSPEED: " + g.format(porsche.getSpeed()) + "  (=" + g.format(3.6*porsche.getSpeed()) + " km/h)" + "\tPOS: " + g.format(porsche.getPos()) + "\tSTATE: " + porsche.getCurrState());
//        }
//        
        // AUSROLLEN
        while (!(porsche.getSpeed() == 0))
        {
            porsche.step(0.1,0.0);
            System.out.println("TIME: " + f.format(porsche.getTime()) + "     " + "\tSPEED: " + g.format(porsche.getSpeed()) + "  (=" + g.format(3.6*porsche.getSpeed()) + " km/h)" + "\tPOS: " + g.format(porsche.getPos()) + "\tSTATE: " + porsche.getCurrState());
        }

    }
    
}
