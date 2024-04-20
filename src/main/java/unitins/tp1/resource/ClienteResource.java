package unitins.tp1.resource;

import java.util.List;

import org.jboss.logging.Logger;

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
import unitins.tp1.dto.cliente.ClienteDTO;
import unitins.tp1.dto.cliente.ClienteResponseDTO;
import unitins.tp1.service.cliente.ClienteService;
import unitins.tp1.service.endereco.EnderecoServiceImpl;


@Path("/clientes")    
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {

    @Inject
    ClienteService service;
    
    @Inject
    EnderecoServiceImpl enderecoImpl;


    @SuppressWarnings("unused")
    private static final Logger LOG = Logger.getLogger(ClienteResource.class);

    @POST
   // @RolesAllowed({"User","Admin"})
    public Response insert(@Valid ClienteDTO dto){
        ClienteResponseDTO retorno = service.insert(dto);
        return Response.status(201).entity(retorno).build();
    }

    @PUT
    @Transactional
    //@RolesAllowed({"User","Admin"})
    @Path("/{id}")
    public Response update (ClienteDTO dto, @PathParam("id") Long id) {
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
    @Path("/search/endereco/{enderecoId}")
    public Response findByEnderecoId(@PathParam("enderecoId") Long enderecoId) {
        List<ClienteResponseDTO> Clientes = service.findByEnderecoId(enderecoId);
        return Response.ok(Clientes).build();
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
