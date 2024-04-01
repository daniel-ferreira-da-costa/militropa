package unitins.tp1.service.jwt;

import unitins.tp1.dto.usuario.UsuarioResponseDTO;

public interface JwtService {
    
    public String generateJwt(UsuarioResponseDTO dto);
    
} 
