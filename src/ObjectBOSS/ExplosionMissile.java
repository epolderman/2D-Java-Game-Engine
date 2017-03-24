/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ObjectBOSS;

import Images.Anime;
import Images.LoadImage;
import Images.LoadSpriteSheet;
import Units.SwordFish;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author eze
 */
public class ExplosionMissile 
{
    private double x;
    private double y;
    private Anime EM;
    private BufferedImage WV;
    private BufferedImage[] JIMHALPERT = new BufferedImage[3];
    private LoadSpriteSheet conflict;
    private Random rand;
    private SwordFish xx;
    private Vicious yy;
    private Boolean ready;
    
    public ExplosionMissile(double x, double y)
    {
    this.rand = new Random();
    this.ready = false;
    this.x = x;
    this.y = y;
    initialize();
    EM = new Anime(JIMHALPERT[0],JIMHALPERT[1],JIMHALPERT[2], 20);
    System.out.println("Explosion Missile Generated");
    
    }
    private void initialize()
    {
        try{
                LoadImage loader = new LoadImage();
                WV = loader.LoadImage("/Resources/Scranton.png");
                conflict = new LoadSpriteSheet(this.returndatDOUGH());
                
                JIMHALPERT[0] = conflict.grabSprite128(1, 3, 128, 128);
                JIMHALPERT[1] = conflict.grabSprite128(2, 3, 128, 128);
                JIMHALPERT[2] = conflict.grabSprite128(3, 3, 128, 128);
                
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    
    }
    public void render(Graphics2D g)
    {
        EM.constructIT(g, x, y);
    }
    public void update(SwordFish xx)
    {   
        
        if(y >= 250)
        {   
            x += 0;
            y = 250;
            ready = true;
        }
         
        
        if(xx.getX() >= 0 && xx.getX() <= 150 && this.returnX() >= 300)
        {
            x -= .6;
            y += .8;
        }
        else if(xx.getX() >= 151 && xx.getX() <= 275 && this.returnX() >= 300)
        {
            x -= .2;
            y += .8;
        }
        else if(xx.getX() >= 276 && xx.getX() <= 400 && this.returnX() <= 200)
        {
            x += .2;
            y += .8;
        }
        else if(xx.getX() >= 401 && xx.getX() <= 558 && this.returnX() <= 200)
        {
            x += .6;
            y += .8;
        }
        else
        {
            x += 0;
            y += .9;
        }

        EM.Animate();
    
    }
    public BufferedImage returndatDOUGH()
    {
        return WV;
    }
    public double returnX()
    {
        return x;
    }
    public double returnY()
    {
        return y;
    }
    public Rectangle returnPARAMZ()
    {
        return new Rectangle((int)x,(int)y, 128,128);
    }
    public Boolean returnREADY()
    {
        return ready;
    }
    































}
