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
public class PacketCGGS extends Packet
{
     private String username;
    
    public PacketCGGS(byte[] data)
    {
    super(11);
    this.username = readData(data);
    }
    
    public PacketCGGS(String username)
    {
    super(11);
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
        return ("11" + this.username).getBytes();
    }
    
    public String getUsername()
    {
        return username;
    }
    
    
    
    
}
