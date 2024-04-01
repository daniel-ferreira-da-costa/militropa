package unitins.tp1.resource;

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
    //@RolesAllowed({"Admin"})
    public CartaoResponseDTO insert(CartaoDTO dto) {
        return service.insert(dto);
    }

    @PUT
    @Transactional
    @Path("/{id}")
    //@RolesAllowed({"Admin"})
    public CartaoResponseDTO update(CartaoDTO dto, @PathParam("id") Long id) {
        return service.update(dto, id);
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    //@RolesAllowed({"Admin"})
    public void delete(@PathParam("id") Long id) {
        service.delete(id);
    }

    @GET
    public Response findAll(){
        return Response.ok(service.findByAll()).build();
    }

    @GET
    @Path("/{id}")
    //@RolesAllowed({"Admin"})
    public Response findById(@PathParam("id") Long id){
        return Response.ok(service.findById(id)).build();
    }

    @GET
    @Path("/search/banco/{banco}")
    public Response findByBanco(@PathParam("banco") String banco){
        return Response.ok(service.findByBanco(banco)).build();
    }
}
