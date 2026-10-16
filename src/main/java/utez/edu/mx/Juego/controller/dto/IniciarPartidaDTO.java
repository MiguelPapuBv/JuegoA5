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
public class IniciarPartidaDTO {
    @NotNull(message="El id del jugador es obligatorio")
    private Long idJugador;

    @NotNull(message="La apuesta es obligatoria")
    @Positive(message="La apuesta debe ser mayor a 0")
    private Double apuesta;
}