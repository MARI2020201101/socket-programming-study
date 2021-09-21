package socket;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TcpIPServer2 {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(7777);
            System.out.println(getTime() + "서버가 준비되었습니다.");

            while(true){
                try{
                    System.out.println(getTime() + "요청을 기다립니다.");
                    Socket socket = serverSocket.accept();
                    System.out.println(getTime() + socket.getInetAddress());
                    System.out.println("get Port: " +socket.getPort());
                    System.out.println("get local port : "+socket.getLocalPort());

                    OutputStream out = socket.getOutputStream();
                    DataOutputStream dos = new DataOutputStream(out);


                    dos.writeUTF(" message from server....");
                    System.out.println(getTime() +" 메시지 전송완료");

                    dos.close();
                    socket.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static String getTime() {
        SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]");
        return f.format(new Date());
    }
}
