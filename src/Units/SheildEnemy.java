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

///Description: These enemeies act as sheilds to other enemies.
//They do not shoot just provide cover for their allies.
//Can only be killed by a plasma blast
public class SheildEnemy extends ShellUnit
{
    
    private BufferedImage[] bimageArray = new BufferedImage[3];
    private Time xx;
    private long duration;
    private Boolean yessir;
    
    public SheildEnemy(double x, double y)
    {   
        this.x = x;
        this.y = y;
        xx = new Time();
        this.duration = 0;
        this.yessir = false;
        System.out.println("Sheild Enemy Generated!");
        xx.startM();
        initialize();
        Animation = new Anime(bimageArray[0], bimageArray[1], bimageArray[2], 10);
        
    }
    
    

    @Override
    public void initialize() 
    {
        try{
                LoadImage loader = new LoadImage();
                bImage = loader.LoadImage("/Resources/EnemiesUltra.png");
                SpriteSheet = new LoadSpriteSheet(this.returnImage());
                
                bimageArray[0] = SpriteSheet.grabSprite128(1, 2, 128, 128);
                bimageArray[1] = SpriteSheet.grabSprite128(2, 2, 128, 128);
                bimageArray[2] = SpriteSheet.grabSprite128(3, 2, 128, 128);
                
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

     public void checkBOUND()
    {
         if(y >= 150 && yessir == false)
            {
                y = 150;
            }
            
    }
    public void checkBOUND2()
    {   
        if(yessir == true)
        {
        y += 2.0;
        x += 0.0;
        }
    
    }
    
    @Override
    public void update() 
    {
        checkBOUND();
        checkBOUND2();
        y += 2.5;
        x += 0;
        
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rectangle returnPARAMZ() 
    {
         return new Rectangle ((int)x,(int)y, 128, 128);
    }
     public void checkTime()
    {   
        xx.stopM();
        duration = xx.getDurationM();
        if(duration >= 12000)
        {
            yessir = true;
            
        }
    
    }
    public double getX()
    {
        return x;
    }
    public double getY()
    {
        return y;
    }
    public Boolean returnYes()
    {
        return yessir;
    }
    
    
    
}
