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
public class PacketSmallM extends Packet
{
    private String username;
    private double x;
    private double y;
    
    
    public PacketSmallM(byte[] data)
    {
    super(12);
    String[] dataArray = readData(data).split(",");
    this.username = dataArray[0];
    try
    {
    this.x = Double.parseDouble(dataArray[1]);
    this.y = Double.parseDouble(dataArray[2]);
    }
    catch(NumberFormatException e)
    {
        System.out.println("Number Parse Error in Small Metoer");
    }
    }
    
    public PacketSmallM(String username, double x, double y)
    {
    super(12);
    this.username = username;
    this.x = x;
    this.y = y;
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
         return ("12" + this.username +","+this.x+","+this.y).getBytes();
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
    
    
    
    
    
}
