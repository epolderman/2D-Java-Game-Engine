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
import Packets.PacketGreen;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author eze
 */

//Description: This enemy is the only enemy that shoots bullets
//a.k.a. GreenEnemy
public class GunEnemy extends ShellUnit
{
    
    
        private BufferedImage[] bimageArray = new BufferedImage[2];
        private Random rand;
        private double movementx;
        private double movementy;
        private MULTIPLAYERSTATE MPS;
        private String id;
        public int hitpoints;
        private boolean dead;
        private SPACESHIPSTATE SSS;
        private boolean singleplayer;
 
        ///single player
         public GunEnemy(double x, double y, SPACESHIPSTATE SSS)
        {   
            rand = new Random();
            this.movementx = 0;
            this.movementy = 0;
            this.singleplayer = true;
            this.SSS = SSS;
            this.x = x;
            this.y = y;
            initialize();
            Animation = new Anime(bimageArray[0],bimageArray[1], 19);
            System.out.println("Gun Enemy Made");
            double lower = .1;
            double upper = .4;
            movementx = Math.random() * (upper - lower) + lower;
            movementy = Math.random() * (upper - lower) + lower;
        
        
        }
        ///multiplayer
         public GunEnemy(double x, double y, MULTIPLAYERSTATE MPS, String id)
        {   
            this.singleplayer = false;
            this.dead = false;
            this.hitpoints = 15;
            this.id = id;
            this.MPS = MPS;
            this.x = x;
            this.y = y;
            initialize();
             Animation = new Anime(bimageArray[0],bimageArray[1], 19);
            System.out.println("Gun Enemy Made");
    
        }
         //server
         public GunEnemy(String id, double x, double y)
        {   
            this.hitpoints = 15;
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
                
                bimageArray[0] = SpriteSheet.grabSprite64(1, 3, 64, 64);
                bimageArray[1] = SpriteSheet.grabSprite64(2, 3, 64, 64);
             
            
            }
            catch(Exception e)
            {
            e.printStackTrace();
            }
    }

    @Override
    public void update() 
    {
                
                if(singleplayer == false)
                {
                x += 0;
                y += .3;
                PacketGreen packet = new PacketGreen(this.id, this.x, this.y, this.hitpoints);
                packet.writeData(MPS.socketClient);
                }
                else
                {
                x += movementx;
                y += movementy;
                Simulate();
                
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
         
            
            if(x <= -15)
            {
                x+= .5;
            }
            //x = -15;
            if(x >= 680)
            {
                x-=.5;
            }
           
    }

    @Override
    public Rectangle returnPARAMZ() 
    {
          return new Rectangle ((int)x,(int)y, 64, 64);
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
         public String returnID()
         {
         return id;
         }
         public void setHITS(int value)
         {
             hitpoints = hitpoints - value;
             
             if(hitpoints <= 0)
             {
                 dead = true;
             }
         }
         public boolean checkDeath()
         {
             return dead;
         
         }
         public int returnHP()
         {
             return hitpoints;
         }
    
    
    
}
