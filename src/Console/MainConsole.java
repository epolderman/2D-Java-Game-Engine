/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Console;

import GameState.MULTIPLAYERSTATE;
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
public class MainConsole 
{   

    ///INSTANCE VARIABLES
    private double x;
    private double y;
    private LoadSpriteSheet mainconsole;
    private BufferedImage MC;
    private BufferedImage[] yessir = new BufferedImage[1]; 
    private Anime console;
    private Time MCT;
    private long shadow;
    private Boolean done;
    private MULTIPLAYERSTATE MPS;
    private Boolean multiplayer;
    
    
    
    public MainConsole(double x, double y)
    {
        this.multiplayer = false;
        this.x = x;
        this.y = y;
        this.done = false;
        this.shadow = 0;
        System.out.println("Main Console Generated");
        initialize();
        console = new Anime(yessir[0], 5);
        MCT = new Time();
        MCT.startM();
        
        
    }
     public MainConsole(double x, double y, MULTIPLAYERSTATE MPS)
    {
        this.multiplayer = true;
        this.MPS = MPS;
        this.x = x;
        this.y = y;
        this.shadow = 0;
        this.done = false;
        System.out.println("Multiplayer State: Main Console Generated");
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
            MC = loader.LoadImage("/Resources/BlueC.png");
            mainconsole = new LoadSpriteSheet(this.returnMC());
            
            yessir[0] = mainconsole.grabSprite128(1, 2, 128, 128);
            //yessir[1] = mainconsole.grabSprite128(2,2,128,128);
           // yessir[2] = mainconsole.grabSprite128(3,2,128,128);
            
            
            
        
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    
    }
     public void Msimulation()
    {
        
        MCT.stopM();
        shadow = MCT.getDurationM();
        MCT.resetStopM();
        
        
     if(MPS.returnSTATE() == true)
        {   //System.out.println("DoneR =" + doneR);   
          done = true;
              
        }
     
     
        if (x >= -5)
        {
            x = -5;
        }
    
    }
    public void simulation()
    {   
        
        MCT.stopM();
        shadow = MCT.getDurationM();
        MCT.resetStopM();
        
        if(shadow >= 14500)
        {
            done = true;
           
        }
        
        
        /////split up the sides////
   
        if (x >= -5)
        {
            x = -5;
        }
       
           

       
    }
   
    public void update()
    {   
        
        if(multiplayer == true)
        {
        Msimulation();
        }
        else
        {
        simulation();
        }
        
        if(done == true)
        {

                        x += .6;
                        y += 0;

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
   
    
    
    
}
