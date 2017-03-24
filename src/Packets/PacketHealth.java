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
public class PacketHealth extends Packet
{
    private String username;
    private int Hitpoints;
    
    public PacketHealth(byte[] data)
    {
    super(19);
    String[] dataArray = readData(data).split(",");
    this.username = dataArray[0];
    this.Hitpoints = Integer.parseInt(dataArray[1]);
    }
    
    public PacketHealth(String username, int Hitpoints)
    {
    super(19);
    this.username = username;
    this.Hitpoints = Hitpoints;
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
        return ("19" + this.username + "," + this.Hitpoints).getBytes();
    }
    
    public String getUsername()
    {
        return username;
    }
    public int getHits()
    {
        return Hitpoints;
    }
    
    
    
}
