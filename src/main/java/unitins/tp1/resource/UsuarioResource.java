package unitins.tp1.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import unitins.tp1.dto.usuario.UsuarioDTO;
import unitins.tp1.dto.usuario.UsuarioResponseDTO;
import unitins.tp1.dto.usuario.alterarLoginUsuarioDTO;
import unitins.tp1.dto.usuario.alterarSenhaUsuarioDTO;
import unitins.tp1.service.usuario.UsuarioService;
import unitins.tp1.validation.ValidationException;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    private static final Logger LOG = Logger.getLogger(UsuarioResource.class);

    @Inject
    UsuarioService service;

    @Inject
    JsonWebToken jwt;

    @POST
    public Response insert(UsuarioDTO dto) {
        LOG.infof("Cadastrando usuário com login='%s'", dto.login());
        try {
            UsuarioResponseDTO criado = service.insert(dto);
            LOG.infof("Usuário criado com ID=%d", criado.id());
            return Response.status(Status.CREATED).entity(criado).build();
        } catch (ValidationException e) {
            LOG.warnf("Falha ao cadastrar usuário: %s", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            LOG.error("Erro inesperado ao cadastrar usuário", e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro interno").build();
        }
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({ "User", "Admin" })
    public Response update(UsuarioDTO dto, @PathParam("id") Long id) {
        LOG.infof("Atualizando usuário ID=%d, novo login='%s'", id, dto.login());
        try {
            service.update(dto, id);
            LOG.infof("Usuário ID=%d atualizado com sucesso", id);
            return Response.noContent().build();
        } catch (NotFoundException e) {
            LOG.warnf("Usuário não encontrado para update ID=%d", id);
            return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            LOG.errorf(e, "Erro inesperado ao atualizar usuário ID=%d", id);
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro interno").build();
        }
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({ "Admin" })
    public Response delete(@PathParam("id") Long id) {
        LOG.infof("Deletando usuário ID=%d", id);
        try {
            service.delete(id);
            LOG.infof("Usuário ID=%d deletado com sucesso", id);
            return Response.noContent().build();
        } catch (ValidationException e) {
            LOG.warnf("Falha ao deletar usuário ID=%d: %s", id, e.getMessage());
            return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            LOG.errorf(e, "Erro inesperado ao deletar usuário ID=%d", id);
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro interno").build();
        }
    }

    @GET
    @RolesAllowed({ "Admin" })
    public Response findAll() {
        LOG.info("Buscando todos os usuários");
        try {
            var list = service.findByAll();
            LOG.infof("Total de usuários encontrados: %d", list.size());
            return Response.ok(list).build();
        } catch (Exception e) {
            LOG.error("Erro inesperado ao buscar todos os usuários", e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro interno").build();
        }
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({ "Admin" })
    public Response findById(@PathParam("id") Long id) {
        LOG.infof("Buscando usuário por ID=%d", id);
        try {
            var dto = service.findById(id);
            LOG.infof("Usuário encontrado: ID=%d, login='%s'", dto.id(), dto.login());
            return Response.ok(dto).build();
        } catch (NotFoundException e) {
            LOG.warnf("Usuário não encontrado ID=%d", id);
            return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            LOG.errorf(e, "Erro inesperado ao buscar usuário ID=%d", id);
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro interno").build();
        }
    }

    @GET
    @Path("/search/login/{login}")
    @RolesAllowed({ "Admin" })
    public Response findByLogin(@PathParam("login") String login) {
        LOG.infof("Buscando usuários com login parecido '%s'", login);
        try {
            var list = service.findByNome(login);
            LOG.infof("Usuarios encontrados para '%s': %d", login, list.size());
            return Response.ok(list).build();
        } catch (Exception e) {
            LOG.errorf(e, "Erro inesperado ao buscar usuários por login '%s'", login);
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro interno").build();
        }
    }

    @GET
    @Path("/my-user")
    @RolesAllowed({ "User", "Admin" })
    public Response findMyUser() {
        String login = jwt.getSubject();
        LOG.infof("Buscando dados do usuário logado: '%s'", login);
        try {
            var dto = service.findByLogin(login);
            LOG.infof("Dados do usuário logado retornados: ID=%d", dto.id());
            return Response.ok(dto).build();
        } catch (NotFoundException e) {
            LOG.warnf("Usuário logado não encontrado: '%s'", login);
            return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            LOG.errorf(e, "Erro inesperado ao buscar usuário logado '%s'", login);
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro interno").build();
        }
    }

    @PATCH
    @Path("/alterarsenha")
    @RolesAllowed({ "User", "Admin" })
    public Response alterarSenha(alterarSenhaUsuarioDTO dto) {
        String login = jwt.getSubject();
        LOG.infof("Iniciando alteração de senha para usuário '%s'", login);
        try {
            service.alterarSenha(dto, login);
            LOG.infof("Senha alterada com sucesso para '%s'", login);
            return Response.noContent().build();
        } catch (ValidationException e) {
            LOG.warnf("Validação de senha falhou para '%s': %s", login, e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (NotFoundException e) {
            LOG.warnf("Usuário não encontrado ao alterar senha: '%s'", login);
            return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            LOG.errorf(e, "Erro inesperado ao alterar senha de '%s'", login);
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro interno").build();
        }
    }

    @PATCH
    @Path("/alterarlogin")
    @RolesAllowed({ "User" })
    public Response alterarLogin(alterarLoginUsuarioDTO dto) {
        String login = jwt.getSubject();
        LOG.infof("Iniciando alteração de login para usuário '%s' → novoLogin='%s'", login, dto.novoLogin());
        try {
            service.alterarLogin(dto, login);
            LOG.infof("Login alterado com sucesso para '%s' → '%s'", login, dto.novoLogin());
            return Response.noContent().build();
        } catch (ValidationException e) {
            LOG.warnf("Validação de novo login falhou para '%s': %s", login, e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (NotFoundException e) {
            LOG.warnf("Usuário não encontrado ao alterar login: '%s'", login);
            return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            LOG.errorf(e, "Erro inesperado ao alterar login de '%s'", login);
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Erro interno").build();
        }
    }
}