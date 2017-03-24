/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Explosions;

import Images.Anime;
import Images.LoadSpriteSheet;
import Time.Time;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author eze
 */

///parent class for all explosions
//they share the same design 
//different initliazation, image, and animation technqiues

public abstract class ShellExplosion 
{
    
    protected double x;
    protected double y;
    protected Anime Animation;
    protected LoadSpriteSheet SpriteSheet;
    protected BufferedImage bImage;
    protected Time duration;
    protected long expired;
   
    public abstract void initialize();
    public abstract void update();
    public abstract void render(Graphics2D g);
    public abstract boolean checkExplosion();
    public abstract BufferedImage returnImage();
    
    
}
