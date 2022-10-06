package es.florida.aev1;

public class AEV1Principal {

	public static void main(String[] args) {
		
		AEV1Vista vista = new AEV1Vista();
		AEV1Modelo modelo = new AEV1Modelo();
		AEV1Controlador controlador = new AEV1Controlador(modelo, vista);
		
	}

}
