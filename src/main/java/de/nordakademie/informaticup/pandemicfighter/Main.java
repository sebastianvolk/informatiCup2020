package de.nordakademie.informaticup.pandemicfighter;

import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

@SuppressWarnings("restriction")
public class Main {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(50123), 0);
        server.createContext("/", new RequestHandler());
        server.setExecutor(null);
        System.out.println("Server started");
        server.start();
    }
}
