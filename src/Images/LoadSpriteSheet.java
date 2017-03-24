/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Images;

import java.awt.image.BufferedImage;

/**
 * ...load sprite sheet class. grabs the different size
 * ...each object within the game                      
 * @author eze
 */
public class LoadSpriteSheet {
    
    private final BufferedImage image;
    
    public LoadSpriteSheet(BufferedImage image)
    {
        this.image = image;
  
    }
    
    ////for 32 x 32 sprites on your sprite sheet
    public BufferedImage grabSprite32(int col, int row, int width, int height){
    
        BufferedImage img = image.getSubimage((col * 32) - 32, (row * 32) - 32, width , height);
        return img;
    }
    ////for 64 x 64 sprites
    public BufferedImage grabSprite64(int col, int row, int width, int height){
    
        BufferedImage img = image.getSubimage((col * 64) - 64, (row * 64) - 64, width , height);
        return img;
    }
    ////for 128 x 128 sprites(the bebop)
    public BufferedImage grabSprite128(int col, int row, int width, int height)
    {
        BufferedImage img = image.getSubimage((col * 128) - 128, (row * 128) - 128, width, height);
        return img;
    }
    ////for vicious
    public BufferedImage grabSprite256(int col, int row, int width, int height)
    {
        BufferedImage img = image.getSubimage((col * 256) - 256, (row * 256) - 256, width, height);
        return img;
    }
    public BufferedImage grabSpriteARM(int col, int row, int width, int height)
    {
        BufferedImage img = image.getSubimage((col * 128) - 128, (row * 384) - 384, width, height);
        return img;
    }
    
    /////for the main console in the swordfish
    public BufferedImage grabSprite640(int col, int row, int width, int height)
    {
        BufferedImage img = image.getSubimage((col * 898) - 898, (row * 898) - 898, width, height);
        return img;
    }
    /////control console
     public BufferedImage grabSprite512(int col, int row, int width, int height)
    {
        BufferedImage img = image.getSubimage((col * 512) - 512, (row * 512) - 512, width, height);
        return img;
    }
   
}
