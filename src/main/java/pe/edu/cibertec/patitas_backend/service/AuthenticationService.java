package pe.edu.cibertec.patitas_backend.service;

import pe.edu.cibertec.patitas_backend.dto.LoginReqDTO;

import java.io.IOException;

public interface AuthenticationService {
    String[] validarUsuario(LoginReqDTO loginReqDTO) throws IOException;
}
