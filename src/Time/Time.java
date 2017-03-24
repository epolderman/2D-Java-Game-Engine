/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Time;

/**
 *  /////useful class just to get elapsed time  in milliseconds etc....
 *  @author eze
 */
public class Time 
{
    ///for nanotime purposes
    private long start;
    private long end;
    
    ////for millisecond time purposes
    private long startM;
    private long endM;
    
    
    
   ////millisecond time
    public long getDurationM()
    {
        return endM - startM;
    }
    public long returnStartM()
    {
        return startM;
    }
    public long returnEndM()
    {
        return endM;
    }
    public void resetStopM()
    {
        endM = 0;
    }
    public void resetStartM()
    {
        startM = 0;
    }
    public void startM()
    {
        startM = System.currentTimeMillis();
    }
    public void stopM()
    {
        endM = System.currentTimeMillis();
    }
    
    
    /////for nanotime
     public long getDuration()
    {
        return end - start;
    
    }
    public long returnStart()
    { 
    return start;
    }
    public long returnEnd()
    {
        return end;
    
    }
    public void reset()
    {
        start = 0;
        end = 0;
    }
    public void start()
    {
        start = System.nanoTime();
    }
    public void stop()
    {
        end = System.nanoTime();
    
    }
    public void equal()
    {
        start = end;
    }
    public void resetStop()
    {
        end = 0;
    }
}
