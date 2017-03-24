/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GameState;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

/**
 *
 * @author eze
 */
public abstract class GameState {
    
    
    
    protected GameStateManager GSM;
    
    public abstract void initialize();
    public abstract void update();
    public abstract void render(Graphics2D g);
    public abstract void keyPressed(int z);
    public abstract void keyReleased(int z);
    public abstract void mousePressed(MouseEvent e);
    
}

    

