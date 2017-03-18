package br.com.alura.loja;

import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;
import com.thoughtworks.xstream.XStream;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by Michel Medeiros on 09/03/2017.
 */
public class ClientTest {

    private HttpServer server;
    private Client client;
    private WebTarget target;

    @Before
    public void inicializaServidor() throws IOException {
        this.server = Servidor.inicializa();
        ClientConfig config = new ClientConfig();
        config.register(new LoggingFilter());
        client = ClientBuilder.newClient(config);
        target = client.target("http://localhost:8081");
    }

    @After
    public void encerraServidor() {
        this.server.stop();
    }

    @Test
    public void testaQueBuscarUmCarrinhoTrazOCarrinhoEsperado() {
        Carrinho carrinho = target.path("/carrinhos/1").request().get(Carrinho.class);
        Assert.assertEquals(carrinho.getRua(), "Rua Vergueiro 3185, 8 andar");
    }

    @Test
    public void testaQueCriaUmCarrinhoAPartirDeUmXML() {
        Carrinho carrinho = new Carrinho();
        carrinho.adiciona(new Produto(314L, "Tablet", 999, 1));
        carrinho.setRua("Rua Vergueiro");
        carrinho.setCidade("Sao Paulo");

        Entity<Carrinho> entity = Entity.entity(carrinho, MediaType.APPLICATION_XML);

        Response response = target.path("/carrinhos").request().post(entity);
        Assert.assertEquals(201, response.getStatus());

    }
}
