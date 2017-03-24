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
 * @author EzE
 */
public class MiddleConsole 
{
            private double x;
            private double y;
            private Anime yes;
            private BufferedImage console;
            private BufferedImage[] middle = new BufferedImage[1];
            private Time midconsole = new Time();
            private LoadSpriteSheet lss;
            private Boolean uu;
            
            public MiddleConsole(double x, double y)
            {   
                
                this.uu = false;
                this.x = x;
                this.y = y;
                initialize();
                yes = new Anime(middle[0], 10);
                midconsole.startM();
            
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
            public void update()
            {   
                simulation();
                if(y <= 655)
                {
                    y = 655;
                }
                x+=0;
                y+=0;
                
            yes.Animate();
            }
            public void simulation()
            {
                midconsole.stopM();
                long dis = midconsole.getDurationM();
                if(dis >= 14500)
                {
                    x+=0;
                    y-=.2;
                    
                    
                }
            }
}
