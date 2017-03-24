/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Images;

import Main.GamePanel;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author eze
 */
public class LoadImage {
    
     private BufferedImage image;
     private double x;
     private double y;
     private double dx;
     private double dy;
     private double movescale;
    
     public LoadImage(String z, double ms)
     {  
         try
         {
         image = ImageIO.read(getClass().getResourceAsStream(z));
         movescale = ms;
         }
         catch(Exception e)
         {
         e.printStackTrace();
         }
     }
    public LoadImage()
    {
    
    }
    public BufferedImage LoadImage(String path) throws IOException
    {
        image = ImageIO.read(getClass().getResourceAsStream(path));
        
        return image;
    }
    public void setPosition(double x, double y)
    {
        this.x = (x * movescale) % GamePanel.WIDTH;
        this.y = (y * movescale) % GamePanel.HEIGHT;
    }
    
    public void scroll(double dx, double dy)
    {
        this.dx = dx;
        this.dy = dy;
    
    }
       public void update()
    {
        x += dx;
        y += dy;
    
    }
    
    public void render(Graphics2D g)
    {
         g.drawImage(image, (int)x, (int)y, null);
        
        if(y < 0)
        {
            if(y <= -GamePanel.HEIGHT)
            {
                y = 0;
            }
            g.drawImage(image, (int)x, (int)y + GamePanel.HEIGHT, null);
        }
        
        if(y > 0)
        {
            if(y >= GamePanel.HEIGHT)
            {
                y = 0;
            }
            g.drawImage(image, (int)x, (int)y - GamePanel.HEIGHT, null);
        }
        
        
    
    }
    
    public BufferedImage returnSpriteSheet()
    {
        return image;
    }
 
    
    
}
