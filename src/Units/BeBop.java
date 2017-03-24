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
///Description: BeBop is the main shuttle in which the SwordFish is transported on
///Does not do any damage nor take any


public class BeBop extends ShellUnit
{
    ///instance variables
     private Time bebopz;
     private Boolean simulate;
     private long orange;
     private BufferedImage[] bimageArray = new BufferedImage[1];
    
    public BeBop(double x, double y)
    {
        
           this.x = x;
           this.y = y;
           initialize();
           System.out.println("Bebopp Created");
           Animation = new Anime( bimageArray[0], 9);
           bebopz = new Time();
           bebopz.startM();
           this.simulate = false;
           this.orange = 0;
    
    
    
    }
    
     public void initialize()
    {  
        try
        {    
            LoadImage loader = new LoadImage();
            bImage = loader.LoadImage("/Resources/Boss2.png");
            SpriteSheet = new LoadSpriteSheet(this.returnImage());
            
            bimageArray[0] = SpriteSheet.grabSprite256(1, 3, 256, 256);
            //bimageArray[1] = SpriteSheet.grabSprite256(2, 3, 256, 256);
            //bimageArray[2] = SpriteSheet.grabSprite256(3, 3, 256, 256);
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    
    }
    @Override
    public Rectangle returnPARAMZ()
    {  
     return new Rectangle((int)x,(int)y, 256,256);

    }
     
    @Override
    public void Simulate()
    {   
        bebopz.stopM();
        orange = bebopz.getDurationM();
        bebopz.resetStopM();
        
        
        
        if(y <= 275 && orange <= 12000)
        {   
            y = 275;
        }
        else if(orange > 12000)
        {
            simulate = true;
        }
    
    }
     @Override
    public void update()
    {   
        
        if(simulate == false)
        {
        x += 0;
        y -= .8;
        Simulate();
        }
        if(simulate == true)
        {
        x-= 5.5;
        y+= 2.5;
        
        }
        
        
        
         Animation.Animate();
        
        
        
    
    }
     @Override
    public void render(Graphics2D g)
    {
        Animation.constructIT(g, x, y);
    
    }
    
   @Override
    public BufferedImage returnImage()
    {
    return bImage;
    }
    public Boolean returnSimulate()
    {
        return simulate;
    
    }
    public double getX()
    {
        return x;
    }
    public double getY()
    {
        return y;
    }
    
    
}
