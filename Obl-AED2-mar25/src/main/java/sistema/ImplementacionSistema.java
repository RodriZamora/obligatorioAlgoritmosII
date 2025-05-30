package sistema;

import abb.ABB;
import dominio.*;
import grafo.Conexion;
import grafo.Grafo;
import grafo.Ciudades;
import interfaz.*;

public class ImplementacionSistema implements Sistema {

    private int maxCiudades;
    //private ABB<Ciudad> ciudades;
    private ABB<Viajero> viajerosCedula;
    private ABB<ViajeroWrapper> viajerosCorreo;
    private ABB<Viajero> viajerosPlatinos;
    private ABB<Viajero> viajerosEstandar;
    private ABB<Viajero> viajerosFrecuentes;
    /*private ABB<Viajero> viajeroRango0;
    private ABB<Viajero> viajeroRango1;
    private ABB<Viajero> viajeroRango2;
    private ABB<Viajero> viajeroRango3;
    private ABB<Viajero> viajeroRango4;
    private ABB<Viajero> viajeroRango5;
    private ABB<Viajero> viajeroRango6;
    private ABB<Viajero> viajeroRango7;
    private ABB<Viajero> viajeroRango8;
    private ABB<Viajero> viajeroRango9;
    private ABB<Viajero> viajeroRango10;
    private ABB<Viajero> viajeroRango11;
    private ABB<Viajero> viajeroRango12;
    private ABB<Viajero> viajeroRango13;*/
    private ABB<Viajero>[] viajerosPorRango = new ABB[14];

    private Grafo ciudades;


    private boolean sistemaInicializado = false;


    @Override
    public Retorno inicializarSistema(int maxCiudades) {
        if (maxCiudades <= 4) {
            return Retorno.error1("El maximo de ciudades es 4");
        }

        this.maxCiudades = maxCiudades;
        this.viajerosCedula = new ABB<>();
        this.viajerosCorreo = new ABB<>();
        this.viajerosEstandar = new ABB<>();
        this.viajerosFrecuentes = new ABB<>();
        this.viajerosPlatinos = new ABB<>();
        this.sistemaInicializado = true;
        /*this.viajeroRango0 = new ABB<>();
        this.viajeroRango1 = new ABB<>();
        this.viajeroRango2 = new ABB<>();
        this.viajeroRango3 = new ABB<>();
        this.viajeroRango4 = new ABB<>();
        this.viajeroRango5 = new ABB<>();
        this.viajeroRango6 = new ABB<>();
        this.viajeroRango7 = new ABB<>();
        this.viajeroRango8 = new ABB<>();
        this.viajeroRango9 = new ABB<>();
        this.viajeroRango10 = new ABB<>();
        this.viajeroRango11 = new ABB<>();
        this.viajeroRango12 = new ABB<>();
        this.viajeroRango13 = new ABB<>();*/
        for (int i = 0; i < 14; i++) {
            viajerosPorRango[i] = new ABB<>();
        }

        this.ciudades = new Grafo(maxCiudades, true);

        return Retorno.ok();
    }

    @Override
    public Retorno registrarViajero(String cedula, String nombre, String correo, int edad, Categoria categoria) {
        if (cedula == null || cedula.trim().isEmpty() || nombre == null || nombre.trim().isEmpty() || correo == null || correo.isEmpty() || categoria == null) {
            return Retorno.error1("Los campos no pueden ser nulos o vacios");
        }
        if (!formatoValidoCedula(cedula)) {
            return Retorno.error2("El formato de la cedula no es valido");
        }

        if (!validarCorreo(correo)) {
            return Retorno.error3("El formato del correo no es valido");
        }
        if (edad < 0 || edad > 139) {
            return Retorno.error4("La edad no es valida");
        }

        int cedulaSanitizada = sanitizarCedula(cedula);

        if (existeViajeroCedula(cedulaSanitizada)) {
            return Retorno.error5("Ya existe un viajero con esa cédula");
        }
        if (existeViajeroCorreo(correo)) {
            return Retorno.error6("Ya existe un viajero con ese correo");
        }

        Viajero viajero = new Viajero(cedula, cedulaSanitizada, nombre, correo, edad, categoria);
        viajerosCedula.insertar(viajero);
        insertarSegunCategoria(viajero);

        ViajeroWrapper viajeroCorreo = new ViajeroWrapper(viajero);
        viajerosCorreo.insertar(viajeroCorreo);

        int rango = obtenerRangoEdad(edad);
        obtenerAbbRango(rango).insertar(viajero);
        return Retorno.ok();
    }


    @Override
    public Retorno buscarViajeroPorCedula(String cedula) {
        if (cedula == null || cedula.trim().isEmpty()) {
            return Retorno.error1("La cedula no puede ser nula o vacia");
        }
        if (!formatoValidoCedula(cedula)) {
            return Retorno.error2("El formato de la cedula no es valido");
        }

        int cedulaSanitizada = sanitizarCedula(cedula);
        int[] contador = new int[1];
        Viajero viajero = viajerosCedula.existeConContador(new Viajero(cedulaSanitizada), contador);
        if (viajero == null) {
            return Retorno.error3("No existe un viajero registrado con esa cedula");
        } else {
            return Retorno.ok(contador[0], viajero.toString());
        }
    }

    @Override
    public Retorno buscarViajeroPorCorreo(String correo) {
        if (correo == null || correo.trim().isEmpty()) {
            return Retorno.error1("El correo no puede ser nulo o vacio");
        }
        if (!validarCorreo(correo)) {
            return Retorno.error2("El formato del correo no es valido");
        }

        int[] contador = new int[1];
        ViajeroWrapper viajeroBuscado = new ViajeroWrapper(correo);
        ViajeroWrapper viajero = viajerosCorreo.existeConContador(viajeroBuscado, contador);
        if (viajero == null) {
            return Retorno.error3("No existe un viajero registrado con ese correo");
        } else {
            return Retorno.ok(contador[0], viajero.toString());
        }
    }

    @Override
    public Retorno listarViajerosPorCedulaAscendente() {
        String viajerosPorCedula = viajerosCedula.listarAscendente();
        return Retorno.ok(viajerosPorCedula);
    }

    @Override
    public Retorno listarViajerosPorCedulaDescendente() {
        String viajerosPorCedula = viajerosCedula.listarDescendente();
        return Retorno.ok(viajerosPorCedula);
    }

    @Override
    public Retorno listarViajerosPorCorreoAscendente() {
        String viajerosPorCorreo = viajerosCorreo.listarAscendente();
        return Retorno.ok(viajerosPorCorreo);
    }

    @Override
    public Retorno listarViajerosPorCategoria(Categoria unaCategoria) {
        switch (unaCategoria) {
            case ESTANDAR -> {
                return Retorno.ok(viajerosEstandar.listarAscendente());
            }
            case FRECUENTE -> {
                return Retorno.ok(viajerosFrecuentes.listarAscendente());
            }
            case PLATINO -> {
                return Retorno.ok(viajerosPlatinos.listarAscendente());
            }
            default -> {
                return Retorno.noImplementada();
            }
        }
    }

    @Override
    public Retorno listarViajerosDeUnRangoAscendente(int rango) {
        if (rango < 0) {
            return Retorno.error1("El rango no puede ser menor a 0");
        }
        if (rango > 13) {
            return Retorno.error2("El rango no puede ser mayor a 13");
        }

        ABB<Viajero> viajerosRango = obtenerAbbRango(rango);
        return Retorno.ok(viajerosRango.listarAscendente());
    }


    @Override
    public Retorno registrarCiudad(String codigo, String nombre) {
        if (this.ciudades.cantidadVertices() >= maxCiudades) {
            return Retorno.error1("Ya se alcanzó el máximo de ciudades registradas");
        }
        if (codigo == null || codigo.trim().isEmpty() || nombre == null || nombre.trim().isEmpty()) {
            return Retorno.error2("Los campos no pueden ser nulos o vacios");
        }

        if (existeCiudad(codigo)) {
            return Retorno.error3("Ya existe una ciudad registrada con ese código");
        }

        Ciudad ciudad = new Ciudad(codigo, nombre);
        Ciudades ciudades = new Ciudades(ciudad.getCodigo());
        this.ciudades.agregarVertice(ciudades);
        return Retorno.ok();


    }


    @Override
    public Retorno registrarConexion(String codigoCiudadOrigen, String codigoCiudadDestino) {
        if (codigoCiudadOrigen == null || codigoCiudadDestino == null || codigoCiudadOrigen.trim().isEmpty() || codigoCiudadDestino.trim().isEmpty()) {
            return Retorno.error1("Los campos no pueden ser nulos o vacios");
        }

        if (!existeCiudad(codigoCiudadOrigen)) {
            return Retorno.error2("No existe la ciudad de origen");
        }
        if (!existeCiudad(codigoCiudadDestino)) {
            return Retorno.error3("No existe la ciudad de destino");
        }

        Ciudades cOrigen = new Ciudades(codigoCiudadOrigen);
        Ciudades cDestino = new Ciudades(codigoCiudadDestino);

        if (ciudades.existeArista(cOrigen, cDestino)) {
            return Retorno.error4("Ya existe una conexion entre esas ciudades");
        }
        Conexion conexion = new Conexion();
        ciudades.agregarConexion(cOrigen, cDestino, conexion);
        return Retorno.ok();
    }

    @Override
    public Retorno registrarVuelo(String codigoCiudadOrigen, String codigoCiudadDestino, String codigoDeVuelo, double combustible, double minutos, double costoEnDolares, TipoVuelo tipoDeVuelo) {
        if (combustible <= 0 || minutos <= 0 || costoEnDolares <= 0) {
            return Retorno.error1("Los datos de combustible, minutos y costo en dolares no pueden ser menores o iguales a 0");
        }
        if (codigoCiudadOrigen == null || codigoCiudadDestino == null || codigoDeVuelo == null || codigoCiudadOrigen.trim().isEmpty() || codigoCiudadDestino.trim().isEmpty() || codigoDeVuelo.trim().isEmpty()) {
            return Retorno.error2("Los campos no pueden ser nulos o vacios");
        }
        if (!existeCiudad(codigoCiudadOrigen)) {
            return Retorno.error3("No existe la ciudad de origen");
        }
        if (!existeCiudad(codigoCiudadDestino)) {
            return Retorno.error4("No existe la ciudad de destino");
        }
        Ciudades vOrigen = new Ciudades(codigoCiudadOrigen);
        Ciudades vDestino = new Ciudades(codigoCiudadDestino);
        Conexion conexion = ciudades.obtenerArista(vOrigen, vDestino);
        if (conexion.getExiste()) {
            return Retorno.error5("No existe una conexion entre esas ciudades");
        }

        if (existeVueloEnConexion(codigoCiudadOrigen, codigoCiudadDestino, codigoDeVuelo)) {
            return Retorno.error6("Ya existe un vuelo con ese código en esa conexión");
        }

        Vuelo vuelo = new Vuelo(codigoCiudadOrigen, codigoCiudadDestino, codigoDeVuelo, combustible, minutos, costoEnDolares, tipoDeVuelo);

        return Retorno.ok();
    }

    private boolean existeVueloEnConexion(String codigoCiudadOrigen, String codigoCiudadDestino, String codigoDeVuelo) {
        return true;
    }

    @Override
    public Retorno actualizarVuelo(String codigoCiudadOrigen, String codigoCiudadDestino, String codigoDeVuelo, double combustible, double minutos, double costoEnDolares, TipoVuelo tipoDeVuelo) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listadoCiudadesCantDeEscalas(String codigoCiudadOrigen, int cantidad) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno viajeCostoMinimoMinutos(String codigoCiudadOrigen, String codigoCiudadDestino, TipoVueloPermitido tipoVueloPermitido) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno viajeCostoMinimoDolares(String codigoCiudadOrigen, String codigoCiudadDestino, TipoVueloPermitido tipoVueloPermitido) {
        return Retorno.noImplementada();
    }


    /// METODOS PRIVADOS AUXILIARES//////////

    //eliminar digito verificador
    private int sanitizarCedula(String cedula) {
        return Integer.parseInt(cedula.replaceAll("[.-]", ""));
    }

    private boolean existeViajeroCorreo(String correo) {
        ViajeroWrapper viajeroBuscado = new ViajeroWrapper(correo);
        ViajeroWrapper viajero = viajerosCorreo.existe(viajeroBuscado);
        return viajero != null;
    }

    private boolean existeViajeroCedula(int cedula) {
        Viajero viajero = viajerosCedula.existe(new Viajero(cedula));
        return viajero != null;
    }

    private boolean validarCorreo(String correo) {
        String expresionRegular = "^(?!.*\\.\\.)[\\w.-]+@[\\w-]+(\\.[\\w-]+)*\\.[a-zA-Z]{2,}$";
        return correo.matches(expresionRegular);
    }

    private boolean formatoValidoCedula(String cedula) {
        String expresionRegular = "^(\\d\\.\\d{3}\\.\\d{3}-\\d|\\d{3}\\.\\d{3}-\\d)$";
        return cedula.matches(expresionRegular);
    }

    private void insertarSegunCategoria(Viajero viajero) {
        switch (viajero.getCategoria()) {
            case ESTANDAR:
                viajerosEstandar.insertar(viajero);
                break;
            case FRECUENTE:
                viajerosFrecuentes.insertar(viajero);
                break;
            case PLATINO:
                viajerosPlatinos.insertar(viajero);
                break;
        }
    }

    private ABB<Viajero> obtenerAbbRango(int rango) {
        return viajerosPorRango[rango];
    }

    private int obtenerRangoEdad(int edad) {
        return edad / 10;
    }

    private boolean existeCiudad(String codigo) {
        Ciudades v = new Ciudades(codigo);
        return ciudades.existe(v);
    }

}
