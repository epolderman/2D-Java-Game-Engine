/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GameState;
///all imports
import Audio.Audio;
import Console.BoostConsole;
import Console.MainConsole;
import Console.MainConsoleRight;
import Console.MiddleConsole;
import Console.PlasmaConsole;
import Console.ViciousHealthConsole;
import Explosions.EMissileExplosion;
import Explosions.GGSExplosion;
import Explosions.GreensExplosion;
import Explosions.MeteorsExplosion;
import Explosions.MissilesExplosion;
import Explosions.PinksExplosion;
import Explosions.RedsExplosion;
import Explosions.SMeteorsExplosion;
import Explosions.ViciousDestructionExplosion;
import Explosions.ViciousExplosion;
import Images.LoadImage;
import Main.GamePanel;
import Mechanics.Collision;
import Mechanics.SpaceScore;
import ObjectBOSS.ExplosionMissile;
import ObjectBOSS.HomingMissile;
import ObjectBOSS.PlasmaBalls;
import ObjectBOSS.Sheild;
import ObjectBOSS.Vicious;
import Units.BeBop;
import Units.GunEnemy;
import Units.GunGreenShot;
import Units.MainMissile;
import Units.PlasmaBall;
import Units.BluntForceEnemy;
import Time.Time;
import Units.BigMeteor;
import Units.SheildEnemy;
import Units.SmallMeteor;
import Units.SwordFish;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author eze
 */
///NOTES ABOUT THE LEVEL///

///FIXED FIXENS
///*FIXED vicious bring in and size of linked list enemies *FIXED
///*FIXED plasma balls movement one for single other for multi rendering ok now *FIXED
///*FIXED time until strings are shown moved from 19000 -> 19750 *FIXED
///*DONE meny gui now takes in level difficulty *DONE
///*DONE organized the explosion package with abstract inheritance *DONE/
///*DONE organized the object packet to Units with abstract inheritance for sleeker code design *///
///*DONE collision detection for plasma burst vs red enemy//*DONE






///single player level
public class SPACESHIPSTATE extends GameState 
{
    

    //////INSTANCE VARIABLES
    private Font controlz; ////for the timer
    private Color colorofTime;  ///color of the font
    private Time RealFolk;  ///for calculating the different times within the class
    private LoadImage Ramsey;   ///for loading the image and scrolling
    private LoadImage Spike;
    private final Color vicious;  ///setting color of the font
    private final Font fontz; ///font color
    private long TimePassed; ////for measuring time in simulate algorithm
    private Boolean draw1;  ///booleans for the time algorithm
    private Boolean draw2;
    private Boolean draw3;
    
    
    ////boosting mechanics instance variables
    ////10 seconds turbo boost and a 10 second cooldown
    private Boolean isBoostready; 
    private Time Boost;
    private long cooldown;
    private Boolean boostON;
    private Boolean checkBOOST;
    public Boolean boosted;
    
    /////simulation instance variables
    /////19 second simulation before you can control the swordfish
    private Boolean controls;
    
    /////Plasma Cannon Special variables
    private Boolean special;
    private Time ari;
    private long kevin;
    
    ////for guns to keep firing
    private Time gun;
    private long gunduration;
    private boolean beginpunishment;
      
   ///scoring
    private SpaceScore spacescore;
   
    //////OBJECTS & DATA STRUCTURES//////////
    private GameStateManager GSM;
    private SwordFish swordfish;
    private BeBop bebop;
    private MiddleConsole middleconsole;
    private MainConsole mc;
    private MainConsoleRight ml;
    private BoostConsole bc;
    private LinkedList<MainMissile> ms = new LinkedList<>();
    private MainMissile mx;
    private LinkedList<PlasmaBall> ps = new LinkedList<>();
    private PlasmaBall plasma; 
    private PlasmaConsole pc;
    private BigMeteor met;
    private LinkedList<BigMeteor> metz = new LinkedList<>();
    private Collision collision;
    private SmallMeteor smetz;
    private LinkedList<SmallMeteor> smallM = new LinkedList<>();
    private MissilesExplosion smallE;
    private LinkedList<MissilesExplosion> smallExx = new LinkedList<>();
    private MeteorsExplosion ME;
    private LinkedList<MeteorsExplosion> MExxx = new LinkedList<>();
    private SMeteorsExplosion SME;
    private LinkedList<SMeteorsExplosion> SMExxx = new LinkedList<>();
    private GunEnemy ge;
    private LinkedList<GunEnemy> geXXX = new LinkedList<>();
    private BluntForceEnemy re;
    private LinkedList<BluntForceEnemy> reXXX = new LinkedList<>();
    private GunGreenShot ggs;
    private LinkedList<GunGreenShot> ggsXXX = new LinkedList<>();
    private SheildEnemy pe;
    private LinkedList<SheildEnemy> peXXX = new LinkedList<>();
    private PinksExplosion exp;
    private LinkedList<PinksExplosion> expXXX = new LinkedList<>();
    private GreensExplosion gexp;
    private LinkedList<GreensExplosion> gexpXXX = new LinkedList<>();
    private RedsExplosion rexp;
    private LinkedList<RedsExplosion> rexpXXX = new LinkedList<>();
    private GGSExplosion direct;
    private LinkedList<GGSExplosion> directXXX = new LinkedList<>();
    ////end objects and data structures///
    
    
    
    /////MAIN BOSS AND OBJECTS//// 
    private Vicious viciouz;
    private Boolean bossready;
    private Boolean bringHIMin;
    private Sheild sheld;
    private Time sheldD;
    private Boolean halpert;
    private double duration;
    private Boolean hookah;
    private HomingMissile selfie;
    private LinkedList<HomingMissile> purple = new LinkedList<>();
    private Time HMtime;
    private ExplosionMissile EP;
    private LinkedList<ExplosionMissile> EPxxx = new LinkedList<>();
    private Time EPtimer;
    private Time andy;
    private Boolean stutter;
    private long boulevard;
    private Boolean doneloading;
    private int counterSHOT;
    private EMissileExplosion EM;
    private LinkedList<EMissileExplosion> EME = new LinkedList<>();
    private PlasmaBalls balls;
    private LinkedList<PlasmaBalls> ballsXXX = new LinkedList<>();
    private ViciousHealthConsole vhc;
    private ViciousDestructionExplosion BD;
    private ViciousExplosion BEX;
    private LinkedList<ViciousExplosion> BEXxxx = new LinkedList<>();
    private Boolean bossloading;
    ///end boss objects

    ////audio objects
    private Audio earth; ///spaceship theme
    private Audio boss; ///boss theme
    ///end audio objects
    
    ///difficulty settings
    private String level;
    
    
    
    
    public SPACESHIPSTATE(GameStateManager GSM,String level) 
    {   
        
        this.bossloading = false;
        this.boulevard = 0;
        this.counterSHOT = 0;
        this.bossready = false;
        this.bringHIMin = false;
        this.halpert = false;
        this.hookah = false;
        this.stutter = false;
        this.doneloading = false;       
        this.draw1 = false;
        this.draw2 = false;
        this.draw3 = false;
        this.isBoostready = true;
        this.checkBOOST = false;
        this.boostON = false;
        this.boosted = false;
        this.controls = false;
        this.special = false;
        this.beginpunishment = false;
        this.cooldown = 0;
        this.kevin = 0;
        this.gunduration = 0;
        this.GSM = GSM;
        vicious = new Color(255,98,88);
        fontz = new Font("Source Code Pro", Font.PLAIN, 30);
        controlz = new Font("Source Code Pro", Font.BOLD, 14);
        RealFolk = new Time();
        RealFolk.startM();
        Boost = new Time();
        ari = new Time();
        gun = new Time();
        earth = new Audio("/Music/Earth.wav");
        sheldD = new Time();
        duration = 0;
        HMtime = new Time();
        EPtimer = new Time();
        andy = new Time();
        earth.play();
        boss = new Audio("/Music/Boss.wav");
        this.level = level;
        System.out.println("Difficulty Level is = " + level);
        initialize();
        
        
    }
    
    /////initz all the objects on the gamestate
    @Override
    public void initialize()
    {   
        try
        {  
            ///loading in background with scroll
            Ramsey = new LoadImage("/Resources/starz.png", 1);
            Ramsey.scroll(0, 7);
        }
        catch(Exception x)
        {
            x.printStackTrace();
        } 
        ///spacescore
        spacescore = new SpaceScore(300,300,level);
        
        /////starting points of the objects
        /////object(x,y)        
        ///main player and jet
        bebop = new BeBop(260,700);
        swordfish = new SwordFish(331, 711, level);
        ///consoles
        mc = new MainConsole(-150, 545);
        ml = new MainConsoleRight(826, 545, this);          
        bc = new BoostConsole(-150,520);
        pc = new PlasmaConsole(826, 550);
        middleconsole  = new MiddleConsole(332, 695);
        ///collision detection
        collision = new Collision(level, spacescore);    
        ///main boss objects
        viciouz = new Vicious(260, -256, this, level);
        sheld = new Sheild(500, 500);
        vhc = new ViciousHealthConsole(332, -150);  
    }

    ////MAIN UPDATE METHOD//
    /*includes*/
    //all object updates
    //boolean value checks
    //utility methods for objects
    @Override
    public void update()
    {      
        if(bebop != null)
        {
        if(bebop.returnSimulate() == true && bebop.getX() <= -200)
        {
            //remove bebop after use of first simulation
            bebop = null;
            System.out.println("State: Bebop turned to null and ready for removal");
        }
        }
        
        
        ///set state to game over if main player health <= 0
        if(swordfish.returnDEAD()== true)
        {   
            earth.stop();
            boss.stop();
            GSM.setSTATE(GameStateManager.GAMEOVERSTATE);
            
        }
       
        ///queues the vicious/boss death sequence
        if(viciouz != null)
        {
        if(viciouz.returnITSTIME() == true)
        {   
            
            System.out.println("In BOSS death");
            BossDeath();
        
        }
        }
        
        ///ends and transitions to the final winning state
        if(viciouz != null)
        {
        if(viciouz.returnENDIT() == true)
        {   
            System.out.println("Ending....");
            boss.stop();
            GSM.setSTATE(GameStateManager.ENDINGSTATE);
        
        }
        }
        
        
        
        ///swordfish plasma cannon and boost cooldown checks5
        checkPLASMA();
        checkBOOST(); 
        ///update all objects
        ml.update();
        Ramsey.update();
        if(bebop != null)
        {
        bebop.update();
        }
        swordfish.update();
        mc.update();
        bc.update();
        pc.update();
        middleconsole.update();
        vhc.update(this);
        
        
        
        ///boss objects
        if(viciouz != null)
        {
        sheildMechanics();
        viciouz.update();
        
        if(stutter == true)
            {
        ExplosionMissileMechanics();
            }
        }
        
        ////boss explosion BOOM!
         for(int i = 0; i < BEXxxx.size(); i++)
        {
            
            
            BEX = BEXxxx.get(i);
            
            BEX.update();
            
             if(BEX.checkExplosion() == true)
             {  
                BEX = null;
                BEXxxx.remove(i);
                
                 System.out.println("State: Boss Explosion Boom Updated");
                
             }
        
        
        }
        
        
        
        
        ////explosion missile mechanics
        for(int i = 0;i < EPxxx.size(); i++)
         {      

            ///2nd method of missile explosions hits
             createMissileExplosionEM();
            
             collision.checkExplosionMissile(EP, ms);
             
             EP = EPxxx.get(i);
             
             EP.update(swordfish);
             
             
             
             if(EP.returnREADY() == true)
             {   
                 //createEMExplosion();
                 
                 EM = new EMissileExplosion(EP.returnX() - 25,EP.returnY() - 50);
                 EME.add(EM);
                 
                 for(int z = 0;z <= 6; z++)
                 {
                 balls = new PlasmaBalls(EP.returnX(), EP.returnY(), level);
                 ballsXXX.add(balls);
                 
                 
                 
                 }
                 EP = null;
                 EPxxx.remove(i);
                 
                 System.out.println("State: Explosion Missile Removed and Updated");
             }
         
         }
        
        
        
        for(int i = 0; i < ballsXXX.size(); i++)
        {
            createMissileExplosionVSPB();
            
            collision.checkPLASMABALLS(balls, ms);
            
            balls = ballsXXX.get(i);
            
            balls.update();
            
             ///remove if out of window
            if((balls.returnY() >= 810 || balls.returnY() <= 0) && balls != null)
             {  
                 balls = null;
                 ballsXXX.remove(i);
                 
                 System.out.println("State: Plasma Balls Updated and Removed");
                 
             
             }////check if ball lost all its hitpoints 3 hits from regular missiles
            else if(balls.checkDEAD() == true)
            {
                balls = null;
                ballsXXX.remove(i);
                
                System.out.println("State: Plasma Balls Updated and Removed");
            }
        
        
        }
        
        
        
        ////explosion missile explosion
        for(int i = 0; i < EME.size(); i++)
        {
            
            
            EM = EME.get(i);
            
            EM.update();
            
             if(EM.checkExplosion() == true)
             {
                EM = null;
                EME.remove(i);
                
                 System.out.println("State: Explosion Missile Removed and Linked List Updated");
             }
        
        
        }
        
        
        ///homing missile boss shots update
         for(int i = 0;i < purple.size(); i++)
         {
             
             //create missile explosion
             createMissileExplosionHM();
             
             collision.checkHOMINGvsREG(selfie,ms);
             
             selfie = purple.get(i);
             
             selfie.update(swordfish, viciouz);
             
             ///add removing out of window here
             if(selfie.returnY() >= 810 && selfie != null)
             {  
                 selfie = null;
                 purple.remove(i);
                 
                 System.out.println("State: Homing Missile Removed and Linked List Updated");
                 
             
             }
         
         }
        
        ////REGULAR MISSILE
        for(int i = 0;i < ms.size();i++)
        {   
            
            createSmallMeteorExplosion();
            
            collision.checkBOSS(mx, smallM);
            
            createEnemyGreenExplosion();
            
            collision.checkGREENvsMIS(mx, geXXX);
            
            createEnemyRedExplosion();
            
            collision.checkREDvsMIS(mx, reXXX);
            
            createGGSExplosion();
            
            collision.checkGGSvsMIS(mx, ggsXXX);
            
            mx = ms.get(i);
           
            mx.update();
            
            if(mx.getY() <= -2 && mx != null)
             {   
                 mx = null;
                 ms.remove(i);
                 
                 System.out.println("State: Missile Linked List Updated and Removed Null");

             }
            
        }
        
        ///////PLASMA CANNON 
        for(int i = 0;i < ps.size();i++)
        {   
            
            
            ///red kamakazi vs plasma
            collision.checkREDvsPLASMA(plasma, reXXX);
            
            
            collision.checkPlasmaCvsPlasmaB(plasma, ballsXXX);
            //checkPlasmaCvsPlasmaB
            
            createMeteorExplosion();
            collision.checkPLASMAvsEM(plasma, EPxxx);
            
            
            createMeteorExplosion();
            collision.checkPLASMAvsHM(plasma, purple);
            
            ///collision checking plasma vs meteors
            collision.checkBOSS2(plasma, smallM);
            
            createMeteorExplosion();
            
            collision.checkPLASMABIG(plasma, metz);
            ////end collision plasma vs meteors
            
            
            createEnemyPinkExplosion();
            
            collision.checkPINKvsPLASMA(plasma, peXXX);
            
            
            createEnemyGreenExplosion();
            
            collision.checkGREENvsPLASMA(plasma, geXXX);
            
            plasma = ps.get(i);
            
            plasma.update();
            
             if(plasma.getY() <= -2 && plasma != null)
             {
                 plasma = null;
                 ps.remove(i);
                 
             
                 System.out.println("State: Plasma Cannon Removed and Updated");
             }
            
            
        }
        
        
        
        //////BIG METEORS
        ////big meteors act as shields for the small meteors
         for(int i = 0;i < metz.size();i++)
        {   

            ////small missiles cannot kill big meteors
            createMissileExplosion();
            
            
            collision.checkSMALLBIG(met, ms);
            
            
            
            met = metz.get(i);
            
            met.update();
            
             if(met.getY() >= 810 && met != null)
             {  
                 met = null;
                 metz.remove(i);
                 
                 System.out.println("State: Meteor Removed and Updated");
                 
                 
             
             }
            
                   
            
        }

        ////SMALL METEORS////
         for(int i = 0; i < smallM.size();i++)
         {   
             
             smetz = smallM.get(i);

             
             smetz.update();
             
             if(smetz.getY() >= 820 && smetz != null)
             {
                 
                 smetz = null;
                 smallM.remove(i);
                 
             
             }
         
         
         }
         
         /////MISSILE EXPLOSIONS////
         for(int i = 0;i < smallExx.size(); i++)
         {
             
            smallE = smallExx.get(i);

             
             smallE.update();
             
             ////for removing the explosion after 920 milliseconds
             if(smallE.checkExplosion() == true)
             {
                 smallE = null;
                smallExx.remove(i);
                
                 System.out.println("State: Missile Explosion Removed and Updated");
             }
             
             
         }
         
         
         
         ////BIG METEOR EXPLOSION
         for(int i = 0;i < MExxx.size(); i++)
         {
             
             ME = MExxx.get(i);

             
             ME.update();
             
             
            if(ME.checkExplosion() == true)
            {
                ME = null;
                MExxx.remove(i);
                
                System.out.println("State: Big Meteor Explosion Removed and Updated");
            
            }
             
             
         }
         
         
         
         /////SMall meteor explosion
          for(int i = 0;i < SMExxx.size(); i++)
         {
             
             SME = SMExxx.get(i);

             
             SME.update();
             
             
            if(SME.checkExplosion() == true)
            {
                SME = null;
                SMExxx.remove(i);
            
                
                System.out.println("State: Small Meteor Explosion Removed and Updated");
            }
         }
            ////pink enemy explosion update
         for(int i = 0;i < expXXX.size(); i++)
         {
             
             exp = expXXX.get(i);

             
             exp.update();
             
             
            if(exp.checkExplosion() == true)
            {
                exp = null;
                expXXX.remove(i);
            
                System.out.println("State: Pink Enemy Explosion Removed and Updated");
            }
            
            
         }
         
             
         ////Removing every enemy after the go out of sight on the gamepanel except the green enemy.
         
         ////green enemies update 
         for(int i = 0; i < geXXX.size();i++)
         {
             
             
             ge = geXXX.get(i);
             
             ge.update();
             
             
             if(ge != null)
             {
               if(ge.getY() >= 810 || ge.getX() <= 0 || ge.getX() >= 680)
             {
                 ge = null;
                 geXXX.remove(i);
                 
                 System.out.println("State: Green Enemy Removed and Updated");
             
             }
               
          
             }
         
         
         }
         
         if(geXXX.size() != 0)
         {
             System.out.println("Green Enemy Size: " + geXXX.size());
         }
         
         ////red enemies update
         for(int i = 0; i < reXXX.size();i++)
         {
             re = reXXX.get(i);
             
             re.update();
         
             if(re != null)
             {
             
              if(re.returnY() >= 810 || re.returnX() < 0 || re.returnX() > 680)
             {
                 re = null;
                 reXXX.remove(i);
                 
                 System.out.println("State: Red Enemy Removed and Updated");
             
             }
              
             
               
             }
              
         
         }
         
         if(reXXX.size() != 0)
         {
             System.out.println("Red Enemy Size: " + reXXX.size());
         }
         
         ////green gun shot update
         for(int i = 0; i < ggsXXX.size();i++)
         {
             ggs = ggsXXX.get(i);
             
             ggs.update();
             
             
             if(ggs.returnY() >= 810 && ggs != null)
             {
                 ggs = null;
                 ggsXXX.remove(i);
                 
             
                 System.out.println("State: Green Enemy Removed and Updated");
             }
         
         }
         
         ////pink enemy update
         for(int i = 0; i < peXXX.size();i++)
         {
             createMissileExplosionPink();
             
             collision.checkPINKvsMIS(pe, ms);
             
             pe = peXXX.get(i);
             
             ////to charge after 10 seconds
             pe.checkTime();
             pe.update();
             
             
             if(pe != null)
             {
             if(pe.getY() > 810 || pe.getX() < 0 || pe.getX() > 680)
             {
                 
                 pe = null;
                 peXXX.remove(i);
                 
                 System.out.println("State: Pink Enemy Removed and Updated");
             
             }
             
           
             }
         
         
         }
         
         if(peXXX.size() != 0)
         {
             System.out.println("Pink Enemy Size: " + peXXX.size());
         }
         
         ////green explosion update
         for(int i = 0; i < gexpXXX.size();i++)
         {  
             gexp = gexpXXX.get(i);
             
             gexp.update();
             
             if(gexp.checkExplosion() == true)
            {
                gexp = null;
                gexpXXX.remove(i);
            
                System.out.println("State:: Green Enemy Exploosion Updated and Removed");
                
            }
         
         
         }
         ////red explosion update
         for(int i = 0; i < rexpXXX.size();i++)
         {  
             rexp = rexpXXX.get(i);
             
             rexp.update();
             
             if(rexp.checkExplosion() == true)
            {
                rexp = null;
                rexpXXX.remove(i);
            
                
                System.out.println("State: Red Enemy Explosion Updated and Removed");
            }
         
         
         }
         
         ////green gun shot update
         for(int i = 0; i < directXXX.size();i++)
         {  
             direct = directXXX.get(i);
             
             direct.update();
             
             if(direct.checkExplosion() == true)
            {
                
                direct = null;
                directXXX.remove(i);
            
                
                System.out.println("State: Green Gun Shot Explosion Removed and Updated");
            }
         
         
         }
         
         

         
        ////COLLISION CHECKING for the swordfish against objects/////    
 
        ///check swordfish vs plasmaballs
        collision.checkSFvsPlasmaB(swordfish, ballsXXX, purple);
        //check swordfish vs all ships
        collision.checkSFvsALLSHIPS(swordfish, ggsXXX, geXXX, reXXX, peXXX);
        ////small meteors and swordfish
        collision.checkSMALL(swordfish, smallM);
        ///big meteors and swordfish
        collision.checkBIG(swordfish, metz);
        ////missile
        
        
        
        if(bossready == true)
        {
        ///vicious collision detection
        collision.checkViciousVSSF(viciouz, sheld, ms, ps, ballsXXX);
          
        ///sheild colission detection and regular missile explosion
        collision.checkSHEILD(sheld, ms);
        createMissileExplosion(); 
        }   
        
        
        
    }
    
   
    ///SWORDFISH METHODS a.k.a. main player( sonya peirce) //
    /*includes*/
    //plasma cannon (S -> big huge special blast)
    ///boost (SPACEBAR -> faster x and y movements) 
    ////algorithm is cooldown based
    public void checkPLASMA()
    {
        if(special == true)
        {
            ari.stopM();
            kevin = ari.getDurationM();
            ari.resetStopM();
            
            
                if(kevin >= 20000)
                {
                    special = false;
                    kevin = 0;
                    ari.resetStartM();
                    ari.resetStopM();
                    pc.setPLASMA(this.returnPLASMA());
                    
                }
        
        }
    
    
    
    }
    ///for boosting mechanics/purposes swordfish main player object
    ///algorithm is cooldown based
    public void checkBOOST()
    {       
        
            ///10 second boost period for the sword fish
            if(checkBOOST == true)
            {
                Boost.stopM();
                cooldown = Boost.getDurationM();
                Boost.resetStopM();
                
                        if(cooldown >= 10000)
                        {
                            boostON = false;
                            Boost.resetStartM();
                            cooldown = 0;
                            Boost.startM();
                            checkBOOST = false;
                            boosted = false;
                            swordfish.setBOOST(this.returnBOOSTED());
                            
                        }
            }
            
            
            ////10 second cooldown after boost has been depleted
            if(boostON == false && isBoostready == false)
            {
                Boost.stopM();
                cooldown = Boost.getDurationM();
                Boost.resetStopM();
                    if(cooldown >= 10000)
                    {
                        isBoostready = true;
                        cooldown = 0;  
                        Boost.resetStartM();
                        bc.setBOOST(this.returnBOOSTED());
                    }
                
            
            }
          
            

    }
    ////end swordfish methods/////
    
    
    ////simulates the beginning sequence of the game
    public void simulate()
    {   
        
        //RealFolk.stopM();
        //TimePassed = RealFolk.getDurationM();
        //RealFolk.resetStopM();
        //System.out.println(TimePassed);
        
        if(TimePassed <= 2500)
        {
            draw1 = true;
        }
        else if(TimePassed <= 5000 && TimePassed > 2500)
        {
            draw2 = true;
        }
        else if(TimePassed <= 7500 && TimePassed > 5000)
        {
            draw3 = true;
        }
        else if(TimePassed > 7500)
        {
            draw3 = false;
           
        }
       
        
        if(TimePassed > 19000)
        {
            Ramsey.scroll(0, 2);
            controls = true;
        }
    
    }
    
    ////main render method
    /*includes*/
    //all object renders
    //queues certain methods after a time frame
    @Override
    public void render(Graphics2D g)
    {   
        
        Ramsey.render(g);
        g.setColor(Color.WHITE);
        g.setFont(fontz);
        RealFolk.stopM();
        TimePassed = RealFolk.getDurationM();
        RealFolk.resetStopM();
        
        if(TimePassed <= 20000)
        {
           simulate();
        }
        
        
        if(draw1 == true)
        {
        g.drawString("Find Vicious", 300, GamePanel.HEIGHT / 2);
        draw1 = false;
        }
        else if(draw2 == true && draw1 == false)
        {
        g.drawString("Kill Vicious", 300, GamePanel.HEIGHT / 2);
        draw2 = false;
        }
        else if(draw3 == true && draw1 == false && draw2 == false)
        {
        g.drawString("Get Revenge", 300, GamePanel.HEIGHT / 2);
        }
        
        
        
        /////REGULAR GUNSHOT
          for(int i = 0;i < ms.size();i++)
        {
            mx = ms.get(i);
            
            mx.render(g);
        }
          /////PLASMA GUN
          for(int i = 0;i < ps.size();i++)
        {
            plasma = ps.get(i);
            
            plasma.render(g);
        }
        
         /////BIG METEORS///
        for(int i = 0;i < metz.size();i++)
        {
            met = metz.get(i);
            
            met.render(g);
        }
        ////SMALL METEORS///
        for(int i = 0; i < smallM.size();i++)
         {
             smetz = smallM.get(i);
             
             smetz.render(g);
         
         
         }
         
         ////for the big meteor explosion
         
          for(int i = 0;i < MExxx.size(); i++)
         {
             
            ME = MExxx.get(i);

             
             ME.render(g);

             
         }
          ////small meteor explosion rendering
           for(int i = 0;i < SMExxx.size(); i++)
         {
             
            SME = SMExxx.get(i);

             
             SME.render(g);

             
         }
           /////green enemy rendering
            for(int i = 0; i < geXXX.size();i++)
         {
             ge = geXXX.get(i);
             
             ge.render(g);
         
         
         }
            ////red enemy rendering
            for(int i = 0; i < reXXX.size();i++)
         {
             re = reXXX.get(i);
             
             re.render(g);
         
         
         }
            ////green gun shot rendering
            for(int i = 0; i < ggsXXX.size();i++)
         {
             ggs = ggsXXX.get(i);
             
             ggs.render(g);
         
         
         }
            
            ////sheild enemy update
         for(int i = 0; i < peXXX.size();i++)
         {
             pe = peXXX.get(i);
             
             pe.render(g);
         
         
         }
         ///pink enemy explosion update
         for(int i = 0;i < expXXX.size(); i++)
         {
             
             exp = expXXX.get(i);

             
             exp.render(g);
             
             
           
         }   
         ////green explosion render
         for(int i = 0; i < gexpXXX.size();i++)
         {
             gexp = gexpXXX.get(i);
             
             gexp.render(g);
         
         
         }
         ///red explosion render
         for(int i = 0; i < rexpXXX.size();i++)
         {  
             rexp = rexpXXX.get(i);
             
             rexp.render(g);
             
            
         
         
         }
         ////green gun shot explosion
         for(int i = 0; i < directXXX.size();i++)
         {  
             direct = directXXX.get(i);
             
             direct.render(g);
         }
         
         ///homing missile boss shots
         for(int i = 0;i < purple.size(); i++)
         {
             selfie = purple.get(i);
             
             selfie.render(g);
         
         }
         
          ///explosion missilke
        for(int i = 0; i < EME.size(); i++)
        {
            EM = EME.get(i);
            
            EM.render(g);
        
        
        }
         
        
        
        if(TimePassed >= 19750)
        {   
            
            g.setColor(Color.WHITE);
            g.setFont(controlz);
            g.drawString("BOOST", 34, 663 );
            g.drawString("PLASMA CANNON", 689, 663);
            g.drawString("HEALTH",372,688);
            g.drawString("SCORE: " + spacescore.returnSS(), 25, 560);
            g.drawString(swordfish.returnHITS() + "%",378,671);
             //g.drawString("VICIOUS",372,10);
            //g.drawString(viciouz.returnHITS() + "%", 372, 24);
            METEORSHOWER();
            enemySHOWER();
            
            
            //keepFIRING();
            
        
        }
        
         
         g.setColor(Color.WHITE);
         g.setFont(controlz);
        
        if(bebop != null)
        {
        bebop.render(g);
        }
        ///score = spacescore.returnSS();
        swordfish.render(g);
        mc.render(g);
        ml.render(g);
        bc.render(g);
        pc.render(g);
        middleconsole.render(g);
        
        
       
         /////explosion missile  explosion linked list
         for(int i = 0;i < EPxxx.size(); i++)
         {
             EP = EPxxx.get(i);
             
             EP.render(g);
         
         }
         
        
         
         if(sheld != null)
            
        {
            
        sheld.render(g);
        ///end boss objects
        
        }
        
        
        
        //ra.render(g);
        //la.render(g);
         if(viciouz != null)
         {
        viciouz.render(g);
         }
        ///BD.render(g);
        vhc.render(g);
        
        
        if(hookah == true)
        {
        bossHomingMShots();
        }//EP.render(g);

        /////for the small missile explosion
         for(int i = 0;i < smallExx.size(); i++)
         {
             
            smallE = smallExx.get(i);

             
            smallE.render(g);
         }
      
       
        
        
        
        
        if(vhc.returnTHERE() == true)
        {
        g.drawString("VICIOUS",372,10);
        g.drawString(viciouz.returnHITS() + "%", 372, 24);
        }
        
        
         
         ///plasma balls
          for(int i = 0; i < ballsXXX.size(); i++)
        {
            balls = ballsXXX.get(i);
            
            balls.render(g);
            
          
        
        
        } 
          
          ////boss explosion
         for(int i = 0; i < BEXxxx.size(); i++)
        {
            
            
            BEX = BEXxxx.get(i);
            
            BEX.render(g);
        
        
        }
         
        
        
        
        
        
        if(TimePassed >= 46000)
        {   
            //System.out.println("Punished Snake Should Start Now");
            PunishedSnake();
        
        }
        
        if(TimePassed >= 90000)
        {   
            beginpunishment = true;
            //System.out.println("TimePassed = " + TimePassed);
            //System.out.println("Size of Gexx is " + geXXX.size());
            ///boolean values triggers to not spawn anymore enemies but 
            ///enemies may still live on if user didnt kill them all
        }
        
        if(TimePassed >= 92000)
        {   
            bossready = true;
            
            ///begins boss battle if true and green enemies link list is 0
           // System.out.println("TimePassed = " + TimePassed);
            //System.out.println("Size of Gexx is " + geXXX.size());
            
            if(doneloading == false) ///stop calling this if already called
            {
                
            
            bringINboss();
            
            }
            
            
        }
       
        
       
        
    }
    
    
    
   
    ////VICIOUS METHODS a.k.a. boss//// 
    /*includes*/
    //explosion missile (gun)
    //homing missile (gun)
    //sheild
    //and boss death sequence
    
    ///explosion missile mechanics,and the blow up sequence
    public void sheildMechanics()
    {       
            ////sheild boss mechanics
            ////15 seconds intervals
            //random generator generates seconds from 2 to 14 for the duration of the shield
            ///each 15 seconds interval will have a different shield duration
            ///spawn values or instance variables
            double dattime = 0;
            double lower = 2;
            double upper = 14;
            double mscott = 0;
        if(sheld != null)   
        {

            sheld.update();
        }
        if(viciouz.returnDEAD() == true)
        {   
            sheld.setX(-300);
            sheld.setY(-300); 
            ///sheld = null;
        }
        else if(viciouz.returnDEAD() == false)
        {  
        if(hookah == true)
        {
            
            
            //15000 = 15 seconds
            sheld.returnEnergy().stopM();
            dattime = sheld.returnEnergy().getDurationM();
            sheld.returnEnergy().resetStopM();
            
            ///info
            System.out.println("Random Seconds Durationis " + duration);
            System.out.println("The 15 second interval is at " + dattime);
            System.out.println("Sheld Boolean value is at " + sheld.destroySHEILD());
            
            
            if(dattime >= 15000)
            {
                sheld.returnEnergy().startM();
                dattime = 0;
                duration = (Math.random() * (upper - lower) + lower) * 1000;
                System.out.println("Duration is " + duration);
                halpert = true;
                sheldD.startM();
                
            }
            
            
            
        
            if(halpert == true)
                
            {       
                
                sheldD.stopM();
                
                mscott = sheldD.getDurationM();
                
                sheldD.resetStopM();
        
                System.out.println("sheldD is at " + mscott);
                
                if(mscott >= duration)
                {
                    sheldD.startM();
                    duration = 0;
                    halpert = false;
                    mscott = 0;
                }
            
            }
        
        }
        
        if(duration == 0)
        {
            sheld.setMove(false);
        }
        
        if(duration != 0)
        {
            sheld.setMove(true);
        
        }

        if(sheld.destroySHEILD() == true)
        {   
            ///if sheild is on vicious he does not take damage
            
            sheld.setX(viciouz.getX());
            sheld.setY(viciouz.getY());
        }
        
        if(sheld.destroySHEILD() == false)
        {
            sheld.setX(-500);
            sheld.setY(-500);
        }
        
        
       }
        
    
    }
    /////ending sequence to the game that includes an 5-10 seconds explosion
    public void BossDeath()
    {       
            
            hookah = false;
            stutter = false;
            halpert = false;
           
            
            for(int i = 0; i <= 2; i++)
            {
                BEX = new ViciousExplosion(viciouz.getX() + 100,viciouz.getY() -100);
                BEXxxx.add(BEX);
                BEX = new ViciousExplosion(viciouz.getX() - 100,viciouz.getY() + 100);
                BEXxxx.add(BEX);
                BEX= new ViciousExplosion(viciouz.getX(),viciouz.getY());
                BEXxxx.add(BEX);
            
            }
            
            for(int z = 0;z <= 30; z++)
                 {
                     
                     
                 balls = new PlasmaBalls(viciouz.getX(), viciouz.getY(), level);
                 ballsXXX.add(balls);
                 
                 
                 
                 }
            
            if(viciouz.returnDEAD() == true)
            {
                sheld.setX(-300);
                sheld.setY(-300); 
            
            }
            
            if(viciouz.returnITSTIME() == true)
            {
                
           
                viciouz.setX(-300);
                viciouz.setY(-300);
            
            
            }    
    
    
    }
    ///vicious boss method of firing explosion missile
    ///algorithm is cooldown based
    public void ExplosionMissileMechanics()
    {
        long sue;
        EPtimer.stopM();
        sue = EPtimer.getDurationM();
        EPtimer.resetStopM();
       
        
        if(sue < 2000 && stutter == true && counterSHOT == 0)
        {
            
        if(viciouz.getX() >= 0 && viciouz.getX() <= 200)
        {
            EP = new ExplosionMissile(viciouz.getX(), viciouz.getY() + 50);
            EPxxx.add(EP);
            selfie = new HomingMissile(viciouz.getX()+ 31, viciouz.getY() + 95);
            purple.add(selfie);
            selfie = new HomingMissile(viciouz.getX()+ 175, viciouz.getY() + 95);
             purple.add(selfie);
            counterSHOT += 1;
        }
        else if(viciouz.getX() >= 201 && viciouz.getX() <= 400)
        {
            EP = new ExplosionMissile(viciouz.getX(), viciouz.getY() + 50);
            EPxxx.add(EP);
            selfie = new HomingMissile(viciouz.getX()+ 31, viciouz.getY() + 95);
            purple.add(selfie);
            selfie = new HomingMissile(viciouz.getX()+ 175, viciouz.getY() + 95);
             purple.add(selfie);
            counterSHOT += 1;
        }
        else if(viciouz.getX() >= 401 && viciouz.getX() <= 558)
        {
            EP = new ExplosionMissile(viciouz.getX(), viciouz.getY() + 50);
            EPxxx.add(EP);
            selfie = new HomingMissile(viciouz.getX()+ 31, viciouz.getY() + 95);
            purple.add(selfie);
            selfie = new HomingMissile(viciouz.getX()+ 175, viciouz.getY() + 95);
             purple.add(selfie);
            counterSHOT += 1;
        }
        else
        {
            System.out.println("Vicious is out of bounds - Nothing was fired");
        }
           
        }
        
       if(sue >= 5000 || counterSHOT >= 1)
       {    
           System.out.println("Exiting Explosion missile Mechanics");
           counterSHOT = 0;
           HMtime.resetStartM();
           andy.resetStartM();
           andy.startM();
           HMtime.startM();
           hookah = true;
           stutter = false;
           
           
       }
        
        
    
    }
    ////bringing in the boss and lower hands into the screen
    ////to simulate the boss battle
    public void bringINboss()
    {       
            
            if(geXXX.size() == 0 && peXXX.size() == 0 && reXXX.size() == 0)
            {
            
            if(bossready == true)
            {   
                viciouz.setLevel(this.returnLEVEL());
                bringHIMin = true;
                viciouz.setIncoming(this.returnBOSSstatus());
                System.out.println("Bringing in boss....");
                Ramsey.scroll(0, 6);
                bossloading = true;
                
                
            
            }
            
            }
            
            
            
            if(viciouz.getY() == -10)
            {   ///check this later
                //viciouz.setHITPOINTS(500);
                
                bossloading = false;
                hookah = true;
                HMtime.startM();
                ///life = 0;
                andy.startM();
                earth.stop();
                boss.play();
                doneloading = true; ///stop calling this if already called
                
            }
            
            
            
    
    
    
    }
    ////fires the main boss missile in 1.5 seconds intervals
    ////algirithm is cooldown based
     public void bossHomingMShots()
    {        
        long animate;
        HMtime.stopM();
        boulevard = HMtime.getDurationM();
        HMtime.resetStopM();
        if(hookah == true)
        {
        if(viciouz != null)
        {
        if(boulevard >= 1500)
        {
        selfie = new HomingMissile(viciouz.getX()+ 31, viciouz.getY() + 95);
        purple.add(selfie); 
        HMtime.resetStartM();
        HMtime.startM();
        }
            }
                }
        andy.stopM();
        animate = andy.getDurationM();
        andy.resetStopM();
        System.out.println("In bosshomingMshots");
        System.out.println("Animate is at " + animate);
        System.out.println("Hookah is at " + hookah);
        
        if(animate >= 10000)//10 seconds
        {   
            hookah = false;
            EPtimer.startM();
            animate = 0;
            stutter = true;
        }
        
        
        
    }
    ///end VICIOUS METHODS a.k.a. boss///
      
     
     
    ///MAIN METEOR SHOWER/// -> description: meteors fall in time frames and random x and y's
    ///algorithm is time based
    public void METEORSHOWER()
    {   
       
        
        if(TimePassed >= 22000 && TimePassed <= 22010)
        {       
                int d = 500;
                for(int i = 0;i <= 10;i++)
                {
                     smetz = new SmallMeteor(d,-5, this);
                     smallM.add(smetz);
                     d += 225;
                
                }

        }
        else if(TimePassed >= 24000 && TimePassed <= 24010)
        {       
                
                met = new BigMeteor(400,0, this);
                metz.add(met);
                
                int d = 250;
                for(int i = 0;i <= 6;i++)
                {
                     smetz = new SmallMeteor(d,-5, this);
                     smallM.add(smetz);
                     d += 225;
                
                }
        }
        else if(TimePassed >= 26000 && TimePassed <= 26010)
        {       
                met = new BigMeteor(600,-5, this);
                metz.add(met);
                
                 int d = 275;
                for(int i = 0;i <= 10;i++)
                {
                     smetz = new SmallMeteor(d,-5, this);
                     smallM.add(smetz);
                     d += 225;
                
                }
                
        }
         else if(TimePassed >= 28000 && TimePassed <= 28010)
        {       
                met = new BigMeteor(400,0, this);
                metz.add(met);
                
               int d = 100;
                for(int i = 0;i <= 10;i++)
                {
                     smetz = new SmallMeteor(d,-5,this);
                     smallM.add(smetz);
                     d += 225;
                
                }
        }
        else if(TimePassed >= 30000 && TimePassed <= 30010)
        {       
                met = new BigMeteor(600,-5, this);
                metz.add(met);
                
                int d = 225;
                for(int i = 0;i <= 10;i++)
                {
                     smetz = new SmallMeteor(d,-5, this);
                     smallM.add(smetz);
                     d += 100;
                
                }
        }
        else if(TimePassed >= 32000 && TimePassed <= 32010)
        {       
                met = new BigMeteor(600,-5, this);
                metz.add(met);
                
                int d = 150;
                for(int i = 0;i <= 10;i++)
                {
                     smetz = new SmallMeteor(d,-5,this);
                     smallM.add(smetz);
                     d += 150;
                
                }
        }
        else if(TimePassed >= 34000 && TimePassed <= 34010)
        {       
                met = new BigMeteor(200,-5, this);
                metz.add(met);
                
                 int d = 475;
                for(int i = 0;i <= 7;i++)
                {
                     smetz = new SmallMeteor(d,-5,this);
                     smallM.add(smetz);
                     d += 100;
                
                }
        }
        else if(TimePassed >= 36000 && TimePassed <= 36010)
        {       
            
                int yessir = 400;
                for(int i = 0;i <= 5;i++)
                {
                     met = new BigMeteor(yessir,-5, this);
                     metz.add(met);
                     yessir += 200;
                
                }
                
               
                
                 int d = 500;
                for(int i = 0;i <= 15;i++)
                {
                     smetz = new SmallMeteor(d,-5, this);
                     smallM.add(smetz);
                     d += 100;
                
                }
                
                
        }
        
        
        
    
    
    }
   
    
    ///ENEMY SHOWER/// -> description: enemies attack the main player relentlessly with random x and y's, 
    ///and checks the link list size to determine how many left
    /////algorithm is time based
    public void enemySHOWER()
    {
    
            if(TimePassed >= 38000 && TimePassed <= 38010)
            {   
                int p = 50;
                for(int z = 0;z <= 2;z++)
                {
                    pe = new SheildEnemy(p, -5);
                    peXXX.add(pe);
                    p += 200;
                
                }
                
                int xD = 75;
                for(int i = 0; i <= 3;i++)
                {   
                    ge = new GunEnemy(xD, -6, this);
                    geXXX.add(ge);
                    xD += 200;
                    
                    
                                if(geXXX != null)
                                {
                                for(i = 0; i < geXXX.size();i++)
                                {
                                        ggs = new GunGreenShot(ge.getX(), ge.getY() + 10, this);
                                        ggsXXX.add(ggs);
                                }
                                }
                }
                

            }
            else if(TimePassed >= 40000 && TimePassed <= 40010)
            {           
                
                        if(geXXX != null)
                        {
                                for(int i = 0; i < geXXX.size();i++)
                                {   
                                    
                                    ggs = new GunGreenShot(geXXX.get(i).getX(), geXXX.get(i).getY()+ 10, this);
                                    ggsXXX.add(ggs);
                                }
                        }
                
                        
                        
                int x = 150;
                for(int i = 0; i < 5;i++)
                {   
                    re= new BluntForceEnemy(x,-6, this);
                    reXXX.add(re);
                    x += 225;
 
                }
                           
            }
            else if(TimePassed >= 42000 && TimePassed <= 42010)
            {
                        if(geXXX != null)
                        {
                                for(int i = 0; i < geXXX.size();i++)
                                {
                                    ggs = new GunGreenShot(geXXX.get(i).getX(), geXXX.get(i).getY()+ 10, this);
                                    ggsXXX.add(ggs);
                                }
                        }
                
                
                
                        int x = 150;
                        for(int i = 0; i < 5;i++)
                         {   
                            re= new BluntForceEnemy(x,-6, this);
                            reXXX.add(re);
                            x += 225;
 
                            }
                         
    
    
            }
            else if(TimePassed >= 44000 && TimePassed <= 44010)
            {
                        int x = 175;
                        for(int i = 0; i < 5;i++)
                         {   
                            re= new BluntForceEnemy(x,-6, this);
                            reXXX.add(re);
                            x += 225;
 
                         }
                        
                        
                if(geXXX.size() <= 10)
                {     
                int xD = 139;
                    for(int z = 0; z <= 4;z++)
                {   
                    ge= new GunEnemy(xD, -6, this);
                    geXXX.add(ge);
                    xD += 175;
                    
                    
                               
                } 
                }
                
                
                 if(geXXX != null)
                        {
                                for(int i = 0; i < geXXX.size();i++)
                                {
                                    ggs = new GunGreenShot(geXXX.get(i).getX(), geXXX.get(i).getY() + 10, this);
                                    ggsXXX.add(ggs);
                                }
                        }
                
            
            
            
            }
            else if(TimePassed >= 46000 && TimePassed <= 46010)
            {
                        
                         int p = 175;
                for(int z = 0;z <= 2;z++)
                {
                    pe = new SheildEnemy(p, -5);
                    peXXX.add(pe);
                    p += 205;
                
                }
                
                        int x = 175;
                        for(int i = 0; i < 5;i++)
                         {   
                            re= new BluntForceEnemy(x,-6, this);
                            reXXX.add(re);
                            x += 225;
 
                         }
                
                
                
                
                if(geXXX.size() <= 10)
                {     
                int xD = 139;
                    for(int z = 0; z <= 2;z++)
                {   
                    ge= new GunEnemy(xD, -6, this);
                    geXXX.add(ge);
                    xD += 225;
                    
                    
                               
                } 
                }
                
                
                 if(geXXX != null)
                        {
                                for(int i = 0; i < geXXX.size();i++)
                                {
                                    ggs = new GunGreenShot(geXXX.get(i).getX(), geXXX.get(i).getY()+ 10, this);
                                    ggsXXX.add(ggs);
                                }
                        }
                
            gun.startM();
            }
            
            
            
    }
    
       
    ////PUNISHING SNAKE METHOD/// -> description: punishes the main player for surviving by sending more enemies
    ////algirithm is time based
   public void PunishedSnake()
   {                    
       
                        if(geXXX != null && TimePassed >= 47000 && beginpunishment == false)
                        {       
                                //System.out.println("Starting the punishment!");
                                gun.stopM();
                                gunduration = gun.getDurationM();
                                gun.resetStopM();
                                Random rand = new Random();
                        
                                            
                                            if(gunduration >= 3000 && gunduration <= 5000)
                                            {
                                    
                                            for(int i = 0; i < geXXX.size();i++)
                                            {
                                                ggs = new GunGreenShot(geXXX.get(i).getX(), geXXX.get(i).getY()+ 10, this);
                                                ggsXXX.add(ggs);
                                            }
                                            
                                            if(geXXX.size() <= 5)
                                            {     
                                                
                                                for(int z = 0; z <= 2;z++)
                                            {   
                                                int yessir = rand.nextInt(500) + 1;
                                                ge= new GunEnemy(yessir, -6, this);
                                                geXXX.add(ge);
                                                
                    
                    
                               
                                            } 
                                            }
                                            
                                            
                                            if(peXXX.size() <= 2)
                                            {
                                            int pinky = 25;
                                             for(int z = 0;z <= 3 ;z++)
                                            {
                                            //int pinky = rand.nextInt(500) + 1;
                                            pe = new SheildEnemy(pinky, -5);
                                            peXXX.add(pe);
                                            pinky += 100;
                                            
                                            
                                            for(int i = 0; i < 2;i++)
                                            {   
                                            int redy = rand.nextInt(700) + 50;
                                            re= new BluntForceEnemy(redy,-6, this);
                                            reXXX.add(re);
                                            
 
                                            }
                                            
                                            
                                            
                                            
                
                                            }
                                            }
                                            gun.resetStartM();
                                            gun.startM();
                                
                                            }
                                
                                
                                
                        }
   
   
   }
   
   
   
   
   
   
   ////UTILITY METHODS///
   public int returnSTATE()
   {
      return 1; 
   }
   
    public void keyPressed(int z)
    {
       
    
    if(controls == false)
        
    {   System.out.println("Initiating Controls...");
    
    }
    else if(controls == true)
    {
        if(z == KeyEvent.VK_RIGHT && boostON == false)
        {
            swordfish.setDX(5);
        }
        else if(z == KeyEvent.VK_RIGHT && boostON == true)
        {
            swordfish.setDX(9);
        }
        else if(z == KeyEvent.VK_LEFT && boostON == false)
        {
            swordfish.setDX(-5);
        }
        else if(z == KeyEvent.VK_LEFT && boostON == true)
        {
            swordfish.setDX(-9);
        }
        
        else if(z == KeyEvent.VK_UP && boostON == false)
        {
           swordfish.setDY(-5);
        }
        else if(z == KeyEvent.VK_UP && boostON == true)
        {
           swordfish.setDY(-9);
        }
        else if(z == KeyEvent.VK_DOWN && boostON == false)
        {
            swordfish.setDY(5);
        }
        else if(z == KeyEvent.VK_DOWN && boostON == true)
        {
            swordfish.setDY(9);
        }
        else if(z == KeyEvent.VK_S && special == false && bossloading == false)
        {
                plasma = new PlasmaBall(swordfish.getX() - 8,swordfish.getY()- 3);
                ps.add(plasma);
                special = true;
                ari.startM();
                pc.setPLASMA(this.returnPLASMA());
                
                
        }
        else if(z == KeyEvent.VK_F && bossloading == false)
        {
                mx = new MainMissile(swordfish.getX() + 24,swordfish.getY() - 5, this);
                ms.add(mx);
                
        }
        else if(z == KeyEvent.VK_SPACE)
        {       
                    if(isBoostready == true)
                    {   boosted = true;
                        isBoostready = false;
                        boostON = true;
                        Boost.startM();
                        checkBOOST = true;
                        swordfish.setBOOST(this.returnBOOSTED());
                        bc.setBOOST(this.returnBOOSTED());
                        
                    }
        
        }
    }
    }
       
    public void keyReleased(int z)
    {
        
        
        if(z == KeyEvent.VK_RIGHT)
        {
            swordfish.setDX(0);
        }
        else if(z == KeyEvent.VK_LEFT)
        {
            swordfish.setDX(0);
        }
        else if(z == KeyEvent.VK_UP)
        {
           swordfish.setDY(0);
        }
        else if(z == KeyEvent.VK_DOWN)
        {   
            
            swordfish.setDY(0);
            
        }
    }
    public void mousePressed(MouseEvent e)
    {
    
    }
     
///GETTER UTILITY METHODS
public int returnBOOSTED()
{  
    if(boosted == true)
    {
        return 1;
    }
    else
    return 2;
    
}
public int returnPLASMA()
{

    if(special == false)
    {
        return 1;
        
    }
    else
    return 2;

}
public String returnLEVEL()
{
    return level;
}
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
public Boolean returnHOOKAH()
{
    return hookah;
}
////END UTILITY METHODS





   /////***********************EXPLOSION GENERATING METHODS*////////////////////////
    ///*Psuedo code*//
    /* in collision class, at each collision a specific x and y are set at the hit at X and Y.
    //check if x and y are not equal to 0
    //create object explosion at those specific x and y's
    //object has a timer so it removes itself from the game
    //set specific x and y back to 0
    */
   
    //green gun shot explosion
    public void createGGSExplosion()
    {
         
        
        if(collision.returnggsX() != 0 || collision.returnggsY() != 0)
        {
            
            direct = new GGSExplosion(collision.returnggsX(),collision.returnggsY());
            directXXX.add(direct);
            collision.setggsX(0); 
            collision.setggsY(0);
            
            

        }
    
        
    
    
    }
    ////red explosion
     public void createEnemyRedExplosion()
    {   
        if(TimePassed > 37000)
        {
        
        if(collision.returnBigX() != 0 || collision.returnBigY() != 0)
        {
            
            rexp = new RedsExplosion(collision.returnBigX(),collision.returnBigY());
            rexpXXX.add(rexp);
            collision.setBigX(0);
            collision.setBigY(0);
            
            

        }
    
        }
    
    }
    /////green explosion
    public void createEnemyGreenExplosion()
    {   
        if(TimePassed > 37000)
        {
        ////finish setters and getters
        if(collision.returnSMALLX() != 0 || collision.returnSMALLY() != 0)
        {
            
            rexp = new RedsExplosion(collision.returnSMALLX()+25,collision.returnSMALLY()+25);
            
            rexpXXX.add(rexp);
            rexp = new RedsExplosion(collision.returnSMALLX()+45,collision.returnSMALLY()+25);
            
            rexpXXX.add(rexp);
            rexp = new RedsExplosion(collision.returnSMALLX()-15,collision.returnSMALLY()+25);
            
            rexpXXX.add(rexp);
            rexp = new RedsExplosion(collision.returnSMALLX()+25,collision.returnSMALLY()+50);
            
            rexpXXX.add(rexp);
            collision.setSMALLX(0);
            collision.setSMALLY(0);
            
            

        }
        
        if(collision.returnZX() != 0 || collision.returnZY() != 0)
        {
            rexp = new RedsExplosion(collision.returnZX()+25,collision.returnZY()+10);
            rexpXXX.add(rexp);
            rexp = new RedsExplosion(collision.returnZX()+45,collision.returnZY()+25);
            rexpXXX.add(rexp);
            rexp = new RedsExplosion(collision.returnZX()-15,collision.returnZY()+25);
            rexpXXX.add(rexp);
            rexp = new RedsExplosion(collision.returnZX()+25,collision.returnZY()+50);
            rexpXXX.add(rexp);
            collision.setZX(0);
            collision.setZY(0);
        
        }
        
    
        }
    
    }
    public void createEnemyPinkExplosion()
    {
        if(collision.returnpinkX() != 0 || collision.returnpinkY() != 0)
        {
            
            exp = new PinksExplosion(collision.returnpinkX()+ 25,collision.returnpinkY() + 10);
            expXXX.add(exp);
            exp = new PinksExplosion(collision.returnpinkX() + 45,collision.returnpinkY() + 25);
            expXXX.add(exp);
            exp = new PinksExplosion(collision.returnpinkX() - 15,collision.returnpinkY() + 25);
            expXXX.add(exp);
            exp = new PinksExplosion(collision.returnpinkX()+ 25,collision.returnpinkY() + 50);
            expXXX.add(exp);
            collision.setpinkX(0);
            collision.setpinkY(0);
            
            

        }
        
    
    
    
    }
    /////create small meteor explosion
    public void createSmallMeteorExplosion()
    {   
        if(TimePassed <= 37000)
    {
             if(collision.returnSMALLX() != 0 || collision.returnSMALLY() != 0)
            {
            
            SME = new SMeteorsExplosion(collision.returnSMALLX() - 10, collision.returnSMALLY() - 10);
            SMExxx.add(SME);
            
            
            

            }
            
             
            collision.setSMALLX(0);
            collision.setSMALLY(0);
    }        
    }
    public void createMissileExplosionVSPB()
    {
        if(collision.returnPIMPX() != 0 && collision.returnPIMPY() != 0)
        {
             smallE = new MissilesExplosion(collision.returnPIMPX() + 30, collision.returnPIMPY()+ 30);
             smallExx.add(smallE);
        
        }
        collision.setPIMPX(0);
        collision.setPIMPY(0);
    
    }  
    ////for generating an explosion when the missiles hits an object
    public void createMissileExplosion()
    {   
        if(collision.returnMissileX() != 0 || collision.returnMissileY() != 0)
            {
                
                if(hookah == false)
                {
                smallE = new MissilesExplosion(collision.returnMissileX(),collision.returnMissileY()+ 125);
                smallExx.add(smallE);
                
                }
                else
                {
                smallE = new MissilesExplosion(collision.returnMissileX() + 100,collision.returnMissileY()+ 235);
                smallExx.add(smallE);
                }
                
                collision.setMissileX(0);
                collision.setMissileY(0);
            }
        
        

        
    }
    /////creating missile explosions for hits against the ExplosionMissile
    public void createMissileExplosionEM()
    {   
        if(collision.returnMissileX2() != 0 || collision.returnMissileY2() != 0)
            {
                
                
                smallE = new MissilesExplosion(collision.returnMissileX2() + 5,collision.returnMissileY2() + 95);
                smallExx.add(smallE);
                
                
                
                collision.setMissileX2(0);
                collision.setMissileY2(0);
            }
        
        

        
    }
    public void createMissileExplosionHM()
    {   
        if(collision.returnMissileX2H() != 0 || collision.returnMissileY2() != 0)
            {
                
                
                smallE = new MissilesExplosion(collision.returnMissileX2H() + 5,collision.returnMissileY2H() + 80);
                smallExx.add(smallE);
                
                
                
                collision.setMissileX2H(0);
                collision.setMissileY2H(0);
            }
        
        

        
    }
    //////Explosion Missile Explosion by boss generated
    public void createEMExplosion()
    {
            if(EPxxx != null)
            {
               for(int i = 0; i < EPxxx.size();i++)
               {
                   if(EP.returnREADY() == true)
                   {
                       EM = new EMissileExplosion(EP.returnX(),EP.returnY());
                       EME.add(EM);
                   }
               
               }
            
            }
           
        
    
    
    }
    ////pink explosion
    public void createMissileExplosionPink()
    {   
        if(TimePassed > 37000)
        {
        if(collision.returnMissileX() != 0 || collision.returnMissileY() != 0)
            {
                smallE = new MissilesExplosion(collision.returnMissileX() + 25,collision.returnMissileY() + 100);
                smallExx.add(smallE);
                collision.setMissileX(0);
                collision.setMissileY(0);

            }
        }
        
    }
    ////meteor explosion
    public void createMeteorExplosion()
    {  
        
        if(collision.returnBigX() != 0 || collision.returnBigY() != 0)
        {
            
            if(TimePassed <= 37000)
            {
            ME = new MeteorsExplosion(collision.returnBigX(),collision.returnBigY() - 45);
            MExxx.add(ME);
            }
            else
            {
            gexp = new GreensExplosion(collision.returnBigX(),collision.returnBigY() - 35);
            gexpXXX.add(gexp); 
            }
            collision.setBigX(0);
            collision.setBigY(0);
            

        }
          
    
    }
    ///////end explosions methods
    



}

