package unitins.tp1.resource;

import java.util.List;

import org.jboss.logging.Logger;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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
import unitins.tp1.dto.FuncionarioDTO;
import unitins.tp1.dto.FuncionarioResponseDTO;
import unitins.tp1.service.EnderecoServiceImpl;
import unitins.tp1.service.FuncionarioService;

@Path("/funcionarios")    
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FuncionarioResource {
    @Inject
    FuncionarioService service;
    
    @Inject
    EnderecoServiceImpl enderecoImpl;


    private static final Logger LOG = Logger.getLogger(FuncionarioResource.class);

    @POST
   // @RolesAllowed({"Admin"})
    public Response insert(@Valid FuncionarioDTO dto){
        FuncionarioResponseDTO retorno = service.insert(dto);
        return Response.status(201).entity(retorno).build();
    }

    @PUT
    @Transactional
    //@RolesAllowed({"Admin"})
    @Path("/{id}")
    public Response update (FuncionarioDTO dto, @PathParam("id") Long id) {
        service.update(dto, id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @DELETE
    @Transactional
   // @RolesAllowed({"Admin"})
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        service.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }


    @GET
    //@RolesAllowed({"Admin"})
    @Path("/search/endereco/{matricula}")
    public Response findByEnderecoId(@PathParam("matricula") String matricula) {
        List<FuncionarioResponseDTO> Funcionarios = service.findByMatricula(matricula);
        return Response.ok(Funcionarios).build();
    }


    @GET
    //@RolesAllowed({"Admin"})
    public Response findAll(){
        return Response.ok(service.findByAll()).build();
    }

    @GET
    //@RolesAllowed({"Admin"})
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        return Response.ok(service.findById(id)).build();
    }

    @GET
    //@RolesAllowed({"Admin"})
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome){
        return Response.ok(service.findByNome(nome)).build();
    }
}
