package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {

    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(8189)){
            System.out.println("Server started... Waiting clients");
            Socket socket = serverSocket.accept();
            new ClientHandler(socket);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
