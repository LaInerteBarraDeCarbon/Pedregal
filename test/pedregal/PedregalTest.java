package pedregal;

import org.junit.Test;

public class PedregalTest {

	private static String archivoIn = "Preparacion de Prueba/Lote de Prueba/Entrada/";
	private static String archivoOut = "Ejecucion de Prueba/Salida Obtenida/";
	
	@Test
	public void test() {
		Pedregal pedregal = new Pedregal(archivoIn + "00_Enunciado.in");
		pedregal.calcula();
		pedregal.grabarArchivo(archivoOut + "00_Enunciado.out");
	}

}
