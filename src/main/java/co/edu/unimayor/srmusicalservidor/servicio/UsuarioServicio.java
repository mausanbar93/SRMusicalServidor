/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unimayor.srmusicalservidor.servicio;

import co.edu.unimayor.srmusicalservidor.datos.Usuario;
import co.edu.unimayor.srmusicalservidor.datos.dto.UsuarioDTO;
import co.edu.unimayor.srmusicalservidor.datos.util.AuthUtil;
import co.edu.unimayor.srmusicalservidor.repositorio.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author admin
 */
@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuariorepositorio;

    public List<UsuarioDTO> login(String cuenta, String contrasena) {
        try {
            List<Usuario> lista = usuariorepositorio.login(cuenta, contrasena);
            List<UsuarioDTO> respuesta = new ArrayList<>();
            UsuarioDTO usuario;
            if (lista.size() > 0) {
                for (Usuario user : lista) {
                    usuario = new UsuarioDTO(user);
                    respuesta.add(usuario);
                }
                return respuesta;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public List<UsuarioDTO> listar() {
        try {
            List<Usuario> lista = (List<Usuario>) usuariorepositorio.findAll();
            List<UsuarioDTO> respuesta = new ArrayList<>();
            UsuarioDTO usuario;
            if (lista.size() > 0) {
                for (Usuario user : lista) {
                    if (user.isHabilitado() && !user.getId().equals(AuthUtil.getCurrentUser().getId())) {
                        usuario = new UsuarioDTO(user);
                        respuesta.add(usuario);
                    }
                }
                respuesta.sort(Comparator.comparingInt(UsuarioDTO::getId).reversed());
                return respuesta;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public List<UsuarioDTO> crear(List<UsuarioDTO> lista) {
        try {
            List<UsuarioDTO> usuarios_respuesta = new ArrayList<>();
            Usuario usuario;
            UsuarioDTO usuario_respuesta;
            for (UsuarioDTO user : lista) {
                Usuario user_busqueda = usuariorepositorio.findCuenta(user.getCuenta());
                if (user_busqueda == null) {
                    usuario = new Usuario();
                    usuario.setNombre(user.getNombre());
                    usuario.setApellido(user.getApellido());
                    usuario.setCuenta(user.getCuenta());
                    usuario.setContrasena(user.getContrasena());
                    usuario.setOcupacion(user.getOcupacion());
                    usuario.setCorreo(user.getCorreo());
                    usuario.setDireccion(user.getDireccion());
                    usuario.setFechaNacimiento(user.getFechaNacimiento());
                    usuario.setNumeroCelular(user.getNumeroCelular());
                    usuario.setHabilitado(Boolean.TRUE);
                    usuario_respuesta = new UsuarioDTO(usuariorepositorio.save(usuario));
                    usuarios_respuesta.add(usuario_respuesta);
                } else {
                    return null;
                }
            }
            return usuarios_respuesta;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<UsuarioDTO> actualizar(List<UsuarioDTO> lista) {
        try {
            List<UsuarioDTO> usuarios_respuesta = new ArrayList<>();
            Usuario usuario;
            UsuarioDTO usuario_respuesta;
            for (UsuarioDTO user : lista) {
                usuario = usuariorepositorio.findOne(user.getId());
                if (usuario.getCuenta().equals(user.getCuenta())) {
                    usuario.setNombre(user.getNombre());
                    usuario.setApellido(user.getApellido());
                    usuario.setCuenta(user.getCuenta());
                    usuario.setContrasena(user.getContrasena());
                    usuario.setOcupacion(user.getOcupacion());
                    usuario.setCorreo(user.getCorreo());
                    usuario.setDireccion(user.getDireccion());
                    usuario.setFechaNacimiento(user.getFechaNacimiento());
                    usuario.setNumeroCelular(user.getNumeroCelular());
                    usuario.setHabilitado(Boolean.TRUE);
                    usuario_respuesta = new UsuarioDTO(usuariorepositorio.save(usuario));
                    usuarios_respuesta.add(usuario_respuesta);
                } else {
                    Usuario user_busqueda = usuariorepositorio.findCuenta(user.getCuenta());
                    if (user_busqueda == null) {
                        usuario.setNombre(user.getNombre());
                        usuario.setApellido(user.getApellido());
                        usuario.setCuenta(user.getCuenta());
                        usuario.setContrasena(user.getContrasena());
                        usuario.setOcupacion(user.getOcupacion());
                        usuario.setCorreo(user.getCorreo());
                        usuario.setDireccion(user.getDireccion());
                        usuario.setFechaNacimiento(user.getFechaNacimiento());
                        usuario.setNumeroCelular(user.getNumeroCelular());
                        usuario.setHabilitado(Boolean.TRUE);
                        usuario_respuesta = new UsuarioDTO(usuariorepositorio.save(usuario));
                        usuarios_respuesta.add(usuario_respuesta);
                    } else {
                        return null;
                    }
                }
            }
            return usuarios_respuesta;
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminar(List<UsuarioDTO> lista) {
        try {
            Usuario usuario;
            for (UsuarioDTO user : lista) {
                usuario = usuariorepositorio.findOne(user.getId());
                usuario.setHabilitado(Boolean.FALSE);
                usuariorepositorio.save(usuario);
            }
        } catch (Exception e) {
            throw e;
        }
    }

}
