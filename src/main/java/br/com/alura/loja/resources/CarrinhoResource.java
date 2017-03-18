package br.com.alura.loja.resources;

import br.com.alura.loja.dao.CarrinhoDAO;
import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;
import com.thoughtworks.xstream.XStream;
import org.glassfish.grizzly.http.util.ContentType;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

/**
 * Created by Michel Medeiros on 09/03/2017.
 */
@Path("carrinhos")
@Produces(MediaType.APPLICATION_XML)
public class CarrinhoResource {
    @GET
    @Path("{id}")
    public Carrinho busca(@PathParam("id") Long id) {
        Carrinho carrinho = new CarrinhoDAO().busca(id);
        return carrinho;
    }

    @GET
    public Carrinho busca() {
        Carrinho carrinho = new CarrinhoDAO().busca(1L);
        return carrinho;
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response adiciona(Carrinho carrinho) {
//        Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
        new CarrinhoDAO().adiciona(carrinho);
        URI uri = URI.create("/carrinhos/" + carrinho.getId());
        return Response.created(uri).build();
    }

    @DELETE
    @Path("{id}/produtos/{produtoId}")
    public Response removeProduto(@PathParam("id") Long id, @PathParam("produtoId") Long produtoId) {
        Carrinho carrinho = new CarrinhoDAO().busca(id);
        carrinho.remove(produtoId);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    @Path("{id}/produtos/{produtoId}/quantidade")
    public Response atualizaProduto(@PathParam("id") Long id, @PathParam("produtoId") Long produtoId,
                                    Produto produto) {
//        Produto produto = (Produto) new XStream().fromXML(conteudo);
        Carrinho carrinho = new CarrinhoDAO().busca(id);
        carrinho.trocaQuantidade(produto);
        return Response.ok().build();
    }
}
