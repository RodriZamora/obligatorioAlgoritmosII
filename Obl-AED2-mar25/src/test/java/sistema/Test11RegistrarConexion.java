package sistema;

import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test11RegistrarConexion {
    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(15);
    }

    @Test
    void registrarConexionOk() {
        s.registrarCiudad("COD01", "Montevideo");
        s.registrarCiudad("COD02", "New York");
        retorno = s.registrarConexion("COD01", "COD02");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    void registrarConexionError1() {
        retorno = s.registrarConexion("", "COD02");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        assertEquals("Los campos no pueden ser nulos o vacios", retorno.getValorString());

        retorno = s.registrarConexion("   ", "COD02");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        assertEquals("Los campos no pueden ser nulos o vacios", retorno.getValorString());

        retorno = s.registrarConexion(null, "COD02");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        assertEquals("Los campos no pueden ser nulos o vacios", retorno.getValorString());

        retorno = s.registrarConexion("COD01", "");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        assertEquals("Los campos no pueden ser nulos o vacios", retorno.getValorString());

        retorno = s.registrarConexion("COD01", "   ");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        assertEquals("Los campos no pueden ser nulos o vacios", retorno.getValorString());

        retorno = s.registrarConexion("COD01", null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        assertEquals("Los campos no pueden ser nulos o vacios", retorno.getValorString());

    }

    @Test
    void registrarConexionError2() {
        retorno = s.registrarConexion("COD01", "COD02");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        assertEquals("No existe la ciudad de origen", retorno.getValorString());
    }

    @Test
    void registrarConexionError3() {
        s.registrarCiudad("COD01", "Montevideo");
        retorno = s.registrarConexion("COD01", "COD02");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
        assertEquals("No existe la ciudad de destino", retorno.getValorString());
    }

    @Test
    void registrarConexionError4() {
        s.registrarCiudad("COD01", "Montevideo");
        s.registrarCiudad("COD02", "New York");
        s.registrarConexion("COD01", "COD02");
        retorno = s.registrarConexion("COD01", "COD02");
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());
        assertEquals("Ya existe una conexion entre esas ciudades", retorno.getValorString());
    }


    @Test
    void registrarConexionCompleto() {

        retorno = s.registrarConexion("", "COD02");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        assertEquals("Los campos no pueden ser nulos o vacios", retorno.getValorString());

        retorno = s.registrarConexion("   ", "COD02");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        assertEquals("Los campos no pueden ser nulos o vacios", retorno.getValorString());

        retorno = s.registrarConexion(null, "COD02");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        assertEquals("Los campos no pueden ser nulos o vacios", retorno.getValorString());

        retorno = s.registrarConexion("COD01", "");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        assertEquals("Los campos no pueden ser nulos o vacios", retorno.getValorString());

        retorno = s.registrarConexion("COD01", "   ");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        assertEquals("Los campos no pueden ser nulos o vacios", retorno.getValorString());

        retorno = s.registrarConexion("COD01", null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        assertEquals("Los campos no pueden ser nulos o vacios", retorno.getValorString());

        retorno = s.registrarConexion("COD01", "COD02");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        assertEquals("No existe la ciudad de origen", retorno.getValorString());

        retorno = s.registrarCiudad("COD01", "Ciudad1");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.registrarConexion("COD01", "COD02");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
        assertEquals("No existe la ciudad de destino", retorno.getValorString());

        retorno = s.registrarCiudad("COD02", "Ciudad2");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.registrarConexion("COD01", "COD02");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.registrarConexion("COD01", "COD02");
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());
        assertEquals("Ya existe una conexion entre esas ciudades", retorno.getValorString());

    }


}
