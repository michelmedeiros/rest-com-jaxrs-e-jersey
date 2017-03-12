package br.com.alura.loja.resources;

import br.com.alura.loja.dao.ProjetoDAO;
import br.com.alura.loja.modelo.Projeto;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Michel Medeiros on 09/03/2017.
 */
@Path("projetos")
public class ProjetoResource {

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_XML)
    public String getProjetos(@PathParam("id") Long id) {
        Projeto projeto = new ProjetoDAO().busca(id);
        return projeto.toXML();
    }
}
