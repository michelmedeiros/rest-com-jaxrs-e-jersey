package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import br.com.alura.loja.dao.ProjetoDAO;
import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Projeto;
import com.thoughtworks.xstream.XStream;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Michel Medeiros on 09/03/2017.
 */
public class ProjetoTest {

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
    public void testaQueBuscarUmProjetoTrazOProejtoEsperado() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8081");
        String conteudo = target.path("/projetos/1").request().get(String.class);
        Projeto projeto = (Projeto) new XStream().fromXML(conteudo);
        System.out.println(conteudo);
        Assert.assertEquals(projeto.getNome(), "Minha loja");
    }


}
