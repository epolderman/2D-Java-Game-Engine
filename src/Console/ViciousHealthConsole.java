/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Console;

import GameState.SPACESHIPSTATE;
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
public class ViciousHealthConsole 
{
            private double x;
            private double y;
            private Anime yes;
            private BufferedImage console;
            private BufferedImage[] middle = new BufferedImage[1];
            private LoadSpriteSheet lss;
            private Boolean uu = false;
            private SPACESHIPSTATE xx;
            private Boolean there;
            
            public ViciousHealthConsole(double x, double y)
            {   this.there = false;
                this.x = x;
                this.y = y;
                initialize();
                yes = new Anime(middle[0], 10);
            
            }
            private void initialize()
            {   
                try
                {
                    LoadImage loader = new LoadImage();
                    console = loader.LoadImage("/Resources/BlueC.png");
                    lss = new LoadSpriteSheet(console);
                    
                    middle[0] = lss.grabSprite128(1, 2, 128, 128);
                    
                    
                
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                
                
                
            }
            public void render(Graphics2D g)
            {
            yes.constructIT(g, x, y);
            }
            public void update(SPACESHIPSTATE xx)
            {   
                if(xx.returnHOOKAH() == true)
               {
                    x+=0;
                    y+=.2;
                }
                
                if(y >= -94)
                {   
                    there = true;
                    y = -94;
                }
                
                x+=0;
                y+=0;
                
            yes.Animate();
            }
            
            
            public Boolean returnTHERE()
            {
                return there;
            }
           
    
    
}
