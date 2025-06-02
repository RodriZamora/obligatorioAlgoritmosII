package sistema;

import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test03BuscarViajero {
    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);
    }

    @Test
    void buscarViajeroOk() {
        s.registrarViajero("1.914.689-5", "LewisHamilton", "hamilton@ort.edu.uy", 35, Categoria.ESTANDAR);
        s.registrarViajero("1.913.689-5", "MaxVerstappen", "verstappen@ort.edu.uy", 28, Categoria.PLATINO);
        s.registrarViajero("1.919.689-5", "CharlesLeclerc", "leclerc@ort.edu.uy", 27, Categoria.FRECUENTE);
        retorno = s.buscarViajeroPorCedula("1.913.689-5");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(2, retorno.getValorInteger());
        assertEquals("1.913.689-5;MaxVerstappen;verstappen@ort.edu.uy;28;Platino", retorno.getValorString());
    }

    @Test
    void buscarViajeroError1() {
        s.registrarViajero("1.914.689-5", "LewisHamilton", "hamilton@ort.edu.uy", 35, Categoria.ESTANDAR);
        s.registrarViajero("1.913.689-5", "MaxVerstappen", "verstappen@ort.edu.uy", 28, Categoria.PLATINO);
        s.registrarViajero("1.919.689-5", "CharlesLeclerc", "leclerc@ort.edu.uy", 27, Categoria.FRECUENTE);
        retorno = s.buscarViajeroPorCedula("");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        assertEquals(0, retorno.getValorInteger());
        assertEquals("La cedula no puede ser nula o vacia", retorno.getValorString());

        retorno = s.buscarViajeroPorCedula(null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        assertEquals(0, retorno.getValorInteger());
        assertEquals("La cedula no puede ser nula o vacia", retorno.getValorString());
    }

    @Test
    void buscarViajeroError2() {
        s.registrarViajero("1.914.689-5", "LewisHamilton", "hamilton@ort.edu.uy", 35, Categoria.ESTANDAR);
        s.registrarViajero("1.913.689-5", "MaxVerstappen", "verstappen@ort.edu.uy", 28, Categoria.PLATINO);
        s.registrarViajero("1.919.689-5", "CharlesLeclerc", "leclerc@ort.edu.uy", 27, Categoria.FRECUENTE);
        retorno = s.buscarViajeroPorCedula("1.91.4.689-5");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        assertEquals(0, retorno.getValorInteger());
        assertEquals("El formato de la cedula no es valido", retorno.getValorString());

        retorno = s.buscarViajeroPorCedula("1.914.6895");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        assertEquals(0, retorno.getValorInteger());
        assertEquals("El formato de la cedula no es valido", retorno.getValorString());

        retorno = s.buscarViajeroPorCedula("1914.689-5");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        assertEquals(0, retorno.getValorInteger());
        assertEquals("El formato de la cedula no es valido", retorno.getValorString());
    }

    @Test
    void buscarViajeroError3() {
        s.registrarViajero("1.914.689-5", "LewisHamilton", "hamilton@ort.edu.uy", 35, Categoria.ESTANDAR);
        s.registrarViajero("1.913.689-5", "MaxVerstappen", "verstappen@ort.edu.uy", 28, Categoria.PLATINO);
        s.registrarViajero("1.919.689-5", "CharlesLeclerc", "leclerc@ort.edu.uy", 27, Categoria.FRECUENTE);

        retorno = s.buscarViajeroPorCedula("4.745.897-9");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
        assertEquals(0, retorno.getValorInteger());
        assertEquals("No existe un viajero registrado con esa cedula", retorno.getValorString());

    }

}
