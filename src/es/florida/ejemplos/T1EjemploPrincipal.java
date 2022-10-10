package es.florida.ejemplos;

public class T1EjemploPrincipal {

	public static void main(String[] args) {
		T1EjemploVista vista = new T1EjemploVista();
		T1EjemploModelo modelo = new T1EjemploModelo();
		T1EjemploControlador controlador = new T1EjemploControlador(modelo,vista);

	}

}
