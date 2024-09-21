package pe.edu.cibertec.patitas_backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.patitas_backend.dto.LoginReqDTO;
import pe.edu.cibertec.patitas_backend.service.AuthenticationService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    ResourceLoader resourceLoader;

    @Override
    public String[] validarUsuario(LoginReqDTO loginReqDTO) throws IOException {

        String[] datosUsuario = null;
        Resource resource = resourceLoader.getResource("classpath:usuarios.txt");

        try(BufferedReader br = new BufferedReader(new FileReader(resource.getFile()))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (loginReqDTO.tipoDocumento().equals(datos[0]) &&
                    loginReqDTO.numDocumento().equals(datos[1]) &&
                    loginReqDTO.password().equals(datos[2])) {

                    datosUsuario = new String[2];
                    datosUsuario[0] = datos[3];
                    datosUsuario[1] = datos[4];
                }

            }
        } catch (IOException e) {
            datosUsuario = null;
            throw new IOException(e);
        }

        return datosUsuario;
    }
}
