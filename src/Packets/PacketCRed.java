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
public class PacketCRed extends Packet
{
    private String username;
    
    public PacketCRed(byte[] data)
    {
    super(06);
    this.username = readData(data);
    }
    
    public PacketCRed(String username)
    {
    super(06);
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
        return ("06" + this.username).getBytes();
    }
    
    public String getUsername()
    {
        return username;
    }
    
}
