package ru.jchat.core.server;

import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Server server;
    private String nick;
    private double connect_time;

    public ClientHandler(Server server, Socket socket) {
        try {
            this.connect_time = System.currentTimeMillis();
            this.socket = socket;
            this.server = server;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

            new Thread(()-> {
                    try{
                        while (true) {

                            if ((System.currentTimeMillis() - connect_time) / 1000 > 10) {
                                System.out.println("Не авторизован!");
                                break;
                            }

                            String msg = in.readUTF();

                            if (msg.startsWith("/auth ")) {
                                String[] data = msg.split("\\s");
                                String newNick = server.getAuthService().getNickByLoginAndPass(data[1], data[2]);
                                if(newNick != null){
                                    if(!server.isNickBusy(newNick)) {
                                        nick = newNick;
                                        sendMsg("/authok " + nick);
                                        server.subscribe(this);
                                        break;
                                    }else{
                                        sendMsg("Такой nick уже занят!");
                                    }
                                }else{
                                    sendMsg("Не верный логин или пароль!");
                                }
                            }
                        }
                        if(!nick.isEmpty()) {
                            while (true) {
                                String msg = in.readUTF();
                                if (msg.startsWith("/")) {
                                    if (msg.startsWith("/end"))
                                        break;
                                    else if (msg.startsWith("/w")) {
                                        String toNick = msg.substring(msg.indexOf(" ") + 1, msg.indexOf(" ", 3));
                                        msg = msg.substring((msg.substring(msg.indexOf(" ") + 1, msg.indexOf(" ", 3))).length() + 3).trim();
                                        server.broadcastMsg(nick, msg, toNick);
                                    }
                                } else
                                    server.broadcastMsg(nick, msg, null);
                            }
                        }
                    }catch (IOException e){
                        e.printStackTrace();
                    }finally {
                        nick = null;
                        server.unsubscribe(this);
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            }).start();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getNick() {
        return nick;
    }
}
