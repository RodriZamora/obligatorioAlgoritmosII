package sistema;

import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test04BuscarViajeroCorreo {
    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);
    }

    @Test
    void buscarViajeroOk() {
        s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        s.registrarViajero("1.914.687-6", "Hamilton", "hamilton@ort.edu.uy", 35, Categoria.PLATINO);
        s.registrarViajero("1.913.689-5", "MaxVerstappen", "verstappen@ort.edu.uy", 28, Categoria.PLATINO);
        s.registrarViajero("1.919.689-5", "CharlesLeclerc", "leclerc@ort.edu.uy", 27, Categoria.FRECUENTE);
        retorno = s.buscarViajeroPorCorreo("verstappen@ort.edu.uy");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(3, retorno.getValorInteger());
        assertEquals("1.913.689-5;MaxVerstappen;verstappen@ort.edu.uy;28;Platino", retorno.getValorString());
    }

    @Test
    void buscarViajeroError1() {
        s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        s.registrarViajero("1.914.689-6", "Hamilton", "hamilton@ort.edu.uy", 35, Categoria.PLATINO);
        s.registrarViajero("1.913.689-5", "MaxVerstappen", "verstappen@ort.edu.uy", 28, Categoria.PLATINO);
        s.registrarViajero("1.919.689-5", "CharlesLeclerc", "leclerc@ort.edu.uy", 27, Categoria.FRECUENTE);

        retorno = s.buscarViajeroPorCorreo("");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.buscarViajeroPorCorreo(null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

    }

    @Test
    void buscarViajeroError2() {
        s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        s.registrarViajero("1.914.689-6", "Hamilton", "hamilton@ort.edu.uy", 35, Categoria.PLATINO);
        s.registrarViajero("1.913.689-5", "MaxVerstappen", "verstappen@ort.edu.uy", 28, Categoria.PLATINO);
        s.registrarViajero("1.919.689-5", "CharlesLeclerc", "leclerc@ort.edu.uy", 27, Categoria.FRECUENTE);

        retorno = s.buscarViajeroPorCorreo("guille@ort.edu.uy.");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.buscarViajeroPorCorreo("guille@orteduuy");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.buscarViajeroPorCorreo("@ort.edu.uy");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.buscarViajeroPorCorreo("guille@.uy");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.buscarViajeroPorCorreo("guille@ort.1a");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.buscarViajeroPorCorreo("guille@ort.edu.uy.");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.buscarViajeroPorCorreo("guille@ort..edu");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

    }

    @Test
    void buscarViajeroError3() {
        s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        s.registrarViajero("1.914.689-6", "Hamilton", "hamilton@ort.edu.uy", 35, Categoria.PLATINO);
        s.registrarViajero("1.913.689-5", "MaxVerstappen", "verstappen@ort.edu.uy", 28, Categoria.PLATINO);
        s.registrarViajero("1.919.689-5", "CharlesLeclerc", "leclerc@ort.edu.uy", 27, Categoria.FRECUENTE);

        retorno = s.buscarViajeroPorCorreo("maxverstappen@ort.edu.uy");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }

}

