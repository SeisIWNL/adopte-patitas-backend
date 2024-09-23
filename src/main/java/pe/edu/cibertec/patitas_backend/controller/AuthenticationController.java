package pe.edu.cibertec.patitas_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.patitas_backend.dto.LoginReqDTO;
import pe.edu.cibertec.patitas_backend.dto.LoginResponseDTO;
import pe.edu.cibertec.patitas_backend.service.AuthenticationService;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginReqDTO loginReqDTO) {

        try {
            String[] datosUsuario = authenticationService.validarUsuario(loginReqDTO);
            if (datosUsuario == null) {
                return new LoginResponseDTO("01","Error: Usuario no encontrado","","");
            }
            return new LoginResponseDTO("00","",datosUsuario[0], datosUsuario[1]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
