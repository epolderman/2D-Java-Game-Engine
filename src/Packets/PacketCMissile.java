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
public class PacketCMissile extends Packet
{
     private String username;
    
    public PacketCMissile(byte[] data)
    {
    super(18);
    this.username = readData(data);
    }
    
    public PacketCMissile(String username)
    {
    super(18);
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
        return ("18" + this.username).getBytes();
    }
    
    public String getUsername()
    {
        return username;
    }
    
    
    
}
