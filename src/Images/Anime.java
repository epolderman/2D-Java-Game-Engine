/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Images;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *  /////animation class to iterate through frames for each sprite
 * @author eze
 */
public class Anime 
{
    ////INSTANCE VARIABLES///
    private BufferedImage i1;
    private BufferedImage i2;
    private BufferedImage i3;
    private BufferedImage i4;
    private BufferedImage i5;
    private BufferedImage i6;
    private BufferedImage i7;
    private BufferedImage i8;
    private BufferedImage i9;
    
    private int count = 0;
    private int speed;
    private int imageC;
    private BufferedImage current;
    private Boolean anime = true;
    private int iterator = 0;
    
    
    ////for 1 frame animation
    ////the higher the speed the slower the frame switch is
    public Anime(BufferedImage i1, int speed)
    {
        this.i1 = i1;
        this.speed = speed;
        imageC = 1;
    }
    ///for 2 frame animation
    public Anime(BufferedImage i1, BufferedImage i2, int speed)
    {
        this.i1 = i1;
        this.i2 = i2;
        this.speed = speed;
        imageC = 2;
    }
    ////for 3 frame animation
    public Anime(BufferedImage i1, BufferedImage i2, BufferedImage i3, int speed)
    {
        this.i1 = i1;
        this.i2 = i2;
        this.i3 = i3;
        this.speed = speed;
        imageC = 3;
    }
    ///4 frame animation
    public Anime(BufferedImage i1, BufferedImage i2, BufferedImage i3, BufferedImage i4, int speed)
    {
        this.i1 = i1;
        this.i2 = i2;
        this.i3 = i3;
        this.i4 = i4;
        this.speed = speed;
        imageC = 4;
    }
    ////5 frame animation
    public Anime(BufferedImage i1, BufferedImage i2, BufferedImage i3, BufferedImage i4, BufferedImage i5, int speed)
    {
        this.i1 = i1;
        this.i2 = i2;
        this.i3 = i3;
        this.i4 = i4;
        this.i5 = i5;
        this.speed = speed;
        imageC = 5;
    }
    ////6 frame animation for big meteor
    public Anime(BufferedImage i1, BufferedImage i2, BufferedImage i3, BufferedImage i4, BufferedImage i5, BufferedImage i6, int speed)
    {
        this.i1 = i1;
        this.i2 = i2;
        this.i3 = i3;
        this.i4 = i4;
        this.i5 = i5;
        this.i6 = i6;
        this.speed = speed;
        imageC = 6;
    }
    ////adjust speed of frames for the animation
    public void Animate()
    {
            iterator++;
            
            if(iterator == speed)
            {  
                iterator = 0;
                next();
               
            }
          
    
    }
    /////finds the numbers of frames and iterates through them in an if/else statement
    public void next()
    {
            if(imageC == 1)
            {
                current = i1;

            }
            
            else if(imageC == 2)
            {   
                if(count == 0)
                {
                    
                current = i1;
                
                }
                else if(count == 1)
                {
                    
                current = i2;
                
                }
                
                count++;
                
                if(count > imageC)
                { 
                    count = 0;
                }                
                
            }
            else if(imageC == 3)
            {
                if(count == 0)
                {
                    
                current = i1;
                
                }
                else if(count == 1)
                {
                    
                current = i2;
                
                }
                else if(count == 2)
                {
                    
                current = i3;
                    
                }
                count++;
                
                if(count > imageC)
                    count = 0;
            
            }
            else if(imageC == 4)
            {
                 if(count == 0)
                {
                    
                current = i1;
                
                }
                else if(count == 1)
                {
                    
                current = i2;
                
                }
                else if(count == 2)
                {
                    
                current = i3;
                    
                }
                else if(count == 3)
                {
                current = i4;
                }
                 
                count++;
                if(count > imageC)
                count = 0;
            
            }
            else if(imageC == 5)
            {
             if(count == 0)
                {
                    
                current = i1;
                
                }
                else if(count == 1)
                {
                    
                current = i2;
                
                }
                else if(count == 2)
                {
                    
                current = i3;
                    
                }
                else if(count == 3)
                {
                current = i4;
                }
                else if(count == 4)
                {
                    current = i5;
                }
                 
                count++;
                if(count > imageC)
                count = 0;
            
            
            }
            else if(imageC == 6)
            {
             if(count == 0)
                {
                    
                current = i1;
                
                }
                else if(count == 1)
                {
                    
                current = i2;
                
                }
                else if(count == 2)
                {
                    
                current = i3;
                    
                }
                else if(count == 3)
                {
                current = i4;
                }
                else if(count == 4)
                {
                    current = i5;
                }
                else if(count == 5)
                {
                    current = i6;
                }
                 
                count++;
                if(count > imageC)
                count = 0;
            
                    
                    
                    }
    
    
    
    }
    ////draw the frames 1 by 1 bruh
    public void constructIT(Graphics2D g, double x, double y)
    {
        g.drawImage(current, (int)x , (int)y, null);
    
    }
    
    
    
}
