package tn.esprit.spring.control;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.services.EmployeServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeServiceImpl employeService;

    @Test
    public void getAllEmployesTest() throws Exception {
        Employe employe = new Employe();
        employe.setId(1L);
        employe.setNom("John Doe");
        employe.setEmail("john.doe@example.com");

        List<Employe> employes = Arrays.asList(employe);

        given(employeService.getAllEmployes()).willReturn(employes);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/employes")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"nom\":\"John Doe\",\"email\":\"john.doe@example.com\"}]"));
    }

    @Test
    public void getEmployeByIdTest() throws Exception {
        Employe employe = new Employe();
        employe.setId(1L);
        employe.setNom("John Doe");
        employe.setEmail("john.doe@example.com");

        given(employeService.getEmployeById(1L)).willReturn(employe);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/employes/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"nom\":\"John Doe\",\"email\":\"john.doe@example.com\"}"));
    }

}