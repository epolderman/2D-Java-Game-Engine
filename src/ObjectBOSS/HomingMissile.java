/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ObjectBOSS;

import Audio.Audio;
import Images.Anime;
import Images.LoadImage;
import Images.LoadSpriteSheet;
import Units.SwordFish;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author eze
 */
public class HomingMissile 
{
    private double x;
    private double y;
    private Anime HM;
    private BufferedImage WV;
    private BufferedImage[] esentrik = new BufferedImage[3];
    private LoadSpriteSheet comesaround;
    private Random rand = new Random();
    private double movementx = 0;
    private double movementy = 0;
    private SwordFish dd;
    private Vicious xx;
    private Audio Shot;
    
    public HomingMissile(double x, double y)
    {
        
    this.x = x;
    this.y = y;
    initialize();
    HM = new Anime(esentrik[0],esentrik[1],esentrik[2], 5);
    System.out.println("Homing Missile Generated");
    double lower = 3;
    double upper = 7;
    movementx = Math.random() * (upper - lower) + lower;
    movementy = Math.random() * (upper - lower) + lower;
    Shot = new Audio("/Music/bossSHOT.wav");
    Shot.play();
    
    }
    private void initialize()
    {
        try{
                LoadImage loader = new LoadImage();
                WV = loader.LoadImage("/Resources/Sprinkle.png");
                comesaround = new LoadSpriteSheet(this.returndatDOUGH());
                
                esentrik[0] = comesaround.grabSprite64(1, 3, 64, 64);
                esentrik[1] = comesaround.grabSprite64(2, 3, 64, 64);
                esentrik[2] = comesaround.grabSprite64(3, 3, 64, 64);
                
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    
    }
    public void render(Graphics2D g)
    {
        HM.constructIT(g, x, y);
    }
    public void update(SwordFish dd, Vicious xx)
    {
        y += movementy;
        
        if(dd.getX() > xx.getX())
        {
        x += .7;
        }
        else if(dd.getX() < xx.getX())
        {
        x -= .7;    
        }
        else if(dd.getX() > xx.getX() + 75)
        {
        y += 2;
        x += 4;
        }
        else if(dd.getX() < xx.getX() - 75)
        {
        y += 2;
        x -= 4;
        }
       
        
        HM.Animate();
    
    }
    public BufferedImage returndatDOUGH()
    {
        return WV;
    }
    public double returnX()
    {
        return x;
    }
    public double returnY()
    {
        return y;
    }
    public Rectangle returnPARAMZ()
    {
        return new Rectangle((int)x,(int)y, 64,64);
    }
    
    
    
}
