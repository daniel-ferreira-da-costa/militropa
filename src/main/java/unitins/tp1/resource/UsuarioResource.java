package unitins.tp1.resource;


import unitins.tp1.dto.usuario.UsuarioDTO;
import unitins.tp1.service.usuario.UsuarioService;

import org.eclipse.microprofile.jwt.JsonWebToken;

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
import jakarta.ws.rs.core.Response.Status;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    UsuarioService service;

    @Inject
    JsonWebToken jwt;
    
    @POST
    public Response insertCliente(UsuarioDTO dto) {
        Log.info("Cadastrando um cliente.");
        return Response.status(Status.CREATED).entity(service.insert(dto)).build();
    }

    @RolesAllowed({"User","Admin"})
    @PUT
    @Transactional
    @Path("/{id}")
    public Response update(UsuarioDTO dto, @PathParam("id") Long id) {
        Log.info("Fazendo update de um usuario.");
        service.update(dto, id);
        return Response.noContent().build();
    }

    @RolesAllowed({"Admin"})
    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        Log.info("Deletando um usuario.");
        service.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }
 
    @RolesAllowed({"Admin"})
    @GET
    public Response findAll() {
        Log.info("Busca de todos os usuarios");
        return Response.ok(service.findByAll()).build();
    }

    @RolesAllowed({"Admin"})
    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        Log.info("Busca de um usuario especificado pelo id.");
       return Response.ok(service.findById(id)).build();
    }
    
    @RolesAllowed({"Admin"})
    @GET
    @Path("/search/login/{login}")
    public Response findByNome(@PathParam("login") String login) {
        Log.info("Busca de um usuario especificado pelo login.");
        return Response.ok(service.findByNome(login)).build();
    }

    @RolesAllowed({"User","Admin"})
    @GET
    @Path("/my-user")
    public Response findMyUser() {
        Log.info("Busca do proprio usuario.");
       return Response.ok(service.findMyUser()).build();
    }
}