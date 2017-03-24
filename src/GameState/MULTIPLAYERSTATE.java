/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

import Audio.Audio;
import Console.MainConsole;
import Console.MainConsoleRight;
import Explosions.GGSExplosion;
import Explosions.GreensExplosion;
import Explosions.MeteorsExplosion;
import Explosions.MissilesExplosion;
import Explosions.RedsExplosion;
import Explosions.SMeteorsExplosion;
import Explosions.ViciousExplosion;
import Images.LoadImage;
import Main.GamePanel;
import Mechanics.Collision;
import NetProgramming.ClientUDP;
import NetProgramming.ServerUDP;
import ObjectBOSS.PlasmaBalls;
import ObjectBOSS.Vicious;
import Units.GunEnemy;
import Units.GunGreenShot;
import Units.MainMissile;
import Units.MultiPlayer;
import Units.BluntForceEnemy;
import Packets.PacketCGreen;
import Packets.PacketDisconnect;
import Packets.PacketLogin;
import Time.Time;
import Units.BigMeteor;
import Units.SmallMeteor;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author eze
 */
////------------THE GUIDE TO THIS MULTIPLAYER----------
///IDE this is coded,ran,and tested in is Netbeans 8.1
///To run this on your internet connection -> around line 248 in method start() of MULTIPLAYERSTATE level
///You must change the ip address to the wireless network you are connected to or localhost, etc..
////The classes/objects that are contained in this demo is..
///Most but not all package Objects ->red enemy, green enemy, green gun shot, metoer, smeteor, playermultiplayer, vicious
///the whole package Packet -> PacketBigM, PacketCBIg, PackgCGGS, etc..
///the whole package NetProgramming -> clientUDP, serverUDP
///the collison class is also used to update the data structures within the serverUDP class if a hit was detected.
///packets are being sent from the update methods of the objects update methods in red enemy, green enemy, meteors, 

///*****collision detection is all up for the missile vs every object -> metoers, red and green enemies, green gun shot, small meteor, vicious
///collision detected update for missile vs vicious(boss) missile now is not aloud to pentrate the shell of the spaceship of the boss

///UPDATES
//**Added Health Collision Updates for every player vs every enemy

//TODO
//**Add a death cinematic//player off//etc..

///this is the level
public class MULTIPLAYERSTATE extends GameState implements MouseListener
{   
    ///state mechanics
    public GameStateManager GSM;
    private LoadImage Ramsey;
    private final GamePanel yahs;
    private String id;
    
    //net mechanics
    public ServerUDP socketServer;
    public ClientUDP socketClient;
    public MultiPlayer MP;
    private List<MultiPlayer> thePlayers = new ArrayList<>();
    
    //audio variables
    private Audio earth;

    ///consoles
    private MainConsoleRight ml;
    private Time MCTR;
    private MainConsole mc;
    private MainConsole mcSecond;
    
    //font
    private Font below;


    ///Laser/Missile Mechanics
    private LinkedList<MainMissile> ms = new LinkedList<>();
    private MainMissile mx;
    private Boolean laserPressed;
    private Time laserTimer;
    private long laserLong;
    private LinkedList<MissilesExplosion> missileExplosions = new LinkedList<>();
    private MissilesExplosion missileExplosion;


    ///enemy mechanics//begin level mechanics
    ///shoots green gun shots
    private GunEnemy greenEnemy;
    private LinkedList<GunEnemy> greenEnemies = new LinkedList<>();
    private GreensExplosion greenExplosion;
    private LinkedList<GreensExplosion> greenExplosions = new LinkedList<>();
    
    ///for green gun shots
    private GunGreenShot ggShot;
    private LinkedList<GunGreenShot> ggShots = new LinkedList<>();
    private GGSExplosion ggShotX;
    private LinkedList<GGSExplosion> ggShotsXs = new LinkedList<>();
    
    ///red enemy mechanics
    private BluntForceEnemy redEnemy;
    private LinkedList<BluntForceEnemy> redEnemies = new LinkedList<>();
    private RedsExplosion redExplosion;
    private LinkedList<RedsExplosion> redExplosions = new LinkedList<>();
    
    ///meteors data structures
    private SmallMeteor smallM;
    private LinkedList<SmallMeteor> smallMs = new LinkedList<>();
    private BigMeteor bigM;
    private LinkedList<BigMeteor> bigMs = new LinkedList<>();
    private MeteorsExplosion metX;
    private LinkedList<MeteorsExplosion> metXs = new LinkedList<>();
    private SMeteorsExplosion smetX;
    private LinkedList<SMeteorsExplosion> smetXs = new LinkedList<>();
    
    
    ///font mechanics for game begin
     private final Font gameBegin; ///font color
     private Time beginDraw;
     private long beginDraws;
     private boolean levelBegin;
     private boolean logoutReady;
     private boolean startSequence;
     private boolean mainStart;
     
     ///for controls
     private boolean controlsReady;
     
     //collision detection
     private Collision hit;
     
     ///begin enemy hoard///vicious mechanics
     private boolean GO;
     private boolean boss;
     private boolean bossloading;
     private boolean bringHIMin;
     private boolean comeup;
     private Vicious viciouz;
     private LinkedList<Vicious> vics = new LinkedList<>();
     private PlasmaBalls plasmaB;
     private LinkedList<PlasmaBalls> plasmaBs = new LinkedList<>();
     private ViciousExplosion BEX;
     private LinkedList<ViciousExplosion> BEXxxx = new LinkedList<>();
     private boolean pimp;
     private int iterations;
     
   
     
     

    public MULTIPLAYERSTATE(GameStateManager GSM,GamePanel yahs)
    {   
        this.laserPressed = false;
        this.iterations = 0;
        this.pimp = false;
        this.comeup = false;
        this.bringHIMin = false;
        this.bossloading = false;
        this.boss = true;
        this.GO = false;
        gameBegin = new Font("Source Code Pro", Font.PLAIN, 30);
        this.beginDraws = 0;
        this.mainStart = false;
        this.startSequence = false;
        this.levelBegin = false;
        this.logoutReady = false;
        this.laserLong = 0;
        this.yahs = yahs;
        this.GSM = GSM;
        initialize();
        ///start the client/server threads
        start();
        earth = new Audio("/Music/Earth.wav");     
        MCTR = new Time();
        laserTimer = new Time();
        beginDraw = new Time();
        below = new Font("Source Code Pro", Font.PLAIN, 14);
        controlsReady = false;
   
        ////add server and client threads after the initliazation of the levels varaibles
        MP = new MultiPlayer(this, 300, 300, JOptionPane.showInputDialog(this, "Please enter a username"),null, -1);       
        this.addEntity(MP);
        PacketLogin loginPacket = new PacketLogin(MP.getUsername(),MP.getX(), MP.getY()); 
        if (socketServer != null) 
        {
            socketServer.addConnection((MultiPlayer) MP, loginPacket);
        } 
       //write packet to login client
        loginPacket.writeData(socketClient);
         //play music       
         earth.play();       
  

    }
    ///initalize some of the objects and setup up background
    @Override
     public void initialize()
     {     

         try
        {  
            ///loading in background with scroll
            Ramsey = new LoadImage("/Resources/starz.png", 1);
            Ramsey.scroll(0, 5);
        }
        catch(Exception x)
        {
            x.printStackTrace();
        } 
         
         ///logout console right
        ml = new MainConsoleRight(826, 545,this);
        
        mc = new MainConsole(-150, 545, this);
        mcSecond = new MainConsole(-150, 400, this);
        
        
        ///collision detection
        hit = new Collision(0,0,0,0,0,0,0,0,this, 0, 0, 0,0);
        
       
        
         
     }
     ///method that starts the threads
     private void start()
    {
        ///start the threads for server and client
         if (JOptionPane.showConfirmDialog(yahs, "Do you want to run the server") == 0)
        {
            socketServer = new ServerUDP(yahs, this);
            socketServer.start();
        }
        
         ///this is where you edit your ip address 
            socketClient = new ClientUDP("localhost", yahs, this);
            socketClient.start(); 
                
    }
     ///main update method for all objects on the level
       @Override
    public void update()
    {   
        
        ///everything in this update allows the user to play against 10 iterations of all enemies and meteors
        //it then stops the enemy hoard and sends in boss
        
        
        //second life console for player two
        if(mcSecond != null)
        {
            mcSecond.update();
        
        }
        
        //life console for player one
        if(mc != null)
        {
            mc.update();
        
        }
        
        
        ///simulate the boss death if hitpoints hit 0
        if(viciouz != null)
        {
        if(viciouz.returnHits() == 0 && pimp == false)
        {
             bossdeath();
             pimp = true;
        }
        
        }
        
        ///simulation of the boss death is over now go to the level ending state(basically swiching the state)
        if(pimp == true)
        {   if(viciouz.returnITSTIME() == true)
        {
            System.out.println("Ending....");
            earth.stop();
            GSM.setSTATE(GameStateManager.ENDINGSTATE);
        }
        }
       
        ///if the vicious linked list is zero bring him in
        if(vics.size() == 0)
        {
        bringINboss();
        }
        ////main timer for synchonization
        //System.out.println("State: Timer = " + beginDraws);
       
   
         
       //to begin the level
       int placemarker = 100;
       int randomx = 10;
       int randomy = 200;
       int value = 0;
         
       ////begin level is conneted layers is over 2
       if(thePlayers.size() == 2 && startSequence == false)
       {    
           
            System.out.println("Two Players Have Entered");
            levelBegin();
            beginDraw.startM();
            startSequence = true;
            levelBegin = true;

       }
       
       ////count iterations till 10 then begin boss battle
       if(iterations == 10)
       {
           GO = false;
           comeup = true;
       
       }
       
 
       ////this starts the enemy hoard at different x's at each iteration
       ///i only produce one of each enemy at each iteration
       ///once th enemy gets outside the view of the gamepanel, it deletes the enemy, and removes it from linked list
       ///then it adds another enemy and begins the process all over again
       if(GO == true)
       {
       if(greenEnemies.size() == 0 && GO == true)
       {    
            value = (int) (Math.random() * (randomx - randomy) + randomy);
            System.out.println("State Green: Random Value: " + value);
            placemarker += 5;
            placemarker = placemarker + value;
            if(placemarker >= 800)
            {
            placemarker = 100;
            }
            System.out.println("State Green: Final PlaceMarker = " + placemarker);
            greenEnemy = new GunEnemy(placemarker, -5, this, "green");
            greenEnemies.add(greenEnemy);
            

       }
       
      
       if(redEnemies.size() == 0 && GO == true)
       {    
            value = (int) (Math.random() * (randomx - randomy) + randomy);
            System.out.println("State Red: Random Value: " + value);
            placemarker += 50;
            placemarker = placemarker + value;
            if(placemarker >= 800)
            {
            placemarker = 500;
            }
            System.out.println("State Red: Final PlaceMarker = " + placemarker);
            redEnemy = new BluntForceEnemy(this, "redone",placemarker, -5);
            redEnemies.add(redEnemy);
            iterations++;
            ////when this hits 10 then the boss comes in
       }
       
        if(smallMs.size() == 0 && GO == true)
       {    
            value = (int) (Math.random() * (randomx - randomy) + randomy);
            System.out.println("State SmallM: Random Value: " + value);
            placemarker += 75;
            placemarker = placemarker + value;
            if(placemarker >= 800)
            {
            placemarker = 500;
            }
            System.out.println("State SmallM: Final PlaceMarker = " + placemarker);
           smallM = new SmallMeteor(this, "smallM",placemarker, -5);
           smallMs.add(smallM);
           
       }
        
          if(bigMs.size() == 0 && GO == true)
       {    
            value = (int) (Math.random() * (randomx - randomy) + randomy);
            System.out.println("State BigM: Random Value: " + value);
            placemarker += 5;
            placemarker = placemarker + value;
            if(placemarker >= 800)
            {
            placemarker = 500;
            }
            System.out.println("State BigM: Final PlaceMarker = " + placemarker);
            bigM = new BigMeteor(this, "bigM", placemarker, -5);
            bigMs.add(bigM);
       }
           
            
            
           
           
      
            
           
            ///grreen gun shot is produced by the green enemy if it is alive
            if((greenEnemy != null && greenEnemies != null) && ggShots.size() == 0)
            {   
                
               
                ggShot = new GunGreenShot(this, "ggs", greenEnemy.getX(), greenEnemy.getY() + 5);
                ggShots.add(ggShot);
              
               
                
            }
       }
       
       ///produce plasma balls by the boss if it is alive and kicking
         if(vics.size() != 0 && plasmaBs.size() == 0)
            {   
               
                System.out.println("IN PB");
                
                
                for(int i = 0;i < vics.size(); i++)
                {
                            viciouz = vics.get(i);
                            plasmaB = new PlasmaBalls(viciouz.getX(), viciouz.getY() + 5, "plasma", this);
                            plasmaBs.add(plasmaB);
                }
               
                
              
               
                
            }
          
          
       
          
        //cooldown for laser
        if(laserPressed == true)
        {    
        laserTimer.stopM();
        laserLong = laserTimer.getDurationM();
        laserTimer.resetStopM();
        
        if(laserLong > 2500)
        {
        laserPressed = false;
        }
        
        }   
        
        
        
         ///set draw variables for beginning sequence 
        beginDraw.stopM();
        beginDraws = beginDraw.getDurationM();
        beginDraw.resetStopM();
        

        ///player update 
        for (MultiPlayer e : getEnts()) 
        {
            e.update();
           
        }
        
        ///background update
        Ramsey.update();
        ///logout console
        ml.update();
        
        
        
        
        ///create missile explosion
        createMissileExplosion();
            
        ///collision detection for missile vs vicious and create explosion if specific x and y change is detected
        ///green enemies cannot be killed with regular laser now
        ///plasma balls deflects lasers as well
        hit.McheckViciousVSmi(viciouz, greenEnemy, plasmaB, ms, mx);

        ///missiles update, deletion, garbage collector vroom vroom
          for(int i = 0;i < ms.size(); i++)
         {
            
             ///create green explosion
            createGreenExplosion();
            
            ///collision detection laser vs green enemies
            hit.McheckGREENvsMIS(mx, greenEnemies, greenEnemy);
                
            ///create an explosion if necessary
            createSMeteorExplosion();
            
            ////check collision detection against small metoers
            hit.McheckMvsSM(mx, smallMs, smallM);
            
            //create explosion if necessary
            createBMeteorExplosion();
            
            ///check collision detection laser against big meteors
            hit.McheckMvsBM(mx, bigMs, bigM);
            
            ///create ggs explosion if necessary
            createGreenGunShotExplosion();
            
            ///collision detection laser vs ggs
            hit.McheckGGSvsMIS(mx, ggShots, ggShot);
            
            ///create red explosion
            createRedExplosion();
            
            ////collision detection laser vs red enemies
            hit.McheckREDvsMIS(mx, redEnemies, redEnemy);
            
            //grab object
            mx = ms.get(i);
            
            //update object
            mx.update();
            
            ///delete and remove the laser if out of game panel
             if(mx.getY() <= -2 && mx != null)
             {  
                 mx = null;
                 ms.remove(i);
                 
                 System.out.println("State: Missile Removed");

             }
            
         }
          
       
          
          ///update for plasma balls
           for(int i = 0;i < plasmaBs.size(); i++)
         {
                         
            plasmaB = plasmaBs.get(i);

            plasmaB.update();
            
            ///remove if out of game panel
             if(plasmaB.returnY() >= 710  && plasmaB != null)
             {  
                 plasmaB = null;
                 plasmaBs.remove(i);
                 
                 System.out.println("State: Plasma Ball Removed");

             }
            
         }
          
          
          
            ///green enemies update, deletion, garbage collector vroom vroom
          for(int i = 0;i < greenEnemies.size(); i++)
         {
                         
            greenEnemy = greenEnemies.get(i);

            
            
              
            if(greenEnemy != null)
            {
                if(greenEnemy.checkDeath() == true)
                          {
                          hit.setSMALLX((int)greenEnemy.getX());
                          hit.setSMALLY((int)greenEnemy.getY());    
                          PacketCGreen packet = new PacketCGreen(greenEnemy.returnID());
                          packet.writeData(this.socketClient);
                          greenEnemy = null;
                          greenEnemies.remove(i);
                          System.out.println("StateGreen: Green Enemy Died - Removed");
                          }
                else if(greenEnemy.getY() >= 710)
                          {
                          greenEnemy = null;
                          greenEnemies.remove(i);
                          System.out.println("State: Green Enemy Removed");
                          }
                else
                {
                    //System.out.println("State: Green Enemy Keeps Moving..!");
                }
                
                
                
                
                    
            }
            
           if(greenEnemy != null)
           {
           greenEnemy.update();
           }
            
            
            
         }
          
          
          ///red enemies, update, deletion, garbage collection
          for(int i = 0;i < redEnemies.size(); i++)
         {
             
            redEnemy = redEnemies.get(i);

             
            redEnemy.update();
            
            
            
             if(redEnemy.returnY() >= 710  && redEnemy != null)
             {  
                 redEnemy = null;
                 redEnemies.remove(i);
                 
                 System.out.println("State: Red Enemy Removed");

             }
            
         }
          
         ////red explosion update
         for(int i = 0; i < redExplosions.size();i++)
         {  
             redExplosion = redExplosions.get(i);
             
             redExplosion.update();
             
             if(redExplosion.checkExplosion() == true)
            {   
                redExplosion = null;
                redExplosions.remove(i);
                System.out.println("State: Red Explosion Removed");
            
            }
         
         
         }
         
         ////green explosion update
         for(int i = 0; i < greenExplosions.size();i++)
         {  
             greenExplosion = greenExplosions.get(i);
             
             greenExplosion.update();
             
             if(greenExplosion.checkExplosion() == true)
            {   
                greenExplosion = null;
                greenExplosions.remove(i);
                System.out.println("State: Green Explosion Removed");
            
            }
         
         
         }
         
          ////green gun shot update
         for(int i = 0; i < ggShots.size();i++)
         {  
             ggShot = ggShots.get(i);
             
             ggShot.update();
             
              if(ggShot.returnY() >= 710  && ggShot != null)
             {  
                 ggShot = null;
                 ggShots.remove(i);
                 
                 System.out.println("State: Green Gun Shot Removed");

             }
         
         
         }
         
          ////green gun explosion
         for(int i = 0; i < ggShotsXs.size();i++)
         {  
             ggShotX = ggShotsXs.get(i);
             
             ggShotX.update();
             
             if(ggShotX.checkExplosion() == true)
            {   
                ggShotX = null;
                ggShotsXs.remove(i);
                System.out.println("State: Green Gun Shot Explosion Removed");
            
            }
         
         
         }
         
         ///small meteor updates
         for(int i = 0;i < smallMs.size(); i++)
         {
                         
            smallM = smallMs.get(i);

            smallM.update();
            
             if(smallM.getY() >= 710  && smallM != null)
             {  
                 smallM = null;
                 smallMs.remove(i);
                 
                 System.out.println("State: Small Meteor Removed");

             }
            
         }
         
         
         ///large metoer updates
         for(int i = 0;i < bigMs.size(); i++)
         {
                         
            bigM = bigMs.get(i);

            bigM.update();
            
             if(bigM.getY() >= 710 && bigM != null)
             {  
                 bigM = null;
                 bigMs.remove(i);
                 
                 System.out.println("State: Large Meteor Removed");

             }
            
         }
         
       
         
         ////small meteor explosions
         for(int i = 0;i < smetXs.size(); i++)
         {
                         
            smetX = smetXs.get(i);

            smetX.update();
            
             if(smetX.checkExplosion() == true)
            {   
                smetX = null;
                smetXs.remove(i);
                System.out.println("State: Small Meteor Explosion Removed");
            
            }
            
         }
         
         ///big meteor explosions
          for(int i = 0;i < metXs.size(); i++)
         {
                         
            metX = metXs.get(i);

            metX.update();
            
             if(metX.checkExplosion() == true)
            {   
                metX = null;
                metXs.remove(i);
                System.out.println("State: Big Meteor Explosion Removed");
            
            }
            
         }
         
          
          ///viciouz
          for(int i = 0;i < vics.size(); i++)
         {
                    
           
             
            viciouz = vics.get(i);

            viciouz.update();
     
         }
          
          //boss explosion
          for(int i = 0;i < BEXxxx.size(); i++)
         {
                         
            BEX = BEXxxx.get(i);

            BEX.update();
            
             if(BEX.checkExplosion() == true)
            {   
                BEX = null;
                BEXxxx.remove(i);
                System.out.println("State: Boss Explosion Removed");
            
            }
            
         }
          
          
           //missile explosions
          for(int i = 0;i < missileExplosions.size(); i++)
         {
                         
            missileExplosion = missileExplosions.get(i);

            missileExplosion.update();
            
             if(missileExplosion.checkExplosion() == true)
            {   
                missileExplosion = null;
                missileExplosions.remove(i);
                System.out.println("State: Missile Explosion Removed");
            
            }
            
         }
          
         
         
         hit.McheckPlayervsEverything(MP, redEnemies, ggShots, greenEnemies, bigMs, smallMs);
      
     
          
     
        
    }
    ///methods created explosion when the boss dies
    private void bossdeath()
    {
    
                BEX = new ViciousExplosion(viciouz.getX() + 100,viciouz.getY() +50);
                BEXxxx.add(BEX);
                BEX = new ViciousExplosion(viciouz.getX() + 150,viciouz.getY() +100);
                BEXxxx.add(BEX);
                BEX = new ViciousExplosion(viciouz.getX(),viciouz.getY() + 150);
                BEXxxx.add(BEX);
                BEX = new ViciousExplosion(viciouz.getX() - 50 ,viciouz.getY() + 200);
                BEXxxx.add(BEX);
                BEX = new ViciousExplosion(viciouz.getX() - 100,viciouz.getY() + 150);
                BEXxxx.add(BEX);
                

    }
    
    ///not using this method*******************/////////////////////////////
    private void levelBegin()
    {   
        ///create green enemies
        ///and start moving them around 
        System.out.println("State: LEVEL BEGIN");
        String ids[] = {"one", "two", "three", "four", "five"};
        double theMove = 100;
        ///your generating unique id's on both sides so they are not maatching
        //the for loop is launching too quickly to space them out
        if(beginDraws >= 10000 && beginDraws <= 10250)
        {
        for(int i = 0; i < 1;i++)
        {
                     //generate unique id, and x to spread out the enemies, and iterate
                     //string array of one, two , three, four, five to generate the enemies
                     //String theID = generateId();
                    
                     System.out.println("State: The Unique ID's " + ids[i]);
                     greenEnemy = new GunEnemy(theMove + 100, 5, this, ids[i]);
                     greenEnemies.add(greenEnemy);
                     theMove += 100;
                
                
        
        }
        }
        
    
    
        
      
    } 
    ///main render method for all the objects
    @Override
    public void render(Graphics2D g)
    {   
        
       
        ///background render scroll
        Ramsey.render(g);
       
        
        ///players render
        for (MultiPlayer e : getEnts()) 
        {
            e.render(g);            
        }
        
        
        
        ////for logout
        g.setFont(below);  
        if(beginDraws >= 5000 && levelBegin == true)
        {
            
            ///you stopped here
            //set up collision for health or multiplayer
            //methods are set up in the class
            //going to have to alter size of arraylist, death, health because of it
        g.drawString("-LOGOUT-", 695, 605);
        
        g.drawString("-Health-", 15, 415);
        g.drawString(this.thePlayers.get(1).getUsername() + " " +
        this.thePlayers.get(1).returnHits() , 10, 440);
        
        g.drawString("-Health-", 15, 560);
        g.drawString(this.thePlayers.get(0).getUsername() + " " +
        this.thePlayers.get(0).returnHits() , 10, 585);     
        }
        
        ///missiles render
          for(int i = 0;i < ms.size(); i++)
         {
             
            mx = ms.get(i);

             
            mx.render(g);
         }
          
          //green enemies render
          for(int i = 0;i < greenEnemies.size(); i++)
         {
             
            greenEnemy = greenEnemies.get(i);

             
            greenEnemy.render(g);
         }
          
           //red enemies render
          for(int i = 0;i < redEnemies.size(); i++)
         {
             
            redEnemy = redEnemies.get(i);

             
            redEnemy.render(g);
         }
          
          
         ///beginning sequence of the render 
        g.setColor(Color.WHITE);
        g.setFont(gameBegin);  
        ///for drawing beginning sequence
        if(beginDraws >= 2000 && beginDraws <= 3500)
        {
        g.drawString("Find Vicious", 300, GamePanel.HEIGHT / 2);
      
       }
        else if(beginDraws <= 6000 && beginDraws > 3500)
        {
        g.drawString("Kill Vicious", 300, GamePanel.HEIGHT / 2);
   
        }
        else if(beginDraws <= 8000 && beginDraws > 6000)
        {
        g.drawString("Get Revenge", 300, GamePanel.HEIGHT / 2);
        GO = true;
        ///comeup = true;
        } 
        
        
        
        //for star scroll
        if(beginDraws >= 8000 && levelBegin == true)
        {
         Ramsey.scroll(0, 2);
         controlsReady = true;
        }
        
        ///red explosion render
        for(int i = 0; i < redExplosions.size();i++)
         {  
             redExplosion = redExplosions.get(i);
             
             redExplosion.render(g);

         }
        
        //green explosions
         for(int i = 0; i < greenExplosions.size();i++)
         {  
             greenExplosion = greenExplosions.get(i);
             
             greenExplosion.render(g);

         }
         
         ///missile explosion
          for(int i = 0; i < missileExplosions.size();i++)
         {  
             missileExplosion = missileExplosions.get(i);
             
             missileExplosion.render(g);
             
           
         
         
         }
          
          ///gree gun shot render
         for(int i = 0; i < ggShots.size();i++)
         {  
             ggShot = ggShots.get(i);
             
             ggShot.render(g);
             
         }
         
         ///green gun shot explosions render
          for(int i = 0; i < ggShotsXs.size();i++)
         {  
             ggShotX = ggShotsXs.get(i);
             
             ggShotX.render(g);
             
   
         }
          
           ///small meteor renders
          for(int i = 0; i < smallMs.size();i++)
         {  
             smallM = smallMs.get(i);
             
             smallM.render(g);
             
   
         }
          
          //big metoer renders
           for(int i = 0; i < bigMs.size();i++)
         {  
             bigM = bigMs.get(i);
             
             bigM.render(g);
             
   
         }
           
           ///big meteor explosions
          for(int i = 0;i < metXs.size(); i++)
         {
                         
            metX = metXs.get(i);

            metX.render(g);
         }
          
          ///small meteor explosions
           for(int i = 0;i < smetXs.size(); i++)
         {
                         
            smetX = smetXs.get(i);

            smetX.render(g);
         }
           
           
           
           
         //logout render
        ml.render(g);
        
         ///second player mainconsole
        mcSecond.render(g);
        
        //mainconsole
        mc.render(g);
        
        

        
        ///vicious render
         for(int i = 0;i < vics.size(); i++)
         {
                         
            viciouz = vics.get(i);

            viciouz.render(g);
    
         }
         
         ///plasma balls render
           for(int i = 0;i < plasmaBs.size(); i++)
         {
                         
            plasmaB = plasmaBs.get(i);

            plasmaB.render(g);
            
            
            
         }
           
           
            //boss explosion render
          for(int i = 0;i < BEXxxx.size(); i++)
         {
                         
            BEX = BEXxxx.get(i);

            BEX.render(g);
         
            
         }
          
            //missile explosion
          for(int i = 0;i < missileExplosions.size(); i++)
         {
                         
            missileExplosion = missileExplosions.get(i);

            missileExplosion.render(g);
         
            
         }
        
       
        
    }
    ////simulation of bringing in the boss
    public void bringINboss()
    {       
            
            
            if(comeup == true)
            {   
                viciouz = new Vicious(this,"vicious", 260, -256);
                vics.add(viciouz);
                //viciouz.setLevel("Easy");
                bringHIMin = true;
                viciouz.setIncoming(this.returnBOSSstatus());
                System.out.println("Bringing in boss....");
               
            }
            

    }
    ///for the vicious class 
    public int returnBOSSstatus()
{
    if(bringHIMin == true)
    {
        return 1;
        ////showdown time 
    }
    else
    {
        return 2;
    }



}
    
    
  
    ///handles concurency synchonization issues
    public synchronized List<MultiPlayer> getEnts()
    {
        return this.thePlayers;
    }
  
     public void keyPressed(int z)
    {   
        
         
        if(z == KeyEvent.VK_RIGHT)
        {   
            
            MP.setDX(9);
        }
      
        else if(z == KeyEvent.VK_LEFT )
        {  
            MP.setDX(-9);
        }
        else if(z == KeyEvent.VK_UP )
        {   
            
           MP.setDY(-9);
        }
     
        else if(z == KeyEvent.VK_DOWN )
        {  
            MP.setDY(9);
           
        }
       
        else if(z == KeyEvent.VK_S)
        {
            //System.out.println("State: GreenEnmies Size: " + greenEnemies.size());
            //System.out.println("State: Server Size of Red: " + socketServer.returnREDsize());
                
                
        }
        else if(z == KeyEvent.VK_F)
        {
            //System.out.println("State: " + viciouz.hitpoints);
            //System.out.println("State: Connected Players: " + thePlayers.size());
            //System.out.println("State: Player 1 " + thePlayers.get(0).getUsername());
            //System.out.println("State: Player 2 " + thePlayers.get(1).getUsername());
            if(controlsReady == false)
            {
            
                System.out.println("State: Initatiing Laser Controls");
            }
            else
            {
                
            
                if(laserPressed == false)
                {
                    
                id = generateId();
                mx = new MainMissile(MP.getX() + 24,MP.getY() - 15, this, id); 
                ms.add(mx);
                laserTimer.startM();
                laserPressed = true;
                
                }
                else
                {
                    System.out.println("State: Wait for Cooldown"); 
                
                }
                
            }

        }
        else if(z == KeyEvent.VK_SPACE)
        {       
                
           
            
        
        }
    
        
    }
    public void keyReleased(int z)
    {
        
          if(z == KeyEvent.VK_RIGHT)
        {
            MP.setDX(0);
        }
        else if(z == KeyEvent.VK_LEFT)
        {
            MP.setDX(0);
        }
        else if(z == KeyEvent.VK_UP)
        {
           MP.setDY(0);
        }
        else if(z == KeyEvent.VK_DOWN)
        {   
            
            MP.setDY(0);
            
        }
        
    
    }
    ////method for logout function
    public void mousePressed(MouseEvent e)
    {
                        int mouseX = e.getX();
                        int mouseY = e.getY();
                        System.out.println(mouseX);
                        System.out.println(mouseY);
                        
                        
                        
                        
                        if(mouseX >= 695 && mouseX <= 817)
                        {
                    if(mouseY >= 562 && mouseY <= 688)
                    {   
                             
                        
                            System.out.println("Logging Out...");
                            PacketDisconnect packet = new PacketDisconnect(this.MP.getUsername());
                            packet.writeData(this.socketClient);
                            GSM.setSTATE(GameStateManager.MAINMENUSTATE);
                            earth.stop();
                            
                    
                    }
                        
                        
                        }
                        
                        
                        
    }
    
       @Override
    public void mouseClicked(MouseEvent me) 
    {
        
         
      
    }

    @Override
    public void mouseReleased(MouseEvent me) 
    {
       
    }

    @Override
    public void mouseEntered(MouseEvent me) 
    {
               
        
    }

    @Override
    public void mouseExited(MouseEvent me) 
    {
         
    }
  
    ////method for adding a player connects the client class
    public void addEntity(MultiPlayer entity)
    {
        this.thePlayers.add(entity);
    }
    
   
    
    ///method that handles disonnecting connects the client class
    public void removeMulti(String username)
    {  
        
        int index = 0;
        for (MultiPlayer e : getEnts()) 
        {
           if(e instanceof MultiPlayer && ((MultiPlayer)e).getUsername().equals(username))
                   {
                       break;
                       
                   }
           index++;
           
        }
        this.thePlayers.remove(index);
    }
    
    ///method that gets a specific character/user connects the client class
    private int getMultiIndex(String username)
    {
        int index = 0;
        for (MultiPlayer e : getEnts()) 
        {
        if(e instanceof MultiPlayer && ((MultiPlayer)e).getUsername().equals(username))
        {
        break;
        }
        index++;
        }
        
        return index;
    
    
    }
    ///updates the move player connects the client class
    public void movePlayer(String username, double x, double y)
    {
        int index = getMultiIndex(username);
        this.thePlayers.get(index).setX(x);
        this.thePlayers.get(index).setY(y);
     
        
    
    }
 
  ///updates or creates the green enemy connects the client class
    public void moveGreen(String username, double x, double y)
    {
        
        
        if(greenEnemies.size() == 0)
       {
           //System.out.println("State: Green: Link List Size was zero -- created in multiplayer state");
           greenEnemy = new GunEnemy(x, y, this, username);
           greenEnemies.add(greenEnemy);

       }
       else if(greenEnemies != null && greenEnemies.size() > 0)
            {   
               
                for(int i = 0; i < greenEnemies.size();i++)
                {   
                    //System.out.println("Passed second filter");
                    if(username.equals(greenEnemies.get(i).returnID()))
                    {   
                        //System.out.println("State: Found and Moving Green in Multiplayer");
                        greenEnemy = greenEnemies.get(i);
                        greenEnemy.setX(x);
                        greenEnemy.setY(y);
                        //ms.get(i).x = x;
                        //ms.get(i).y = y;
                        
   
                    } 
                    
                   
                }
            
            }
      else
               System.out.println("State: Green: Fatal Error in Green");
        
    
    
    
    
    
    
    }
    ///updates or creates the red enemy connects the client class
    public void moveRed(String username, double x, double y)
    {
        
        
        if(redEnemies.size() == 0)
       {
           //System.out.println("State: Red: Link List Size was zero -- created in multiplayer state");
           redEnemy = new BluntForceEnemy(this, username,x, y);
           redEnemies.add(redEnemy);

       }
       else if(redEnemies != null && redEnemies.size() > 0)
            {   
               
                for(int i = 0; i < redEnemies.size();i++)
                {   
                    //System.out.println("Passed second filter");
                    if(username.equals(redEnemies.get(i).returnID()))
                    {   
                        //System.out.println("State: Found and Moving Red in Multiplayer");
                        redEnemy = redEnemies.get(i);
                        redEnemy.setX(x);
                        redEnemy.setY(y);
                        
                        
   
                    } 
                    
                   
                }
            
            }
      else
               System.out.println("State: Fatal Error In Red Move");
        
    
    
    
    
    
    
    }
    ///move ggs or creates connects the client class
    public void moveGGS(String username, double x, double y)
    {
        
        
        if(ggShots.size() == 0)
       {
           
           ggShot = new GunGreenShot(this, username, x, y);
           ggShots.add(ggShot);

       }
       else if(ggShots != null && ggShots.size() > 0)
            {   
               
                for(int i = 0; i < ggShots.size();i++)
                {   
                   
                    if(username.equals(ggShots.get(i).returnID()))
                    {   
                       
                        ggShot = ggShots.get(i);
                        ggShot.setX(x);
                        ggShot.setY(y);
                        
                        
   
                    } 
                    
                   
                }
            
            }
      else
               System.out.println("State: Fatal Error In GGS Move");
        
    
    
    
    
    
    
    }
    //big meteor move connects the client class
    public void moveBIGM(String username, double x, double y)
    {
        
        
        if(bigMs.size() == 0)
       {
           
           bigM = new BigMeteor(this, username, x, y);
           bigMs.add(bigM);

       }
       else if(bigMs != null && bigMs.size() > 0)
            {   
               
                for(int i = 0; i < bigMs.size();i++)
                {   
                   
                    if(username.equals(bigMs.get(i).returnID()))
                    {   
                       
                        bigM = bigMs.get(i);
                        bigM.setX(x);
                        bigM.setY(y);
                        
                        
   
                    } 
                    
                   
                }
            
            }
      else
               System.out.println("State: Fatal Error In Big Meteor Move");
        
    
    
    
    
    
    
    }
   //small meteor move connects the client class
     public void moveSMALLM(String username, double x, double y)
    {
        
        
        if(smallMs.size() == 0)
       {
           
           smallM = new SmallMeteor(this, username, x, y);
           smallMs.add(smallM);

       }
       else if(smallMs != null && smallMs.size() > 0)
            {   
               
                for(int i = 0; i < smallMs.size();i++)
                {   
                   
                    if(username.equals(smallMs.get(i).returnID()))
                    {   
                       
                        smallM = smallMs.get(i);
                        smallM.setX(x);
                        smallM.setY(y);
                        
                        
   
                    } 
                    
                   
                }
            
            }
      else
               System.out.println("State: Fatal Error In Small Meteor Move");
        
    
    
    
    
    
    
    }
     ///handles the movement of the boss or creates one connects the client class
     public void moveBoss(String username, double x, double y)
     {
      if(vics.size() == 0)
       {
           
           viciouz = new Vicious(this, username, x, y);
           vics.add(viciouz);

       }
       else if(vics != null && vics.size() > 0)
            {   
               
                for(int i = 0; i < vics.size();i++)
                {   
                   
                    if(username.equals(vics.get(i).getUsername()))
                    {   
                       
                        viciouz = vics.get(i);
                        viciouz.setX(x);
                        viciouz.setY(y);
                        
                        
   
                    } 
                    
                   
                }
            
            }
      else
               System.out.println("State: Fatal Error In Vicious Move");
        
    
    
         
     
     
     }
     //updates the health
     public void updateHealth(String username, int hits)
     {
        int index = getMultiIndex(username);
        this.thePlayers.get(index).setHits(hits);
     
     
     }
   
   
   
   ///moves or creates the laser
     public void moveLaser(String username, double x, double y)
   {    
       
       if(ms.size() == 0)
       {
           //System.out.println("State: Link List Size was zero -- created in multiplayer state");
           mx = new MainMissile(x, y, this, username);
           ms.add(mx);

       }
       else if(ms != null && ms.size() > 0)
            {   
               
                for(int i = 0; i < ms.size();i++)
                {   
                    //System.out.println("Passed second filter");
                    if(username.equals(ms.get(i).returnID()))
                    {   
                        //System.out.println("State: Found and Moving Missile in Multiplayer");
                        mx = ms.get(i);
                        mx.setX(x);
                        mx.setY(y);
                      
                        
   
                    } 
                    
                   
                }
            
            }
      else
               System.out.println("State: Fatal Error in Move Laser");
       
     
     
    }
     ///moves or creates the plasma balls
     public void movePB(String username, double x, double y)
   {    
       
       if(plasmaBs.size() == 0)
       {
           //System.out.println("State: Link List Size was zero -- created in multiplayer state");
           plasmaB = new PlasmaBalls(x, y, username,this);
           plasmaBs.add(plasmaB);

       }
       else if(plasmaB != null && plasmaBs.size() > 0)
            {   
               
                for(int i = 0; i < plasmaBs.size();i++)
                {   
                    //System.out.println("Passed second filter");
                    if(username.equals(plasmaBs.get(i).getUsername()))
                    {   
                        //System.out.println("State: Found and Moving Missile in Multiplayer");
                        plasmaB = plasmaBs.get(i);
                        plasmaB.setX(x);
                        plasmaB.setY(y);
                        //ms.get(i).x = x;
                        //ms.get(i).y = y;
                        
   
                    } 
                    
                   
                }
            
            }
      else
               System.out.println("State: Fatal Error in Plasma Balls");
       
     
     
    }
     
     
     ///this is a string generator for the laser so it has a unique string to mainaint throughout the classes
      protected String generateId() 
    {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

    public boolean returnSTATE() 
    {
        return levelBegin;
    }
    
    //////////////these methods create explosions from the colision class
    //the methods work like this. -> if a collision is detected in the class it sets these methods at a specific x and y
    ///the creates the explosion at that x and y and the object explosion handles the removal
    public void createRedExplosion()
    {
        ///for generating red explosion
          if(hit.returnBigX() != 0 || hit.returnBigY() != 0)
        {
            
            redExplosion = new RedsExplosion(hit.returnBigX(), hit.returnBigY());
            redExplosions.add(redExplosion);
            
            //set back to zero
            hit.setBigX(0);
            hit.setBigY(0);
            
        }
    
    
    
    }
    ////creates a green explosion
    public void createGreenExplosion()
    {
        //create green explosions here
         if(hit.returnSMALLX() != 0 || hit.returnSMALLY() != 0)
        {
            
            greenExplosion = new GreensExplosion(hit.returnSMALLX(), hit.returnSMALLY());
            greenExplosions.add(greenExplosion);
            
            //set back to zero
            hit.setSMALLX(0);
            hit.setSMALLY(0);
            
        }
        
    
    
    
    
    }
    /////creates a green gun shot explosion
    public void createGreenGunShotExplosion()
    {
    
        if(hit.returnpinkX() != 0 || hit.returnpinkY() != 0)
        {
        
            ggShotX = new GGSExplosion(hit.returnPIMPX(), hit.returnPIMPY());
            ggShotsXs.add(ggShotX);
            
            hit.setpinkX(0);
            hit.setpinkY(0);
            
        }
    
    
    }
    ////create a big meteor explosion -
    public void createBMeteorExplosion()
    {
    
        if(hit.returnMissileX2() != 0 || hit.returnMissileY2() != 0)
        {
        
           metX = new MeteorsExplosion(hit.returnMissileX2(), hit.returnMissileY2());
           metXs.add(metX);
            
           hit.setMissileX2(0);
           hit.setMissileY2(0);
            
        }
    
    
    }
    
    ///create metoer explosion if specific  for small meteor
    public void createSMeteorExplosion()
    {
    
        if(hit.returnpinkX() != 0 || hit.returnpinkY() != 0)
        {
        
           smetX = new SMeteorsExplosion(hit.returnpinkX(), hit.returnpinkY());
           smetXs.add(smetX);
            
           hit.setpinkX(0);
           hit.setpinkY(0);
            
        }
    
    
    }
    ////for creating regular missile explosion
    public void createMissileExplosion()
    {
    
        if(hit.returnZX() != 0 || hit.returnZY() != 0)
        {
        
            missileExplosion = new MissilesExplosion(hit.returnZX(), hit.returnZY() + 5);
            missileExplosions.add(missileExplosion);
            
            hit.setZX(0);
            hit.setZY(0);
        
        
        
        }
    
    
    
    
    
    
    }
    
    
    
    
    
    
    
    
  
    
    
    
}
