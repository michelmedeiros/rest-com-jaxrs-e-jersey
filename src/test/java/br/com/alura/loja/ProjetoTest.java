package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.alura.loja.dao.ProjetoDAO;
import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.modelo.Projeto;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Michel Medeiros on 09/03/2017.
 */
public class ProjetoTest {

    private HttpServer server;
    private WebTarget target;
    private Client client;

    @Before
    public void inicializaServidor() throws IOException {
        this.server = Servidor.inicializa();
        ClientConfig config = new ClientConfig();
        config.register(new LoggingFilter());
        this.client = ClientBuilder.newClient(config);
        this.target = client.target("http://localhost:8081");
    }

    @After
    public void encerraServidor() {
        this.server.stop();
    }

    @Test
    public void testaQueBuscarUmProjetoTrazOProejtoEsperado() {
        Projeto projeto = target.path("/projetos/1").request().get(Projeto.class);
        assertEquals(projeto.getNome(), "Minha loja");
    }

    @Test
    public void testaQueSuportaNovosCarrinhos(){
        Carrinho carrinho = new Carrinho();
        carrinho.adiciona(new Produto(314L, "Tablet", 999, 1));
        carrinho.setRua("Rua Vergueiro");
        carrinho.setCidade("Sao Paulo");

        Entity<Carrinho> entity = Entity.entity(carrinho, MediaType.APPLICATION_XML);

        Response response = target.path("/carrinhos").request().post(entity);
        String location = response.getHeaderString("Location");
        String conteudo = client.target(location).request().get(String.class);
        assertTrue(conteudo.contains("Tablet"));
        assertEquals(201, response.getStatus());
    }
}
