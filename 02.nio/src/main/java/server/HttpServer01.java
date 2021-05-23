package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by liaock on 2021/5/16
 **/

public class HttpServer01 {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8801);
        RequestHandler requestHandler = new RequestHandler("i am server 1 at port 8801");
        while (true) {
            Socket socket = serverSocket.accept();
            requestHandler.service(socket);
        }
    }
}
