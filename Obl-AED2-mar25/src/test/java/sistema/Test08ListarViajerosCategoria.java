package sistema;

import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test08ListarViajerosCategoria {
    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);
    }

    @Test
    void listarSinViajeros() {
        retorno = s.listarViajerosPorCategoria(Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("", retorno.getValorString());

        s.registrarViajero("1.913.689-5", "MaxVerstappen", "verstappen@ort.edu.uy", 27, Categoria.PLATINO);
        retorno = s.listarViajerosPorCategoria(Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("", retorno.getValorString());
    }

    @Test
    void listarUnViajero() {
        s.registrarViajero("1.913.689-5", "MaxVerstappen", "verstappen@ort.edu.uy", 27, Categoria.PLATINO);
        retorno = s.listarViajerosPorCategoria(Categoria.PLATINO);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("1.913.689-5;MaxVerstappen;verstappen@ort.edu.uy;27;Platino", retorno.getValorString());
    }

    @Test
    void listarViajerosPlatinos(){
        s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.PLATINO);
        s.registrarViajero("1.915.689-6", "Hamilton", "hamilton@ort.edu.uy", 35, Categoria.PLATINO);
        s.registrarViajero("1.913.689-5", "MaxVerstappen", "verstappen@ort.edu.uy", 27, Categoria.PLATINO);
        s.registrarViajero("1.919.689-5", "CharlesLeclerc", "leclerc@ort.edu.uy", 27, Categoria.PLATINO);
        retorno = s.listarViajerosPorCategoria(Categoria.PLATINO);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("1.913.689-5;MaxVerstappen;verstappen@ort.edu.uy;27;Platino|" +
                        "1.914.689-5;Guillermo;guille@ort.edu.uy;35;Platino|"+
                        "1.915.689-6;Hamilton;hamilton@ort.edu.uy;35;Platino|" +
                        "1.919.689-5;CharlesLeclerc;leclerc@ort.edu.uy;27;Platino"
                , retorno.getValorString());
    }

    @Test
    void listarViajerosCategoria() {
        s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        s.registrarViajero("1.915.689-6", "Hamilton", "hamilton@ort.edu.uy", 35, Categoria.PLATINO);
        s.registrarViajero("1.913.689-5", "MaxVerstappen", "verstappen@ort.edu.uy", 27, Categoria.PLATINO);
        s.registrarViajero("1.919.689-5", "CharlesLeclerc", "leclerc@ort.edu.uy", 27, Categoria.FRECUENTE);
        retorno = s.listarViajerosPorCategoria(Categoria.PLATINO);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("1.913.689-5;MaxVerstappen;verstappen@ort.edu.uy;27;Platino|" +
                        "1.915.689-6;Hamilton;hamilton@ort.edu.uy;35;Platino"
                , retorno.getValorString());
    }

}


