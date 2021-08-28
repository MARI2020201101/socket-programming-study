package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerSample {
    public static void main(String[] args) {
        SocketServerSample sample = new SocketServerSample();
        sample.startServer();
    }

    private void startServer() {
        try (ServerSocket server = new ServerSocket(9999);
            Socket client = server.accept()) {
            while(true){
                System.out.println("Client accepted...............");
                InputStream stream = client.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(stream));
                String data = null;
                StringBuilder receivedData = new StringBuilder();
                while((data=in.readLine())!=null){
                    receivedData.append(data);
                }
                System.out.println("received Data : "+receivedData);
                in.close();
                stream.close();
                if(receivedData!=null && "EXIT".equals(receivedData.toString())){
                    System.out.println("Stop Socket Server................");
                    break;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
