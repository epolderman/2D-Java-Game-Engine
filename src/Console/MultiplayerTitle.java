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
public class MultiplayerTitle {
    
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
    
    ///for clicking purposes
    private BufferedImage[] clicked = new BufferedImage[1];
    private Anime clickedconsole;
    
    
    
    
    public MultiplayerTitle(double x, double y)
    {
        this.x = x;
        this.y = y;
        
        System.out.println("MultiplayerTitle Generated");
        initialize();
        console = new Anime(yessir[0], 5);
        //for clicked
        clickedconsole = new Anime(clicked[0], 5);
        MCT = new Time();
        MCT.startM();
        
        
    }
    private void initialize()
    {
        try
        {   
            LoadImage loader = new LoadImage();
            MC = loader.LoadImage("/Resources/BlueC.png");
            controlconsole = new LoadSpriteSheet(this.returnMC());
            
            yessir[0] = controlconsole.grabSprite128(2, 3, 128, 128);
            //if clicked
            clicked[0] = controlconsole.grabSprite128(1, 3, 128, 128);
 
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    
    }
   
   
    public void update()
    {   
       x+=0;
       y+=0;

        console.Animate();
        clickedconsole.Animate();
    }
    public void render(Graphics2D g)
    {
        if(done == true)
        {
            clickedconsole.constructIT(g, x, y);
        }
        else if(done == false)
        {
            console.constructIT(g, x, y);
        }
        
        
    }
    public BufferedImage returnMC()
    {
        return MC;
    }
    public void setX(double z)
    {
        x = z;
    
    }
     public void setY(double s)
    {
        y = s;
    
    }
   
    public double getX()
    {
        return x;
    }
    
     
      public Boolean returnDONE()
      {
          return done;
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
    
}
