package utez.edu.mx.Juego.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import utez.edu.mx.Juego.controller.dto.RecargarSaldoDTO;
import utez.edu.mx.Juego.model.Jugador;
import utez.edu.mx.Juego.repository.JugadorRepository;
import utez.edu.mx.Juego.exception.customException.BadRequestException;

@Service
public class JugadorService {

    private final JugadorRepository jugadorRepository;

    public JugadorService(JugadorRepository jugadorRepository) {
        this.jugadorRepository = jugadorRepository;
    }

    @Transactional
    public Jugador recargarSaldo(RequestRecargarSaldoDTO payload) {
        Jugador jugador = jugadorRepository.findById(payload.getIdJugador())
                .orElseThrow(() -> new BadRequestException("El jugador no existe"));

        jugador.setSaldo(jugador.getSaldo() + payload.getMonto());
        return jugadorRepository.save(jugador);
    }
}