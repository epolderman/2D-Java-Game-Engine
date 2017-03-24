/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Console;

import GameState.MULTIPLAYERSTATE;
import GameState.SPACESHIPSTATE;
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
public class MainConsoleRight 
{
     ///INSTANCE VARIABLES
    private double x;
    private double y;
    private LoadSpriteSheet mainconsoleR;
    private BufferedImage MCR;
    private BufferedImage[] yessirR = new BufferedImage[1]; 
    Anime consoleR;
    Time MCTR;
    private long shadowR = 0;
    private Boolean doneR;
    private MULTIPLAYERSTATE MPS;
    private SPACESHIPSTATE SSS;
    private Boolean singleplayer;
    
    
    
    
    public MainConsoleRight(double x, double y, SPACESHIPSTATE SSS)
    {   this.singleplayer = true;
        this.SSS = SSS;
        this.x = x;
        this.y = y;
        System.out.println("Main Console Right Generated");
        initialize();
        this.doneR = false;
        consoleR = new Anime(yessirR[0], 5);
        MCTR = new Time();
        MCTR.startM();
        
    }
    
    public MainConsoleRight(double x, double y, MULTIPLAYERSTATE MPS)
    {   
        this.singleplayer = false;
        this.MPS = MPS;
        this.x = x;
        this.y = y;
        System.out.println("Main Console Right Generated");
        initialize();
        this.doneR = false;
        consoleR = new Anime(yessirR[0], 5);
        MCTR = new Time();
        MCTR.startM();
        
    }
    private void initialize()
    {
        try
        {   
            LoadImage loader = new LoadImage();
            MCR = loader.LoadImage("/Resources/BlueC.png");
            mainconsoleR = new LoadSpriteSheet(this.returnMC());
            
            yessirR[0] = mainconsoleR.grabSprite128(1, 2, 128, 128);
            //yessirR[1] = mainconsoleR.grabSprite128(2,2,128,128);
            //yessirR[2] = mainconsoleR.grabSprite128(3,2,128,128);
            
            
            
        
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    
    }
    public void Msimulation()
    {
       
     ///this timer is calculated in the multiplayer state
     if(MPS.returnSTATE() == true)
        {   //System.out.println("DoneR =" + doneR);   
            doneR = true;
              
        }
    
    }
    public void simulation()
    {   
        
        MCTR.stopM();
        shadowR = MCTR.getDurationM();
        MCTR.resetStopM();
        
      
        if(shadowR >= 14500)
       {
           doneR = true;
        }
        
        
       
     
    
    }
    public void update()
    {   
        if(singleplayer == true)
        {
        simulation();
        }
        else
        {
        Msimulation();
        }
        
        if(doneR == true)
        {
        x -= .6;
        y += 0;
        }
        
        if(x <= 676)
            {
                x = 676;
            }
        
        
        consoleR.Animate();
    }
    public void render(Graphics2D g)
    {
        consoleR.constructIT(g, x, y);
    }
    public BufferedImage returnMC()
    {
        return MCR;
    }
   
    
    
    
}
