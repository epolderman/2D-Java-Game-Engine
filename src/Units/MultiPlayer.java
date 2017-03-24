/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import GameState.MULTIPLAYERSTATE;
import Images.Anime;
import Images.LoadImage;
import Images.LoadSpriteSheet;
import Packets.PacketMove;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.net.InetAddress;

/**
 *
 * @author eze
 */
///Description: This is the multiplayer players
//Same basic unit structure as the swordfish just for multiplayer purposes
//added ip address, port numbers, etc..



public class MultiPlayer extends ShellUnit
{
    private BufferedImage[] bimageArray = new BufferedImage[3];
    private BufferedImage[] enemyBOOST = new BufferedImage[3];
    private Anime AnimationBoost;
    private String username;
    private InetAddress ipAddress;
    private int port;
    private MULTIPLAYERSTATE MPS;
    private int Hitpoints;
    private Boolean dead;
    private double dx;
    private double dy;
    
    public MultiPlayer(MULTIPLAYERSTATE MPS, double x, double y, String username, InetAddress ipAddress, int port) 
    {
        this.x = x;
        this.y = y;
        this.MPS = MPS;
        this.Hitpoints = 250;
        this.ipAddress = ipAddress;
        this.port = port;
        this.dead = false;
        this.username = username;
        initialize();
        Animation = new Anime(bimageArray[0], bimageArray[1], bimageArray[2], 10);
        AnimationBoost = new Anime(enemyBOOST[0], enemyBOOST[1], enemyBOOST[2], 10);
    }
    
     @Override
    public void initialize()
    {
     try
        {   ////load sprite image in and grab the subimage and set the animation
            LoadImage loader = new LoadImage();
            bImage = loader.LoadImage("/Resources/MultiPlayer.png");
            SpriteSheet = new LoadSpriteSheet(this.returnImage());
            
            
            bimageArray[0] = SpriteSheet.grabSprite128(1,2,128,128);
            bimageArray[1] = SpriteSheet.grabSprite128(2,2,128,128);
            bimageArray[2] = SpriteSheet.grabSprite128(3,2,128,128); 
            
            
            enemyBOOST[0] = SpriteSheet.grabSprite128(1,3,128,128);
            enemyBOOST[1] = SpriteSheet.grabSprite128(2,3,128,128);
            enemyBOOST[2] = SpriteSheet.grabSprite128(3,3,128,128);
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    
    
    }
      @Override
    public void Simulate()
    {
        
        if(x <= -15)
            x = -15;
        if(x >= 680)
            x = 680;
        if(y <= 0)
         y = 5;
        if(y >= 585)
            y = 585;
        
        
    }
    @Override
     public void update()
    {   
        
        Simulate();
        x += dx;
        y += dy;
        
        PacketMove packet = new PacketMove(this.getUsername(),this.x, this.y);
        packet.writeData(MPS.socketClient);
        
        
        
        if("Erik".equals(this.username) || "erik".equals(this.username))
        {
        Animation.Animate();
        }
        else
        {
        AnimationBoost.Animate();
        }
    
    }
    @Override
     public void render(Graphics2D g)
    {
        
     if("Erik".equals(this.username) || "erik".equals(this.username))
     {
     Animation.constructIT(g, x, y);
     }
     else
     {
     AnimationBoost.constructIT(g, x, y);
     }
     
     
    }
     @Override
    public BufferedImage returnImage()
    {
    return bImage;
    }
    

    
     public void notchHits(int hits)
    {
        
        Hitpoints = Hitpoints - hits;
        
    
    }
    
    public Boolean checkDeath()
    {
        
     if(Hitpoints < 0)
     {
     dead = true;
     
     }
     else
     {
     dead = false;
     }
        
        
     return dead;
    }
    ///end death and health methods
    
    //setters/getters
       public void setDX(double dx)
    {
        this.dx = dx;
    }
    public void setDY(double dy)
    {
        this.dy = dy;
    
    }
    public double getDX()
    {
        return dx;
    }
    public double getDY()
    {
        return dy;
    }
     public String getUsername() 
     {
        return username;
    }
   
    public double getX()
    {
        return x;
    }
    public double getY()
    {
        return y;
    }
    public void setX(double x)
    {
        this.x = x;
    }
    public void setY(double y)
    {
        this.y = y;
    }
    public void setHits(int Hitpoints)
    {
        this.Hitpoints = Hitpoints;
    }
    public int returnHits()
    {
        return Hitpoints;
    }
    public InetAddress returnIP()
    {
        return ipAddress;
    }
    public void setIP(InetAddress ipAddress)
    {
        this.ipAddress = ipAddress;
    }
    public int returnPORT()
    {
        return port;
    }
    public void setPort(int port)
    {
        this.port = port;
    }
    @Override
    public Rectangle returnPARAMZ()
    {  
     return new Rectangle ((int)x,(int)y, 85, 85);

    }
}
