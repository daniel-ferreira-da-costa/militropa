package unitins.tp1.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;

import io.quarkus.logging.Log;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import unitins.tp1.dto.endereco.EnderecoDTO;
import unitins.tp1.dto.endereco.EnderecoResponseDTO;
import unitins.tp1.service.endereco.EnderecoService;

@Path("/enderecos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnderecoResource {

    @Inject
    EnderecoService service;

    @Inject
    JsonWebToken jwt;

    @PUT
    @Transactional
    @Path("/{id}")
    //@RolesAllowed({"User","Admin"})
    public EnderecoResponseDTO update(EnderecoDTO dto, @PathParam("id") Long id) {
        Log.info("Atualizando um endereco: "+id);
        return service.update(dto, id);
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    //@RolesAllowed({"User","Admin"})
    public void delete(@PathParam("id") Long id) {
        Log.info("Deletando um endereco:" +id);
        service.delete(id);
    }

    @GET
    //@RolesAllowed({"User","Admin"})
    public Response findAll(){
        Log.info("Buscando todos os enderecos cadastrados.");
        return Response.ok(service.findByAll()).build();
    }

    @GET
    @Path("/{id}")
    //@RolesAllowed({"Admin"})
    public Response findById(@PathParam("id") Long id){
        Log.info("Buscando um endereco expecificado pelo id: "+id);
        return Response.ok(service.findById(id)).build();
    }

    @GET
    @Path("/search/nome/{nome}")
    //@RolesAllowed({"User","Admin"})
    public Response findByNome(@PathParam("nome") String nome){
        Log.info("Buscando um endereco expecificado pelo nome: "+nome);
        return Response.ok(service.findByNome(nome)).build();
    }
}
