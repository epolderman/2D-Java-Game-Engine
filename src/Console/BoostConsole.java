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
public class BoostConsole 
{   
    /////INSTANCE VARIABLES
    private double x;
    private double y;
    private Anime BC;
    private Anime XXX;
    private LoadSpriteSheet BoostConsole;
    private BufferedImage[] CB = new BufferedImage[3];
    private BufferedImage[] XX = new BufferedImage[3];
    private BufferedImage Turbo;
    public Boolean boosted;
    private Time boostit;
    private long shadowz;
    private Boolean done;
    
    public BoostConsole(double x, double y)
    {   this.boosted = false;
        this.shadowz = 0;
        this.done = false;
        this.x = x;
        this.y = y;
        initialize();
        boostit = new Time();
        boostit.startM();
        System.out.println("Boost Console Generated");
        BC = new Anime(CB[0],CB[1],CB[2], 25);
        XXX = new Anime(XX[0],XX[1],XX[2], 25);
    
    }
    private void initialize()
    {
        try
        {
            LoadImage loader = new LoadImage();
            Turbo = loader.LoadImage("/Resources/Bebopp.png");
            BoostConsole = new LoadSpriteSheet(this.returnBC());
            
            CB[0] = BoostConsole.grabSprite128(1, 2, 128, 128);
            CB[1] = BoostConsole.grabSprite128(2, 2, 128, 128);
            CB[2] = BoostConsole.grabSprite128(3, 2, 128, 128);
            
            XX[0] = BoostConsole.grabSprite128(1, 3, 128, 128);
            XX[1] = BoostConsole.grabSprite128(2, 3, 128, 128);
            XX[2] = BoostConsole.grabSprite128(3, 3, 128, 128);
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    
    
    }
    public void render(Graphics2D g)
    {  
        if(boosted == true)
    {
        BC.constructIT(g, x, y);
    }
    else if (boosted == false)
    {
        XXX.constructIT(g, x, y);
    }
    }
    public void update()
    {
        simulation();
        
        
        if(done == true)
        {
        x += .6;
        y += 0;
        }
    
    if(boosted == true)
    {
        BC.Animate();
    }
    else if (boosted == false)
    {
        XXX.Animate();
    }
        
    
    }
     public void simulation()
    {   
        
        boostit.stopM();
        shadowz = boostit.getDurationM();
        boostit.resetStopM();
        
        if(shadowz >= 14500)
        {
            done = true;
        }
        
        
        
        
        if (x >= 11)
        {
            x = 11;
        }
    
    }
    
    public BufferedImage returnBC()
    {
        return Turbo;
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
    
    
    
}
