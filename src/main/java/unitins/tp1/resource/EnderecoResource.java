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
import unitins.tp1.dto.endereco.EnderecoDTO;
import unitins.tp1.dto.endereco.EnderecoResponseDTO;
import unitins.tp1.service.endereco.EnderecoService;

@Path("/enderecos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnderecoResource {

    @Inject
    EnderecoService service;

    @POST
    @RolesAllowed({"Admin"})
    public EnderecoResponseDTO insert(EnderecoDTO dto) {
        Log.info("Cadastrando um endereco.");
        return service.insert(dto);
    }

    @PUT
    @Transactional
    @Path("/{id}")
    @RolesAllowed({"Admin"})
    public EnderecoResponseDTO update(EnderecoDTO dto, @PathParam("id") Long id) {
        Log.info("Atualizando um endereco.");
        return service.update(dto, id);
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    @RolesAllowed({"Admin"})
    public void delete(@PathParam("id") Long id) {
        Log.info("Deletando um endereco.");
        service.delete(id);
    }

    @GET
    public Response findAll(){
        Log.info("Buscando todos os enderecos cadastrados.");
        return Response.ok(service.findByAll()).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"Admin"})
    public Response findById(@PathParam("id") Long id){
        Log.info("Buscando um endereco expecificado pelo id.");
        return Response.ok(service.findById(id)).build();
    }

    @GET
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome){
        Log.info("Buscando um endereco expecificado pelo nome.");
        return Response.ok(service.findByNome(nome)).build();
    }
}
