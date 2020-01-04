package de.nordakademie.informaticup.pandemicfighter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class RequestHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        System.out.println("Incoming request: " + httpExchange.getRequestURI());
        httpExchange.getResponseHeaders().set("Content-Type", "application/json; charset=utf-8");
        String method = httpExchange.getRequestMethod();
        String query = httpExchange.getRequestURI().getQuery();
        String path = httpExchange.getRequestURI().getPath();
        if (method.equals("POST") && path.equals("/")) {
            Gson gson = new GsonBuilder()
                    .excludeFieldsWithModifiers(Modifier.TRANSIENT)
                    .create();
            String reqBody = convertStreamToString(httpExchange.getRequestBody());
            JsonObject jsonRequestObject = gson.fromJson(reqBody, JsonObject.class);
            int round = jsonRequestObject.get("round").getAsInt();
            String outcome = jsonRequestObject.get("outcome").getAsString();
            System.out.println("Round: " + round + " Outcome: " + outcome);
            System.out.println(jsonRequestObject.toString());

            JsonObject jsonResponseObject = new JsonObject();
            jsonResponseObject.addProperty("type", "endRound");
            String response = jsonResponseObject.toString();
            httpExchange.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes(StandardCharsets.UTF_8));
            os.close();
        }
        httpExchange.sendResponseHeaders(404, 0);
        OutputStream os = httpExchange.getResponseBody();
        os.write(null);
        os.close();
    }

    private String convertStreamToString(InputStream is) {
        Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}