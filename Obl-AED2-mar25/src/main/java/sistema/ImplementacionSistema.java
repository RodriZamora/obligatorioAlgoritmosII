package sistema;

import abb.ABB;
import dominio.Ciudad;
import dominio.ComparadorPorCedula;
import dominio.ComparadorPorCorreo;
import dominio.Viajero;
import interfaz.*;

public class ImplementacionSistema implements Sistema {

    private int maxCiudades;
    private ABB<Ciudad> ciudades;
    ABB<Viajero> viajerosCedula;
    ABB<Viajero> viajerosCorreo;// = new ABB<>(new ComparadorPorCorreo());
    private boolean sistemaInicializado = false;


    @Override
    public Retorno inicializarSistema(int maxCiudades) {
        if (maxCiudades <= 4) {
            return Retorno.error1("El maximo de ciudades es 4");
        }

        this.maxCiudades = maxCiudades;
        this.ciudades = new ABB<>();
        this.viajerosCedula = new ABB<>(new ComparadorPorCedula());
        this.viajerosCorreo = new ABB<>(new ComparadorPorCorreo());
        this.sistemaInicializado = true;
        return Retorno.ok();
    }

    @Override
    public Retorno registrarViajero(String cedula, String nombre, String correo, int edad, Categoria categoria) {
        if (cedula == null || cedula.isEmpty() || nombre == null || nombre.isEmpty() || correo == null || correo.isEmpty() || categoria == null) {
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
        if (existeViajeroCedula(cedula)) {
            return Retorno.error5("Ya existe un viajero con esa cédula");
        }
        if (existeViajeroCorreo(correo)) {
            return Retorno.error6("Ya existe un viajero con ese correo");
        }

        Viajero viajero = new Viajero(cedula, nombre, correo, edad, categoria);
        viajerosCedula.insertar(viajero);
        viajerosCorreo.insertar(viajero);
        return Retorno.ok();
    }

    private boolean existeViajeroCorreo(String correo) {
        Viajero viajero = viajerosCorreo.existe(new Viajero(null, null, correo, 0, null));
        return viajero != null;
    }

    private boolean existeViajeroCedula(String cedula) {
        Viajero viajero = viajerosCedula.existe(new Viajero(cedula, null, null, 0, null));
        return viajero != null;
    }

    private boolean validarCorreo(String correo) {
        String expresionRegular = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return correo.matches(expresionRegular);
    }

    private boolean formatoValidoCedula(String cedula) {
        String expresionRegular = "^(\\d\\.\\d{3}\\.\\d{3}-\\d|\\d{3}\\.\\d{3}-\\d)$";
        return cedula.matches(expresionRegular);
    }


    @Override
    public Retorno buscarViajeroPorCedula(String cedula) {
        if (cedula == null || cedula.isEmpty()) {
            return Retorno.error1("La cedula no puede ser nula o vacia");
        }
        if (!formatoValidoCedula(cedula)) {
            return Retorno.error2("El formato de la cedula no es valido");
        }

        int[] contador = new int[1];
        Viajero viajero = viajerosCedula.existeConContador(new Viajero(cedula, "", "", 0, null), contador);
        if (viajero == null) {
            return Retorno.error3("No existe un viajero registrado con esa cedula");
        } else {
            return Retorno.ok(contador[0], viajero.toString());
        }
    }

    @Override
    public Retorno buscarViajeroPorCorreo(String correo) {
        if(correo == null || correo.isEmpty()){
            return Retorno.error1("El correo no puede ser nulo o vacio");
        }
        if(!validarCorreo(correo)){
            return Retorno.error2("El formato del correo no es valido");
        }

        int[] contador = new int[1];
        Viajero viajero = viajerosCorreo.existeConContador(new Viajero("", "", correo, 0, null), contador);
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
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarViajerosPorCorreoAscendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarViajerosPorCategoria(Categoria unaCategoria) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarViajerosDeUnRangoAscendente(int rango) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarCiudad(String codigo, String nombre) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarConexion(String codigoCiudadOrigen, String codigoCiudadDestino) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarVuelo(String codigoCiudadOrigen, String codigoCiudadDestino, String codigoDeVuelo, double combustible, double minutos, double costoEnDolares, TipoVuelo tipoDeVuelo) {
        return Retorno.noImplementada();
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

}
