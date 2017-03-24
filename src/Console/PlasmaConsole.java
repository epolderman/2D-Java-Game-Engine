/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Console;

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
public class PlasmaConsole 
{
    ////INSTANCE VARIABLES
    private double x;
    private double y;
    private BufferedImage plasma;
    private LoadSpriteSheet enchilada;
    private BufferedImage[] carbs = new BufferedImage[3];
    private BufferedImage[] carbz = new BufferedImage[3];
    private Time ozil;
    private Anime kenshin;
    private Anime kenshin2;
    public Boolean special;
    private long duration;
    private Boolean yep;
   
    
    
    
     public PlasmaConsole(double x, double y)
     {   this.special = false;
         this.yep = false;
         this.duration = 0;
         this.x = x;
         this.y = y;
         initialize();
         ozil = new Time();
         ozil.startM();
         kenshin = new Anime(carbs[0], carbs[1], carbs[2], 10);
         kenshin2 = new Anime(carbz[0], carbz[1], carbz[2], 30);
     
     
     }
     private void initialize()
     {
         try
         {  
             
             LoadImage loader = new LoadImage();
             plasma = loader.LoadImage("/Resources/Yessir.png");
             enchilada = new LoadSpriteSheet(this.returnPLASMA());
             
             carbs[0] = enchilada.grabSprite128(1, 2, 128, 128);
             carbs[1] = enchilada.grabSprite128(2, 2, 128, 128);
             carbs[2] = enchilada.grabSprite128(3, 2, 128, 128);
             
             carbz[0] = enchilada.grabSprite128(1, 3, 128, 128);
             carbz[1] = enchilada.grabSprite128(2, 3, 128, 128);
             carbz[2] = enchilada.grabSprite128(3, 3, 128, 128);       
             
             
             
         }
         catch(Exception e)
         {
            e.printStackTrace();
         }
         
     
     }
     private void simulation()
     {
         ozil.stopM();
         duration = ozil.getDurationM();
         ozil.resetStopM();
         
         if(duration >= 14500)
         {
             yep = true;
         
         }
         
         if(x <= 678)
         {
             x = 678;
         }
         
     
     }
     public void render(Graphics2D g)
     {      
         if(special == true)
         {
         kenshin.constructIT(g, x, y);
         }
         
         else if(special == false)
         {
         kenshin2.constructIT(g, x, y);
         }
     }
     public void update()
     {  
         simulation();
         
         if(yep == true)
         {
             x -= .6;
             y += 0;
         
         }
         
         
         if(special == true)
         {
         kenshin.Animate();
         }
         else if(special == false)
         {
         
         kenshin2.Animate();
         }
     }
     public BufferedImage returnPLASMA()
     {
         return plasma;
     
     }
     public void setPLASMA(int z)
     {  
         if(z == 1)
         { 
             special = false;
         }
         else
         { 
             special = true;
         }
     
     
     
     }
    
    
    
}
