/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import GameState.MULTIPLAYERSTATE;
import GameState.SPACESHIPSTATE;
import Images.Anime;
import Images.LoadImage;
import Images.LoadSpriteSheet;
import Packets.PacketSmallM;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author eze
 */
public class SmallMeteor extends ShellUnit
{
     
    private BufferedImage[] bimageArray = new BufferedImage[3];
    private int hitpoints;
    private Random r = new Random();
    private int speed;
    private MULTIPLAYERSTATE MPS;
    private String id;
    private SPACESHIPSTATE SSS;
    private Boolean singleplayer;

    
    ///for single player
    public SmallMeteor(double x, double y, SPACESHIPSTATE SSS)
    {   
        
        this.hitpoints = 0;
        this.speed = r.nextInt(2) + 2;
        this.singleplayer = true;
        this.bimageArray = new BufferedImage[3];
        this.SSS = SSS;
        this.x = x;
        this.y = y;
        initialize();
        System.out.println("Small  Meteor Made");
        Animation = new Anime(bimageArray[0],bimageArray[1],bimageArray[2], 20);
    
    
    }
    ///for multiplayer
    public SmallMeteor(MULTIPLAYERSTATE MPS, String id, double x, double y)
    {   
        this.singleplayer = false;
        this.bimageArray = new BufferedImage[3];
        this.MPS = MPS;
        this.id = id;
        this.x = x;
        this.y = y;
        initialize();
        System.out.println("Small  Meteor Made");
        Animation = new Anime(bimageArray[0],bimageArray[1],bimageArray[2], 20);
    
    
    }
    
    ///for server data structure
     public SmallMeteor(String id, double x, double y)
    {   
        this.id = id;
        this.bimageArray = new BufferedImage[3];
        this.x = x;
        this.y = y;
        System.out.println("Small Meteor: Created for Server");
        
    }
     
     
     
    @Override
    public void initialize()
    {
        
        try
        {
            LoadImage loader = new LoadImage();
            bImage = loader.LoadImage("/Resources/SmallMetz.png");
            SpriteSheet = new LoadSpriteSheet(this.returnImage());
            
            bimageArray[0] = SpriteSheet.grabSprite64(1, 2, 64, 64);
            bimageArray[1] = SpriteSheet.grabSprite64(2, 2, 64, 64);
            bimageArray[2] = SpriteSheet.grabSprite64(3, 2, 64, 64);
           
        
        }
        catch(Exception e)
        {
            e.printStackTrace();
        
        }
    
    }
    @Override
    public Rectangle returnPARAMZ()
    {
        return new Rectangle ((int)x,(int)y, 64, 64);
        
    }
  
    @Override
    public void render(Graphics2D g)
    {
        Animation.constructIT(g, x, y);
    
    }
    @Override
     public void update()
    {   
        
        x -= .5;
        
        if(singleplayer == false)
        {
       y += .3;
       PacketSmallM packet = new PacketSmallM(this.id, this.x, this.y);
       packet.writeData(MPS.socketClient);
        }
        else
        {
        y += speed;
        }
        
        
        Animation.Animate();
        
        
        
    }
      public void setY(double y)
    {
        this.y = y;
    
    }
    public void setX(double x)
    {
        this.x = x;
    }
    public double getY()
    {
        return y;
    }
    public String returnID()
    {
        return id;
    }
     @Override
    public void Simulate()
    {
        System.out.println("Dont Need This");
    }
       @Override
    public BufferedImage returnImage()
    {
    return bImage;
    }
    
    
    
}
