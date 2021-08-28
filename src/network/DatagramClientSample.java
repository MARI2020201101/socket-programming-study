package network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class DatagramClientSample {
    public static void main(String[] args) {
        DatagramClientSample sample = new DatagramClientSample();
        sample.sendDatagramSample();
    }

    private void sendDatagramSample() {
        for (int i=0 ; i<10 ; i++){
            sendDatagramData("I like udp! //"+new Date());
        }
        sendDatagramData("EXIT");
    }

    private void sendDatagramData(String s) {
        try{
            DatagramSocket client = new DatagramSocket();
            InetAddress address = InetAddress.getByName("127.0.0.1");
            byte[] buffer = s.getBytes(StandardCharsets.UTF_8);
            DatagramPacket packet = new DatagramPacket(buffer,0,buffer.length,address,9999);
            client.send(packet);
            System.out.println("Client send data.................");
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
