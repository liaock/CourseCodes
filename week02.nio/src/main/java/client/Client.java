package client;

import java.io.IOException;

/**
 * Created by liaock on 2021/5/16
 **/

public class Client {

    public static void main(String[] args) throws IOException {
        String serverUrlAndPort = "http://127.0.0.1:8801";
        String responseStr = HttpClientUtils.httpGet(serverUrlAndPort);
        System.out.println(responseStr);
    }


}
