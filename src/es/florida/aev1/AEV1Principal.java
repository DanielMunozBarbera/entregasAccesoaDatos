package es.florida.aev1;

/**
 * @author Daniel Muñoz Barbera - Clase principal en la cual se ejecuta nuestro
 *         programa, utiliza una estancia del resto de clases: AEV1Vista,
 *         AEV1Controlador y AEV1Modelo
 *
 */
public class AEV1Principal {

	public static void main(String[] args) {

		AEV1Vista vista = new AEV1Vista();
		AEV1Modelo modelo = new AEV1Modelo();
		AEV1Controlador controlador = new AEV1Controlador(modelo, vista);

	}

}
