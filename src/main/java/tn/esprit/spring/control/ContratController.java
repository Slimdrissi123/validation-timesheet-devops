package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.services.ContratService;

import java.util.Optional;

@RestController
@RequestMapping("/api/contrats")
public class ContratController {

    @Autowired
    private ContratService contratService;

//http://localhost:192.168.56.2/api/contrats
    @PostMapping
    public ResponseEntity<Contrat> addContrat(@RequestBody Contrat contrat) {
        Contrat savedContrat = contratService.addContrat(contrat);
        return ResponseEntity.ok(savedContrat);
    }

//http://localhost:192.168.56.2/api/contrats/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Contrat> getContratById(@PathVariable Long id) {
        Optional<Contrat> contrat = contratService.getContratById(id);
        if (contrat.isPresent()) {
            return ResponseEntity.ok(contrat.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//http://localhost:192.168.56.2/api/contrats/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContrat(@PathVariable Long id) {
        Optional<Contrat> contrat = contratService.getContratById(id);
        if (contrat.isPresent()) {
            contratService.deleteContrat(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
