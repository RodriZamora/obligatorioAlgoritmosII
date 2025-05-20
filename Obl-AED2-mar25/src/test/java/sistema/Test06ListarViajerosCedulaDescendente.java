package sistema;

import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test06ListarViajerosCedulaDescendente {
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
        retorno = s.listarViajerosPorCedulaDescendente();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("1.919.689-5;CharlesLeclerc;leclerc@ort.edu.uy;27;FRECUENTE|" +
                "1.914.689-6;Hamilton;hamilton@ort.edu.uy;35;PLATINO|" +
                "1.914.689-5;Guillermo;guille@ort.edu.uy;35;ESTANDAR|" +
                "1.913.689-5;MaxVerstappen;verstappen@ort.edu.uy;28;PLATINO", retorno.getValorString());
    }

}
