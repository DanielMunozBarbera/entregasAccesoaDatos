package aev3;


/**
 * @author Daniel Muñoz Barbera
 *	Clase principal de nuestro Modelo Vista Controlador, se encarga de iniciar todo el programa.
 */
public class AEV3Principal {
	/**
	 * @param args
	 * Metodo main de la clase AEV3Principal
	 */
	public static void main(String[] args) {

		AEV3Vista vista = new AEV3Vista();
		AEV3Modelo modelo = new AEV3Modelo();
		AEV3Controlador controlador = new AEV3Controlador(modelo,vista);

	}
}
