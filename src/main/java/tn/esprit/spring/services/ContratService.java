// Path: src/main/java/tn/esprit/spring/services/ContratService.java
package tn.esprit.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.repositories.ContratRepository;

import java.util.Optional;

@Service
public class ContratService {

    @Autowired
    private ContratRepository contratRepository;

    public Contrat addContrat(Contrat contrat) {
        return contratRepository.save(contrat);
    }

    public Optional<Contrat> getContratById(Long id) {
        return contratRepository.findById(id);
    }

    public void deleteContrat(Long id) {
        contratRepository.deleteById(id);
    }
}
