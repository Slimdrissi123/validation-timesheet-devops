// Path: src/main/java/tn/esprit/spring/repositories/ContratRepository.java
package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entities.Contrat;

public interface ContratRepository extends JpaRepository<Contrat, Long> {
}
