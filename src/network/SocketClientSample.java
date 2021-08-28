package network;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Date;

public class SocketClientSample {
    public static void main(String[] args) {
        SocketClientSample sample = new SocketClientSample();
        sample.sendSocketSample();
    }

    private void sendSocketSample() {
        for(int i = 0 ; i<10 ; i++){
            sendSocketData(" I like java! //"+ new Date());
        }
        sendSocketData("EXIT");
    }

    private void sendSocketData(String s) {
        try (Socket socket = new Socket("127.0.0.1", 9999)) {
            System.out.println("Client socket status : " + socket.isConnected());
            Thread.sleep(1000);
            OutputStream stream = socket.getOutputStream();
            BufferedOutputStream out = new BufferedOutputStream(stream);
            byte[] data = s.getBytes(StandardCharsets.UTF_8);
            out.write(data);
            System.out.println("Client socket: Send data...........");
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
