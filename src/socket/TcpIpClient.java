package socket;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

public class TcpIpClient {
    public static void main(String[] args) {
        try{
            String serverip = "127.0.0.1";
            System.out.println("연결 시작 : "+serverip);
            Socket socket = new Socket(serverip,7777);

            InputStream in = socket.getInputStream();
            DataInputStream dis = new DataInputStream(in);

            System.out.println("서버로부터 받은 메시지 : " + dis.readUTF());
            System.out.println("연결 종료");
            dis.close();
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
