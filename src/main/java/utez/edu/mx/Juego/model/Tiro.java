package mx.edu.utez.Juego.model.tiro;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.proyecto1C.model.partida.Partida;

@Entity
@Table(name="tiros")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tiro {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="partida_id")
    private Partida partida;

    private int valorDado1;
    private int valorDado2;
    private int suma;
    private boolean esGanador;
}