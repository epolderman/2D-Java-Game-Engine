/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Main;



import GameState.GameStateManager;
import NetProgramming.ClientUDP;
import NetProgramming.ServerUDP;
import Time.Time;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 *
 * @author eze
 * //////    Game Speed is the number of times the game state gets updated per second   ////
 * //////     in other words...the number of times update game gets called   /////
 * //////    FPS is frames per second and how many times render game gets called //////
 */
public class GamePanel extends Canvas implements Runnable,KeyListener,MouseListener
{
    ////DIMENSIONS FOR THE GAME////
    public static final int WIDTH = 800; 
    public static final int HEIGHT = 690;
    
   
    
    //////GAME INSTANCE VARIABLES////
    private Thread ozil; ////mainthread
    private Boolean game_is_running = false; ///boolean value to keep game running
    private static final double FPS = 60.0; ////your frames per second
    private static final double TICKS = 1000000000.0 / FPS; /////number of updates
    Time theTime = new Time(); ////time class to determine time from milliseconds
    private BufferedImage IMG = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB); ////have to get the images from the resource file   
    GameStateManager GSM;
    private Graphics2D g;
    private ClientUDP socketClient;
    private ServerUDP socketServer;
   
    public GamePanel()
    {
        
        setFocusable(true);
        requestFocus();
        addKeyListener(this);
        addMouseListener(this);
        
    } 
    ///////INITIALIZE GSM because it handles all the initializations of every state/////
    private void initialize()
    {   
        IMG = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) IMG.getGraphics();
        GSM = new GameStateManager();
        
        
        
    }
   ///////END INITIALIZATION////////////////////////////
    
    ////////GAME ENGINE///HEART OF THE GAME///////
    public void run() 
    {   
        initialize();
        
        theTime.start();
        long timer = System.currentTimeMillis();
        double delay = 0;
        int frames = 0;
        int updates = 0;
        
       
        
        
        while(game_is_running)
        {   
            
            theTime.stop();
            long duration = theTime.getDuration();
            delay += duration / TICKS;
            theTime.equal();
            
            while (delay >= 1)    
            {
                update();
                updates++;
                delay--;
            }
                render();
                BufferThatBitch();
                frames++;
                
                if (System.currentTimeMillis() - timer > 1000)
                {
                    timer += 1000;
                    //System.out.println("Updates = " + updates);
                    //System.out.println("Frames = " + frames);
                    //System.out.println("width ="  + getWidth());
                    //System.out.println("height = " + getHeight());
                    updates = 0;
                    frames = 0;
                }
        }   
        
        stop();
  }
    //////END GAME ENGINE////
    
  
        
        
        
    ///////BEGIN THREAD and END THREAD METHODS///    
    protected synchronized void start()
    {
            game_is_running = true;
         
        
        ozil = new Thread(this);
        ozil.start();
            
            
    }
    protected synchronized void stop() 
    {
           
            game_is_running = false;
        try
            {
            ozil.join();
            }
            
        catch(Exception x)
            {
                x.printStackTrace();
            }
         
            System.exit(0);
        
        
    }
    ////////END THREAD BEGIN AND STOP///////////
    
    
    /////FPS is dictated this way/////
    private void render()
    {  
       /////game state manager dictates which state/objects are drawn   
        GSM.render(g); 
    }
    private void BufferThatBitch()
    {   
        
        BufferStrategy BUFF = getBufferStrategy();  
        if(BUFF == null)    ///create bufferStrat to increase graphics loading time
        {                   ////temporary storage
            createBufferStrategy(3);    ////improves performance
            return;                     
        }
        
        
        
        
        Graphics g2 = BUFF.getDrawGraphics();
        g2.drawImage(IMG, 0, 0, getWidth(), getHeight(), null);
        g2.dispose();
        BUFF.show();
        
    }
    
    
    ////////the game speed update method can be named TICK//////
    private void update()
    {   
        
       GSM.update();
      
    }
    /////////end TICK method////////////////////

    //////////KEYBOARD INPUT METHODS BEGIN/////////
    public void keyTyped(KeyEvent ke) 
    {
     
    }
    public void keyPressed(KeyEvent ke) 
    {
        GSM.keyPressed(ke.getKeyCode());
    }
    public void keyReleased(KeyEvent ke) 
    {
        GSM.keyReleased(ke.getKeyCode());
    }
    ///////////END KEYBOARD INPUT/////////////

    @Override
    public void mouseClicked(MouseEvent me) 
    {
        
    }

    @Override
    public void mousePressed(MouseEvent me) 
    {
        GSM.mousePressed(me);
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        
    }

    @Override
    public void mouseEntered(MouseEvent me) {
      
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }

  


    
    
}
