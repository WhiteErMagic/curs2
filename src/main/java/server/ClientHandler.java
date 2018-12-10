package server;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler{
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public ClientHandler(final Socket socket) {
        try {
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());


            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        while(true){
                            String msg = in.readUTF();
                            if(msg.length() > 0)
                                System.out.println(msg);
                            msg = reader.readLine();
                            if(msg.length() > 0)
                                sendMsg(msg);
                            if (msg.equals("/end")) break;
                        }
                    }catch (IOException e){
                        e.printStackTrace();
                    }finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
