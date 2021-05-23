package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by liaock on 2021/5/16
 **/

public class RequestHandler {
    /**
     * 请求处理.
     * @param socket
     */
    public void service(Socket socket) {
        try {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String body = "hello,nio1";
            printWriter.println("Content-Length:" + body.getBytes().length);
            printWriter.println();
            printWriter.write(body);
            printWriter.close();
            socket.close();
        } catch (IOException e) { // | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
