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
public class PacketGreen extends Packet
{
    private String username;
    private double x;
    private double y;
    private double hitpoints;
    
    
    public PacketGreen(byte[] data)
    {
    super(04);
    String[] dataArray = readData(data).split(",");
    this.username = dataArray[0];
    try
    {
    this.x = Double.parseDouble(dataArray[1]);
    this.y = Double.parseDouble(dataArray[2]);
    this.hitpoints = Double.parseDouble(dataArray[3]);
    }
    catch(NumberFormatException e)
    {
        System.out.println("Number Parse Error in Green Packet");
    }
    }
    
    public PacketGreen(String username, double x, double y, double hp)
    {
    super(04);
    this.username = username;
    this.x = x;
    this.y = y;
    this.hitpoints = hp;
    }

    @Override
    public void writeData(ClientUDP client) 
    {
     client.sendData(getData());
    }

    @Override
    public void writeData(ServerUDP server) 
    {
    server.sendDataToAllClients(getData());
    }

    @Override
    public byte[] getData() 
    {
         return ("04" + this.username +","+this.x+","+this.y+","+this.hitpoints).getBytes();
    }
    
    
    
    public double getX()
    {
        return x;
    }
    public double getY()
    {
        return y;
    }
     public String getUsername()
    {
        return username;
    }
     public double getHits()
     {
         return hitpoints;
     }
    

}
