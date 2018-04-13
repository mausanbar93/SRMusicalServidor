/**
 * CancionDTO.java
 *
 * Creada el 7/10/2017, 01:04:15 AM
 *
 * Clase Java desarrollada por Mauricio Sánchez Barragán para la empresa Seratic Ltda el día 7/10/2017.
 *
 * Esta clase es confidencial y para uso de las aplicaciones de la empresa Seratic Ltda.
 * Prohibido su uso sin autorización explícita de personal autorizado de la empresa Seratic Ltda.
 *
 * Para información sobre el uso de esta clase, así como bugs, actualizaciones o mejoras
 * envíar un email a <seratic@seratic.com> o a <mauricio.sanchez@seratic.com>.
 */
package co.edu.unimayor.srmusicalservidor.servicio;

import co.edu.unimayor.srmusicalservidor.datos.dto.PrediccionDTO;
import co.edu.unimayor.srmusicalservidor.datos.dto.PropiedadesMusicalesDTO;
import co.edu.unimayor.srmusicalservidor.datos.dto.PropiedadesMusicalesEstadoDTO;
import co.edu.unimayor.srmusicalservidor.repositorio.CancionRepositorio;
import co.edu.unimayor.srmusicalservidor.repositorio.ClasificadorNBMusicalRepositorio;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author Ing. Mauricio Sánchez Barragán <mauricio.sanchez@seratic.com>
 * @version 1.0
 * @date 7/10/2017
 */
@Service
public class ClasificadorNBMusicalServicio {

    @Value("${constantes.DISCRETIZAR_RANKING}")
    private Integer DISCRETIZAR_RANKING;
    @Value("${constantes.DISCRETIZAR_SEMANAS}")
    private Integer DISCRETIZAR_SEMANAS;
    @Value("${constantes.DISCRETIZAR_VISUALIZACIONES}")
    private Integer DISCRETIZAR_VISUALIZACIONES;
    @Value("${constantes.DISCRETIZAR_VALORACION}")
    private Integer DISCRETIZAR_VALORACION;
    @Value("${constantes.EMOCION_POSITIVA}")
    private String EMOCION_POSITIVA;
    @Value("${constantes.EMOCION_NEUTRAL}")
    private String EMOCION_NEUTRAL;
    @Value("${constantes.EMOCION_NEGATIVA}")
    private String EMOCION_NEGATIVA;

    @Autowired
    private ClasificadorNBMusicalRepositorio clasificadorNBMusicalRepositorio;
    @Autowired
    private CancionRepositorio cancionRepositorio;

    // Declaración de variables necesarias para manejo de weka
    // Attribute atributo_arou, atributo_val, atributo_tem, atributo_clase, atributo_si;
    Attribute atributo_ranking, atributo_semanas, atributo_visualizacion, atributo_clase;
    FastVector fv_ranking, fv_semanas, fv_visualizacion, clase, vectorAtributos;
    NaiveBayes cModel;
    Instances isTrainingSet;
    Evaluation eTest;
    FastVector pred;
    List<int[]> datosValorados, datosNoValorados;

    public ClasificadorNBMusicalServicio() {
    }

    public ClasificadorNBMusicalServicio(Integer DISCRETIZAR_RANKING, Integer DISCRETIZAR_SEMANAS, Integer DISCRETIZAR_VISUALIZACIONES, Integer DISCRETIZAR_VALORACION, String EMOCION_POSITIVA, String EMOCION_NEUTRAL, String EMOCION_NEGATIVA, ClasificadorNBMusicalRepositorio clasificadorNBMusicalRepositorio, CancionRepositorio cancionRepositorio, Attribute atributo_ranking, Attribute atributo_semanas, Attribute atributo_visualizacion, Attribute atributo_clase, FastVector fv_ranking, FastVector fv_semanas, FastVector fv_visualizacion, FastVector clase, FastVector vectorAtributos, NaiveBayes cModel, Instances isTrainingSet, Evaluation eTest, FastVector pred, List<int[]> datosValorados, List<int[]> datosNoValorados) {
        this.DISCRETIZAR_RANKING = DISCRETIZAR_RANKING;
        this.DISCRETIZAR_SEMANAS = DISCRETIZAR_SEMANAS;
        this.DISCRETIZAR_VISUALIZACIONES = DISCRETIZAR_VISUALIZACIONES;
        this.DISCRETIZAR_VALORACION = DISCRETIZAR_VALORACION;
        this.EMOCION_POSITIVA = EMOCION_POSITIVA;
        this.EMOCION_NEUTRAL = EMOCION_NEUTRAL;
        this.EMOCION_NEGATIVA = EMOCION_NEGATIVA;
        this.clasificadorNBMusicalRepositorio = clasificadorNBMusicalRepositorio;
        this.cancionRepositorio = cancionRepositorio;
        this.atributo_ranking = atributo_ranking;
        this.atributo_semanas = atributo_semanas;
        this.atributo_visualizacion = atributo_visualizacion;
        this.atributo_clase = atributo_clase;
        this.fv_ranking = fv_ranking;
        this.fv_semanas = fv_semanas;
        this.fv_visualizacion = fv_visualizacion;
        this.clase = clase;
        this.vectorAtributos = vectorAtributos;
        this.cModel = cModel;
        this.isTrainingSet = isTrainingSet;
        this.eTest = eTest;
        this.pred = pred;
        this.datosValorados = datosValorados;
        this.datosNoValorados = datosNoValorados;
    }

    /**
     * Método encargado de definir los atributos del vector de entrenamiento
     * para weka.
     */
    public void definirAtributos() {
        // Declaración del vector para el atributo arousal
        fv_ranking = new FastVector(2);
        fv_ranking.addElement("0");
        fv_ranking.addElement("1");
        atributo_ranking = new Attribute("ranking", fv_ranking);

        // Declaración del vector para el atributo valence
        fv_semanas = new FastVector(2);
        fv_semanas.addElement("0");
        fv_semanas.addElement("1");
        atributo_semanas = new Attribute("semanas", fv_semanas);

        // Declaración del vector para el atributo tempo
        fv_visualizacion = new FastVector(2);
        fv_visualizacion.addElement("0");
        fv_visualizacion.addElement("1");
        atributo_visualizacion = new Attribute("visualizacion", fv_visualizacion);

        // Declaración del vector para el atributo clase o etiqueta a predecir
        clase = new FastVector(2);
        clase.addElement("0");
        clase.addElement("1");
        atributo_clase = new Attribute("valoracion", clase);

        // Declara el número de atributos asignados al vector de entrenamiento
        vectorAtributos = new FastVector(4);
        vectorAtributos.addElement(atributo_ranking);
        vectorAtributos.addElement(atributo_semanas);
        vectorAtributos.addElement(atributo_visualizacion);
        vectorAtributos.addElement(atributo_clase);
    }

    /**
     * Método encargado de cargar el conjunto de datos (Carácteristicas
     * musicales de los contenidos), almacenados en la base de datos al vector
     * de entrenamiento, sin considerar el indice de stress como parámetro de
     * entrada.
     *
     * @param usuario
     */
    public void cargarConjuntoDatosBasico(Integer usuario) {
        List<PropiedadesMusicalesDTO> listaV = clasificadorNBMusicalRepositorio.obtenerPropiedadesMusicalesCancionesValoradas(usuario);
        List<PropiedadesMusicalesDTO> listaNoV = clasificadorNBMusicalRepositorio.obtenerPropiedadesMusicalesCancionesNoValoradas(usuario);
        Double mediaRanking = cancionRepositorio.mediaRanking();
        Double mediaSemanas = cancionRepositorio.mediaSemanas();
        Double mediaVisualizacion = cancionRepositorio.mediaVisualizacion();

        // Se cargan los registros que el usuario ha valorado o visualizado
        datosValorados = discretizarPropiedadesMusicalesCancionesValoradas(listaV, mediaRanking, mediaSemanas, mediaVisualizacion);
        // Se cargan los registros que el usuario no ha valorado o visualizado
        datosNoValorados = discretizarPropiedadesMusicalesCancionesNoValoradas(listaNoV, mediaRanking, mediaSemanas, mediaVisualizacion);

        isTrainingSet = new Instances("Rel", vectorAtributos, (datosValorados.size() + datosNoValorados.size()));
        System.out.println("LA SUMA ES " + (datosValorados.size() + datosNoValorados.size()));
        isTrainingSet.setClassIndex(3);

        // Setencia mediante el cual se cargan el valor de los registros
        // valorados a weka
        Instance inst;
        for (int i = 0; i < datosValorados.size(); i++) {
            inst = new Instance(4) {};
            int temp[] = datosValorados.get(i);
            System.out.println("Mira vos " + Arrays.toString(temp));
            inst.setValue((Attribute) vectorAtributos.elementAt(0), "" + temp[1]);
            inst.setValue((Attribute) vectorAtributos.elementAt(1), "" + temp[2]);
            inst.setValue((Attribute) vectorAtributos.elementAt(2), "" + temp[3]);
            inst.setValue((Attribute) vectorAtributos.elementAt(3), "" + temp[4]);
            isTrainingSet.add(inst);

        }
        System.out.println("El tam1 es " + isTrainingSet.numInstances());

        // Setencia mediante el cual se cargan el valor de los registros no
        // valorados a weka
        Instance inst1;
        for (int i = 0; i < datosNoValorados.size(); i++) {
            inst1 = new Instance(4);
            int temp[] = datosNoValorados.get(i);
            System.out.println("Mira vos1 " + Arrays.toString(temp));
            inst1.setValue((Attribute) vectorAtributos.elementAt(0), "" + temp[1]);
            inst1.setValue((Attribute) vectorAtributos.elementAt(1), "" + temp[2]);
            inst1.setValue((Attribute) vectorAtributos.elementAt(2), "" + temp[3]);
            isTrainingSet.add(inst1);
        }
        System.out.println("El tam2 es " + isTrainingSet.numInstances());
    }

    /**
     * Método encargado de cargar el conjunto de datos (Carácteristicas
     * musicales de los contenidos), almacenados en la base de datos al vector
     * de entrenamiento, considerando el indice de stress (si) como parámetro de
     * entrada.
     *
     * @param usuario
     * @param estado
     */
    public void cargarConjuntoDatosIntermedio(Integer usuario, String estado) {
        List<PropiedadesMusicalesEstadoDTO> listaV = clasificadorNBMusicalRepositorio.obtenerPropiedadesMusicalesCancionesEstadoValoradas(usuario, estado);
        List<PropiedadesMusicalesEstadoDTO> listaNoV = clasificadorNBMusicalRepositorio.obtenerPropiedadesMusicalesCancionesEstadoNoValoradas(usuario, estado);
        Double mediaRanking = cancionRepositorio.mediaRanking();
        Double mediaSemanas = cancionRepositorio.mediaSemanas();
        Double mediaVisualizacion = cancionRepositorio.mediaVisualizacion();

        // Se cargan los registros que el usuario ha valorado o visualizado
        datosValorados = discretizarPropiedadesMusicalesCancionesEstadoValoradas(listaV, mediaRanking, mediaSemanas, mediaVisualizacion, estado);
        // Se cargan los registros que el usuario no ha valorado o visualizado
        datosNoValorados = discretizarPropiedadesMusicalesCancionesEstadoNoValoradas(listaNoV, mediaRanking, mediaSemanas, mediaVisualizacion, estado);

        isTrainingSet = new Instances("Rel", vectorAtributos, (datosValorados.size() + datosNoValorados.size()));
        System.out.println("LA SUMA ES " + (datosValorados.size() + datosNoValorados.size()));
        isTrainingSet.setClassIndex(3);

        // Setencia mediante el cual se cargan el valor de los registros
        // valorados a weka
        Instance inst;
        for (int i = 0; i < datosValorados.size(); i++) {
            inst = new Instance(4);
            int temp[] = datosValorados.get(i);
            System.out.println("Mira vos " + Arrays.toString(temp));
            inst.setValue((Attribute) vectorAtributos.elementAt(0), "" + temp[1]);
            inst.setValue((Attribute) vectorAtributos.elementAt(1), "" + temp[2]);
            inst.setValue((Attribute) vectorAtributos.elementAt(2), "" + temp[3]);
            inst.setValue((Attribute) vectorAtributos.elementAt(3), "" + temp[4]);
            isTrainingSet.add(inst);

        }
        System.out.println("El tam1 es " + isTrainingSet.numInstances());

        // Setencia mediante el cual se cargan el valor de los registros no
        // valorados a weka
        Instance inst1;
        for (int i = 0; i < datosNoValorados.size(); i++) {
            inst1 = new Instance(4);
            int temp[] = datosNoValorados.get(i);
            System.out.println("Mira vos1 " + Arrays.toString(temp));
            inst1.setValue((Attribute) vectorAtributos.elementAt(0), "" + temp[1]);
            inst1.setValue((Attribute) vectorAtributos.elementAt(1), "" + temp[2]);
            inst1.setValue((Attribute) vectorAtributos.elementAt(2), "" + temp[3]);
            isTrainingSet.add(inst1);
        }
        System.out.println("El tam2 es " + isTrainingSet.numInstances());
    }

    /**
     * Método encargado de evaluar el modelo o clasificador (NaiveBayes)
     *
     * @throws Exception
     */
    public void evaluarModelo() throws Exception {
        // Construcción o declaración del modelo
        cModel = new NaiveBayes();
        cModel.buildClassifier(isTrainingSet);

        // Evaluación del modelo, con los datos definidos
        eTest = new Evaluation(isTrainingSet);
        eTest.evaluateModel(cModel, isTrainingSet);
    }

    /**
     * Método encargado de imprimir el resumen de las operaciones y predicciones
     * del modelo evaluadas por weka
     */
    public void obtenerResumen() {
        System.out.println(eTest.toSummaryString());
    }

    /**
     * Método encargado de imprimir la matrix de confusion (matrix encargada de
     * informar el número de aciertos y errores del modelo) del modelo evaluado
     * por weka
     */
    public void obtenerMatrizConfusion() {
        double[][] cmMatrix = eTest.confusionMatrix();
        for (int row_i = 0; row_i < cmMatrix.length; row_i++) {
            for (int col_i = 0; col_i < cmMatrix.length; col_i++) {
                System.out.print(cmMatrix[row_i][col_i]);
                System.out.print("|");
            }
            System.out.println();
        }
    }

    /**
     * Método encargado de listar las predicciones positivas, proporcionadas por
     * el modelo de clasificación en weka
     *
     * @return objetoJSON con las predicciones positivas, conformado por los
     * porcentajes de valoración y el id asociado al contenido
     * @throws JSONException
     */
    public ArrayList<PrediccionDTO> obtenerPrediccionesPositivas() throws JSONException {
        // Cargar prediciones en una Lista de Arreglos
        pred = eTest.predictions();
        System.out.println("***************************");
        System.out.println("Numero de predicciones " + pred.size());
        System.out.println("***************************");

        String cadena;
        ArrayList<PrediccionDTO> lista = new ArrayList<>();
        String tokens[];
        int cont = datosValorados.size();
        int cont1 = 0;
        PrediccionDTO obj;

        while (cont < (datosValorados.size() + datosNoValorados.size())) {
            cadena = pred.elementAt(cont).toString();
            System.out.println((cont1 + 1) + ". Pred " + cadena);

            tokens = cadena.split(" ");
            int temp[] = datosNoValorados.get(cont1);

            if (tokens[2].equals("1.0")) {
                obj = new PrediccionDTO();
                obj.setPred("1");
                obj.setId_contenido(temp[0]);
                obj.setProb0(Double.parseDouble(tokens[4]));
                obj.setProb1(Double.parseDouble(tokens[5]));
                lista.add(obj);
            }
            cont++;
            cont1++;
        }
        System.out.println("***************************");
        Collections.sort(lista, Collections.reverseOrder());
        return lista;
    }

    public ArrayList<PrediccionDTO> obtenerPredicciones() throws JSONException {
        // Cargar prediciones en una Lista de Arreglos
        pred = eTest.predictions();
        System.out.println("***************************");
        System.out.println("Numero de predicciones " + pred.size());
        System.out.println("***************************");

        String cadena;
        ArrayList<PrediccionDTO> lista = new ArrayList<>();
        ArrayList<PrediccionDTO> lista1 = new ArrayList<>();
        String tokens[];
        int cont = 0;
        PrediccionDTO obj;

        // Relleno de nuevo la lista con todos los contenidos
        datosValorados.addAll(datosNoValorados);

        // Registra primero las prediciones con Pred('1')
        while (cont < datosValorados.size()) {
            cadena = pred.elementAt(cont).toString();
            tokens = cadena.split(" ");
            int temp[] = datosValorados.get(cont);
            obj = new PrediccionDTO();
            if (tokens[2].equals("1.0")) {
                obj.setPred("1");
                obj.setId_contenido(temp[0]);
                obj.setProb0(Double.parseDouble(tokens[4]));
                obj.setProb1(Double.parseDouble(tokens[5]));
                lista.add(obj);
            }
            cont++;
        }
        Collections.sort(lista, Collections.reverseOrder());
        cadena = "";
        cont = 0;
        // Registra segundo las prediciones con Pred('0')
        while (cont < datosValorados.size()) {
            cadena = pred.elementAt(cont).toString();
            System.out.println((cont + 1) + ". Pred " + cadena);

            tokens = cadena.split(" ");
            int temp[] = datosValorados.get(cont);
            obj = new PrediccionDTO();
            if (tokens[2].equals("0.0")) {
                obj.setPred("0");
                obj.setId_contenido(temp[0]);
                obj.setProb0(Double.parseDouble(tokens[4]));
                obj.setProb1(Double.parseDouble(tokens[5]));
                lista1.add(obj);
            }
            cont++;
        }
        System.out.println("***************************");
        Collections.sort(lista1);
        lista.addAll(lista1);
        return lista;
    }

    public List<int[]> discretizarPropiedadesMusicalesCancionesValoradas(List<PropiedadesMusicalesDTO> lista, Double mediaRanking, Double mediaSemanas, Double mediaVisualizacion) {
        List datosRetorno = new ArrayList<>();
        Integer ranking, semanas, visualizacion, valoracion;
        System.out.println("************************************");
        System.out.println("******* CONTENIDOS VALORADOS *******");
        for (PropiedadesMusicalesDTO item : lista) {
            System.out.println("************************************");
            System.out.println("IdCancion: " + item.getId());
            System.out.println("Ranking: " + item.getRanking());
            System.out.println("Semanas: " + item.getSemanas());
            System.out.println("Visualización: " + item.getVisualizacion());
            System.out.println("Valoracion: " + item.getValoracion());

            ranking = item.getRanking() < mediaRanking * (DISCRETIZAR_RANKING / 100) ? 1 : 0;
            semanas = item.getSemanas() > mediaSemanas * (DISCRETIZAR_SEMANAS / 100) ? 1 : 0;
            visualizacion = item.getVisualizacion() > mediaVisualizacion * (DISCRETIZAR_VISUALIZACIONES / 100) ? 1 : 0;
            valoracion = item.getValoracion() > DISCRETIZAR_VALORACION ? 1 : 0;

            System.out.println("Ranking-Discretizado: " + ranking);
            System.out.println("Semanas-Discretizado: " + semanas);
            System.out.println("Visualización-Discretizado: " + visualizacion);
            System.out.println("Valoracion-Discretizado: " + valoracion);
            int[] temp = {item.getId(), ranking, semanas, visualizacion, valoracion};
            datosRetorno.add(temp);
        }
        System.out.println("*************** FIN ****************");
        System.out.println("************************************");
        return datosRetorno;
    }

    public List<int[]> discretizarPropiedadesMusicalesCancionesNoValoradas(List<PropiedadesMusicalesDTO> lista, Double mediaRanking, Double mediaSemanas, Double mediaVisualizacion) {
        List datosRetorno = new ArrayList<>();
        Integer ranking, semanas, visualizacion, valoracion;
        System.out.println("************************************");
        System.out.println("******* CONTENIDOS NO VALORADOS *******");
        for (PropiedadesMusicalesDTO item : lista) {
            System.out.println("************************************");
            System.out.println("IdCancion: " + item.getId());
            System.out.println("Ranking: " + item.getRanking());
            System.out.println("Semanas: " + item.getSemanas());
            System.out.println("Visualización: " + item.getVisualizacion());
            System.out.println("Valoracion: " + item.getValoracion());

            ranking = item.getRanking() < mediaRanking * (DISCRETIZAR_RANKING / 100) ? 1 : 0;
            semanas = item.getSemanas() > mediaSemanas * (DISCRETIZAR_SEMANAS / 100) ? 1 : 0;
            visualizacion = item.getVisualizacion() > mediaVisualizacion * (DISCRETIZAR_VISUALIZACIONES / 100) ? 1 : 0;
            valoracion = item.getValoracion() > DISCRETIZAR_VALORACION ? 1 : 0;

            System.out.println("Ranking-Discretizado: " + ranking);
            System.out.println("Semanas-Discretizado: " + semanas);
            System.out.println("Visualización-Discretizado: " + visualizacion);
            System.out.println("Valoracion-Discretizado: " + valoracion);
            int[] temp = {item.getId(), ranking, semanas, visualizacion, valoracion};
            datosRetorno.add(temp);
        }
        System.out.println("*************** FIN ****************");
        System.out.println("************************************");
        return datosRetorno;
    }

    public List<int[]> discretizarPropiedadesMusicalesCancionesEstadoValoradas(List<PropiedadesMusicalesEstadoDTO> lista, Double mediaRanking, Double mediaSemanas, Double mediaVisualizacion, String estado) {
        List datosRetorno = new ArrayList<>();
        Integer ranking, semanas, visualizacion, valoracion;
        System.out.println("************************************");
        System.out.println("******* CONTENIDOS VALORADOS *******");
        for (PropiedadesMusicalesEstadoDTO item : lista) {
            System.out.println("************************************");
            System.out.println("IdCancion: " + item.getId());
            System.out.println("Ranking: " + item.getRanking());
            System.out.println("Semanas: " + item.getSemanas());
            System.out.println("Visualización: " + item.getVisualizacion());
            System.out.println("Valoracion: " + item.getValoracion());
            System.out.println("Estado: " + item.getEstado());

            ranking = item.getRanking() < mediaRanking * (DISCRETIZAR_RANKING / 100) ? 1 : 0;
            semanas = item.getSemanas() > mediaSemanas * (DISCRETIZAR_SEMANAS / 100) ? 1 : 0;
            visualizacion = item.getVisualizacion() > mediaVisualizacion * (DISCRETIZAR_VISUALIZACIONES / 100) ? 1 : 0;
            valoracion = item.getValoracion() > DISCRETIZAR_VALORACION ? 1 : 0;

            System.out.println("Ranking-Discretizado: " + ranking);
            System.out.println("Semanas-Discretizado: " + semanas);
            System.out.println("Visualización-Discretizado: " + visualizacion);
            System.out.println("Valoracion-Discretizado: " + valoracion);
            int[] temp = {item.getId(), ranking, semanas, visualizacion, valoracion};
            if (item.getEstado().equals(EMOCION_POSITIVA) && estado.equals(EMOCION_POSITIVA)) {
                datosRetorno.add(temp);
            } else if (item.getEstado().equals(EMOCION_NEUTRAL) && estado.equals(EMOCION_NEUTRAL)) {
                datosRetorno.add(temp);
            } else if (item.getEstado().equals(EMOCION_NEGATIVA) && estado.equals(EMOCION_NEGATIVA)) {
                datosRetorno.add(temp);
            }
        }
        System.out.println("*************** FIN ****************");
        System.out.println("************************************");
        return datosRetorno;
    }

    public List<int[]> discretizarPropiedadesMusicalesCancionesEstadoNoValoradas(List<PropiedadesMusicalesEstadoDTO> lista, Double mediaRanking, Double mediaSemanas, Double mediaVisualizacion, String estado) {
        List datosRetorno = new ArrayList<>();
        Integer ranking, semanas, visualizacion, valoracion;
        System.out.println("************************************");
        System.out.println("******* CONTENIDOS NO VALORADOS *******");
        for (PropiedadesMusicalesEstadoDTO item : lista) {
            System.out.println("************************************");
            System.out.println("IdCancion: " + item.getId());
            System.out.println("Ranking: " + item.getRanking());
            System.out.println("Semanas: " + item.getSemanas());
            System.out.println("Visualización: " + item.getVisualizacion());
            System.out.println("Valoracion: " + item.getValoracion());
            System.out.println("Estado: " + item.getEstado());

            ranking = item.getRanking() < mediaRanking * (DISCRETIZAR_RANKING / 100) ? 1 : 0;
            semanas = item.getSemanas() > mediaSemanas * (DISCRETIZAR_SEMANAS / 100) ? 1 : 0;
            visualizacion = item.getVisualizacion() > mediaVisualizacion * (DISCRETIZAR_VISUALIZACIONES / 100) ? 1 : 0;
            valoracion = item.getValoracion() > DISCRETIZAR_VALORACION ? 1 : 0;

            System.out.println("Ranking-Discretizado: " + ranking);
            System.out.println("Semanas-Discretizado: " + semanas);
            System.out.println("Visualización-Discretizado: " + visualizacion);
            System.out.println("Valoracion-Discretizado: " + valoracion);
            int[] temp = {item.getId(), ranking, semanas, visualizacion, valoracion};
            if (item.getEstado().equals(EMOCION_POSITIVA) && estado.equals(EMOCION_POSITIVA)) {
                datosRetorno.add(temp);
            } else if (item.getEstado().equals(EMOCION_NEUTRAL) && estado.equals(EMOCION_NEUTRAL)) {
                datosRetorno.add(temp);
            } else if (item.getEstado().equals(EMOCION_NEGATIVA) && estado.equals(EMOCION_NEGATIVA)) {
                datosRetorno.add(temp);
            }
        }
        System.out.println("*************** FIN ****************");
        System.out.println("************************************");
        return datosRetorno;
    }

}
