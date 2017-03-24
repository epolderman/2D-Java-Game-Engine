/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ObjectBOSS;

import GameState.MULTIPLAYERSTATE;
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
public class PlasmaBalls 
{
    //instance variables
    private double x;
    private double y;
    private Anime inside;
    private BufferedImage wave;
    private BufferedImage[] way = new BufferedImage[3];
    private LoadSpriteSheet changa;
    private Random rand = new Random();
    private double movementx = 0;
    private double movementy = 0;
    private int hitpoints;
    private Boolean dead = false;
    private String level;
    private MULTIPLAYERSTATE MPS;
    private String id;
    private boolean multiPlayer;
    
    
    //for single player level
    public PlasmaBalls(double x, double y, String level)
    {
        
    this.multiPlayer = false;
    this.x = x;
    this.y = y;
    initialize();
    inside = new Anime(way[0],way[1],way[2], 20);
    System.out.println("Plasma Bawws!");
    double lower = -.5;
    double upper = 5;
    movementx = Math.random() * (upper - lower) + lower;
    movementy = Math.random() * (upper - lower) + lower;
    this.level = level;
         switch (level) 
         {
             case "Easy":
                 hitpoints = 15;
                 break;
             case "Medium":
                 hitpoints = 30;
                 break;
             case "Hardened":
                 hitpoints = 45;
                 break;
         }
    
    
    }
    ///for multiplayer level
     public PlasmaBalls(double x, double y,String id, MULTIPLAYERSTATE MPS)
    {
        this.multiPlayer = true;
        this.MPS = MPS;
        this.id = id;
        this.x = x;
        this.y = y;
        initialize();
        inside = new Anime(way[0],way[1],way[2], 20);
        System.out.println("Plasma Bawws!");
        System.out.println("Plase Balls Created For State");
      
    }
     ///for server data structure management
     public PlasmaBalls(String id, double x, double y)
    {
        
        this.id = id;
        this.x = x;
        this.y = y;
        System.out.println("Created for Server!");
       
      
    }
    private void initialize()
    {
        try{
                LoadImage loader = new LoadImage();
                wave = loader.LoadImage("/Resources/Enemies.png");
                changa = new LoadSpriteSheet(this.returnCHANGE());
                
                way[0] = changa.grabSprite64(4, 3, 64, 64);
                way[1] = changa.grabSprite64(4, 2, 64, 64);
                way[2] = changa.grabSprite64(4, 1, 64, 64);
                
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    
    }
    public void render(Graphics2D g)
    {
        inside.constructIT(g, x, y);
    }
    public void update()
    {   
        ///check hit points
        
        if(multiPlayer == false)
        {
        y += movementy;
        x += movementx;
        }
        
        if(multiPlayer == true)
        {
        x += 0;
        y += 5;
        }
        
        inside.Animate();
    
    }
    public void checkHITS(int value)
    {   
        hitpoints -= value;
        
        if(hitpoints <= 0)
        {
            dead = true;
        }
        
    
    }
    public Boolean checkDEAD()
    {
        return dead;
    }
    public BufferedImage returnCHANGE()
    {
        return wave;
    }
    public double returnX()
    {
        return x;
    }
    public double returnY()
    {
        return y;
    }
    public Rectangle returnPARAMZ()
    {
        return new Rectangle((int)x,(int)y, 64,64);
    }
    public String getUsername()
    {
        return id;
    }
    public void setX(double x)
    {
        this.x = x;
    }
     public void setY(double y)
    {
        this.y = y;
    }
   
    
    
}
