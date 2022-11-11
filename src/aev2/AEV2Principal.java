package aev2;

/**
 * @author Daniel Muñoz Barbera
 *  Metodo principal de la clase AEV2Principal que utilizamos para llamar a todo nuestro programa
 */
public class AEV2Principal {

	public static void main(String[] args) {

		AEV2Vista vista = new AEV2Vista();
		AEV2Modelo modelo = new AEV2Modelo();
		AEV2Controlador controlador = new AEV2Controlador(modelo, vista);

	}

}
