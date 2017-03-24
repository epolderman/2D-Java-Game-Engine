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
public class PacketMove extends Packet{
    
    private String username;
    private double x;
    private double y;
  
    
    public PacketMove(byte[] data)
    {
        
    super(02);
    String[] dataArray = readData(data).split(",");
    this.username = dataArray[0];
    this.x = Double.parseDouble(dataArray[1]);
    this.y = Double.parseDouble(dataArray[2]);
   
    
    }
    
    public PacketMove(String username, double x, double y)
    {
    super(02);
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
        return ("02" + this.username + "," + this.x + "," + this.y).getBytes();
        ///02erik,100,100, 50
    }
    
    public String getUsername()
    {
        return username;
    }
    public double getX()
    {
       return x;
       
    }
    public double getY()
    {
    return y;
    }
  
    
    
    
}
