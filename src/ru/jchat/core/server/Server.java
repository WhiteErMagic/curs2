package ru.jchat.core.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;

public class Server {
    private AuthSirvice authService;
    private Vector<ClientHandler> clients;
    public Server() {
        clients = new Vector<>();
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            System.out.println("Server started... Waiting clients");
            while (true){
                Socket socket = serverSocket.accept();
                authService = new AuthSirvice();
                authService.connect();
                System.out.println("Client conected " + socket.getLocalAddress() + " " + socket.getPort() + socket.getLocalPort());
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Не удалось запустить сервис авторизации!");
        } finally {
            authService.disconnect();
        }
    }

    public void broadcastMsg(String nick, String msg, String toNick){
        for (ClientHandler c: clients) {
            if(toNick == null)
                c.sendMsg(nick + " " + msg);
            else
                if(c.getNick().equals(toNick) || c.getNick().equals(nick))
                    c.sendMsg(nick + " " + msg);
        }
    }

    public void subscribe(ClientHandler clientHandler){
        clients.add(clientHandler);
        broadcastClientsList();
    }

    public void unsubscribe(ClientHandler clientHandler){
        clients.remove(clientHandler);
        broadcastClientsList();
    }

    public AuthSirvice getAuthService() {
        return authService;
    }

    public boolean isNickBusy(String nick){
        for (ClientHandler c: clients) {
            if(c.getNick().equals(nick))
                return true;
        }

        return false;
    }

    public void broadcastClientsList(){
        StringBuilder sb = new StringBuilder("/clientslist ");
        for (ClientHandler c: clients) {
            sb.append(c.getNick() + " ");
        }

        String out = sb.toString();

        for (ClientHandler c: clients) {
            c.sendMsg(out);
        }

    }
}
