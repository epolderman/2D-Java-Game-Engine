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
import Packets.PacketRed;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author eze
 */

//Description: Blunt Force Enemies fly toward the enemy and try to kill them by
//suicide, blunt force, or kamakazi style
///a.k.a. RedEnemy

public class BluntForceEnemy extends ShellUnit
{

        private BufferedImage[] bimageArray = new BufferedImage[2];
        private Random rand;
        private double movementy;
        private MULTIPLAYERSTATE MPS;
        private String id; 
        private SPACESHIPSTATE SSS;
        private boolean singleplayer;
    
        //single player
     public BluntForceEnemy(double x, double y, SPACESHIPSTATE SSS)
        {  
            rand = new Random();
            this.singleplayer = true;
            this.SSS = SSS;
            this.x = x;
            this.y = y;
            initialize();
            Animation = new Anime(bimageArray[0],bimageArray[1], 19);
            System.out.println("Blunt Force Enemy SinglePlayer");
            double lower = 1;
            double upper = 4;
            movementy = Math.random() * (upper - lower) + lower;
        
        
        }
     
      ///multiplayer
         public BluntForceEnemy(MULTIPLAYERSTATE MPS, String id, double x, double y)
        {   
            this.singleplayer = false;
            this.MPS = MPS;
            this.id = id;
            this.x = x;
            this.y = y;
            initialize();
            Animation = new Anime(bimageArray[0],bimageArray[1], 19);
            System.out.println("Blunt Force Enemy Multiplayer");
     
        }
         
          ///server
         public BluntForceEnemy(String id, double x, double y)
        {   
            System.out.println("Blunt Force Enemy");
            this.id = id;
            this.x = x;
            this.y = y;

        }
        
    
    
    
    @Override
    public void initialize() 
    {
        
            try
            {   
                LoadImage loader = new LoadImage();
                bImage = loader.LoadImage("/Resources/Enemies.png");
                SpriteSheet = new LoadSpriteSheet(this.returnImage());
                
                bimageArray[0] = SpriteSheet.grabSprite64(1, 2, 64, 64);
                bimageArray[1] = SpriteSheet.grabSprite64(2, 2, 64, 64);
              
            
            }
            catch(Exception e)
            {
            e.printStackTrace();
            }
     
    }

    @Override
    public void update() 
    {          
               Simulate();
            
              
            
                if(singleplayer == false)
                {
                
                x += 0;
                y += .9;
                PacketRed packet = new PacketRed(this.id, this.x, this.y);
                packet.writeData(MPS.socketClient);
                
                }
                else
                {
                x += 0;
                y += movementy;
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
        if(y >= 325)
            {
                x -= 1.5;
                y += 4;
            }
            
    }

    @Override
    public Rectangle returnPARAMZ() 
    {
      return new Rectangle((int)x,(int)y, 64,64);
    }
     public double returnX()
    {
        return x;
    }
    public double returnY()
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
