package br.com.alura.loja;

import br.com.alura.loja.modelo.Carrinho;
import com.thoughtworks.xstream.XStream;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.io.IOException;

/**
 * Created by Michel Medeiros on 09/03/2017.
 */
public class ClientTest {

    private HttpServer server;

    @Before
    public void inicializaServidor() throws IOException {
        this.server = Servidor.inicializa();
    }

    @After
    public void encerraServidor() {
        this.server.stop();
    }

    @Test
    public void testaQueBuscarUmCarrinhoTrazOCarrinhoEsperado() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8081");
        String conteudo = target.path("/carrinhos/1").request().get(String.class);
        Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
        System.out.println(conteudo);
        Assert.assertEquals(carrinho.getRua(), "Rua Vergueiro 3185, 8 andar");
    }
}
