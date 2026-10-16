package utez.edu.mx.Juego.exception;

// Excepción personalizada: se lanza cuando no se cumple una regla de negocio
public class ReglaNegocioException extends RuntimeException {

    public ReglaNegocioException(String mensaje) {
        super(mensaje);
    }
}
