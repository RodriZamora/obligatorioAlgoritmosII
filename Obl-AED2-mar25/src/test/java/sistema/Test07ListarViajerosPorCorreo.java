/*package sistema;

import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test07ListarViajerosPorCorreo {
    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);
    }

    @Test
    void listarViajerosCorreo() {
        s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        s.registrarViajero("1.914.689-6", "Hamilton", "hamilton@ort.edu.uy", 35, Categoria.PLATINO);
        s.registrarViajero("1.913.689-5", "MaxVerstappen", "verstappen@ort.edu.uy", 28, Categoria.PLATINO);
        s.registrarViajero("1.919.689-5", "CharlesLeclerc", "leclerc@ort.edu.uy", 27, Categoria.FRECUENTE);
        retorno = s.listarViajerosPorCorreoAscendente();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("1.914.689-5;Guillermo;guille@ort.edu.uy;35;ESTANDAR|" +
                "1.914.689-6;Hamilton;hamilton@ort.edu.uy;35;PLATINO|" +
                "1.919.689-5;CharlesLeclerc;leclerc@ort.edu.uy;27;FRECUENTE|" +
                "1.913.689-5;MaxVerstappen;verstappen@ort.edu.uy;28;PLATINO", retorno.getValorString());
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

public class Test07ListarViajerosPorCorreo {
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
        retorno = s.listarViajerosPorCorreoAscendente();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(guille, retorno.getValorString());
    }

    @Test
    void listarSolo2Viajeros() {
        s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        s.registrarViajero("2.614.689-5", "Ana", "ana@ort.edu.uy", 25, Categoria.PLATINO);
        retorno = s.listarViajerosPorCorreoAscendente();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(ana+ separador +guille, retorno.getValorString());
    }


    @Test
    void listarPorCorreoCreciente() {
        s.registrarViajero("1.914.689-5", "Guillermo", "guille@ort.edu.uy", 35, Categoria.ESTANDAR);
        s.registrarViajero("614.689-5", "Pedro", "pedro@ort.edu.uy", 75, Categoria.FRECUENTE);

        String salidaEsperada = guille+ separador +pedro;
        retorno = s.listarViajerosPorCorreoAscendente();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(salidaEsperada, retorno.getValorString());

        s.registrarViajero("2.614.689-5", "Ana", "ana@ort.edu.uy", 25, Categoria.PLATINO);
        s.registrarViajero("3.614.689-5", "María", "maria@ort.edu.uy", 45, Categoria.ESTANDAR);

        salidaEsperada = ana+ separador +guille+ separador +maria+ separador +pedro;
        retorno = s.listarViajerosPorCorreoAscendente();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(salidaEsperada, retorno.getValorString());

    }





}

