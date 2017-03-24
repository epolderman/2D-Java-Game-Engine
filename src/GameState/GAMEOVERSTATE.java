/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GameState;

import Audio.Audio;
import Console.EndingConsole;
import Images.LoadImage;
import Time.Time;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author eze
 */
class GAMEOVERSTATE extends GameState implements MouseListener{
    
    ///FIXED* fix the key released dead scheme thing going on *FIXED MOFO
    
    private GameStateManager GSM;
    private Font end;
    private Font title;
    private Font yahs;
    private Color color;
    private LoadImage Toby;
    private Audio desperation;
    private Time keepit;
    private Time menu;
    private Audio sirens;
    private EndingConsole ec;
    private Font name;
    
    
    public GAMEOVERSTATE(GameStateManager GSM) 
    {
            this.GSM = GSM;
        
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.getAllFonts();
            initialize();
            color = new Color(255,98,88);
            end = new Font("Source Code Pro", Font.PLAIN, 60);
            title = new Font("Source Code Pro", Font.ITALIC,20);
            yahs = new Font("Source Code Pro", Font.PLAIN,20);
            menu = new Time();
            sirens = new Audio("/Music/woah.wav");
            sirens.play();
            name = new Font("Source Code Pro", Font.PLAIN,12);
            
          
            
        
    }
    public void initialize()
    {
         try
        {
            Toby = new LoadImage("/Resources/gameoverstate.jpg", 1);
           
        }
        catch(Exception x)
        {
            x.printStackTrace();
        }

          ec = new EndingConsole(140,800);
    
    }
    public void update()
    {
        Toby.update();
        ec.update();
    }
    public void render(Graphics2D g)
    {   
        Toby.render(g);
        
        g.setColor(Color.WHITE);
        g.setFont(end);
        g.drawString("YOU ARE DEAD", 350, 100);
        g.setColor(Color.WHITE);
        g.setFont(title);
        long bebop;
        
        menu.stopM();
        bebop = menu.getDurationM();
        menu.resetStopM();
        
        //g.drawString("Get Revenge", Revenge.x + 2, Revenge.y + 28);
        if(bebop >= 2000)
        {
             g.drawString("Angel's banished from Heaven", 350, 175);   
        
        }
        
        if(bebop >= 5000)
        {
            g.drawString("have no choice but to become devil's.", 350, 200);  
        }
        
        if(bebop >= 9000)
        {
            g.drawString("--Vicious", 570, 245);  
        }
        
        if(bebop >= 14000)
        {
                menu.resetStartM();
                menu.startM();
        }
        
        
        
        g.setFont(yahs);
        g.setColor(Color.RED);
        ec.render(g);
        if(ec.getDONE() == true)
        {
        g.drawString("Restart", 489, 595);
        g.drawString("Back to Main Menu", 200,595);
        g.setColor(Color.WHITE);
        g.setFont(name);
        g.drawString("Until the End", 345,685);
        }
        
        
    }
    public void keyPressed(int k)
    {
        if(k == KeyEvent.VK_RIGHT)
        {
            System.out.println("Game is Over -> Controls Turned Off");
        }  
        
        if(k == KeyEvent.VK_UP)
        {
           System.out.println("Game is Over -> Controls Turned Off");
        }  
        
        if(k == KeyEvent.VK_LEFT)
        {
           System.out.println("Game is Over -> Controls Turned Off");
        }  
        
        if(k == KeyEvent.VK_DOWN)
        {
           System.out.println("Game is Over -> Controls Turned Off");
        }  
    }
    public void keyReleased(int k)
    {
         
       if(k == KeyEvent.VK_RIGHT)
        {
            System.out.println("Game is Over -> Controls Turned Off");
        }  
        
        if(k == KeyEvent.VK_UP)
        {
           System.out.println("Game is Over -> Controls Turned Off");
        }  
        
        if(k == KeyEvent.VK_LEFT)
        {
           System.out.println("Game is Over -> Controls Turned Off");
        }  
        
        if(k == KeyEvent.VK_DOWN)
        {
           System.out.println("Game is Over -> Controls Turned Off");
        }  
    }
    public void mousePressed(MouseEvent e)
    {
                int mouseX = e.getX();
                int mouseY = e.getY();
                        //System.out.println(mouseX);
                        //System.out.println(mouseY);
                 
                
                if(mouseX >= 499 && mouseX <= 725)
                {
                    if(mouseY >= 575 && mouseY <= 611)
                    {   
                        GSM.setSTATE(GameStateManager.SPACESHIPSTATEZ);
                        sirens.stop();
             
                    }
                }
                
                if(mouseX >= 210 && mouseX <= 420)
                {
                    if(mouseY >= 575 && mouseY <= 611)
                    {   
                        GSM.setSTATE(GameStateManager.MAINMENUSTATE);
                        sirens.stop();
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

