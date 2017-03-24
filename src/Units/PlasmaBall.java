/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import Audio.Audio;
import Images.Anime;
import Images.LoadImage;
import Images.LoadSpriteSheet;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author eze
 */
//Descirption: Main Cannon from the swordifsh
///does unspeakable damage to all units


public class PlasmaBall extends ShellUnit
{

     private Audio PlasmaShot;
     private BufferedImage[] bimageArray = new BufferedImage[3];
     
    
    
    public PlasmaBall(double x, double y)
    {
    this.x = x;
    this.y = y;
    initialize();
    Animation = new Anime(bimageArray[0], bimageArray[1], bimageArray[2], 30);
    PlasmaShot = new Audio("/Music/PlasmaShot.wav");
    PlasmaShot.play();
    
    
    
    }
    
    
    
    @Override
    public void initialize() 
    {
         try
        {   
            LoadImage loader = new LoadImage();
            bImage = loader.LoadImage("/Resources/BeboppN.png");
            SpriteSheet = new LoadSpriteSheet(this.returnImage());
            
            bimageArray[0] = SpriteSheet.grabSprite128(1, 3, 128, 128);
            bimageArray[1] = SpriteSheet.grabSprite128(2, 3, 128, 128);
            bimageArray[2] = SpriteSheet.grabSprite128(3, 3, 128, 128);
            
            
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void update() 
    {
        y -= 4;
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

    @Override
    public void Simulate() 
    {
      
    }

    @Override
    public Rectangle returnPARAMZ() 
    {
         return new Rectangle ((int)x,(int)y, 128, 128);
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
