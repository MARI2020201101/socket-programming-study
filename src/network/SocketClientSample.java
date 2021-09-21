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
        Socket socket=null;
        try {
            socket = new Socket("127.0.0.1", 9999);//소켓을 열고
            System.out.println("Client socket status : " + socket.isConnected());
            Thread.sleep(1000);
            OutputStream stream = socket.getOutputStream();//스트림을 소켓에서 열어서
            // PrintWriter를 이용하면, char단위로 읽기때문에 아래 로직을 줄일 수 있다.
            BufferedOutputStream out = new BufferedOutputStream(stream);
            byte[] data = s.getBytes(StandardCharsets.UTF_8);
            out.write(data);//버퍼로 소켓에 쓴다. 바이트스트림기반 버퍼
            System.out.println("Client socket: Send data...........");
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(socket!=null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
