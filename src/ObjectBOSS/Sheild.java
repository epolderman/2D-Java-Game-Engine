/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ObjectBOSS;

import Images.Anime;
import Images.LoadImage;
import Images.LoadSpriteSheet;
import Time.Time;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author eze
 */
public class Sheild 
{
    private double x;
    private double y;
    private Anime generate;
    private LoadSpriteSheet needya;
    private BufferedImage jupiterAscention;
    private BufferedImage[] attention = new BufferedImage[1];
    private Random rand = new Random();
    private double duration;
    private Time energy = new Time();
    //private Boolean turnoff = false;
    private double expired;
    private Boolean moveit = false;
    ///random generator to determine when the boss's sheild is up
    ///shoot bullets to the right and to the left of the player to anticipate the movements
    ///draw shield sprite
    ///collision detection and exploision of arms and boss
    ///
    
    public Sheild(double x, double y)
    {   
        this.x = x;
        this.y = y;
        initialize();
        generate = new Anime(attention[0], 10);
        double lower = 1;
        double upper = 10;
        duration = (Math.random() * (upper - lower) + lower) * 1000;
        System.out.println("Main Sheild Generated!"); 
        System.out.println("Duration for this is sheild is " + duration);
        
        //energy = new Time();
        ///start the energy time it has
        energy.startM();
        
    
    
    }
    private void initialize()
    {
        try
        {   LoadImage loader = new LoadImage();
            jupiterAscention = loader.LoadImage("/Resources/BossSnow.png");
            needya = new LoadSpriteSheet(this.yeaboi());
            
            attention[0] = needya.grabSprite256(3, 1, 256, 256);
            //attention[1] = needya.grabSprite256(2, 3, 256, 256);
            //attention[2] = needya.grabSprite256(3, 2, 256, 256);
            
            
        
        }
        catch(Exception e)
        {
            e.printStackTrace();
        
        
        }
    
    
    
    }
    public void update()
    {   
        
        x += 0;
        y += 0;
        
        
       
        //System.out.println("Energy time = " + expired);
        generate.Animate();
        
    }
    public void controlBOUND()
    {
        if(y >= -95)
            y = -95;
        
        
    
    }
    public Boolean destroySHEILD()
    {   
        return moveit;
           
    }
    public Time returnEnergy()
    {   
        return energy;
    }
    public void render(Graphics2D g)
    {
        
        generate.constructIT(g, x, y);
    
    }
    public BufferedImage yeaboi()
    {
        return jupiterAscention;
    
    }
    public double getX()
    {
        return x;
    }
    public double getY()
    {
        return y;
    }
    public void setX(double xx)
    {
        x = xx;
    }
    public void setY(double yy)
    {
        y = yy;
    }
     public Rectangle returnPARAMZ()
    {
         return new Rectangle ((int)x,(int)y, 256, 256);
    
    }
     public Boolean returnMoveit()
     {
         return moveit;
     }
     public void setMove(Boolean m)
     {
         this.moveit = m;
     }
    
    
}
