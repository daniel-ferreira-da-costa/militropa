package unitins.tp1.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import unitins.tp1.dto.CartaoDTO;
import unitins.tp1.dto.PagamentoResponseDTO;
import unitins.tp1.service.PagamentoService;

import org.jboss.logging.Logger;

@Path("/pagamento")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PagamentoResource {
    @Inject
    PagamentoService service;

    private static final Logger LOG = Logger.getLogger(PagamentoResource.class);

    @Path("/cartao")
    @RolesAllowed({"Admin","User"})
    @POST
    @Transactional
    public Response postCartaoPagamento(@Valid CartaoDTO dto) {
        LOG.info("Fazendo pagamento via cartao");
        LOG.info("A partir da solicitacao de id: " + dto);
        PagamentoResponseDTO responseDTO = service.pagarCartao(dto);
        return Response.status(Response.Status.CREATED).entity(responseDTO).build();
    }
}
