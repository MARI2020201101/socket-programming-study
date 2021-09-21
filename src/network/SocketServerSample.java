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
        ServerSocket server=null;
        Socket client = null;
        try {
            server = new ServerSocket(9999);//소켓을 열고
            while(true){
                System.out.println("Client accepted...............");
                client = server.accept();//클라이언트를 기다린다.
                InputStream stream = client.getInputStream();//스트림을 열어서
                BufferedReader in = new BufferedReader(new InputStreamReader(stream));//버퍼로 읽는다.
                String data = null;
                StringBuilder receivedData = new StringBuilder();
                while((data=in.readLine())!=null){
                    receivedData.append(data);
                }
                System.out.println("received Data : "+receivedData);

                if(receivedData!=null && "EXIT".equals(receivedData.toString())){
                    System.out.println("Stop Socket Server................");
                    break;
                }
                in.close();
                stream.close();
                client.close();//exit후 닫도록 수정함
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(server!=null){
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
