package ma.projet.tp_jpa.repositories;

import ma.projet.tp_jpa.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByNomContains(String keyword);
    List<Patient> findByMalade(boolean malade);
}
