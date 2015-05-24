
package wochentag;

public class EnumTutorial {
    public static void main(String[] args) {
        EWochentag wt = EWochentag.FREITAG;
        
        String name1 = wt.name();           // Nicht überschriebbar
        // String name1 = EWochentag.FREITAG.name();
        
        String name2 = wt.toString(); //Überschreibbar (Siehe toString Tutorial)
        // String name2 = EWochentag.FREITAG.toString();
        
        EWochentag name3 = EWochentag.valueOf("FREITAG");
        
        EWochentag name4 = Enum.valueOf(EWochentag.class, "FREITAG");
        
        //Geben wir uns nochmal die Namen aus ...
        System.out.printf("Name 1: %s\n", name1);
        System.out.printf("Name 2: %s\n", name2);
        System.out.printf("Name 3: %s\n", name3);
        System.out.printf("Name 4: %s\n", name4);
        
        // Wochentage aufzählen
        // for (EWochentag tag: EWochentag.class.getEnumConstants()){
        for (EWochentag tag : EWochentag.values()) {
            System.out.printf("Tag: %s\t(Ordinal: %d)\n", tag.name(), tag.ordinal());
        }
        
        // Kleiner Test ;)
        EWochentag heute = EWochentag.MITTWOCH;
        EWochentag beginn =EWochentag.MONTAG;
        
        System.out.printf("Die Woche beginnt immer am %s, heute ist %s. Die Woche hat vor %d Tagen begonnen!\n", beginn.name(), heute.name(), heute.compareTo(beginn));
        
        
    }
    
}
