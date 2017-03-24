/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Packets;

import NetProgramming.ClientUDP;
import NetProgramming.ServerUDP;

/**
 *
 * @author eze
 */
public abstract class Packet 
{
    public static enum PacketTypes
    {   ////enums for the specific packets being sent
           INVALID(-1), LOGIN(00), DISCONNECT(01), 
           MOVE(02), LASER(03), GREEN(04), RED(05), 
           CRED(06), CGREEN(07), GGS(10), CGGS(11),
           SMALLM(12), BIGM(13), CBIG(14), CSMALL(15), 
           BOSS(16), PB(17), CMISSILE(18), HEALTH(19);
           
           private int packetId;
           private PacketTypes(int packetId)
           {
               this.packetId = packetId;
           
           }
           
           public int getId()
           {
           return packetId;
           }
           
           
    }
    
   
    
    public byte packetId;
    
    public Packet(int packetId)
    {
    this.packetId = (byte) packetId;
    }
    
    ////for writing to client
    public abstract void writeData(ClientUDP client);
    //for writing to server
    public abstract void writeData(ServerUDP server);
    
    ///reads the data in the string
    public String readData(byte[] data)
    {
        String message = new String(data).trim();
        return message.substring(2);
        ///returning substring gets rid of the packet id to processing input
    }
    
    //filters from the packet id 00, 01, 02 etc...
    public static PacketTypes lookupPacket(int id)
    {
        for(PacketTypes p : PacketTypes.values())
        {
        if(p.getId() == id)
        {
        return p;
        }
        }
        System.out.println("Returning Invalid!");
        return PacketTypes.INVALID;
    }
    
    public abstract byte[] getData();
    
    
    ///looks up packet from a specific string
    public static PacketTypes lookupPacket(String packetId)
    {
        try
        {
            return lookupPacket(Integer.parseInt(packetId));
        }
        catch(NumberFormatException e)
        {   System.out.println("Invalid Packet");
            return PacketTypes.INVALID;
        }
    
    }
    
       
    
    
}
