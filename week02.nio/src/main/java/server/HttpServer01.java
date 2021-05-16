package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by liaock on 2021/5/16
 **/

public class HttpServer01 {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSockt = new ServerSocket(8801);
        RequestHandler requestHandler = new RequestHandler();
        while (true) {
            Socket socket = serverSockt.accept();
            requestHandler.service(socket);
        }
    }
}
