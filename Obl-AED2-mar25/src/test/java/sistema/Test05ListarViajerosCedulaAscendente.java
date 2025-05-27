/*package sistema;

import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test05ListarViajerosCedulaAscendente {
    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);
    }

    @Test
    void listarViajerosCedula() {
        s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        s.registrarViajero("1.914.689-6", "Hamilton", "hamilton@ort.edu.uy", 35, Categoria.PLATINO);
        s.registrarViajero("1.913.689-5", "MaxVerstappen", "verstappen@ort.edu.uy", 28, Categoria.PLATINO);
        s.registrarViajero("1.919.689-5", "CharlesLeclerc", "leclerc@ort.edu.uy", 27, Categoria.FRECUENTE);
        retorno = s.listarViajerosPorCedulaAscendente();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("1.913.689-5;MaxVerstappen;verstappen@ort.edu.uy;28;PLATINO|" +
                "1.914.689-5;Guillermo;guille@ort.edu.uy;35;ESTANDAR|" +
                "1.914.689-6;Hamilton;hamilton@ort.edu.uy;35;PLATINO|" +
                "1.919.689-5;CharlesLeclerc;leclerc@ort.edu.uy;27;FRECUENTE", retorno.getValorString());
    }

}*/




package sistema;

import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static sistema.Salidas.*;

public class Test05ListarViajerosCedulaAscendente {
    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();



    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);
    }

    @Test
    void listarVacio() {
        retorno = s.listarViajerosPorCedulaAscendente();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("", retorno.getValorString());
    }

    @Test
    void listar1Viajero() {
        s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        retorno = s.listarViajerosPorCedulaAscendente();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(guille, retorno.getValorString());
    }

    @Test
    void listarSolo2Viajeros() {
        s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        s.registrarViajero("2.614.689-5", "Ana", "ana@ort.edu.uy", 25, Categoria.PLATINO);
        retorno = s.listarViajerosPorCedulaAscendente();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(guille+ separador +ana, retorno.getValorString());
    }


    @Test
    void listarPorCedulaCrecienteNumerica() {
        s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        s.registrarViajero("614.689-5", "Pedro", "pedro@ort.edu.uy", 75, Categoria.FRECUENTE);

        String salidaEsperada = pedro+ separador +guille;
        retorno = s.listarViajerosPorCedulaAscendente();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(salidaEsperada, retorno.getValorString());

        s.registrarViajero("2.614.689-5", "Ana", "ana@ort.edu.uy", 25, Categoria.PLATINO);
        s.registrarViajero("3.614.689-5", "María", "maria@ort.edu.uy", 45, Categoria.ESTANDAR);

        salidaEsperada = pedro+ separador +guille+ separador +ana+ separador +maria;
        retorno = s.listarViajerosPorCedulaAscendente();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(salidaEsperada, retorno.getValorString());

    }





}
