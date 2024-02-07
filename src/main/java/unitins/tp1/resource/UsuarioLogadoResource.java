package unitins.tp1.resource;

import unitins.tp1.service.UsuarioService;
import org.eclipse.microprofile.jwt.JsonWebToken;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.core.Response;

public class UsuarioLogadoResource {

    @Inject
    JsonWebToken jwt;

    @Inject
    UsuarioService usuarioService;

    @GET
    public Response getUsuario(){

        String login = jwt.getSubject();
        return Response.ok(usuarioService.findByLogin(login)).build(); 
    }    
}
