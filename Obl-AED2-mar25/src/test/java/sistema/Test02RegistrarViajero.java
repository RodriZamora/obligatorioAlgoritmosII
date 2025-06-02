package sistema;

import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test02RegistrarViajero {
    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(10);
    }

    @Test
    void registrarViajeroOk() {
        retorno = s.registrarViajero("1.914.689-5", "MaxVerstappen", "verstappen@ort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    void registrarViajeroError1() {
        retorno = s.registrarViajero("", "MaxVerstappen", "verstappen@ort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarViajero("1.914.689-5", "", "verstappen@ort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarViajero("1.914.689-5", "MaxVerstappen", "", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarViajero(null, "MaxVerstappen", "verstappen@ort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarViajero("1.914.689-5", null, "verstappen@ort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarViajero("1.914.689-5", "MaxVerstappen", null, 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarViajero("1.914.689-5", "MaxVerstappen", "verstappen@ort.edu.uy", 35, null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void registrarViajeroError2() {
        retorno = s.registrarViajero("1.91.4.689-5", "MaxVerstappen", "verstappen@ort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarViajero("1.914.6895", "MaxVerstappen", "verstappen@ort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarViajero("1914.689-5", "MaxVerstappen", "verstappen@ort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void registrarViajeroError3() {
        retorno = s.registrarViajero("1.914.689-5", "MaxVerstappen", "verstappenort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());

        retorno = s.registrarViajero("1.915.689-5", "MaxVerstappen", "verstappen@orteduuy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());

        retorno = s.registrarViajero("1.916.689-5", "MaxVerstappen", "@ort.edu.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());

        retorno = s.registrarViajero("1.914.689-5", "MaxVerstappen", "verstappen@.uy", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());

        retorno = s.registrarViajero("1.914.689-5", "MaxVerstappen", "verstappen@ort.1a", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());

        retorno = s.registrarViajero("1.914.689-5", "MaxVerstappen", "verstappen@ort.edu.uy.", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());

        retorno = s.registrarViajero("1.914.689-5", "MaxVerstappen", "verstappen@ort..edu", 35, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }

    @Test
    void registrarViajeroError4() {
        retorno = s.registrarViajero("1.914.689-5", "MaxVerstappen", "verstappen@ort.edu.uy", -1, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());

        retorno = s.registrarViajero("1.915.689-5", "MaxVerstappen", "verstappen@ort.edu.uy", 140, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());

        retorno = s.registrarViajero("1.916.689-5", "MaxVerstappen", "verstappen@ort.edu.uy", 0, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.registrarViajero("1.917.689-6", "MaxVerstappen", "verstappen1@ort.edu.uy", 139, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    void registrarViajeroError5() {
        retorno = s.registrarViajero("1.914.689-5", "MaxVerstappen", "verstappen@ort.edu.uy", 19, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.registrarViajero("1.914.689-5", "MaxVerstappen", "verstappen@ort.edu.uy", 27, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_5, retorno.getResultado());
    }

    @Test
    void registrarViajeroError6() {
        retorno = s.registrarViajero("1.914.689-5", "MaxVerstappen", "verstappen@ort.edu.uy", 19, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.registrarViajero("1.915.689-6", "MaxVerstappen", "verstappen@ort.edu.uy", 27, Categoria.ESTANDAR);
        assertEquals(Retorno.Resultado.ERROR_6, retorno.getResultado());
    }
}


