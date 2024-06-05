package tn.esprit.spring.services;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.repositories.ContratRepository;

import java.util.Date;
import java.util.Optional;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class ContratServiceImplMock {

    @Mock
    private ContratRepository contratRepository;

    @InjectMocks
    private ContratService contratService;

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
        Contrat savedContrat = contratService.addContrat(contrat);
        assertThat(savedContrat).isNotNull();
        assertThat(savedContrat.getReference()).isEqualTo(1L);
        verify(contratRepository).save(contrat);
    }

    @Test
    @Order(2)
    void testGetContratById() {
        when(contratRepository.findById(1L)).thenReturn(Optional.of(contrat));
        Optional<Contrat> fetchedContrat = contratService.getContratById(1L);
        assertThat(fetchedContrat).isPresent();
        assertThat(fetchedContrat.get().getReference()).isEqualTo(1L);
        verify(contratRepository).findById(1L);
    }

    @Test
    @Order(3)
    void testDeleteContrat() {
        contratService.deleteContrat(1L);
        verify(contratRepository).deleteById(1L);
    }
}
