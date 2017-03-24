/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Explosions;

import Images.Anime;
import Images.LoadImage;
import Images.LoadSpriteSheet;
import Time.Time;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author eze
 */
public class ViciousExplosion extends ShellExplosion
{
     ///inheriting all the instance variables
    private BufferedImage[] bimageArray = new BufferedImage[3];
    
    public ViciousExplosion(double x, double y)
    {   
        this.expired = 0;
        duration = new Time();
        this.x = x;
        this.y = y;
        initialize();
        duration.startM();
        Animation = new Anime(bimageArray[0], bimageArray[1], bimageArray[2], 10);
    }
    
    @Override
    public void initialize()
    {
    
          try
        {   
            LoadImage loader = new LoadImage();
            bImage = loader.LoadImage("/Resources/BossExplode.png");
            SpriteSheet = new LoadSpriteSheet(this.returnImage());
            
            bimageArray[0] = SpriteSheet.grabSprite256(1, 3, 256, 256);
            bimageArray[1] = SpriteSheet.grabSprite256(2, 3, 256, 256);
            bimageArray[1] = SpriteSheet.grabSprite256(3, 3, 256, 256);
            
        
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    
    }
    
    @Override
    public void update()
    {
        x += 0;
        y += 0;
        
       Animation.Animate();
    
    }
    
    @Override
    public void render(Graphics2D g)
    {
     
        Animation.constructIT(g, x, y);
    }
    
    @Override
    public boolean checkExplosion()
    {
        duration.stopM();
        expired = duration.getDurationM();
        duration.resetStopM();
        
        if(expired > 1000)
        {
        return true;
        }
        else
        {
        return false;
        }
        
    
    }
    
    @Override
    public BufferedImage returnImage()
    {
        return bImage;
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
    
    
    
    
    
   
    
}
