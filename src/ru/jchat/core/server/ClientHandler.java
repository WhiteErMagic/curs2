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

    public ClientHandler(Server server, Socket socket) {
        try {
            this.socket = socket;
            this.server = server;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

            new Thread(()-> {
                    try{
                        while (true) {
                            String msg = in.readUTF();
                            if (msg.startsWith("/auth")) {
                                String[] data = msg.split("\\s");
                                String newNick = server.getAuthService().getNickByLoginAndPass(data[1], data[2]);
                                if(newNick != null){
                                    if(!server.isNickBusy(newNick)) {
                                        nick = newNick;
                                        server.subscribe(this);
                                        sendMsg("/authok");
                                        break;
                                    }else{
                                        showAlert("Такой nick уже занят!");
                                    }
                                }else{
                                    showAlert("Не верный логин или пароль!");
                                }
                            }
                        }
                        while(true){
                            String msg = in.readUTF();
                            if (msg.startsWith("/")) {
                                if (msg.startsWith("/end"))
                                    break;
                                else if (msg.startsWith("/w")) {
                                    String toNick = msg.substring(msg.indexOf(" ") + 1, msg.indexOf(" ", 3));
                                    msg = msg.substring((msg.substring(msg.indexOf(" ") + 1, msg.indexOf(" ", 3))).length() + 3).trim();
                                    server.broadcastMsg(nick, msg, toNick);
                                }
                            }else
                                 server.broadcastMsg(nick, msg, null);
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

    public void showAlert(String msg){
        Platform.runLater (() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Возникли проблемы!");
            alert.setHeaderText(null);
            alert.setContentText(msg);
            alert.showAndWait();
        });
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
