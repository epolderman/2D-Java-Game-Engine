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
public class MenuBarConsole {
    
      ///INSTANCE VARIABLES
    private double x;
    private double y;
    private LoadSpriteSheet controlconsole;
    private BufferedImage MC;
    private BufferedImage[] yessir = new BufferedImage[1]; 
    private Anime console;
    private Time MCT;
    private Boolean done;
    public String side;
    
    
    
    
    public MenuBarConsole(double x, double y)
    {
        this.x = x;
        this.y = y;
        this.done = false;
        System.out.println("Menu Bar Generated");
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
            MC = loader.LoadImage("/Resources/Console7.png");
            controlconsole = new LoadSpriteSheet(this.returnMC());
            
            yessir[0] = controlconsole.grabSprite640(1, 1, 898, 64);
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    
    }
    public void simulation()
    {   
        ///simulation
    }
   
    public void update()
    {   
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
    public double getX()
    {
        return x;
    }
   
    
    
    
}
