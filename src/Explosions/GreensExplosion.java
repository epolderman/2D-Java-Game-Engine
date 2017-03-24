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
public class GreensExplosion extends ShellExplosion
{
     ///inheriting all the instance variables
    private BufferedImage[] bimageArray = new BufferedImage[3];
    
    public GreensExplosion(double x, double y)
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
            bImage = loader.LoadImage("/Resources/Explosion.png");
            SpriteSheet = new LoadSpriteSheet(this.returnImage());
            
            bimageArray[0] = SpriteSheet.grabSprite64(1, 1, 64, 64);;
            bimageArray[1] = SpriteSheet.grabSprite64(2, 1, 64, 64);
            bimageArray[2] = SpriteSheet.grabSprite64(3, 1, 64, 64);
            
        
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
    
    
    
    
}
