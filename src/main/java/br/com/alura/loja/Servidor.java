package br.com.alura.loja;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

/**
 * Created by Michel Medeiros on 09/03/2017.
 */
public class Servidor {

    public static void main(String[] args) throws IOException {
        HttpServer server = inicializa();
        System.out.println("Servidor rodando...");
        System.in.read();
        server.stop();

    }

    static HttpServer inicializa() throws IOException {
        URI uri = URI.create("http://localhost:8081/");
        ResourceConfig config = new ResourceConfig().packages("br.com.alura.loja");
        return GrizzlyHttpServerFactory.createHttpServer(uri, config);
    }

    static void paraServidor(HttpServer server) {
        server.stop();
    }
}
