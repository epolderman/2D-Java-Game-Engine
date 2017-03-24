/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import Images.Anime;
import Images.LoadImage;
import Images.LoadSpriteSheet;
import Time.Time;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author eze
 */

///Description: main player in single player -> a.k.a. Sonya Peirce
//has two different rendering animations
//one for boosted spaceship -> x and y varaibles are increased
//other is for regular

public class SwordFish extends ShellUnit
{   
    ///instance variables
    
    ///regular image array
    private BufferedImage[] bimageArray = new BufferedImage[3];
    //for boost animation
    private BufferedImage[] swordfishBOOST = new BufferedImage[3];
    private Anime animeBOOST;
    public Boolean boosted;
    private Boolean simulate;
    private Time milkshakes;
    private long puppies;
    private Boolean there;
    
    ///life mechanics
    private int hitpoints;
    private boolean dead;
    private String level;
    
    //move mechanics
    private double dx;
    private double dy;
    
    
    public SwordFish(double x, double y, String level)
    {
    this.x = x;
    this.y = y;
    System.out.println("SwordFish created at " + x + " , " + y);
    this.puppies = 0;
    this.boosted = false;
    this.simulate = false;
    this.dead = false;
    this.there = false;
    initialize();
    Animation = new Anime(bimageArray[0],bimageArray[1],bimageArray[2], 10);
    animeBOOST = new Anime(swordfishBOOST[0],swordfishBOOST[1],swordfishBOOST[2], 10);
    milkshakes = new Time();
    milkshakes.startM();
    this.level = level;
    getDifficulty();
    
    }
    
    @Override
    public void initialize()
    {
     try
        {   ////load sprite image in and grab the subimage and set the animation
            LoadImage loader = new LoadImage();
            bImage = loader.LoadImage("/Resources/SF.png");
            SpriteSheet = new LoadSpriteSheet(this.returnImage());
            bimageArray[0] = SpriteSheet.grabSprite128(1,2,128,128);
            bimageArray[1] = SpriteSheet.grabSprite128(2,2,128,128);
            bimageArray[2] = SpriteSheet.grabSprite128(3,2,128,128); 
            swordfishBOOST[0] = SpriteSheet.grabSprite128(1,3,128,128);
            swordfishBOOST[1] = SpriteSheet.grabSprite128(2,3,128,128);
            swordfishBOOST[2] = SpriteSheet.grabSprite128(3,3,128,128);
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    
    
    }
   
    @Override
    public void render(Graphics2D g)
    {
        if(boosted == false)
        {
        Animation.constructIT(g, x, y);
        }
        if(boosted == true)
        {
        animeBOOST.constructIT(g, x, y);
        }
    
    
    }
    @Override
    public BufferedImage returnImage()
    {
    return bImage;
    }
       public void control()
    {
        ////control the bounds of the swordfish
       if(simulate == true)
     {    
        if(x <= -15)
            x = -15;
        if(x >= 680)
            x = 680;
        if(y <= 0)
         y = 0;
        if(y >= 585)
            y = 585;
      }

    }
    @Override
    public void Simulate()
    {
    
       milkshakes.stopM();
       puppies = milkshakes.getDurationM();
       milkshakes.resetStopM();
       
        if(puppies <= 11000 && simulate == false && there == false)
        {
            
        x += 0;
        y -= .8; 
            
            if(y <= 270)
            {
                y = 270;
            }
        }
        
        else if(puppies > 11000 && there == false)
        {
            x += 0;
            y -= 5;
            
                if(y <= 75)
                {   
                    y = 75;
                    there = true;
                    
                   
                    
                }
                
            
        }
        
        
        
        
        
       if(there == true && simulate == false)
           {  
               y += .6;
              
               if(y >= 350)
               {
                   y = 350;
                   simulate = true;
               }
              
           }
       
       
           
    }
    
    @Override
    public Rectangle returnPARAMZ()
    {  
     return new Rectangle ((int)x,(int)y, 85, 85);

    }
     private void getDifficulty()
    {
         switch (level) 
         {
            case "Easy":
                hitpoints = 1500;
                break;
            case "Medium":
                hitpoints = 500;
                break;
            case "Hardened":
                hitpoints = 250;
                break;
        }
        System.out.println("Hitpoints for Swordfish " + hitpoints);
    
    }
  
      @Override
    public void update()
    {
        Simulate();
        x += dx;
        y += dy;
        control();
        
      
        
        if(boosted == false)
        {
            Animation.Animate();
        }
        if(boosted == true)
        {
            animeBOOST.Animate();
        }
    
    
    
    }
     
      public void setBOOST(int b)
    {
        if(b == 1)
        {
        boosted = true;
        }
        else
        {
        boosted = false;
        }
        
    }
      
       public double getDX()
    {
        return dx;
    }
    public double getDY()
    {
        return dy;
    }
    public double getX()
    {
        return x;
    }
    public double getY()
    {
        return y;
    }
    public void setX(double x)
    {
        this.x = x;
    }
    public void setY(double y)
    {
        this.y = y;
    }
    public void checkHITS(int value)
    {
        hitpoints -= value;
        if(hitpoints <= 0)
        {
            dead = true;
        }
    }
    public Boolean returnDEAD()
    {
        return dead;
    
    }
    public int returnHITS()
    {   
        return hitpoints;
        
        
    }
     public void setDX(double dx)
    {
        this.dx = dx;
    }
    public void setDY(double dy)
    {
        this.dy = dy;
    
    }
    
    
    
    
    
}
