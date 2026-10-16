package utez.edu.mx.Juego.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.Juego.controller.dto.RecargarSaldoDTO;
import utez.edu.mx.Juego.model.Jugador;
import utez.edu.mx.Juego.service.JugadorService;

@RestController
@RequestMapping("/jugador")
@CrossOrigin({"*"})
public class JugadorController {

    private final JugadorService jugadorService;

    public JugadorController(JugadorService jugadorService) {
        this.jugadorService = jugadorService;
    }

    @PutMapping("/recargar")
    public ResponseEntity<Jugador> recargarSaldo(@Valid @RequestBody RequestRecargarSaldoDTO payload) {
        return ResponseEntity
                .status(200)
                .body(this.jugadorService.recargarSaldo(payload));
    }
}