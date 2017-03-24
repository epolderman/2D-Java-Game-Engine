/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mechanics;

/**
 *
 * @author eze
 */
public class SpaceScore 
{   
    
    private String diff;
    protected int score;
    private double x;
    private double y;
    private int value;
    
    public SpaceScore(double x, double y, String diff)
    {
        this.diff = diff;
        this.x = x;
        this.y = y;
        
    }
    protected void Notch(int value)
    {
                ///scoring
                switch (diff) {
                    case "Easy":
                        score += value + 7.5;
                        break;
                    case "Medium":
                        score += value + 15;
                        break;
                    case "Hardened":
                        score += value + 20;
                        break;
                    default:
                        ///debugging
                        System.out.println("Error Occured in Scoring");
                        break;
                }
    
    }
    public int returnSS()
    {
        return score;
    }
    
    
    
}
