package utez.edu.mx.Juego.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RealizarTiroDTO {
    @NotNull(message="El id de la partida es obligatorio")
    private Long idPartida;
}