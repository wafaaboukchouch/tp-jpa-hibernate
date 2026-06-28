package ma.projet.tp_jpa.repositories;

import ma.projet.tp_jpa.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
}
