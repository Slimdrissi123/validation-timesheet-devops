package tn.esprit.spring.services;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.repositories.ContratRepository;

import java.util.Optional;
import java.util.Date;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class UserServiceImplMock {

    @Mock
    private ContratRepository contratRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private Contrat contrat;

    @BeforeEach
    void setUp() {
        contrat = new Contrat(new Date(), "CDI", 2000f);
        contrat.setReference(1L);
    }

    @Test
    @Order(1)
    void testAddContrat() {
        when(contratRepository.save(contrat)).thenReturn(contrat);
        Contrat savedContrat = userService.addContrat(contrat);
        verify(contratRepository).save(contrat);
    }

    @Test
    @Order(2)
    void testGetContratById() {
        when(contratRepository.findById(1L)).thenReturn(Optional.of(contrat));
        Contrat fetchedContrat = userService.getContratById(1L);
        verify(contratRepository).findById(1L);
    }

    // Add more tests as needed
}
