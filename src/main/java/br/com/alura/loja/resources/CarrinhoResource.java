package br.com.alura.loja.resources;

import br.com.alura.loja.dao.CarrinhoDAO;
import br.com.alura.loja.modelo.Carrinho;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by Michel Medeiros on 09/03/2017.
 */
@Path("carrinhos")
@Produces(MediaType.APPLICATION_XML)
public class CarrinhoResource {
    @GET
    @Path("{id}")
    public String busca(@PathParam("id") Long id) {
        Carrinho carrinho = new CarrinhoDAO().busca(id);
        return carrinho.toXML();
    }
}
