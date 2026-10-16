package utez.edu.mx.Juego.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import utez.edu.mx.Juego.controller.dto.IniciarPartidaDTO;
import utez.edu.mx.Juego.controller.dto.RealizarTiroDTO;
import utez.edu.mx.Juego.model.EstadoPartida;
import utez.edu.mx.Juego.model.Jugador;
import utez.edu.mx.Juego.model.Partida;
import utez.edu.mx.Juego.model.Tiro;
import utez.edu.mx.Juego.repository.JugadorRepository;
import utez.edu.mx.Juego.repository.PartidaRepository;
import utez.edu.mx.Juego.repository.TiroRepository;
import utez.edu.mx.Juego.exception.customException.BadRequestException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class PartidaService {

    private final PartidaRepository partidaRepository;
    private final JugadorRepository jugadorRepository;
    private final TiroRepository tiroRepository;

    public PartidaService(PartidaRepository partidaRepository, JugadorRepository jugadorRepository, TiroRepository tiroRepository) {
        this.partidaRepository = partidaRepository;
        this.jugadorRepository = jugadorRepository;
        this.tiroRepository = tiroRepository;
    }

    @Transactional
    public Partida iniciarPartida(RequestIniciarPartidaDTO payload) {
        Jugador jugador = jugadorRepository.findById(payload.getIdJugador())
                .orElseThrow(() -> new BadRequestException("El jugador no existe"));

        if (!jugador.isActivo()) {
            throw new BadRequestException("El jugador no está activo");
        }
        if (jugador.getSaldo() < payload.getApuesta()) {
            throw new BadRequestException("Saldo insuficiente para esta apuesta");
        }

        jugador.setSaldo(jugador.getSaldo() - payload.getApuesta());
        jugadorRepository.save(jugador);

        Partida nuevaPartida = new Partida();
        nuevaPartida.setFecha(LocalDateTime.now());
        nuevaPartida.setEstado(EstadoPartida.EN_JUEGO);
        nuevaPartida.setJugador(jugador);
        nuevaPartida.setApuesta(payload.getApuesta());

        return partidaRepository.save(nuevaPartida);
    }

    @Transactional
    public Tiro realizarTiro(RequestRealizarTiroDTO payload) {
        Partida partida = partidaRepository.findById(payload.getIdPartida())
                .orElseThrow(() -> new BadRequestException("La partida no existe"));

        if (partida.getEstado() != EstadoPartida.EN_JUEGO) {
            throw new BadRequestException("Esta partida ya finalizó");
        }

        Random random = new Random();
        int dado1 = random.nextInt(6) + 1;
        int dado2 = random.nextInt(6) + 1;
        int suma = dado1 + dado2;
        boolean ganador = false;

        if (suma == 7 || suma == 11) {
            ganador = true;
            partida.setEstado(EstadoPartida.FINALIZADA);
            
            // Gana el doble
            Jugador jugador = partida.getJugador();
            jugador.setSaldo(jugador.getSaldo() + (partida.getApuesta() * 2));
            jugadorRepository.save(jugador);
        } else if (suma == 2 || suma == 3 || suma == 12) {
            partida.setEstado(EstadoPartida.FINALIZADA);
        } 
        
        partidaRepository.save(partida);

        Tiro nuevoTiro = new Tiro();
        nuevoTiro.setPartida(partida);
        nuevoTiro.setValorDado1(dado1);
        nuevoTiro.setValorDado2(dado2);
        nuevoTiro.setSuma(suma);
        nuevoTiro.setEsGanador(ganador);

        return tiroRepository.save(nuevoTiro);
    }

    @Transactional
    public Partida finalizarPartidaManual(Long id) {
        Partida partida = partidaRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("La partida no existe"));

        partida.setEstado(EstadoPartida.FINALIZADA);
        return partidaRepository.save(partida);
    }

    public List<Partida> obtenerHistorialPartida(Long idJugador) {
        return partidaRepository.findByJugadorId(idJugador);
    }
}