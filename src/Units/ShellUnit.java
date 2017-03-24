/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import Images.Anime;
import Images.LoadSpriteSheet;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author eze
 */

//parent class for all units
//have similar methods just each unit is a bit more unique than the other

public abstract class ShellUnit 
{
     
    protected double x;
    protected double y;
    protected Anime Animation;
    protected LoadSpriteSheet SpriteSheet;
    protected BufferedImage bImage;
   
    public abstract void initialize();
    public abstract void update();
    public abstract void render(Graphics2D g);
    public abstract BufferedImage returnImage();
    
    //controls the bounds of the object
    public abstract void Simulate();
    
    ///returns pixel parameters
    public abstract Rectangle returnPARAMZ();
    

}
