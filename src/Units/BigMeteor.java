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
import Packets.PacketBigM;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author eze
 */
public class BigMeteor extends ShellUnit
{
    //instance variables
    private BufferedImage[] bimageArray = new BufferedImage[6];
    private Random r = new Random();
    private int speed; 
    private String id;
    private MULTIPLAYERSTATE MPS;
    private SPACESHIPSTATE SSS;
    private Boolean singleplayer;
    
    ///single player constructor
     public BigMeteor(double x, double y, SPACESHIPSTATE SSS)
    {   
        this.singleplayer = true;
        this.SSS = SSS;
        this.x = x;
        this.y = y;
        initialize();
        speed = r.nextInt(2) + 1 * 2;
        System.out.println("Big Meteor Generated with Y Speed: !" + speed);
        Animation = new Anime(bimageArray[0], bimageArray[1], bimageArray[2],bimageArray[3], bimageArray[4], bimageArray[5], 30);
        
    
    }
    ///for multplayer state
    public BigMeteor(MULTIPLAYERSTATE MPS, String id, double x, double y)
    {   
        this.singleplayer = false;
        this.MPS = MPS;
        this.id = id;
        this.x = x;
        this.y = y;
        initialize();
        System.out.println("Big Meteor Made!");
        Animation = new Anime(bimageArray[0], bimageArray[1], bimageArray[2],bimageArray[3], bimageArray[4], bimageArray[5], 30);
        
    
    }
    //for server
    public BigMeteor(String id, double x, double y)
    {   
        this.id = id;
        this.x = x;
        this.y = y;
        System.out.println("Metoer: Created For Sever Management");
    }
    @Override
    public void initialize()
    {
            try
            { 
                
                LoadImage loader = new LoadImage();
                bImage = loader.LoadImage("/Resources/BigMetz.png");
                SpriteSheet = new LoadSpriteSheet(this.returnImage());
                
                bimageArray[0] = SpriteSheet.grabSprite128(1, 1, 128, 128);
                bimageArray[1] = SpriteSheet.grabSprite128(2, 1, 128, 128);
                bimageArray[2] = SpriteSheet.grabSprite128(3, 1, 128, 128);
                bimageArray[3] = SpriteSheet.grabSprite128(1, 2, 128, 128);
                bimageArray[4] = SpriteSheet.grabSprite128(2, 2, 128, 128);
                bimageArray[5] = SpriteSheet.grabSprite128(3, 2, 128, 128);
               
                
    
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
    
    }
    
    @Override
    public Rectangle returnPARAMZ()
    {
        return new Rectangle ((int)x,(int)y, 128, 128);
        
    }
     @Override
    public BufferedImage returnImage()
    {
    return bImage;
    }
     @Override
    public void Simulate()
    {
        System.out.println("Dont Need This");
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
       y += .5;
       PacketBigM packet = new PacketBigM(this.id, this.x, this.y);
       packet.writeData(MPS.socketClient);
        }
        else
        {
        y += speed;
        }
       
        Animation.Animate();
    
    }
        public double getX()
    {
        return x;
    }
    public double getY()
    {
        return y;
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
