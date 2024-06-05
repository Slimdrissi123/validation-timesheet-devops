package tn.esprit.spring.services;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.repositories.ContratRepository;

import java.util.Optional;
import java.util.Date;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ContratRepository contratRepository;

    private Contrat contrat;

    @BeforeEach
    void setUp() {
        contrat = new Contrat(new Date(), "CDI", 2000f);
        contrat.setReference(1L);
    }

    @Test
    @Order(1)
    void testAddContrat() {
        Contrat savedContrat = userService.addContrat(contrat);
        assertThat(savedContrat).isNotNull();
        assertThat(savedContrat.getReference()).isEqualTo(1L);
    }

    @Test
    @Order(2)
    void testGetContratById() {
        Contrat savedContrat = userService.addContrat(contrat);
        Optional<Contrat> fetchedContrat = contratRepository.findById(savedContrat.getReference());
        assertThat(fetchedContrat).isPresent();
        assertThat(fetchedContrat.get().getReference()).isEqualTo(1L);
    }

    // Add more tests as needed
}
