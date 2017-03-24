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
public class PacketLogin extends Packet
{
    private String username;
    private double x;
    private double y;
    
    
    public PacketLogin(byte[] data)
    {
    super(00);
    String[] dataArray = readData(data).split(",");
    this.username = dataArray[0];
    this.x = Double.parseDouble(dataArray[1]);
    this.y = Double.parseDouble(dataArray[2]);
    }
    
    public PacketLogin(String username, double x, double y)
    {
    super(00);
    this.x = x;
    this.y = y;
    this.username = username;
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
    public byte[] getData() {
        return ("00" + this.username + "," + this.getX() + "," + this.getY()).getBytes();
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
