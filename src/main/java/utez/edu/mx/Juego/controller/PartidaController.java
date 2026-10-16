package utez.edu.mx.Juego.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.Juego.controller.dto.IniciarPartidaDTO;
import utez.edu.mx.Juego.controller.dto.RealizarTiroDTO;
import utez.edu.mx.Juego.model.Partida;
import utez.edu.mx.Juego.model.Tiro;
import utez.edu.mx.Juego.service.PartidaService;

import java.util.List;
@RestController
@RequestMapping("/partida")
@CrossOrigin({"*"})
public class PartidaController {

    private final PartidaService partidaService;

    public PartidaController(PartidaService partidaService) {
        this.partidaService = partidaService;
    }

    @PostMapping("/iniciar")
    public ResponseEntity<Partida> iniciarPartida(@Valid @RequestBody RequestIniciarPartidaDTO payload) {
        return ResponseEntity
                .status(200)
                .body(this.partidaService.iniciarPartida(payload));
    }

    @PostMapping("/tiro")
    public ResponseEntity<Tiro> realizarTiro(@Valid @RequestBody RequestRealizarTiroDTO payload) {
        return ResponseEntity
                .status(200)
                .body(this.partidaService.realizarTiro(payload));
    }

    @PutMapping("/{id}/finalizar")
    public ResponseEntity<Partida> finalizarPartidaManual(@PathVariable Long id) {
        return ResponseEntity
                .status(200)
                .body(this.partidaService.finalizarPartidaManual(id));
    }

    @GetMapping("/historial/{jugadorId}")
    public ResponseEntity<List<Partida>> obtenerHistorialPartida(@PathVariable Long jugadorId) {
        return ResponseEntity
                .status(200)
                .body(this.partidaService.obtenerHistorialPartida(jugadorId));
    }
}