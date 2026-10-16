package utez.edu.mx.Juego.controller.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecargarSaldoDTO {
    @NotNull(message="El id del jugador es obligatorio")
    private Long idJugador;

    @NotNull(message="El monto es obligatorio")
    @Positive(message="El monto a recargar debe ser mayor a 0")
    private Double monto;
}