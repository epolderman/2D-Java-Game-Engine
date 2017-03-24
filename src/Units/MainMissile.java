/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import Audio.Audio;
import GameState.MULTIPLAYERSTATE;
import GameState.SPACESHIPSTATE;
import Images.Anime;
import Images.LoadImage;
import Images.LoadSpriteSheet;
import Packets.PacketLaser;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author eze
 */
///Description: Main Missile for the Swordfish and Multiplayers
//-> cooldown time is regulated in the levels
//-> server has to keep track of these units



public class MainMissile extends ShellUnit
{   //instance variables
    private MULTIPLAYERSTATE MPS;
    private String id;
    private SPACESHIPSTATE SSS;
    private Boolean singleplayer;
    private Audio shot;
    private BufferedImage[] bimageArray = new BufferedImage[3];
    ///singleplayer
    public MainMissile(double x, double y, SPACESHIPSTATE SSS)
    {
    this.singleplayer = true;
    this.x = x;
    this.y = y;
    this.SSS = SSS;
    shot = new Audio("/Music/IT2.wav");
    shot.play();
    initialize();
    Animation = new Anime(bimageArray[0],bimageArray[1],bimageArray[2], 10);
    }
    //multiplayer
    public MainMissile(double x, double y, MULTIPLAYERSTATE MPS, String id)
    {
    this.singleplayer = false;
    this.x = x;
    this.y = y;
    this.MPS = MPS;
    this.id = id;
    shot = new Audio("/Music/IT2.wav");
    shot.play();
    initialize();
    Animation = new Anime(bimageArray[0],bimageArray[1],bimageArray[2],10);
    }
    ///server
    public MainMissile(String id,double x, double y)
    {
    this.x = x;
    this.y = y;
    System.out.println("Created missile for the server!");
    System.out.println("ID = " + id);
    this.id = id;
    }

    @Override
    public void initialize() 
    {
      try
        {   
            LoadImage loader = new LoadImage();
            bImage = loader.LoadImage("/Resources/SpikeSheet.png");
            SpriteSheet = new LoadSpriteSheet(this.returnImage());
            
            bimageArray[0] = SpriteSheet.grabSprite64(1, 4, 64, 64);
            bimageArray[1] = SpriteSheet.grabSprite64(2, 4, 64, 64);
            bimageArray[2] = SpriteSheet.grabSprite64(3, 4, 64, 64);
           
            
            
        
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        
    }

    @Override
    public void update() 
    {
        y -= 7;
        x += 0;
        
        if(singleplayer == false)
        {
        
        PacketLaser packet = new PacketLaser(this.id, this.x, this.y);
        packet.writeData(MPS.socketClient);
        
        }
        
        
        
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
        System.out.println("Done Need this");
    }

    @Override
    public Rectangle returnPARAMZ() 
    {
      return new Rectangle ((int)x,(int)y, 64, 64);
    }
     public String returnID()
    {
        return id;
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
  
    
}
