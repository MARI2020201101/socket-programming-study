package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class TcpIpMultiChatServer {
    HashMap clients;

    TcpIpMultiChatServer(){
        clients = new HashMap();
        Collections.synchronizedMap(clients);
    }
    public void start(){
        ServerSocket serverSocket = null;
        Socket socket = null;
        try{
            serverSocket = new ServerSocket(7777);
            System.out.println("서버 시작되었습니다. ");
            while(true){
                socket = serverSocket.accept();
                System.out.println(" [ " + socket.getInetAddress() + " : "+ socket.getPort()+" ] " +
                        "님께서 접속하였습니다." );
                ServerReceiver thread = new ServerReceiver(socket);
                thread.start();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TcpIpMultiChatServer().start();
    }

    class ServerReceiver extends Thread{
        Socket socket;
        DataInputStream in;
        DataOutputStream out;
        public ServerReceiver(Socket socket){
            this.socket = socket;
            try{
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        @Override
        public void run() {
            String name = "";

            try{
                name = in.readUTF();
                sendToALL("# " + name + "님이 입장하였습니다.");
                clients.put(name,out);
                System.out.println("현재 서버 접속자 수는  : " + clients.size() + " 입니다.");

                while(in!=null){
                    sendToALL(in.readUTF());
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                sendToALL("# "+ name + "님이 나가셨습니다.");
                clients.remove(name);
                System.out.println(" [ " + socket.getInetAddress() + " : "+ socket.getPort()+" ] " +
                        "님께서 퇴장하였습니다." );
                System.out.println("현재 서버 접속자 수는  : " + clients.size() + " 입니다.");
            }
        }

        private void sendToALL(String msg) {
            Iterator it = clients.keySet().iterator();

            while (it.hasNext()){
                try{
                    DataOutputStream out = (DataOutputStream) clients.get(it.next());
                    out.writeUTF(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
