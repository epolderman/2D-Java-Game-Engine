/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Main;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author eze
 */
public class Main 
{
        
        
    
        public static void main(String[] args) throws Exception
        {    
            ///DIMENSIONS OF GAME BEBOP//////
            GamePanel Bebop = new GamePanel();
            Bebop.setPreferredSize(new Dimension(810, 700));
            Bebop.setMaximumSize(new Dimension(810, 700));
            Bebop.setMinimumSize(new Dimension(810, 700));
            ////END DIMENSIONS/////
            
            /////SET UP JFRAME//////
            
            JFrame XX;
            XX = new JFrame("Until the End");
            XX.add(Bebop); ////add the game to our jframe
            XX.pack(); ////adds components to the jframe and sizes it accordingly
            XX.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); ////allows us to exit
            XX.setResizable(false); ///cant resize
            XX.setVisible(true); ///makes visible
            XX.setLocationRelativeTo(null); ////GET DESCRIPTION
            /////END SETUP OF JFRAME////
        
            
            ////start the thread and game!
           Bebop.start();
            
        }
    
    
    
}
