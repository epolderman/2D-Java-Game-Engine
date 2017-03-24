/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GameState;

import Audio.Audio;
import Console.EndingConsole;
import Images.LoadImage;
import Units.EndingFlake;
import Time.Time;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author eze
 */
public class ENDINGSTATE extends GameState implements MouseListener{
    
    
        
    
        private GameStateManager GSM;
        private LoadImage dwight;
        private Time menu;
        private Audio revenge;
        private Font bold;
        private Font yahs;
        private Color color;
        private EndingFlake snow;
        private LinkedList<EndingFlake> sXXX = new LinkedList<>();
        private Time snowz;
        private long snowd;
        private EndingConsole ec;
        private Audio sirens;
        private Font name;
    
    
    public ENDINGSTATE(GameStateManager GSM) 
    {
            this.snowd = 0;
            this.GSM = GSM;
            initialize();
            color = new Color(255,98,88);
            yahs = new Font("Source Code Pro", Font.PLAIN, 20);
            bold = new Font("Source Code Pro", Font.PLAIN, 25);
            menu = new Time();
            menu.startM();
            snowz = new Time();
            snowz.startM();
            ec = new EndingConsole(140,800);
            sirens = new Audio("/Music/woah.wav");
            sirens.play();
            name = new Font("Source Code Pro", Font.PLAIN,12);
          
        
    }

    
        @Override
    public void initialize()
    {
        try
        {
            dwight = new LoadImage("/Resources/endingTHEMEyahs.jpg", 1);
           
        }
        catch(Exception x)
        {
            x.printStackTrace();
        }
        
        
        
        
        
    }
        @Override
    public void update()
    {
        
        for(int i = 0; i < sXXX.size();i++)
         {   
             
             snow = sXXX.get(i);

             
             snow.update();
             
             if(snow.getY() >= 820 && snow != null)
             {
                 sXXX.remove(i);
             }
             
             if(snow.getX() < -45 )
             {
                 sXXX.remove(i);
             }
             
             
             
         
         }
        
        
        dwight.update();
        ec.update();
        
        
    }
    public void addSNOW()
    {   
        
        snowz.stopM();
        snowd = snowz.getDurationM();
        snowz.resetStopM();
        
        
        if(snowd <= 1000)
        {
        Random rand = new Random();
        for(int z = 0;z <= 2;z++)
    {   
        int yessir = rand.nextInt(1000) + 1;
        
        snow = new EndingFlake(yessir, -25);
        sXXX.add(snow);
        
        
        
    }   
        }  
        else if(snowd >= 2000 && snowd <= 2500)
        {
            snowz.resetStartM();
            snowz.startM();
        }
        
    
    }
    
        @Override
    public void render(Graphics2D g)
    {    
       if(sXXX.size() <= 500)
       {
         addSNOW();
       }
       
         dwight.render(g);
         
         for(int i = 0; i < sXXX.size();i++)
         {   
             
             snow = sXXX.get(i);

             
             snow.render(g);
             
             
         
         }
         
        g.setFont(yahs);
        g.setColor(Color.PINK);
        long bebop;
        
        menu.stopM();
        bebop = menu.getDurationM();
        menu.resetStopM();
        
        
       
       
        
        ec.render(g);
        
        if(ec.getDONE() == true)
        {
        if(bebop >= 2000)
        {
             g.drawString("Revenge is a dish", 150, 565);   
        
        }
        
        if(bebop >= 4000)
        {
            g.drawString("best served cold.", 150, 600);  
        }
        
      
       
        
        if(bebop >= 10000)
        {
                menu.resetStartM();
                menu.startM();
        }    
            
            
            
            
        g.setColor(Color.GREEN);
        g.setFont(bold);
        g.drawString("Main Menu", 500, 565);
        g.setFont(name);
        g.setColor(Color.GREEN);
        g.drawString("Until the End", 345,685);
        
        
        
        }
    }
    public void keyPressed(int k)
    {
    
    }
    public void keyReleased(int k)
    {
    
    }
        @Override
    public void mousePressed(MouseEvent e)
    {
         int mouseX = e.getX();
         int mouseY = e.getY();
        
         if(mouseX >= 500 && mouseX <= 700)
                {
                    if(mouseY >= 565 && mouseY <= 620)
                    {   
                        sirens.stop();
                        GSM.setSTATE(GameStateManager.MAINMENUSTATE);
                        
             
                    }
                }
    
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
      
    }

    @Override
    public void mouseReleased(MouseEvent me) {
       
    }

    @Override
    public void mouseEntered(MouseEvent me) 
    {
              
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
         
    }
   
    
    
}
