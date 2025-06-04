package sistema;

import interfaz.Retorno;
import interfaz.Sistema;
import interfaz.TipoVuelo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static sistema.Salidas.*;

public class Test14CiudadesPorCantidadEscalas {
    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(30);
    }

    @Test
    void ciudadesPorCantidadEscalasError1() {
        retorno = s.listadoCiudadesCantDeEscalas("COD01", -1);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.listadoCiudadesCantDeEscalas("COD01", -5);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.listadoCiudadesCantDeEscalas("COD01", -100);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void ciudadesPorCantidadEscalasError2() {
        retorno = s.listadoCiudadesCantDeEscalas("", 3);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.listadoCiudadesCantDeEscalas("   ", 3);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.listadoCiudadesCantDeEscalas(null, 3);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void ciudadesPorCantidadEscalasError3() {
        retorno = s.listadoCiudadesCantDeEscalas("COD01", 0);
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());

        retorno = s.listadoCiudadesCantDeEscalas("COD02", 5);
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }

    @Test
    void ciudadesPorCantidadEscalasSoloUnaCiudad() {
        s.registrarCiudad("COD01", "Ciudad1");
        retorno = s.listadoCiudadesCantDeEscalas("COD01", 0);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(ciudad1, retorno.getValorString());

        s.registrarCiudad("COD01", "Ciudad1");
        retorno = s.listadoCiudadesCantDeEscalas("COD01", 5);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(ciudad1, retorno.getValorString());

        s.registrarCiudad("COD01", "Ciudad1");
        retorno = s.listadoCiudadesCantDeEscalas("COD01", 10);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(ciudad1, retorno.getValorString());
    }

    @Test
    void ciudadesPorCantidadEscalasDosCiudades() {
        s.registrarCiudad("COD01", "Ciudad1");
        s.registrarCiudad("COD02", "Ciudad2");
        s.registrarConexion("COD01", "COD02");
        retorno = s.listadoCiudadesCantDeEscalas("COD01", 0);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(ciudad1, retorno.getValorString());

        //Conexion sin vuelos (debe tener al menos 1 vuelo)
        retorno = s.listadoCiudadesCantDeEscalas("COD01", 1);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(ciudad1, retorno.getValorString());

        s.registrarVuelo("COD01", "COD02", "codigo1", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        retorno = s.listadoCiudadesCantDeEscalas("COD01", 1);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(ciudad1 + separador + ciudad2, retorno.getValorString());
    }

    @Test
    void ciudadesPorCantidadEscalasCompleto() {
        s.registrarCiudad("COD01", "Montevideo");
        s.registrarCiudad("COD02", "New York");
        s.registrarCiudad("COD03", "Miami");
        s.registrarCiudad("COD04", "Houston");
        s.registrarCiudad("COD05", "Maldonado");
        s.registrarCiudad("COD06", "Medellin");
        s.registrarCiudad("COD07", "Cartagena");
        s.registrarCiudad("COD08", "New Jersey");
        s.registrarCiudad("COD09", "Madrid");
        s.registrarCiudad("COD10", "Mallorca");
        s.registrarCiudad("COD11", "Barcelona");
        s.registrarCiudad("COD12", "Buenos Aires");
        s.registrarCiudad("COD13", "Rio de Janeiro");
        s.registrarCiudad("COD14", "Florianopolis");
        s.registrarCiudad("COD15", "Washington");
        s.registrarCiudad("COD16", "Colorado Springs");
        s.registrarCiudad("COD17", "Pensylvania");
        s.registrarCiudad("COD18", "Los Angeles");
        s.registrarCiudad("COD19", "California");
        s.registrarCiudad("COD20", "San Diego");
        s.registrarCiudad("COD21", "Bogota");
        s.registrarCiudad("COD22", "Ciudad de Mexico");
        s.registrarCiudad("COD23", "Ciudad de Panama");

        //Distancia 1
        s.registrarConexion("COD01", "COD02");
        s.registrarVuelo("COD01", "COD02", "codigo1", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        s.registrarConexion("COD01", "COD16");
        s.registrarVuelo("COD01", "COD16", "codigo2", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        s.registrarConexion("COD01", "COD07");
        s.registrarVuelo("COD01", "COD07", "codigo3", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        s.registrarConexion("COD01", "COD06");
        s.registrarVuelo("COD01", "COD06", "codigo4", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        s.registrarConexion("COD01", "COD17");
        s.registrarVuelo("COD01", "COD17", "codigo5", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        s.registrarConexion("COD01", "COD05");
        s.registrarVuelo("COD01", "COD05", "codigo6", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        s.registrarConexion("COD01", "COD18");
        s.registrarVuelo("COD01", "COD18", "codigo7", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        s.registrarConexion("COD01", "COD04");
        s.registrarVuelo("COD01", "COD04", "codigo8", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        s.registrarConexion("COD01", "COD23");
        s.registrarVuelo("COD01", "COD23", "codigo9", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        s.registrarConexion("COD01", "COD03");
        s.registrarVuelo("COD01", "COD03", "codigo10", 5000, 360, 1500, TipoVuelo.COMERCIAL);

        retorno = s.listadoCiudadesCantDeEscalas("COD01", 0);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("COD01;Montevideo", retorno.getValorString());

        retorno = s.listadoCiudadesCantDeEscalas("COD01", 1);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("COD01;Montevideo|COD02;New York|COD03;Miami|COD04;Houston|COD05;Maldonado|COD06;Medellin|COD07;Cartagena|COD16;Colorado Springs|COD17;Pensylvania|COD18;Los Angeles|COD23;Ciudad de Panama", retorno.getValorString());

        //Distancia2
        s.registrarConexion("COD16", "COD21");
        s.registrarVuelo("COD16", "COD21", "codigo11", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        s.registrarConexion("COD02", "COD08");
        s.registrarVuelo("COD02", "COD08", "codigo12", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        s.registrarConexion("COD02", "COD09");
        s.registrarVuelo("COD02", "COD09", "codigo13", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        s.registrarConexion("COD03", "COD10");
        s.registrarVuelo("COD03", "COD10", "codigo14", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        s.registrarConexion("COD04", "COD11");
        s.registrarVuelo("COD04", "COD11", "codigo15", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        s.registrarConexion("COD04", "COD12");
        s.registrarVuelo("COD04", "COD12", "codigo16", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        s.registrarConexion("COD18", "COD19");
        s.registrarVuelo("COD18", "COD19", "codigo17", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        s.registrarConexion("COD05", "COD20");
        s.registrarVuelo("COD05", "COD20", "codigo18", 5000, 360, 1500, TipoVuelo.COMERCIAL);

        //Distasncia3
        s.registrarConexion("COD08", "COD13");
        s.registrarVuelo("COD08", "COD13", "codigo19", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        s.registrarConexion("COD09", "COD14");
        s.registrarVuelo("COD09", "COD14", "codigo20", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        s.registrarConexion("COD10", "COD15");
        s.registrarVuelo("COD10", "COD15", "codigo21", 5000, 360, 1500, TipoVuelo.COMERCIAL);
        s.registrarConexion("COD11", "COD22");
        s.registrarVuelo("COD11", "COD22", "codigo22", 5000, 360, 1500, TipoVuelo.COMERCIAL);

        retorno = s.listadoCiudadesCantDeEscalas("COD01", 0);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("COD01;Montevideo", retorno.getValorString());

        retorno = s.listadoCiudadesCantDeEscalas("COD01", 1);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("COD01;Montevideo|COD02;New York|COD03;Miami|COD04;Houston|COD05;Maldonado|COD06;Medellin|COD07;Cartagena|COD16;Colorado Springs|COD17;Pensylvania|COD18;Los Angeles|COD23;Ciudad de Panama", retorno.getValorString());

        retorno = s.listadoCiudadesCantDeEscalas("COD01", 2);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("COD01;Montevideo|COD02;New York|COD03;Miami|COD04;Houston|COD05;Maldonado|COD06;Medellin|COD07;Cartagena|COD08;New Jersey|COD09;Madrid|COD10;Mallorca|COD11;Barcelona|COD12;Buenos Aires|COD16;Colorado Springs|COD17;Pensylvania|COD18;Los Angeles|COD19;California|COD20;San Diego|COD21;Bogota|COD23;Ciudad de Panama", retorno.getValorString());

        retorno = s.listadoCiudadesCantDeEscalas("COD01", 3);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("COD01;Montevideo|COD02;New York|COD03;Miami|COD04;Houston|COD05;Maldonado|COD06;Medellin|COD07;Cartagena|COD08;New Jersey|COD09;Madrid|COD10;Mallorca|COD11;Barcelona|COD12;Buenos Aires|COD13;Rio de Janeiro|COD14;Florianopolis|COD15;Washington|COD16;Colorado Springs|COD17;Pensylvania|COD18;Los Angeles|COD19;California|COD20;San Diego|COD21;Bogota|COD22;Ciudad de Mexico|COD23;Ciudad de Panama", retorno.getValorString());

        retorno = s.listadoCiudadesCantDeEscalas("COD01", 5);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("COD01;Montevideo|COD02;New York|COD03;Miami|COD04;Houston|COD05;Maldonado|COD06;Medellin|COD07;Cartagena|COD08;New Jersey|COD09;Madrid|COD10;Mallorca|COD11;Barcelona|COD12;Buenos Aires|COD13;Rio de Janeiro|COD14;Florianopolis|COD15;Washington|COD16;Colorado Springs|COD17;Pensylvania|COD18;Los Angeles|COD19;California|COD20;San Diego|COD21;Bogota|COD22;Ciudad de Mexico|COD23;Ciudad de Panama", retorno.getValorString());

        s.registrarCiudad("COD24", "Colonia");
        s.registrarCiudad("COD25", "Caracas");
        s.registrarConexion("COD24", "COD25");
        s.registrarVuelo("COD24", "COD25", "codigo", 5000, 360, 1500, TipoVuelo.COMERCIAL);

        retorno = s.listadoCiudadesCantDeEscalas("COD01", 5);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("COD01;Montevideo|COD02;New York|COD03;Miami|COD04;Houston|COD05;Maldonado|COD06;Medellin|COD07;Cartagena|COD08;New Jersey|COD09;Madrid|COD10;Mallorca|COD11;Barcelona|COD12;Buenos Aires|COD13;Rio de Janeiro|COD14;Florianopolis|COD15;Washington|COD16;Colorado Springs|COD17;Pensylvania|COD18;Los Angeles|COD19;California|COD20;San Diego|COD21;Bogota|COD22;Ciudad de Mexico|COD23;Ciudad de Panama", retorno.getValorString());

        retorno = s.listadoCiudadesCantDeEscalas("COD02", 5);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("COD02;New York|COD08;New Jersey|COD09;Madrid|COD13;Rio de Janeiro|COD14;Florianopolis", retorno.getValorString());
    }

}
