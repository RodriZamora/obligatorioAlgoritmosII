package sistema;

import interfaz.Retorno;
import interfaz.Sistema;
import interfaz.TipoVuelo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test12RegistrarVuelo {
    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(15);
    }

    @Test
    void registrarVueloOk() {
        s.registrarCiudad("COD01", "Ciudad1");
        s.registrarCiudad("COD02", "Ciudad2");
        s.registrarConexion("COD01", "COD02");
        retorno = s.registrarVuelo("COD01", "COD02", "codigo1", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    void registrarVueloError1() {
        retorno = s.registrarVuelo("COD01", "COD02", "codigo1", -10, 360, 1500, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        assertEquals("Los datos de combustible, minutos y costo en dolares no pueden ser menores o iguales a 0", retorno.getValorString());

        retorno = s.registrarVuelo("COD01", "COD02", "codigo1", 0, 360, 1500, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        assertEquals("Los datos de combustible, minutos y costo en dolares no pueden ser menores o iguales a 0", retorno.getValorString());

        retorno = s.registrarVuelo("COD01", "COD02", "codigo1", 5000, -10, 1500, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        assertEquals("Los datos de combustible, minutos y costo en dolares no pueden ser menores o iguales a 0", retorno.getValorString());

        retorno = s.registrarVuelo("COD01", "COD02", "codigo1", 5000, 0, 1500, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        assertEquals("Los datos de combustible, minutos y costo en dolares no pueden ser menores o iguales a 0", retorno.getValorString());

        retorno = s.registrarVuelo("COD01", "COD02", "codigo1", 5000, 360, -10, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        assertEquals("Los datos de combustible, minutos y costo en dolares no pueden ser menores o iguales a 0", retorno.getValorString());

    }

    @Test
    void registrarVueloError2() {
        retorno = s.registrarVuelo("", "COD02", "codigo1", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        assertEquals("Los campos no pueden ser nulos o vacios", retorno.getValorString());

        retorno = s.registrarVuelo("  ", "COD02", "codigo1", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        assertEquals("Los campos no pueden ser nulos o vacios", retorno.getValorString());

        retorno = s.registrarVuelo(null, "COD02", "codigo1", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        assertEquals("Los campos no pueden ser nulos o vacios", retorno.getValorString());

        retorno = s.registrarVuelo("COD01", "", "codigo1", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        assertEquals("Los campos no pueden ser nulos o vacios", retorno.getValorString());

        retorno = s.registrarVuelo("COD01", "   ", "codigo1", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        assertEquals("Los campos no pueden ser nulos o vacios", retorno.getValorString());

        retorno = s.registrarVuelo("COD01", null, "codigo1", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        assertEquals("Los campos no pueden ser nulos o vacios", retorno.getValorString());

        retorno = s.registrarVuelo("COD01", "COD02", "", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        assertEquals("Los campos no pueden ser nulos o vacios", retorno.getValorString());

        retorno = s.registrarVuelo("COD01", "COD02", "   ", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        assertEquals("Los campos no pueden ser nulos o vacios", retorno.getValorString());

        retorno = s.registrarVuelo("COD01", "COD02", null, 5000, 360, 1500, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        assertEquals("Los campos no pueden ser nulos o vacios", retorno.getValorString());

        retorno = s.registrarVuelo("COD01", "COD02", "codigo1", 5000, 360, 1500, null);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        assertEquals("Los campos no pueden ser nulos o vacios", retorno.getValorString());

    }

    @Test
    void registrarVueloError3() {
        retorno = s.registrarVuelo("COD01", "COD02", "codigo1", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
        assertEquals("No existe la ciudad de origen", retorno.getValorString());
    }

    @Test
    void registrarVueloError4() {
        s.registrarCiudad("COD01", "Montevideo");
        retorno = s.registrarVuelo("COD01", "COD02", "codigo1", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());
        assertEquals("No existe la ciudad de destino", retorno.getValorString());
    }

    @Test
    void registrarVueloError5() {
        s.registrarCiudad("COD01", "Montevideo");
        s.registrarCiudad("COD02", "New York");
        retorno = s.registrarVuelo("COD01", "COD02", "codigo1", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_5, retorno.getResultado());
        assertEquals("No existe una conexion entre esas ciudades", retorno.getValorString());
    }

    @Test
    void registrarVueloError6() {
        s.registrarCiudad("COD01", "Montevideo");
        s.registrarCiudad("COD02", "New York");
        s.registrarConexion("COD01", "COD02");
        s.registrarVuelo("COD01", "COD02", "codigo1", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        retorno = s.registrarVuelo("COD01", "COD02", "codigo1", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_6, retorno.getResultado());
        assertEquals("Ya existe un vuelo con ese código en esa conexión", retorno.getValorString());
    }

    @Test
    void registrarVueloCompleto() {
        retorno = s.registrarVuelo("COD01", "COD02", "codigo1", -10, 360, 1500, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        assertEquals("Los datos de combustible, minutos y costo en dolares no pueden ser menores o iguales a 0", retorno.getValorString());

        retorno = s.registrarVuelo("COD01", "COD02", "codigo1", 0, 360, 1500, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        assertEquals("Los datos de combustible, minutos y costo en dolares no pueden ser menores o iguales a 0", retorno.getValorString());

        retorno = s.registrarVuelo("COD01", "COD02", "codigo1", 5000, -10, 1500, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        assertEquals("Los datos de combustible, minutos y costo en dolares no pueden ser menores o iguales a 0", retorno.getValorString());

        retorno = s.registrarVuelo("COD01", "COD02", "codigo1", 5000, 0, 1500, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        assertEquals("Los datos de combustible, minutos y costo en dolares no pueden ser menores o iguales a 0", retorno.getValorString());

        retorno = s.registrarVuelo("COD01", "COD02", "codigo1", 5000, 360, -10, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        assertEquals("Los datos de combustible, minutos y costo en dolares no pueden ser menores o iguales a 0", retorno.getValorString());

        retorno = s.registrarVuelo("  ", "COD02", "codigo1", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        assertEquals("Los campos no pueden ser nulos o vacios", retorno.getValorString());

        retorno = s.registrarVuelo(null, "COD02", "codigo1", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        assertEquals("Los campos no pueden ser nulos o vacios", retorno.getValorString());

        retorno = s.registrarVuelo("COD01", "", "codigo1", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        assertEquals("Los campos no pueden ser nulos o vacios", retorno.getValorString());

        retorno = s.registrarVuelo("COD01", "   ", "codigo1", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        assertEquals("Los campos no pueden ser nulos o vacios", retorno.getValorString());

        retorno = s.registrarVuelo("COD01", null, "codigo1", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        assertEquals("Los campos no pueden ser nulos o vacios", retorno.getValorString());

        retorno = s.registrarVuelo("COD01", "COD02", "", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        assertEquals("Los campos no pueden ser nulos o vacios", retorno.getValorString());

        retorno = s.registrarVuelo("COD01", "COD02", "   ", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        assertEquals("Los campos no pueden ser nulos o vacios", retorno.getValorString());

        retorno = s.registrarVuelo("COD01", "COD02", null, 5000, 360, 1500, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        assertEquals("Los campos no pueden ser nulos o vacios", retorno.getValorString());

        retorno = s.registrarVuelo("COD01", "COD02", "codigo1", 5000, 360, 1500, null);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        assertEquals("Los campos no pueden ser nulos o vacios", retorno.getValorString());

        retorno = s.registrarVuelo("COD01", "COD02", "codigo1", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
        assertEquals("No existe la ciudad de origen", retorno.getValorString());

        s.registrarCiudad("COD01", "Ciudad1");

        retorno = s.registrarVuelo("COD01", "COD02", "codigo1", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());
        assertEquals("No existe la ciudad de destino", retorno.getValorString());

        s.registrarCiudad("COD02", "Ciudad2");

        retorno = s.registrarVuelo("COD01", "COD02", "codigo1", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_5, retorno.getResultado());
        assertEquals("No existe una conexion entre esas ciudades", retorno.getValorString());

        s.registrarConexion("COD01", "COD02");

        retorno = s.registrarVuelo("COD01", "COD02", "codigo1", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.registrarVuelo("COD01", "COD02", "codigo1", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        assertEquals(Retorno.Resultado.ERROR_6, retorno.getResultado());
        assertEquals("Ya existe un vuelo con ese código en esa conexión", retorno.getValorString());

    }
}
