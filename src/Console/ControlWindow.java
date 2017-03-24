/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Console;

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
public class ControlWindow 
{
     ///INSTANCE VARIABLES
    private double x;
    private double y;
    private LoadSpriteSheet controlconsole;
    private BufferedImage MC;
    private BufferedImage[] yessir = new BufferedImage[1]; 
    private Anime console;
    private Time MCT;
    private Boolean done = false;
    public String side;
    
    
    
    
    public ControlWindow(double x, double y)
    {
        this.x = x;
        this.y = y;
        
        System.out.println("Control Console Generated");
        initialize();
        console = new Anime(yessir[0], 5);
        MCT = new Time();
        MCT.startM();
        
        
    }
    private void initialize()
    {
        try
        {   
            LoadImage loader = new LoadImage();
            MC = loader.LoadImage("/Resources/Controls.png");
            controlconsole = new LoadSpriteSheet(this.returnMC());
            
            yessir[0] = controlconsole.grabSprite512(1, 1, 512, 512);
            
            
            
            
        
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    
    }
    public void simulation()
    {   
       if(done == true)
       {
        x+=2;
        y+=0;
       }
       if(done == false)
       {
           x-=2;
           y+=0;
           
           if(x <= -510)
           {
               x = -510;
           }
       
       }
    }
   
    public void update()
    {   
        simulation();
        
        if(x >= -295)
        {

                        x = -295;
                        
                        //y += 0;

        }
        
        
        
  
        console.Animate();
    }
    public void render(Graphics2D g)
    {
        console.constructIT(g, x, y);
    }
    public BufferedImage returnMC()
    {
        return MC;
    }
    public void setX(double z)
    {
        x = z;
    
    }
    public void setDONE()
    {   
        if(done == true)
        {
            done = false;
        }
        else
        {
            done = true;
        }
    }
    public double getX()
    {
        return x;
    }
   
    
    
    
    
}
