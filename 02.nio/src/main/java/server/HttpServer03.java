package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liaock on 2021/5/16
 **/

public class HttpServer03 {
    public static void main(String[] args) throws IOException {

        // 线程如果处理时间长，FixedThreadPool 可能会因为阻塞队列无限扩张导致OOM.
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 2);
        ServerSocket serverSocket = new ServerSocket(8803);
        RequestHandler requestHandler = new RequestHandler("i am server 1 at port 8803");
        while (true) {
            Socket socket = serverSocket.accept();
            executorService.execute(() -> requestHandler.service(socket));
        }
    }
}
