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
public class PacketDisconnect extends Packet {
    
    private String username;
    
    public PacketDisconnect(byte[] data)
    {
    super(01);
    this.username = readData(data);
    }
    
    public PacketDisconnect(String username)
    {
    super(01);
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
        return ("01" + this.username).getBytes();
    }
    
    public String getUsername()
    {
        return username;
    }
    
    
}
