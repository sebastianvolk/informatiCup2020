package de.nordakademie.informaticup.pandemicfighter;

import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

@SuppressWarnings("restriction")
public class Main {
    public static void main(String[] args) throws Exception {
        int port = 50123;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new RequestHandler());
        server.setExecutor(null);
        System.out.println("Server started on port " + port);
        System.out.println("Quit the server with CONTROL-C");
        server.start();
    }
}
