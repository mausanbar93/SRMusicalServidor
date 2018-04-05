/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unimayor.srmusicalservidor.servicio;

import co.edu.unimayor.srmusicalservidor.datos.dto.PlaylistaDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.unimayor.srmusicalservidor.repositorio.PlaylistaRepositorio;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author admin
 */
@Service
public class PlaylistaServicio {

    @Value("${constantes.URLSERVIDORCANCIONES}")
    private String URLSERVIDORCANCIONES;
    
    @Autowired
    private PlaylistaRepositorio playlistarepositorio;

    public List<PlaylistaDTO> listarCancionesUsuario(Integer usuario) {
        try {
            List<PlaylistaDTO> respuesta = playlistarepositorio.listarCancionesUsuario(usuario);
            if (respuesta.size() > 0) {
                respuesta.forEach((playlistaDTO) -> {
                    playlistaDTO.setUrlAudio(URLSERVIDORCANCIONES+playlistaDTO.getEstado()+"/"+playlistaDTO.getId()+".mp3");
                });
                return respuesta;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw e;
        }
    }

//    public List<UsuarioDTO> actualizar(List<UsuarioDTO> lista) {
//        try {
//            List<UsuarioDTO> usuarios_respuesta = new ArrayList<>();
//            Usuario usuario;
//            UsuarioDTO usuario_respuesta;
//            for (UsuarioDTO user : lista) {
//                usuario = usuariorepositorio.findOne(user.getId());
//                if (usuario.getCuenta().equals(user.getCuenta())) {
//                    usuario.setNombre(user.getNombre());
//                    usuario.setApellido(user.getApellido());
//                    usuario.setCuenta(user.getCuenta());
//                    usuario.setContrasena(user.getContrasena());
//                    usuario.setOcupacion(user.getOcupacion());
//                    usuario.setCorreo(user.getCorreo());
//                    usuario.setDireccion(user.getDireccion());
//                    usuario.setFechaNacimiento(user.getFechaNacimiento());
//                    usuario.setNumeroCelular(user.getNumeroCelular());
//                    usuario.setHabilitado(Boolean.TRUE);
//                    usuario_respuesta = new UsuarioDTO(usuariorepositorio.save(usuario));
//                    usuarios_respuesta.add(usuario_respuesta);
//                } else {
//                    Usuario user_busqueda = usuariorepositorio.findCuenta(user.getCuenta());
//                    if (user_busqueda == null) {
//                        usuario.setNombre(user.getNombre());
//                        usuario.setApellido(user.getApellido());
//                        usuario.setCuenta(user.getCuenta());
//                        usuario.setContrasena(user.getContrasena());
//                        usuario.setOcupacion(user.getOcupacion());
//                        usuario.setCorreo(user.getCorreo());
//                        usuario.setDireccion(user.getDireccion());
//                        usuario.setFechaNacimiento(user.getFechaNacimiento());
//                        usuario.setNumeroCelular(user.getNumeroCelular());
//                        usuario.setHabilitado(Boolean.TRUE);
//                        usuario_respuesta = new UsuarioDTO(usuariorepositorio.save(usuario));
//                        usuarios_respuesta.add(usuario_respuesta);
//                    } else {
//                        return null;
//                    }
//                }
//            }
//            return usuarios_respuesta;
//        } catch (Exception e) {
//            throw e;
//        }
//    }
}
