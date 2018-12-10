package Client;

import java.io.*;
import java.net.Socket;

public class Client{
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 8189;
    private Socket socket;
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private DataInputStream in;
    private DataOutputStream out;
    public Client() {
        try {
            socket = new Socket(SERVER_HOST, SERVER_PORT);
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        String msg = reader.readLine();
                        if(msg.length() > 0)
                            sendMsg(msg);
                        msg = in.readUTF();
                        if(msg.length() > 0)
                            System.out.println(msg);
                    }
                } catch (Exception e) {
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

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
