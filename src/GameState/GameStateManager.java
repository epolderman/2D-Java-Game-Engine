/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GameState;

import Main.GamePanel;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;



/**
 *  
 * @author eze
 */
public class GameStateManager 
{
    
    
    ////enums constant of each state
    public static final int MAINMENUSTATE = 0;
    public static final int SPACESHIPSTATEZ = 1;
    public static final int GAMEOVERSTATE = 2;
    public static final int ENDINGSTATE = 3;
    public static final int MULTIPLAYERSTATE = 4;
    private static final int NUMOFSTATES = 5;
    private String difficulty;
    public GamePanel yahs;
   
    
    
    ////array of gamestates that is stored and can be loaded 
    ////through the gamestate manager
    private GameState[] gamestatez;
    private int nowstate;
    
    ////construtor initializes the array with the number 
    ////of gamestates and loads menustate
    ////which would be located in array subscript 0
    public GameStateManager()
    {
        gamestatez = new GameState[NUMOFSTATES];
        
        ////switch the states
        nowstate = MAINMENUSTATE;
        //nowstate = ENDINGSTATE;
        
        injectSTATE(nowstate);
        
        
    }
    public void unloadSTATE(int state)
    {   if(gamestatez[state] == null)
    {
        System.out.println("its null bro");
    }
        gamestatez[state] = null;
    }
    
    
    public void setSTATE(int state)
    {
        unloadSTATE(nowstate);
        
        nowstate = state;
        
        if(nowstate == 1)
        {
            System.out.println("your there");
        }
        injectSTATE(state);
        
        
    
    }
    
    
    
    ////inject state is a switch statement determining which state 
    ///the game is in and will force it if certain variables are met
    public void injectSTATE(int state)
    {
        switch(state)
        {
            case MAINMENUSTATE: 
                ///menustate
                gamestatez[state] = new MENUSTATE(this);
                break;
            case SPACESHIPSTATEZ:
                ////spaceshipstate
                gamestatez[state] = new SPACESHIPSTATE(this,difficulty);
                break;
            case GAMEOVERSTATE:
                ///gameoverstate
                gamestatez[state] = new GAMEOVERSTATE(this);
                break;
            case ENDINGSTATE:
                ///endingstate
                gamestatez[state] = new ENDINGSTATE(this);
                break;
            case MULTIPLAYERSTATE:
                ///multiplayer state
                gamestatez[state] = new MULTIPLAYERSTATE(this,yahs);
                break;
            default:
                System.out.println("Woah... Break... About... To... Happen!");
        }
    }
    ////update method for all objects in the gamestate
    public void update()
    {
        if(gamestatez[nowstate] != null)
    {
        gamestatez[nowstate].update();
    }
    }
    ////draw/render method for each object on the gamestate
    public void render(Graphics2D g)
    { 
        
    if(gamestatez[nowstate] != null)
    {
        gamestatez[nowstate].render(g);
    }
    
    }
    
    public void keyPressed(int z)
    {
        if(gamestatez != null)
        {
        gamestatez[nowstate].keyPressed(z); 
        }
        
    }
     
    public void keyReleased(int z)
    {if(gamestatez != null)
    {
        gamestatez[nowstate].keyReleased(z);
    
    }
    }
    /////for testing purposes
    public int returnit()
    {
        return nowstate;
    }

    public void mousePressed(MouseEvent me) 
    {
        gamestatez[nowstate].mousePressed(me);
    }
    public void setLEVEL(String d)
    {
        this.difficulty = d;
    }
    
   
  
}
