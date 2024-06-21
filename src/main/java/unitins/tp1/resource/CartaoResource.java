package unitins.tp1.resource;

import io.quarkus.logging.Log;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import unitins.tp1.dto.cartao.CartaoDTO;
import unitins.tp1.dto.cartao.CartaoResponseDTO;
import unitins.tp1.service.cartao.CartaoService;

@Path("/cartoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartaoResource {

    @Inject
    CartaoService service;

    @POST
    @RolesAllowed({"User"})
    public CartaoResponseDTO insert(Long idCliente, CartaoDTO dto) {
        Log.info("Cadastrando um cartao.");
        return service.insert(idCliente, dto);
    }

    @PUT
    @Transactional
    @Path("/{id}")
    @RolesAllowed({"User"})
    public CartaoResponseDTO update(CartaoDTO dto, @PathParam("id") Long id) {
        Log.info("Atualizando dados de um cartao.");
        return service.update(dto, id);
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    @RolesAllowed({"Admin"})
    public void delete(@PathParam("id") Long id) {
        Log.info("Deletando um cartao.");
        service.delete(id);
    }

    @GET
    @RolesAllowed({"Admin"})
    public Response findAll(){
        Log.info("Buscando todos os cartoes.");
        return Response.ok(service.findByAll()).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"Admin"})
    public Response findById(@PathParam("id") Long id){
        Log.info("Buscando um cartao expecificado pelo id.");
        return Response.ok(service.findById(id)).build();
    }

    @GET
    @Path("/search/banco/{banco}")
    @RolesAllowed({"User","Admin"})
    public Response findByBanco(@PathParam("banco") String banco){
        Log.info("Buscando um cartao expecificado pelo banco.");
        return Response.ok(service.findByBanco(banco)).build();
    }

    @GET
    @Path("/search/cartao/cliente/{idCliente}")
    @RolesAllowed({"User","Admin"})
    public Response findByCliente(@PathParam("idCliente") Long idCliente){
        Log.info("Buscando um cartao expecificado pelo id do cliente.");
        return Response.ok(service.findByCliente(idCliente)).build();
    }
}