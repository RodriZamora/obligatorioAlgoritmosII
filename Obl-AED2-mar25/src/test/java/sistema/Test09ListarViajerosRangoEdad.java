package sistema;

import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test09ListarViajerosRangoEdad {
    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);
    }

    @Test
    void listarViajerosCategoriaRango2() {
        s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        s.registrarViajero("1.915.689-6", "Hamilton", "hamilton@ort.edu.uy", 35, Categoria.PLATINO);
        s.registrarViajero("1.913.689-5", "MaxVerstappen", "verstappen@ort.edu.uy", 28, Categoria.PLATINO);
        s.registrarViajero("1.919.689-5", "CharlesLeclerc", "leclerc@ort.edu.uy", 27, Categoria.FRECUENTE);
        retorno = s.listarViajerosDeUnRangoAscendente(2);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("1.913.689-5;MaxVerstappen;verstappen@ort.edu.uy;28;Platino|" +
                        "1.919.689-5;CharlesLeclerc;leclerc@ort.edu.uy;27;Frecuente"
                , retorno.getValorString());
    }

    @Test
    void listarViajerosCategoriaRango3() {
        s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        s.registrarViajero("1.915.689-6", "Hamilton", "hamilton@ort.edu.uy", 35, Categoria.PLATINO);
        s.registrarViajero("1.913.689-5", "MaxVerstappen", "verstappen@ort.edu.uy", 28, Categoria.PLATINO);
        s.registrarViajero("1.919.689-5", "CharlesLeclerc", "leclerc@ort.edu.uy", 27, Categoria.FRECUENTE);
        retorno = s.listarViajerosDeUnRangoAscendente(3);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("1.914.689-5;Guillermo;guille@ort.edu.uy;35;Estándar|" +
                        "1.915.689-6;Hamilton;hamilton@ort.edu.uy;35;Platino"
                , retorno.getValorString());
    }

    @Test
    void listarPorEdadSinViajeros() {
        retorno = s.listarViajerosDeUnRangoAscendente(5);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("", retorno.getValorString());
    }

    @Test
    void listarViajerosCategoriaError1() {
        s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        s.registrarViajero("1.915.689-6", "Hamilton", "hamilton@ort.edu.uy", 35, Categoria.PLATINO);
        s.registrarViajero("1.913.689-5", "MaxVerstappen", "verstappen@ort.edu.uy", 28, Categoria.PLATINO);
        s.registrarViajero("1.919.689-5", "CharlesLeclerc", "leclerc@ort.edu.uy", 27, Categoria.FRECUENTE);
        retorno = s.listarViajerosDeUnRangoAscendente(-1);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        assertEquals("El rango no puede ser menor a 0", retorno.getValorString());

        retorno = s.listarViajerosDeUnRangoAscendente(-500);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        assertEquals("El rango no puede ser menor a 0", retorno.getValorString());
    }

    @Test
    void listarViajerosCategoriaError2() {
        s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        s.registrarViajero("1.915.689-6", "Hamilton", "hamilton@ort.edu.uy", 35, Categoria.PLATINO);
        s.registrarViajero("1.913.689-5", "MaxVerstappen", "verstappen@ort.edu.uy", 28, Categoria.PLATINO);
        s.registrarViajero("1.919.689-5", "CharlesLeclerc", "leclerc@ort.edu.uy", 27, Categoria.FRECUENTE);
        retorno = s.listarViajerosDeUnRangoAscendente(14);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        assertEquals("El rango no puede ser mayor a 13", retorno.getValorString());
    }


}



