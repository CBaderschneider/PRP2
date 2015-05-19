/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Utils;

/**
 *
 * @author ClausTorben
 */
public final class MyUtils {
    private MyUtils(){};
    public static int MyMod(int aBase, int aMod){
        return (int) MyMod((double) aBase, (double) aMod);
    }
    public static double MyMod(double aBase, double aMod){
        double r = aBase % aMod;
        if (r < 0)
        {
            r += aMod;
        }
        return r;
    }
}
