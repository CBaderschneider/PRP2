/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Aufgabe1b.GUI.BG;

import Aufgabe1b.GUI.GEngine;

/**
 *
 * @author abq202
 */
public class Background {
    private static Background[][] _background;
    private String _bgpicture;
    private double _bgtraction;
    private int x;
    private int y;
           
    static{
        Background._background = new Background[GEngine.getInstance().pfTilesX()][GEngine.getInstance().pfTilesY()];   
    }
    
    public static Background[][] getBackground() {
       return Background._background;
      
    }
    public static void setBackground(Background[][] aBackground) {
        Background._background = aBackground;
    }
    public static void setBackgroundByPosition(Background aBackground, int x, int y) {
        Background._background[x][y] = aBackground;   
    }
    
    public static Background getBackgroundByPosition(int x, int y){
        //System.out.println(x + " " + y);
        return Background.getBackground()[x][y];
    }
    
    public static void generateBackground(){
        int pftilesX = GEngine.getInstance().pfTilesX();
        int pftilesy = GEngine.getInstance().pfTilesY();
        for (int x = 0; x<pftilesX;x++){
            for (int y = 0; y<pftilesy;y++){
                if (y<pftilesy/2){
                    if (x < pftilesX / 4)
                        new Street(x, y);
                    else if(x < (pftilesX / 4) * 2)
                        new Ice(x, y);
                    else if (x < (pftilesX / 4) * 3)
                        new Wet(x, y);
                    else
                        new Snow(x, y); 
                }else{
                    if (x < pftilesX / 4)
                        new Ice(x, y);
                    else if(x < (pftilesX / 4) * 2)
                        new Wet(x, y);
                    else if (x < (pftilesX / 4) * 3)
                        new Snow(x, y);
                    else
                        new Street(x, y);
                }
            }
        }
    }
    
    public Background(String abgpicture, double abgtraction, int ax, int ay){
        this._bgtraction = abgtraction;
        this.x = ax;
        this.y = ay;
        setBgpicture(abgpicture);
        Background.setBackgroundByPosition(this, x, y);
    }
    
    public String getBgpicture() {
        return _bgpicture;
    }

    private void setBgpicture(String abgpicture) {
        this._bgpicture = abgpicture;
        GEngine.getInstance().setTile(x, y, abgpicture);
        
    }

    public double getBgtraction() {
        return _bgtraction;
    }

    private void setBgtraction(double abgtraction) {
        this._bgtraction = abgtraction;
    }
}
