package sistema;

import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test10RegistrarCiudad {
    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(15);
    }

    @Test
    void registrarCiudadOk() {
        retorno = s.registrarCiudad("COD01", "Montevideo");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

    }

    @Test
    void registrarCiudadError1() {
        s.inicializarSistema(5);
        s.registrarCiudad("COD01", "Montevideo");
        s.registrarCiudad("COD02", "San Jose");
        s.registrarCiudad("COD03", "Maldonado");
        s.registrarCiudad("COD04", "Canelones");
        s.registrarCiudad("COD05", "Colonia");
        retorno = s.registrarCiudad("COD06", "Rocha");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        assertEquals("Ya se alcanzó el máximo de ciudades registradas", retorno.getValorString());
    }

    @Test
    void registrarCiudadError2() {
        retorno = s.registrarCiudad("", null);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        assertEquals("Los campos no pueden ser nulos o vacios", retorno.getValorString());

        retorno = s.registrarCiudad("", "Montevideo");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        assertEquals("Los campos no pueden ser nulos o vacios", retorno.getValorString());

        retorno = s.registrarCiudad("   ", "Montevideo");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        assertEquals("Los campos no pueden ser nulos o vacios", retorno.getValorString());

        retorno = s.registrarCiudad(null, "Montevideo");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        assertEquals("Los campos no pueden ser nulos o vacios", retorno.getValorString());

        retorno = s.registrarCiudad("COD01", "");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        assertEquals("Los campos no pueden ser nulos o vacios", retorno.getValorString());

        retorno = s.registrarCiudad("COD01", "   ");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        assertEquals("Los campos no pueden ser nulos o vacios", retorno.getValorString());

        retorno = s.registrarCiudad("COD01", null);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        assertEquals("Los campos no pueden ser nulos o vacios", retorno.getValorString());
    }

    @Test
    void registrarCiudadError3() {
        s.registrarCiudad("COD01", "Montevideo");
        retorno = s.registrarCiudad("COD01", "Paysandú");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
        assertEquals("Ya existe una ciudad registrada con ese código", retorno.getValorString());
    }

    @Test
    void registrarCiudadCompleto() {

        retorno = s.registrarCiudad("", "Montevideo");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        assertEquals("Los campos no pueden ser nulos o vacios", retorno.getValorString());

        retorno = s.registrarCiudad("   ", "Montevideo");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        assertEquals("Los campos no pueden ser nulos o vacios", retorno.getValorString());

        retorno = s.registrarCiudad(null, "Montevideo");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        assertEquals("Los campos no pueden ser nulos o vacios", retorno.getValorString());

        retorno = s.registrarCiudad("COD01", "");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        assertEquals("Los campos no pueden ser nulos o vacios", retorno.getValorString());

        retorno = s.registrarCiudad("COD01", "   ");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        assertEquals("Los campos no pueden ser nulos o vacios", retorno.getValorString());

        retorno = s.registrarCiudad("COD01", null);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        assertEquals("Los campos no pueden ser nulos o vacios", retorno.getValorString());

        retorno = s.registrarCiudad("COD01", "Montevideo");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.registrarCiudad("COD02", "Maldonado");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.registrarCiudad("COD02", "Maldonado");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
        assertEquals("Ya existe una ciudad registrada con ese código", retorno.getValorString());

        retorno = s.registrarCiudad("COD16", "New York");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

    }


}
