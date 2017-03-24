/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GameState;

import Audio.Audio;
import Console.ControlWindow;
import Console.DifficultyTitle;
import Console.MainConsoleRight;
import Console.MainTitle;
import Console.MenuBarConsole;
import Console.MultiplayerHUB;
import Console.MultiplayerTitle;
import Images.LoadImage;
import Units.MenuFlake;
import Time.Time;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author eze
 */

///MAIN MENU TO THE GAME -> can set diffificulty, look at controls, directs use to single player or multiplayer mode
////EDITS -> maybe tweak the snow size for smaller renders or fewer renders to slow down the object time


public class MENUSTATE extends GameState implements MouseListener
{
    
    /////INSTANCE VARIABLES/////
    private Font title;
    private Color rollover;
    private Font below;
    private Font source;
    private GameStateManager GSM;
    private LoadImage Ramsey;
    private Audio sirens;
    private Time menu;
    private long bebop = 0;
    private MenuFlake snow;
    private LinkedList<MenuFlake> sXXX = new LinkedList<>();
    private Time snowz;
    private long snowd = 0;
    private ControlWindow cw;
    private Time controls;
    private String level;
    private MainConsoleRight MCR;
    private MainTitle MT;
    private MenuBarConsole MBC;
    private MultiplayerTitle MPT;
    private DifficultyTitle DT;
    private MultiplayerHUB MHUB;
    private int currentChoice = 0;
    private String[] levels = {"Easy", "Medium", "Hardened"};
    private String a = "VenomSnake";
    private String[] tips = {"Welcome " + a, "Press C for Controls "," Patch 310.308b Updated"
    , "56 Active Users Online"};
    private int z = 0;
    private Boolean hubclicked;
    private Boolean difhubclicked;
    private Boolean setRight;
    
    
    public MENUSTATE(GameStateManager GSM)
    {       this.hubclicked = false;
            this.difhubclicked = false;
            this.setRight = false;
            this.GSM = GSM;
            initialize();
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.getAllFonts();          
            title = new Font("Source Code Pro", Font.PLAIN, 15);
            below = new Font("Source Code Pro", Font.PLAIN, 20);
            source = new Font("Source Code Pro", Font.PLAIN, 17);
            sirens = new Audio("/Music/mgs5.wav");
            sirens.play();
            menu = new Time();
            menu.startM();
            snowz = new Time();
            snowz.startM();
            rollover = Color.BLACK;
            controls = new Time();
    }
    ///initiliaze objects for all units
    @Override
    public void initialize()
    {
        
        
        try
        {
            Ramsey = new LoadImage("/Resources/untiltheEND.jpg", 1);
           
        }
        catch(Exception x)
        {
            x.printStackTrace();
        }
        
         cw = new ControlWindow(-510,50);
         
         MT = new MainTitle(50,525);
         
         MBC = new MenuBarConsole(0,600);
         
         MPT = new MultiplayerTitle(450, 520);
         
         DT = new DifficultyTitle(600, 518);
        
         MHUB = new MultiplayerHUB(800,0);
         
        
    }
    ///renders for all objects
    @Override
    public void render(Graphics2D g)
    {    
        
        if(sXXX.size() <= 500)
        {
        addSNOW();
        }
        
        Ramsey.render(g);
        
        
         for(int i = 0; i < sXXX.size();i++)
         {   
             
             snow = sXXX.get(i);

             
             snow.render(g);
         }
         cw.render(g);
         MBC.render(g);
         MT.render(g);
         MPT.render(g);
         DT.render(g);
         MHUB.render(g);

        
        g.setColor(Color.WHITE);
        g.setFont(source);
        
        
 
        menu.stopM();
        bebop = menu.getDurationM();
        menu.resetStopM();
        
        long jazz;
        controls.stopM();
        jazz = controls.getDurationM();
        controls.resetStopM();
        
        if(jazz <= 2000)
        {   
            rollover = Color.WHITE;
            g.drawString(tips[z], 200, 640);
            //z++;
        }
        if(jazz > 2100 && jazz <= 2450)
        {
        z++;
        if(z > 3)
        {
        z = 0;
        }
        }
      
        //System.out.println("z = " + z);
        if(jazz >= 4000 )
        {   
            jazz = 0;
            controls.startM();
        }
        //System.out.println("z = " + z);
        if(cw.getX() == -295)
        {
            g.setColor(Color.ORANGE);
            g.setFont(source);
            g.drawString("-CONTROLS-", 15, 100);
            g.drawString("-Special-", 15, 140);
            g.drawString("Boost: SPACEBAR", 15, 180);
            g.drawString("-Weapons-", 15, 220);
            g.drawString("Plasma Missile: S", 15, 260);
            g.drawString("Regular Missile: F", 15, 300);
            g.drawString("-Movements-", 15, 340);
            g.drawString("Left: Left Arrow", 15, 380);
            g.drawString("Right: Right Arrow", 15, 420);
            g.drawString("Backward: Back Arrow", 15, 460);
            g.drawString("Forward: Up Arrow", 15, 500);
        
        }
        
        if(MHUB.returnRight() == true)
        {
            
        g.setFont(below);
        g.setColor(Color.RED);
        g.drawString("Difficulty:", 300, 100);
        g.setColor(Color.ORANGE);
        g.setFont(title);
        int x = 100;
        for(int i = 0; i < levels.length; i++)
        {
            if(i == currentChoice)
            {
                g.setColor(Color.ORANGE);
            }
            else
            {
                g.setColor(Color.WHITE);
            }
            x += 20;
            
            g.drawString(levels[i], 300, x);
        }
        
         g.setColor(Color.WHITE);
         g.drawString("Easy: The game is very forgiving.", 300, 300);
         g.drawString("Medium: The game is not forgiving enough.", 300, 320);
         g.drawString("Hardened: You will not survive.", 300, 340);
       
        
            
            
        
        }
       
        
        
    
    }
   ///update all objects 
    @Override
    public void update()
    { 
        
        
        for(int i = 0; i < sXXX.size();i++)
         {   
             
             snow = sXXX.get(i);

             
             snow.update();
             
             if(snow != null)
             {
                  if(snow.getY() >= 820 || snow.getX() < -45)
                  {
                      snow = null;
                      sXXX.remove(i);
                  }
             
             
             }
          
         }
        
        
      //System.out.println("snow size: " + sXXX.size());
      //LC.update(); 
      MBC.update();
      MT.update();
      cw.update(); 
      MPT.update();
      DT.update();
      MHUB.update();
      Ramsey.update();  
    }
    ///produces the snow effect
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
        
        snow = new MenuFlake(yessir, -25);
        sXXX.add(snow);
        
        
        
    }   
        }  
        else if(snowd >= 2000 && snowd <= 2500)
        {
            snowz.resetStartM();
            snowz.startM();
        }
        
    
    }
    
    
    public void keyPressed(int z)
    {   
        
        //cw.setDONE();
         if(z == KeyEvent.VK_C)
        {
           cw.setDONE();
           
           ///for removing the string level difficulty
           if(MHUB.returnRight() == false)
           {
           setRight = true;
           }
           
           
        }
         
          
        if(z == KeyEvent.VK_UP)
        {
            currentChoice--;
            
            if(currentChoice == -1)
            {
                currentChoice = levels.length - 1;
            }
        }
        
        if(z == KeyEvent.VK_DOWN)
        {
            currentChoice++;
            
            if(currentChoice == levels.length)
            {
                currentChoice = 0;
            }
        }
         
         
   
    }
    public void keyReleased(int z)
    {
    
    }
    public void mousePressed(MouseEvent e)
    {
        
        int mouseX = e.getX();
        int mouseY = e.getY();
        System.out.println(mouseX);
        System.out.println(mouseY);
                          
              ///starts the multiplayer level
              if(mouseX >= 463 && mouseX <= 588)
                {
                    if(mouseY >= 644 && mouseY <= 662)
                    {   
                       
                       select();
                       GSM.setLEVEL(level);
                       GSM.setSTATE(GameStateManager.MULTIPLAYERSTATE);
                       sirens.stop();

                    }
                }
              
              
              
              ///difficulty window is clicked
                if(mouseX >= 619 && mouseX <= 744)
                {
                    if(mouseY >= 640 && mouseY <= 660)
                    {   
                       if(hubclicked == true)
                        {
                            System.out.println("Hub is already out");
                       }
                        else
                        {     
                            
                           DT.setDONE();
                           MHUB.setDONE();
                           cw.setDONE();
                           
                           if(difhubclicked == false)
                           {
                           difhubclicked = true;
                           }
                           else
                           {
                               difhubclicked = false;
                           }
                           
                        }
                      
                        
             
                    }
                }
                
                
                
                ////single player level
                if(mouseX >= 53 && mouseX <= 179)
                {
                   if(mouseY >= 621 && mouseY <= 679)
                    {   
                       
                        sirens.stop();
                        select();
                       GSM.setLEVEL(level);
                       GSM.setSTATE(GameStateManager.SPACESHIPSTATEZ);
             
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
    public void mouseExited(MouseEvent me) 
    {
         
    }
    ///for selecting the difficulty
     private void select()
    {
        if(currentChoice == 0)
        {
            level = "Easy";
        }
        
        if(currentChoice == 1)
        {
            level = "Medium";
        }
        
        if(currentChoice == 2)
        {
            level = "Hardened";
        }
    }
 
    

    
}
