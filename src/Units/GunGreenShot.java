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
import Packets.PacketGGS;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author eze
 */
public class GunGreenShot extends ShellUnit
{
     
    private BufferedImage[] bimageArray = new BufferedImage[3];
    private Random rand;
    double movementx;
    double movementy;
    private MULTIPLAYERSTATE MPS;
    private String id;
    private SPACESHIPSTATE SSS;
    private boolean singleplayer;
    
    ///for single player
    public GunGreenShot(double x, double y, SPACESHIPSTATE SSS)
    {
    this.singleplayer = true;
    this.SSS = SSS;
    this.x = x;
    this.y = y;
    initialize();
    Animation = new Anime(bimageArray[0],bimageArray[1],bimageArray[2], 5);
    System.out.println("Green Gunshot!");
    rand = new Random();
    double lower = 1;
    double upper = 3;
    movementx = Math.random() * (upper - lower) + lower;
    movementy = Math.random() * (upper - lower) + lower;
    }
    ////for multiplayerstate
    public GunGreenShot(MULTIPLAYERSTATE MPS, String id,  double x, double y)
    {
        
    this.singleplayer = false;
    this.id = id;
    this.MPS = MPS;
    this.x = x;
    this.y = y;
    initialize();
    Animation = new Anime(bimageArray[0],bimageArray[1],bimageArray[2], 5);
    System.out.println("Green Gunshot!");

    }
    
    //for server database
    public GunGreenShot(String id, double x, double y)
    {
        
    System.out.println("Created for Server");
    this.id = id;
    this.x = x;
    this.y = y;
  
    }
    @Override
    public void Simulate()
    {
    //unsupported
    
    }
    
     @Override
    public void initialize()
    {
        try{
                LoadImage loader = new LoadImage();
                bImage = loader.LoadImage("/Resources/Enemies.png");
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
    public void render(Graphics2D g)
    {
        Animation.constructIT(g, x, y);
    }
    @Override
    public void update()
    {   
        checkBOUNDS();
        
        
        
        if(singleplayer == false)
        {
        y += .8;
        x += 0;
        PacketGGS packet = new PacketGGS(this.id, this.x, this.y);
        packet.writeData(MPS.socketClient);
        }
        else
        {
            x += 0;
            y += movementy;
        
        }
        
        Animation.Animate();
    
    }
     public void checkBOUNDS()
        {
            
            
            if(y >= 325)
            {
                x += 1.5;
                y += 4;
                
            }
            
            
            
        
        }
    @Override
    public BufferedImage returnImage()
    {
        return bImage;
    }
    public double returnX()
    {
        return x;
    }
    public double returnY()
    {
        return y;
    }
    @Override
    public Rectangle returnPARAMZ()
    {
        return new Rectangle((int)x,(int)y, 64,64);
    }
    public String returnID()
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
