/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unimayor.srmusicalservidor.servicio;

import co.edu.unimayor.srmusicalservidor.datos.Cancion;
import co.edu.unimayor.srmusicalservidor.datos.Estado;
import co.edu.unimayor.srmusicalservidor.datos.dto.CancionDTO;
import co.edu.unimayor.srmusicalservidor.repositorio.CancionRepositorio;
import co.edu.unimayor.srmusicalservidor.repositorio.EstadoRepositorio;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author admin
 */
@Service
public class CancionServicio {

    @Autowired
    private CancionRepositorio cancionrepositorio;
    @Autowired
    private EstadoRepositorio estadorepositorio;
    @Autowired
    private ServletContext servletContext;

    public void leer() {
        try {
            URL res = servletContext.getClassLoader().getResource("static");
            JsonParser parser = new JsonParser();
            FileReader fr = new FileReader(res.getPath().replace("%20", " ") + File.separator + "resources" + File.separator + "songs.json");
            JsonElement datos = parser.parse(fr);
            List<CancionDTO> canciones = new ArrayList<>();
            CancionDTO cancionRespuesta;
            Cancion cancion;
            if (datos.isJsonObject()) {
                System.out.println("Es objeto");
                JsonObject obj = datos.getAsJsonObject();
                java.util.Set<java.util.Map.Entry<String, JsonElement>> entradas = obj.entrySet();
                java.util.Iterator<java.util.Map.Entry<String, JsonElement>> iter = entradas.iterator();
                while (iter.hasNext()) {
                    java.util.Map.Entry<String, JsonElement> entrada = iter.next();
                    System.out.println("Clave: " + entrada.getKey());
                    System.out.println("Valor:" + entrada.getValue());
                    JsonObject lista_songs = (JsonObject) entrada.getValue();
                    java.util.Set<java.util.Map.Entry<String, JsonElement>> entradas_songs = lista_songs.entrySet();
                    java.util.Iterator<java.util.Map.Entry<String, JsonElement>> iter_songs = entradas_songs.iterator();
                    while (iter_songs.hasNext()) {
                        cancion = new Cancion();
                        java.util.Map.Entry<String, JsonElement> entrada_s = iter_songs.next();
                        System.out.println("Clave: " + entrada_s.getKey());
                        System.out.println("Valor:" + entrada_s.getValue());
                        JsonObject lista_songs_item = (JsonObject) entrada_s.getValue();
                        java.util.Set<java.util.Map.Entry<String, JsonElement>> entradas_songs_item = lista_songs_item.entrySet();
                        java.util.Iterator<java.util.Map.Entry<String, JsonElement>> iter_songs_item = entradas_songs_item.iterator();
                        while (iter_songs_item.hasNext()) {
                            java.util.Map.Entry<String, JsonElement> entrada_s_item = iter_songs_item.next();
                            String clave = entrada_s_item.getKey();
                            String valor = entrada_s_item.getValue().toString().replaceAll("\"", "");
                            switch (clave) {
                                case "letra":
                                    if (valor.contains("1409612408028")) {
                                        String expresion = "\\n...\\n\\n******* This Lyrics is NOT for Commercial use *******\\n(1409612408028)";
                                        valor = valor.substring(0, valor.length() - expresion.length());
                                        valor = valor.replaceAll("/\n\n", "<br>").replaceAll("/\n", "<br>").replaceAll("/\\u00fa", "ú").replaceAll("/\\u00ed", "í");
                                        //valor = valor.replaceAll("/\\u00fa", "ú").replaceAll("/\\u00ed", "í");
                                    }
                                    cancion.setLetra(valor);
                                    break;
                                case "api":
                                    cancion.setApi(valor);
                                    break;
                                case "artista":
                                    cancion.setArtista(valor);
                                    break;
                                case "titulo":
                                    cancion.setTitulo(valor);
                                    break;
                                case "ranking":
                                    cancion.setRankingBillboard(Integer.parseInt(valor));
                                    break;
                                case "semanas":
                                    cancion.setSemanaBillboard(Integer.parseInt(valor));
                                    break;
                                case "url":
                                    cancion.setUrl(valor);
                                    break;
                                case "visualizacion":
                                    cancion.setNumeroVisualizacion(!valor.equals("") ? Integer.parseInt(valor) : 0);
                                    break;
                                case "duracion":
                                    cancion.setDuracionSegundo(!valor.equals("") ? Integer.parseInt(valor) : 0);
                                    break;
                                case "valoracion":
                                    cancion.setValoracionReferencia(!valor.equals("") ? Double.parseDouble(valor) : 0);
                                    break;
                                case "estado": {
                                    Estado est = estadorepositorio.findNombre(valor);
                                    cancion.setEstadoByEstadoReferencia(est != null ? est : null);
                                    break;
                                }
                                case "sentiment": {
                                    Estado est = estadorepositorio.findNombre(valor);
                                    cancion.setEstadoByEstadoApiSentiment(est != null ? est : null);
                                    break;
                                }
                                case "sentiment140": {
                                    Estado est = estadorepositorio.findNombre(valor);
                                    cancion.setEstadoByEstadoApiSentiment140(est != null ? est : null);
                                    break;
                                }
                                case "repustate": {
                                    Estado est = estadorepositorio.findNombre(valor);
                                    cancion.setEstadoByEstadoApiRepustate(est != null ? est : null);
                                    break;
                                }
                                case "text_processing": {
                                    Estado est = estadorepositorio.findNombre(valor);
                                    cancion.setEstadoByEstadoApiTextProcessing(est != null ? est : null);
                                    break;
                                }
                                default:
                                    break;
                            }
                        }
                        if (cancion.getApi() != null) {
                            cancion.setHabilitado(Boolean.TRUE);
                            cancionRespuesta = new CancionDTO(cancionrepositorio.save(cancion));
                            canciones.add(cancionRespuesta);
                        }
                    }
                }
                // Es un conjunto de pares clave, valor
                // Para cada par, imprimir la clave y llamar a dumpJSONElement(valor)
            } else if (datos.isJsonArray()) {
                System.out.println("JSON-ARRAY" + datos);
                // Es un conjunto de valores, que pueden ser elementos simples o compuestos
                // Para cada valor, llamar a dumpJSONElemento(valor)
            } else if (datos.isJsonPrimitive()) {
                System.out.println("JSON-PRIMITIVE" + datos);
                // Es un elemento simple. Determinar si se trata de un valor booleano,
                // un número o cadena de texto
            } else if (datos.isJsonNull()) {
                System.out.println("Es NULL");
            } else {
                System.out.println("Es otra cosa");
            }
            System.out.println("LISTA FINAL: " + canciones);
        } catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
        }
    }

    public List<CancionDTO> listar() {
        try {
            List<Cancion> lista = (List<Cancion>) cancionrepositorio.findAll();
            List<CancionDTO> respuesta = new ArrayList<>();
            CancionDTO cancion;
            if (lista.size() > 0) {
                for (Cancion canc : lista) {
                    if (canc.isHabilitado()) {
                        cancion = new CancionDTO(canc);
                        respuesta.add(cancion);
                    }
                }
                //respuesta.sort(Comparator.comparingInt(CancionDTO::getId).reversed());
                return respuesta;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw e;
        }
    }

//    public List<CancionDTO> crear(List<CancionDTO> lista) {
//        try {
//            List<CancionDTO> canciones_respuesta = new ArrayList<>();
//            Cancion cancion;
//            CancionDTO cancion_respuesta;
//            for (CancionDTO canc : lista) {
//                Cancion canc_busqueda = cancionrepositorio.findCuenta(user.getCuenta());
//                if (user_busqueda == null) {
//                    usuario = new Usuario();
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
//                    return null;
//                }
//            }
//            return usuarios_respuesta;
//        } catch (Exception e) {
//            throw e;
//        }
//    }
//
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
    public void eliminar(List<CancionDTO> lista) {
        try {
            Cancion cancion;
            for (CancionDTO canc : lista) {
                cancion = cancionrepositorio.findOne(canc.getId());
                cancion.setHabilitado(Boolean.FALSE);
                cancionrepositorio.save(cancion);
            }
        } catch (Exception e) {
            throw e;
        }
    }

}
