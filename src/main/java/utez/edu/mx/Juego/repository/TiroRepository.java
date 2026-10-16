
package utez.edu.mx.Juego.repository;

import utez.edu.mx.Juego.model.Tiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiroRepository extends JpaRepository<Tiro, Long> {
}