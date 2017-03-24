/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import Images.Anime;
import Images.LoadImage;
import Images.LoadSpriteSheet;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author eze
 */
public class MenuFlake extends ShellUnit
{
    
    private BufferedImage[] bimageArray = new BufferedImage[1];
    private Random rand;
    private double movementx;
    private double movementy;
    

    public MenuFlake(double x, double y)
    {
        this.x = x;
        this.y = y;
        rand = new Random();
        double lower = .1;
        double upper = .6;
        movementx = Math.random() * (upper - lower) + lower;
        movementy = Math.random() * (upper - lower) + lower;
        initialize();
        Animation = new Anime(bimageArray[0], 10);
    
    
    
    }
    
    
    @Override
    public void initialize() 
    {
        try
        {
            
                LoadImage loader = new LoadImage();
                bImage = loader.LoadImage("/Resources/Sprinkle.png");
                SpriteSheet = new LoadSpriteSheet(returnImage());
                
              
                
                bimageArray[0] = SpriteSheet.grabSprite64(4, 4, 64, 64);
                
            
                
                
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void update() 
    {
        y += movementy;
        x -= movementx;
        
       
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
    public void Simulate() {
          
    }
    @Override
    public Rectangle returnPARAMZ() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
