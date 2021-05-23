package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by liaock on 2021/5/16
 **/

public class HttpServer02 {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8801);
        RequestHandler requestHandler = new RequestHandler();
        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(()-> requestHandler.service(socket)).start();
        }
    }

}
