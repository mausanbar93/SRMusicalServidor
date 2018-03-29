/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unimayor.srmusicalservidor.datos.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Administrador
 */
public class ExtJSReturnUtil {

    /**
     * Conjunto de métodos para el mapeo de objetos,
     *
     * @param datos Lista de datos obtenidos a traves de una consulta
     * @return mapeo de datos a objeto JSON
     */
    public static Map<String, Object> mapOK(List datos) {
        Map<String, Object> modelMap;
        if (datos == null) {
            modelMap = new HashMap(2);
            modelMap.put("success", true);
            modelMap.put("total", 0);
        } else {
            modelMap = new HashMap(3);
            modelMap.put("success", true);
            modelMap.put("data", datos);
            modelMap.put("total", datos.size());
        }
        return modelMap;
    }

    public static Map<String, Object> pagingMapOK(List datos, int total) {
        Map<String, Object> modelMap;
        if (datos == null) {
            modelMap = new HashMap(2);
            modelMap.put("success", true);
            modelMap.put("total", 0);
        } else {
            modelMap = new HashMap(3);
            modelMap.put("success", true);
            modelMap.put("data", datos);
            modelMap.put("total", total);
        }
        return modelMap;
    }

    public static Map<String, Object> mapOK(Object obj) {
        Map<String, Object> modelMap = new HashMap(3);
        modelMap.put("success", true);
        modelMap.put("total", obj != null ? 1 : 0);
        modelMap.put("data", obj);
        return modelMap;
    }

    public static Map<String, Object> mapOk(String msg) {
        Map<String, Object> modelMap = new HashMap(2);
        modelMap.put("success", true);
        modelMap.put("message", msg);
        return modelMap;
    }

    public static Map<String, Object> mapError(String msg) {
        Map<String, Object> modelMap = new HashMap(2);
        modelMap.put("success", false);
        modelMap.put("message", msg);
        return modelMap;
    }

}
