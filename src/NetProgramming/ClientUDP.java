/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package NetProgramming;

import GameState.MULTIPLAYERSTATE;
import Main.GamePanel;
import Units.MultiPlayer;
import Packets.Packet;
import Packets.PacketBigM;
import Packets.PacketBoss;
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
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author EzE
 */
//////////////the client class is exactly the same as the server class except it handles methods are called into the level methods
//////////////multiplayer state is passed through the class so it can access the levels methods
/////////////it does not contain any data structures as it does not need to
public class ClientUDP extends Thread
{
    
    //instance variables
    ///ipaddress for connection'
    //socket for sending data
    ///need gamepanel access
    ///multiplayer state for the level
    private InetAddress ipaddress;
    private DatagramSocket socket;
    private GamePanel yes;
    private MULTIPLAYERSTATE MPS;
    
    public ClientUDP(String ipAddress, GamePanel yes, MULTIPLAYERSTATE MPS)
    {   
        ////initialize everything
        this.MPS = MPS;
        this.yes = yes;
        try
        {
            this.socket  = new DatagramSocket();
            this.ipaddress = InetAddress.getByName(ipAddress);
            
        }
        catch(SocketException e)
        {
            e.printStackTrace();
        
        }
        catch(UnknownHostException e)
        {
            e.printStackTrace();
        }
        
    
    
    }
    ////runs the stream of data
    public void run()
    {   
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
           // System.out.println("[" + ipaddress.getHostAddress());
           // System.out.println("SERVER > " + new String(packet.getData()));
            
            
        
        }
    
    }
    
    /////these are the enums packets that are being send to the level/client on the specific port/ip
    ////a more detailed explanation is in the server class due to it doing more of the work
     public void parsePacket(byte[] data, InetAddress address, int port)
    {   
        
        String message = new String(data).trim();
        Packet.PacketTypes type = Packet.lookupPacket(message.substring(0, 2));
        Packet packet = null;
        switch(type)
        {
            case INVALID:
                break;
            case LOGIN:
                packet = new PacketLogin(data);
                handleLogin((PacketLogin)packet,address, port);
                break;
            case DISCONNECT:
                packet = new PacketDisconnect(data);
                System.out.println("[" + address.getHostAddress() + ":" + port + "] "
                    + ((PacketDisconnect) packet).getUsername() + " has left the galaxy...");
                MPS.removeMulti(((PacketDisconnect)packet).getUsername());
                break;
            case MOVE:
                //System.out.println("In move client subscript");
                packet = new PacketMove(data);
                this.handleMove((PacketMove)packet);
                break;
            case LASER:
                //System.out.println("In laser client subscript");
                packet = new PacketLaser(data);
                this.handleLaser((PacketLaser)packet);
                break;
            case GREEN:
                packet = new PacketGreen(data);
                this.handleGreen((PacketGreen)packet);
                break;
            case RED:
                packet = new PacketRed(data);
                this.handleRed((PacketRed)packet);
                break;
            case GGS:
                packet = new PacketGGS(data);
                this.handleGGS((PacketGGS)packet);
                break;
            case SMALLM:
                packet = new PacketSmallM(data);
                this.handleSmallMeteor((PacketSmallM)packet);
                break;
            case BIGM:
                packet = new PacketBigM(data);
                this.handleBigMeteor((PacketBigM)packet);
                break;
            case BOSS:
                packet = new PacketBoss(data);
                this.handleBoss((PacketBoss)packet);
                break;
            case PB:
                packet = new PacketPB(data);
                this.handlePB((PacketPB)packet);
                break;
            case HEALTH:
                packet = new PacketHealth(data);
                this.handleHealth((PacketHealth)packet);
                break;
        
        
        }  
        
        
    }
     /////all these methods call the methods from the level because you want to update them on the client side
     private void handleHealth(PacketHealth packet)
     {
     this.MPS.updateHealth(packet.getUsername(), packet.getHits());
     
     }
     
     private void handlePB(PacketPB packet)
     {
         this.MPS.movePB(packet.getUsername(),packet.getX(), packet.getY());
     
     }
     private void handleBoss(PacketBoss packet)
     {
        //System.out.println("Client: In Handle Red");
        this.MPS.moveBoss(packet.getUsername(),packet.getX(), packet.getY());
     
     }
     private void handleSmallMeteor(PacketSmallM packet)
     {
        //System.out.println("Client: In Handle Red");
        this.MPS.moveSMALLM(packet.getUsername(),packet.getX(), packet.getY());
     
     }
     private void handleBigMeteor(PacketBigM packet)
     {
        //System.out.println("Client: In Handle Red");
        this.MPS.moveBIGM(packet.getUsername(),packet.getX(), packet.getY());
     
     }
     
     private void handleGGS(PacketGGS packet)
     {
        //System.out.println("Client: In Handle Red");
        this.MPS.moveGGS(packet.getUsername(),packet.getX(), packet.getY());
     
     }
     private void handleRed(PacketRed packet)
     {
        //System.out.println("Client: In Handle Red");
        this.MPS.moveRed(packet.getUsername(),packet.getX(), packet.getY());
     
     }
    
       private void handleGreen(PacketGreen packet) 
    {   
        //System.out.println("Client: In Handle Green");
        this.MPS.moveGreen(packet.getUsername(),packet.getX(), packet.getY());
      
   }
     
        private void handleLaser(PacketLaser packet) 
    {   
        //System.out.println("Client: In Handle Laser");
        this.MPS.moveLaser(packet.getUsername(),packet.getX(), packet.getY());
      
   }
     
     
     private void handleMove(PacketMove packet)
     {  //System.out.println("in handle move client");
         this.MPS.movePlayer(packet.getUsername(), packet.getX(), packet.getY());
     
     }
    
    public void sendData(byte[] data)
    {
        DatagramPacket packet = new DatagramPacket(data, data.length, ipaddress, 1331);
        try 
        {
            socket.send(packet);
            
        } catch (IOException ex) 
        {
           ex.printStackTrace();
        }
    
    }
    
    private void handleLogin(PacketLogin packet, InetAddress address, int port)
    {
                //System.out.println("IN CLIENT LOGIN");
               
                System.out.println("[" + address.getHostAddress() + ":" + port + "] "
                    + packet.getUsername() + " has joined the game...");
                ///add player here
                //MultiPlayer player = null;
               MultiPlayer player = new MultiPlayer(MPS, packet.getX(), packet.getY(), ((PacketLogin)packet).getUsername(),address, port);
                MPS.addEntity(player);
    }

 
    
    
}