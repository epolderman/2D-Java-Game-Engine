/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ObjectBOSS;

import GameState.MULTIPLAYERSTATE;
import GameState.SPACESHIPSTATE;
import Images.Anime;
import Images.LoadImage;
import Images.LoadSpriteSheet;
import Packets.PacketBoss;
import Time.Time;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author eze
 */
public class Vicious 
{
    private double x;
    private double y;
    private Anime vicious;
    private Anime destruct;
    private LoadSpriteSheet itis;
    private LoadSpriteSheet itisD;
    private BufferedImage jupiter;
    private BufferedImage jupiterD;
    private BufferedImage[] sessions = new BufferedImage[2];
    private BufferedImage[] destruction = new BufferedImage[2];
    private Boolean comin;
    private Boolean right;
    private Boolean left;
    private Random rand = new Random();
    private double decision;
    private int hitpoints;
    private boolean dead;
    private Time timeout = new Time();
    private Boolean itstime;
    private Time ending = new Time();
    private boolean credits;
    private boolean ENDIT;
    private String level; 
    private boolean cantTOUCHthis;
    private String id;
    private MULTIPLAYERSTATE MPS;
    private SPACESHIPSTATE SSS;
    private Boolean singleplayer;
    
    
  
    //for single player state
    public Vicious(double x, double y, SPACESHIPSTATE SSS, String level)
    {   
        this.comin = false;
        this.right = false;
        this.left = false;
        this.dead = false;
        this.itstime = false;
        this.credits = false;
        this.ENDIT = false;
        this.level = level;
        this.cantTOUCHthis = false;
        setLevel(level);
        this.singleplayer = true;
        this.SSS = SSS;
        this.x = x;
        this.y = y;
        initialize();
        vicious = new Anime(sessions[0],sessions[1], 10);
        destruct = new Anime(destruction[0],destruction[1], 10);
        System.out.println("Vicious Created!");
        double lower = 1;
        double upper = 10;
        double dat;
        decision = Math.round(dat = Math.random() * (upper - lower) + lower);
        System.out.println("Decision is " + decision);
        decide();
        System.out.println("Right is " + right);
        System.out.println("Left is " + left);
        
        
       
        
  
    }
    ///for multiplayer state
     public Vicious(MULTIPLAYERSTATE MPS, String id, double x, double y)
    {   this.cantTOUCHthis = false;
        this.comin = false;
        this.left = false;
        this.dead = false;
        this.itstime = false;
        this.credits = false;
        this.ENDIT = false;  
        this.singleplayer = false;
        this.right = true;
        this.MPS = MPS;
        this.id = id;
        this.x = x;
        this.y = y;
        initialize();
        vicious = new Anime(sessions[0],sessions[1], 10);
        destruct = new Anime(destruction[0],destruction[1], 10);
        System.out.println("Vicious Created!");  
        hitpoints = 200;
    }
     
     ////for server purposes
      public Vicious(String id, double x, double y)
    {   
        this.id = id;
        this.x = x;
        this.y = y;
        
    }
     
    private void initialize()
    {
        try
        {   
            ////polished vicious
            
            LoadImage loader = new LoadImage();
            jupiter = loader.LoadImage("/Resources/BossSnow.png");
            itis = new LoadSpriteSheet(this.yeaboi());
            
            sessions[0] = itis.grabSprite256(1, 1, 256, 256);
            sessions[1] = itis.grabSprite256(2, 1, 256, 256);
            ///sessions[2] = itis.grabSprite256(3, 1, 256, 256);
            
            ///for destruction vicious
            
            jupiterD = loader.LoadImage("/Resources/Boss2.png");
            itisD = new LoadSpriteSheet(this.yeaboi2());
            
            
            destruction[0] = itisD.grabSprite256(1, 1, 256, 256);
            destruction[1] = itisD.grabSprite256(2, 1, 256, 256);
            ///destruction[2] = itisD.grabSprite256(3, 1, 256, 256);
            
            
        
        }
        catch(Exception e)
        {
            e.printStackTrace();
        
        
        }
    
    
    
    }
    public void setLevel(String level)
    {
         switch (level) {
            case "Easy":
                hitpoints = 250;
                break;
            case "Medium":
                hitpoints = 500;
                break;
            case "Hardened":
                hitpoints = 750;
                break;
        }
        System.out.println("Hitpoints for Vicious " + hitpoints);
        
    }
   
     public Rectangle returnPARAMZ()
    {
        return new Rectangle((int)x,(int)y, 256,256);
    }
    public void update()
    {   
        //System.out.println("Vicous X: " + x);
       // System.out.println("ViciousY: " + y);
       
            
        CREDITS();
        TIMEOUT();
        
        if(comin == false)
        {
                x += 0;
                y += 0;
        }
        
         if(comin == true)
        {
               
                y += .4;
        }
        
        controlBOUND();
        
        if(singleplayer == false)
        {
        PacketBoss packet = new PacketBoss(this.id, this.x, this.y);
        packet.writeData(MPS.socketClient);
        }
        
        
        if(hitpoints >= 101)
        {
        vicious.Animate();
        }
        else
        {
        destruct.Animate();
        }
    }
    ////your working on left to right for the boss
    public void controlBOUND()
    {
        
        
        if(returnDEAD() == true)
            {
          
                y += .7;
            
            if(y >= 250)
                {   
                itstime = true;
                y = 250;
                }
            }
        else
        {
        
        
        if(y >= -20)
        {   
            
            y = -10;
            
        }
        
        if(right == true && y >= -130)
        {  
            x += .7;
            
            if(x >= 558)
            {
                right = false;
                left = true;
            }
            
        }
        
        if(left == true && y >= -130)
        {
            x -= .7;
            
            if(x <= -10)
            {
                left = false;
                right = true;
            }
        }
        
        }    
            
        
        
        
    }
      public void checkHITS(int value)
    {
        hitpoints -= value;
        if(hitpoints <= 0)
        {   
            timeout.startM();
            dead = true;
        }
    }
    public Boolean returnDEAD()
    {
        return dead;
    
    }
    public void setDEAD()
    {
        dead = false;
    }
    public int returnHITS()
    {   
        return hitpoints;
        
        
    }
    public void decide()
    {
        if(decision % 2 == 0)
        {
            right = true;
        }
        else
        {
            left = true;
        }
    
    }
   
    public void render(Graphics2D g)
    {
        if(hitpoints >= 101)
        {
        vicious.constructIT(g, x, y);
        }
        else
        {
        destruct.constructIT(g,x,y);
        }
    }
    public BufferedImage yeaboi()
    {   
        return jupiter;
    
    }
    public BufferedImage yeaboi2()
    {
        return jupiterD;
    }
    
    public void setIncoming(int z)
    {
        if(z == 1)
        {
            comin = true;
        }
        else
        {
            comin = false;
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
    public void setX(double xx)
    {
        x = xx;
    }
    public void setY(double yy)
    {
        y = yy;
    }
    public void TIMEOUT()
    {
        long expired;
        
        if(itstime == true)
        {
        timeout.stopM();
        expired = timeout.getDurationM();
        timeout.resetStopM();
        if(expired > 150)
        {
            credits = true;
            itstime = false;
            ending.startM();
            
        }
        else
            
            itstime = true;
        
        }
    
    }
    public Boolean returnITSTIME()
    {
        return itstime;
    }
    public void CREDITS()
    {
        long done;
        
        if(credits == true)
        {
            ending.stopM();
            done = ending.getDurationM();
            ending.resetStopM();
            
            if(done >= 5000)
            {
                ENDIT = true;
            
            }
            else
            {
                ENDIT = false;
            }
        
        }
        
    
    }
    
    public Boolean returnENDIT()
    {
    
        return ENDIT;
    }
    public void setHITPOINTS(int z)
    {
        hitpoints = z;
    }
    public String getUsername()
    {
    
        return id;
    
    }
    public int returnHits()
    {
        return hitpoints;
    }
   
    
    
    
    
    
    
}



