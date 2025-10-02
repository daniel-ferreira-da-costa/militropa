package unitins.tp1.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import io.quarkus.security.identity.SecurityIdentity;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import unitins.tp1.dto.pedido.PedidoDTO;
import unitins.tp1.service.cliente.ClienteService;
import unitins.tp1.service.pedido.PedidoService;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/pedidos")
public class PedidoResource {

    private static final Logger LOG = Logger.getLogger(PedidoResource.class);

    @Inject
    public PedidoService pedidoService;

    @Inject
    SecurityIdentity securityIdentity;

    @Inject
    JsonWebToken jwt;

    @Inject
    ClienteService clienteService;

    @POST
    @RolesAllowed({ "User" })
    public Response insert(@Valid PedidoDTO dto) {
        LOG.info("Executando criação de pedido");
        return Response.status(Status.CREATED).entity(pedidoService.insert(dto)).build();
    }

    @GET
    @RolesAllowed({ "User", "Admin" })
    public Response findAll() {
        LOG.info("Executando FindAll");
        return Response.ok(pedidoService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({ "Admin" })
    public Response findById(@PathParam("id") Long id) {
        LOG.infof("Executando o findById");
        return Response.ok(pedidoService.findById(id)).build();
    }

    @GET
    @Path("/search/cliente/{id}")
    @RolesAllowed({ "User", "Admin" })
    public Response findByCliente(@PathParam("id") Long idCliente) {
        return Response.ok(pedidoService.findByCliente(idCliente)).build();
    }

    @PATCH
    @Path("/alterarStatusPagamento/{idPedido}")
    @RolesAllowed({ "User" })
    public Response alterarStatusPagamento(@PathParam("idPedido") Long idPedido) {
        LOG.info("Executando alteração de status de pagamento");
        pedidoService.alterarStatusPagamento(idPedido);
        return Response.noContent().build();
    }

    @GET
    @Path("/meusPedidos")
    @RolesAllowed("User")
    public Response meusPedidos() {
        try {
            LOG.info("Buscando pedidos do usuário: " + jwt.getSubject());
            if (jwt.getSubject() == null || jwt.getSubject().isEmpty()) {
                return Response.status(Status.UNAUTHORIZED).entity("Usuário não autenticado").build();
            }
            return Response.ok(pedidoService.meusPedidos()).build();
        } catch (ValidationException e) {
            LOG.error("Erro ao buscar pedidos do usuário: " + e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (NotFoundException e) {
            LOG.error("Pedidos não encontrados: " + e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
