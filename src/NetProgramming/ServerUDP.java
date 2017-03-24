/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NetProgramming;

import GameState.MULTIPLAYERSTATE;
import Main.GamePanel;
import ObjectBOSS.PlasmaBalls;
import ObjectBOSS.Vicious;
import Units.GunEnemy;
import Units.GunGreenShot;
import Units.MainMissile;
import Units.MultiPlayer;
import Units.BluntForceEnemy;
import Units.SmallMeteor;
import Packets.Packet;
import Packets.Packet.PacketTypes;
import Packets.PacketBigM;
import Packets.PacketBoss;
import Packets.PacketCBig;
import Packets.PacketCGGS;
import Packets.PacketCGreen;
import Packets.PacketCMissile;
import Packets.PacketCRed;
import Packets.PacketCSmall;
import Packets.PacketDisconnect;
import Packets.PacketGGS;
import Packets.PacketGreen;
import Packets.PacketHealth;
import Packets.PacketLaser;
import Packets.PacketLogin;
import Packets.PacketMove;
import Packets.PacketPB;
import Packets.PacketRed;
import Packets.PacketSmallM;
import Units.BigMeteor;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author eze
 */
public class ServerUDP extends Thread
{
   
    ///the socket we are going to allow connection to
    private DatagramSocket socket;
    ///need access to gamepanel for user input
    private GamePanel yahs;
    ///array list of players connected to the server
    private List<MultiPlayer> connectedPlayers = new ArrayList<>();
    ///need the specific state for allowing players onto the level
    ///think of this as a level
    private MULTIPLAYERSTATE MPS;
    
    ///boss
    private Vicious vicious;
    private LinkedList<Vicious> vics = new LinkedList<>();
   
    ///data structures for all objects except main PLAYER//////////////////
    
    ///missiles
    private LinkedList<MainMissile> ms = new LinkedList<>();
    private MainMissile mx;
    //green enemy
    private GunEnemy greenEnemy;
    private LinkedList<GunEnemy> greenEnemies = new LinkedList<>();
    //red enemy
    private BluntForceEnemy redEnemy;
    private LinkedList<BluntForceEnemy> redEnemies = new LinkedList<>();
    //green gun shot
    private GunGreenShot ggShot;
    private LinkedList<GunGreenShot> ggShots = new LinkedList<>();
    ///meteors
    private SmallMeteor smallM;
    private LinkedList<SmallMeteor> smallMs = new LinkedList<>();
    private BigMeteor bigM;
    private LinkedList<BigMeteor> bigMs = new LinkedList<>();
    
    ///plasmaballs
    private PlasmaBalls pb;
    private LinkedList<PlasmaBalls> pbS = new LinkedList<>();
    
    ///end data structures
    
    
    ///constructor for the Server
    public ServerUDP(GamePanel yahs, MULTIPLAYERSTATE MPS)
    {   
        ///initiliaze everything
        this.MPS = MPS;
        this.yahs = yahs;
        try
        {   ///
            this.socket  = new DatagramSocket(1331);
            
        }
        catch(SocketException e)
        {
            e.printStackTrace();
        
        }
        
        ///for debugging
        System.out.println("Server: Up and Running...");
        
        
       

    
    }
    
    //method that runs to allow streams of data to filter through
    ///listen to packets coming in 
    public void run()
    {  
        ///keeps running for accepting input through the udp socket
        while(true)
        {       
            
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);
                
            try 
            {
             socket.receive(packet);
             
            } catch (IOException e) 
            {
                e.printStackTrace();
            }
            
            this.parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
            
            
            
        }
    
    }
    
    ///main method that is used to take in packets and use them accordingly
    public void parsePacket(byte[] data, InetAddress address, int port)
    {   
        
        String message = new String(data).trim();
        PacketTypes type = Packet.lookupPacket(message.substring(0, 2));
        @SuppressWarnings("UnusedAssignment")
        Packet packet = null;
        
        switch(type)
        {
            case INVALID:
                ///invalid packet info
                break;
            case LOGIN:
                ///login packet logs a user in and connected player is added to the array list
                packet = new PacketLogin(data);
                System.out.println("[" + address.getHostAddress() + ":" + port + "] "
                    + ((PacketLogin) packet).getUsername() + " has connected...");
                MultiPlayer player = new MultiPlayer(MPS, 300, 300, ((PacketLogin)packet).getUsername(),address, port);
                this.addConnection(player,((PacketLogin)packet));
                System.out.println("Server: Total Connected Players: " + connectedPlayers.size());
                break;
            case DISCONNECT:
                ///disconnect packet
                packet = new PacketDisconnect(data);
                System.out.println("[" + address.getHostAddress() + ":" + port + "] "
                    + ((PacketDisconnect) packet).getUsername() + " has disconnected...");
                this.removeConnection(((PacketDisconnect) packet));
                break;
            case MOVE:
                ///move packet that moves the players, added -> hitpoint update
                packet = new PacketMove(data);
                this.handleMove(((PacketMove)packet));
                break;
            case LASER:
                ///laser packets for moving it or creating
                packet = new PacketLaser(data);
                this.handleLaser(((PacketLaser)packet));
                //System.out.println("Server Size Red:" + redEnemies.size());
                break;
            case GREEN:
                ////moves the green enemy and creates updates the linked list
                packet = new PacketGreen(data);
                this.handleGreen(((PacketGreen)packet));
                break;
             case RED:
                 ///moves the red enemy and creates updates the linked list
                packet = new PacketRed(data);
                this.handleRed(((PacketRed)packet));
                break;
             case CRED:
                 ///red enemy: collision detection packet updates data structure in this class
                 packet = new PacketCRed(data);
                 this.updateRed(((PacketCRed)packet));
                 break;
             case CGREEN:
                 ///green enemy: collision detection packet updates data structures in this class
                 packet = new PacketCGreen(data);
                 this.updateGreen(((PacketCGreen)packet));
                 break;
             case GGS:
                 ///green gun shot moves and creates objects
                  packet = new PacketGGS(data);
                  this.handleGGS(((PacketGGS)packet));
                  break;
             case CGGS:
                 ///collision detection for green gun shot updates data structure does not send to client
                  packet = new PacketCGGS(data);
                  this.updateGGS(((PacketCGGS)packet));
                  break;
             case SMALLM:
                 ////small meteor creation and update packet/method
                  packet = new PacketSmallM(data);
                  this.handleSmallMeteor(((PacketSmallM)packet));
                  break;
             case BIGM:
                 ///big metoer creation and upate packet/method
                  packet = new PacketBigM(data);
                  this.handleBigMeteor(((PacketBigM)packet));
                  break;
             case CBIG:
                 ///collision detection update big metoer linked list
                 packet = new PacketCBig(data);
                 this.updateBig(((PacketCBig)packet));
                 break;
             case CSMALL:
                 ///collision detection for small metoer linked list
                 packet = new PacketCSmall(data);
                 this.updateSmall(((PacketCSmall)packet));
                 break;
             case BOSS:
                 ////update boss and created linked list of bosses
                 packet = new PacketBoss(data);
                 this.handleBoss(((PacketBoss)packet));
                 break;
             case PB:
                 ///update plasma balls and create linked list of objects
                 packet = new PacketPB(data);
                 this.handlePB(((PacketPB)packet));
                 break;
             case CMISSILE:
                 ///updates the data structure for missiles
                 packet = new PacketCMissile(data);
                 this.updateMissile(((PacketCMissile)packet));
                 System.out.println("Server: Size of Missile Linked List is : " + ms.size());
                 break;
             case HEALTH:
                 ///updates the health for each player
                 packet = new PacketHealth(data);
                 this.updateHealth(((PacketHealth)packet));
                 System.out.println("Server. Health Updated");
                 break;
            
        
        }  
        
        
    }
    
    
    ///*METHOD INSTRUCTIONS*///
    ////ok the handle methods for objects psuedo code look like this:
    //if link list is zero -> creat object -> add to linked list
    //else look for unique string that identifies object -> find it and update the x and y-> send to client
    //if unique string does not exist in linked list -> add to it with unique string
    
    
    ////update methods are strictly updates the data structures within the class if a collision detection was
    ////found and update it by setting to null -> then removing from linked list
    
    
    
    
    
    
    
    
    
    ///updates health of players
    private void updateHealth(PacketHealth packet)
    {
        if(getMulti(packet.getUsername()) != null)
            {
            int index = getMultiInt(packet.getUsername());
            this.connectedPlayers.get(index).setHits(packet.getHits());
            packet.writeData(this);
            }
    
    
    
    }
    private void updateMissile(PacketCMissile packet)
    {
    
    
                    for(int i = 0; i < ms.size();i++)
                    {   
                        
                       
                 
                    if(packet.getUsername().equals(ms.get(i).returnID()))
                    {   
                        
                        mx = ms.get(i);
                        mx = null;
                        ms.remove(i);
                        System.out.println("Server: Collision Missile Successful Removal");
                        
                    }
    
                    }
    
 
    
    }
    private void handlePB(PacketPB packet)
    {   
       
                if(pbS.size() == 0)
                {  
                    
                    ///link list is empty
                    System.out.println("Server: Created PB and Moving");
                    pb = new PlasmaBalls(packet.getUsername(), packet.getX(), packet.getY());
                    pbS.add(pb);
                    packet.writeData(this);
                
                }
                else
                {   //link list has more than one in it
   
   
                   for(int i = 0; i < pbS.size();i++)
                    {   
                    //System.out.println("Passed second filter");
                    if(packet.getUsername().equals(pbS.get(i).getUsername()))
                    {   
                        //System.out.println("pB X:" + pb.x);
                        //System.out.println("PB Y:" + pb.y);
                        pb = pbS.get(i);
                        pb.setX(packet.getX());
                        pb.setY(packet.getY());
                        packet.writeData(this);
                    }
                    else
                    {   //did not exist so create a x , y, and username to log into link list
                        pb = new PlasmaBalls(packet.getUsername(), packet.getX(), packet.getY());
                        pbS.add(pb);
                        packet.writeData(this);
                        System.out.println("Server: Created Vicious:" + pb.getUsername());

                    }    
     
                    }
                }
    
        
        
                    
 
    }
    private void handleBoss(PacketBoss packet)
    {   
       
                if(vics.size() == 0)
                {  
                    
                    ///link list is empty
                    System.out.println("Server: Created Vicious and Moving");
                    vicious = new Vicious(MPS, packet.getUsername(), packet.getX(), packet.getY());
                    vics.add(vicious);
                    packet.writeData(this);
                
                }
                else
                {   //link list has more than one in it
   
   
                   for(int i = 0; i < vics.size();i++)
                    {   
                    //System.out.println("Passed second filter");
                    if(packet.getUsername().equals(vics.get(i).getUsername()))
                    {   
                        //System.out.println("Moving Vicious X:" + vicious.x);
                        //System.out.println("Moving Vicious Y:" + vicious.y);
                        vicious = vics.get(i);
                        vicious.setX(packet.getX());
                        vicious.setY(packet.getY());
                        packet.writeData(this);
                    }
                    else
                    {   //did not exist so create a x , y, and username to log into link list
                        vicious = new Vicious(packet.getUsername(), packet.getX(), packet.getY());
                        vics.add(vicious);
                        packet.writeData(this);
                        System.out.println("Server: Created Vicious:" + vicious.getUsername());

                    }    
     
                    }
                }
    
        
        
                    
 
    }
    private void updateSmall(PacketCSmall packet)
    {
        
                    for(int i = 0; i < smallMs.size();i++)
                    {   
                        
                       
                 
                    if(packet.getUsername().equals(smallMs.get(i).returnID()))
                    {   
                        
                        smallM = smallMs.get(i);
                        smallM = null;
                        smallMs.remove(i);
                        System.out.println("Server: Collision Small Meteor Successful Removal");
                        
                    }
    
                    }
    
    
    }
    
    private void updateBig(PacketCBig packet)
    {
        
                    for(int i = 0; i < bigMs.size();i++)
                    {   
                        
                       
                 
                    if(packet.getUsername().equals(bigMs.get(i).returnID()))
                    {   
                        
                        bigM = bigMs.get(i);
                        bigM = null;
                        bigMs.remove(i);
                        System.out.println("Server: Collision Big Meteor Successful Removal");
                        
                    }
    
                    }
    
    
    }
    
    
    private void handleSmallMeteor(PacketSmallM packet)
    {
    
        if(packet.getY() >= 710)
                {   
                    System.out.println("Server: In Deletion Method");
                    
                    
                      for(int i = 0; i < smallMs.size();i++)
                    {   
                        
                       
                 
                    if(packet.getUsername().equals(smallMs.get(i).returnID()))
                    {   
                        System.out.println("Server: In Inner For Loop");
                        smallM = smallMs.get(i);
                        smallM = null;
                        smallMs.remove(i);
                        System.out.println("Server: Small Meteor Successful Removal");
                        
                    }
                  
                        
                      
                    
                    
                    }
                
              
                
                }
                else if(smallMs.size() == 0)
                {  
                    
                    ///link list is empty
                    System.out.println("Server: Did not find - Created Meteor and Moving");
                    smallM = new SmallMeteor(MPS, packet.getUsername(), packet.getX(), packet.getY());
                    smallMs.add(smallM);
                    packet.writeData(this);
                
                }
                else
                {   //link list has more than one in it
   
   
                   for(int i = 0; i < smallMs.size();i++)
                    {   
                    //System.out.println("Passed second filter");
                    if(packet.getUsername().equals(smallMs.get(i).returnID()))
                    {   
                        
                        smallM = smallMs.get(i);
                        smallM.setX(packet.getX());
                        smallM.setY(packet.getY());
                        packet.writeData(this);
                    }
                    else
                    {   //did not exist so create a x , y, and username to log into link list
                        smallM = new SmallMeteor(packet.getUsername(), packet.getX(), packet.getY());
                        smallMs.add(smallM);
                        packet.writeData(this);
                        System.out.println("Server: Meteor not in linkedL - Created Red:" + smallM.returnID());

                    }    
     
                    }
                }
    
        
        
        
 
    }
    private void handleBigMeteor(PacketBigM packet)
    {
    
           if(packet.getY() >= 710)
                {   
                    System.out.println("Server: In Deletion Method");
                    
                    
                      for(int i = 0; i < bigMs.size();i++)
                    {   
                        
                       
                 
                    if(packet.getUsername().equals(bigMs.get(i).returnID()))
                    {   
                        System.out.println("Server: In Inner For Loop");
                        bigM = bigMs.get(i);
                        bigM = null;
                        bigMs.remove(i);
                        System.out.println("Server: Big Meteor Successful Removal");
                        
                    }
                  
                        
                      
                    
                    
                    }
                
              
                
                }
                else if(bigMs.size() == 0)
                {  
                    
                    ///link list is empty
                    System.out.println("Server: Did not find - Created Meteor and Moving");
                    bigM = new BigMeteor(MPS, packet.getUsername(), packet.getX(), packet.getY());
                    bigMs.add(bigM);
                    packet.writeData(this);
                
                }
                else
                {   //link list has more than one in it
   
   
                   for(int i = 0; i < bigMs.size();i++)
                    {   
                    //System.out.println("Passed second filter");
                    if(packet.getUsername().equals(bigMs.get(i).returnID()))
                    {   
                        
                        bigM = bigMs.get(i);
                        bigM.setX(packet.getX());
                        bigM.setY(packet.getY());
                        packet.writeData(this);
                    }
                    else
                    {   //did not exist so create a x , y, and username to log into link list
                        bigM = new BigMeteor(packet.getUsername(), packet.getX(), packet.getY());
                        bigMs.add(bigM);
                        packet.writeData(this);
                        System.out.println("Server: Meteor not in linkedL - Created Red:" + bigM.returnID());

                    }    
     
                    }
                }
    
        
        
        

    }
    private void updateGGS(PacketCGGS packet)
    {
                
                    for(int i = 0; i < ggShots.size();i++)
                    {   
                        
                       
                 
                    if(packet.getUsername().equals(ggShots.get(i).returnID()))
                    {   
                        
                        ggShot = ggShots.get(i);
                        ggShot = null;
                        ggShots.remove(i);
                        System.out.println("Server: Collision Green Gun Shot Successful Removal");
                        
                    }
    
                    }
    
    }
    
    
     private void handleGGS(PacketGGS packet)
    {
        
                
         
                if(packet.getY() >= 710)
                {   
                    System.out.println("Server: In Deletion Method");
                    
                    
                      for(int i = 0; i < ggShots.size();i++)
                    {   
                        
                       
                 
                    if(packet.getUsername().equals(ggShots.get(i).returnID()))
                    {   
                        System.out.println("Server: In Inner For Loop");
                        ggShot = ggShots.get(i);
                        ggShot = null;
                        ggShots.remove(i);
                        System.out.println("Server: Green Gun Shot Successful Removal");
                        
                    }
                  
                        
                      
                    
                    
                    }
                
                
                
                }
                else if(ggShots.size() == 0)
                {  
                    
                    ///link list is empty
                    System.out.println("Server: Did not find - Created Missile and Moving");
                    ggShot = new GunGreenShot(packet.getUsername(), packet.getX(), packet.getY());
                    ggShots.add(ggShot);
                    packet.writeData(this);
                
                }
                else
                {   //link list has more than one in it
   
   
                   for(int i = 0; i < ggShots.size();i++)
                    {   
                    //System.out.println("Passed second filter");
                    if(packet.getUsername().equals(ggShots.get(i).returnID()))
                    {   
                        
                        ggShot = ggShots.get(i);
                        ggShot.setX(packet.getX());
                        ggShot.setY(packet.getY());
                        packet.writeData(this);
                    }
                    else
                    {   //did not exist so create a x , y, and username to log into link list
                        ggShot = new GunGreenShot(packet.getUsername(), packet.getX(), packet.getY());
                        ggShots.add(ggShot);
                        packet.writeData(this);
                        System.out.println("Server: Red Enemy not in linkedL - Created Red:" + ggShot.returnID());

                    }    
     
                    }
                }
    
        
        
        
    
    
    
    }
    
  
     private void updateGreen(PacketCGreen packet)
    {
                
                    for(int i = 0; i < greenEnemies.size();i++)
                    {   
                        
                       
                 
                    if(packet.getUsername().equals(greenEnemies.get(i).returnID()))
                    {   
                        System.out.println("ServerGreen Green Size: " + greenEnemies.size());
                        greenEnemy = greenEnemies.get(i);
                        greenEnemy = null;
                        greenEnemies.remove(i);
                        System.out.println("ServerGreen: Collision Green Enemy Successful Removal");
                        
                    }
    
                    }
    
    }
    
    
    ///for collision detection on red enemy update linked list
    private void updateRed(PacketCRed packet)
    {
                
                    for(int i = 0; i < redEnemies.size();i++)
                    {   
                        
                       
                 
                    if(packet.getUsername().equals(redEnemies.get(i).returnID()))
                    {   
                        
                        redEnemy = redEnemies.get(i);
                        redEnemy = null;
                        redEnemies.remove(i);
                        System.out.println("Server: Collision Red Enemy Successful Removal");
                        
                    }
    
                    }
    
    }
    ///handling red enemy movements
    private void handleRed(PacketRed packet)
    {
        
                
         
                if(packet.getY() >= 710 )
                {   
                    System.out.println("Server: In Deletion Method");
                    
                    
                      for(int i = 0; i < redEnemies.size();i++)
                    {   
                        
                       
                 
                    if(packet.getUsername().equals(redEnemies.get(i).returnID()))
                    {   
                        System.out.println("Server: In Inner For Loop");
                        redEnemy = redEnemies.get(i);
                        redEnemy = null;
                        redEnemies.remove(i);
                        System.out.println("Server: Red Enemy Successful Removal");
                        
                    }
                  
                        
                      
                    
                    
                    }
                
                
                
                }
                else if(redEnemies.size() == 0)
                {  
                    
                    ///link list is empty
                    System.out.println("Server: Did not find - Created Missile and Moving");
                    redEnemy = new BluntForceEnemy(packet.getUsername(), packet.getX(), packet.getY());
                    redEnemies.add(redEnemy);
                    packet.writeData(this);
                
                }
                else
                {   //link list has more than one in it
   
   
                   for(int i = 0; i < redEnemies.size();i++)
                    {   
                    //System.out.println("Passed second filter");
                    if(packet.getUsername().equals(redEnemies.get(i).returnID()))
                    {   
                        
                        //System.out.println("Server: Green: Packet Info Name:" + packet.getUsername());
                        //System.out.println("Server: Green: Link Link in Server Name:" + redEnemies.get(i).returnID());
                        //System.out.println("Server: Green: Found and Moving Missile");
                        //System.out.println("Server: Moving Red Enemy");
                        redEnemy = redEnemies.get(i);
                        redEnemy.setX(packet.getX());
                        redEnemy.setY(packet.getY());
                        packet.writeData(this);
                    }
                    else
                    {   //did not exist so create a x , y, and username to log into link list
                        redEnemy = new BluntForceEnemy(packet.getUsername(), packet.getX(), packet.getY());
                        redEnemies.add(redEnemy);
                        packet.writeData(this);
                        System.out.println("Server: Red Enemy not in linkedL - Created Red:" + redEnemy.returnID());

                    }    
     
                    }
                }
    
        
        
        
    
    
    
    }
    ///handle green enemies
    private void handleGreen(PacketGreen packet)
    {
            //handle green packets
             //System.out.println("Server: In handle green");
            
         
                if(packet.getY() >= 710 || packet.getHits() <= 0)
                {   
                    System.out.println("Server: Green: In Deletion Method");
                    
                    
                      for(int i = 0; i < greenEnemies.size();i++)
                    {   
                        
                       
                 
                    if(packet.getUsername().equals(greenEnemies.get(i).returnID()))
                    {   
                        //System.out.println("Server: In Inner For Loop");
                        greenEnemy = greenEnemies.get(i);
                        greenEnemy = null;
                        greenEnemies.remove(i);
                        System.out.println("Server: Successful Removal");
                        
                    }
                  
                        
                      
                    
                    
                    }
                
                
                
                }
                else if(greenEnemies.size() == 0)
                {  
                    
                    ///link list is empty
                    System.out.println("Server: Green :Did not find - Created Green and Moving");
                    greenEnemy = new GunEnemy(packet.getUsername(), packet.getX(), packet.getY());
                    greenEnemies.add(greenEnemy);
                    packet.writeData(this);
                
                }
                else
                {   //link list has more than one in it
   
   
                   for(int i = 0; i < greenEnemies.size();i++)
                    {   
                    //System.out.println("Passed second filter");
                    if(packet.getUsername().equals(greenEnemies.get(i).returnID()))
                    {   
                        
                        //System.out.println("Server: Green: Packet Info Name:" + packet.getUsername());
                        //System.out.println("Server: Green: Link Link in Server Name:" + greenEnemies.get(i).returnID());
                        //System.out.println("Server: Green: Found and Moving Missile");
                        
                        greenEnemy = greenEnemies.get(i);
                        greenEnemy.setX(packet.getX());
                        greenEnemy.setY(packet.getY());
                        packet.writeData(this);
                    }
                    else
                    {   //did not exist so create a x , y, and username to log into link list
                        greenEnemy = new GunEnemy(packet.getUsername(), packet.getX(), packet.getY());
                        greenEnemies.add(greenEnemy);
                        packet.writeData(this);
                        System.out.println("Server: Green :Not in link list database - Created Missile and Moving");

                    }    
     
                    }
                }
    
    
    }
    private void handleLaser(PacketLaser packet)
   {        
                if(packet.getY() <= -2)
                {   
                    
                      for(int i = 0; i < ms.size();i++)
                    {   
          
                    if(packet.getUsername().equals(ms.get(i).returnID()))
                    {   
                        //System.out.println("in inner for loop");
                        mx = ms.get(i);
                        mx = null;
                        ms.remove(i);
                        System.out.println("Server: Successful Removal");
                        
                    }
   
                    
                    }
     
                }
                else if(ms.size() == 0)
                {  
                    
                    ///link list is empty
                    System.out.println("Server: Did not find - Created Missile and Moving");
                    mx = new MainMissile(packet.getUsername(), packet.getX(), packet.getY());
                    ms.add(mx);
                    packet.writeData(this);
                
                }
                else
                {   
   
                   for(int i = 0; i < ms.size();i++)
                    {   
                    //System.out.println("Passed second filter");
                    if(packet.getUsername().equals(ms.get(i).returnID()))
                    {   
                        
                        mx = ms.get(i);
                        mx.setX(packet.getX());
                        mx.setY(packet.getY());
                        packet.writeData(this);
                    }
                    else
                    {   //did not exist so create a x , y, and username to log into link list
                        mx = new MainMissile(packet.getUsername(), packet.getX(), packet.getY());
                        ms.add(mx);
                        packet.writeData(this);
                        System.out.println("Server: Not in link list database - Created Missile and Moving");

                    }    
     
                    }
                }
              
            
            
            
            
            
            
            

               
    }
    //handles the x and y movements as well as the health
    private void handleMove(PacketMove packet)
    {   ///get index and update the characters x and y
            if(getMulti(packet.getUsername()) != null)
            {
            int index = getMultiInt(packet.getUsername());
            this.connectedPlayers.get(index).setX(packet.getX());
            this.connectedPlayers.get(index).setY(packet.getY());
         
            packet.writeData(this);
            }
    
    
    }
    ///sends data to utility methods for the server class to specific ip address and port
    public void sendData(byte[] data, InetAddress ipaddress, int port)
    {
        DatagramPacket packet = new DatagramPacket(data, data.length, ipaddress, port);
        try 
        {
          this.socket.send(packet);
        } 
        catch (IOException ex) 
        {
           ex.printStackTrace();
        }
    
    }
    ///send to all cleints/players the updates
    public void sendDataToAllClients(byte[] data)
    {
        for(MultiPlayer p: connectedPlayers)
        {
            sendData(data, p.returnIP(), p.returnPORT());
        }
    }
    
    ///adds a connection to the array list of players
    public void addConnection(MultiPlayer player, PacketLogin packet) 
    {   ///set a boolean
        boolean alreadyConnected = false;
        
        ///loop through connected players array list
        for (MultiPlayer p : this.connectedPlayers) 
        {   ///find player and add
            if (player.getUsername().equalsIgnoreCase(p.getUsername())) 
            {
                if (p.returnIP() == null) 
                {
                    p.setIP(player.returnIP());
                }
                if (p.returnPORT() == -1) 
                {
                    p.setPort(player.returnPORT());
                }
                alreadyConnected = true;
                
            } 
            else 
            {
                // relay to the current connected player that there is a new
                // player
                sendData(packet.getData(), p.returnIP(), p.returnPORT());

                // relay to the new player that the currently connect player
                // exists
                packet = new PacketLogin(p.getUsername(),p.getX(),p.getY());
                sendData(packet.getData(), player.returnIP(), player.returnPORT());
            }
        }
        if (!alreadyConnected) {
            this.connectedPlayers.add(player);
           // packet.writeData(this);
        }
    }
    ///get the player when passed through a specific name through the array list
    public MultiPlayer getMulti(String username)
    {
        for(MultiPlayer player:this.connectedPlayers)
        {
            if(player.getUsername().equals(username))
            {
                return player;
            }
        }
        return null;
        
    }
     public MainMissile getMissile(String username)
    {
        for(MainMissile mx:this.ms)
        {
            if(mx.returnID().equals(username))
            {
                return mx;
            }
        }
        return null;
        
    }
    
    ///find the index of a player when passed through a username
     public int getMultiInt(String username)
    {
        int index = 0;
        for(MultiPlayer player:this.connectedPlayers)
        {
            if(player.getUsername().equals(username))
            {
                break;
            }
            index++;
        }
        return index;
        
    }
    
    ///removes the connected player from the array list
    public void removeConnection(PacketDisconnect packet)
    {   
        //System.out.println("In server remove connection");
        MultiPlayer player = getMulti(packet.getUsername());
        this.connectedPlayers.remove(getMultiInt(packet.getUsername()));
        packet.writeData(this);
    

    }
    ///utility method for returning the size of the red enemy
    public int returnREDsize()
    {   
        int enemysize = this.redEnemies.size();
        return enemysize;
    }
    
    
    
    
    
}
