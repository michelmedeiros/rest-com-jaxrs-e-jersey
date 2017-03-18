package br.com.alura.loja.resources;

import br.com.alura.loja.dao.CarrinhoDAO;
import br.com.alura.loja.dao.ProjetoDAO;
import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.modelo.Projeto;
import com.thoughtworks.xstream.XStream;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

/**
 * Created by Michel Medeiros on 09/03/2017.
 */
@Path("projetos")
public class ProjetoResource {


    @GET
    public List<Projeto>  getProjetos() {
        List<Projeto> projetos = new ProjetoDAO().busca();
        return projetos;
    }

    @GET
    @Path("{id}")
    public Projeto getProjetoId(@PathParam("id") Long id) {
        Projeto projeto = new ProjetoDAO().busca(id);
        return projeto;
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response adiciona(Projeto projeto) {
//        Projeto projeto = (Projeto) new XStream().fromXML(conteudo);
        new ProjetoDAO().adiciona(projeto);
        URI uri = URI.create("/projetos/" + projeto.getId());
        return Response.created(uri).build();
    }

    @DELETE
    @Path("{id}/produtos/{produtoId}")
    public Response removeProduto(@PathParam("id") Long id, @PathParam("produtoId") Long produtoId) {
        Projeto projeto = new ProjetoDAO().busca(id);
        projeto.remove(produtoId);
        return Response.ok().build();
    }

}
