package unitins.tp1.resource;

import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import unitins.tp1.dto.cliente.ClienteDTO;
import unitins.tp1.dto.cliente.ClienteResponseDTO;
import unitins.tp1.service.cliente.ClienteService;

@Path("/cadastro")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CadastroClienteResource {

    @Inject
    ClienteService service;

    @POST
    public Response insert(@Valid ClienteDTO dto) {
        Log.info("Inserindo um cliente."+dto.login());
        ClienteResponseDTO retorno = service.insert(dto);
        return Response.status(201).entity(retorno).build();
    }
}
