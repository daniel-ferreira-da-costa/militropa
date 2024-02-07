package unitins.tp1.service;

import unitins.tp1.dto.UsuarioResponseDTO;

public interface JwtService {

    public String generateJwt(UsuarioResponseDTO dto);
    
}
