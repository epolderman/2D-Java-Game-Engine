/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Mechanics;

import GameState.MULTIPLAYERSTATE;
import ObjectBOSS.ExplosionMissile;
import ObjectBOSS.HomingMissile;
import ObjectBOSS.PlasmaBalls;
import ObjectBOSS.Sheild;
import ObjectBOSS.Vicious;
import Units.GunEnemy;
import Units.GunGreenShot;
import Units.MainMissile;
import Units.SheildEnemy;
import Units.PlasmaBall;
import Units.MultiPlayer;
import Units.BluntForceEnemy;
import Packets.PacketCBig;
import Packets.PacketCGGS;
import Packets.PacketCGreen;
import Packets.PacketCMissile;
import Packets.PacketCRed;
import Packets.PacketCSmall;
import Packets.PacketHealth;
import Units.BigMeteor;
import Units.SmallMeteor;
import Units.SwordFish;
import java.util.LinkedList;

/**
 *
 * @author eze
 */

////Collision detection class.-> Guide
////private instance variables
////constructor for single player
////constructor for multiplayer
////setters/getters
////multiplayer methods
////single player methods

    

public class Collision 
{   
    ///instance variables for explosions generating mechanics   
    private double MissileX;
    private double MissileY;
    private double MissileX2;
    private double MissileY2;
    private double MissileX2H;
    private double MissileY2H;
    private double BigX;
    private double BigY;
    private double SmallX;
    private double SmallY;
    private double pinkx;
    private double pinky;
    private double ggsX;
    private double ggsY;
    private double ZX;
    private double ZY;
    private double pimpX;
    private double pimpY;
    private String level;
    private SpaceScore SS;
    private int bigMdamageSF;
    private int smallMdamageSF;
    private int plasmaBdamageSF;
    private int homingBdamageSF;
    private int GGSdamageSF;
    private int greenSF;
    private int redSF;
    private int pinkSF;
    private int RMtoVic;
    private int PCtoVic;
    private int PBtoVic;
    private int RMtoPB;
    
    ////constructor in multiplayer
    private MULTIPLAYERSTATE MPS;
    
    
    public Collision(String level, SpaceScore SS)
    {   
        this.ZX = 0;
        this.ZY = 0;
        this.ggsX = 0;
        this.ggsY = 0;
        this.pinkx = 0;
        this.pinky = 0;
        this.SmallY = 0;
        this.SmallX = 0;
        this.BigX = 0;
        this.BigY = 0;
        this.MissileX = 0;
        this.MissileY = 0;
        this.MissileX2 = 0;
        this.MissileY2 = 0;
        this.MissileX2H = 0;
        this.MissileY2H = 0;
        this.pimpX = 0;
        this.pimpY = 0;
        this.level = level;
        this.SS = SS;
        initialize();
        ///for testing purposes
        System.out.println("Collision Difficulty Level is " + level);
        System.out.println("Big Meteor HitPoints = " + bigMdamageSF);
        System.out.println("Small Meteor HitPoints = " + smallMdamageSF);
        System.out.println("Homing Missile HitPoints = " + homingBdamageSF);
        System.out.println("RMtoPB HitPoints = " + RMtoPB);

    }
    //double bigx, bigy is the collosio for red enemy
    ///smallx and small y for green enem explosion
    ///pimpX and pimpY are for generating gren gun shot explosions
    ///missilex2 and millsy2 are for missile vs big meteor
    //ZX and ZY are use for missile explosions
    
    /////multiplayer state constuctor
    public Collision (double XZ, double ZY, double BigX, double BigY,double SmallY, 
                      double SmallX,double pimpX, double pimpY, 
                      MULTIPLAYERSTATE MPS, double MissileX2, double MissileY2,
                      double pinkx, double pinky)
    {   
        this.ZX = XZ;
        this.ZY = ZY;
        this.pinkx = pinkx;
        this.pinky = pinky;
        this.MissileX2 = MissileX2;
        this.MissileY2 = MissileY2;
        this.MPS = MPS;
        this.BigX = BigX;
        this.BigY = BigY;
        this.SmallX = SmallX;
        this.SmallY = SmallY;
        this.pimpX = pimpX;
        this.pimpY = pimpY;
        System.out.println("Collision: System Up and Running");
    
    }
    
    
    
    ///for the difficulty/spacescore
public void initialize()
{
        switch (level) {
            case "Easy":
                bigMdamageSF = 50;
                smallMdamageSF = 25;
                plasmaBdamageSF = 20;
                GGSdamageSF = 20;
                greenSF = 25;
                redSF = 25;
                pinkSF= 25;
                RMtoVic = 10;
                PCtoVic = 40;
                PBtoVic = 20;
                RMtoPB = 5;
                homingBdamageSF = 50;
                break;
            case "Medium":
                bigMdamageSF = 60;
                smallMdamageSF = 30;
                plasmaBdamageSF = 25;
                GGSdamageSF = 25;
                greenSF = 30;
                redSF = 30;
                pinkSF= 30;
                RMtoVic = 10;
                PCtoVic = 30;
                PBtoVic = 25;
                RMtoPB = 3;
                homingBdamageSF = 55;
                break;
            case "Hardened":
                bigMdamageSF = 70;
                smallMdamageSF = 40;
                plasmaBdamageSF = 25;
                GGSdamageSF = 30;
                greenSF = 30;
                redSF = 30;
                pinkSF= 30;
                RMtoVic = 15;
                PCtoVic = 30;
                PBtoVic = 25;
                RMtoPB = 2;
                homingBdamageSF = 60;
                break;
        }
    

}
////begin setters and getters
////for the missile collision detection
 public void setPIMPX(int x)
 {  
     this.pimpX = x;
 }
 public void setPIMPY(int y)
 {
     this.pimpY = y;
 }
  public double returnPIMPX()
 {
     return pimpX;
 }
 public double returnPIMPY()
 {
     return pimpY;
 }   
 public void setMissileX(int x)
 {   
     this.MissileX = x;
 }
 public void setMissileY(int y)
 {
     this.MissileY = y;
 }
 public void setMissileX2(int x)
 {
     
     this.MissileX2 = x;

 }
 public void setMissileY2(int y)
 {
 
     this.MissileY2 = y;
 }
  public double returnMissileX2()
 {
     return MissileX2;
 }
 public double returnMissileY2()
 {
     return MissileY2;
 }
 //
 public void setMissileX2H(int x)
 {
     
     this.MissileX2H = x;

 }
 public void setMissileY2H(int y)
 {
 
     this.MissileY2H = y;
 }
  public double returnMissileX2H()
 {
     return MissileX2H;
 }
 public double returnMissileY2H()
 {
     return MissileY2H;
 }
 public double returnMissileX()
 {
     return MissileX;
 }
 public double returnMissileY()
 {
     return MissileY;
 }
 public void setBigX(int x)
 {
     
     this.BigX = x;
 }
 public void setBigY(int y)
 {
 
     this.BigY = y;
 }
 public double returnBigX()
 {
     return BigX;
 }
 public double returnBigY()
 {
     return BigY;
 }
 public void setSMALLX(int x)
 {
     
     this.SmallX = x;
 }
 public void setSMALLY(int y)
 {
 
     this.SmallY = y;
 }
 public double returnSMALLX()
 {
     return SmallX;
 }
 public double returnSMALLY()
 {
     return SmallY;
 }
 public void setpinkX(int x)
 {
     
     this.pinkx = x;
 }
 public void setpinkY(int y)
 {
 
     this.pinky = y;
 }
 public double returnpinkX()
 {
     return pinkx;
 }
 public double returnpinkY()
 {
     return pinky;
 }
 public void setggsX(int x)
 {
     
     this.ggsX = x;
 }
 public void setggsY(int y)
 {
 
     this.ggsY = y;
 }
 public double returnggsX()
 {
     return ggsX;
 }
 public double returnggsY()
 {
     return ggsY;
 }
 public void setZX(int x)
 {
     
     this.ZX = x;
 }
 public void setZY(int y)
 {
 
     this.ZY = y;
 }
 public double returnZX()
 {
     return ZX;
 }
 public double returnZY()
 {
     return ZY;
 }
 ////end setters and getters

    
 
    ///Multiplayer Collision Detection Methods/**************************************************
 
 
 
 ///player detection vs all enemies
 public void McheckPlayervsEverything(MultiPlayer PM, 
                                      LinkedList<BluntForceEnemy> BFE, 
                                      LinkedList<GunGreenShot> GGS,  
                                      LinkedList<GunEnemy> GE,
                                      LinkedList<BigMeteor> BM,
                                      LinkedList<SmallMeteor> SM)
 {
 
                if(PM != null)
                {
                    if(BFE.size() >= 0)
                    {
                    ///blunt force enemy hit
                for(int i = 0;i < BFE.size();i++)
                {
                    
                    
                if(PM.returnPARAMZ().intersects(BFE.get(i).returnPARAMZ()))
                        {
                            ///notch the multiplayer objects health
                            PM.notchHits(1);
                            PacketHealth packet = new PacketHealth(PM.getUsername(),PM.returnHits());
                            packet.writeData(MPS.socketClient);
                          
                          
                        }
                
                }
                    }
                
                
                if(GE.size() >= 0)
                ///Gun enemy shot
                 for(int i = 0;i < GE.size();i++)
                {
                    
                    
                if(PM.returnPARAMZ().intersects(GE.get(i).returnPARAMZ()))
                        {
                            ///notch the multiplayer objects health
                            PM.notchHits(1);
                            PacketHealth packet = new PacketHealth(PM.getUsername(),PM.returnHits());
                            packet.writeData(MPS.socketClient);
                        
                            
                        }
                
                }
                
                 
                if(GGS.size() >= 0)
                 {
                 
                 ///Green Gun Shot
                 for(int i = 0;i < GGS.size();i++)
                {
                    
                    
                if(PM.returnPARAMZ().intersects(GGS.get(i).returnPARAMZ()))
                        {
                            ///notch the multiplayer objects health
                            PM.notchHits(1);
                            PacketHealth packet = new PacketHealth(PM.getUsername(),PM.returnHits());
                            packet.writeData(MPS.socketClient);
                         
                            
                        }
                
                }
                 }
                
                ///big meteor
                 if(BM.size() >= 0)
                 {
                 
                 ///Green Gun Shot
                 for(int i = 0;i < BM.size();i++)
                {
                    
                    
                if(PM.returnPARAMZ().intersects(BM.get(i).returnPARAMZ()))
                        {
                            ///notch the multiplayer objects health
                            PM.notchHits(1);
                            PacketHealth packet = new PacketHealth(PM.getUsername(),PM.returnHits());
                            packet.writeData(MPS.socketClient);
                         
                            
                        }
                
                }
                 }
                 
                 
                 ///small meteors
                   if(SM.size() >= 0)
                 {
                 
                 ///Green Gun Shot
                 for(int i = 0;i < SM.size();i++)
                {
                    
                    
                if(PM.returnPARAMZ().intersects(SM.get(i).returnPARAMZ()))
                        {
                            ///notch the multiplayer objects health
                            PM.notchHits(1);
                            PacketHealth packet = new PacketHealth(PM.getUsername(),PM.returnHits());
                            packet.writeData(MPS.socketClient);
                         
                            
                        }
                
                }
                 }
                
                
                
                
                
                
                
                
                }
                
                
                
                
                
}
     
 
 
    /////check the red enemy and the missile
   public void McheckREDvsMIS(MainMissile mi, LinkedList<BluntForceEnemy> re, BluntForceEnemy redE)
   {
               if(mi != null && re != null)
            {
                for(int i = 0;i < re.size();i++)
                            {
                    
                                    if(mi.returnPARAMZ().intersects(re.get(i).returnPARAMZ()))
                            {       
                                    
                                    //System.out.println("Collision: X Hit at: " + this.BigX);
                                    //System.out.println("Collision: Y Hit at: " + this.BigY);
                                    this.setBigX((int)re.get(i).returnX());
                                    this.setBigY((int)re.get(i).returnY());
                                    redE = re.get(i);
                                    PacketCRed packet = new PacketCRed(redE.returnID());
                                    packet.writeData(MPS.socketClient);
                                    ///look into this
                                    re.remove(i);
                                   
                                    
                                    
                            }
                                   
                                    
                                    
                            }
   
  
            }
                         
   }
   
    ///green enemy collosion detection
    public void McheckGREENvsMIS(MainMissile mi, LinkedList<GunEnemy> greenEnemies, GunEnemy greenEnemy)
   {
                if(mi != null && greenEnemies != null)
            {
                for(int i = 0;i < greenEnemies.size();i++)
                            {
                    
                                    if(mi.returnPARAMZ().intersects(greenEnemies.get(i).returnPARAMZ()))
                            {       
                                    
                                    greenEnemy = greenEnemies.get(i);
                                    if(greenEnemy.checkDeath() == true)
                                    {
                                    this.setSMALLX((int)greenEnemy.getX());
                                    this.setSMALLY((int)greenEnemy.getY());
                                    PacketCGreen packet = new PacketCGreen(greenEnemy.returnID());
                                    packet.writeData(MPS.socketClient);
                                    greenEnemies.remove(i);
                                    }
                                    
                                    
                            }
                                   
                                    
                                    
                            }
   
   
            }
                         
   }
    
    ///green gun shot vs missile
    public void McheckGGSvsMIS(MainMissile mi, LinkedList<GunGreenShot> ggs, GunGreenShot ggShot)
   {
                         if(mi != null && ggs != null)
            {
                                    for(int i = 0;i < ggs.size();i++)
                            {
                    
                                    if(mi.returnPARAMZ().intersects(ggs.get(i).returnPARAMZ()))
                            {       
                                    
                                    this.setPIMPX((int)ggs.get(i).returnX());
                                    this.setPIMPY((int)ggs.get(i).returnY());
                                    ggShot = ggs.get(i);
                                    PacketCGGS packet = new PacketCGGS(ggShot.returnID());
                                    packet.writeData(MPS.socketClient);  
                                    ggs.remove(i);
                                   
                                    
                            }
                                   
                                    
                                    
                            }
   
   
            }
                         
   }
   ///small missile vs big meteor
   /////removes the big meteors with laser hits
     public void McheckMvsBM(MainMissile mi, LinkedList<BigMeteor> big, BigMeteor metz)
    {
            
            if( mi != null && big != null)
            {
                         
                        
                            for(int i = 0;i < big.size();i++)
                            {
                    
                                    if(mi.returnPARAMZ().intersects(big.get(i).returnPARAMZ()))
                            {       
                                    
                                    this.setMissileX2((int)mi.getX());
                                    this.setMissileY2((int)mi.getY());
                                    metz = big.get(i);
                                    PacketCBig packet = new PacketCBig(metz.returnID());
                                    packet.writeData(MPS.socketClient);  
                                    big.remove(i);
                                    
                            }
                                   
                                    
                                    
                            }
                            
                            
                            
            }
                                 
    
    }
     //missile vs small meteors
     
       public void McheckMvsSM(MainMissile mi, LinkedList<SmallMeteor> sbig, SmallMeteor smetz)
    {
            
            if( mi != null && sbig != null)
            {
                         
                        
                            for(int i = 0;i < sbig.size();i++)
                            {
                    
                                    if(mi.returnPARAMZ().intersects(sbig.get(i).returnPARAMZ()))
                            {       
                                    
                                    this.setpinkX((int)mi.getX());
                                    this.setpinkY((int)mi.getY());
                                    smetz = sbig.get(i);
                                    PacketCSmall packet = new PacketCSmall(smetz.returnID());
                                    packet.writeData(MPS.socketClient);  
                                    sbig.remove(i);
                                    
                            }
                                   
                                    
                                    
                            }
                            
                            
                            
            }
                                 
    
    }
       ////vicious collision detection and missile
        public void McheckViciousVSmi(Vicious VS, GunEnemy greenEnemy, PlasmaBalls pb,  LinkedList<MainMissile> Missiles, MainMissile Missile)
    {
         
            
            if(Missiles != null)
            {
            for(int i = 0;i < Missiles.size();i++)
            {       
                    if(VS != null && Missiles.size() > 0)
                    {
                    ///check collisions vs vicious
                    if(VS.returnPARAMZ().intersects(Missiles.get(i).returnPARAMZ()))
                    {
                        //RM.remove(i);
                        
                        Missile = Missiles.get(i);
                        this.setZX((int)Missile.getX());
                        this.setZY((int)Missile.getY());
                        PacketCMissile packet = new PacketCMissile(Missile.returnID());
                        packet.writeData(MPS.socketClient);                          
                        Missiles.remove(i);
                        System.out.println("VS hitpt" + VS.returnHits());
                        VS.checkHITS(5);
                    }
                    }
                    
                    
                    
                    if(greenEnemy != null && Missiles.size() > 0)
                    {
                       ///check collisions vs green enemy
                     if(greenEnemy.returnPARAMZ().intersects(Missiles.get(i).returnPARAMZ()))
                    {
                        
                        //System.out.println("GunEnemy Hitpoints: " + greenEnemy.returnHP());
                        greenEnemy.setHITS(5);
                        Missile = Missiles.get(i);
                        this.setZX((int)Missile.getX());
                        this.setZY((int)Missile.getY());
                        PacketCMissile packet = new PacketCMissile(Missile.returnID());
                        packet.writeData(MPS.socketClient);          
                        Missiles.remove(i);
                         
                        
  
                    }
                    }
                    
                    
                    if(pb != null && Missiles.size() > 0)
                    {
                       ///check collisions vs green enemy
                     if(pb.returnPARAMZ().intersects(Missiles.get(i).returnPARAMZ()))
                    {
                                               
                        Missile = Missiles.get(i);
                        this.setZX((int)Missile.getX());
                        this.setZY((int)Missile.getY());
                        PacketCMissile packet = new PacketCMissile(Missile.returnID());
                        packet.writeData(MPS.socketClient);          
                        Missiles.remove(i);
                         
                        
  
                    }
                    }
                    
                    
                 
                    
                    
            }
            }
            
        

    }
        
    /*********************************SINGLE PLAYER COLLISION DETECTION*************/
    ///Section is reserved for SwordFish(Main Player) collision detection methods
 
    /////checks collision detection when the swordfish hits a big metoer
    public void checkBIG(SwordFish sf, LinkedList<BigMeteor> M)
    {
        if(sf != null && M != null)
            
        {
            for(int i = 0;i < M.size();i++)
                {
           
                    if(sf.returnPARAMZ().intersects(M.get(i).returnPARAMZ()))
                    {
                        M.remove(i);
                        SS.Notch(10);
                        ///50 hitpoints subtracted
                        sf.checkHITS(bigMdamageSF);
                    }
                }
        }               
    
    }
    
   ////checks for collision detection when a meteors hits the swordfish
    public void checkSMALL(SwordFish sf, LinkedList<SmallMeteor> SM)
    {
        if( sf != null && SM != null)
        {
            for(int i = 0;i < SM.size();i++)
            {
                    if(sf.returnPARAMZ().intersects(SM.get(i).returnPARAMZ()))
                    {
                        SM.remove(i);
                          SS.Notch(5);
                        ///small metoer does 5 damage to swordfish
                        sf.checkHITS(smallMdamageSF);
                    }
            }
                        
        }
    }
    ///collision detection swordfish vs plasmaballs and homing missiles
    public void checkSFvsPlasmaB(SwordFish sf, LinkedList<PlasmaBalls> PB, LinkedList<HomingMissile> HM)
    {
        if( sf != null && PB != null)
        {
            for(int i = 0;i < PB.size();i++)
            {
                    if(sf.returnPARAMZ().intersects(PB.get(i).returnPARAMZ()))
                    {
                        PB.remove(i);
                          SS.Notch(5);
                        ///plasma balls vs SF
                        sf.checkHITS(plasmaBdamageSF);
                    }
            }
                        
        }
          if( sf != null && HM != null)
        {
            for(int i = 0;i < HM.size();i++)
            {
                    if(sf.returnPARAMZ().intersects(HM.get(i).returnPARAMZ()))
                    {
                        HM.remove(i);
                        SS.Notch(5);
                        ///Homing Missile vs SF
                        sf.checkHITS(homingBdamageSF);
                    }
            }
                        
        }
        
    }
   
    
    public void checkSFvsALLSHIPS(SwordFish sf, LinkedList<GunGreenShot> GGS,LinkedList<GunEnemy> GE,LinkedList<BluntForceEnemy> RE,
            LinkedList<SheildEnemy> PE)
    {
        if( sf != null && GGS != null)
        {
            for(int i = 0;i < GGS.size();i++)
            {
                    if(sf.returnPARAMZ().intersects(GGS.get(i).returnPARAMZ()))
                    {
                        GGS.remove(i);
                        SS.Notch(5);
                        ///GGS hit damage against SF
                        sf.checkHITS(GGSdamageSF);
                    }
            }
                        
        }
        
        if( sf != null && GE != null)
        {
            for(int i = 0;i < GE.size();i++)
            {
                    if(sf.returnPARAMZ().intersects(GE.get(i).returnPARAMZ()))
                    {
                        GE.remove(i);
                        SS.Notch(5);
                        ///Green Enemy damage against SF
                        sf.checkHITS(greenSF);
                    }
            }
                        
        }
        if( sf != null && RE != null)
        {
            for(int i = 0;i < RE.size();i++)
            {
                    if(sf.returnPARAMZ().intersects(RE.get(i).returnPARAMZ()))
                    {
                        RE.remove(i);
                        SS.Notch(5);
                        ///red enemy hit damage against SF
                        sf.checkHITS(redSF);
                    }
            }
                        
        }
        
        if( sf != null && PE != null)
        {
            for(int i = 0;i < PE.size();i++)
            {
                    if(sf.returnPARAMZ().intersects(PE.get(i).returnPARAMZ()))
                    {   
                        
                        PE.remove(i);
                        SS.Notch(5);
                        ///pink enemy hit damage against SF
                        sf.checkHITS(pinkSF);
                    }
            }
                        
        }
        
        
        
    }
    
    ///end swordfish collision
    
    ///begin vicious collision checking
    public void checkViciousVSSF(Vicious VS, Sheild sh, LinkedList<MainMissile> RM, LinkedList<PlasmaBall> PC,LinkedList<PlasmaBalls> PB)
    {
        if( VS != null)
        {   
            
            if(RM != null)
            {
            for(int i = 0;i < RM.size();i++)
            {
                    if(VS.returnPARAMZ().intersects(RM.get(i).returnPARAMZ()))
                    {
                        RM.remove(i);
                        
                        
                        if(sh.destroySHEILD() == true)
                        {
                            System.out.println("Collision + State: Boss taking no damage");
                        }
                        else
                        {
                        VS.checkHITS(RMtoVic);
                        }
                    }
            }
            }
            
            if(PC != null)
            {
            for(int i = 0;i < PC.size();i++)
            {
                    if(VS.returnPARAMZ().intersects(PC.get(i).returnPARAMZ()))
                    {
                        PC.remove(i);
                        if(sh.destroySHEILD() == true)
                        {
                            System.out.println("Collision + State: Boss taking no damage");
                        }
                        else
                        {
                        VS.checkHITS(PCtoVic);
                        }
                    }
            }
            }
            
            
            if(PB != null)
            {
            for(int i = 0;i < PB.size();i++)
            {
                    if(VS.returnPARAMZ().intersects(PB.get(i).returnPARAMZ()))
                    {
                        PB.remove(i);
                        
                        VS.checkHITS(PBtoVic);
                    }
            }
            }
            
            if(PB != null)
            {
            for(int i = 0;i < PB.size();i++)
            {
                    if(sh.returnPARAMZ().intersects(PB.get(i).returnPARAMZ()))
                    {
                        PB.remove(i);
                        
                    }
            }
            }
            
            if(PC != null)
            {
            for(int i = 0;i < PC.size();i++)
            {
                    if(sh.returnPARAMZ().intersects(PC.get(i).returnPARAMZ()))
                    {
                        PC.remove(i);
                        
                    }
            }
            }
            
             if(RM != null)
            {
            for(int i = 0;i < RM.size();i++)
            {
                    if(sh.returnPARAMZ().intersects(RM.get(i).returnPARAMZ()))
                    {
                        RM.remove(i);
                        
                    }
            }
            }
            
            
            
                        
        }
    
    }
    
    
    
    /////removes the small meteors from the linked list when missiles hit
    ////NOT USING THIS METHOD
    public int checkMISSILE(MainMissile small, LinkedList<SmallMeteor> mis)
    {
            if(small != null && mis != null)
            {
                    for(int i = 0;i < mis.size();i++)
                     {
                    
                           if(small.returnPARAMZ().intersects(mis.get(i).returnPARAMZ()))
                                {
                        
                                    return 10;
                        
                    
                                }
                    }
                        return 1;
            
            }           
                        return 0;
    }
    /////////removes  the small meteors from the linked list with the small missiles
    public void checkBOSS(MainMissile xx, LinkedList<SmallMeteor> yy)
    {
            
            if( xx != null && yy != null)
            {
                         
                        
                            for(int i = 0;i < yy.size();i++)
                            {
                                    
                                    
                                    if(xx.returnPARAMZ().intersects(yy.get(i).returnPARAMZ()))
                            {   
                                    this.setSMALLX((int)xx.getX());
                                    this.setSMALLY((int)xx.getY());
                                    SS.Notch(5);
                                    yy.remove(i);
                            }
                                   
                                    
                                    
                            }
                            
                            
                            
            }
    
            }
    public void checkPLASMABALLS(PlasmaBalls pb,LinkedList<MainMissile> mis)
    {
        if(mis != null && pb != null)
        {
            for(int i = 0;i < mis.size();i++)
            {
                if(pb.returnPARAMZ().intersects(mis.get(i).returnPARAMZ()))
                            {   
                                    this.setPIMPX((int)pb.returnX());
                                    this.setPIMPY((int)pb.returnY());
                                    mis.remove(i);
                                    SS.Notch(5);
                                    pb.checkHITS(RMtoPB);
                                    
                            }
            
            }
        
        
        }
    }
    
    ////removes the small meteors from the linked list using the plasma cannon
    public void checkBOSS2(PlasmaBall PC, LinkedList<SmallMeteor> yy)
    {
            
            if( PC != null && yy != null)
            {
                         
                        
                            for(int i = 0;i < yy.size();i++)
                            {
                                    
                                    if(PC.returnPARAMZ().intersects(yy.get(i).returnPARAMZ()))
                            {   
                                    SS.Notch(5);
                                    yy.remove(i);
                            }
                                   
                                    
                                    
                            }
                            
                            
                            
            }
    
            }
    ////plasma cannon vs plasma balls collision detection
     public void checkPlasmaCvsPlasmaB(PlasmaBall PC, LinkedList<PlasmaBalls> yy)
    {
            
            if( PC != null && yy != null)
            {
                         
                        
                            for(int i = 0;i < yy.size();i++)
                            {
                                    
                                    if(PC.returnPARAMZ().intersects(yy.get(i).returnPARAMZ()))
                            {   
                                    SS.Notch(5);
                                    yy.remove(i);
                            }
                                   
                                    
                                    
                            }
                            
                            
                            
            }
    
            }
    
    /////plasma cannon vs boss homing missiles
    public void checkPLASMAvsHM(PlasmaBall PC, LinkedList<HomingMissile> HM)
    {
            
            if( PC != null && HM != null)
            {
                         
                        
                            for(int i = 0;i < HM.size();i++)
                            {
                                    
                                    if(PC.returnPARAMZ().intersects(HM.get(i).returnPARAMZ()))
                            {   SS.Notch(5);
                                    this.setBigX((int)PC.getX());
                                    this.setBigY((int)PC.getY());
                                    HM.remove(i);
                            }
                                   
                                    
                                    
                            }
                            
                            
                            
            }
    
            }
    
     public void checkPLASMAvsEM(PlasmaBall PC, LinkedList<ExplosionMissile> EM)
    {
            
            if( PC != null && EM != null)
            {
                         
                        
                            for(int i = 0;i < EM.size();i++)
                            {
                                    
                                    if(PC.returnPARAMZ().intersects(EM.get(i).returnPARAMZ()))
                            {   SS.Notch(5);
                                    this.setBigX((int)PC.getX());
                                    this.setBigY((int)PC.getY());
                                    EM.remove(i);
                            }
                                   
                                    
                                    
                            }
                            
                            
                            
            }
    
            }
    
    
    /////removes the big meteors with the plasma cannon
     public void checkPLASMABIG(PlasmaBall PC, LinkedList<BigMeteor> big)
    {
            
            if( PC != null && big != null)
            {
                         
                        
                            for(int i = 0;i < big.size();i++)
                            {
                    
                                    if(PC.returnPARAMZ().intersects(big.get(i).returnPARAMZ()))
                            {       
                                    SS.Notch(10);
                                    this.setBigX((int)PC.getX());
                                    this.setBigY((int)PC.getY());
                                    big.remove(i);
                                    
                            }
                                   
                                    
                                    
                            }
                            
                            
                            
            }
                                 
    
    }
     
     ///for sheild against small missiles collision
    public void checkSHEILD(Sheild sh, LinkedList<MainMissile> mx)
    {
        if(sh != null && mx != null)
        {
                for(int i = 0;i < mx.size();i++)
                {
                     if(sh.returnPARAMZ().intersects(mx.get(i).returnPARAMZ()))
                            {       
                                    
                                    this.setMissileX((int)sh.getX());
                                    this.setMissileY((int)sh.getY());
                                    mx.remove(i);
                                    
                            }
                }        
        }

    }
      ///for sheild against small missiles collision
    public void checkExplosionMissile(ExplosionMissile sh, LinkedList<MainMissile> mx)
    {
        if(sh != null && mx != null)
        {
                for(int i = 0;i < mx.size();i++)
                {
                     if(sh.returnPARAMZ().intersects(mx.get(i).returnPARAMZ()))
                            {       
                                    
                                    this.setMissileX2((int)sh.returnX());
                                    this.setMissileY2((int)sh.returnY());
                                    mx.remove(i);
                                    
                            }
                }        
        }

    }
     //////removes the missiles from the linked list againt he big meteors
   public void checkSMALLBIG(BigMeteor met, LinkedList<MainMissile> mis)
    {
            
            if( met != null && mis != null)
            {
                         
                        
                            for(int i = 0;i < mis.size();i++)
                            {
                    
                                    if(met.returnPARAMZ().intersects(mis.get(i).returnPARAMZ()))
                            {       
                                    
                                    this.setMissileX((int)met.getX());
                                    this.setMissileY((int)met.getY());
                                    mis.remove(i);
                                    
                            }
                                   
                                    
                                    
                            }
                            
                     
                            
            }
                                 
       
            
    } 
   ////pink enemy and missile
   public void checkPINKvsMIS(SheildEnemy pe,LinkedList<MainMissile> mis)
   {
            if(pe != null && mis != null)
            {
                for(int i = 0;i < mis.size();i++)
                            {
                    
                                    if(pe.returnPARAMZ().intersects(mis.get(i).returnPARAMZ()))
                            {       
                                    
                                    this.setMissileX((int)pe.getX());
                                    this.setMissileY((int)pe.getY());
                                    mis.remove(i);
                                    
                            }
                                   
                                    
                                    
                            }
            
            
            
            }
 
   }
   ////for regular missile vs homing missiles
   ////RETURN RIGHT HERE
     public void checkHOMINGvsREG(HomingMissile pe,LinkedList<MainMissile> mis)
   {
            if(pe != null && mis != null)
            {
                for(int i = 0;i < mis.size();i++)
                            {
                    
                                    if(pe.returnPARAMZ().intersects(mis.get(i).returnPARAMZ()))
                            {       
                                    
                                    this.setMissileX2H((int)pe.returnX());
                                    this.setMissileY2H((int)pe.returnY());
                                    mis.remove(i);
                                    
                            }
                                   
                                    
                                    
                            }
            
            
            
            }
 
   }
   ////pink enemy and plasma cannon
   public void checkPINKvsPLASMA(PlasmaBall PC,LinkedList<SheildEnemy> pe)
   {
            if(pe != null && PC != null)
            {
                for(int i = 0;i < pe.size();i++)
                            {
                    
                                    if(PC.returnPARAMZ().intersects(pe.get(i).returnPARAMZ()))
                            {       
                                    
                                    this.setpinkX((int)pe.get(i).getX());
                                    this.setpinkY((int)pe.get(i).getY());
                                    pe.remove(i);
                                    SS.Notch(5);
                                    
                            }
                                   
                                    
                                    
                            }
            
            
            
            }
   }
   /////check green vs plasma cannon 
   public void checkGREENvsPLASMA(PlasmaBall PC,LinkedList<GunEnemy> ge)
   {
            if(ge != null && PC != null)
            {
                for(int i = 0;i < ge.size();i++)
                            {
                    
                                    if(PC.returnPARAMZ().intersects(ge.get(i).returnPARAMZ()))
                            {       
                                    
                                    this.setZX((int)ge.get(i).getX());
                                    this.setZY((int)ge.get(i).getY());
                                    ge.remove(i);
                                    SS.Notch(5);
                                    
                            }
                                   
                                    
                                    
                            }
            
            
            
            }
   }
   ////check green enemy and missile
   public void checkGREENvsMIS(MainMissile mi, LinkedList<GunEnemy> ge)
   {
                         if(mi != null && ge != null)
            {
                for(int i = 0;i < ge.size();i++)
                            {
                    
                                    if(mi.returnPARAMZ().intersects(ge.get(i).returnPARAMZ()))
                            {       
                                    
                                    this.setSMALLX((int)ge.get(i).getX());
                                    this.setSMALLY((int)ge.get(i).getY());
                                    ge.remove(i);
                                    SS.Notch(5);
                                    
                            }
                                   
                                    
                                    
                            }
   
   
            }
                         
   }
   ///check red enemy vs plasma cannon
    /////check the red enemy and the missile
   public void checkREDvsPLASMA(PlasmaBall mi, LinkedList<BluntForceEnemy> re)
   {
                         if(mi != null && re != null)
            {
                for(int i = 0;i < re.size();i++)
                            {
                    
                                    if(mi.returnPARAMZ().intersects(re.get(i).returnPARAMZ()))
                            {       
                                    
                                    this.setBigX((int)re.get(i).returnX());
                                    this.setBigY((int)re.get(i).returnY());
                                    re.remove(i);
                                    SS.Notch(5);
                                    
                            }
                                   
                                    
                                    
                            }
   
   
            }
                         
   }
   
   
   
   /////check the red enemy and the missile
   public void checkREDvsMIS(MainMissile mi, LinkedList<BluntForceEnemy> re)
   {
                         if(mi != null && re != null)
            {
                for(int i = 0;i < re.size();i++)
                            {
                    
                                    if(mi.returnPARAMZ().intersects(re.get(i).returnPARAMZ()))
                            {       
                                    
                                    this.setBigX((int)re.get(i).returnX());
                                    this.setBigY((int)re.get(i).returnY());
                                    re.remove(i);
                                    SS.Notch(5);
                                    
                            }
                                   
                                    
                                    
                            }
   
   
            }
                         
   }
   /////check the green enemies gun shot against main character missile
   public void checkGGSvsMIS(MainMissile mi, LinkedList<GunGreenShot> ggs)
   {
                         if(mi != null && ggs != null)
            {
                                    for(int i = 0;i < ggs.size();i++)
                            {
                    
                                    if(mi.returnPARAMZ().intersects(ggs.get(i).returnPARAMZ()))
                            {       
                                    
                                    this.setggsX((int)ggs.get(i).returnX());
                                    this.setggsY((int)ggs.get(i).returnY());
                                    ggs.remove(i);
                                    SS.Notch(5);
                                    
                            }
                                   
                                    
                                    
                            }
   
   
            }
                         
   }
   
   
}
